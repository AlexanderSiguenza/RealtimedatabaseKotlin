package edu.udb.realtimedatabase

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import edu.udb.realtimedatabase.datos.Persona

class AddPersonaActivity : AppCompatActivity() {
    var edtDUI: EditText? = null
    var edtNombre: EditText? = null
    var key = ""
    var nombre = ""
    var dui = ""
    var accion = ""
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_persona)
        inicializar()
    }

    private fun inicializar() {
        edtNombre = findViewById<EditText>(R.id.edtNombre)
        edtDUI = findViewById<EditText>(R.id.edtDUI)

        // Obtención de datos que envia actividad anterior
        val datos: Bundle? = intent.getExtras()
        key = datos.getString("key").toString()
        dui = datos.getString("dui").toString()
        nombre = datos.getString("nombre").toString()
        accion = datos.getString("accion").toString()
        edtDUI.setText(dui)
        edtNombre.setText(nombre)
    }

    fun guardar(v: View?) {
        val nombre: String = edtNombre.getText().toString()
        val dui: String = edtDUI.getText().toString()
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