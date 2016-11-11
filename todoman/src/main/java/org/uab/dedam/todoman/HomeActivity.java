package org.uab.dedam.todoman;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
		presenter=new HomePresenterImpl(homeActivityContext, this, null, new FindItemsInteractorImpl());
		Button btn_taskNew=(Button)findViewById(R.id.btn_taskNew);
		btn_taskNew.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				presenter.onNewTaskClicked();
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
