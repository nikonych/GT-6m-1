package com.example.gt_6m_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.gt_6m_1.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.editText.setText(intent.getStringExtra(TEXT_KEY))
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
                        getString(R.string.empty_edit_text),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun saveTextToIntent() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(TEXT_KEY, binding.editText.text.toString())
        setResult(RESULT_OK, intent)
        finish()
    }

    companion object {
        const val TEXT_KEY = "TEXT_KEY"
    }
}