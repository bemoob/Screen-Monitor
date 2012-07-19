package com.faqsAndroid.screenmonitor;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;

public class MainActivity extends Activity 
{
	private class MainActivityHandler extends Handler
	{
		public void handleMessage(Message message)
		{
			switch (message.what)
			{
			case ScreenBroadcastReceiver.SCREEN_IS_ON:
		        ((TextView) findViewById(R.id.text)).setText(R.string.screen_is_on);
				break;
			case ScreenBroadcastReceiver.SCREEN_IS_OFF:
		        ((TextView) findViewById(R.id.text)).setText(R.string.screen_is_off);
				break;
			}
		}
	}
	
	private Handler iHandler;
	private BroadcastReceiver iBroadcastReceiver; 
	
    public void onCreate(Bundle saved_instance_state) 
    {
        super.onCreate(saved_instance_state);
        setContentView(R.layout.main);
        iHandler = new MainActivityHandler();
        ((TextView) findViewById(R.id.text)).setText(R.string.screen_is_on);
        
        // Instanciamos el BroadcastReceiver...
        
        iBroadcastReceiver = new ScreenBroadcastReceiver(iHandler);
        
    	// Los eventos de pantalla encendida o apagada NO pueden monitorizarse a través de su declaración en
    	// el manifest, provablemente porque su activación debe hacerse con cuidado para no afectar a la respuesta
    	// del sistema a la pulsación del botón de encendido.
    	
    	registerReceiver(iBroadcastReceiver, new IntentFilter(Intent.ACTION_SCREEN_OFF));
    	registerReceiver(iBroadcastReceiver, new IntentFilter(Intent.ACTION_SCREEN_ON));       
    }    
    
    protected void onDestroy()
    {
    	// La actividad se ha destruído, por lo que cancelamos la recepción de eventos.
    	// Con independencia del número de eventos que estemos monitorizando mediante un BroadcastReceiver
    	// sólo es necesario ejecutar una vez la función de cancelación, que cancela la recepción de todos
    	// los eventos asociados al objeto...
    	
    	unregisterReceiver(iBroadcastReceiver);
    	super.onDestroy();
    }
}
