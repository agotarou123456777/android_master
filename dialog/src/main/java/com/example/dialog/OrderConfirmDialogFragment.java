package com.example.dialog;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

public class OrderConfirmDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog_title);
        builder.setMessage(R.string.dialog_msg);

        builder.setPositiveButton(R.string.dialog_btn_ok, new DialogButtonClickListener());
        builder.setNegativeButton(R.string.dialog_btn_ng, new DialogButtonClickListener());
        builder.setNeutralButton(R.string.dialog_btn_nu, new DialogButtonClickListener());

        AlertDialog dialog = builder.create();
        return dialog;
    }

    private class DialogButtonClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            String toast_msg = "";
            if (which == DialogInterface.BUTTON_POSITIVE){
                toast_msg = getString(R.string.dialog_ok_toast);
            } else if (which == DialogInterface.BUTTON_NEGATIVE) {
                toast_msg = getString(R.string.dialog_ng_toast);
            } else if (which == DialogInterface.BUTTON_NEUTRAL) {
                toast_msg = getString(R.string.dialog_nu_toast);
            }
            Toast.makeText(getActivity(),  toast_msg, Toast.LENGTH_LONG).show();
        }
    }



}
