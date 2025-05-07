package practicaenequipocristianvictoraitornico.players.utils

import practicaenequipocristianvictoraitornico.players.models.Especialidad
import practicaenequipocristianvictoraitornico.players.models.Posicion
import java.util.*

fun String.toEspecialidad(): Especialidad? {
    return if (this.uppercase(Locale.getDefault()) != "ENTRENADOR_PORTEROS" &&
        this.uppercase(Locale.getDefault()) != "ENTRENADOR_ASISTENTE" &&
        this.uppercase(Locale.getDefault()) != "ENTRENADOR_PRINCIPAL")
        null else Especialidad.valueOf(this.uppercase(Locale.getDefault()))
}
fun String.toPosicion(): Posicion? {
    return if (this.uppercase(Locale.getDefault()) != "DELANTERO" &&
        this.uppercase(Locale.getDefault()) != "CENTROCAMPISTA" && this.uppercase(Locale.getDefault()) != "PORTERO" &&
        this.uppercase(Locale.getDefault()) != "DEFENSA") null else Posicion.valueOf(this.uppercase(Locale.getDefault()))

}