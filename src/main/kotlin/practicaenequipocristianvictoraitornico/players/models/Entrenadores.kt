package practicaenequipocristianvictoraitornico.players.models

import java.time.LocalDate

class Entrenadores(
    id:Long,
    nombre:String,
    apellidos:String,
    fechaNacimiento: LocalDate,
    fechaIncorporacion: LocalDate,
    salario:Double,
    pais:String,
    val especialidad:Especialidad
):Persona(id,nombre,apellidos,fechaNacimiento,fechaIncorporacion,salario,pais){
    override fun toString(): String {
        return "id: $id,nombre: $nombre,apellidos: $apellidos,fecha_nacimiento: $fechaNacimiento," +
                "fecha_incorporacion: $fechaIncorporacion,salario: $salario,pais: $pais," +
                "especialidad: $especialidad}"
    }

}
enum class Especialidad {
    ENTRENADOR_PORTEROS,ENTRENADOR_ASISTENTE,ENTRENADOR_PRINCIPAL
}