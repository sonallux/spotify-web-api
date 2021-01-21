package de.sonallux.spotify.api.authorization;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthTokens {
    @JsonProperty("access_token")
    public String accessToken;

    @JsonProperty("token_type")
    public String tokenType;

    public String scope;

    @JsonProperty("expires_in")
    public int expiresIn;

    @JsonProperty("refresh_token")
    public String refreshToken;
}
