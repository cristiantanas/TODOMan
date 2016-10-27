package org.uab.dedam.todoman;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ContentFragment extends Fragment {
	public static final String TAG = "ContentFragment";
	private ContentFragmentIterationListener mCallback = null;
	public interface ContentFragmentIterationListener{
		public void onContentFragmentIteration(Bundle parameters);
	}

	public static ContentFragment newInstance(Bundle arguments){
		ContentFragment f = new ContentFragment();
		if(arguments != null){
			f.setArguments(arguments);
		}
		return f;
	}
	public ContentFragment() {

	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		try{
			mCallback = (ContentFragmentIterationListener) context;
		}catch(CastClassException ex){
			Log.e("ContentFragment", "El Activity debe implementar la interfaz ContentFragmentIterationListener");
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
		View v =  inflater.inflate(R.layout.fragment_Content, container, false);
		if(v != null) {

		}
		return v;
		}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
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
