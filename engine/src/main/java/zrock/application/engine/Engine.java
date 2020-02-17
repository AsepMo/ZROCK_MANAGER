package zrock.application.engine;

import android.support.v7.app.AlertDialog;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Environment;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.concurrent.CountDownLatch;

public class Engine
{
	public static final int ALERT_DIALOG2 = 2;
    public static boolean doubleBackToExitPressedOnce = false;
    public static boolean isClicked;
	public static class Wrapper {
        public String info;
    }

    public static class start extends AsyncTask<String, Void, Wrapper> {

		private Context mContext;
        private View rootView;
		ProgressDialog progress = null;
        
        public start(Context context, View rootView) {
            this.mContext = context;
            this.rootView = rootView;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            progress = new ProgressDialog(mContext);
			progress.setCancelable(false);
			//progress.setMessage("Script Executing...\n\nPlease wait!");
			progress.setMessage("START CONTENT");
			progress.show();
        }

        @Override
        public Wrapper doInBackground(String... args) {
            final Wrapper w1 = new Wrapper();

            publishProgress(new Void[]{});

            final CountDownLatch latch3 = new CountDownLatch(1);

            try {
                Process su = Runtime.getRuntime().exec("su");
                DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());
                outputStream.writeBytes("/data/data/zrock.application/files/script.sh\n");
                outputStream.writeBytes("exit\n");
                outputStream.flush();
                BufferedReader reader = new BufferedReader(new InputStreamReader(su.getInputStream()));
                int read;
                char[] buffer = new char[4096];
                StringBuffer output = new StringBuffer();
                while ((read = reader.read(buffer)) > 0) {
                    output.append(buffer, 0, read);
                }
                reader.close();

                w1.info = output.toString().trim();

                su.waitFor();

                latch3.countDown();

            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            return w1;
        }


        @Override
        public void onPostExecute(final Wrapper w1) {
            progress.dismiss();

            	AlertDialog alertDialog = new AlertDialog.Builder(mContext, R.style.Theme_AppCompat_Dialog_Alert).create();
						alertDialog.setMessage("Result of the executing:\n" + w1.info);
						alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
						alertDialog.show();
					};
        
    }
}
