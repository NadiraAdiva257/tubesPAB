package com.example.tubes.signs

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tubes.R
import com.example.tubes.User
import com.google.firebase.database.*

class SignUpActivity : AppCompatActivity() {
    lateinit var Iback: ImageView
    lateinit var BdaftarAkun: Button
    lateinit var sNama: EditText
    lateinit var sUsername: EditText
    lateinit var sEmail: EditText
    lateinit var sPassword: EditText
    lateinit var sNohp: EditText

    lateinit var mDatabaseReference: DatabaseReference
    lateinit var mFirebaseInstance: FirebaseDatabase
    lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mFirebaseInstance = FirebaseDatabase.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference()
        mDatabaseReference = mFirebaseInstance.getReference("User")

        Iback = findViewById<ImageView>(R.id.IBackSign)
        BdaftarAkun = findViewById<Button>(R.id.BbuatAkun)
        sNama = findViewById<EditText>(R.id.ENamaSign)
        sUsername = findViewById<EditText>(R.id.EUsernameSign)
        sEmail = findViewById<EditText>(R.id.EEmailSign)
        sPassword = findViewById<EditText>(R.id.EPasswordSign)
        sNohp = findViewById<EditText>(R.id.ENoHpSign)

        Iback.setOnClickListener {
            var goSign = Intent(this, SignInActivity::class.java)
            startActivity(goSign)
            finishAffinity()
        }

        BdaftarAkun.setOnClickListener {
            val namaSign = sNama.text.toString().trim()
            val usernameSign = sUsername.text.toString().trim()
            val emailSign = sEmail.text.toString().trim()
            val passwordSign = sPassword.text.toString().trim()
            val noHpSign = sNohp.text.toString().trim()

            if (namaSign.isEmpty()) {
                sNama.error = "Silahkan isi nama anda"
                sNama.requestFocus()
                return@setOnClickListener
            } else if (usernameSign.isEmpty()) {
                sUsername.error = "Silahkan isi username anda"
                sUsername.requestFocus()
                return@setOnClickListener
            } else if (emailSign.isEmpty()) {
                sEmail.error = "Silahkan isi email anda"
                sEmail.requestFocus()
                return@setOnClickListener
            } else if (passwordSign.isEmpty()) {
                sPassword.error = "Silahkan isi password anda"
                sPassword.requestFocus()
                return@setOnClickListener
            } else if (noHpSign.isEmpty()) {
                sNohp.error = "Silahkan isi nomor telepon anda"
                sNohp.requestFocus()
                return@setOnClickListener
            } else {
                saveUsername(namaSign, usernameSign, emailSign, passwordSign, noHpSign)
            }
        }
    }

    private fun saveUsername(namaSign: String, usernameSign: String, emailSign: String, passwordSign: String, noHpSign: String) {
        var user = User()
        user.nama = namaSign
        user.username = usernameSign
        user.email = emailSign
        user.password = passwordSign
        user.noHp = noHpSign

        if (usernameSign != null) {
            checkingUsername(usernameSign, user)
        }
    }

    private fun checkingUsername(usernameSign: String, data: User) {
        mDatabaseReference.child(usernameSign).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var user = dataSnapshot.getValue(User::class.java)
                if (user == null) {
                    mDatabaseReference.child(usernameSign).setValue(data)

                    var goHome = Intent(this@SignUpActivity, SignInActivity::class.java)
                    startActivity(goHome)
                } else {
                    Toast.makeText(
                        this@SignUpActivity, "Akun telah terdaftar",
                        Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(
                    this@SignUpActivity, "" + databaseError.message,
                    Toast.LENGTH_LONG).show()
            }

        })
    }
}

