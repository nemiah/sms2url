package it.furtmeier.sms2url;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * Created by nemiah on 13.05.17.
 */

public class ReceiverSMS extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) {
            for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                String messageBody = smsMessage.getMessageBody();
                Log.d("SMS", messageBody);

                SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);

                ActivityMain.TV.append("\nNew Message:\n");
                ActivityMain.TV.append("From: "+smsMessage.getDisplayOriginatingAddress()+"\n");
                ActivityMain.TV.append("Body: "+messageBody+"\n");
                ActivityMain.TV.append("To: "+sharedPrefs.getString("url", "NULL")+"\n");

                String[] data = new String[4];
                data[0] = sharedPrefs.getString("url", "NULL");
                data[1] = sharedPrefs.getString("phone", "NULL");
                data[2] = smsMessage.getDisplayOriginatingAddress();
                data[3] = messageBody;

                new TaskGET().execute(data);
            }
        }
    }
}
