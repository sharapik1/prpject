package com.deathspirit.worldcinema.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.deathspirit.worldcinema.films.MainActivity
import com.deathspirit.worldcinema.R
import com.deathspirit.worldcinema.common.HTTP
import com.deathspirit.worldcinema.common.MyApp
import org.json.JSONObject

class LogUpActivity : AppCompatActivity() {
    lateinit var nameEditText: EditText
    lateinit var surNameEditText: EditText
    lateinit var eMailEditText: EditText
    lateinit var passwordEditText: EditText
    lateinit var passwordTwoEditText: EditText
    lateinit var app: MyApp
    lateinit var math: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_up)
        app = applicationContext as MyApp
        nameEditText = findViewById(R.id.name)
        surNameEditText = findViewById(R.id.surName)
        eMailEditText = findViewById(R.id.eMail)
        passwordEditText = findViewById(R.id.password)
        passwordTwoEditText = findViewById(R.id.passwordTwo)

    }

    fun register(view: View) {
        val re = Regex("""[a-z0-9]+@[a-z0-9]\.[a-z]{1,3}$""")
        math = re.find(eMailEditText.text.toString()).toString()
        if (eMailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty() && math != null && nameEditText.text.isNotEmpty() && surNameEditText.text.isNotEmpty() && passwordTwoEditText.text.isNotEmpty() ) {
            HTTP.requestPOST(
                "http://cinema.areas.su/auth/register",
                JSONObject().put("username", eMailEditText.text)
                    .put("password", passwordEditText.text)
                    .put("name", nameEditText.text)
                    .put("surName", surNameEditText.text),
                mapOf(
                    "Content-Type" to "application/json"
                )
            ) { result, error, code ->
                runOnUiThread {
                    if (code == 200) {
                        startActivity(Intent(this, MainActivity::class.java))
                    } else
                        AlertDialog.Builder(this)
                            .setTitle("Ошибка http-запроса")
                            .setMessage(error)
                            .setPositiveButton("OK", null)
                            .create()
                            .show()
                }
            }
        }
        else{
            AlertDialog.Builder(this)
                .setTitle("Ошибка")
                .setMessage("Поля пустые, или заполненые не верно(пароли не совпадают или некорректный e-mail)")
                .setPositiveButton("Cancel", null)
                .create()
                .show()
        }

    }
    fun iHaveAcount(view: View) {
        startActivity(Intent(this, LogInActivity::class.java))
    }
}