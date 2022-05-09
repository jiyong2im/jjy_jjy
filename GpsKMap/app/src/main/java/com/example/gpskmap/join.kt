package com.example.gpskmap

import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.gpskmap.databinding.ActivityJoinBinding
import java.sql.Connection
import java.sql.DriverManager

class join : AppCompatActivity() {
    private lateinit var binding: ActivityJoinBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJoinBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnJoin.setOnClickListener {
            Log.d(TAG, "회원가입 버튼 클릭")
            val id = binding.editCid.text.toString()
            val pw = binding.editTextPw.text.toString()
            val pw_re = binding.editPwRe.text.toString()
            val phone_num = binding.editPhone.text.toString()
            val resident = binding.editNum.text.toString()
            val resident2 = binding.editNum2.text.toString()

//        val dialog = AlertDialog.Builder(this)
//
//        val listener = DialogInterface.OnClickListener{ _, p1 ->
//            when (p1) {
//                DialogInterface.BUTTON_POSITIVE ->
//                    Log.d(TAG, "다이얼로그")
//            }
//        }
//        dialog.setTitle("회원가입 실패").setMessage("afd").setPositiveButton("확인",
//            DialogInterface.OnClickListener{dialog, id ->
//                Log.d(TAG, "다이얼로그")
//            }
//                    dialog.show()
//          휴대전화 인증번호 인증 로직 구성 요망!
            when {
                !id.isEmpty() ->
                    Toast.makeText(this, "아이디 오류 회원 가입 실패", Toast.LENGTH_SHORT).show()
                !pw.isEmpty() ->
                    Toast.makeText(this, "아이디 오류 회원 가입 실패", Toast.LENGTH_SHORT).show()
                !pw_re.isEmpty() ->
                    Toast.makeText(this, "아이디 오류 회원 가입 실패", Toast.LENGTH_SHORT).show()
                pw !== pw_re ->
                    Toast.makeText(this, "아이디 오류 회원 가입 실패", Toast.LENGTH_SHORT).show()
                !phone_num.isEmpty() ->
                    Toast.makeText(this, "아이디 오류 회원 가입 실패", Toast.LENGTH_SHORT).show()
                !resident.isEmpty() ->
                    Toast.makeText(this, "아이디 오류 회원 가입 실패", Toast.LENGTH_SHORT).show()
                !resident2.isEmpty() ->
                    Toast.makeText(this, "아이디 오류 회원 가입 실패", Toast.LENGTH_SHORT).show()
                else -> {
                    // id값 중복 확인 됐는지 확인 요망.
                    // DB 넣기 요망.'
                    val sharedPreference =
                        getSharedPreferences("file name", Context.MODE_PRIVATE)
                    val editor = sharedPreference.edit()
                    editor.putString("id", id)
                    editor.putString("pw", pw)
                    editor.apply()
                    // 로그인 화면으로 이동
                    val intent = Intent(this, login::class.java)

                    startActivity(intent)
                }
            }
        }

        binding.btnOwner.setOnClickListener {
            val intent = Intent(this, join_shop::class.java)
            startActivity(intent)
        }
    }
}









