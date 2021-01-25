package de.sonallux.spotify.api.authorization.client_credentials;

import de.sonallux.spotify.api.authorization.AuthTokens;
import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

interface AuthorizationTokenApi {
    HttpUrl BASE_URL = HttpUrl.get("https://accounts.spotify.com");

    @POST("api/token")
    @FormUrlEncoded
    Call<AuthTokens> getAuthTokens(
        @Header("Authorization") String authHeaderValue,
        @Field("grant_type") String grantType);
}
