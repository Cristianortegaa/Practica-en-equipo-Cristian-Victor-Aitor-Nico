package practicaenequipocristianvictoraitornico.players.dto


/**
 * Clase que representa a los entrenadores.
 *
 * @param especialidad Tipo de entrenador.
 */

class EntrenadorDto(
    id: Long,
    nombre: String,
    apellidos: String,
    fechaNacimiento: String,
    fechaIncorporacion: String,
    salario: Double,
    pais: String,
    val especialidad: String
): PersonaDto(id, nombre, apellidos, fechaNacimiento, fechaIncorporacion, salario, pais)