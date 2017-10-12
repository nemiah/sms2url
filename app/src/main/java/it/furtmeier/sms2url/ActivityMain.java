package it.furtmeier.sms2url;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;

public class ActivityMain extends AppCompatActivity {

    public static TextView TV;
    public static ScrollView SV;
    public static StringBuilder log;
    public static boolean isInit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        TV = (TextView)findViewById(R.id.textView);
        SV = (ScrollView) findViewById(R.id.scrollView);

        //log("TESt\nasdasd\nadas\n\n\n\nasdas\nTESt\nasdasd\nadas\n\n\n\nasdas\nTESt\nasdasd\nadas\n\n\n\nasdas\nTESt\nasdasd\nadas\n\n\n\nasdas\nTESt\nasdasd\nadas\n\n\n\nasdas");

        init(sharedPrefs);

        TV.setText(log);


        /*String[] data = new String[4];
        data[0] = sharedPrefs.getString("url", "NULL");
        data[1] = sharedPrefs.getString("phone", "NULL");
        data[2] = "sms2url";
        data[3] = "Application started";

        new TaskGET().execute(data);*/
    }

    public static void init(SharedPreferences sharedPrefs){
        if(ActivityMain.isInit)
            return;

        if(log == null)
            log = new StringBuilder();

        log("Starting...");
        log(DateFormat.getDateTimeInstance().format(new Date()));
        log("Phone number: "+sharedPrefs.getString("phone", "NULL"));
        log("Target URL:\n"+sharedPrefs.getString("url", "NULL")+"?device="+sharedPrefs.getString("phone", "NULL")+"&phone=remoteNumber&text=SMScontent");

        ActivityMain.isInit = true;
    }

    public static void log(String message){
        //TV.append(message+"\n");

        log.append(message+"\n");

        if(TV != null){
            TV.setText(log);

            scroll();
        }
    }

    private static void scroll(){
        if(SV == null)
            return;

        SV.postDelayed(new Runnable() {
            @Override
            public void run() {
                SV.fullScroll(ScrollView.FOCUS_DOWN);
            }
        }, 500);
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
