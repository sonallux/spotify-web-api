package de.sonallux.spotify.api.apis.browse;

import com.fasterxml.jackson.core.type.TypeReference;
import de.sonallux.spotify.api.http.ApiCall;
import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.http.Request;
import de.sonallux.spotify.api.models.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-recommendations">Get Recommendations request</a>
 *
 * <h3>Response</h3>
 * <p>On success, the HTTP status code in the response header is <code>200 OK</code> and the response body contains a recommendations response object in JSON format.</p>
 */
public class GetRecommendationsRequest {
    private static final TypeReference<Recommendations> RESPONSE_TYPE = new TypeReference<>() {};
    private final ApiClient apiClient;
    private final Request request;

    /**
     * <h3>Get Recommendations request</h3>
     * @param apiClient <p>The API client</p>
     * @param seedArtists <p>A comma separated list of <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify IDs</a> for seed artists. Up to 5 seed values may be provided in any combination of <code>seed_artists</code>, <code>seed_tracks</code> and <code>seed_genres</code>.</p>
     * @param seedGenres <p>A comma separated list of any genres in the set of <a href="#available-genre-seeds">available genre seeds</a>. Up to 5 seed values may be provided in any combination of <code>seed_artists</code>, <code>seed_tracks</code> and <code>seed_genres</code>.</p>
     * @param seedTracks <p>A comma separated list of <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify IDs</a> for a seed track. Up to 5 seed values may be provided in any combination of <code>seed_artists</code>, <code>seed_tracks</code> and <code>seed_genres</code>.</p>
     */
    public GetRecommendationsRequest(ApiClient apiClient, String seedArtists, String seedGenres, String seedTracks) {
        this.apiClient = apiClient;
        this.request = new Request("GET", "/recommendations")
            .addQueryParameter("seed_artists", String.valueOf(seedArtists))
            .addQueryParameter("seed_genres", String.valueOf(seedGenres))
            .addQueryParameter("seed_tracks", String.valueOf(seedTracks))
        ;
    }

    /**
     * <p>The target size of the list of recommended tracks. For seeds with unusually small pools or when highly restrictive filtering is applied, it may be impossible to generate the requested number of recommended tracks. Debugging information for such cases is available in the response. Default: 20. Minimum: 1. Maximum: 100.</p>
     */
    public GetRecommendationsRequest limit(int limit) {
        this.request.addQueryParameter("limit", String.valueOf(limit));
        return this;
    }

    /**
     * <p>An <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country code</a> or the string <code>from_token</code>. Provide this parameter if you want to apply <a href="https://developer.spotify.com/documentation/general/guides/track-relinking-guide">Track Relinking</a>. Because <code>min_*</code>, <code>max_*</code> and <code>target_*</code> are applied to pools before relinking, the generated results may not precisely match the filters applied. Original, non-relinked tracks are available via the <code>linked_from</code> attribute of the <a href="https://developer.spotify.com/documentation/general/guides/track-relinking-guide">relinked track response</a>.</p>
     */
    public GetRecommendationsRequest market(String market) {
        this.request.addQueryParameter("market", String.valueOf(market));
        return this;
    }

    /**
     * <p>For each tunable track attribute, a hard floor on the selected track attribute's value can be provided. See tunable track attributes below for the list of available options. For example, <code>min_tempo=140</code> would restrict results to only those tracks with a tempo of greater than 140 beats per minute.</p>
     */
    public GetRecommendationsRequest minAcousticness(Number minAcousticness) {
        this.request.addQueryParameter("min_acousticness", String.valueOf(minAcousticness));
        return this;
    }

    /**
     * <p>For each tunable track attribute, a hard ceiling on the selected track attribute's value can be provided. See tunable track attributes below for the list of available options. For example, <code>max_instrumentalness=0.35</code> would filter out most tracks that are likely to be instrumental.</p>
     */
    public GetRecommendationsRequest maxAcousticness(Number maxAcousticness) {
        this.request.addQueryParameter("max_acousticness", String.valueOf(maxAcousticness));
        return this;
    }

    /**
     * <p>For each of the tunable track attributes (below) a target value may be provided. Tracks with the attribute values nearest to the target values will be preferred. For example, you might request <code>target_energy=0.6</code> and <code>target_danceability=0.8</code>. All target values will be weighed equally in ranking results.</p>
     */
    public GetRecommendationsRequest targetAcousticness(Number targetAcousticness) {
        this.request.addQueryParameter("target_acousticness", String.valueOf(targetAcousticness));
        return this;
    }

