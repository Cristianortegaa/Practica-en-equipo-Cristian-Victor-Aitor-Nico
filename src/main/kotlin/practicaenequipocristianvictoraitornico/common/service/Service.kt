package practicaenequipocristianvictoraitornico.common.service

import com.github.michaelbull.result.Result
import practicaenequipocristianvictoraitornico.personal.models.Persona

interface Service<T,E,ID> {
    fun getAll():List<Persona>
    fun getByID(id:ID): Result<T, E>
    fun save(item: T): Result<T,E>
    fun delete(id: ID): Result<T,E>
    fun update(id: ID, item: T): Result<T,E>
}