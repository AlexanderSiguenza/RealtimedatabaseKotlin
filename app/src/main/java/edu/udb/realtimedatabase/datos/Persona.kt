package edu.udb.realtimedatabase.datos

data class Persona(
    var dui: String? = null,
    var nombre: String? = null,
    var apellido: String? = null,
    var telefono: String? = null,
    var edad: Int? = null,
    var direccion: String? = null,
    var key: String? = null, // Usado como clave generada por Firebase
    var per: MutableMap<String, Boolean> = mutableMapOf()
) {
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "dui" to dui,
            "nombre" to nombre,
            "apellido" to apellido,
            "telefono" to telefono,
            "edad" to edad,
            "direccion" to direccion,
            "per" to per
        )
    }
}
