package com.nullcognition.learnandroidprogrammingfromscratchbeta;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MyActivity extends Activity implements com.nullcognition.learnandroidprogrammingfromscratchbeta.FragmentListView.OnListFragmentItemInteractionListener {

  private static android.content.Intent serviceIntent;
  com.nullcognition.learnandroidprogrammingfromscratchbeta.MyServiceBound boundService;
  boolean isBound = false;
  private android.content.ServiceConnection serviceConnection = new android.content.ServiceConnection() {

	@Override
	public void onServiceConnected(android.content.ComponentName name, android.os.IBinder service){
	  com.nullcognition.learnandroidprogrammingfromscratchbeta.MyServiceBound.LocalBinder binder = (com.nullcognition.learnandroidprogrammingfromscratchbeta.MyServiceBound.LocalBinder)service;
	  boundService = binder.getService();
	  isBound = true;
	}

	@Override
	public void onServiceDisconnected(android.content.ComponentName name){
	  isBound = false;
	}
  };
  android.widget.VideoView videoView = null;
  private DB                      countryDatabase = null;
  private android.database.Cursor countries       = null;

  @Override
  protected void onCreate(Bundle savedInstanceState){
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_my);

	android.app.FragmentManager fragmentManager = getFragmentManager();
	android.app.FragmentTransaction fragmentTrasaction = fragmentManager.beginTransaction();

	android.view.ViewGroup v = (android.view.ViewGroup)findViewById(com.nullcognition.learnandroidprogrammingfromscratchbeta.R.id.linLayout);

	FragmentListView fragmentListView = FragmentListView.newInstance();

	fragmentTrasaction.add(v.getId(), fragmentListView, "FragmentListView");
	// will not be in container ^ is not set

	fragmentTrasaction.addToBackStack("Fragment");
	fragmentTrasaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);

	fragmentTrasaction.commit();
	fragmentManager.executePendingTransactions();

	// video playback, remember to use the right codec
//	android.widget.MediaController mediaController = new android.widget.MediaController(this);
//	videoView = (android.widget.VideoView)findViewById(com.nullcognition.learnandroidprogrammingfromscratchbeta.R.id.videoView);
//	videoView.setMediaController(mediaController);
//	videoView.setVideoPath("/mnt/sdcard/vid.mp4");
//	mediaController.setAnchorView(videoView);
//	mediaController.setMediaPlayer(videoView);
//	videoView.requestFocus();
//	mediaController.show();
//	videoView.start();
//	videoView.setZOrderOnTop(true);

//Create the database helper:

	android.database.sqlite.SQLiteDatabase.CursorFactory factory = null;
	countryDatabase = new DB(this, "countryDB", factory);

	String[] columnNames = {"Name", "Pop", "Area", android.provider.BaseColumns._ID};

	int[] targetLayoutIDs = {com.nullcognition.learnandroidprogrammingfromscratchbeta.R.id.textView01, com.nullcognition.learnandroidprogrammingfromscratchbeta.R.id.textView02, com.nullcognition.learnandroidprogrammingfromscratchbeta.R.id.textView03};

	android.database.sqlite.SQLiteDatabase db = countryDatabase.getReadableDatabase();
	countries = db.query("countries", columnNames, null, null, null, null, null);
	android.widget.CursorAdapter adapter = new android.widget.SimpleCursorAdapter(this,
																				  com.nullcognition.learnandroidprogrammingfromscratchbeta.R.layout.list,
																				  countries, columnNames, targetLayoutIDs, 0);
	if(fragmentListView != null){fragmentListView.setListAdapter(adapter);}

	android.widget.Button button = (android.widget.Button)findViewById(com.nullcognition.learnandroidprogrammingfromscratchbeta.R.id.button);
	button.setOnLongClickListener(new android.view.View.OnLongClickListener() {

	  @Override
	  public boolean onLongClick(android.view.View v){
		stopService(serviceIntent);
		return true;
	  }
	});
	button.setOnClickListener(new android.view.View.OnClickListener() {

	  @Override
	  public void onClick(android.view.View inView){
		serviceIntent = new android.content.Intent(getApplicationContext(), MyService.class);
		serviceIntent.putExtra("serviceIntent", "value");
		startService(serviceIntent);
	  }
	});

	android.widget.Button button2 = (android.widget.Button)findViewById(com.nullcognition.learnandroidprogrammingfromscratchbeta.R.id.button2);
	button2.setOnClickListener(new android.view.View.OnClickListener() {

	  @Override
	  public void onClick(android.view.View inView){
		android.content.Intent serIntent = new android.content.Intent(getApplicationContext(), MyIntentService.class);
		serIntent.putExtra("serIntent", "values");
		startService(serIntent);
	  }
	});

	android.widget.Button button3 = (android.widget.Button)findViewById(com.nullcognition.learnandroidprogrammingfromscratchbeta.R.id.button3);
	button3.setOnClickListener(new android.view.View.OnClickListener() {

	  @Override
	  public void onClick(android.view.View inView){
		int a = boundService.meth(1, 2);
		android.widget.Toast
		  .makeText(getApplicationContext(), ("your int from the service is " + Integer.toString(a)), android.widget.Toast.LENGTH_SHORT).show();

	  }
	});
  }

  @Override
  protected void onStart(){
	super.onStart();
	bindService(new android.content.Intent(MyActivity.this, MyServiceBound.class), serviceConnection, android.content.Context.BIND_AUTO_CREATE);

  }

  @Override
  protected void onPause(){
	super.onPause();
	if(isBound == true){
	  unbindService(serviceConnection);
	}
	countryDatabase.close();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu){
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.my, menu);
	return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item){
	// Handle action bar item clicks here. The action bar will
	// automatically handle clicks on the Home/Up button, so long
	// as you specify a parent activity in AndroidManifest.xml.
	int id = item.getItemId();

	//noinspection SimplifiableIfStatement
	if(id == R.id.action_settings){
	  return true;
	}

	return super.onOptionsItemSelected(item);
  }

  @Override
  public void onFragmentListViewItemInteraction(int inPosition){
	android.widget.Toast.makeText(this, "your clicked on " + (inPosition + 1), android.widget.Toast.LENGTH_SHORT).show();


  }
}
