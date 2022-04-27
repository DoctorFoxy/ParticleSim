package StartScreen;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.particlesim.MainActivity;
import com.example.particlesim.R;



import android.view.View;
import android.widget.Button;


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

    }
    public void onNewGame_Pressed(View caller){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void onLoadGame_pressed(View caller){

    }
    public void onSettings_Pressed(View caller){
        Intent intent = new Intent(this, SettingsScreen.class);
        startActivity(intent);
    }
}