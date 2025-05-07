package practicaenequipocristianvictoraitornico.personal.services

import com.github.michaelbull.result.Result
import practicaenequipocristianvictoraitornico.personal.exception.PersonasException
import practicaenequipocristianvictoraitornico.personal.models.Persona
import java.nio.file.Path

class PersonaServiceImpl:PersonaService {
    override fun importarDatosDesdeFichero(fichero: Path) {
        TODO("Not yet implemented")
    }

    override fun exportarDatosDesdeFichero(fichero: Path) {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<Persona> {
        TODO("Not yet implemented")
    }

    override fun getByID(id: Long): Result<Persona, PersonasException> {
        TODO("Not yet implemented")
    }

    override fun save(item: Persona): Result<Persona, PersonasException> {
        TODO("Not yet implemented")
    }

    override fun delete(id: Long): Result<Persona, PersonasException> {
        TODO("Not yet implemented")
    }

    override fun update(id: Long, item: Persona): Result<Persona, PersonasException> {
        TODO("Not yet implemented")
    }

}