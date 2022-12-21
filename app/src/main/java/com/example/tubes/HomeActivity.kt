package com.example.tubes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment

class HomeActivity : AppCompatActivity() {
    lateinit var home: ImageView
    lateinit var history: ImageView
    lateinit var challenge: ImageView
    lateinit var notif: ImageView
    lateinit var profil: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        home = findViewById<ImageView>(R.id.iv_menu1)
        //history = findViewById<ImageView>(R.id.iv_menu2)
        challenge = findViewById<ImageView>(R.id.iv_menu3)
        //notif = findViewById<ImageView>(R.id.iv_menu4)
        profil = findViewById<ImageView>(R.id.iv_menu5)

        val fragmentHome = HomeFragment()
        //val fragmentHistory = HistoryFragment()
        val fragmentChallenge = ChallengeFragment()
        //val fragmentNotif = NotifFragment()
        val fragmentProfil = ProfilFragment()

        setFragment(fragmentHome)

        home.setOnClickListener{
            setFragment(fragmentHome)

            changeIcon(home, R.drawable.ic_home_active)
            //changeIcon(history, R.drawable.ic_history)
            changeIcon(challenge, R.drawable.ic_quest)
            //changeIcon(notif, R.drawable.ic_notifikasi)
            changeIcon(profil, R.drawable.ic_profil)
        }

//        history.setOnClickListener{
//            setFragment(fragmentHistory)
//
//            changeIcon(home, R.drawable.ic_home)
//            changeIcon(history, R.drawable.ic_history_active)
//            changeIcon(challenge, R.drawable.ic_quest)
//            changeIcon(notif, R.drawable.ic_notifikasi)
//            changeIcon(profil, R.drawable.ic_profil)
//        }

        challenge.setOnClickListener{
            setFragment(fragmentChallenge)

            changeIcon(home, R.drawable.ic_home)
            //changeIcon(history, R.drawable.ic_history)
            changeIcon(challenge, R.drawable.ic_quest_active)
            //changeIcon(notif, R.drawable.ic_notifikasi)
            changeIcon(profil, R.drawable.ic_profil)
        }

//        notif.setOnClickListener{
//            setFragment(fragmentNotif)
//
//            changeIcon(home, R.drawable.ic_home)
//            //changeIcon(history, R.drawable.ic_history)
//            changeIcon(challenge, R.drawable.ic_quest)
//            changeIcon(notif, R.drawable.ic_notifikasi_active)
//            changeIcon(profil, R.drawable.ic_profil)
//        }

        profil.setOnClickListener{
            setFragment(fragmentProfil)

            changeIcon(home, R.drawable.ic_home)
            //changeIcon(history, R.drawable.ic_history)
            changeIcon(challenge, R.drawable.ic_quest)
            //changeIcon(notif, R.drawable.ic_notifikasi)
            changeIcon(profil, R.drawable.ic_profil_active)
        }
    }

    private fun setFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransacion = fragmentManager.beginTransaction()
        fragmentTransacion.replace(R.id.layout_frame, fragment)
        fragmentTransacion.commit()
    }

    private fun changeIcon(imageView: ImageView, int: Int) {
        imageView.setImageResource(int)
    }
}