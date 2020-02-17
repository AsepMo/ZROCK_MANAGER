package zrock.manager;

import zrock.manager.app.chrome.Shared;

import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.app.Activity;
import android.net.Uri;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class ManagerActivity extends AppCompatActivity 
{
	private Toolbar mToolbar;
	public static void github(Activity mContext){
		String urlGithub = "https://zrock-application.github.io/ZROCK/";
		Shared.setLink(mContext ,urlGithub);
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
		setTheme(R.style.Theme_ZRock_Application);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zrock_editor);
		mToolbar = (Toolbar)findViewById(R.id.zrock_toolbar);
		setSupportActionBar(mToolbar);
		
    }
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
			String urlGithub = "https://github.com/ZRock-Application/ZRock_Engine/releases";
			Shared.setLink(ManagerActivity.this ,urlGithub);
			return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

