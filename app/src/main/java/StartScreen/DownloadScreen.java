package StartScreen;

import androidx.appcompat.app.AppCompatActivity;
import  com.example.particlesim.MainActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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
import org.json.JSONObject;

import java.util.ArrayList;

import ParticleEngine.WorldParser;

public class DownloadScreen extends AppCompatActivity {
    ListView downloadsList;
    private RequestQueue requestQueue;
    private ArrayList<String> worldData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_download_screen);
        downloadsList = findViewById(R.id.FilteredDownloadsListView);
        worldData = new ArrayList();

        downloadsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DownloadScreen.this, MainActivity.class);
                System.out.println(worldData.get(i));
                intent.putExtra("World", worldData.get(i));
                startActivity(intent);
                finish();
            }
        });
    }

    public void onUsernameButtonClick(View Caller) {
        setData(true);
    }

    public void onWorldNameButtonClick(View Caller) {
        setData(false);
    }

    public void onBackClick(View Caller) {
        Intent intent = new Intent(DownloadScreen.this, MainActivity.class);
        intent.putExtra("World", getIntent().getSerializableExtra("World"));
        startActivity(intent);
        finish();
    }

    public void setData(boolean isUsername) {
        requestQueue = Volley.newRequestQueue( this );

        String requestURL;

        if (isUsername) {
            EditText textUsername = (EditText)findViewById(R.id.editTexstFilterBy);
            String username = textUsername.getText().toString();

            requestURL = "https://studev.groept.be/api/a21pt211/filterOnUserName/" + username;
        }
        else {
            EditText textWorldName = (EditText)findViewById(R.id.editTexstFilterBy);
            String worldName = textWorldName.getText().toString();

            requestURL = "https://studev.groept.be/api/a21pt211/filterOnWorldName/" + worldName;
        }

        JsonArrayRequest submitRequest = new JsonArrayRequest(Request.Method.GET, requestURL, null,

                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response)
                    {
                        try {
                            ArrayList<String> listItems = new ArrayList();
                            for( int i = 0; i < response.length(); i++ )
                            {
                                JSONObject curObject = response.getJSONObject( i );
                                listItems.add("u/" + curObject.getString("Username") + " | " + curObject.getString("WorldName"));
                                worldData.add(curObject.getString("WorldData"));

                            }
                            ArrayAdapter arrayAdapter = new ArrayAdapter<String>(DownloadScreen.this, android.R.layout.simple_list_item_1, listItems);
                            downloadsList.setAdapter(arrayAdapter);
                        }
                        catch( JSONException e )
                        {
                            System.out.println("Exception");
                            //Log.e( "Database", e.getMessage(), e );
                        }
                    }
                },

                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        System.out.println( error.getLocalizedMessage() );
                    }
                }
        );

        requestQueue.add(submitRequest);
    }


}