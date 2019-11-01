package com.example.other;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private ProgressBar progressBar;
    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progress_bar);
        btnStart = findViewById(R.id.btn_start);
        btnStart.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_start) {
            startAsyncTask();
        }
    }

    private void startAsyncTask() {
        ExampleAsyncTask exampleAsyncTask = new ExampleAsyncTask(this);
        exampleAsyncTask.execute(10);
    }

    private static class ExampleAsyncTask extends AsyncTask<Integer, Integer, String> {
        private WeakReference<MainActivity> activityWeakReference;

        ExampleAsyncTask(MainActivity mainActivity) {
            activityWeakReference = new WeakReference<>(mainActivity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            MainActivity mainActivity = activityWeakReference.get();
            if (mainActivity == null || mainActivity.isFinishing()) return;
            mainActivity.progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Integer... integers) {

            for (int i = 0; i < integers[0]; i++) {
                publishProgress((i * 100) / integers[0]);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Completed!!";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            MainActivity mainActivity = activityWeakReference.get();
            if (mainActivity == null || mainActivity.isFinishing()) return;
            mainActivity.progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            MainActivity mainActivity = activityWeakReference.get();
            if (mainActivity == null || mainActivity.isFinishing()) return;

            Toast.makeText(mainActivity, s, Toast.LENGTH_SHORT).show();
            mainActivity.progressBar.setVisibility(View.GONE);
            mainActivity.progressBar.setProgress(0);
        }
    }
}
