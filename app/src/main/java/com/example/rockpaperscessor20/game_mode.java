package com.example.rockpaperscessor20;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class game_mode extends AppCompatActivity {
    int cPoints = 0;
    int pPoints = 0;
    ImageView playImg;
    ImageView compImg;
    TextView playerScore;
    TextView compScore;
    TextView playerName;

    Intent intent;
    String name;
    String roundStr;
    int rounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode);

        intent = getIntent();
        name = intent.getStringExtra(MainActivity.EXTRA_PLAYER_NAME);
        roundStr = intent.getStringExtra(MainActivity.EXTRA_ROUNDS);

        for(int i=0; i< roundStr.length(); i++){
            if(Character.isDigit(roundStr.charAt(i))){
                rounds = Integer.parseInt(roundStr);
            }else{
                Toast.makeText(getApplicationContext(), "Only Numeric Values!",Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(this, MainActivity.class);
                startActivity(intent2);
            }
        }


        playerName = findViewById(R.id.playername);
        playerName.setText(name);

        // buttons
        Button reset = findViewById(R.id.btnReset);
        ImageButton rock = findViewById(R.id.imgrock);
        ImageButton paper = findViewById(R.id.imgpaper);
        ImageButton scissor = findViewById(R.id.imgscissor);


        //images
        playImg = findViewById(R.id.imgViewPlayer);
        compImg = findViewById(R.id.imgViewComp);

        //scores
        playerScore = findViewById(R.id.txtScorePlayer);
        compScore = findViewById(R.id.txtScoreCompter);


        rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playImg.setImageResource(R.drawable.rock);
                int c = randomGenerator();
                int p = 1;
                eval(c, p);
            }
        });
        paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playImg.setImageResource(R.drawable.paper);
                int c = randomGenerator();
                int p = 2;
                eval(c, p);
            }
        });
        scissor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playImg.setImageResource(R.drawable.scissor);
                int c = randomGenerator();
                int p = 3;
                eval(c, p);
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeZero();
            }
        });

    }


    int max = 3;
    int min = 1;

    public int randomGenerator() {
        int rand = (int) (Math.random() * (max - min + 1) + min);
        switch (rand) {
            case 3:
                compImg.setImageResource(R.drawable.scissor);
                break;
            case 2:
                compImg.setImageResource(R.drawable.paper);
                break;
            case 1:
                compImg.setImageResource(R.drawable.rock);
                break;
        }
        return rand;
    }

    public void makeZero() {
        cPoints = 0;
        pPoints = 0;
        playImg.setImageResource(R.drawable.rps);
        compImg.setImageResource(R.drawable.rps);
        playerScore.setText(String.valueOf(0));
        compScore.setText(String.valueOf(0));

    }

    void Winner(String wins) {
        AlertDialog.Builder alrt = new AlertDialog.Builder(game_mode.this);
        alrt.setMessage(wins);
        alrt.setTitle("Winner!");
        alrt.setPositiveButton("OK", (DialogInterface.OnClickListener) (dialog, which) -> {
            makeZero();
        });
        AlertDialog alrtDia = alrt.create();
        alrtDia.show();
    }

    public void eval(int c, int p) {
        if (cPoints == rounds) {
            Winner("ComputerG Win!");
            Toast.makeText(getApplicationContext(), "Computer Wins", Toast.LENGTH_SHORT).show();
            makeZero();
        } else if (pPoints == rounds) {
            Winner(playerName.getText().toString());
            Toast.makeText(getApplicationContext(), playerName.getText().toString(), Toast.LENGTH_SHORT).show();
            makeZero();
        }
        if (c == 3 && p == 1) {
            pPoints += 1;
            playerScore.setText(String.valueOf(pPoints));
            Log.d("pset", "eval: increment p");
        } else if (c == 1 && p == 3) {
            cPoints += 1;
            compScore.setText(String.valueOf(cPoints));
            Log.d("cset", "eval: eval : increment c");
        } else if (c > p) {   // r3 > p2  || r3 > s1
            cPoints += 1;
            compScore.setText(String.valueOf(cPoints));
            Log.d("rock", "eval: rocks c>p");
        } else if (c < p) {
            pPoints += 1;
            playerScore.setText(String.valueOf(pPoints));
            Log.d("comp", "eval: c<p");
        }
    }
}



