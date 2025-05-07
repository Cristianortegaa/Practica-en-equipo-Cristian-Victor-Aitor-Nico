package practicaenequipocristianvictoraitornico.personal.cache

import com.github.benmanes.caffeine.cache.Cache
import com.github.benmanes.caffeine.cache.Caffeine
import org.lighthousegames.logging.logging
import practicaenequipocristianvictoraitornico.personal.config.Config
import practicaenequipocristianvictoraitornico.personal.models.Persona

fun darPersonasCache(
    size:Int= Config.configProperties.capacity
):Cache<Long, Persona> {
    val logger= logging()
    logger.debug { "creando cache" }
    return Caffeine.newBuilder().maximumSize(size.toLong()).build()
}