package de.sonallux.json.diff.lists;

import lombok.Value;

@Value
public class ItemRemoved<T> implements ListItemChange<T> {
    int index;
    T item;
}
