package de.jsone_studios.spotify.api.authentication;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

interface AuthSpotifyService
{
    @POST("api/token")
    @FormUrlEncoded
    Call<AuthTokens> getAuthTokens(@Header("Authorization") String authHeaderValue, @Field("grant_type") String grantType,
                                   @Field("code") String code, @Field("redirect_uri") String redirectUri);

    @POST("api/token")
    @FormUrlEncoded
    Call<AuthTokens> getAuthTokens(@Header("Authorization") String authHeaderValue, @Field("grant_type") String grantType,
                                   @Field("refresh_token") String refreshToken);
}
