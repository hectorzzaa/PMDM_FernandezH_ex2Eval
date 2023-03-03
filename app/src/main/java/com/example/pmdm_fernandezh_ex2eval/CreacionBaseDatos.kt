package com.example.pmdm_fernandezh_ex2eval

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CreacionBaseDatos (context: Context):
    SQLiteOpenHelper(context, DATABASE_NAME,null,DATABASE_VERSION) {

    companion object{
        private const val DATABASE_NAME="dbModulos"
        private const val DATABASE_VERSION=1
    }
    //CREO LA TABLA
    private val sqlCreate:String = "CREATE TABLE IF NOT EXISTS modulos (" +
            "id INTEGER PRIMARY KEY," +
            "nombre TEXT," +
            "ciclo TEXT," +
            "horasLectivas INTEGER," +
            "curso TEXT)"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(sqlCreate)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");


        onCreate(db)
    }



}