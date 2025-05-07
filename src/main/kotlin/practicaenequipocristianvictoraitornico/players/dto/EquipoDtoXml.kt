package practicaenequipocristianvictoraitornico.players.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * DTO que representa la estructura del XML de equipo.
 */
@Serializable
@SerialName("equipo")
data class EquipoDtoXml(val personal: List<PersonalDtoXml>)