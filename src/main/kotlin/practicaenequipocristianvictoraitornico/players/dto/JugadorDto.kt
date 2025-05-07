package practicaenequipocristianvictoraitornico.players.dto

import kotlinx.serialization.Serializable


class JugadorDto(
    id: Long,
    nombre: String,
    apellidos: String,
    fechaNacimiento: String,
    fechaIncorporacion: String,
    salario: Double,
    pais: String,
    val posicion: String,
    val dorsal: Int,
    val altura: Double,
    val peso: Double,
    val goles: Int,
    val partidosJugados: Int
): PersonaDto(id, nombre, apellidos, fechaNacimiento, fechaIncorporacion, salario, pais)