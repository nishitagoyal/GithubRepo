package com.example.githubrepo.Controller;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.githubrepo.Adapter.DetailAdapter;
import com.example.githubrepo.Model.GithubRepoDataModel;
import com.example.githubrepo.R;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    TextView tvName;
    TextView tvAuthor;
    ImageView ivAvatar;
    TextView tvStars;
    TextView tvForks;
    TextView tvDescription;
    TextView tvUrl;
    List<GithubRepoDataModel.DeveloperDetail> mylist;
    RecyclerView recyclerViewDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        findViews();
        populateValues();
        settingRecyclerView();


    }
    private void findViews()
    {
        tvName = findViewById(R.id.Name);
        tvAuthor=findViewById(R.id.author);
        ivAvatar = findViewById(R.id.avatar);
        tvForks = findViewById(R.id.fork);
        tvStars = findViewById(R.id.star);
        tvDescription = findViewById(R.id.description);
        tvUrl = findViewById(R.id.url);
    }

    private void populateValues()
    {
        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");
        String author = intent.getStringExtra("Author");
        String avatar = intent.getStringExtra("Avatar");
        String url = intent.getStringExtra("Url");
        String description = intent.getStringExtra("Description");
        String stars = String.valueOf(intent.getIntExtra("Stars",1));
        String forks = String.valueOf(intent.getIntExtra("Forks",1));
        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttp3Downloader(this));
        builder.build().load(avatar)
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(ivAvatar);
        mylist = (List<GithubRepoDataModel.DeveloperDetail>) intent.getSerializableExtra("DeveloperDetailList");

        tvAuthor.setText(author);
        tvName.setText(name);
        tvStars.setText(stars);
        tvForks.setText(forks);
        tvDescription.setText(description);
        tvUrl.setText(url);

    }
    private void settingRecyclerView()
    {
        recyclerViewDetail = findViewById(R.id.recyclerViewDetail);
        DetailAdapter detailAdapter = new DetailAdapter(this, mylist, new DetailAdapter.DetailInterface() {
            @Override
            public void onItemClick(int pos) {
                String url = mylist.get(pos).getHref();
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DetailActivity.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewDetail.setLayoutManager(layoutManager);
        recyclerViewDetail.setAdapter(detailAdapter);
    }
}
