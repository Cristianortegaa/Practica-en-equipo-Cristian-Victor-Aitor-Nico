package practicaenequipocristianvictoraitornico.players.services

import practicaenequipocristianvictoraitornico.common.service.Service
import practicaenequipocristianvictoraitornico.players.exception.PersonasException
import practicaenequipocristianvictoraitornico.players.models.Persona
import java.nio.file.Path

interface PersonaService: Service<Persona, PersonasException, Long> {
    fun importarDatosDesdeFichero(fichero: Path)
    fun exportarDatosDesdeFichero(fichero: Path)
}