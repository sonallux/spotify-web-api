package de.sonallux.spotify.api.authorization;

public interface ApiAuthorizationProvider {
    /**
     * Get the value for the <code>Authorization</code> header that should be added to a request. This method
     * will be called for every request, so always the latest authentication token can be used.
     * If no <code>Authorization</code> header should be added to the request <code>null</code> can be returned.
     * @return the value of the <code>Authorization</code> header or <code>null</code>
     */
    String getAuthorizationHeaderValue();

    /**
     * Callback to initiate a token refresh after a 401 Unauthorized response from the Spotify Web API.
     * If this operation is not supported or failed, return <code>false</code>.
     * A return value of <code>true</code> indicates that the failing request should be retried.
     * The {@link ApiAuthorizationProvider#getAuthorizationHeaderValue()} method will be called again
     * to get the new header with the new token
     * @return <code>true</code> to indicate to successful token refresh, otherwise <code>false</code>
     */
    boolean refreshAccessToken();
}
