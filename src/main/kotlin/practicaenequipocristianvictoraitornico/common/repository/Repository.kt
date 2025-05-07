package practicaenequipocristianvictoraitornico.common.repository

interface Repository<T, ID> {
    fun getAll(): List<T>
    fun getById(id: ID): T?
    fun update(objeto:T,id: ID):T?
    fun delete(id: ID):T?
    fun save(objeto: T):T
}