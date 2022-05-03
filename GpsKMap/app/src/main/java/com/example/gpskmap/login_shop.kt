package com.example.gpskmap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gpskmap.databinding.ActivityLoginShopBinding
import com.example.gpskmap.databinding.ActivityMainBinding

class login_shop : AppCompatActivity() {

    private lateinit var binding: ActivityLoginShopBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginShopBinding.inflate(layoutInflater);
        setContentView(binding.root)
        //setContentView(R.layout.activity_login_shop)
        // edittext 받아오기
        val id = binding.editIdShop.text.toString()
        val pw = binding.editPwShop.text.toString()




        // 일반 로그인 전환
        binding.btnUserLoginShop.setOnClickListener {
            val intent = Intent(this, login::class.java)
            startActivity(intent)
        }
        // 로그인 하기
        binding.btnShopLoginShop.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}