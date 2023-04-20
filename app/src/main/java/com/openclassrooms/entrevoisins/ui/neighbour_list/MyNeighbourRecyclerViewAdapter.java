package com.openclassrooms.entrevoisins.ui.neighbour_list;

import static androidx.core.content.ContextCompat.startActivity;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.IntentFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyNeighbourRecyclerViewAdapter.ViewHolder> {

    private final List<Neighbour> mNeighbours;
    private final RecyclerViewInterface mRecyclerViewInterface;

    public MyNeighbourRecyclerViewAdapter(List<Neighbour> items, RecyclerViewInterface mRecyclerViewInterface) {
        mNeighbours = items;
        this.mRecyclerViewInterface = mRecyclerViewInterface;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemview_neighbour, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Neighbour neighbour = mNeighbours.get(position);
        holder.mNeighbourName.setText(neighbour.getName());
        Glide.with(holder.mNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mNeighbourAvatar);

        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteNeighbourEvent(neighbour));
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getAdapterPosition();
                mRecyclerViewInterface.onItemClick(pos);
                /*if (mRecyclerViewInterface != null){
                    int pos = holder.getAdapterPosition();

                    if (pos != RecyclerView.NO_POSITION){
                        mRecyclerViewInterface.onItemClick(pos);
                    }

                }*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNeighbours.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_avatar)
        public ImageView mNeighbourAvatar;
        @BindView(R.id.item_list_name)
        public TextView mNeighbourName;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            /*Mon implémentation pour lancer DisplayNeighbourActivity aprés un clic sur un des neighbours

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mRecyclerViewInterface != null){
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION){
                            mRecyclerViewInterface.onItemClick(pos);
                        }

                    }
                }
            });*/


        }
    }
}
