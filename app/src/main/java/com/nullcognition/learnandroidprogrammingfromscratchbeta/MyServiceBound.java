package com.nullcognition.learnandroidprogrammingfromscratchbeta;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyServiceBound extends Service {

  private final android.os.IBinder binder = new com.nullcognition.learnandroidprogrammingfromscratchbeta.MyServiceBound.LocalBinder();

  public MyServiceBound(){
  }

  @Override
  public IBinder onBind(Intent intent){
	return binder;
  }

  public int meth(int a, int b){
	return a + b;
  }

  public class LocalBinder extends android.os.Binder {

	com.nullcognition.learnandroidprogrammingfromscratchbeta.MyServiceBound getService(){
	  // Return this instance of LocalService so clients can call public methods
	  return com.nullcognition.learnandroidprogrammingfromscratchbeta.MyServiceBound.this;
	}
  }
}
