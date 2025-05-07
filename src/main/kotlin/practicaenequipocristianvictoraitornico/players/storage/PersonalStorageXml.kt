package practicaenequipocristianvictoraitornico.players.storage

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import nl.adaptivity.xmlutil.serialization.XML
import org.lighthousegames.logging.logging
import practicaenequipocristianvictoraitornico.players.dto.EntrenadorDto
import practicaenequipocristianvictoraitornico.players.dto.EquipoDtoXml
import practicaenequipocristianvictoraitornico.players.dto.JugadorDto
import practicaenequipocristianvictoraitornico.players.dto.PersonalDtoXml
import practicaenequipocristianvictoraitornico.players.exception.PersonasException
import practicaenequipocristianvictoraitornico.players.mappers.PersonaMapper
import practicaenequipocristianvictoraitornico.players.models.Entrenadores
import practicaenequipocristianvictoraitornico.players.models.Jugadores
import practicaenequipocristianvictoraitornico.players.models.Persona
import java.io.File

/**
 * Esta es la implementación de la interfaz PersonalStorage.kt para leer y escribir datos de un listado de personas en formato XML.
 */
class PersonalStorageXml: PersonalStorage {

    private val logger = logging()
    private val personaMapper = PersonaMapper()


    /**
     * Lee las personas desde un archivo XML.
     * @param file El archivo XML desde el que se leerán las personas.
     * @return Lista de personas (Jugadores o Entrenadores).
     * @throws PersonasException.PersonasStorageException Si el archivo no existe, no es un archivo o no se puede leer.
     */
    override fun leerDelArchivo(file: File): Result<List<Persona>,PersonasException> {
        logger.debug { "Leyendo personas de fichero XML: $file" }

        // condicional para verificar que el archivo sea válido
        if (!file.exists() || !file.isFile || !file.canRead() || file.length() == 0L || !file.name.endsWith(".xml")) { // si no existe, no se puede leer, esta vacío o no es de la extensión xml
            logger.error { "El fichero no existe, o no es un fichero o no se puede leer: $file" }
            return Err(PersonasException.PersonasStorageException("El fichero no existe, o no es un fichero o no se puede leer: $file"))
        }

        // leemos el archivo XML
        val xmlString = file.readText()
        val xml = XML {}

        // se paresa el XML a la clase correspondiente
        val equipoDto = xml.decodeFromString<EquipoDtoXml>(xmlString)

        // se convierte cada persona en su modelo correspondiente
        return Ok(equipoDto.personal.map {
            when (it.tipo) {
                "Jugador" -> personaMapper.toModel(
                    JugadorDto(
                    id = it.id.toLong(),
                    nombre = it.nombre,
                    apellidos = it.apellidos,
                    fechaNacimiento = it.fechaNacimiento,
                    fechaIncorporacion = it.fechaIncorporacion,
                    salario = it.salario,
                    pais = it.pais,
                    posicion = it.posicion.toString(),
                    dorsal = it.dorsal?.toInt() ?: 0 ,
                    altura = it.altura?.toDouble() ?: 0.0,
                    peso = it.peso?.toDouble() ?: 0.0,
                    goles = it.goles?.toInt() ?: 0,
                    partidosJugados = it.partidosJugados?.toInt() ?: 0
                )
                )
                "Entrenador" -> personaMapper.toModel(
                    EntrenadorDto(
                    id = it.id.toLong(),
                    nombre = it.nombre,
                    apellidos = it.apellidos,
                    fechaNacimiento = it.fechaNacimiento,
                    fechaIncorporacion = it.fechaIncorporacion,
                    salario = it.salario,
                    pais = it.pais,
                    especialidad = it.especialidad.toString()
                )
                )
                else -> return Err(PersonasException.PersonasInvalidoException("Tipo de persona desconocido en XML"))
            }
        })


    }

    /**
     * Escribe las personas en un archivo XML.
     * @param personas La lista de personas (Jugadores o Entrenadores) que se escribirán.
     * @param file El archivo XML donde se guardarán las personas.
     * @throws PersonasException.PersonasStorageException Si el directorio no existe o no es válido.
     */
    override fun escribirAUnArchivo(file: File, persona: List<Persona>): Result<String,PersonasException> {
        logger.debug { "Escribiendo personas en fichero XML: $file" }

        // condicional para verificar que el archivo sea válido
        if (!file.parentFile.exists() || !file.parentFile.isDirectory || !file.name.endsWith(".xml")) { // si no existe, no está en el directorio o no es de la extensión .xml
            logger.error { "El directorio padre del fichero no existe: ${file.parentFile.absolutePath}" }
            return Err(PersonasException.PersonasStorageException("El directorio padre del fichero no existe: ${file.parentFile.absolutePath}"))
        }

        // convertimos las personas a DTOs
        val equipoDto = EquipoDtoXml(persona.map { persona ->
            when (persona) {
                is Jugadores -> PersonalDtoXml(
                    id = persona.id.toInt(),
                    tipo = "Jugador",
                    nombre = persona.nombre,
                    apellidos = persona.apellidos,
                    fechaNacimiento = persona.fechaNacimiento.toString(),
                    fechaIncorporacion = persona.fechaIncorporacion.toString(),
                    salario = persona.salario,
                    pais = persona.pais,
                    especialidad = "",
                    posicion = persona.posicion.name,
                    dorsal = persona.dorsal.toString(),
                    altura = persona.altura.toString(),
                    peso = persona.peso.toString(),
                    goles = persona.goles.toString(),
                    partidosJugados = persona.partidosJugados.toString()
                )
                is Entrenadores -> PersonalDtoXml(
                    id = persona.id.toInt(),
                    tipo = "Entrenador",
                    nombre = persona.nombre,
                    apellidos = persona.apellidos,
                    fechaNacimiento = persona.fechaNacimiento.toString(),
                    fechaIncorporacion = persona.fechaIncorporacion.toString(),
                    salario = persona.salario,
                    pais = persona.pais,
                    especialidad = persona.especialidad.name,
                    posicion = "",
                    dorsal = null,
                    altura = null,
                    peso = null,
                    goles = null,
                    partidosJugados = null
                )
                else -> return Err(PersonasException.PersonasInvalidoException("Tipo de persona desconocido"))
            }
        })

        // escritura del archivo XML
        val xml = XML { indent = 4 }
        file.writeText(xml.encodeToString(equipoDto))
        return Ok("archivo guardado correctamente")
    }
}