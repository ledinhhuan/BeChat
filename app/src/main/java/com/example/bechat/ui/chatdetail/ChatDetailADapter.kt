package com.example.bechat.ui.chatdetail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bechat.R
import com.example.bechat.model.ChatData

class ChatDetailADapter (private val chatList: MutableList<ChatData>, context: Context, var idUserCurrent : String): RecyclerView.Adapter<ChatDetailADapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.item_list_chat_detail,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chat = chatList[position]
        holder.bindData(chat)
    }

    inner class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview){
        var itemLeft = itemview.findViewById<RelativeLayout>(R.id.itemLeftChat)
        var itemRight = itemview.findViewById<RelativeLayout>(R.id.itemRightChat)
        var avatarLeft = itemview.findViewById<ImageView>(R.id.avatarLeftImg)
        var avatarRight = itemview.findViewById<ImageView>(R.id.avatarRightImg)
        var messLeft = itemview.findViewById<TextView>(R.id.messLeftTx)
        var messRight = itemview.findViewById<TextView>(R.id.messRightTx)
        var timeLeft = itemview.findViewById<TextView>(R.id.messRightTx)
        var timeRight = itemview.findViewById<TextView>(R.id.messRightTx)
        fun bindData(item : ChatData){
            if(item.user.id == idUserCurrent){
                itemLeft.visibility = View.GONE
                itemRight.visibility = View.VISIBLE
                messRight.text= item.mess
            }else{
                itemLeft.visibility = View.VISIBLE
                itemRight.visibility = View.GONE
                messLeft.text= item.mess
            }
        }
    }
}