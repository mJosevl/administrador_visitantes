package com.example.mjosevl_20240505.entidades

import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mjosevl_20240505.R
import com.example.mjosevl_20240505.database.DatabaseHelper
import com.example.mjosevl_20240505.entidades.Visitor
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AddVisitorActivity : AppCompatActivity() {
    private lateinit var editTextRut: EditText
    private lateinit var editTextFirstName: EditText
    private lateinit var editTextLastName: EditText
    private lateinit var editTextEntryTime: EditText
    private lateinit var editTextApartment: EditText
    private lateinit var buttonSave: Button
    private lateinit var databaseHelper: DatabaseHelper
    private var selectedEntryTime: Long = System.currentTimeMillis()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_visitor)

        databaseHelper = DatabaseHelper(this)
        editTextRut = findViewById(R.id.editTextRut)
        editTextFirstName = findViewById(R.id.editTextFirstName)
        editTextLastName = findViewById(R.id.editTextLastName)
        editTextEntryTime = findViewById(R.id.editTextEntryTime)
        editTextApartment = findViewById(R.id.editTextApartment)
        buttonSave = findViewById(R.id.buttonSave)

        editTextEntryTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hour)
                calendar.set(Calendar.MINUTE, minute)
                selectedEntryTime = calendar.timeInMillis
                editTextEntryTime.setText(SimpleDateFormat("HH:mm", Locale.getDefault()).format(calendar.time))
            }
            TimePickerDialog(this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show()
        }


        buttonSave.setOnClickListener {
            saveVisitor()
        }
    }


    private fun saveVisitor() {
        val rut = editTextRut.text.toString().trim()
        val firstName = editTextFirstName.text.toString().trim()
        val lastName = editTextLastName.text.toString().trim()
        val apartment = editTextApartment.text.toString().trim()

        // Verifica que todos los campos estén llenos
        if (rut.isNotEmpty() && firstName.isNotEmpty() && lastName.isNotEmpty() && apartment.isNotEmpty()) {
            val visitor = Visitor(rut, firstName, lastName, selectedEntryTime, null, apartment)
            val wasSuccessful = databaseHelper.addVisitor(visitor)

            runOnUiThread {
                if (wasSuccessful) {
                    Log.d("AddVisitorActivity", "Visitante guardado correctamente")
                    Toast.makeText(this@AddVisitorActivity, "Visitante ingresado correctamente", Toast.LENGTH_LONG).show()
                    finish() // Cierra la actividad y regresa
                } else {
                    Toast.makeText(this@AddVisitorActivity, "Error al guardar visitante", Toast.LENGTH_LONG).show()
                }
            }
        } else {
            runOnUiThread {
                Log.d("AddVisitorActivity", "Falló al guardar visitante")
                Toast.makeText(this, "Se deben ingresar todos los datos", Toast.LENGTH_LONG).show()
            }
        }
    }


}

