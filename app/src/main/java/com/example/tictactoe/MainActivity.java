package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private int player = 0;
    private int player0, player1;
    private int scoreP1 = 0, scoreP2 = 0;
    private String result = "";

    private GridLayout gridLayout;
    private Button btnNewGame;

    private int[][] winner = {
            {0,1,2}, {0,3,6}, {0,4,8},
            {1,4,7}, {2,5,8}, {3,4,5},
            {6,7,8}, {2,4,6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridLayout = findViewById(R.id.gridLayout);
        btnNewGame = findViewById(R.id.btnNewGame);

        player0 = R.drawable.yellow;
        player1 = R.drawable.red;
    }

    public void onClickCell(View view){
        ImageView iv = (ImageView)view;
        if (player == 0) {
            iv.setTag(player0);
            iv.setImageResource(R.drawable.yellow);
            player = 1;
        }else{
            iv.setTag(player1);
            iv.setImageResource(R.drawable.red);
            player = 0;
        }
        iv.setClickable(false);

        checkWinner();
    }

    public void onNewGame(View view){
        toggleImages(true);
        btnNewGame.setVisibility(View.GONE);
    }

    private void toggleImages(boolean set){
        ArrayList<ImageView> images = getImages();
        for (int i=0; i<images.size(); i++){
            images.get(i).setClickable(set);
            if (set){
                images.get(i).setImageDrawable(null);
                images.get(i).setTag(-1);
            }
        }
    }

    private void checkWinner(){

        ArrayList<ImageView> images = getImages();

            for (int a = 0; a < winner.length; a++){
                if (    Integer.parseInt(String.valueOf(images.get(winner[a][0]).getTag())) == player0 &&
                        Integer.parseInt(String.valueOf(images.get(winner[a][1]).getTag())) == player0 &&
                        Integer.parseInt(String.valueOf(images.get(winner[a][2]).getTag())) == player0){
                            scoreP1 += 1;
                            result = "Player 1";
                            finishGame();
                }

                if (    Integer.parseInt(String.valueOf(images.get(winner[a][0]).getTag())) == player1 &&
                        Integer.parseInt(String.valueOf(images.get(winner[a][1]).getTag())) == player1 &&
                        Integer.parseInt(String.valueOf(images.get(winner[a][2]).getTag())) == player1){

                            scoreP2 += 1;
                            result = "Player 2";
                            finishGame();
                }
            }

        ((TextView)findViewById(R.id.txtP1)).setText("Player 1: " + String.valueOf(scoreP1));
        ((TextView)findViewById(R.id.txtP2)).setText("Player 2: " + String.valueOf(scoreP2));
    }

    private void finishGame(){
        Log.e("Winner ", result);
        toggleImages(false);
        Toast.makeText(this, result + " has won this game 😎", Toast.LENGTH_SHORT).show();
        btnNewGame.setVisibility(View.VISIBLE);
    }

    private ArrayList<ImageView> getImages(){
        ArrayList<ImageView> images = new ArrayList<>();
        for (int i=0; i<=gridLayout.getChildCount(); i++){
            if (gridLayout.getChildAt(i) instanceof ImageView){
                images.add((ImageView) gridLayout.getChildAt(i));
//                System.out.println("Image : " + i  + " => "+ images.get(images.size()-1).getTag().toString());
            }
        }
        return images;
    }
}
