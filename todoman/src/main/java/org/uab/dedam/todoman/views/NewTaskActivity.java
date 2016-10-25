package org.uab.dedam.todoman.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import net.sgoliver.android.fragments.R;

public class NewTaskActivity extends AppCompatActivity {
	
	public static final String EXTRA_TEXTO = 
			"net.sgoliver.android.fragments.EXTRA_TEXTO";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_task);
		
		FragmentNewTask detalle =
				(FragmentNewTask)getSupportFragmentManager()
					.findFragmentById(R.id.FrgDetalle);
		
		detalle.mostrarDetalle(getIntent().getStringExtra(EXTRA_TEXTO));
	}
}
