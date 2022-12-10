package com.example.tubes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Spinner
import android.widget.TextView
import java.util.*

class Keranjang : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    lateinit var spJam : Spinner
    lateinit var spBank : Spinner
    var tanggal = 0
    var bulan = 0
    var tahun = 0
    lateinit var teksTanggal : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keranjang)

        spJam = findViewById(R.id.spJam)
        spBank = findViewById(R.id.spBank)

        ArrayAdapter.createFromResource(
            this,
            R.array.pilihanJam,
            android.R.layout.simple_spinner_item
        ).also { listJam ->
            listJam.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spJam.adapter = listJam
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.pilihanBank,
            android.R.layout.simple_spinner_item
        ).also { listBank ->
            listBank.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spBank.adapter = listBank
        }
    }

    fun getSaatIni(){
        val kal: Calendar = Calendar.getInstance()
        tanggal = kal.get(Calendar.DAY_OF_MONTH)
        bulan = kal.get(Calendar.MONTH)
        tahun = kal.get(Calendar.YEAR)
    }

    fun fSetTanggal(view: View){
        getSaatIni()
        DatePickerDialog(this, this, tahun, bulan, tanggal).show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int){
        tanggal = dayOfMonth
        bulan = month
        tahun = year

        teksTanggal = findViewById(R.id.tvTanggal)
        teksTanggal.text = "${tanggal} - ${bulan} - ${tahun}"
    }
}