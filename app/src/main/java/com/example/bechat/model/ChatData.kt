package com.example.bechat.model

data class ChatData (
        var mess: String,
        var time: Long,
        var user: User,//sender
        var type: String
)