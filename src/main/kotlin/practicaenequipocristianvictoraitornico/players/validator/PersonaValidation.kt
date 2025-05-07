package practicaenequipocristianvictoraitornico.players.validator

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.lighthousegames.logging.logging
import practicaenequipocristianvictoraitornico.players.exception.PersonasException
import practicaenequipocristianvictoraitornico.players.models.Jugadores
import practicaenequipocristianvictoraitornico.players.models.Persona

class PersonaValidation: Validate<Persona,PersonasException> {
    private val logger= logging()
    override fun validator(item: Persona): Result<Persona, PersonasException> {
        logger.debug { "validando persona" }

                if(item.nombre.isBlank()){
                    return Err(PersonasException.PersonasInvalidoException("nombre invalido, nombre en blanco"))
                }
                if (item.nombre.length <= 2){
                    return Err(PersonasException.PersonasInvalidoException("nombre invalido, demasiado corto"))
                }
                if(item.apellidos.isBlank()){
                    return Err(PersonasException.PersonasInvalidoException("los apellidos están en blanco"))
                }
                if (item.apellidos.length <= 2){
                    return Err(PersonasException.PersonasInvalidoException("apellidos inválidos, demasiado cortos"))
                }

                if (item.salario<=0){
                    return Err(PersonasException.PersonasInvalidoException("salario invalido, salario negativo o igual a 0"))
                }
                if(item.pais.isBlank()){
                    return Err(PersonasException.PersonasInvalidoException("el pais esta en blanco"))
                }
                if (item.pais.length <= 2){
                    return Err(PersonasException.PersonasInvalidoException("pais invalido, demasiado corto"))
                }

                //para comprobar si están correctos el resto de datos
                return if(item is Jugadores) validarJugador(item) else Ok(item)


    }    /**
     * comprueba el resto de datos de jugadores
     * @param jugadores jugador a comprobar
     * @throws PersonasException.PersonaInvalidoException
     */
    private fun validarJugador(jugadores: Jugadores): Result<Persona,PersonasException> {
        val logger=logging()
        logger.debug { "validando jugador" }
        if (jugadores.dorsal<=0){
            return Err(PersonasException.PersonasInvalidoException("dorsal invalido, dorsal negativo"))
        }
        if (jugadores.dorsal>99){
            return Err(PersonasException.PersonasInvalidoException("dorsal invalido, dorsal mayor a 99"))
        }
        if (jugadores.altura<=0.5){
            return Err(PersonasException.PersonasInvalidoException("altura invalida, demasiado corto"))
        }
        if(jugadores.altura>3.5){
            return Err(PersonasException.PersonasInvalidoException("altura invalido, demasiado alto"))
        }
        if (jugadores.peso<=45){
            return Err(PersonasException.PersonasInvalidoException("peso invalido, desnutrido"))
        }
        if (jugadores.peso>150){
            return Err(PersonasException.PersonasInvalidoException("peso invalido, tanque ruso"))
        }
        if (jugadores.goles<0){
            return Err(PersonasException.PersonasInvalidoException("goles invalido, goles negativo"))
        }
        if (jugadores.partidosJugados<0){
            return Err(PersonasException.PersonasInvalidoException("partidos jugados inválidos, partidos negativo"))
        }
        return Ok(jugadores)
    }


}