package com.technology.givol;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class ViewDialog3 {
    Context context;

    public ViewDialog3(Context context) {
        // super(context);
        this.context = context;

    }

    public void showDialog(Activity activity, String msg) {

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.customview);

        TextView text = (TextView) dialog.findViewById(R.id.txtValue);
        text.setText(msg);

        Button okButton = (Button) dialog.findViewById(R.id.buttonOk);
        // Button cancleButton = (Button) dialog.findViewById(R.id.btn_dialog_cancle_feedback);


        okButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, CurrentLocationActivity.class);
                context.startActivity(i);

            }
        });
//        cancleButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });

        dialog.show();

    }
}