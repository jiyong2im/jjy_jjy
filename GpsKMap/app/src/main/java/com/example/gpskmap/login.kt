package com.example.gpskmap

import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.gpskmap.databinding.ActivityLoginBinding
import com.example.gpskmap.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_login.*


class login : AppCompatActivity() {
    var pro: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        // edittext 받아오기
        val id = edit_id.text.toString()
        val pw = edit_pw.text.toString()


// 아이디 찾기 다이얼로그
        text_id_find.setOnClickListener {
            var builder = AlertDialog.Builder(this)
            builder.setTitle("커스텀 다이얼로그")
            builder.setIcon(R.mipmap.ic_launcher)

            var v1 = layoutInflater.inflate(R.layout.alertdalog_id_find, null)
            builder.setView(v1)

            // p0에 해당 AlertDialog가 들어온다. findViewById를 통해 view를 가져와서 사용
            var listener = DialogInterface.OnClickListener { p0, p1 ->
                var alert = p0 as AlertDialog
                var edit1: EditText? = alert.findViewById<EditText>(R.id.edit_text_name)
                var edit2: EditText? = alert.findViewById<EditText>(R.id.edit_text_number)
                var edit3: EditText? = alert.findViewById<EditText>(R.id.edit_text_phone1)
                var edit4: EditText? = alert.findViewById<EditText>(R.id.edit_text_phone2)
                var edit5: EditText? = alert.findViewById<EditText>(R.id.edit_text_phone3)

                //임시 아이디 값 불러와서 띄우기 login 에
                    //  test.text = "${edit1?.text}"

            }

            builder.setPositiveButton("확인", listener)
            builder.setNegativeButton("취소", null)

            builder.show()
        }



//
//
//        binding.text_id_find.setOnClickListener {
//            val builder = AlertDialog.Builder(this)
//            val builderItem = AlertdialogEdittextBinding.inflate(layoutInflater)
//            val editText = builderItem.editText
//            with(builder){
//                setTitle("Input Name")
//                setMessage("이름을 입력 하세요")
//                setView(builderItem.root)
//                setPositiveButton("OK"){ dialogInterface: DialogInterface, i: Int ->
//                    if(editText.text != null) toast("입력된 이름 : ${editText.text}")
//                }
//                show()
//            }
//        }
//

//        text_id_find.setOnClickListener {
//            var builder = AlertDialog.Builder(this)
//            builder.setTitle("커스텀 다이얼로그")
//            builder.setIcon(R.mipmap.ic_launcher)
//
//            var v1 = layoutInflater.inflate(R.layout.dialog, null)
//            builder.setView(v1)
//
//            // p0에 해당 AlertDialog가 들어온다. findViewById를 통해 view를 가져와서 사용
//            var listener = DialogInterface.OnClickListener { p0, p1 ->
//                var alert = p0 as AlertDialog
//                var edit1: EditText? = alert.findViewById<EditText>(R.id.editText)
//                var edit2: EditText? = alert.findViewById<EditText>(R.id.editText2)
//
//                tv1.text = "${edit1?.text}"
//                tv1.append("${edit2?.text}")
//            }
//
//            builder.setPositiveButton("확인", listener)
//            builder.setNegativeButton("취소", null)
//
//            builder.show()
//        }



        //사장님 로그인 전환
        btn_shop_login.setOnClickListener {
            val intent = Intent(this, login_shop::class.java)
            startActivity(intent)
        }


        // 로그인
        btn_login.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}