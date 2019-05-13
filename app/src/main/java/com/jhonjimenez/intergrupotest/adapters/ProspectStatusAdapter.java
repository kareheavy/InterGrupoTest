package com.jhonjimenez.intergrupotest.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.jhonjimenez.intergrupotest.R;
import com.jhonjimenez.intergrupotest.utils.Constants;
import com.jhonjimenez.intergrupotest.utils.Status;

import java.util.List;

public class ProspectStatusAdapter extends ArrayAdapter<Status> {

    private Context context;
    private List<Status> statuses;

    public ProspectStatusAdapter(@NonNull Context context, List<Status> statuses) {
        super(context, R.layout.item_spinner_status, statuses);
        this.context = context;
        this.statuses = statuses;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_spinner_status, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textViewDescripcion = convertView.findViewById(R.id.textview_descripcion);
            viewHolder.imageViewStatus = convertView.findViewById(R.id.imageview_status);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Status objectStatus = statuses.get(position);
        viewHolder.textViewDescripcion.setText(objectStatus.getDescription());

        switch (objectStatus.getId()){
            case Constants.PENDING :
                viewHolder.imageViewStatus.setImageResource(R.drawable.ic_access_time_orange_24dp);
                break;
            case Constants.APPROVED :
                viewHolder.imageViewStatus.setImageResource(R.drawable.ic_check_circle_green_24dp);
                break;
            case Constants.ACCEPTED :
                viewHolder.imageViewStatus.setImageResource(R.drawable.ic_done_all_green_24dp);
                break;
            case Constants.REJECTED :
                viewHolder.imageViewStatus.setImageResource(R.drawable.ic_cancel_red_24dp);
                break;
            case Constants.DISABLED :
                viewHolder.imageViewStatus.setImageResource(R.drawable.ic_do_not_disturb_gray_24dp);
                break;
        }

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_spinner_status, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textViewDescripcion = convertView.findViewById(R.id.textview_descripcion);
            viewHolder.imageViewStatus = convertView.findViewById(R.id.imageview_status);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Status objectStatus = statuses.get(position);
        viewHolder.textViewDescripcion.setText(objectStatus.getDescription());

        switch (objectStatus.getId()){
            case Constants.PENDING :
                viewHolder.imageViewStatus.setImageResource(R.drawable.ic_access_time_orange_24dp);
                break;
            case Constants.APPROVED :
                viewHolder.imageViewStatus.setImageResource(R.drawable.ic_check_circle_green_24dp);
                break;
            case Constants.ACCEPTED :
                viewHolder.imageViewStatus.setImageResource(R.drawable.ic_done_all_green_24dp);
                break;
            case Constants.REJECTED :
                viewHolder.imageViewStatus.setImageResource(R.drawable.ic_cancel_red_24dp);
                break;
            case Constants.DISABLED :
                viewHolder.imageViewStatus.setImageResource(R.drawable.ic_do_not_disturb_gray_24dp);
                break;
        }

        return convertView;
    }

    static class ViewHolder {

        TextView textViewDescripcion;
        ImageView imageViewStatus;
    }

}
