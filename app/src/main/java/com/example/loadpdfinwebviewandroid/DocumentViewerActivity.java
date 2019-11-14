package com.example.loadpdfinwebviewandroid;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import static com.example.loadpdfinwebviewandroid.MainActivity.FILE_PATH_KEY;

public class DocumentViewerActivity extends AppCompatActivity {

    private String fileToBeDownload;
    public static final String FILE_PATH = "file_path";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_viewer);

        Intent intent = getIntent();
        if (intent != null) {
            fileToBeDownload = intent.getStringExtra(FILE_PATH_KEY);
        }

        Fragment fragment = new DocumentViewerFragment();
        Bundle bundle = new Bundle();
        bundle.putString(FILE_PATH, fileToBeDownload);
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}
