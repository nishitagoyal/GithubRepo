package com.example.githubrepo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.githubrepo.Model.GithubRepoDataModel;
import com.example.githubrepo.R;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.UserViewHolder> {

    private Context context;
    private List<GithubRepoDataModel.DeveloperDetail> developerDetailList;
    public interface DetailInterface {
        void onItemClick(int pos);
    }
    DetailInterface detailInterface;

//    public DetailAdapter(Context context, List<GithubRepoDataModel.DeveloperDetail> developerDetailList) {
//        this.context = context;
//        this.developerDetailList = developerDetailList;
//    }


    public DetailAdapter(Context context, List<GithubRepoDataModel.DeveloperDetail> developerDetailList, DetailInterface detailInterface) {
        this.context = context;
        this.developerDetailList = developerDetailList;
        this.detailInterface = detailInterface;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.developer_row_layout, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final UserViewHolder viewHolder, int i) {
        viewHolder.tvDevName.setText(developerDetailList.get(i).getUsername());
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(developerDetailList.get(i).getAvatar())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(viewHolder.ivDevAvatar);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailInterface.onItemClick(viewHolder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return developerDetailList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder
    {
        public View mView;
        TextView tvDevName;
        ImageView ivDevAvatar;

        public UserViewHolder(View itemView) {
            super(itemView);
            mView= itemView;
            tvDevName = mView.findViewById(R.id.developerName);
            ivDevAvatar = mView.findViewById(R.id.developerAvatar);
        }
    }
}
