package com.example.bechat.ui.chatList

import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bechat.R
import com.example.bechat.model.ChatData
import com.example.bechat.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_chat.*

class ChatFragment :Fragment(){

    lateinit var adapter: AdapterListChat
    private var chatList = mutableListOf<User>()
    private var currentUser = FirebaseAuth.getInstance().currentUser
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chat,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = AdapterListChat(chatList,context!!)
        chatRecyclerView.layoutManager= LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        chatRecyclerView.adapter = adapter
        getFriends()
        adapter.onClickItemListenner(object :AdapterListChat.OnClickItemListener{
            override fun onItemClick(user: User) {
                var bundle = Bundle()
                bundle.putString("user",user.id)
                view.findNavController().navigate(R.id.chatDetailFragment,bundle)
            }

        })


        imgSettingChat.setOnClickListener {

        }
    }
    private fun getFriends(){
        var databaseReference : DatabaseReference = FirebaseDatabase.getInstance().getReference("Users").child(currentUser!!.uid)

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                chatList.clear()
                var user = p0.getValue(User::class.java)
                Log.d("TAG:ChatFragment","getFriends: ${user?.friends.toString()}")
                for (id in user?.friends!!){
                    var reference = FirebaseDatabase.getInstance().getReference("Users").child(id)
                    reference.addValueEventListener(object : ValueEventListener {
                        override fun onCancelled(p0: DatabaseError) {

                        }

                        override fun onDataChange(p0: DataSnapshot) {
                            var friend :User = p0.getValue(User ::class.java)!!
                            Log.d("TAG:ChatFragment","getFriends: ${friend.username}")
                            chatList.add(friend)
                        }

                    })
                }
                Handler().postDelayed({
                    adapter.notifyDataSetChanged()
                },500)

            }


        })
    }
}