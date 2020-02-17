package zrock.manager;

import zrock.application.engine.graphics.drawable.ImageLoader;

import android.os.AsyncTask;
import android.os.Bundle;

public class WorkerActivity extends StartActivity {
    private static final String IMAGE_URL = "";

    private ImageLoader mImageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mImageLoader = new ImageLoader();
        mImageLoader.execute(IMAGE_URL);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mImageLoader.getStatus() != AsyncTask.Status.FINISHED) {
            mImageLoader.cancel(true);
        }
    }

}
