package com.example.pmdm_fernandezh_ex2eval

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class Operaciones {

    lateinit var context: Context
    lateinit var db: SQLiteDatabase


    fun init(context: Context) {
        this.context = context
    }


    fun open(): Boolean {
        val helper = CreacionBaseDatos(context)
        db = helper.writableDatabase
        return db != null

    }

    fun close() {
        db.close()
    }

    //metodos para usar la base de datos:

    fun insertarModulos(nombre:String,ciclo:String,horas:Int,curso:String){
        val registro = ContentValues()

        registro.put("nombre", nombre)
        registro.put("ciclo", ciclo)
        registro.put("horasLectivas", horas)
        registro.put("curso", curso)
        db.insert("modulos", null, registro)

    }

    fun consultarLista(): MutableList<String> {

        val cursor = db.rawQuery("SELECT * FROM modulos", null)
        var lista = mutableListOf<String>()
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val modulo = cursor.getString(1)
                val ciclo = cursor.getString(2)
                val horas = cursor.getInt(3)
                val curso = cursor.getString(4)
                lista.add(id.toString())
                lista.add(modulo)
                lista.add(ciclo)
                lista.add(horas.toString())
                lista.add(curso)

            } while (cursor.moveToNext())

        }
        cursor.close()
        return lista


    }
    fun borrarUsuariosConInsert(id: Int) {

        db.delete("modulos", "id=$id", null)




    }




}