package StartScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.particlesim.R;

import org.json.JSONArray;
import org.json.JSONException;


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
        String worldName = textWorldName.getText().toString();
        worldName = worldName.replaceAll(" ", "_");
        System.out.println(worldName);

        EditText textUsername = (EditText)findViewById(R.id.editTextUsername);
        String username = textUsername.getText().toString();
        username = username.replaceAll(" ", "m");

        requestURL += username + "/" + worldName + "/" + getIntent().getSerializableExtra("World");
        System.out.println(requestURL);

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