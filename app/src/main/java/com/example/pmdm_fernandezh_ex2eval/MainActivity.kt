package com.example.pmdm_fernandezh_ex2eval

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Spinner
import android.widget.Toast
import com.example.pmdm_fernandezh_ex2eval.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var o: Operaciones
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        o = Operaciones()
        o.init(applicationContext)
        o.open()

        binding.buttonInsertar.setOnClickListener {
            Log.i("botones", "he pulsado insertar")
            insertarDatosMain()

        }
        binding.buttonConsultaTodo.setOnClickListener {
            val prueba=o.consultarLista()
            val prueba2=rellenarLista()
            val lanzador = Intent(this, Activity2::class.java)
            Log.i("botones","he pulsado mostrar todo + $prueba2")
            lanzador.putExtra("ID",prueba[0])
            lanzador.putExtra("MODULO",prueba[1])
            lanzador.putExtra("CICLO",prueba[2])
            lanzador.putExtra("HORAS",prueba[3])
            lanzador.putExtra("CUSO",prueba[4])
            lanzador.putExtra("A",prueba2)



            startActivity(lanzador)

        }
        binding.buttonBorrar.setOnClickListener {
            Log.i("botones", "he pulsado insertar")
            borrarDatosEnMain()
        }


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_principal, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {

        R.id.Insertar -> {
            Log.i("botones", "Pulsado ítem insertar")
            insertarDatosMain()
            true
        }
        R.id.borrar -> {
            Log.i("botones", "Pulsado ítem borrar")
            borrarDatosEnMain()
            true
        }
        R.id.consultar->{
            val prueba=o.consultarLista()
            val prueba2=rellenarLista()
            val lanzador = Intent(this, Activity2::class.java)
            Log.i("botones","he pulsado mostrar todo + $prueba2")
            lanzador.putExtra("ID",prueba[0])
            lanzador.putExtra("MODULO",prueba[1])
            lanzador.putExtra("CICLO",prueba[2])
            lanzador.putExtra("HORAS",prueba[3])
            lanzador.putExtra("CUSO",prueba[4])
            lanzador.putExtra("A",prueba2)



            startActivity(lanzador)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    fun borrarDatosEnMain() {

        if (validarCampos(binding.textInputLayoutId)){

            val id = binding.textInputLayoutId.editText?.text.toString().toInt()

            o.borrarUsuariosConInsert(id)
            Snackbar.make(binding.root, "Se ha borrado correctamente", Snackbar.LENGTH_SHORT)
                .show()


        } else {
            binding.textInputLayoutId.error="El codigo no puede estar vacio"


        }

    }


    fun insertarDatosMain() {

        if (validarCampos(binding.textInputLayoutNombre)
            && validarCampos(binding.textInputLayoutCiclo)
            && validarCampos(binding.textInputLayoutHoras)
            && validarCurso(binding.spinnerCurso)
        ) {

            val nombre = binding.textInputLayoutNombre.editText?.text.toString()
            val ciclo = binding.textInputLayoutCiclo.editText?.text.toString()
            val horas = binding.textInputLayoutHoras.editText?.text.toString().toInt()
            val curso = binding.spinnerCurso.selectedItem.toString()

            o.insertarModulos(nombre, ciclo, horas, curso)

            Log.i("insertar", "se ha insertado")
            Snackbar.make(binding.root, "Se ha insertado correctamente", Snackbar.LENGTH_SHORT)
                .show()

        } else {


            if (!validarCampos(binding.textInputLayoutNombre)) {
                binding.textInputLayoutNombre.error = "no puede haber un campo vacio"
            }
            if (!validarCampos(binding.textInputLayoutCiclo)) {
                binding.textInputLayoutCiclo.error = "no puede haber un campo vacio"
            }
            if (!validarCampos(binding.textInputLayoutHoras)) {
                binding.textInputLayoutHoras.error = "no puede haber un campo vacio"
            }
            if(!validarCurso(binding.spinnerCurso)){
                Toast.makeText(this, "no se ha selccionado el curso valido", Toast.LENGTH_SHORT)
                    .show()
            }


        }


    }


    private fun validarCampos(tilNombre: TextInputLayout): Boolean {

        tilNombre.error=null
        if (tilNombre.editText?.text.toString().isEmpty()) {
            return false
        }
        return true
    }

    private fun validarCurso(modulo: Spinner): Boolean {


        if (modulo.selectedItem == "Seleccione un curso..") {
            return false
            Log.i("Spinner", "es falso")
        }
        return true
        Log.i("Spinner", "es verdadero")
    }
    private fun rellenarLista():String{
        val datos  = o.consultarLista()
        var cadena = ""
        for (fila in datos) {
            cadena += "$fila\n"
        }
        return cadena
    }

    override fun onDestroy() {
        super.onDestroy()
        // Cerramos la base de datos
        o.close()
    }
}

