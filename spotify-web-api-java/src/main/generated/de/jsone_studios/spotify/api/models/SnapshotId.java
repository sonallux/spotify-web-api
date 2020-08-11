package de.jsone_studios.spotify.api.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class SnapshotId {
    /**
     * The snapshot_id can be used to identify your playlist version in future requests.
     */
    private String snapshot_id;
}