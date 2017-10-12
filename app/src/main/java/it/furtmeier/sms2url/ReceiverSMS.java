package it.furtmeier.sms2url;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by nemiah on 13.05.17.
 */

public class ReceiverSMS extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction()))
            return;


        StringBuffer content = new StringBuffer();

        String from = "";

        for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
            content.append(smsMessage.getDisplayMessageBody());
            from = smsMessage.getDisplayOriginatingAddress();
        }


        String messageBody = content.toString();
        if(messageBody == "")
            return;

        Log.d("SMS", messageBody);

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);

        if(!ActivityMain.isInit)
            ActivityMain.init(sharedPrefs);


        ActivityMain.log("\nNew Message:");
        ActivityMain.log(DateFormat.getDateTimeInstance().format(new Date()));
        ActivityMain.log("From: "+from);
        ActivityMain.log("Body: "+messageBody);
        ActivityMain.log("To: "+sharedPrefs.getString("url", "NULL"));

        String[] data = new String[4];
        data[0] = sharedPrefs.getString("url", "NULL");
        data[1] = sharedPrefs.getString("phone", "NULL");
        data[2] = from;
        data[3] = messageBody;

        new TaskGET().execute(data);

    }
}
