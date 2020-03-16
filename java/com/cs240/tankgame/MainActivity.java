package com.cs240.tankgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends Activity {

    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openGameWindow();
            }

        });
    }
    public void openGameWindow(){
        Intent intent = new Intent (this, GameWindow.class);
        startActivity(intent);
    }
}