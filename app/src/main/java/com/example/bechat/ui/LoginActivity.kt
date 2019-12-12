package com.example.bechat.ui

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bechat.R
import com.example.bechat.helper.Util.hideKeyboard
import kotlinx.android.synthetic.main.activity_login.*
import com.example.bechat.helper.Util.onRightDrawableClicked

class LoginActivity : AppCompatActivity(),View.OnClickListener{


    private var hidePass = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //set onclick
        passwordEdittext.onRightDrawableClicked{
            if(hidePass){
                it.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.ic_no_hide,0)
                it.inputType = InputType.TYPE_CLASS_TEXT
                it.setSelection(it.text.length)
                hidePass = false
            }else{
                it.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_hide,0)
                it.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                it.setSelection(it.text.length)
                hidePass = true
            }
        }
        loginBtn.setOnClickListener(this)
        signUpText.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.loginBtn ->{
                hideKeyboard(this,v)
                Toast.makeText(this,"check login",Toast.LENGTH_SHORT).show()
            }
            R.id.signUpText->{
               val intent = Intent(this, SignUpActivity :: class.java)
                startActivity(intent)
            }
        }
    }
}