package practicaenequipocristianvictoraitornico.players.storage

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.lighthousegames.logging.logging
import practicaenequipocristianvictoraitornico.players.dto.EntrenadorDto
import practicaenequipocristianvictoraitornico.players.dto.JugadorDto
import practicaenequipocristianvictoraitornico.players.exception.PersonasException
import practicaenequipocristianvictoraitornico.players.mappers.PersonaMapper
import practicaenequipocristianvictoraitornico.players.models.Entrenadores
import practicaenequipocristianvictoraitornico.players.models.Jugadores
import practicaenequipocristianvictoraitornico.players.models.Persona
import java.io.File
import java.io.RandomAccessFile

/**
 * Esta es la implementación de la interfaz PersonalStorage.kt para leer y escribir datos de un listado de personas en formato BIN.
 */

class PersonalStorageBin : PersonalStorage {
    private val logger = logging()
    private val personalMapper = PersonaMapper()

    init {
        logger.debug { "Inicializando almacenamiento de personal en Bin" }
    }

    /**
     * Lee el personal de un fichero Bin
     * @param file Fichero Bin
     * @return Lista de personas
     * @throws PersonasException.PersonasStorageException Si el fichero no existe, no es un fichero o no se puede leer
     */
    override fun leerDelArchivo(file: File): Result<List<Persona>,PersonasException> {
        logger.debug { "Leyendo personal de fichero Bin: $file" }
        if (!file.exists() || !file.isFile || !file.canRead() || file.length() == 0L || !file.name.endsWith(".bin", true)) {
            logger.error { "El fichero no existe, o no es un fichero o no se puede leer: $file" }
            Err(PersonasException.PersonasStorageException("El fichero no existe, o no es un fichero o no se puede leer: $file"))
        }

        val personal = mutableListOf<Persona>()

        //lectura de datos
        RandomAccessFile(file, "r").use { raf ->
            while (raf.filePointer < raf.length()) {
                val rol = raf.readUTF()
                val id = raf.readLong()
                val nombre = raf.readUTF()
                val apellidos = raf.readUTF()
                val fechaNacimiento = raf.readUTF()
                val fechaIncorporacion = raf.readUTF()
                val salario = raf.readDouble()
                val pais = raf.readUTF()

                if (rol == "Jugador") {
                    val posicion = raf.readUTF()
                    val dorsal = raf.readInt()
                    val altura = raf.readDouble()
                    val peso = raf.readDouble()
                    val goles = raf.readInt()
                    val partidosJugados = raf.readInt()

                    val jugadorDto = JugadorDto(
                        id, nombre, apellidos, fechaNacimiento, fechaIncorporacion, salario, pais,
                        posicion, dorsal, altura, peso, goles, partidosJugados
                    )
                    personal.add(personalMapper.toModel(jugadorDto))
                } else if (rol == "Entrenador") {
                    val especialidad = raf.readUTF()

                    val entrenadorDto = EntrenadorDto(
                        id, nombre, apellidos, fechaNacimiento, fechaIncorporacion, salario, pais, especialidad
                    )
                    personal.add(personalMapper.toModel(entrenadorDto))
                }
            }
        }
        return Ok(personal)
    }

    /**
     * Escribe las personas en un fichero Bin
     * @param persona Lista de personas
     * @param file Fichero bin
     * @throws PersonasException.PersonasStorageException Si el directorio padre del fichero no existe
     */
    override fun escribirAUnArchivo(file: File, persona: List<Persona>): Result<String,PersonasException> {
        logger.debug { "Escribiendo personal en fichero Bin: $file" }
        if (!file.parentFile.exists() || !file.parentFile.isDirectory || !file.name.endsWith(".bin", true)) {
            logger.error { "El directorio padre del fichero no existe: ${file.parentFile.absolutePath}" }
            return Err(PersonasException.PersonasStorageException("El directorio padre del fichero no existe: ${file.parentFile.absolutePath}"))
        }

        RandomAccessFile(file, "rw").use { raf ->
            raf.setLength(0) // impia el archivo antes de escribir
            for (personal in persona) {
                when (personal) {
                    is Jugadores -> {
                        raf.writeUTF("Jugador")
                        raf.writeLong(personal.id)
                        raf.writeUTF(personal.nombre)
                        raf.writeUTF(personal.apellidos)
                        raf.writeUTF(personal.fechaNacimiento.toString())
                        raf.writeUTF(personal.fechaIncorporacion.toString())
                        raf.writeDouble(personal.salario)
                        raf.writeUTF(personal.pais)
                        raf.writeUTF(personal.posicion.name)
                        raf.writeInt(personal.dorsal)
                        raf.writeDouble(personal.altura)
                        raf.writeDouble(personal.peso)
                        raf.writeInt(personal.goles)
                        raf.writeInt(personal.partidosJugados)
                    }
                    is Entrenadores -> {
                        raf.writeUTF("Entrenador")
                        raf.writeLong(personal.id)
                        raf.writeUTF(personal.nombre)
                        raf.writeUTF(personal.apellidos)
                        raf.writeUTF(personal.fechaNacimiento.toString())
                        raf.writeUTF(personal.fechaIncorporacion.toString())
                        raf.writeDouble(personal.salario)
                        raf.writeUTF(personal.pais)
                        raf.writeUTF(personal.especialidad.name)
                    }
                }
            }
        }
        return Ok("guardado con exito")
    }
}