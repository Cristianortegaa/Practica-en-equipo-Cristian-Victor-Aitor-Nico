package practicaenequipocristianvictoraitornico.users.mapper

import practicaenequipocristianvictoraitornico.users.dao.UsersEntity
import practicaenequipocristianvictoraitornico.users.models.User

class UsersMappper {
    fun toEntity(user:User): UsersEntity {
        return UsersEntity(
            name = user.name,
            password = user.password,
            admin = user.admin,
        )
    }
    fun toModel(user:UsersEntity): User {
        return User(
            name = user.name,
            password = user.password,
            admin = user.admin,
        )
    }
}