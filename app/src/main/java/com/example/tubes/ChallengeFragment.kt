package com.example.tubes

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
import com.example.tubes.adapter.QuestAdapter
import com.example.tubes.model.Quest
import com.example.tubes.util.Preferences
import com.google.firebase.database.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ChallengeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChallengeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var preferences: Preferences
    private lateinit var mDatabaseReference: DatabaseReference

    private var questList = ArrayList<Quest>()
    private lateinit var iKoin: ImageView
    private lateinit var tPoinku: TextView
    private lateinit var tPoin: TextView
    private lateinit var rvQuest: RecyclerView

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
        return inflater.inflate(R.layout.fragment_challenge, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        iKoin = requireView().findViewById(R.id.iKoin)
        tPoinku = requireView().findViewById(R.id.tPoinKu)
        tPoin = requireView().findViewById(R.id.tPoin)
        rvQuest = requireView().findViewById(R.id.rvQuest)

        preferences = Preferences(requireActivity().applicationContext)
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Quest")

        tPoin.setText(preferences.getValues("poin"))
        rvQuest.layoutManager = LinearLayoutManager(context)

        getQuest()
        }

    private fun getQuest(){
        mDatabaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(datasnapshot: DataSnapshot) {

                questList.clear()
                for (getdataSnapshot in datasnapshot.children){
                    var quest = getdataSnapshot.getValue(Quest::class.java)
                    questList.add(quest!!)
                }
                rvQuest.adapter = QuestAdapter(questList){

                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(context, ""+databaseError.message, Toast.LENGTH_LONG).show()
            }
        })
    }
    }


