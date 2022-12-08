package com.example.tubes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.database.*
import java.util.prefs.Preferences

class SignInActivity : AppCompatActivity() {

    lateinit var Bdaftar: Button
    lateinit var Eusername: EditText
    lateinit var Epassword: EditText
    lateinit var Bmasuk: Button

    lateinit var mDatabaseReference: DatabaseReference
    lateinit var preference: com.example.tubes.Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
        setContentView(R.layout.activity_sign_in)

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("User")
        preference = Preferences(this)

        Bdaftar = findViewById<Button>(R.id.Bbuat)
        Eusername = findViewById<EditText>(R.id.Eusername)
        Epassword = findViewById<EditText>(R.id.Epassword)
        Bmasuk = findViewById<Button>(R.id.Bmasuk)

        Bdaftar.setOnClickListener {
            var goDaftar = Intent(this, SignUpActivity::class.java)
            startActivity(goDaftar)
        }

        Bmasuk.setOnClickListener {
            val username = Eusername.text.toString().trim()
            val password = Epassword.text.toString().trim()

            if (username.isEmpty()){
                Eusername.error = "Silahkan masukkan username anda"
                Eusername.requestFocus()
                return@setOnClickListener
            }else if (password.isEmpty()){
                Epassword.error = "Silahkan masukkan password anda"
                Epassword.requestFocus()
                return@setOnClickListener
            } else {
                pushLogin(username, password)
            }
        }
    }

    private fun pushLogin(username: String, password: String) {
        mDatabaseReference.child(username).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var user = dataSnapshot.getValue(User::class.java)
                if (user == null) {
                    Toast.makeText(
                        this@SignInActivity, "User tidak ditemukan",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    if (user.password.equals(password)) {

                        preference.setValues("nama", user.nama.toString())
                        preference.setValues("username", user.username.toString())
                        preference.setValues("email", user.email.toString())
                        preference.setValues("password", user.password.toString())
                        preference.setValues("noHp", user.noHp.toString())
                        preference.setValues("status", "1")

                        var goHome = Intent(this@SignInActivity, SignUpActivity::class.java)
                        startActivity(goHome)
                    } else {
                        Toast.makeText(
                            this@SignInActivity, "Password anda salah",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(
                    this@SignInActivity, databaseError.message,
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }
}
