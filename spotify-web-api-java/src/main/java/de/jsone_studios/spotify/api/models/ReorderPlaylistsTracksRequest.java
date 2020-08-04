package de.jsone_studios.spotify.api.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ReorderPlaylistsTracksRequest {
    /**
     * The position of the first item to be reordered.
     */
    @NonNull
    private Integer range_start;
    /**
     * The position where the items should be inserted. To reorder the items to the end of the playlist, simply set insert_before to the position after the last item. Examples: To reorder the first item to the last position in a playlist with 10 items, set range_start to 0, and insert_before to 10. To reorder the last item in a playlist with 10 items to the start of the playlist, set range_start to 9, and insert_before to 0.
     */
    @NonNull
    private Integer insert_before;
    /**
     * The amount of items to be reordered. Defaults to 1 if not set. The range of items to be reordered begins from the range_start position, and includes the range_length subsequent items. Example: To move the items at index 9-10 to the start of the playlist, range_start is set to 9, and range_length is set to 2.
     */
    private Integer range_length;
    /**
     * The playlist’s snapshot ID against which you want to make the changes.
     */
    private String snapshot_id;
}