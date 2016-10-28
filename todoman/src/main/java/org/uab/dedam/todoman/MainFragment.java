package org.uab.dedam.todoman;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends Fragment {
	// TODO Crear variables para todos los elementos del layout
		private MainFragmentIterationListener mCallback = null;
	public interface MainFragmentIterationListener{
		public void onMainFragmentIteration(Bundle parameters);
	}

	public MainFragment() {

	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		mCallback = (MainFragmentIterationListener) context;
		// TODO Solucionar catch exception.
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// super.onCreateView(inflater, container, savedInstanceState);
		View view =  inflater.inflate(R.layout.fragment_main, container, false);
				if(view != null){
			Button btn_taskNew=(Button)view.findViewById(R.id.btn_taskNew);
			btn_taskNew.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Bundle bundle = new Bundle();
					bundle.putString("action", "task_new");
					mCallback.onMainFragmentIteration(bundle);
				}
			});

		}
		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
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
