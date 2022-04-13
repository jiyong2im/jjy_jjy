package com.example.gpskmap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_join.*
import kotlinx.android.synthetic.main.activity_login_shop.*

class login_shop : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_shop)
        // edittext 받아오기
        val id = edit_id_shop.text.toString()
        val pw = edit_pw_shop.text.toString()




        // 일반 로그인 전환
        btn_user_login.setOnClickListener {
            val intent = Intent(this, login::class.java)
            startActivity(intent)
        }
        // 로그인 하기
        btn_login_shop.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}