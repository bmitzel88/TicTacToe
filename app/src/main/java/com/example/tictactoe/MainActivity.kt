package com.example.tictactoe

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var currentPlayer = "X" // Start with player X
    private lateinit var buttons: Array<Button>
    private lateinit var instructions : TextView
    private lateinit var newGameButton : Button

    // Handle button click for TicTacToe buttons
    fun onButtonClicked(button: Button) {
        // Check if the button is blank
        if (button.text.isEmpty()) {
            button.text = currentPlayer
            button.isEnabled = false; // disable the button

            // Switch turn
            currentPlayer = if (currentPlayer == "X") "O" else "X"
            instructions.text = "Player $currentPlayer's Turn" // update turn message
        }
    }


    // Reset the game
    fun resetGame() {
        for (button in buttons) {
            button.text = "" // Clear the text on each button
            button.isEnabled = true // Enable each button
        }
        currentPlayer = "X" // Reset to first player (x)
        instructions.text = "Player X's Turn" // Reset instructions

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Find buttons and UI elements
        instructions = findViewById(R.id.instructions)
        newGameButton = findViewById(R.id.newGameButton)

        buttons = arrayOf(
            findViewById(R.id.button1), findViewById(R.id.button2), findViewById(R.id.button3),
            findViewById(R.id.button4), findViewById(R.id.button5), findViewById(R.id.button6),
            findViewById(R.id.button7), findViewById(R.id.button8), findViewById(R.id.button9)
        )


        // Set click listener for the New Game button

        for (button in buttons) {
            button.setOnClickListener { onButtonClicked(button) }
        }

        newGameButton.setOnClickListener {resetGame()}


    }
}


















