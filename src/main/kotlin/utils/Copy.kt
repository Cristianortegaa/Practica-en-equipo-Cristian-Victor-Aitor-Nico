package utils
import mappers.toLocalDate
import models.Entrenadores
import models.Especialidad
import models.Jugadores
import models.Posicion

fun Jugadores.copy(list: Map<Int,String?>):Jugadores{
    return Jugadores(
        id=this.id,
        nombre=list[1]?:  run { this.nombre  },
        apellidos = list[2]?: run { this.apellidos },
        fechaNacimiento= this.fechaNacimiento ,
        fechaIncorporacion= this.fechaIncorporacion ,
        salario=list[3]?.toDoubleOrNull()?:run { this.salario },
        pais=this.pais ,
        posicion= (list[4]?.toPosicion() ?: run { this.posicion }) as Posicion,
        dorsal=list[5]?.toIntOrNull()?:run { this.dorsal },
        altura= this.altura,
        peso=list[6]?.toDoubleOrNull()?: run { this.peso },
        goles=list[7]?.toIntOrNull()?: run { this.goles },
        partidosJugados=list[8]?.toIntOrNull()?:run { this.partidosJugados },
    )
}



fun Entrenadores.copy(list: Map<Int,String?>):Entrenadores{
    return Entrenadores(
        id=this.id,
        nombre=list[1]?:  run { this.nombre },
        apellidos = list[2]?: run { this.apellidos },
        fechaNacimiento= this.fechaNacimiento ,
        fechaIncorporacion=  this.fechaIncorporacion ,
        salario=list[3]?.toDoubleOrNull()?:run { this.salario },
        pais=list[4]?:run { this.pais },
        especialidad= (list[5]?.toEspecialidad() ?: run { this.especialidad }) as Especialidad,

    )
}


