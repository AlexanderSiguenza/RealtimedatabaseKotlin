package edu.udb.realtimedatabase

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*
import edu.udb.realtimedatabase.datos.Persona

class PersonasActivity : AppCompatActivity() {
    // Ordenamiento para hacer las consultas a los datos
    private val consultaOrdenada: Query = refPersonas.orderByChild("nombre")
    private var personas: MutableList<Persona>? = null
    private var listaPersonas: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personas)
        inicializar()
    }

    private fun inicializar() {
        val fabAgregar: FloatingActionButton = findViewById(R.id.fab_agregar)
        listaPersonas = findViewById(R.id.ListaPersonas)

        // Cuando el usuario haga clic en la lista (para editar registro)
        listaPersonas!!.setOnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(this, AddPersonaActivity::class.java)
            intent.putExtra("accion", "e") // Editar
            val persona = personas!![i]
            intent.putExtra("key", persona.key)
            intent.putExtra("nombre", persona.nombre)
            intent.putExtra("dui", persona.dui)
            intent.putExtra("apellido", persona.apellido)
            intent.putExtra("telefono", persona.telefono)
            intent.putExtra("edad", persona.edad.toString())
            intent.putExtra("direccion", persona.direccion)
            startActivity(intent)
        }

        // Cuando el usuario hace un LongClick (clic sin soltar elemento por más de 2 segundos)
        // Es porque el usuario quiere eliminar el registro
        listaPersonas!!.onItemLongClickListener = AdapterView.OnItemLongClickListener { adapterView, view, position, l ->
            // Preparando cuadro de diálogo para preguntar al usuario
            // Si está seguro de eliminar o no el registro
            val ad = AlertDialog.Builder(this@PersonasActivity)
            ad.setMessage("¿Está seguro de eliminar el registro?")
                .setTitle("Confirmación")
            ad.setPositiveButton("Sí") { dialog, id ->
                personas!![position].key?.let {
                    refPersonas.child(it).removeValue()
                }
                Toast.makeText(
                    this@PersonasActivity,
                    "Registro borrado!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            ad.setNegativeButton("No") { dialog, id ->
                Toast.makeText(
                    this@PersonasActivity,
                    "Operación de borrado cancelada!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            ad.show()
            true
        }

        fabAgregar.setOnClickListener {
            // Cuando el usuario quiere agregar un nuevo registro
            val intent = Intent(this, AddPersonaActivity::class.java)
            intent.putExtra("accion", "a") // Agregar
            intent.putExtra("key", "")
            intent.putExtra("nombre", "")
            intent.putExtra("dui", "")
            intent.putExtra("apellido", "")
            intent.putExtra("telefono", "")
            intent.putExtra("edad", "")
            intent.putExtra("direccion", "")
            startActivity(intent)
        }

        personas = ArrayList()

        // Cambiarlo refPersonas a consultaOrdenada para ordenar lista
        consultaOrdenada.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Procedimiento que se ejecuta cuando hubo algún cambio
                // en la base de datos
                // Se actualiza la colección de personas
                personas!!.clear()
                for (dato in dataSnapshot.children) {
                    val persona: Persona? = dato.getValue(Persona::class.java)
                    persona?.key = dato.key // Asigna la clave generada por Firebase
                    if (persona != null) {
                        personas!!.add(persona)
                    }
                }
                val adapter = AdaptadorPersona(
                    this@PersonasActivity,
                    personas as ArrayList<Persona>
                )
                listaPersonas!!.adapter = adapter
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }

    companion object {
        private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        private val refPersonas: DatabaseReference = database.getReference("personas")
    }
}
