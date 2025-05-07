package practicaenequipocristianvictoraitornico.players.dao

import java.time.LocalDate


data class PersonaEntity(
    val id: Int=0,
    val tipo: String,
    val nombre: String,
    val apellidos: String,
    val fechaNacimiento: LocalDate,
    val fechaIncorporacion: LocalDate,
    val salario: Double,
    val pais: String,
    val especialidad: String?=null,
    val posicion: String?=null,
    val dorsal: Int?=null,
    val altura: Double?=null,
    val peso: Double?=null,
    val goles: Int?=null,
    val partidosJugados: Int?=null,
)
