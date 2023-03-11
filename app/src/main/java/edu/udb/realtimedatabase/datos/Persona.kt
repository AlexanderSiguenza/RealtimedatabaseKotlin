package edu.udb.realtimedatabase.datos

class Persona {
    var dui: String? = null
    var nombre: String? = null
    var key: String? = null

    constructor() {}
    constructor(dui: String?, nombre: String?) {
        this.dui = dui
        this.nombre = nombre
    }
}