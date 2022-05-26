package com.trs.app.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by zhuguohui
 * Date: 2022/5/26
 * Time: 9:56
 * Desc:
 */
public class TopTipUtil {

    private static Dialog dialog;
    private static TextView tv_title;
    static Handler handler;
    private static boolean isShow=false;

    public static void showInfo(Context context, String info) {
        initDialog(context);
        tv_title.setText(info);
        Log.i("zzz", "showInfo: dialog ("+dialog+") isShowing  is " +isShow);
      /*  if (!isShow) {
            dialog.show();
            isShow=true;
        }*/
        dialog.show();
        dismissAuto();
    }

    private static void dismissAuto() {
        handler.removeCallbacks(dismissRunnable);
        handler.postDelayed(dismissRunnable, 3000);
    }

    private static Runnable dismissRunnable = new Runnable() {
        @Override
        public void run() {
            if (dialog != null && dialog.isShowing()) {
                dialog.hide();
                isShow=false;
            }
        }
    };

    private static void initDialog(Context context) {
        if (dialog != null) {
            return;
        }
        handler = new Handler();
        dialog = new Dialog(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.layout_dialog, null);
        tv_title = dialogView.findViewById(R.id.tv_title);
        dialog.setContentView(dialogView);
        dialog.getWindow().getDecorView().setBackgroundColor(Color.RED);
        dialog.getWindow().getDecorView().setPadding(0, 0, 0, 0);
        dialog.setCanceledOnTouchOutside(false);
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = 350;
        params.flags = WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
        |WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
        ;


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            params.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
        dialog.getWindow().setGravity(Gravity.TOP);
        dialog.getWindow().setAttributes(params);
        dialog.getWindow().setWindowAnimations(R.style.dialogWindowAnim);
        dialog.getWindow().setDimAmount(0);


        dialog.show();
    }
}
