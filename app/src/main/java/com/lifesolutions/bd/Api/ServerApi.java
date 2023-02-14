package com.lifesolutions.bd.Api;


import com.lifesolutions.bd.Models.AgoraAccessToken;
import com.lifesolutions.bd.Models.AgoraSendUserDetails;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServerApi {

    @POST("url-encoder")
    Call<UrlType> urlEncode(@Body UrlType url);

    @POST("remove")
    Call<ResType> removeImage(@Body ResType fileUrl);

    @POST("get-all-courses")
    Call<GetCourses> getAllCourses();

    @POST("get-token-from-channel")
    Call<AgoraAccessToken> postAccess(@Body AgoraSendUserDetails dataModal);

}
