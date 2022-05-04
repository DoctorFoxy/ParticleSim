package StartScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.particlesim.MainActivity;
import com.example.particlesim.R;



import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import ParticleEngine.EmptyParticle;

public class SettingsScreen extends AppCompatActivity {
    private Button ResumeB;
    private Button MainMenuB;
    private Button UploadB;
    private Button DownloadB;
    private CheckBox CheckBoxBW;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_screen);
        ResumeB =findViewById(R.id.ResumeB);
        MainMenuB =findViewById(R.id.MainMenuB);
        UploadB =findViewById(R.id.UploadB);
        DownloadB=findViewById(R.id.DownloadB);
        CheckBoxBW=findViewById(R.id.CheckBoxBW);
    }
    public void onResumeB_Pressed(View caller){
        /*Intent intent = new Intent(this,MainActivity.class);
        if (CheckBoxBW.isChecked()){
            EmptyParticle.isBlack;
            }

            startActivity(intent);*/
        }
    public void onMainMenuB_Pressed(View caller){
    Intent intent = new Intent(this, StartScreen.class);
      /*if (CheckBoxBW.isChecked()){
            EmptyParticle.isBlack;
            }*/
    startActivity(intent);
    }
    public void onUploadB_Pressed(View caller){
        Intent intent = new Intent(this, UploadScreen.class);
        startActivity(intent);
    }
    public void onDownloadB_Pressed(View caller){
    // still needs to be implemented the way to the database
    Intent intent = new Intent(this, StartScreen.class);
      /*if (CheckBoxBW.isChecked()){
            EmptyParticle.isBlack;
            }*/
    startActivity(intent);
    }
}
