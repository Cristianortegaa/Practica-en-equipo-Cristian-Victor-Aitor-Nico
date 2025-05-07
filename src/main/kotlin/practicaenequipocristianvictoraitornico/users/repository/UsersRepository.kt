package practicaenequipocristianvictoraitornico.users.repository

import practicaenequipocristianvictoraitornico.common.repository.Repository
import practicaenequipocristianvictoraitornico.users.models.User


interface UsersRepository: Repository<User, String> {
}