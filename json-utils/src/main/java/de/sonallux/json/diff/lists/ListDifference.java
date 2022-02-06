package de.sonallux.json.diff.lists;

import java.util.List;

public interface ListDifference<T> {
    List<ListItemChange<T>> listDifferences(List<T> leftList, List<T> rightList);
}
