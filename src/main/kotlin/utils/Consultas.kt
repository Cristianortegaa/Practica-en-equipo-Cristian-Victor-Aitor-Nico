package utils

import models.Entrenadores
import models.Jugadores
import models.Posicion
import services.PersonaServiceImplementation

fun PersonaServiceImplementation.consultas(){
    logger.debug{}
    val lista=repositorio.getAll()
    val jugadores= lista.filter { it is Jugadores }.map { it  as Jugadores}.toList()
    val entrenadores= lista.filter { it is Entrenadores }.map { it as Entrenadores }.toList()

    /**
     * Realizazr las consultas
     */
    println("Consultas Disponibles: ")
    println("1. Listados de personal agrupados por entrenadores y jugadores")
    println("2. El delantero más alto")
    println("3. Media de goles de los delanteros")
    println("4. Defensa con más partidos jugados")
    println("5. Jugadores agrupados por su país de origen")
    println("6. Entrenador con el mayor salario")
    println("7. Promedio de altura de los jugadores agrupados por posición")
    println("8. Listado de todos los jugadores que han anotado más de 10 goles")
    println("9. Jugadores con un salario mayor al promedio del equipo")
    println("10. Número total de partidos jugados por todos los jugadores")
    println("11. Jugadores agrupados por el año de su incorporación al club")
    println("12. Entrenadores agrupados por su especialidad")
    println("13. Jugador más joven en el equipo")
    println("14. Promedio de peso de los jugadores por posición")
    println("15. Listado de todos los jugadores que tienen un dorsal par")
    println("16. Jugadores que han jugado menos de 5 partidos")
    println("17. Media de goles por partido de cada jugador")
    println("18. Listado de jugadores que tienen una altura superior a la media del equipo")
    println("19. Entrenadores que se incorporaron al club en los últimos 5 años")
    println("20. Jugadores que han anotado más goles que el promedio de su posición")
    println("21. Por posición, máximo de goles, mínimo de goles y media")
    println("22. Estimación del coste total de la plantilla")
    println("23. Total del salario pagado, agrupados por año de incorporación")
    println("24. Jugadores agrupados por país y, dentro de cada grupo, el jugador con más partidos jugados")
    println("25. Promedio de goles por posición, y dentro de cada posición, el jugador con el mayor número de goles")
    println("26. Entrenadores agrupados por especialidad, y dentro de cada especialidad, el entrenador con el salario más alto")
    println("27. Jugadores agrupados por década de nacimiento, y dentro de cada grupo, el promedio de partidos jugados")
    println("28. Salario promedio de los jugadores agrupados por su país de origen, y dentro de cada grupo, el jugador con el salario más bajo y alto")
    print("Seleccione una consulta: ")


    when (readlnOrNull()?.toIntOrNull()) {
        1 -> println(lista)
        2 -> println(jugadores.filter { it.posicion == Posicion.DELANTERO }.maxByOrNull { it.altura })
        3 -> println(jugadores.filter { it.posicion == Posicion.DELANTERO }.map { it.goles }.average())
        4 -> println(jugadores.filter { it.posicion == Posicion.DEFENSA }.maxByOrNull { it.partidosJugados })
        5 -> println(jugadores.groupBy { it.pais })
        6 -> println(entrenadores.maxByOrNull { it.salario })
        7 -> jugadores.groupBy { it.posicion }.forEach { (posicion, lista) ->
            val promedioAltura = lista.map { it.altura }.average()
            println("$posicion: $promedioAltura")
        }
        8 -> println(jugadores.filter { it.goles > 10 })
        9 -> {
            val salarioPromedio = jugadores.map { it.salario }.average()
            println(jugadores.filter { it.salario > salarioPromedio })
        }

        10 -> println(jugadores.sumOf { it.partidosJugados })
        11 -> println(jugadores.groupBy { it.fechaIncorporacion.year })
        12 -> println(entrenadores.groupBy { it.especialidad })
        13 -> println(jugadores.minByOrNull { it.fechaNacimiento })
        14 -> jugadores.groupBy { it.posicion }.forEach { (posicion, lista) ->
            val promedioPeso = lista.map { it.peso }.average()
            println("$posicion: $promedioPeso")
        }

        else -> println("Consulta aún no implementada o opción no válida.")
    }
}
