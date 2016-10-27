package org.uab.dedam.todoman;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainFragment extends Fragment {
	public static final String TAG = "MainFragment";
	private MainFragmentIterationListener mCallback = null;
	public interface MainFragmentIterationListener{
		public void onMainFragmentIteration(Bundle parameters);
	}

	public static MainFragment newInstance(Bundle arguments){
		MainFragment f = new MainFragment();
		if(arguments != null){
			f.setArguments(arguments);
		}
		return f;
	}

	public MainFragment() {

	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		try{
			mCallback = (MainFragmentIterationListener) context;
		}catch(CastClassException ex){
			Log.e("MainFragment", "El Activity debe implementar la interfaz MainFragmentIterationListener");
		}
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View v =  inflater.inflate(R.layout.fragment_main, container, false);
		if(v != null){
			Button btn_taskNew = (Button) findViewById(R.id.btn_taskNew);
			btn_taskNew.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Bundle bundle = new Bundle();
					bundle.putString("action", "task_new");
					mCallback.onMainFragmentIteration(bundle);
				}
			});

		}
		return v;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		listView.setAdapter(new MyListAdapter);
		textView.setText("Hola mundo");
		if(savedInstanceState !=null){
			textView.setText(savedInstanceState.getString("helloWorld");
		}
	}

	@Override
	public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
		super.onViewStateRestored(savedInstanceState);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public void onDetach() {
		mCallback = null;
		super.onDetach();
	}
}
