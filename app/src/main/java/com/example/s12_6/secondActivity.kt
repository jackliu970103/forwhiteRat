package com.example.s12_6

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class secondActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val backButton =findViewById<Button>(R.id.bbb)
        backButton.setOnClickListener(
            {
                val intent =Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
        )
        val result =findViewById<TextView>(R.id.result)
        val winner =findViewById<TextView>(R.id.winner)
        val getWinner =intent.getStringExtra("winner")
        var iWin :Int =intent.getIntExtra("win",0)
        var itWin:Int =intent.getIntExtra("itwin",0)
        var nowinner =intent.getIntExtra("nowinner",0)
        result.text ="勝：${iWin} 敗：${itWin} 平：${nowinner}"
        winner.text=getWinner
    }
}