package com.example.loadpdfinwebviewandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String filePath = "http://www.adobe.com/devnet/acrobat/pdfs/pdf_open_parameters.pdf";
    public static final String FILE_PATH_KEY = "file_path_key";
    public static final String GOOGLE_DRIVE_DOCUMENT_VIEWER_BASE_URL = "http://drive.google.com/viewerng/viewer?embedded=true&url=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLoadDocument = findViewById(R.id.btn_load_pdf);
        btnLoadDocument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent documentIntent = new Intent(MainActivity.this, DocumentViewerActivity.class);
                documentIntent.putExtra(FILE_PATH_KEY, GOOGLE_DRIVE_DOCUMENT_VIEWER_BASE_URL + filePath);
                startActivity(documentIntent);
            }
        });
    }
}
