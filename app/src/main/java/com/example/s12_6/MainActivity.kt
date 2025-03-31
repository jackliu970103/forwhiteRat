package com.example.s12_6

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextName = findViewById<EditText>(R.id.editTextText)
        val radioGroup = findViewById<RadioGroup>(R.id.radiogroup)
        val buttonPlay = findViewById<Button>(R.id.button)
        val name = findViewById<TextView>(R.id.name)
        val winner = findViewById<TextView>(R.id.winner)
        val ichose = findViewById<TextView>(R.id.ichose)
        val itchose = findViewById<TextView>(R.id.itchose)
        val result = findViewById<TextView>(R.id.result)
        val resetB = findViewById<Button>(R.id.resetButton)

        var iWin = 0
        var itWin = 0
        var nowinner = 0

        result.text = "戰績\n 勝:$iWin 敗:$itWin 平:$nowinner"

        resetB.setOnClickListener {
            iWin = 0
            itWin = 0
            nowinner = 0
            result.text = "戰績\n 勝:$iWin 敗:$itWin 平:$nowinner"  // 更新 UI
        }

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
                R.id.radioButton -> "剪刀"
                R.id.radioButton2 -> "石頭"
                R.id.radioButton3 -> "布"
                else -> ""
            }

            val choices = listOf("剪刀", "石頭", "布")
            val computerChoice = choices.random()

            val resultText = when {
                playerChoice == computerChoice -> {
                    nowinner++
                    "平手！"
                }
                (playerChoice == "剪刀" && computerChoice == "布") ||
                        (playerChoice == "石頭" && computerChoice == "剪刀") ||
                        (playerChoice == "布" && computerChoice == "石頭") -> {
                    iWin++
                    "$playerName 獲勝！"
                }
                else -> {
                    itWin++
                    "電腦獲勝！"
                }
            }

            val intent = Intent(this, secondActivity::class.java)
            intent.putExtra("nowinner", nowinner)
            intent.putExtra("win", iWin)
            intent.putExtra("itwin", itWin)
            if (iWin == 4) {
                intent.putExtra("winner", playerName)
                startActivity(intent)
                finish()
            } else if (itWin == 4) {
                intent.putExtra("winner", "電腦")
                startActivity(intent)
                finish()
            }



            name.text = "名字\n$playerName"
            winner.text = "勝利者\n$resultText"
            ichose.text = "我方出拳\n$playerChoice"
            itchose.text = "電腦出拳\n$computerChoice"
            result.text = "戰績\n 勝:$iWin 敗:$itWin 平:$nowinner"
        }
    }
}