    /**
     * <p>For each tunable track attribute, a hard floor on the selected track attribute's value can be provided. See tunable track attributes below for the list of available options. For example, <code>min_tempo=140</code> would restrict results to only those tracks with a tempo of greater than 140 beats per minute.</p>
     */
    public GetRecommendationsRequest minDanceability(Number minDanceability) {
        this.request.addQueryParameter("min_danceability", String.valueOf(minDanceability));
        return this;
    }

    /**
     * <p>For each tunable track attribute, a hard ceiling on the selected track attribute's value can be provided. See tunable track attributes below for the list of available options. For example, <code>max_instrumentalness=0.35</code> would filter out most tracks that are likely to be instrumental.</p>
     */
    public GetRecommendationsRequest maxDanceability(Number maxDanceability) {
        this.request.addQueryParameter("max_danceability", String.valueOf(maxDanceability));
        return this;
    }

    /**
     * <p>For each of the tunable track attributes (below) a target value may be provided. Tracks with the attribute values nearest to the target values will be preferred. For example, you might request <code>target_energy=0.6</code> and <code>target_danceability=0.8</code>. All target values will be weighed equally in ranking results.</p>
     */
    public GetRecommendationsRequest targetDanceability(Number targetDanceability) {
        this.request.addQueryParameter("target_danceability", String.valueOf(targetDanceability));
        return this;
    }

    /**
     * <p>For each tunable track attribute, a hard floor on the selected track attribute's value can be provided. See tunable track attributes below for the list of available options. For example, <code>min_tempo=140</code> would restrict results to only those tracks with a tempo of greater than 140 beats per minute.</p>
     */
    public GetRecommendationsRequest minDurationMs(int minDurationMs) {
        this.request.addQueryParameter("min_duration_ms", String.valueOf(minDurationMs));
        return this;
    }

    /**
     * <p>For each tunable track attribute, a hard ceiling on the selected track attribute's value can be provided. See tunable track attributes below for the list of available options. For example, <code>max_instrumentalness=0.35</code> would filter out most tracks that are likely to be instrumental.</p>
     */
    public GetRecommendationsRequest maxDurationMs(int maxDurationMs) {
        this.request.addQueryParameter("max_duration_ms", String.valueOf(maxDurationMs));
        return this;
    }

    /**
     * <p>Target duration of the track (ms)</p>
     */
    public GetRecommendationsRequest targetDurationMs(int targetDurationMs) {
        this.request.addQueryParameter("target_duration_ms", String.valueOf(targetDurationMs));
        return this;
    }

    /**
     * <p>For each tunable track attribute, a hard floor on the selected track attribute's value can be provided. See tunable track attributes below for the list of available options. For example, <code>min_tempo=140</code> would restrict results to only those tracks with a tempo of greater than 140 beats per minute.</p>
     */
    public GetRecommendationsRequest minEnergy(Number minEnergy) {
        this.request.addQueryParameter("min_energy", String.valueOf(minEnergy));
        return this;
    }

    /**
     * <p>For each tunable track attribute, a hard ceiling on the selected track attribute's value can be provided. See tunable track attributes below for the list of available options. For example, <code>max_instrumentalness=0.35</code> would filter out most tracks that are likely to be instrumental.</p>
     */
    public GetRecommendationsRequest maxEnergy(Number maxEnergy) {
        this.request.addQueryParameter("max_energy", String.valueOf(maxEnergy));
        return this;
    }

    /**
     * <p>For each of the tunable track attributes (below) a target value may be provided. Tracks with the attribute values nearest to the target values will be preferred. For example, you might request <code>target_energy=0.6</code> and <code>target_danceability=0.8</code>. All target values will be weighed equally in ranking results.</p>
     */
    public GetRecommendationsRequest targetEnergy(Number targetEnergy) {
        this.request.addQueryParameter("target_energy", String.valueOf(targetEnergy));
        return this;
    }

    /**
     * <p>For each tunable track attribute, a hard floor on the selected track attribute's value can be provided. See tunable track attributes below for the list of available options. For example, <code>min_tempo=140</code> would restrict results to only those tracks with a tempo of greater than 140 beats per minute.</p>
     */
    public GetRecommendationsRequest minInstrumentalness(Number minInstrumentalness) {
        this.request.addQueryParameter("min_instrumentalness", String.valueOf(minInstrumentalness));
        return this;
    }

