package com.jhonjimenez.intergrupotest.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.jhonjimenez.intergrupotest.R;
import com.jhonjimenez.intergrupotest.models.Prospect;
import com.jhonjimenez.intergrupotest.utils.Constants;

import java.util.List;

public class ProspectAdapter extends RecyclerView.Adapter<ProspectAdapter.ViewHolder> {

    private List<Prospect> prospects;
    private IProspectAdapter listener;

    public void setData(List<Prospect> prospects) {
        this.prospects = prospects;
        notifyDataSetChanged();
    }

    public interface IProspectAdapter{

        void editProspect(Prospect prospect);

    }

    public ProspectAdapter(List<Prospect> prospects, IProspectAdapter listener) {
        this.prospects = prospects;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_prospect, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Prospect objectProspect = prospects.get(position);

        holder.textViewFullName.setText(objectProspect.getName().toUpperCase() + " " + objectProspect.getSurname().toUpperCase());
        holder.textViewDocument.setText("cc. " + objectProspect.getSchProspectIdentification());
        holder.textViewPhone.setText("Teléfono: " + objectProspect.getTelephone());

        switch (objectProspect.getStatusCd()){
            case Constants.PENDING :
                holder.imageViewStatus.setImageResource(R.drawable.ic_access_time_orange_24dp);
                break;
            case Constants.APPROVED :
                holder.imageViewStatus.setImageResource(R.drawable.ic_check_circle_green_24dp);
                break;
            case Constants.ACCEPTED :
                holder.imageViewStatus.setImageResource(R.drawable.ic_done_all_green_24dp);
                break;
            case Constants.REJECTED :
                holder.imageViewStatus.setImageResource(R.drawable.ic_cancel_red_24dp);
                break;
            case Constants.DISABLED :
                holder.imageViewStatus.setImageResource(R.drawable.ic_do_not_disturb_gray_24dp);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return prospects == null ? 0 : prospects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageview_status)
        ImageView imageViewStatus;
        @BindView(R.id.textview_fullname)
        TextView textViewFullName;
        @BindView(R.id.textview_document)
        TextView textViewDocument;
        @BindView(R.id.textview_phone)
        TextView textViewPhone;
        @BindView(R.id.button_edit)
        Button buttonEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.button_edit)
        public void editProspect(){
            if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                listener.editProspect(prospects.get(getAdapterPosition()));
            }
        }

    }
}
