package practicaenequipocristianvictoraitornico.players.repository

import org.lighthousegames.logging.logging
import practicaenequipocristianvictoraitornico.players.dao.PersonaDao
import practicaenequipocristianvictoraitornico.players.mappers.PersonaMapper
import practicaenequipocristianvictoraitornico.players.models.Entrenadores
import practicaenequipocristianvictoraitornico.players.models.Jugadores
import practicaenequipocristianvictoraitornico.players.models.Persona

class PersonasRepositoryImplementation(
    private val dao: PersonaDao,
    private val mapper: PersonaMapper,
):PersonalRepository {
    private val logger = logging()
    override fun getAll(): List<Persona> {
        logger.debug { "Getting all personas" }
        return dao.getAll().map { mapper.toDatabaseModel(it) }
    }

    override fun getById(id: Long): Persona? {
        logger.debug { "Getting persona by id $id" }
        return dao.getById(id.toInt())?.let { mapper.toDatabaseModel(it) }
    }

    override fun update(objeto: Persona, id: Long): Persona? {
        logger.debug { "Updating persona by id $id" }
    val updated = dao.update(mapper.toEntity(objeto),id.toInt())
        return if (updated==1) personaCopy(objeto,id) else null
    }

    override fun delete(id: Long): Persona? {
        logger.debug { "Deleting persona by id $id" }
        dao.getById(id.toInt())?.let {
            if (dao.deleteById(id.toInt())==1) return mapper.toDatabaseModel(it)
            else null
        }
        return null
    }

    override fun save(objeto: Persona): Persona {
        val id= dao.save(mapper.toEntity(objeto))
        return personaCopy(objeto,id.toLong())
    }
    private fun personaCopy(persona: Persona,id: Long): Persona {
        return if(persona is Jugadores){
            jugadorCopy(persona, id)
        } else {
            entrenadorCopy(persona as Entrenadores,id)
        }
    }
    private fun entrenadorCopy(persona: Entrenadores, id: Long): Entrenadores {
        logger.debug { "copiando entrenador" }
        val entrenador = Entrenadores(
            id,
            persona.nombre,
            persona.apellidos,
            persona.fechaNacimiento,
            persona.fechaIncorporacion,
            persona.salario,
            persona.pais,
            persona.especialidad
        )
        return entrenador
    }
    private fun jugadorCopy(persona: Jugadores, id: Long): Jugadores {
        logger.debug { "copiando jugador" }
        val jugador = Jugadores(
            id,
            persona.nombre,
            persona.apellidos,
            persona.fechaNacimiento,
            persona.fechaIncorporacion,
            persona.salario,
            persona.pais,
            persona.posicion,
            persona.dorsal,
            persona.altura,
            persona.peso,
            persona.goles,
            persona.partidosJugados
        )
        return jugador
    }
}