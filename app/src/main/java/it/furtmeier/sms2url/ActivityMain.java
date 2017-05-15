package it.furtmeier.sms2url;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class ActivityMain extends AppCompatActivity {

    public static TextView TV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Log.d("SMS", "HI, I'm here!");
        TV = (TextView)findViewById(R.id.textView);
        TV.append("Starting...\n");
        TV.append("Phone number: "+sharedPrefs.getString("phone", "NULL")+"\n");
        TV.append("Target URL:\n"+sharedPrefs.getString("url", "NULL")+"?device="+sharedPrefs.getString("phone", "NULL")+"&phone=remoteNumber&text=SMScontent\n");


        String[] data = new String[3];
        data[0] = sharedPrefs.getString("phone", "NULL");
        data[1] = "sms2url";
        data[2] = "Application started";

        new TaskGET().execute(data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                startActivity(new Intent(this, ActivitySettings.class));

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
