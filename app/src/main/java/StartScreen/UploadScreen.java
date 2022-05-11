package StartScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
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

public class UploadScreen extends AppCompatActivity {
    private RequestQueue requestQueue;
    private TextView txtResponse;

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

    public void upload() {
        requestQueue = Volley.newRequestQueue( this );
        String requestURL = "https://studev.groept.be/api/a21pt211/getAll";

        StringRequest submitRequest = new StringRequest(Request.Method.GET, requestURL,

                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try {
                            JSONArray responseArray = new JSONArray(response);
                            String responseString = "";
                            for( int i = 0; i < responseArray.length(); i++ )
                            {
                                JSONObject curObject = responseArray.getJSONObject( i );
                                responseString += curObject.getString( "name" ) + " : " + curObject.getString( "email" ) + "\n";
                            }
                            txtResponse.setText(responseString);
                        }
                        catch( JSONException e )
                        {
                            Log.e( "Database", e.getMessage(), e );
                        }
                    }
                },

                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        txtResponse.setText( error.getLocalizedMessage() );
                    }
                }
        );

        requestQueue.add(submitRequest);
    }
}