package com.trs.app.myapplication;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

/**
 * Created by zhuguohui
 * Date: 2022/5/27
 * Time: 10:02
 * Desc:
 */
public class MyFragment extends DialogFragment {

    private int measuredWidth;
    private int measuredHeight;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_dialog, (ViewGroup) getActivity().getWindow().getDecorView(), false);
        measuredWidth = view.getLayoutParams().width;
        measuredHeight = view.getLayoutParams().height;
        view.measure(0,0);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("zzz", "onViewCreated: width="+measuredWidth+" height="+measuredHeight);
        if (getDialog() != null) {
            WindowManager.LayoutParams attributes = getDialog().getWindow().getAttributes();
            attributes.width = measuredWidth;
            attributes.height = measuredHeight;
            attributes.windowAnimations=R.style.dialogWindowAnim;
            attributes.gravity= Gravity.TOP|Gravity.START;
            getDialog().getWindow().setAttributes(attributes);

            getDialog().getWindow().getDecorView().setBackground(new ColorDrawable(Color.BLUE));
            getDialog().getWindow().getDecorView().setPadding(0,0,0,0);

        }
    }
}
