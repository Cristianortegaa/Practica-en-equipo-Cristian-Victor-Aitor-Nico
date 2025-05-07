package practicaenequipocristianvictoraitornico.players.mappers



import practicaenequipocristianvictoraitornico.players.dao.PersonaEntity
import practicaenequipocristianvictoraitornico.players.dto.EntrenadorDto
import practicaenequipocristianvictoraitornico.players.dto.JugadorDto
import practicaenequipocristianvictoraitornico.players.dto.PersonaDto
import practicaenequipocristianvictoraitornico.players.models.Entrenadores
import practicaenequipocristianvictoraitornico.players.models.Jugadores
import practicaenequipocristianvictoraitornico.players.models.Persona
import practicaenequipocristianvictoraitornico.players.utils.toEspecialidad
import practicaenequipocristianvictoraitornico.players.utils.toPosicion

import java.time.LocalDate

/**
 * Clase Mapper que se encarga de convertir entre el modelo Jugador y el DTO JugadorDto.
 */
class PersonaMapper {

    // Convierte una Persona genérica a su correspondiente DTO (PersonaDto)
    fun toDto(persona: Persona): PersonaDto {
        return when (persona) {
            is Jugadores -> toJugadorDto(persona)
            is Entrenadores -> toEntrenadorDto(persona)
            else -> throw IllegalArgumentException("Tipo de persona no soportado")
        }
    }

    // Convierte un Jugador a JugadorDto
    private fun toJugadorDto(jugador: Jugadores): JugadorDto {
        return JugadorDto(
            id = jugador.id,
            nombre = jugador.nombre,
            apellidos = jugador.apellidos,
            fechaNacimiento = jugador.fechaNacimiento.toString(),
            fechaIncorporacion = jugador.fechaIncorporacion.toString(),
            salario = jugador.salario,
            pais = jugador.pais,
            posicion = jugador.posicion.name, // Usamos el valor del enum como String
            dorsal = jugador.dorsal,
            altura = jugador.altura,
            peso = jugador.peso,
            goles = jugador.goles,
            partidosJugados = jugador.partidosJugados
        )
    }

    // Convierte un Entrenador a EntrenadorDto
    private fun toEntrenadorDto(entrenador: Entrenadores): EntrenadorDto {
        return EntrenadorDto(
            id = entrenador.id,
            nombre = entrenador.nombre,
            apellidos = entrenador.apellidos,
            fechaNacimiento = entrenador.fechaNacimiento.toString(),
            fechaIncorporacion = entrenador.fechaIncorporacion.toString(),
            salario = entrenador.salario,
            pais = entrenador.pais,
            especialidad = entrenador.especialidad.name // Usamos el valor del enum como String
        )
    }

    // Convierte un JugadorDto a Jugador
    fun toModel(jugadorDto: JugadorDto): Jugadores {
        return Jugadores(
            id = jugadorDto.id,
            nombre = jugadorDto.nombre,
            apellidos = jugadorDto.apellidos,
            fechaNacimiento = jugadorDto.fechaNacimiento.toLocalDate() !!,
            fechaIncorporacion = jugadorDto.fechaIncorporacion.toLocalDate() !!,
            salario = jugadorDto.salario,
            pais = jugadorDto.pais,
            posicion = jugadorDto.posicion.toPosicion()!!, // Convertimos el String a enum
            dorsal = jugadorDto.dorsal,
            altura = jugadorDto.altura,
            peso = jugadorDto.peso,
            goles = jugadorDto.goles,
            partidosJugados = jugadorDto.partidosJugados
        )
    }

    // Convierte un EntrenadorDto a Entrenador
    fun toModel(entrenadorDto: EntrenadorDto): Entrenadores {
        return Entrenadores(
            especialidad = entrenadorDto.especialidad.toEspecialidad()!!, // Convertimos el String a enum
            id = entrenadorDto.id,
            nombre = entrenadorDto.nombre,
            apellidos = entrenadorDto.apellidos,
            fechaNacimiento = entrenadorDto.fechaNacimiento.toLocalDate() !!,
            fechaIncorporacion = entrenadorDto.fechaIncorporacion.toLocalDate()!!,
            salario = entrenadorDto.salario,
            pais = entrenadorDto.pais
        )
    }

    private fun jugadorToModel(persona: PersonaEntity): Jugadores {
        return Jugadores(
            id = persona.id.toLong(),
            nombre = persona.nombre,
            apellidos = persona.apellidos,
            fechaNacimiento = persona.fechaNacimiento,
            fechaIncorporacion = persona.fechaIncorporacion,
            salario = persona.salario,
            pais = persona.pais,
            posicion = persona.posicion!!.toPosicion()!!,
            dorsal = persona.dorsal!!,
            altura = persona.altura!!,
            peso = persona.peso!!,
            goles = persona.goles!!,
            partidosJugados = persona.partidosJugados!!
        )
    }

    fun toDatabaseModel(persona:PersonaEntity):Persona{
        return when(persona.tipo){
            "Jugador"-> jugadorToModel(persona)
            "Entrenador"-> entrenadorToModel(persona)
            else -> throw IllegalArgumentException("Tipo ${persona.tipo}")
        }
    }

    private fun entrenadorToModel(persona: PersonaEntity): Entrenadores {
        return Entrenadores(
            id = persona.id.toLong(),
            nombre = persona.nombre,
            apellidos = persona.apellidos,
            fechaNacimiento = persona.fechaNacimiento,
            fechaIncorporacion = persona.fechaNacimiento,
            salario = persona.salario,
            pais = persona.pais,
            especialidad = persona.especialidad!!.toEspecialidad()!!,
        )
    }

    private fun jugadorToEntity(persona: Jugadores): PersonaEntity {
        return PersonaEntity(
            id=persona.id.toInt(),
            tipo = "Jugador",
            nombre = persona.nombre,
            apellidos = persona.apellidos,
            fechaNacimiento = persona.fechaNacimiento,
            fechaIncorporacion = persona.fechaIncorporacion,
            salario = persona.salario,
            pais = persona.pais,
            posicion = persona.posicion.toString(),
            dorsal = persona.dorsal,
            altura = persona.altura,
            peso = persona.peso,
            goles = persona.goles,
            partidosJugados = persona.partidosJugados
        )
    }

    fun toEntity(persona:Persona): PersonaEntity{
        return when(persona){
            is Jugadores-> jugadorToEntity(persona)
            is Entrenadores-> entrenadorToEntity(persona)
            else -> {throw IllegalArgumentException("tipo de persona no soportado")}
        }

    }

    private fun entrenadorToEntity(persona: Entrenadores): PersonaEntity {
        return PersonaEntity(
            especialidad = persona.especialidad.toString(), // Convertimos el String a enum
            id = persona.id.toInt(),
            nombre = persona.nombre,
            apellidos = persona.apellidos,
            fechaNacimiento = persona.fechaNacimiento,
            fechaIncorporacion = persona.fechaIncorporacion,
            salario = persona.salario,
            pais = persona.pais,
            tipo = "Entrenador",
        )
    }
}

internal fun String.toLocalDate(): LocalDate? {
    return LocalDate.parse(this)
}