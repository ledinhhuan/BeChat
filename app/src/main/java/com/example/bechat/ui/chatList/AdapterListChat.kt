package com.example.bechat.ui.chatList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bechat.R
import com.example.bechat.model.ChatData

class AdapterListChat (private val chatList: MutableList<ChatData>, context: Context): RecyclerView.Adapter<AdapterListChat.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.item_list_chat,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chat = chatList[position]
        holder.bindData(chat)
    }

    inner class ViewHolder(itemview: View) :RecyclerView.ViewHolder(itemview){
        var name = itemview.findViewById<TextView>(R.id.nameUserItemTx)
        var mess = itemview.findViewById<TextView>(R.id.contentItemTx)
        fun bindData(item : ChatData){
            name.text = item.user.username
            mess.text = item.mess
        }
    }
}