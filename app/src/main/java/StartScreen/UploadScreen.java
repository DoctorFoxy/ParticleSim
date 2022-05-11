package StartScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.particlesim.MainActivity;
import com.example.particlesim.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class UploadScreen extends AppCompatActivity {
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_screen);
    }

    public void onUploadPressed(View caller) throws JSONException {

        // TODO: UPLOAD DATABASE STUFF
        upload();
        //Intent intent = new Intent(this, SettingsScreen.class);
        //startActivity(intent);
    }

    public void onBackPressed(View caller){
        finish();
    }

    public void upload() {
        requestQueue = Volley.newRequestQueue( this );
        String requestURL = "https://studev.groept.be/api/a21pt211/uploadWorld/";

        EditText textWorldName = (EditText)findViewById(R.id.editTextWorldName);
        String WorldName = textWorldName.getText().toString();

        EditText textUsername = (EditText)findViewById(R.id.editTextUsername);
        String Username = textUsername.getText().toString();
        System.out.println(getIntent().getSerializableExtra("World"));
        requestURL += Username + "/" + WorldName + "/" + getIntent().getSerializableExtra("World");

        JsonArrayRequest submitRequest = new JsonArrayRequest(Request.Method.GET, requestURL, null,

                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response) {
                        System.out.println("SUCCESS");
                    }
                },

                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        //txtResponse.setText( error.getLocalizedMessage() );
                        //TODO: Error handler upload
                        System.out.println("ERROR");
                    }
                }
        );

        requestQueue.add(submitRequest);
    }
}