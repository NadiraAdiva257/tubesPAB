package com.example.tubes

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat.finishAffinity
import com.example.tubes.databinding.FragmentProfilBinding
import com.google.firebase.database.*
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfilFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfilFragment() : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentProfilBinding
    private lateinit var preferences: Preferences
    private lateinit var mDatabaReference: DatabaseReference

    private lateinit var tv_nama_profile: TextView
    private lateinit var tv_username_profile: TextView
    private lateinit var tv_email_profile: TextView
    private lateinit var tv_nohp_profile: TextView
    private lateinit var but_edit_profile: Button

    private lateinit var et_nama_profile: EditText
    private lateinit var et_username_profile: EditText
    private lateinit var et_email_profile: EditText
    private lateinit var et_nohp_profile: EditText

    lateinit var _USERNAME: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentProfilBinding.inflate(layoutInflater)
        arguments?.let {

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val bind = FragmentProfilBinding.inflate(layoutInflater)
//        bind.butEditProfile.setOnClickListener {
//            val intent = Intent(this@ProfilFragment.requireContext(), EditProfil::class.java)
//            startActivity(intent)
//        }

        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_profil, container, false)

        val view = inflater.inflate(R.layout.fragment_profil, container, false)
        et_nama_profile = view.findViewById(R.id.etNamaProfil)
        et_username_profile = view.findViewById(R.id.etUsernameProfil)
        et_email_profile = view.findViewById(R.id.etEmailProfil)
        et_nohp_profile = view.findViewById(R.id.etNoHpProfil)

        preferences = Preferences(requireActivity().applicationContext)
        mDatabaReference = FirebaseDatabase.getInstance().getReference("User")

        if(preferences.getValues("nama")!="" && preferences.getValues("username")!="" && preferences.getValues("email")!="" && preferences.getValues("noHp")!=""){
            et_nama_profile.setText(preferences.getValues("nama"))
            et_username_profile.setText(preferences.getValues("username"))
            et_email_profile.setText(preferences.getValues("email"))
            et_nohp_profile.setText(preferences.getValues("noHp"))
//            tv_nama_profile.setText(preferences.getValues("nama"))
//            tv_username_profile.setText(preferences.getValues("username"))
//            tv_email_profile.setText(preferences.getValues("email"))
//            tv_nohp_profile.setText(preferences.getValues("noHp"))
        }

//        val user: User()
//        etNama.setText(user.nama)
//        etUsername.setText(user.username)
//        etEmail.setText(user.email)
//        etNohp.setText(user.noHp)
//
        val dbUser = FirebaseDatabase.getInstance().getReference("User")

        binding.butEditProfile.setOnClickListener {
            if(et_nama_profile.text.isNotEmpty() && et_username_profile.text.isNotEmpty() && et_email_profile.text.isNotEmpty() && et_nohp_profile.text.isNotEmpty()){
                val updateData = mapOf<String, String>(
                    "nama" to et_nama_profile.text.toString().trim(),
                    "username" to et_username_profile.text.toString().trim(),
                    "email" to et_email_profile.text.toString().trim(),
                    "noHp" to et_nohp_profile.text.toString().trim(),
                )
//
//                dbUser.setValue(updateData)
                dbUser.child("jeli").updateChildren(updateData).addOnCanceledListener {
                    Toast.makeText(this.context,"berhasil update",Toast.LENGTH_LONG).show()
                }
//                dbUser.child("jeli").child("nama").setValue(et_nama_profile.text.toString().trim())
//                dbUser.child("jeli").child("username").setValue(et_username_profile.text.toString().trim())
//                dbUser.child("jeli").child("email").setValue(et_email_profile.text.toString().trim())
//                dbUser.child("jeli").child("noHp").setValue(et_nohp_profile.text.toString().trim())
            }
        }
//
        return view

//        return bind.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        tv_nama_profile = requireView().findViewById(R.id.tvNamaProfil)
//        tv_username_profile = requireView().findViewById(R.id.tvUsernameProfil)
//        tv_email_profile = requireView().findViewById(R.id.tvEmailProfil)
//        tv_nohp_profile = requireView().findViewById(R.id.tvNoHpProfil)
//        but_edit_profile = view.findViewById(R.id.butEditProfile)

//        preferences = Preferences(requireActivity().applicationContext)
//        mDatabaReference = FirebaseDatabase.getInstance().getReference("User")
//
//        if(preferences.getValues("nama")!="" && preferences.getValues("username")!="" && preferences.getValues("email")!="" && preferences.getValues("noHp")!=""){
//            et_nama_profile.setText(preferences.getValues("nama"))
//            et_username_profile.setText(preferences.getValues("username"))
//            et_email_profile.setText(preferences.getValues("email"))
//            et_nohp_profile.setText(preferences.getValues("noHp"))
////            tv_nama_profile.setText(preferences.getValues("nama"))
////            tv_username_profile.setText(preferences.getValues("username"))
////            tv_email_profile.setText(preferences.getValues("email"))
////            tv_nohp_profile.setText(preferences.getValues("noHp"))
//        }
    }

}

