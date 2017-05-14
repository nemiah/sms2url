package it.furtmeier.sms2url;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ReceiverBooted extends BroadcastReceiver {
	@Override
	public void onReceive(final Context context, Intent intent) {
		//CheckRunningReceiver.enable(context);

		String[] data = new String[3];
		data[0] = "015156513730";
		data[1] = "sms2url";
		data[2] = "Device booted";

		new TaskGET().execute(data);
	}
}
