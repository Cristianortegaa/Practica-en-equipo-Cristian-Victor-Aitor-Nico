package practicaenequipocristianvictoraitornico.personal.services

import practicaenequipocristianvictoraitornico.common.service.Service
import practicaenequipocristianvictoraitornico.personal.exception.PersonasException
import practicaenequipocristianvictoraitornico.personal.models.Persona
import java.nio.file.Path

interface PersonaService: Service<Persona, PersonasException, Long> {
    fun importarDatosDesdeFichero(fichero: Path)
    fun exportarDatosDesdeFichero(fichero: Path)
}