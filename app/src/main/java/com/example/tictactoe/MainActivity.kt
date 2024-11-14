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

        // Make an array of the playable buttons
        val buttons : Array<Button?> = arrayOf(
            btn1, btn2, btn3,
            btn4, btn5, btn6,
            btn7, btn8, btn9
        )

        // Set up click listeners
        for (button in buttons) {
            button?.setOnClickListener{ onButtonClicked(button) }
        }

        btnNewGame?.setOnClickListener{ resetGame() }

    }

    // Handle button click for TicTacToe buttons
    fun onButtonClicked(button: Button) {
        val buttons : Array<Button?> = arrayOf(
            btn1, btn2, btn3,
            btn4, btn5, btn6,
            btn7, btn8, btn9
        )

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
                instructions?.text = "Player $currentPlayer's Turn" // update turn message
            }
        }
    }

    // Check for winner by checking rows and columns for winning combinations
    fun checkForWinner() : Boolean {

        val buttons : Array<Button?> = arrayOf(btn1, btn2, btn3,
            btn4, btn5, btn6,
            btn7, btn8, btn9)


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

    fun isPlayable() {

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

    fun disableAllButtons() {

        val buttons : Array<Button?> = arrayOf(
            btn1, btn2, btn3,
            btn4, btn5, btn6,
            btn7, btn8, btn9
        )

        for (button in buttons) {
            button?.isEnabled = false
        }
    }
}


















