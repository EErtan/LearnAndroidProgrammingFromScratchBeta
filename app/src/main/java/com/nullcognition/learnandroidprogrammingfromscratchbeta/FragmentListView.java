package com.nullcognition.learnandroidprogrammingfromscratchbeta;
public class FragmentListView extends android.app.ListFragment {

//  private static final String POSITIVE_KEY = "positive";
//  private int idPositive;

  public FragmentListView(){}

  public static com.nullcognition.learnandroidprogrammingfromscratchbeta.FragmentListView newInstance(){ // int inIdPositive
	com.nullcognition.learnandroidprogrammingfromscratchbeta.FragmentListView fragment = new com.nullcognition.learnandroidprogrammingfromscratchbeta.FragmentListView();
	android.os.Bundle args = new android.os.Bundle();
//	args.putInt(POSITIVE_KEY, inTextIdPositive);
	fragment.setArguments(args);
	return fragment;
  }

  @Override
  public void onCreate(android.os.Bundle savedInstanceState){
	super.onCreate(savedInstanceState);
	if(getArguments() != null){
//	  idPositive = getArguments().getInt(POSITIVE_KEY);
	}
	//setListsListAdapter();
  }

  private void setListsListAdapter(){ // change this based on the adapter used
	setListAdapter( // public static HashMap used below
					new android.widget.ArrayAdapter<String>(getActivity(), R.layout.list, R.id.textView00,
															getResources().getStringArray(com.nullcognition.learnandroidprogrammingfromscratchbeta.R.array.elements)));
  }

  @Override
  public void onAttach(android.app.Activity activity){
	super.onAttach(activity);
	try{
	  fragmentListViewItemInteractionListener = (com.nullcognition.learnandroidprogrammingfromscratchbeta.FragmentListView.OnListFragmentItemInteractionListener)activity;
	}
	catch(ClassCastException e){
	  throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
	}
  }

  @Override
  public void onDetach(){
	super.onDetach();
	fragmentListViewItemInteractionListener = null;
  }

  private com.nullcognition.learnandroidprogrammingfromscratchbeta.FragmentListView.OnListFragmentItemInteractionListener fragmentListViewItemInteractionListener;

  public interface OnListFragmentItemInteractionListener {

	public void onFragmentListViewItemInteraction(int inPosition);
  }

  @Override
  public void onListItemClick(android.widget.ListView inListView, android.view.View inView, int inPosition, long inId){
	super.onListItemClick(inListView, inView, inPosition, inId);
	if(null != fragmentListViewItemInteractionListener){
	  fragmentListViewItemInteractionListener.onFragmentListViewItemInteraction(inPosition);
	}
  }

  public android.widget.ListView getListV(){ return getListView();}

}
