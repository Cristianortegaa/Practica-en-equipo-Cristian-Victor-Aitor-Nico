package practicaenequipocristianvictoraitornico.users.models

data class User(
    val name: String,
    val password: String,
    val admin: Boolean,
)
