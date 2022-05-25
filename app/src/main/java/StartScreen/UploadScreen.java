package StartScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.particlesim.MainActivity;
import com.example.particlesim.R;

import org.json.JSONArray;
import org.json.JSONException;


public class UploadScreen extends AppCompatActivity {
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_upload_screen);

    }

    public void onUploadPressed(View caller) throws JSONException {
        if (getIntent().getSerializableExtra("World") == null) {
            Toast.makeText(getApplicationContext(),"Error: WorldData = null",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("World", getIntent().getSerializableExtra("World"));
            intent.putExtra("Blackmode",getIntent().getBooleanExtra("Blackmode",false));
            startActivity(intent);
            finish();
        }
        else {
            upload();
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("World", getIntent().getSerializableExtra("World"));
            intent.putExtra("Blackmode",getIntent().getBooleanExtra("Blackmode",false));
            startActivity(intent);
            finish();
        }
    }

    public void onBackPressed(View caller){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("World", getIntent().getSerializableExtra("World"));
        intent.putExtra("Blackmode",getIntent().getBooleanExtra("Blackmode",false));
        startActivity(intent);
        finish();
    }



    public void upload() {
        requestQueue = Volley.newRequestQueue( this );
        String requestURL = "https://studev.groept.be/api/a21pt211/uploadWorld/";

        EditText textWorldName = (EditText)findViewById(R.id.editTextWorldName);
        String worldName = textWorldName.getText().toString();
        worldName = worldName.trim();
        worldName = worldName.replaceAll(" ", "_");

        EditText textUsername = (EditText)findViewById(R.id.editTextUsername);
        String username = textUsername.getText().toString();
        username = username.trim();
        username = username.replaceAll(" ", "_");


        requestURL += username + "/" + worldName + "/" + getIntent().getSerializableExtra("World");
        System.out.println(requestURL);

        JsonArrayRequest submitRequest = new JsonArrayRequest(Request.Method.GET, requestURL, null,

                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(getApplicationContext(),"SUCCESS",Toast.LENGTH_SHORT).show();
                    }
                },

                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(getApplicationContext(),"Response error: " + error.toString(),Toast.LENGTH_SHORT).show();
                    }
                }
        );

        requestQueue.add(submitRequest);
    }
}