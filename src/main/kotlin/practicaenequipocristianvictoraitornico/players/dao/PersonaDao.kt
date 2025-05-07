package practicaenequipocristianvictoraitornico.players.dao

import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.customizer.BindBean
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate
import org.lighthousegames.logging.logging

interface PersonaDao {
    @SqlQuery("SELECT * FROM persona")
    fun getAll(): List<PersonaEntity>

    @SqlQuery("SELECT * FROM persona WHERE id = :id")
    fun getById(@Bind("id") id: Int): PersonaEntity?

    @SqlUpdate(
        "INSERT INTO persona (nombre, apellido, fecha_nacimiento, fecha_incorporacion, salario, pais, roll, especialidad" +
                " posicion, dorsal, altura, peso, goles, partidos_jugados)VALUES (:nombre, :apellidos, :fechaNacimiento, :fechaIncorporacion" +
                " :salario, :pais, :tipo, :especialidad, :posicion, :dorsal, :altura, :peso, :goles, :partidosJugados)"
    )
    @GetGeneratedKeys("id")
    fun save(@BindBean("persona") persona: PersonaEntity): Int

    @SqlUpdate(
        "UPDATE persona SET nombre= :nombre, apellido= :apellido, fecha_nacimiento= :fechaNacimiento, fecha_incorporacion=" +
                " :fechaIncorporacion, salario= :salario, pais= :pais, roll= :tipo, especialidad= :especialidad, posicion= :posicion," +
                " dorsal= :dorsal, altura= :altura, peso= :peso, goles= :goles, partidos_jugados= :partidosJugados  WHERE id=:identification"
    )
    fun update(@BindBean("persona") persona: PersonaEntity, @Bind("identification") identification: Int): Int

    @SqlUpdate("DELETE FROM persona WHERE id=:id")
    fun deleteById(@Bind("id") id: Int): Int
}
fun getPersonasDao(jdbi: Jdbi): PersonaDao {
    val logger= logging()
    logger.info { "obteniendo personas dao" }
    return jdbi.onDemand(PersonaDao::class.java)
}
/*
val id: Int=0,
    val tipo: String,
    val nombre: String,
    val apellidos: String,
    val fechaNacimiento: LocalDate,
    val fechaIncorporacion: LocalDate,
    val salario: Double,
    val pais: String,
    val especialidad: String?=null,
    val posicion: String?=null,
    val dorsal: Int?=null,
    val altura: Double?=null,
    val peso: Double?=null,
    val goles: Int?=null,
    val partidosJugados: Int?=null,
id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    nombre VARCHAR NOT NULL,
    apellidos VARCHAR NOT NULL,
    fecha_nacimiento DATE NOT NULL ,
    fecha_incorporacion DATE NOT NULL ,
    salario DECIMAL(2-9,2) NOT NULL,
    pais VARCHAR NOT NULL,
    rol VARCHAR NOT NULL,
    especialidad VARCHAR DEFAULT NULL,
    posicion VARCHAR DEFAULT NULL,
    dorsal INT DEFAULT NULL,
    altura DOUBLE(1,2) DEFAULT NULL,
    peso DOUBLE(1,2) DEFAULT NULL,
    goles INT DEFAULT NULL,
    partidos_jugados INT DEFAULT NULL
 */