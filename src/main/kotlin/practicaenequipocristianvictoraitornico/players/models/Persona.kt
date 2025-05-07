package practicaenequipocristianvictoraitornico.players.models

import org.lighthousegames.logging.logging
import java.time.LocalDate

abstract class Persona(
    val id:Long,
    val nombre:String,
    val apellidos:String,
    val fechaNacimiento:LocalDate,
    val fechaIncorporacion:LocalDate,
    val salario:Double,
    val pais:String,

    ){
    internal val logger= logging()
}