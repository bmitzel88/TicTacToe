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

    // Initialize buttons
    private lateinit var buttons: Array<Button>
    private var btnNewGame : Button? = null
    private var instructions: TextView? = null

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

        // Initialize buttons and TextView
        instructions = binding.instructions
        btnNewGame = binding.newGameButton
        buttons = arrayOf(
            binding.button1, binding.button2, binding.button3,
            binding.button4, binding.button5, binding.button6,
            binding.button7, binding.button8, binding.button9
        )

        // Set up click listeners
        for (button in buttons) {
            button.setOnClickListener{ onButtonClicked(button) }
        }

        btnNewGame?.setOnClickListener{ resetGame() }

    }

    // Handle button click for TicTacToe buttons
    fun onButtonClicked(button: Button) {
        // Check if the button is blank
        if (button.text.isEmpty()) {
            button.text = currentPlayer
            button.isEnabled = false; // disable the button

            if (checkForWinner()) {
                instructions?.text = "Player $currentPlayer wins!"
                disableAllButtons() // Prevent user interaction with buttons
            }
            else if (buttons.all {!it!!.isEnabled}) // if all buttons are enabled, no winner, its a draw
                instructions?.text = "It's a draw!"
            else {
                // Switch turn to the other player
                currentPlayer = if (currentPlayer == "X") "O" else "X"
                instructions?.text = "Player $currentPlayer's turn" // update turn message
            }
        }
    }

    // Check for winner by checking rows and columns for winning combinations
    fun checkForWinner() : Boolean {
        val winningCombinations = arrayOf(
            listOf(0, 1, 2), // Row 1
            listOf(3, 4, 5), // Row 2
            listOf(6, 7, 8), // Row 3
            listOf(0, 3, 6), // Column 1
            listOf(1, 4, 7), // Column 2
            listOf(2, 5, 8), // Column 3
            listOf(0, 4, 8), // Diagonal 1
            listOf(2, 4, 6)  // Diagonal 2
        )

        for (combination in winningCombinations) {
            if (buttons[combination[0]]?.text == currentPlayer &&
                buttons[combination[1]]?.text == currentPlayer &&
                buttons[combination[2]]?.text == currentPlayer
            ) {
                return true
            }

        }
        return false
    }

    // Reset the game
    fun resetGame() {
        for (button in buttons) {
            button.text = "" // Clear the text on each button
            button.isEnabled = true // Enable each button
        }
        currentPlayer = "X" // Reset to first player (x)
        instructions?.text = "Player X's turn" // Reset instructions

    }

    fun disableAllButtons() {
        for (button in buttons) {
            button.isEnabled = false
        }
    }
}


















