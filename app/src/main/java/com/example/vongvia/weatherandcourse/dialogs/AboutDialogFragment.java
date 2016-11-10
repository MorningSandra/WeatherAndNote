package com.example.vongvia.weatherandcourse.dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.example.vongvia.weatherandcourse.R;

public class AboutDialogFragment extends DialogFragment {

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();//载入界面
        View view = inflater.inflate(R.layout.dialog_about, null);//将这个布局文件中加载到Activity中来操作。
        builder.setView(view);
        return builder.create();
    }
}