    /**
     * <p>For each tunable track attribute, a hard ceiling on the selected track attribute's value can be provided. See tunable track attributes below for the list of available options. For example, <code>max_instrumentalness=0.35</code> would filter out most tracks that are likely to be instrumental.</p>
     */
    public GetRecommendationsRequest maxInstrumentalness(Number maxInstrumentalness) {
        this.request.addQueryParameter("max_instrumentalness", String.valueOf(maxInstrumentalness));
        return this;
    }

    /**
     * <p>For each of the tunable track attributes (below) a target value may be provided. Tracks with the attribute values nearest to the target values will be preferred. For example, you might request <code>target_energy=0.6</code> and <code>target_danceability=0.8</code>. All target values will be weighed equally in ranking results.</p>
     */
    public GetRecommendationsRequest targetInstrumentalness(Number targetInstrumentalness) {
        this.request.addQueryParameter("target_instrumentalness", String.valueOf(targetInstrumentalness));
        return this;
    }

    /**
     * <p>For each tunable track attribute, a hard floor on the selected track attribute's value can be provided. See tunable track attributes below for the list of available options. For example, <code>min_tempo=140</code> would restrict results to only those tracks with a tempo of greater than 140 beats per minute.</p>
     */
    public GetRecommendationsRequest minKey(int minKey) {
        this.request.addQueryParameter("min_key", String.valueOf(minKey));
        return this;
    }

    /**
     * <p>For each tunable track attribute, a hard ceiling on the selected track attribute's value can be provided. See tunable track attributes below for the list of available options. For example, <code>max_instrumentalness=0.35</code> would filter out most tracks that are likely to be instrumental.</p>
     */
    public GetRecommendationsRequest maxKey(int maxKey) {
        this.request.addQueryParameter("max_key", String.valueOf(maxKey));
        return this;
    }

    /**
     * <p>For each of the tunable track attributes (below) a target value may be provided. Tracks with the attribute values nearest to the target values will be preferred. For example, you might request <code>target_energy=0.6</code> and <code>target_danceability=0.8</code>. All target values will be weighed equally in ranking results.</p>
     */
    public GetRecommendationsRequest targetKey(int targetKey) {
        this.request.addQueryParameter("target_key", String.valueOf(targetKey));
        return this;
    }

    /**
     * <p>For each tunable track attribute, a hard floor on the selected track attribute's value can be provided. See tunable track attributes below for the list of available options. For example, <code>min_tempo=140</code> would restrict results to only those tracks with a tempo of greater than 140 beats per minute.</p>
     */
    public GetRecommendationsRequest minLiveness(Number minLiveness) {
        this.request.addQueryParameter("min_liveness", String.valueOf(minLiveness));
        return this;
    }

    /**
     * <p>For each tunable track attribute, a hard ceiling on the selected track attribute's value can be provided. See tunable track attributes below for the list of available options. For example, <code>max_instrumentalness=0.35</code> would filter out most tracks that are likely to be instrumental.</p>
     */
    public GetRecommendationsRequest maxLiveness(Number maxLiveness) {
        this.request.addQueryParameter("max_liveness", String.valueOf(maxLiveness));
        return this;
    }

    /**
     * <p>For each of the tunable track attributes (below) a target value may be provided. Tracks with the attribute values nearest to the target values will be preferred. For example, you might request <code>target_energy=0.6</code> and <code>target_danceability=0.8</code>. All target values will be weighed equally in ranking results.</p>
     */
    public GetRecommendationsRequest targetLiveness(Number targetLiveness) {
        this.request.addQueryParameter("target_liveness", String.valueOf(targetLiveness));
        return this;
    }

    /**
     * <p>For each tunable track attribute, a hard floor on the selected track attribute's value can be provided. See tunable track attributes below for the list of available options. For example, <code>min_tempo=140</code> would restrict results to only those tracks with a tempo of greater than 140 beats per minute.</p>
     */
    public GetRecommendationsRequest minLoudness(Number minLoudness) {
        this.request.addQueryParameter("min_loudness", String.valueOf(minLoudness));
        return this;
    }

