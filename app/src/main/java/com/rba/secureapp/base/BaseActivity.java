package com.rba.secureapp.base;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;

import com.rba.secureapp.R;

/**
 * Created by Ricardo Bravo on 14/12/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private AlertDialogListener alertDialogListener;

    public interface AlertDialogListener {
        void onClickDialog(int code);
    }

    public void setAlertDialogListener(AlertDialogListener alertDialogListener){
        this.alertDialogListener = alertDialogListener;
    }

    public void showMessageDialog(String message, final int code) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
        builder.setCancelable(false);
        builder.setMessage(message);
        String accept = getString(R.string.accept);
        builder.setPositiveButton(accept, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(alertDialogListener!= null){
                    alertDialogListener.onClickDialog(code);
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

}
