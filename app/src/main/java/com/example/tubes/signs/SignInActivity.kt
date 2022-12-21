package com.example.tubes.signs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.tubes.HomeActivity
import com.example.tubes.R
import com.example.tubes.util.User
import com.example.tubes.util.Preferences
import com.google.firebase.database.*

class SignInActivity : AppCompatActivity() {

    lateinit var Bdaftar: Button
    lateinit var Eusername: EditText
    lateinit var Epassword: EditText
    lateinit var Bmasuk: Button

    lateinit var mDatabaseReference: DatabaseReference
    lateinit var preference: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                        preference.setValues("poin", user.poin.toString())
                        preference.setValues("status", "1")

                        var goHome = Intent(this@SignInActivity, HomeActivity::class.java)
                        startActivity(goHome)
                        finishAffinity()
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
