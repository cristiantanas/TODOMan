package org.uab.dedam.todoman;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ContentFragment extends Fragment {
	// TODO Crear variables para todos los elementos del layout
		private ContentFragmentIterationListener mCallback = null;
	private String action;

	public interface ContentFragmentIterationListener{
		public void onContentFragmentIteration(Bundle parameters);
	}

			public ContentFragment() {

	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		mCallback = (ContentFragmentIterationListener) context;
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
		View view =  inflater.inflate(R.layout.fragment_content, container, false);
		if(view != null) {
// TODO Implementar listeners para los botones y otros elementos del fragment.
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

			public void doAction(String action){
switch (action){
			case "new_task":
// TODO Implementar acción de borrado de campos y cambio de título de ventana
				break;
			// TODO Implementar resto de acciones del fragment.
		}
	
			}
			
}
