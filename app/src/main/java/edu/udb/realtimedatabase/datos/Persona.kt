package edu.udb.realtimedatabase.datos

class Persona {
    fun key(key: String?) {
        TODO("Not yet implemented")
    }

    var dui: String? = null
    var nombre: String? = null
    var key: String? = null

    constructor() {}
    constructor(dui: String?, nombre: String?) {
        this.dui = dui
        this.nombre = nombre
    }
}