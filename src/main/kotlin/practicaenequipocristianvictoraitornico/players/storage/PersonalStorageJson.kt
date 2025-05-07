package practicaenequipocristianvictoraitornico.players.storage

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import kotlinx.serialization.json.Json
import org.lighthousegames.logging.logging
import practicaenequipocristianvictoraitornico.players.dto.EntrenadorDto
import practicaenequipocristianvictoraitornico.players.dto.JugadorDto
import practicaenequipocristianvictoraitornico.players.dto.PersonaDto
import practicaenequipocristianvictoraitornico.players.dto.PersonalDtoXml
import practicaenequipocristianvictoraitornico.players.exception.PersonasException
import practicaenequipocristianvictoraitornico.players.mappers.PersonaMapper
import practicaenequipocristianvictoraitornico.players.models.Entrenadores
import practicaenequipocristianvictoraitornico.players.models.Jugadores
import practicaenequipocristianvictoraitornico.players.models.Persona
import practicaenequipocristianvictoraitornico.players.models.Posicion
import java.io.File

/**
 *
 * Esta clase se encarga de leer y escribir personas (Jugadores y Entrenadores)
 * en formato JSON.
 */
class PersonalStorageJson : PersonalStorage {

    // Logger para registrar los eventos que suceden en la clase
    private val logger = logging()

    // Instancia del Mapper que se encarga de la conversión entre modelos y DTOs
    private val personaMapper = PersonaMapper()

