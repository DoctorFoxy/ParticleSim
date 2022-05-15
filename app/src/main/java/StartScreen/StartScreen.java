package StartScreen;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.particlesim.MainActivity;
import com.example.particlesim.R;



import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import ParticleEngine.Particle;
import ParticleEngine.ParticleWorld;
import ParticleEngine.WorldParser;


public class StartScreen extends AppCompatActivity {
    private Button LoadGameB;
    private Button SettingsB;
    private String worldIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.start_screen);
        LoadGameB =findViewById(R.id.LoadGameB);
        SettingsB =findViewById(R.id.SettingsB);

        worldIntent = (String) getIntent().getSerializableExtra("World");

        if (worldIntent == null) {
            System.out.println("YES SIR");
        }
        try {
            System.out.println("MainActivity");
            System.out.println(worldIntent);
            ParticleWorld test = new ParticleWorld();
            test.setWorld(WorldParser.stringToWorld(worldIntent, test));
            System.out.println("test");
        }
        catch (Exception e) {
            System.out.println(e);
        }

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