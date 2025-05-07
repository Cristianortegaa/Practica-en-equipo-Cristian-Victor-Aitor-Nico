package practicaenequipocristianvictoraitornico.players.exception
abstract class PersonasException (val messager: String){
    /**
    * Excepción que indica un problema con el almacenamiento de personas.
    *
    * @param message Mensaje de error.
    */
    class PersonasStorageException(message: String): PersonasException(message)

    /**
     * Excepción que indica que no encuentra a la persona.
     *
     * @param id Identificador personal de la persona que no se ha podido encontrar.
     */
    class PersonaNotFoundException(id: Long): PersonasException("Persona no encontrada con id: $id")

    /**
     * Excepción que indica que los datos de la persona no son válidos
     *
     * @param message Mensaje de error
     */
    class PersonasInvalidoException(message: String): PersonasException("Persona no válida: $message")

}