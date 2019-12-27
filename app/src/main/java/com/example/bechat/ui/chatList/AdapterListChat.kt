package com.example.bechat.ui.chatList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.bechat.R
import com.example.bechat.model.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AdapterListChat (private val chatList: MutableList<User>, var context: Context): RecyclerView.Adapter<AdapterListChat.ViewHolder>(){

    private var mOnclickItemtListener : AdapterListChat.OnClickItemListener?= null
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
        var avatar = itemview.findViewById<ImageView>(R.id.avatarUserItemImg)
        fun bindData(item : User){
            name.text = item.username
            Glide.with(context)
                    .load(item.avatarURL)
                    .apply(RequestOptions.circleCropTransform())
                    .into(avatar)
            itemView.setOnClickListener {
                mOnclickItemtListener?.onItemClick(item)
            }
        }
    }
    private fun getUser(id :String): User?{
        var user : User?= null
        var reference = FirebaseDatabase.getInstance().getReference("Users").child(id)
        reference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                user = p0.getValue(User ::class.java)

            }

        })
        return user
    }
    fun onClickItemListenner( listen: OnClickItemListener){
        mOnclickItemtListener = listen
    }

    interface OnClickItemListener {
        fun onItemClick(user :User)
    }
}