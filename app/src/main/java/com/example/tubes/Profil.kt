package com.example.tubes

import android.app.Dialog
import android.content.Context
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

fun showEditProfil(context: Context, user: User, db: FirebaseDatabase){
    val dialog = Dialog(context)
    dialog.setCancelable(false)
    dialog.setContentView(R.layout.activity_edit_profil)
    dialog.window?.setLayout(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )

    val etNama = dialog.findViewById(R.id.etNamaProfil) as EditText
    val etUsername = dialog.findViewById(R.id.etUsernameProfil) as EditText
    val etEmail = dialog.findViewById(R.id.etEmailProfil) as EditText
    val etNoHp = dialog.findViewById(R.id.etNoHpProfil) as EditText

    etNama.setText(user.nama)
    etUsername.setText(user.username)
    etEmail.setText(user.email)
    etNoHp.setText(user.noHp)

    val butEdit = dialog.findViewById(R.id.butEditProfile) as Button

    butEdit.setOnClickListener {
        if (etNama.text.isNotEmpty() && etUsername.text.isNotEmpty() && etEmail.text.isNotEmpty() && etNoHp.text.isNotEmpty()) {
            val updatedData = mapOf<String, String>(
                "nama" to etNama.text.toString().trim(),
                "username" to etUsername.text.toString().trim(),
                "email" to etEmail.text.toString().trim(),
                "noHp" to etEmail.text.toString().trim()
            )

            /**
             * Update data to firebase
             */
            db.getReference("user").updateChildren(updatedData)
                .addOnSuccessListener {
                    dialog.dismiss()
                    Toast.makeText(context, "Data berhasil diupdate", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(context, "Data berhasil diupdate", Toast.LENGTH_SHORT).show()
                }
        }
    }

    dialog.show()
}