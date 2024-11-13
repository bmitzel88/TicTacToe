package com.example.tictactoe

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var currentPlayer = "X" // Start with player X
    private lateinit var binding: ActivityMainBinding

    // initialize buttons
    private var btn1 : Button? = null
    private var btn2 : Button? = null
    private var btn3 : Button? = null
    private var btn4 : Button? = null
    private var btn5 : Button? = null
    private var btn6 : Button? = null
    private var btn7 : Button? = null
    private var btn8 : Button? = null
    private var btn9 : Button? = null
    private var btnNewGame : Button? = null
    private var instructions: TextView? = null


    // Handle button click for TicTacToe buttons
    fun onButtonClicked(button: Button) {
        // Check if the button is blank
        if (button.text.isEmpty()) {
            button.text = currentPlayer
            button.isEnabled = false; // disable the button

            // Switch turn
            currentPlayer = if (currentPlayer == "X") "O" else "X"
            instructions?.text = "Player $currentPlayer's Turn" // update turn message
        }
    }


    // Reset the game
    fun resetGame() {
        val buttons : Array<Button?> = arrayOf(btn1, btn2, btn3,
                              btn4, btn5, btn6,
                              btn7, btn8, btn9)


        for (button in buttons) {
            button?.text = "" // Clear the text on each button
            button?.isEnabled = true // Enable each button
        }
        currentPlayer = "X" // Reset to first player (x)
        instructions?.text = "Player X's Turn" // Reset instructions

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Get all the buttons
        btn1 = binding.button1
        btn2 = binding.button2
        btn3 = binding.button3
        btn4 = binding.button4
        btn5 = binding.button5
        btn6 = binding.button6
        btn7 = binding.button7
        btn8 = binding.button8
        btn9 = binding.button9
        btnNewGame = binding.newGameButton
        instructions = binding.instructions



    }
}


















