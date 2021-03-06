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
import com.example.gpskmap.databinding.ActivityJoinShopBinding
//import kotlinx.android.synthetic.main.activity_join.*
//import kotlinx.android.synthetic.main.activity_join.edit_Cid
//import kotlinx.android.synthetic.main.activity_join_shop.*
import java.sql.Connection
import java.sql.DriverManager


class join_shop : AppCompatActivity() {
    private lateinit var binding: ActivityJoinShopBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityJoinShopBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnJoinShop.setOnClickListener {
            Log.d(TAG, "회원가입 버튼 클릭")
            val id = binding.editJoinIdShop.text.toString()
            val pw = binding.editTextPwShop.text.toString()
            val pw_re = binding.editPwReShop.text.toString()
            val name = binding.editCidShop.text.toString()
            val num = binding.editNumShop.text.toString()
            val num2 = binding.editNumShop2.text.toString()
            val phone_num = binding.editPhoneShop.text.toString()
            val resident = binding.editResidentShop.text.toString()
            val resident2 = binding.editResidentShop.text.toString()
            //인증번호 인증 로직 요먕 !! ! !!!
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

            when {
                !id.isEmpty() ->
                    Toast.makeText(this, "아이디 오류", Toast.LENGTH_SHORT).show()
                !pw.isEmpty() ->
                    Toast.makeText(this, "비밀번호 오류", Toast.LENGTH_SHORT).show()
                !pw_re.isEmpty() ->
                    Toast.makeText(this, "비밀번호 확인 오류", Toast.LENGTH_SHORT).show()
                pw !== pw_re ->
                    Toast.makeText(this, "비밀번호가 같지 않습니다", Toast.LENGTH_SHORT).show()
                !num.isEmpty() ->
                    Toast.makeText(this, "생년월일이 맞지 않습니다", Toast.LENGTH_SHORT).show()
                !phone_num.isEmpty() ->
                    Toast.makeText(this, "전화번호가 맞지 않습니다", Toast.LENGTH_SHORT).show()
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
        // 사용자 회원가입
        binding.btnUserShop.setOnClickListener {
                val intent = Intent(this, join::class.java)
                startActivity(intent)
        }
    }
}








