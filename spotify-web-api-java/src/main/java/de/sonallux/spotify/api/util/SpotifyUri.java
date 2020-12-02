package de.sonallux.spotify.api.util;

public class SpotifyUri
{
    private String artistsId;
    private String albumId;
    private String trackId;
    private String playlistId;
    private String userId;

    public boolean isArtist()
    {
        return artistsId != null;
    }

    public String getArtistId() throws SpotifyUriException
    {
        if (artistsId != null)
        {
            return artistsId;
        }
        throw new SpotifyUriException("SpotifyUri does not contain an artist id");
    }

    public boolean isAlbum()
    {
        return albumId != null;
    }

    public String getAlbumId() throws SpotifyUriException
    {
        if (albumId != null)
        {
            return albumId;
        }
        throw new SpotifyUriException("SpotifyUri does not contain an album id");
    }

    public boolean isTrack()
    {
        return trackId != null;
    }

    public String getTrackId() throws SpotifyUriException
    {
        if (trackId != null)
        {
            return trackId;
        }
        throw new SpotifyUriException("SpotifyUri does not contain a track id");
    }

    public boolean isPlaylist()
    {
        return playlistId != null;
    }

    public String getPlaylistId() throws SpotifyUriException
    {
        if (playlistId != null)
        {
            return playlistId;
        }
        throw new SpotifyUriException("SpotifyUri does not contain a playlist id");
    }

    public boolean isUser()
    {
        return userId != null;
    }

    public String getUserId() throws SpotifyUriException
    {
        if (userId != null)
        {
            return userId;
        }
        throw new SpotifyUriException("SpotifyUri does not contain a user id");
    }

    public String toSpotifyUri() throws SpotifyUriException
    {
        if (artistsId != null)
        {
            return "spotify:artist:" + artistsId;
        }
        else if (albumId != null)
        {
            return "spotify:album:" + albumId;
        }
        else if (trackId != null)
        {
            return "spotify:track:" + trackId;
        }
        else if (playlistId != null)
        {
            return "spotify:playlist:" + playlistId;
        }
        else if (userId != null)
        {
            return "spotify:user:" + userId;
        }
        throw new SpotifyUriException("Failed to generate spotify uri");
    }

    @Override
    public String toString()
    {
        try
        {
            return toSpotifyUri();
        }
        catch (SpotifyUriException e)
        {
            return super.toString();
        }
    }

    public static SpotifyUri parseUri(String string) throws SpotifyUriException
    {
        if (string == null || string.length() == 0)
        {
            throw new SpotifyUriException("Can not parse empty spotifyUri");
        }

        String[] parts = string.split(":");
        if (parts.length != 3)
        {
            throw new SpotifyUriException("SpotifyUri has wrong format: " + string);
        }
        if (!"spotify".equals(parts[0]))
        {
            throw new SpotifyUriException("SpotifyUri must start with 'spotify'");
        }
        SpotifyUri spotifyUri = new SpotifyUri();
        if ("artist".equals(parts[1]))
        {
            spotifyUri.artistsId = parts[2];
        }
        else if ("album".equals(parts[1]))
        {
            spotifyUri.albumId = parts[2];
        }
        else if ("track".equals(parts[1]))
        {
            spotifyUri.trackId = parts[2];
        }
        else if ("user".equals(parts[1]))
        {
            spotifyUri.userId = parts[2];

        }
        else if ("playlist".equals(parts[1]))
        {
            spotifyUri.playlistId = parts[2];
        }
        else
        {
            throw new SpotifyUriException("Unknown field: " + parts[1]);
        }

        return spotifyUri;
    }
}
