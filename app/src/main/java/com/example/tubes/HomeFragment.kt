package com.example.tubes

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tubes.adapter.RestaurantAdapter
import com.example.tubes.model.Restaurant
import com.example.tubes.util.Preferences
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var preferences: Preferences
    private lateinit var mDatabaReference: DatabaseReference

    private var dataList = ArrayList<Restaurant>()
    private lateinit var iv_koin: ImageView
    private lateinit var tv_PoinKu: TextView
    private lateinit var tv_poin: TextView
    private lateinit var rv_daftar_restaurant: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        iv_koin = requireView().findViewById(R.id.iv_koin)
        tv_PoinKu = requireView().findViewById(R.id.tvPoinKu)
        tv_poin = requireView().findViewById(R.id.tvPoin)
        rv_daftar_restaurant = requireView().findViewById(R.id.rvDaftar)

        preferences = Preferences(requireActivity().applicationContext)
        mDatabaReference = FirebaseDatabase.getInstance().getReference("Restaurant1")

        if(preferences.getValues("poin")!="") {
            tv_poin.setText(preferences.getValues("poin"))
        }



        rv_daftar_restaurant.layoutManager = LinearLayoutManager(context)

        getData()

    }

    private fun getData() {
        mDatabaReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                dataList.clear()
                for (getdataSnapshot in dataSnapshot.children) {
                    var restaurant = getdataSnapshot.getValue(Restaurant::class.java)
                    dataList.add(restaurant!!)
                }

                rv_daftar_restaurant.adapter = RestaurantAdapter(dataList) {
                    var  intent = Intent(context, DetailActivity::class.java).putExtra("data", it)
                    startActivity(intent)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(context, ""+databaseError.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}