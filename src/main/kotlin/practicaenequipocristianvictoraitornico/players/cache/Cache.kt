package practicaenequipocristianvictoraitornico.players.cache

import com.github.benmanes.caffeine.cache.Cache
import com.github.benmanes.caffeine.cache.Caffeine
import org.lighthousegames.logging.logging
import practicaenequipocristianvictoraitornico.players.config.Config
import practicaenequipocristianvictoraitornico.players.models.Persona

fun darPersonasCache(
    size:Int= Config.configProperties.capacity
):Cache<Long, Persona> {
    val logger= logging()
    logger.debug { "creando cache" }
    return Caffeine.newBuilder().maximumSize(size.toLong()).build()
}