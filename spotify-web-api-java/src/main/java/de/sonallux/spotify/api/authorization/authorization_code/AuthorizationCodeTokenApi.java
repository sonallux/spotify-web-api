package de.sonallux.spotify.api.authorization.authorization_code;

import de.sonallux.spotify.api.authorization.AuthTokens;
import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

interface AuthorizationCodeTokenApi {
    HttpUrl BASE_URL = HttpUrl.get("https://accounts.spotify.com");

    @POST("api/token")
    @FormUrlEncoded
    Call<AuthTokens> getTokensFromAuthorizationCode(
        @Header("Authorization") String authHeaderValue,
        @Field("grant_type") String grantType,
        @Field("code") String code,
        @Field("redirect_uri") String redirectUri);

    @POST("api/token")
    @FormUrlEncoded
    Call<AuthTokens> getTokensFromRefreshToken(
        @Header("Authorization") String authHeaderValue,
        @Field("grant_type") String grantType,
        @Field("refresh_token") String refreshToken);

    @POST("api/token")
    @FormUrlEncoded
    Call<AuthTokens> getTokensFromAuthorizationCodePKCE(
        @Field("client_id") String clientId,
        @Field("grant_type") String grantType,
        @Field("code") String code,
        @Field("redirect_uri") String redirectUri,
        @Field("code_verifier") String codeVerifier);

    @POST("api/token")
    @FormUrlEncoded
    Call<AuthTokens> getTokensFromRefreshTokenPKCE(
        @Field("client_id") String clientId,
        @Field("grant_type") String grantType,
        @Field("refresh_token") String refreshToken);
}