    /**
     * <p>For each tunable track attribute, a hard ceiling on the selected track attribute's value can be provided. See tunable track attributes below for the list of available options. For example, <code>max_instrumentalness=0.35</code> would filter out most tracks that are likely to be instrumental.</p>
     */
    public GetRecommendationsRequest maxLoudness(Number maxLoudness) {
        this.request.addQueryParameter("max_loudness", String.valueOf(maxLoudness));
        return this;
    }

    /**
     * <p>For each of the tunable track attributes (below) a target value may be provided. Tracks with the attribute values nearest to the target values will be preferred. For example, you might request <code>target_energy=0.6</code> and <code>target_danceability=0.8</code>. All target values will be weighed equally in ranking results.</p>
     */
    public GetRecommendationsRequest targetLoudness(Number targetLoudness) {
        this.request.addQueryParameter("target_loudness", String.valueOf(targetLoudness));
        return this;
    }

    /**
     * <p>For each tunable track attribute, a hard floor on the selected track attribute's value can be provided. See tunable track attributes below for the list of available options. For example, <code>min_tempo=140</code> would restrict results to only those tracks with a tempo of greater than 140 beats per minute.</p>
     */
    public GetRecommendationsRequest minMode(int minMode) {
        this.request.addQueryParameter("min_mode", String.valueOf(minMode));
        return this;
    }

    /**
     * <p>For each tunable track attribute, a hard ceiling on the selected track attribute's value can be provided. See tunable track attributes below for the list of available options. For example, <code>max_instrumentalness=0.35</code> would filter out most tracks that are likely to be instrumental.</p>
     */
    public GetRecommendationsRequest maxMode(int maxMode) {
        this.request.addQueryParameter("max_mode", String.valueOf(maxMode));
        return this;
    }

    /**
     * <p>For each of the tunable track attributes (below) a target value may be provided. Tracks with the attribute values nearest to the target values will be preferred. For example, you might request <code>target_energy=0.6</code> and <code>target_danceability=0.8</code>. All target values will be weighed equally in ranking results.</p>
     */
    public GetRecommendationsRequest targetMode(int targetMode) {
        this.request.addQueryParameter("target_mode", String.valueOf(targetMode));
        return this;
    }

    /**
     * <p>For each tunable track attribute, a hard floor on the selected track attribute's value can be provided. See tunable track attributes below for the list of available options. For example, <code>min_tempo=140</code> would restrict results to only those tracks with a tempo of greater than 140 beats per minute.</p>
     */
    public GetRecommendationsRequest minPopularity(int minPopularity) {
        this.request.addQueryParameter("min_popularity", String.valueOf(minPopularity));
        return this;
    }

    /**
     * <p>For each tunable track attribute, a hard ceiling on the selected track attribute's value can be provided. See tunable track attributes below for the list of available options. For example, <code>max_instrumentalness=0.35</code> would filter out most tracks that are likely to be instrumental.</p>
     */
    public GetRecommendationsRequest maxPopularity(int maxPopularity) {
        this.request.addQueryParameter("max_popularity", String.valueOf(maxPopularity));
        return this;
    }

    /**
     * <p>For each of the tunable track attributes (below) a target value may be provided. Tracks with the attribute values nearest to the target values will be preferred. For example, you might request <code>target_energy=0.6</code> and <code>target_danceability=0.8</code>. All target values will be weighed equally in ranking results.</p>
     */
    public GetRecommendationsRequest targetPopularity(int targetPopularity) {
        this.request.addQueryParameter("target_popularity", String.valueOf(targetPopularity));
        return this;
    }

    /**
     * <p>For each tunable track attribute, a hard floor on the selected track attribute's value can be provided. See tunable track attributes below for the list of available options. For example, <code>min_tempo=140</code> would restrict results to only those tracks with a tempo of greater than 140 beats per minute.</p>
     */
    public GetRecommendationsRequest minSpeechiness(Number minSpeechiness) {
        this.request.addQueryParameter("min_speechiness", String.valueOf(minSpeechiness));
        return this;
    }

    /**
     * <p>For each tunable track attribute, a hard ceiling on the selected track attribute's value can be provided. See tunable track attributes below for the list of available options. For example, <code>max_instrumentalness=0.35</code> would filter out most tracks that are likely to be instrumental.</p>
     */
    public GetRecommendationsRequest maxSpeechiness(Number maxSpeechiness) {
        this.request.addQueryParameter("max_speechiness", String.valueOf(maxSpeechiness));
        return this;
    }

