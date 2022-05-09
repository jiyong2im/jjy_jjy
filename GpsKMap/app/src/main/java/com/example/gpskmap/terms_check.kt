package com.example.gpskmap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import com.example.gpskmap.databinding.ActivityLoginShopBinding
import com.example.gpskmap.databinding.ActivityTermsCheckBinding

class terms_check : AppCompatActivity() {
    private lateinit var binding: ActivityTermsCheckBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTermsCheckBinding.inflate(layoutInflater);
        setContentView(binding.root)


        var listener = CompoundButton.OnCheckedChangeListener { buttonView,
        isChecked ->
            if (isChecked){
                when(buttonView.id){
                    R.id.all_checkBox -> {}
                    R.id.checkBox1   -> {}
                    R.id.checkBox2   -> {}
                    R.id.checkBox3   -> {}
                }
            }else{

            }
        }
        binding.allCheckBox.setOnCheckedChangeListener(listener)
        binding.checkBox1.setOnCheckedChangeListener(listener)
        binding.checkBox2.setOnCheckedChangeListener(listener)
        binding.checkBox3.setOnCheckedChangeListener(listener)
    }
}