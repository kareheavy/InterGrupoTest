package com.jhonjimenez.intergrupotest.utils;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.jhonjimenez.intergrupotest.R;

public class Messages {

    public static void showCustomToast(Activity activity, int status, String message) {

        LayoutInflater inflater = activity.getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) activity.findViewById(R.id.custom_toast_container));

        TextView text = layout.findViewById(R.id.textview);
        ImageView imageView = layout.findViewById(R.id.imageView);
        LinearLayout linear = layout.findViewById(R.id.linenarLayout);

        if (status == Constants.STATUS_ERROR) {
            imageView.setImageResource(R.drawable.ic_error_white_24dp);
            linear.setBackgroundResource(R.drawable.custom_toast_border_error);
        } else {
            imageView.setImageResource(R.drawable.ic_insert_emoticon_white_24dp);
            linear.setBackgroundResource(R.drawable.custom_toast_border_ok);
        }

        text.setText(message);

        Toast toast = new Toast(activity);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}
