package com.faqsAndroid.screenmonitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

public class ScreenBroadcastReceiver extends BroadcastReceiver
{
	public static final int SCREEN_IS_OFF = 1;
	public static final int SCREEN_IS_ON = 2;
	
	private Handler iHandler;
	
	ScreenBroadcastReceiver(Handler handler)
	{
		iHandler = handler;
	}
	
	public void onReceive(Context context, Intent intent) 
	{
		Log.d("Screen Monitor", "Received event: " + intent.getAction());
		if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF))
		{
			// La pantalla acaba de apagarse.
			// Lo notificamos usando el handler...
			
			iHandler.sendEmptyMessage(SCREEN_IS_OFF);
		}
		else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON))
		{
			// La pantalla acaba de encenderse.
			// Lo notificamos usando el handler...
			
			iHandler.sendEmptyMessage(SCREEN_IS_ON);
		}
	}

}
