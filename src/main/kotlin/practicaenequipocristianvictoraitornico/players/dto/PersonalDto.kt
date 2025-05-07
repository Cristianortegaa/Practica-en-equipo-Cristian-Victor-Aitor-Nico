package practicaenequipocristianvictoraitornico.players.dto





/**
 * Clase abstracta DTO que representa a las persona.
 * Sirve como base para otras clases DTO (EntrenadorDto y JugadorDto)
 *
 * @param id Identificador personal.
 * @param nombre Nombre de la persona.
 * @param apellidos Apellidos de la persona.
 * @param fechaNacimiento Fecha de nacimiento de la persona.
 * @param fechaIncorporacion Fecha de incorporación de la persona.
 * @param salario Lo que cobra la persona.
 * @param pais Nacionalidad de la persona.
 */
abstract class PersonaDto (

    val id: Long,
    val nombre: String,
    val apellidos: String,
    val fechaNacimiento: String,
    val fechaIncorporacion: String,
    val salario: Double,
    val pais: String

)