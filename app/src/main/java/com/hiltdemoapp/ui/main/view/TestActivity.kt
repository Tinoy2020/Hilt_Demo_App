package com.hiltdemoapp.ui.main.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hiltdemoapp.R
import com.hiltdemoapp.utils.ValidatorUtil
import kotlinx.android.synthetic.main.activity_test.*


class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_test)
        setupView()
    }

    private fun setupView() {
        btnLogin.setOnClickListener {
            if (!ValidatorUtil.checkInputValid(inNumber.text.toString())) {
                Toast.makeText(
                    applicationContext,
                    R.string.number_empty,
                    Toast.LENGTH_SHORT
                ).show()
            } else if (inNumber.text.toString() != (inConfirmNumber.text.toString())) {
                Toast.makeText(
                    applicationContext,
                    R.string.toast_error,
                    Toast.LENGTH_SHORT
                ).show()
            } else if (inUsername.text.toString().trim().isEmpty()) {
                Toast.makeText(
                    applicationContext,
                    R.string.username_empty,
                    Toast.LENGTH_SHORT
                ).show()
            }
            else {
                txtLoginResult.text = "Hello " + inUsername.text.toString().trim()
            }
        }
    }

}