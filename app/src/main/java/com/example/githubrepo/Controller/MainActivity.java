package com.example.githubrepo.Controller;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.githubrepo.Adapter.CustomAdapter;
import com.example.githubrepo.Model.GithubRepoDataModel;
import com.example.githubrepo.Network.GetDataService;
import com.example.githubrepo.Network.RetrofitClient;
import com.example.githubrepo.R;
import com.victor.loading.book.BookLoading;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{

    RecyclerView recyclerView;
    private CustomAdapter customAdapter;
    private GithubRepoDataModel githubRepoDataModel;
    ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        swipeRefreshLayout=findViewById(R.id.swipeContainer);
        loadJSON();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadJSON();
                Toast.makeText(MainActivity.this,"Github Repo Refreshed :)",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void initView(final List<GithubRepoDataModel> githubRepoDataModelList)
    {
        recyclerView = findViewById(R.id.recyclerView);
        customAdapter = new CustomAdapter(this, githubRepoDataModelList, new CustomAdapter.DataTransferInterface() {
            @Override
            public void onItemClick(int pos) {
                GithubRepoDataModel dataModel = githubRepoDataModelList.get(pos);
                String name = dataModel.getName();
                String description = dataModel.getDescription();
                String avatar = dataModel.getAvatar();
                String url  = dataModel.getUrl();
                String author = dataModel.getAuthor();
                int stars = dataModel.getStars();
                int forks = dataModel.getForks();
                List<GithubRepoDataModel.DeveloperDetail> list = dataModel.getDeveloperDetailList();
                Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                intent.putExtra("Name",name);
                intent.putExtra("Author",author);
                intent.putExtra("Avatar",avatar);
                intent.putExtra("Stars",stars);
                intent.putExtra("Forks",forks);
                intent.putExtra("Url",url);
                intent.putExtra("Description",description);
                intent.putExtra("DeveloperDetailList", (Serializable) list);
                startActivity(intent);
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(customAdapter);
        swipeRefreshLayout.setRefreshing(false);
    }
    private void loadJSON()
    {
        RetrofitClient retrofitClient = new RetrofitClient();
        GetDataService getDataService = retrofitClient.getRetrofitInstance().create(GetDataService.class);
        Call<List<GithubRepoDataModel>> call = getDataService.getResponse();
        call.enqueue(new Callback<List<GithubRepoDataModel>>() {
            @Override
            public void onResponse(Call<List<GithubRepoDataModel>> call, Response<List<GithubRepoDataModel>> response) {
                initView(response.body());
                progressBar.setVisibility(View.GONE);
            }
            @Override
            public void onFailure(Call<List<GithubRepoDataModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
