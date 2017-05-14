package it.furtmeier.sms2url;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
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

                MainActivity.TV.append("Message from: "+smsMessage.getDisplayOriginatingAddress()+"\n");
                MainActivity.TV.append("Message body: "+messageBody+"\n");

                String[] data = new String[3];
                data[0] = "015156513730";
                data[1] = smsMessage.getDisplayOriginatingAddress();
                data[2] = messageBody;

                new TaskGET().execute(data);
            }
        }
    }
}
