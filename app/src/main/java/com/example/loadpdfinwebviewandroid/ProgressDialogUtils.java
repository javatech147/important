package com.example.loadpdfinwebviewandroid;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

public class ProgressDialogUtils {

    public static ProgressDialog showProgressDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Loading");

        //progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // Set the progress dialog background color transparent
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //progressDialog.setIndeterminate(false);

        return progressDialog;
    }
}
