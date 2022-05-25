package StartScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.particlesim.MainActivity;
import com.example.particlesim.R;


import android.view.MotionEvent;
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
    @SuppressLint("ClickableViewAccessibility")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_settings_screen);
        ResumeB = findViewById(R.id.ResumeB);
        MainMenuB = findViewById(R.id.MainMenuB);
        UploadB = findViewById(R.id.UploadB);
        DownloadB = findViewById(R.id.DownloadB);
        CheckBoxBW = findViewById(R.id.CheckBoxBW);

        //Listeners
        ResumeB.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    //Button Pressed
                    ResumeB.setBackgroundResource(R.drawable.bluebuttonpressed);
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    //finger was lifted
                    ResumeB.setBackgroundResource(R.drawable.bluebutton);
                }
                return false;
            }
        });

        MainMenuB.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    //Button Pressed
                    MainMenuB.setBackgroundResource(R.drawable.bluebuttonpressed);
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    //finger was lifted
                    MainMenuB.setBackgroundResource(R.drawable.bluebutton);
                }
                return false;
            }
        });

        UploadB.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    //Button Pressed
                    UploadB.setBackgroundResource(R.drawable.bluebuttonpressed);
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    //finger was lifted
                    UploadB.setBackgroundResource(R.drawable.bluebutton);
                }
                return false;
            }
        });

        DownloadB.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    //Button Pressed
                    DownloadB.setBackgroundResource(R.drawable.bluebuttonpressed);
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    //finger was lifted
                    DownloadB.setBackgroundResource(R.drawable.bluebutton);
                }
                return false;
            }
        });
    }
    public void onResumeB_Pressed(View caller){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("World", getIntent().getSerializableExtra("World"));
        startActivity(intent);
        finish();
    }

    public void onMainMenuB_Pressed(View caller){
        Intent intent = new Intent(this, StartScreen.class);
        //TODO: Stuff for CheckBox black empty particle color needs to be implemented
          /*if (CheckBoxBW.isChecked()){
                EmptyParticle.isBlack;
                }*/
        intent.putExtra("World", getIntent().getSerializableExtra("World"));
        startActivity(intent);
        finish();
    }

    public void onUploadB_Pressed(View caller){
        Intent intent = new Intent(this, UploadScreen.class);
        intent.putExtra("World", getIntent().getSerializableExtra("World"));
        startActivity(intent);
        finish();
    }

    public void onDownloadB_Pressed(View caller){
        Intent intent = new Intent(this, DownloadScreen.class);
        startActivity(intent);
        finish();
    }



}
