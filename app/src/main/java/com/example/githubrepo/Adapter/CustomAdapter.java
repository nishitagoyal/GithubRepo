package com.example.githubrepo.Adapter;

import android.content.Context;
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

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private Context context;
    private List<GithubRepoDataModel> githubRepoDataModelList;
    public interface DataTransferInterface {
        void onItemClick(int pos);
    }
    DataTransferInterface dataTransferInterface;

    public CustomAdapter(Context context, List<GithubRepoDataModel> githubRepoDataModelList, DataTransferInterface dataTransferInterface) {
        this.context = context;
        this.githubRepoDataModelList = githubRepoDataModelList;
        this.dataTransferInterface = dataTransferInterface;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_row_layout, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder viewHolder, int i) {

        viewHolder.txtName.setText(githubRepoDataModelList.get(i).getName());
        viewHolder.txtAuthor.setText(githubRepoDataModelList.get(i).getAuthor());
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(githubRepoDataModelList.get(i).getAvatar())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(viewHolder.AvatarImage);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataTransferInterface.onItemClick(viewHolder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {

        return githubRepoDataModelList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView txtName;
        TextView txtAuthor;
        ImageView AvatarImage;

        CustomViewHolder(View view) {
            super(view);
            mView = view;
            txtName = mView.findViewById(R.id.repoName);
            txtAuthor = mView.findViewById(R.id.repoAuthor);
            AvatarImage = mView.findViewById(R.id.repoAvatar);
        }
    }

}
