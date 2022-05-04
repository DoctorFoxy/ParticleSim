package StartScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.particlesim.MainActivity;
import com.example.particlesim.R;

public class UploadScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_screen);
    }

    public void onUploadPressed(View caller){

        // TODO: UPLOAD DATABASE STUFF

        Intent intent = new Intent(this, SettingsScreen.class);
        startActivity(intent);
    }

    public void onBackPressed(View caller){
        Intent intent = new Intent(this, SettingsScreen.class);
        startActivity(intent);
    }
}