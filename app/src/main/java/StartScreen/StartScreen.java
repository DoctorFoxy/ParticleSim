package StartScreen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.particlesim.MainActivity;
import com.example.particlesim.R;


import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import ParticleEngine.ParticleWorld;
import ParticleEngine.WorldParser;


public class StartScreen extends AppCompatActivity {
    private Button LoadGameB;
    private Button SettingsB;
    private String worldIntent;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.start_screen);
        LoadGameB =findViewById(R.id.loadGameButton);
        SettingsB =findViewById(R.id.settingsButton);

        //Intent stuff
        worldIntent = (String) getIntent().getSerializableExtra("World");

        if (worldIntent == null) {
            System.out.println("World Intent is null");
        }
        try {
            System.out.println("MainActivity");
            System.out.println(worldIntent);
            ParticleWorld test = new ParticleWorld();
            test.setWorld(WorldParser.stringToWorld(worldIntent, test));
        }
        catch (Exception e) {
            System.out.println(e);
        }

        //Listeners
        Button playButton = findViewById(R.id.newGameButton);
        playButton.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    //Button Pressed
                    playButton.setBackgroundResource(R.drawable.bluebuttonpressed);
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    //finger was lifted
                    playButton.setBackgroundResource(R.drawable.bluebutton);
                }
                return false;
            }
        });

        Button settingsButton = findViewById(R.id.settingsButton);
        settingsButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    //Button Pressed
                    settingsButton.setBackgroundResource(R.drawable.bluebuttonpressed);
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    //finger was lifted
                    settingsButton.setBackgroundResource(R.drawable.bluebutton);
                }
                return false;
            }
        });

        Button loadGameButton = findViewById(R.id.loadGameButton);
        loadGameButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    //Button Pressed
                    loadGameButton.setBackgroundResource(R.drawable.bluebuttonpressed);
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    //finger was lifted
                    loadGameButton.setBackgroundResource(R.drawable.bluebutton);
                }
                return false;
            }
        });
    }

    public void onNewGame_Pressed(View caller){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("World", worldIntent);
        startActivity(intent);
    }
    public void onLoadGame_pressed(View caller){
        Intent intent = new Intent(this, DownloadScreen.class);
        intent.putExtra("World", worldIntent);
        startActivity(intent);
        finish();
    }
    public void onSettings_Pressed(View caller){
        Intent intent = new Intent(this, SettingsScreen.class);
        System.out.println(worldIntent);
        intent.putExtra("World", worldIntent);
        startActivity(intent);
        finish();

    }
}