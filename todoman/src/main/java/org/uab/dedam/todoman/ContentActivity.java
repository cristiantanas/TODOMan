package org.uab.dedam.todoman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ContentActivity extends AppCompatActivity implements ContentFragment.ContentFragmentIterationListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_content);
		Bundle parameters = getIntent().getExtras();
		String action=parameters.getString("action");
		ContentFragment content = (ContentFragment)
getSupportFragmentManager().findFragmentById(R.id.ContentFragment);

		}

	@Override
	public void onContentFragmentIteration(Bundle parameters) {
// TODO Implementar interacción entre fragment de contenido y activity home
	}

}
