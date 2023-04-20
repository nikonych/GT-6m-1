package com.example.gt_6m_1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.gt_6m_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var intent: Intent? = null
    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val text = result.data?.getStringExtra(TEXT_KEY)
            binding.editText.setText(text)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClickListener()
    }

    private fun initClickListener() {
        with(binding) {

            button.setOnClickListener {
                if (editText.text.isNotEmpty()) {
                    saveTextToIntent()
                } else {
                    Toast.makeText(
                        applicationContext,
                        R.string.empty_edit_text,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun saveTextToIntent() {
        intent = Intent(this, ResultActivity::class.java)
        intent?.putExtra(TEXT_KEY, binding.editText.text.toString())
        startForResult.launch(intent)
    }

    companion object {
        const val TEXT_KEY = "TEXT_KEY"
    }

}