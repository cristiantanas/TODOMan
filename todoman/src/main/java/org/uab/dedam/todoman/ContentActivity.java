package org.uab.dedam.todoman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ContentActivity extends AppCompatActivity implements ContentFragment.ContentFragmentIterationListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_content);
	}

	@Override
	public void onContentFragmentIteration(Bundle parameters) {

	}
}
