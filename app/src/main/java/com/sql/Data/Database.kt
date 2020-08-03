package com.sql.Data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class Database(context: Context):
    SQLiteOpenHelper(context, DB_NAME , null , DB_VERSION) {

    private val TABLE_NAME ="MyFirstTable"
    private val ID="ID"
    private val EDT_ONE="EDT_ONE"
    private val EDT_TWO="EDT_TWO"
    private val EDT_THREE="EDT_THREE"
    private val EDT_FOUR="EDT_FOUR"

    private val CREATE_TABLE_NAME = "CREATE TABLE IF NOT EXISTS $TABLE_NAME(" +
            ID + " INTEGER_PRIMARY_KEY, $EDT_ONE TEXT, $EDT_TWO TEXT, $EDT_THREE TEXT, $EDT_FOUR TEXT );"



    override fun onCreate(sqlite: SQLiteDatabase?) {
        sqlite?.execSQL(CREATE_TABLE_NAME)

    }

    override fun onUpgrade(sqlite: SQLiteDatabase?, p1: Int, p2: Int) {
        sqlite?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(sqlite)
    }

    fun saveEditTexts(data : DataEdtcs){  //(сохраняем edit text)
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(EDT_ONE, data.EdtOne)
        cv.put(EDT_TWO, data.EdtTwo)
        cv.put(EDT_THREE, data.EdtThree)
        cv.put(EDT_FOUR, data.EdtFour)
        val rowId=db.insert(TABLE_NAME, null, cv)

        Log.d("____save", "$rowId")

        db.close()
    }

    fun getEditTexts(): ArrayList<DataEdtcs> {   //(вытаскивыаем сохраненные данные)
        val list = arrayListOf<DataEdtcs>()
        val db = this.readableDatabase //(открываем доступ к базе данных чтоб могли вывести данные считываемая а не описываемая)
        val cursor = db.query(TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null)

        if (cursor.moveToFirst()){ //(move to first значит идет на самую первую строку)
            val EDT_ONE_INDEX=cursor.getColumnIndex(EDT_ONE)
            val EDT_TWO_INDEX=cursor.getColumnIndex(EDT_TWO)
            val EDT_THREE_INDEX=cursor.getColumnIndex(EDT_THREE)
            val EDT_FOUR_INDEX=cursor.getColumnIndex(EDT_FOUR)

            do {
                val model = DataEdtcs()

                model.EdtOne= cursor.getString(EDT_ONE_INDEX)
                model.EdtTwo = cursor.getString(EDT_TWO_INDEX)
                model.EdtThree= cursor.getString(EDT_THREE_INDEX)
                model.EdtFour= cursor.getString(EDT_FOUR_INDEX)

                list.add(model)

            } while (cursor.moveToNext())
            Log.d("____getsave", "${list.size}")
        }
        cursor.close()
        db.close()
        return list
    }



    companion object {
       private const val DB_NAME = "DB_First"
       private const val DB_VERSION = 1
    }


}