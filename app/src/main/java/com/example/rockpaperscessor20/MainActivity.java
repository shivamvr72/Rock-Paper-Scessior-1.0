package com.example.rockpaperscessor20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button play;
    EditText playerName;
    EditText rounds;
    public static final String EXTRA_PLAYER_NAME =  "com.example.rockpaperscessor20.NAME";
    public static final String EXTRA_ROUNDS =  "com.example.rockpaperscessor20.ROUNDS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play = findViewById(R.id.btnplay);
        playerName = findViewById(R.id.edtPlayerName1);
        rounds = findViewById(R.id.edtRounds2);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameStart();
            }
        });
    }

    public void gameStart(){
        Toast.makeText(getApplicationContext(), "Rock Paper Scissor", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, game_mode.class);
        String pname = playerName.getText().toString();
        String round = rounds.getText().toString();

        intent.putExtra(EXTRA_PLAYER_NAME, pname);
        intent.putExtra(EXTRA_ROUNDS, round);

        startActivity(intent);
    }
}