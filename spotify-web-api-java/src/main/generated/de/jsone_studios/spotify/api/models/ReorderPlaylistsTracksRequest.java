package de.jsone_studios.spotify.api.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReorderPlaylistsTracksRequest {
    /**
     * <p>The position of the first item to be reordered.</p>
     */
    private Integer range_start;
    /**
     * <p>The position where the items should be inserted.<br> To reorder the items to the end of the playlist, simply set <em>insert_before</em> to the position after the last item.<br> Examples:<br> To reorder the first item to the last position in a playlist with 10 items, set <em>range_start</em> to 0, and <em>insert_before</em> to 10.<br> To reorder the last item in a playlist with 10 items to the start of the playlist, set <em>range_start</em> to 9, and <em>insert_before</em> to 0.</p>
     */
    private Integer insert_before;
    /**
     * <p>The amount of items to be reordered. Defaults to 1 if not set.<br> The range of items to be reordered begins from the <em>range_start</em> position, and includes the <em>range_length</em> subsequent items.<br> Example:<br> To move the items at index 9-10 to the start of the playlist, <em>range_start</em> is set to 9, and <em>range_length</em> is set to 2.</p>
     */
    private Integer range_length;
    /**
     * <p>The playlist's snapshot ID against which you want to make the changes.</p>
     */
    private String snapshot_id;
}
