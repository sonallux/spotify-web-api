package de.sonallux.spotify.api.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpotifyUriTest {
    @Test
    void testArtist() throws SpotifyUriException {
        SpotifyUri spotifyUri = SpotifyUri.parseUri("spotify:artist:3t5xRXzsuZmMDkQzgOX35S");
        assertTrue(spotifyUri.isArtist());
        assertEquals("3t5xRXzsuZmMDkQzgOX35S", spotifyUri.getArtistId());
        assertEquals("spotify:artist:3t5xRXzsuZmMDkQzgOX35S", spotifyUri.toSpotifyUri());
    }

    @Test
    void testAlbum() throws SpotifyUriException {
        SpotifyUri spotifyUri = SpotifyUri.parseUri("spotify:album:6PbItq7wFLcz5pNlvbGH8D");
        assertTrue(spotifyUri.isAlbum());
        assertEquals("6PbItq7wFLcz5pNlvbGH8D", spotifyUri.getAlbumId());
        assertEquals("spotify:album:6PbItq7wFLcz5pNlvbGH8D", spotifyUri.toSpotifyUri());
    }

    @Test
    void testEpisode() throws SpotifyUriException {
        SpotifyUri spotifyUri = SpotifyUri.parseUri("spotify:episode:6PbItq7wFLcz5pNlvbGH8D");
        assertTrue(spotifyUri.isEpisode());
        assertEquals("6PbItq7wFLcz5pNlvbGH8D", spotifyUri.getEpisodeId());
        assertEquals("spotify:episode:6PbItq7wFLcz5pNlvbGH8D", spotifyUri.toSpotifyUri());
    }

    @Test
    void testPlaylist() throws SpotifyUriException {
        SpotifyUri spotifyUri = SpotifyUri.parseUri("spotify:playlist:37i9dQZF1DX4npDJDFDYLg");
        assertTrue(spotifyUri.isPlaylist());
        assertEquals("37i9dQZF1DX4npDJDFDYLg", spotifyUri.getPlaylistId());
        assertEquals("spotify:playlist:37i9dQZF1DX4npDJDFDYLg", spotifyUri.toSpotifyUri());
    }

    @Test
    void testShow() throws SpotifyUriException {
        SpotifyUri spotifyUri = SpotifyUri.parseUri("spotify:show:30t2cItUPY7CvZOup7zptn");
        assertTrue(spotifyUri.isShow());
        assertEquals("30t2cItUPY7CvZOup7zptn", spotifyUri.getShowId());
        assertEquals("spotify:show:30t2cItUPY7CvZOup7zptn", spotifyUri.toSpotifyUri());
    }

    @Test
    void testTrack() throws SpotifyUriException {
        SpotifyUri spotifyUri = SpotifyUri.parseUri("spotify:track:30t2cItUPY7CvZOup7zptn");
        assertTrue(spotifyUri.isTrack());
        assertEquals("30t2cItUPY7CvZOup7zptn", spotifyUri.getTrackId());
        assertEquals("spotify:track:30t2cItUPY7CvZOup7zptn", spotifyUri.toSpotifyUri());
    }

    @Test
    void testUser() throws SpotifyUriException {
        SpotifyUri spotifyUri = SpotifyUri.parseUri("spotify:user:foo123");
        assertTrue(spotifyUri.isUser());
        assertEquals("foo123", spotifyUri.getUserId());
        assertEquals("spotify:user:foo123", spotifyUri.toSpotifyUri());
    }

    @Test
    void testParsePlaylistOldFormat() {
        assertThrows(SpotifyUriException.class,
                () -> SpotifyUri.parseUri("spotify:user:spotify:playlist:37i9dQZF1DX4npDJDFDYLg"));
    }
}
