package StartScreen;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.particlesim.MainActivity;
import com.example.particlesim.R;



import android.view.View;
import android.widget.Button;

import ParticleEngine.Particle;
import ParticleEngine.ParticleWorld;
import ParticleEngine.WorldParser;


public class StartScreen extends AppCompatActivity {
    private Button NewGameB;
    private Button LoadGameB;
    private Button SettingsB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);
        NewGameB =findViewById(R.id.NewGameB);
        LoadGameB =findViewById(R.id.LoadGameB);
        SettingsB =findViewById(R.id.SettingsB);

        if (getIntent().getSerializableExtra("World") == null) {
            System.out.println("YES SIR");
        }
        try {
            System.out.println("MainActivity");
            System.out.println(getIntent().getSerializableExtra("World"));
            ParticleWorld test = new ParticleWorld();
            test.setWorld(WorldParser.stringToWorld((String) getIntent().getSerializableExtra("World"), test));
            System.out.println("test");
        }
        catch (Exception e) {

        }

    }
    public void onNewGame_Pressed(View caller){
        if (getIntent().getSerializableExtra("World") != null) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("World", getIntent().getSerializableExtra("World"));
            startActivity(intent);
            finish();
        }
        else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
    public void onLoadGame_pressed(View caller){
        Intent intent = new Intent(this, DownloadScreen.class);
        startActivity(intent);
        finish();
    }
    public void onSettings_Pressed(View caller){
        Intent intent = new Intent(this, SettingsScreen.class);
        startActivity(intent);
    }
}