package com.nullcognition.learnandroidprogrammingfromscratchbeta;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {

  public MyService(){
  }

  @Override
  public int onStartCommand(android.content.Intent intent, int flags, int startId){

	String intentString = intent.getExtras().getString("serviceIntent");
	android.widget.Toast.makeText(getApplicationContext(), "Service has received" + intentString, android.widget.Toast.LENGTH_SHORT).show();

	return super.onStartCommand(intent, flags, startId);
  }

  @Override
  public void onDestroy(){
	android.widget.Toast.makeText(getApplicationContext(), "service onDestroy", android.widget.Toast.LENGTH_SHORT).show();

	super.onDestroy();
  }

  @Override
  public IBinder onBind(Intent intent){
	return null;
  }
}
