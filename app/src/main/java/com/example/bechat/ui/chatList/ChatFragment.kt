package com.example.bechat.ui.chatList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bechat.R
import com.example.bechat.model.ChatData
import com.example.bechat.model.User
import kotlinx.android.synthetic.main.fragment_chat.*

class ChatFragment :Fragment(){

    lateinit var adapter: AdapterListChat
    private var chatList = mutableListOf<ChatData>()
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

        //cheat data
        var user = User("123","nameuser")
        var chatData = ChatData("new message",123,user,"")
        for (i in 0 .. 20){
            chatList.add(chatData)
        }
        adapter.notifyDataSetChanged()
    }
}