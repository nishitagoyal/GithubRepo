package com.example.githubrepo.Network;

import com.example.githubrepo.Model.GithubRepoDataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("repositories")
    Call<List<GithubRepoDataModel>> getResponse();
}
