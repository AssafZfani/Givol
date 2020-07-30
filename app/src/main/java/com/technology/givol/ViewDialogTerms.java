package com.technology.givol;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class ViewDialogTerms {
    public void showDialog(Activity activity, String val, String msg) {

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.customview_1);
        TextView txtValue = (TextView) dialog.findViewById(R.id.txtValue);

        TextView text = (TextView) dialog.findViewById(R.id.terms_value);
        if (msg.equals("null")) {
            txtValue.setText(val);
            text.setText("No Data");
        } else if (!msg.isEmpty()) {
            txtValue.setText(val);
            text.setText(msg);
        }
        Button okButton = (Button) dialog.findViewById(R.id.buttonOk);
        // Button cancleButton = (Button) dialog.findViewById(R.id.btn_dialog_cancle_feedback);


        okButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
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