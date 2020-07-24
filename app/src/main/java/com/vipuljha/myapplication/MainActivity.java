package com.vipuljha.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView winnerTextView;
    Button playAgainButton;

    //0 is red, 1 is yellow and 3 is blank
    int activePlayer = 0;

    boolean gameActive = true;

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        winnerTextView = findViewById(R.id.winner_textView);
        playAgainButton = findViewById(R.id.playagain_button);

        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                androidx.gridlayout.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);
                playAgainButton.setVisibility(View.INVISIBLE);
                winnerTextView.setText("");
                for (int i = 0; i < gridLayout.getChildCount(); i++) {
                    ImageView counter = (ImageView) gridLayout.getChildAt(i);
                    counter.setImageDrawable(null);
                }

                for (int i = 0; i < gameState.length; i++) {

                    gameState[i] = 2;

                }

                activePlayer = 0;

                gameActive = true;
            }
        });
    }


    public void dropCoin(View view) {

        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2 && gameActive) {
            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.red);
                activePlayer = 1;
            } else if (activePlayer == 1) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(360);

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    String winner = "";
                    if (activePlayer == 1) {
                        winner = "Red";
                    } else {
                        winner = "Yellow";
                    }
                    gameActive = false;
                    winnerTextView.setText(winner + " player won!");
                    playAgainButton.setVisibility(View.VISIBLE);
                } else if (gameState[0] != 2 && gameState[1] != 2 && gameState[2] != 2 && gameState[3] != 2 && gameState[4] != 2 && gameState[5] != 2 && gameState[6] != 2 && gameState[7] != 2 && gameState[8] != 2
                        && gameState[winningPosition[0]] != gameState[winningPosition[1]] && gameState[winningPosition[1]] != gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    winnerTextView.setText("nobody has won");
                    playAgainButton.setVisibility(View.VISIBLE);
                }
            }
        }
    }
}