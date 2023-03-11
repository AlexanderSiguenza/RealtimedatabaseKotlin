package edu.udb.realtimedatabase

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import edu.udb.realtimedatabase.datos.Persona

class AddPersonaActivity : AppCompatActivity() {
    private var edtDUI: EditText? = null
    private var edtNombre: EditText? = null
    private var key = ""
    private var nombre = ""
    private var dui = ""
    private var accion = ""

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_persona)
        inicializar()
    }

    private fun inicializar() {
        edtNombre = findViewById<EditText>(R.id.edtNombre)
        edtDUI = findViewById<EditText>(R.id.edtDUI)

        // Obtención de datos que envia actividad anterior
        val datos: Bundle? = intent.getExtras()
        if (datos != null) {
            key = datos.getString("key").toString()
        }
        if (datos != null) {
            dui = datos.getString("dui").toString()
        }
        if (datos != null) {
            nombre = datos.getString("nombre").toString()
        }
        if (datos != null) {
            accion = datos.getString("accion").toString()
        }
        //edtDUI.setText(dui)
        //edtNombre.setText(nombre)
    }

    fun guardar(v: View?) {
        val nombre: String = edtNombre?.text.toString()
        val dui: String = edtDUI?.text.toString()
        // Se forma objeto persona
        val persona = Persona(dui, nombre)
        if (accion == "a") { //Agregar usando push()
            PersonasActivity.refPersonas.push().setValue(persona)
        } else  // Editar usando setValue
        {
            PersonasActivity.refPersonas.child(key).setValue(persona)
        }
        finish()
    }

    fun cancelar(v: View?) {
        finish()
    }
}