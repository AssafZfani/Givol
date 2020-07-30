package com.technology.givol;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

public class ViewDialog1 {
    Context context;

    public ViewDialog1(Context context) {
        // super(context);
        this.context = context;

    }

    public static void alertDialogShow(final Context context, String message) {
        final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setMessage(message);
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(context, MainActivity.class);
                context.startActivity(i);
            }
        });
        alertDialog.show();
    }
}