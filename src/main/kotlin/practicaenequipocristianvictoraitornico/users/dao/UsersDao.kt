package practicaenequipocristianvictoraitornico.users.dao


import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.customizer.BindBean
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate
import org.lighthousegames.logging.logging
import practicaenequipocristianvictoraitornico.users.models.User

interface UsersDao {
    /*nombre_usuario VARCHAR PRIMARY KEY NOT NULL,
    contraseña VARCHAR NOT NULL
);*/
    @SqlQuery("SELECT * FROM users")
    fun getAll(): List<UsersEntity>
    @SqlQuery("select * from users where nombre_usuario = :name")
    fun getByName(@Bind("name") name: String): UsersEntity?
    @SqlUpdate("INSERT INTO users (nombre_usuario,contraseña,admin) VALUES (:name, :password,:admin))")
    fun save(@BindBean user: UsersEntity): Int
    @SqlUpdate("UPDATE users SET contraseña = :password, admin= :admin WHERE nombre_usuario = :name,")
    fun update(@BindBean user: UsersEntity,@Bind("name")name: String): Int
    @SqlUpdate("DELETE FROM users WHERE nombre_usuario = :name")
    fun delete(@Bind("name") name: String): Int
}
fun provideUsersDao(jdbi: Jdbi): UsersDao {
    val logger= logging()
    logger.info { "obteniendo dao de usuarios" }
    return jdbi.onDemand(UsersDao::class.java)
}