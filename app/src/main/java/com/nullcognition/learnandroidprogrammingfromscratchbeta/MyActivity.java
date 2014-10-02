package com.nullcognition.learnandroidprogrammingfromscratchbeta;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MyActivity extends Activity implements com.nullcognition.learnandroidprogrammingfromscratchbeta.FragmentListView.OnListFragmentItemInteractionListener {

  private DB                      countryDatabase = null;
  private android.database.Cursor countries       = null;

  @Override
  protected void onCreate(Bundle savedInstanceState){
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_my);

	android.app.FragmentManager fragmentManager = getFragmentManager();
	android.app.FragmentTransaction fragmentTrasaction = fragmentManager.beginTransaction();

	android.view.ViewGroup v = (android.view.ViewGroup)findViewById(com.nullcognition.learnandroidprogrammingfromscratchbeta.R.id.relLayout);

	FragmentListView fragmentListView = FragmentListView.newInstance();

	fragmentTrasaction.add(v.getId(), fragmentListView, "FragmentListView");
	// will not be in container ^ is not set

	fragmentTrasaction.addToBackStack("Fragment");
	fragmentTrasaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);

	fragmentTrasaction.commit();
	fragmentManager.executePendingTransactions();

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
	if(fragmentListView != null){
	  fragmentListView.setListAdapter(adapter);
	}

  }

  @Override
  protected void onPause(){
	super.onPause();
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
