package zrock.manager.app;

import zrock.manager.R;
import zrock.manager.app.chrome.Shared;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class DownloadActivity extends AppCompatActivity 
{
	private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
		setTheme(R.style.Theme_ZRock_Application);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_engine_donwload_progress);
		mToolbar = (Toolbar)findViewById(R.id.zrock_toolbar);
		setSupportActionBar(mToolbar);
		mToolbar.setTitle("Download");

    }

}
