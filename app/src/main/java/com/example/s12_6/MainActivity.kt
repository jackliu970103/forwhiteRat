package com.example.s12_6

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.s12_6.R

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 確保 ID 與 XML 一致
        val editTextName = findViewById<EditText>(R.id.editTextText) // 修正
        val radioGroup = findViewById<RadioGroup>(R.id.radiogroup)
        val buttonPlay = findViewById<Button>(R.id.button) // 修正
        val name =findViewById<TextView>(R.id.name)
        val winner =findViewById<TextView>(R.id.winner)
        val ichose =findViewById<TextView>(R.id.ichose)
        val itchose=findViewById<TextView>(R.id.itchose)

        buttonPlay.setOnClickListener {
            val playerName = editTextName.text.toString().trim()
            if (playerName.isEmpty()) {
                Toast.makeText(this, "請輸入玩家姓名！", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedOptionId = radioGroup.checkedRadioButtonId
            if (selectedOptionId == -1) {
                Toast.makeText(this, "請選擇剪刀、石頭或布！", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val playerChoice = when (selectedOptionId) {
                R.id.radioButton -> "剪刀"  // 確保 XML ID 正確
                R.id.radioButton2 -> "石頭"
                R.id.radioButton3 -> "布"
                else -> ""
            }

            val choices = listOf("剪刀", "石頭", "布")
            val computerChoice = choices.random()

            val result = when {
                playerChoice == computerChoice -> "平手！"
                (playerChoice == "剪刀" && computerChoice == "布") ||
                        (playerChoice == "石頭" && computerChoice == "剪刀") ||
                        (playerChoice == "布" && computerChoice == "石頭") -> "$playerName"
                else -> "電腦獲勝！"
            }
            name.text="名字\n${editTextName.text}"
            winner.text="勝利者\n$result"
            ichose.text="我方出拳\n$playerChoice"
            itchose.text="電腦出拳\n$computerChoice"
        }
    }
}

