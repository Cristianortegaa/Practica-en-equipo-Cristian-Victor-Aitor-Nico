package practicaenequipocristianvictoraitornico.users.repository

import practicaenequipocristianvictoraitornico.users.mapper.UsersMappper
import practicaenequipocristianvictoraitornico.users.dao.UsersDao
import practicaenequipocristianvictoraitornico.users.models.User

class UsersRepositoryImpl(
    private val usersDao: UsersDao,
    private val mapper: UsersMappper
): UsersRepository {

    override fun getAll():List<User> {
        return usersDao.getAll().map { mapper.toModel(it) }
    }

    override fun getById(id: String): User? {
        return usersDao.getByName(id)?.let { mapper.toModel(it) }
    }

    override fun update(objeto: User, id: String): User? {
        val updated= usersDao.update(mapper.toEntity(objeto),id)
        return if(updated == 1){
            objeto.copy(name=id)
        }else{
            null
        }
    }

    override fun delete(id: String): User? {
        val user = usersDao.getByName(id)?.let {
            usersDao.delete(id)
            return mapper.toModel(it)
        }
        return user
    }

    override fun save(objeto: User): User {
        usersDao.save(mapper.toEntity(objeto))
        return objeto
    }
}