    /**
     * Esta función lee las personas de un archivo JSON y las devuelve como una lista de objetos Persona.
     *
     * @param file Este es el archivo JSON desde el cual se leen los datos.
     * @return Devuelve la lista de personas leídas del archivo.
     * @throws PersonasException.PersonasStorageException Si el archivo no existe, no se puede leer, o tiene un formato incorrecto.
     */
    override fun leerDelArchivo (file:File): Result<List<Persona>,PersonasException> {
        // Mensaje de debug para la lectura del archivo
        logger.debug { "Leyendo personas de fichero JSON: $file" }

        // verificación de que el archivo sea válido antes de intentar leerlo
        if (!file.exists() || !file.isFile || !file.canRead() || file.length() == 0L || !file.name.endsWith(".json")) {
            logger.error { "El fichero no existe, o no es un fichero o no se puede leer: $file" }
            return Err(PersonasException.PersonasStorageException("El fichero no existe, o no es un fichero o no se puede leer: $file"))
        }
        val json= Json { ignoreUnknownKeys = true
        }
        val list= json.decodeFromString<List<PersonalDtoXml>>(file.readText()).map { it->
            when(it.tipo){
                "Jugador"->{
                    personaMapper.toModel(
                        JugadorDto(
                            id = it.id.toLong(),
                            nombre = it.nombre,
                            apellidos = it.apellidos,
                            fechaNacimiento = it.fechaNacimiento,
                            fechaIncorporacion = it.fechaNacimiento,
                            salario = it.salario,
                            pais = it.pais,
                            posicion = it.posicion.toString(),
                            dorsal = it.dorsal?.toInt() ?: 0 ,
                            altura = it.altura?.toDouble() ?: 0.0,
                            peso = it.peso?.toDouble() ?: 0.0,
                            goles = it.goles?.toInt() ?: 0,
                            partidosJugados = it.partidosJugados?.toInt() ?: 0,
                        )
                    )
                }
                "Entrenador"->{
                    personaMapper.toModel(
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
                }
                else->{
                    return Err(PersonasException.PersonasInvalidoException("debe ser un entrenador o un jugador"))
                }
            }
        }
        return Ok(list)

        // lectura el contenido del archivo JSON
       /* val jsonContent = file.readText()

        val personas = mutableListOf<Persona>()

        val personasJson = jsonContent.trim().removeSurrounding("[", "]").split("}, {")
    */
        // se recorre cada objeto JSON para convertirlo en el tipo correspondiente
        /*for (personaJson in personasJson) {
            val json = personaJson
                .removeSurrounding("{", "}")
                .split(",")
                .map { it.split(":") }
                .associate { it[0].trim().removeSurrounding("\"") to it[1].trim().removeSurrounding("\"") }

            // dependiendo del rol de cada persona, se crea el DTO correspondiente
            when (json["rol"]) {
                "Jugador" -> {
                    // creación el DTO de Jugador
                    val jugadorDto = JugadorDto(
                        id = json["id"]?.toLong() ?: 0,
                        nombre = json["nombre"] ?: "",
                        apellidos = json["apellidos"] ?: "",
                        fechaNacimiento = json["fecha_nacimiento"] ?: "",
                        fechaIncorporacion = json["fecha_incorporacion"] ?: "",
                        salario = json["salario"]?.toDouble() ?: 0.0,
                        pais = json["pais"] ?: "",
                        posicion = json["posicion"] ?: "",
                        dorsal = json["dorsal"]?.toInt() ?: 0,
                        altura = json["altura"]?.toDouble() ?: 0.0,
                        peso = json["peso"]?.toDouble() ?: 0.0,
                        goles = json["goles"]?.toInt() ?: 0,
                        partidosJugados = json["partidosJugados"]?.toInt() ?: 0
                    )
                    // conversión del DTO a modelo y se agrega a la lista
                    personas.add(personaMapper.toModel(jugadorDto))
                }
                "Entrenador" -> {
                    // creación del DTO de entrenador
                    val entrenadorDto = EntrenadorDto(
                        id = json["id"]?.toLong() ?: 0,
                        nombre = json["nombre"] ?: "",
                        apellidos = json["apellidos"] ?: "",
                        fechaNacimiento = json["fecha_nacimiento"] ?: "",
                        fechaIncorporacion = json["fecha_incorporacion"] ?: "",
                        salario = json["salario"]?.toDouble() ?: 0.0,
                        pais = json["pais"] ?: "",
                        especialidad = json["especialidad"] ?: ""
                    )
                    // conversión del DTO a modelo y se agrega a la lista
                    personas.add(personaMapper.toModel(entrenadorDto))
                }

            }
        }*/

        // devolución de la lista de personas convertidas

    }

    /**
     * Se encarga de escribir una lista de personas en un archivo JSON.
     *
     * @param file El archivo JSON donde escribir los datos.
     * @param persona La lista de personas a escribir en el archivo.
     * @throws PersonasException.PersonasStorageException Si el archivo no es válido o no se puede escribir.
     */
    override fun escribirAUnArchivo (file: File, persona: List<Persona>): Result<String,PersonasException> {
        // mensaje de debug para la escritura del archivo
        logger.debug { "Escribiendo personas en fichero JSON: $file" }

        // verificación de que el directorio del archivo existe y es válido
        if (!file.parentFile.exists() || !file.parentFile.isDirectory || !file.name.endsWith(".json")) {
            logger.error { "El directorio padre del fichero no existe: ${file.parentFile.absolutePath}" }
            return Err(PersonasException.PersonasStorageException("El directorio padre del fichero no existe: ${file.parentFile.absolutePath}"))
        }

        // creación del JSON manualmente como String
        val json = persona.joinToString(prefix = "[", postfix = "]") { persona ->
            when (persona) {
                is Jugadores -> """
                    {
                        "tipo": "Jugador",
                        "id": "${persona.id}",
                        "nombre": "${persona.nombre}",
                        "apellidos": "${persona.apellidos}",
                        "fechaNacimiento": "${persona.fechaNacimiento}",
                        "fechaIncorporacion": "${persona.fechaIncorporacion}",
                        "salario": "${persona.salario}",
                        "pais": "${persona.pais}",
                        "posicion": "${persona.posicion.name}",
                        "dorsal": "${persona.dorsal}",
                        "altura": "${persona.altura}",
                        "peso": "${persona.peso}",
                        "goles": "${persona.goles}",
                        "partidosJugados": "${persona.partidosJugados}"
                    }
                """.trimIndent()
                is Entrenadores -> """
                    {
                        "tipo": "Entrenador",
                        "id": "${persona.id}",
                        "nombre": "${persona.nombre}",
                        "apellidos": "${persona.apellidos}",
                        "fechaNacimiento": "${persona.fechaNacimiento}",
                        "fechaIncorporacion": "${persona.fechaIncorporacion}",
                        "salario": "${persona.salario}",
                        "pais": "${persona.pais}",
                        "especialidad": "${persona.especialidad.name}"
                    }
                """.trimIndent()
                else -> ""
            }
        }

        // escritura del JSON en el archivo

        file.writeText(json)
        return Ok("archivo guardado correctamente")
    }


}