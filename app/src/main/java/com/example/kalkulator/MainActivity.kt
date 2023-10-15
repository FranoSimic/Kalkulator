package com.example.kalkulator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cijenaEditText = findViewById<EditText>(R.id.cijena_usluge)
        val box = findViewById<TextView>(R.id.ukupno)
        val switch = findViewById<Switch>(R.id.zaokruzi)

        val textWatcher: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not needed for your case
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var _ukupno:Float
                if((cijenaEditText.getText().toString().equals("")))_ukupno=0f
                else{
                    _ukupno = cijenaEditText.text.toString().toFloat()
                }
                box.text = _ukupno.toString()
                val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
                val izvrsnaUsluga = findViewById<RadioButton>(R.id.izvrsna_usluga)
                val odlicnaUsluga = findViewById<RadioButton>(R.id.odlicna_usluga)
                val dobraUsluga = findViewById<RadioButton>(R.id.dobra_usluga)

                // Assign numeric values to each radio button
                izvrsnaUsluga.tag = 1
                odlicnaUsluga.tag = 2
                dobraUsluga.tag = 3
                radioGroup.setOnCheckedChangeListener { group, checkedId ->
                    var ukupno = _ukupno
                    val selectedRadioButton = findViewById<RadioButton>(checkedId)
                    val selectedValue = selectedRadioButton.tag.toString().toInt()
                    when (selectedValue) {
                        1 -> ukupno += (_ukupno * 0.2f)
                        2 -> ukupno += (_ukupno * 0.17f)
                        3 -> ukupno += (_ukupno * 0.15f)

                    }
                    switch.setOnCheckedChangeListener { _, isChecked ->
                        var ukupno_ : Float
                        if(isChecked)ukupno_ = ukupno.toInt().toFloat()
                        else ukupno_ = ukupno
                        box.text = ukupno_.toString()
                    }
                    box.text=ukupno.toString()
                }
                switch.setOnCheckedChangeListener { _, isChecked ->
                    val ukupno = _ukupno.toInt().toFloat()
                    box.text = ukupno.toString()
                }
            }
            override fun afterTextChanged(s: Editable?) {
                // Not needed for your case
            }
        }
        cijenaEditText.addTextChangedListener(textWatcher)
    }
}


