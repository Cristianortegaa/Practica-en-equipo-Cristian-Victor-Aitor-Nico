package practicaenequipocristianvictoraitornico.players.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement

/**
 * DTO que representa cada persona (jugador o entrenador) dentro del XML.
 */
@Serializable
@SerialName("personal")
data class PersonalDtoXml(
    @SerialName("id")
    val id: Int=0,
    @XmlElement
    @SerialName("tipo")
    val tipo: String,
    @XmlElement
    @SerialName("nombre")
    val nombre: String,
    @XmlElement
    @SerialName("apellidos")
    val apellidos: String,
    @XmlElement
    @SerialName("fechaNacimiento")
    val fechaNacimiento: String,
    @XmlElement
    @SerialName("fechaIncorporacion")
    val fechaIncorporacion: String,
    @XmlElement
    @SerialName("salario")
    val salario: Double,
    @XmlElement
    @SerialName("pais")
    val pais: String,
    @XmlElement
    @SerialName("especialidad")
    val especialidad: String?,
    @XmlElement
    @SerialName("posicion")
    val posicion: String?,
    @XmlElement
    @SerialName("dorsal")
    val dorsal: String?,
    @XmlElement
    @SerialName("altura")
    val altura: String?,
    @XmlElement
    @SerialName("peso")
    val peso: String?,
    @XmlElement
    @SerialName("goles")
    val goles: String?,
    @XmlElement
    @SerialName("partidosJugados")
    val partidosJugados: String?
)
