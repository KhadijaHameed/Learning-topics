package com.example.stack

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.roomwrapperimplement.R
import com.example.roomwrapperimplement.databinding.ActivityStackBinding
import java.util.*

class StackActivity : AppCompatActivity() {


    val stack = Stack<Int>()
    var a: Int = 0
    private lateinit var binding: ActivityStackBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStackBinding.inflate(layoutInflater)
        setContentView(binding.root)  //activity_stack
        supportActionBar?.title = "stack push and pop val"

        binding.btnStackPush.setOnClickListener {
            var pushStack : Int= binding.etStackPush.text.toString().toInt()
            a+=   stack.push(pushStack)
            binding.etStackPush.text = null
        }

        binding.btnShowResult.setOnClickListener {
          //  a = stack.pop()
            //TODO incoMplete ... do stack push and pop count
            binding.tvStackPop.text = stack.pop().toString()
        }

    }

    private fun initViews() {
        //  etNewWord = findViewById(R.id.et_word)

    }

}