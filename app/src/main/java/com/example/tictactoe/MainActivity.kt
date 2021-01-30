package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.children
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //player1-X player2-O
    var player = 1
    var gameActive = true
    var boardArray = arrayListOf(-1, -1, -1, -1, -1, -1, -1, -1, -1)
    var winningCombo = listOf(listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8),
            listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8), listOf(0, 4, 8), listOf(2, 4, 6))
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun play(view: View) {
        val slot = view as ImageView
        var index = slot.tag.toString().toInt();
        if (boardArray[index] == -1 && gameActive) {
            boardArray[index] = player
            slot.translationY = -2000f
            if (player == 1) {
                slot.setImageResource(R.drawable.tic_tac_toe_x)
                player = 2
            } else {
                slot.setImageResource(R.drawable.tic_tac_toe_o)
                player = 1
            }
            slot.animate().translationYBy(2000f).rotation(1800f).duration = 500
            for (combo in winningCombo) {
                if (boardArray[combo[0]] == boardArray[combo[1]]
                        && boardArray[combo[1]] == boardArray[combo[2]]
                        && boardArray[combo[0]] != -1) {
                    var winner = ""
                    winner = if (player == 1) {
                        "circles"
                    } else {
                        "crosses"
                    }
                    Toast.makeText(this, "Winner is $winner", Toast.LENGTH_LONG).show();
                    gameActive = false
                    binding.playAgainButton.visibility=View.VISIBLE
                }
            }

        }
        if(gameActive) {
            var count = 0
            for (i in boardArray) {
                if(i==1 || i==2){
                    count++
                    if(count==9){
                        Toast.makeText(this, "It's a draw", Toast.LENGTH_LONG).show();
                        binding.playAgainButton.visibility=View.VISIBLE
                    }
                }
            }
        }

    }

    fun playAgain(view:View){
        binding.playAgainButton.visibility=View.INVISIBLE
        gameActive=true;
        for(i in 0..8){
            boardArray[i]=-1
        }
        binding.root.children.forEach {
            if(it !is Button){
                val slot=it as ImageView
                if(slot.tag!=null){
                    slot.setImageDrawable(null)
                }
            }
        }

    }
}