package com.sql.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.sql.Data.DataEdtcs
import com.sql.Data.Database
import com.sql.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var db : Database? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Listeners()
        db= Database(applicationContext)
        recycadapt()

        //val s=db?.getEditTexts()

    }

    private fun recycadapt() {
        val adapter= ReclAdapter()
        recycler.adapter = adapter
        val data = db?.getEditTexts()
        if (data != null)
        adapter.update(data)
    }

    private fun Listeners() {
        BtnSave.setOnClickListener {
            db?.saveEditTexts(forlistener())
        }
    }

    private fun forlistener(): DataEdtcs{
        return DataEdtcs(
            EdtOne.text.toString(),
            EdtTwo.text.toString(),
            EdtThree.text.toString(),
            EdtFour.text.toString()
        )
    }
}