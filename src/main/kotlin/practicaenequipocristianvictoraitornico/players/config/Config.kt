package practicaenequipocristianvictoraitornico.players.config

import org.lighthousegames.logging.logging
import java.io.FileNotFoundException
import java.util.*

object Config {
    private val logger= logging()
    val configProperties: ConfiguracionProperties by lazy {
        loadConfig()
    }

    private fun loadConfig():ConfiguracionProperties {
        logger.debug { "cargando propiedades" }
        val properties = Properties()
        //busca el archivo de propiedades si no lo encuentra lanza una excepción
        val propertiesStream=this.javaClass.classLoader.getResourceAsStream("config.properties")
            ?: throw FileNotFoundException("config.properties")
        properties.load(propertiesStream)
        //crea las constantes basándonos en el archivo properties para crear el objeto configuración
        val stringDateConf:String = properties.getProperty("local.time")?:"en-EN"
        val databaseUrl=System.getProperty("database.url")?:"jdbc:h2:./jugadores"
        val initTables=properties.getProperty("init.tables").toBoolean()?:false
        val initdata=properties.getProperty("init.data").toBoolean()?:false
        val cache=properties.getProperty("cache.capacity").toIntOrNull()?: 5
        val imgDir= properties.getProperty("data.imagenes")?:"imagenes"


        return ConfiguracionProperties(databaseUrl,initdata,initTables,cache,stringDateConf,imgDir)
    }



}data class ConfiguracionProperties(
    val url:String,
    val initData: Boolean,
    val initTables: Boolean,
    val capacity: Int,
    val locale: String,
    val imgDir: String
)
