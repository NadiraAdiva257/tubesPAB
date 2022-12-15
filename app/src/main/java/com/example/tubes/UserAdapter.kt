//package com.example.tubes
//
//import android.app.AlertDialog
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ArrayAdapter
//import android.widget.EditText
//import android.widget.TextView
//import android.widget.Toast
//import com.google.firebase.database.FirebaseDatabase
//
//class UserAdapter(val mCtx: Context, val layoutResId: Int, val user: User): ArrayAdapter<User> (mCtx, layoutResId) {
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
//
//        val view: View = layoutInflater.inflate(layoutResId, null)
//
//        val tvNama: TextView = view.findViewById(R.id.tvNamaProfil)
//        val tvUsername: TextView = view.findViewById(R.id.tvUsernameProfil)
//        val tvEmail: TextView = view.findViewById(R.id.tvEmailProfil)
//        val tvNohp: TextView = view.findViewById(R.id.tvNoHpProfil)
//        val butEdit: TextView = view.findViewById(R.id.butSimpanProfile)
//
//        butEdit.setOnClickListener {
//            showUpdateDialog(user)
//        }
//
//        tvNama.text = user.nama
//        tvUsername.text = user.username
//        tvEmail.text = user.email
//        tvNohp.text = user.noHp
//
//        return view
//    }
//
//    fun showUpdateDialog(user: User) {
//
//        val builder = AlertDialog.Builder(mCtx)
//        builder.setTitle("Edit Profile")
//
//        val inflater = LayoutInflater.from(mCtx)
//        val view = inflater.inflate(R.layout.activity_edit_profil, null)
//
//        val etNama = view.findViewById<EditText>(R.id.etNamaProfil)
//        val etUsername = view.findViewById<EditText>(R.id.tvUsernameProfil)
//        val etEmail = view.findViewById<EditText>(R.id.etEmailProfil)
//        val etNohp = view.findViewById<EditText>(R.id.etNoHpProfil)
//
//        etNama.setText(user.nama)
//        etUsername.setText(user.username)
//        etEmail.setText(user.email)
//        etNohp.setText(user.noHp)
//
//        builder.setView(view)
//
//        builder.setPositiveButton("Update"){p0,p1 ->
//            val dbUser = FirebaseDatabase.getInstance().getReference("User")
//            // menangkap nama yg akan diinputkan kembali ke db
//            val nama = etNama.text.toString().trim()
//            val username = etUsername.text.toString().trim()
//            val email = etEmail.text.toString().trim()
//            val nohp = etNohp.text.toString().trim()
//
//            if(nama.isEmpty()){
//                etNama.error = "Mohon nama diisi"
//                etNama.requestFocus()
//                return@setPositiveButton
//            }
//            if(username.isEmpty()){
//                etUsername.error = "Mohon username diisi"
//                etUsername.requestFocus()
//                return@setPositiveButton
//            }
//            if(email.isEmpty()){
//                etEmail.error = "Mohon email diisi"
//                etEmail.requestFocus()
//                return@setPositiveButton
//            }
//            if(nohp.isEmpty()){
//                etNohp.error = "Mohon nomor hp diisi"
//                etNohp.requestFocus()
//                return@setPositiveButton
//            }
//
//            val user = User(nama, username, email, nohp, "", "")
//
//            dbUser.setValue(user)
//
//            Toast.makeText(mCtx, "Data berhasil diupdate", Toast.LENGTH_SHORT).show()
//        }
//        builder.setNegativeButton("No"){p0,p1 ->
//
//        }
//
//        val alert = builder.create()
//        alert.show()
//    }
//}