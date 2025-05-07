package practicaenequipocristianvictoraitornico.players.storage

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.lighthousegames.logging.logging
import practicaenequipocristianvictoraitornico.players.exception.PersonasException
import practicaenequipocristianvictoraitornico.players.models.*
import java.io.File
import java.time.LocalDate

class PersonalStorageCsv: PersonalStorage {

    private val logger = logging() // creación del logger



    /**
     * Se encarga de leer un listado de personas facilitadas por un archivo de extensión .csv.
     *
     * @param file Este es el archivo CSV desde el cual se leen los datos.
     * @return Devuelve la lista de personas leídas del archivo.
     * @throws PersonasException.PersonasStorageException Si el archivo no existe, no se puede leer, o tiene un formato incorrecto.
     */
    override fun leerDelArchivo(file: File): Result<List<Persona>,PersonasException> {
        if (!file.isFile || !file.exists() || !file.canRead()) { // si no tiene un buen formato, no existe o no es puede leer
            logger.error { "El fichero no existe o no se puede leer: $file" }
            return Err(PersonasException.PersonasStorageException("El fichero no existe o no se puede leer: $file")) // excepción creada en exception/PersonaExcepcion.kt
        }

        return Ok(file.readLines() // devuelve la lista de objetos leídos
            .drop(1) // omisión de cabecera
            .map { it.split(",") } // separación con coma
            .map { it.map { it.trim() } } // eliminación de espacios en blanco
            .map { // mapeando el archivo

                val id = it[0].toLong()
                val nombre = it[1]
                val apellidos = it[2]
                val fechaNacimiento = LocalDate.parse(it[3])
                val fechaIncorporacion = LocalDate.parse(it[4])
                val salario = it[5].toDouble()
                val pais = it[6]
                val rol = it[7]

                // se crean las instacias Entrenadores y Jugadores dependiendo de su valor en la columna Rol
                when (rol) {

                    "Entrenador" -> Entrenadores(
                        especialidad = Especialidad.valueOf(it[8]),
                        id = id,
                        nombre = nombre,
                        apellidos = apellidos,
                        fechaNacimiento = fechaNacimiento,
                        fechaIncorporacion = fechaIncorporacion,
                        salario = salario,
                        pais = pais
                    )

                    "Jugador" -> Jugadores(
                        id = id,
                        nombre = nombre,
                        apellidos = apellidos,
                        fechaNacimiento = fechaNacimiento,
                        fechaIncorporacion = fechaIncorporacion,
                        salario = salario,
                        pais = pais,
                        posicion = Posicion.valueOf(it[9]),
                        dorsal = it[10].toInt(),
                        altura = it[11].toDouble(),
                        peso = it[12].toDouble(),
                        goles = it[13].toInt(),
                        partidosJugados = it[14].toInt()
                    )
                    else -> return Err(PersonasException.PersonasStorageException("Rol desconocido en el CSV: $rol"))
                }
            })
    }

    /**
     * Se encarga de escribir una lista de personas en un archivo CSV.
     *
     * @param file El archivo CSV donde escribir los datos.
     * @param persona La lista de personas a escribir en el archivo.
     * @throws PersonasException.PersonasStorageException Si el archivo no es válido o no se puede escribir.
     */
    override fun escribirAUnArchivo(file: File, persona: List<Persona>): Result<String,PersonasException> {
        logger.debug { "Escribiendo personas en el fichero CSV: $file" }

        // comprobación de si el archivo es válido
        if (!file.parentFile.exists() || !file.parentFile.isDirectory || !file.name.endsWith(".csv", true)) { // si no existe, no está en el directorio o no es de la extensión .csv
            logger.error { "El directorio padre del fichero no existe o el archivo no es un CSV: ${file.parentFile.absolutePath}" }
            return Err(PersonasException.PersonasStorageException("No se puede escribir en el archivo debido a que no existe o no es de la extensión adecuada 😔"))
        }

        // escritura de la cabecera del CSV
        file.writeText("id,nombre,apellidos,fechaNacimiento,fechaIncorporacion,salario,pais,rol,extra1,extra2,extra3,extra4,extra5,extra6,extra7\n")

        // escritura de cada una de las personas en el archivo
        persona.forEach { persona ->
            val csvRow = when (persona) {
                is Entrenadores -> {
                    // extracción de los valores de la clase Entrenadores
                    "${persona.id},${persona.nombre},${persona.apellidos},${persona.fechaNacimiento},${persona.fechaIncorporacion},${persona.salario},${persona.pais},Entrenador,${persona.especialidad},,,,,,,"
                }
                is Jugadores -> {
                    // extracción de los valores de la clase Jugadores
                    "${persona.id},${persona.nombre},${persona.apellidos},${persona.fechaNacimiento},${persona.fechaIncorporacion},${persona.salario},${persona.pais},Jugador,${persona.posicion},${persona.dorsal},${persona.altura},${persona.peso},${persona.goles},${persona.partidosJugados},,,"
                }
                else -> return Err(PersonasException.PersonasStorageException("Tipo de persona desconocido: ${persona::class.simpleName}"))
            }
            file.appendText("$csvRow\n")
        }

        logger.info { "Datos guardados correctamente 😎" }
        return Ok("guardado con exito")
    }

}