package com.example.pmdm_fernandezh_ex2eval

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pmdm_fernandezh_ex2eval.databinding.Activity2Binding

class Activity2 : AppCompatActivity() {
    private lateinit var binding: Activity2Binding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding=Activity2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val id=intent.getStringExtra("ID")
        val nombre=intent.getStringExtra("MODULO")
        val ciclo=intent.getStringExtra("CICLO")
        val horas=intent.getStringExtra("HORAS")
        val curso=intent.getStringExtra("CURSO")
        val a=intent.getStringExtra("A")
        binding.textViewLista.text = a
        /*val lista= mutableListOf<String?>()
        lista.add(id)
        lista.add(nombre)
        lista.add(ciclo)
        lista.add(horas)
        lista.add(curso)



            val datos  = lista
            var cadena = ""
            for (fila in datos) {
                cadena += "$fila"
            }
            binding.textViewLista.text = cadena*/



       // binding.textViewLista.text=id


    }
}