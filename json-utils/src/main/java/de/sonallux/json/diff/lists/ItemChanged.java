package de.sonallux.json.diff.lists;

import lombok.Value;

@Value
public class ItemChanged<T> implements ListItemChange<T> {
    int index;
    T left;
    T right;
}
