package practicaenequipocristianvictoraitornico.players.validator

import com.github.michaelbull.result.Result

interface Validate<T,E> {
    fun validator(item: T): Result<T,E>
}