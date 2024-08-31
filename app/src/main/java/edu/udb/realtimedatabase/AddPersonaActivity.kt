package edu.udb.realtimedatabase

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import edu.udb.realtimedatabase.datos.Persona

class AddPersonaActivity : AppCompatActivity() {
    private var edtDUI: EditText? = null
    private var edtNombre: EditText? = null
    private var edtApellido: EditText? = null
    private var edtTelefono: EditText? = null
    private var edtEdad: EditText? = null
    private var edtDireccion: EditText? = null
    private var key: String = ""
    private var accion: String = ""
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_persona)
        inicializar()
    }

    private fun inicializar() {
        edtNombre = findViewById(R.id.edtNombre)
        edtDUI = findViewById(R.id.edtDUI)
        edtApellido = findViewById(R.id.edtApellido)
        edtTelefono = findViewById(R.id.edtTelefono)
        edtEdad = findViewById(R.id.edtEdad)
        edtDireccion = findViewById(R.id.edtDireccion)

        // Obtención de datos que envía la actividad anterior
        val datos: Bundle? = intent.extras
        datos?.let {
            key = it.getString("key", "")
            edtDUI?.setText(it.getString("dui", ""))
            edtNombre?.setText(it.getString("nombre", ""))
            edtApellido?.setText(it.getString("apellido", ""))
            edtTelefono?.setText(it.getString("telefono", ""))
            edtEdad?.setText(it.getString("edad", ""))
            edtDireccion?.setText(it.getString("direccion", ""))
            accion = it.getString("accion", "")
        }
    }

    fun guardar(v: View?) {
        val nombre: String = edtNombre?.text.toString()
        val dui: String = edtDUI?.text.toString()
        val apellido: String = edtApellido?.text.toString()
        val telefono: String = edtTelefono?.text.toString()
        val edad: Int = edtEdad?.text.toString().toIntOrNull() ?: 0
        val direccion: String = edtDireccion?.text.toString()

        database = FirebaseDatabase.getInstance().getReference("personas")

        // Se forma objeto persona
        val persona = Persona(dui, nombre, apellido, telefono, edad, direccion)

        if (accion == "a") { // Agregar registro
            val newKey = database.push().key // Generar una nueva clave
            if (newKey != null) {
                database.child(newKey).setValue(persona).addOnSuccessListener {
                    Toast.makeText(this, "Se guardó con éxito", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "No se pudo generar una clave", Toast.LENGTH_SHORT).show()
            }
        } else if (accion == "e") { // Editar registro
            if (key.isNotEmpty()) {
                val personaValues = persona.toMap()
                val childUpdates = hashMapOf<String, Any>(
                    key to personaValues
                )
                database.updateChildren(childUpdates)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Se actualizó con éxito", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Error al actualizar", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "No se encontró la clave del registro", Toast.LENGTH_SHORT).show()
            }
        }
        finish()
    }

    fun cancelar(v: View?) {
        finish()
    }
}