    /**
     * <p>For each of the tunable track attributes (below) a target value may be provided. Tracks with the attribute values nearest to the target values will be preferred. For example, you might request <code>target_energy=0.6</code> and <code>target_danceability=0.8</code>. All target values will be weighed equally in ranking results.</p>
     */
    public GetRecommendationsRequest targetSpeechiness(Number targetSpeechiness) {
        this.request.addQueryParameter("target_speechiness", String.valueOf(targetSpeechiness));
        return this;
    }

    /**
     * <p>For each tunable track attribute, a hard floor on the selected track attribute's value can be provided. See tunable track attributes below for the list of available options. For example, <code>min_tempo=140</code> would restrict results to only those tracks with a tempo of greater than 140 beats per minute.</p>
     */
    public GetRecommendationsRequest minTempo(Number minTempo) {
        this.request.addQueryParameter("min_tempo", String.valueOf(minTempo));
        return this;
    }

    /**
     * <p>For each tunable track attribute, a hard ceiling on the selected track attribute's value can be provided. See tunable track attributes below for the list of available options. For example, <code>max_instrumentalness=0.35</code> would filter out most tracks that are likely to be instrumental.</p>
     */
    public GetRecommendationsRequest maxTempo(Number maxTempo) {
        this.request.addQueryParameter("max_tempo", String.valueOf(maxTempo));
        return this;
    }

    /**
     * <p>Target tempo (BPM)</p>
     */
    public GetRecommendationsRequest targetTempo(Number targetTempo) {
        this.request.addQueryParameter("target_tempo", String.valueOf(targetTempo));
        return this;
    }

    /**
     * <p>For each tunable track attribute, a hard floor on the selected track attribute's value can be provided. See tunable track attributes below for the list of available options. For example, <code>min_tempo=140</code> would restrict results to only those tracks with a tempo of greater than 140 beats per minute.</p>
     */
    public GetRecommendationsRequest minTimeSignature(int minTimeSignature) {
        this.request.addQueryParameter("min_time_signature", String.valueOf(minTimeSignature));
        return this;
    }

    /**
     * <p>For each tunable track attribute, a hard ceiling on the selected track attribute's value can be provided. See tunable track attributes below for the list of available options. For example, <code>max_instrumentalness=0.35</code> would filter out most tracks that are likely to be instrumental.</p>
     */
    public GetRecommendationsRequest maxTimeSignature(int maxTimeSignature) {
        this.request.addQueryParameter("max_time_signature", String.valueOf(maxTimeSignature));
        return this;
    }

    /**
     * <p>For each of the tunable track attributes (below) a target value may be provided. Tracks with the attribute values nearest to the target values will be preferred. For example, you might request <code>target_energy=0.6</code> and <code>target_danceability=0.8</code>. All target values will be weighed equally in ranking results.</p>
     */
    public GetRecommendationsRequest targetTimeSignature(int targetTimeSignature) {
        this.request.addQueryParameter("target_time_signature", String.valueOf(targetTimeSignature));
        return this;
    }

    /**
     * <p>For each tunable track attribute, a hard floor on the selected track attribute's value can be provided. See tunable track attributes below for the list of available options. For example, <code>min_tempo=140</code> would restrict results to only those tracks with a tempo of greater than 140 beats per minute.</p>
     */
    public GetRecommendationsRequest minValence(Number minValence) {
        this.request.addQueryParameter("min_valence", String.valueOf(minValence));
        return this;
    }

    /**
     * <p>For each tunable track attribute, a hard ceiling on the selected track attribute's value can be provided. See tunable track attributes below for the list of available options. For example, <code>max_instrumentalness=0.35</code> would filter out most tracks that are likely to be instrumental.</p>
     */
    public GetRecommendationsRequest maxValence(Number maxValence) {
        this.request.addQueryParameter("max_valence", String.valueOf(maxValence));
        return this;
    }

    /**
     * <p>For each of the tunable track attributes (below) a target value may be provided. Tracks with the attribute values nearest to the target values will be preferred. For example, you might request <code>target_energy=0.6</code> and <code>target_danceability=0.8</code>. All target values will be weighed equally in ranking results.</p>
     */
    public GetRecommendationsRequest targetValence(Number targetValence) {
        this.request.addQueryParameter("target_valence", String.valueOf(targetValence));
        return this;
    }

    /**
     * Build the request into an executable call
     */
    public ApiCall<Recommendations> build() {
        return apiClient.createApiCall(request, RESPONSE_TYPE);
    }
}
