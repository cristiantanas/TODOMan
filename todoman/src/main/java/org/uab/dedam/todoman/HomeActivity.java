package org.uab.dedam.todoman;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements HomeView {
	Button btn_taskNew;
	ListView lst_taskList;
	HomePresenter presenter;
	taskAdapter adapter;
	Context homeActivityContext;

	@Override
	protected void onDestroy() {
		presenter.onDestroy();
		super.onDestroy();
	}

	@Override
	protected void onResume() {super.onResume();presenter.onResume();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		homeActivityContext=this;
				lst_taskList=(ListView)findViewById(R.id.lst_taskList);
		presenter=new HomePresenterImpl(homeActivityContext, this, null, new DataBaseInteractorImpl(), new AlarmsInteractorImpl());
		Button btn_taskNew=(Button)findViewById(R.id.btn_taskNew);
		btn_taskNew.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				presenter.onNewTaskClicked();
			}
		});
		lst_taskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				presenter.onTaskClicked(id);
			}
		});
	}

	@Override
	public void setTasks(List<task> tasks) {
		adapter=new taskAdapter(this,tasks);
		lst_taskList.setAdapter(adapter);
	}

	@Override
	public void showMessage(String message) {
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}
}
