package de.sonallux.json.diff.lists;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class LevenshteinListDifference<T> implements ListDifference<T> {
    private final int penalty;

    public LevenshteinListDifference(int penalty) {
        this.penalty = penalty;
    }

    public LevenshteinListDifference() {
        this(1);
    }

    @Override
    public List<ListItemChange<T>> listDifferences(List<T> leftList, List<T> rightList) {
        var scores = backtrack(leftList, rightList);
        return convertToChanges(scores, leftList, rightList);
    }

    private int[][] backtrack(List<T> leftList, List<T> rightList) {
        var leftDim = leftList.size();
        var rightDim = rightList.size();

        var scores = initScores(leftDim + 1, rightDim + 1);

        for (var i = 0; i < leftDim; ++i) {
            for (var j = 0; j < rightDim; ++j) {
                int left = scores[i][j + 1] + 1;
                int right = scores[i + 1][j] + 1;
                int current = scores[i][j];

                if (!Objects.equals(leftList.get(i), rightList.get(j))) {
                    current += penalty;
                }

                scores[i + 1][j + 1] = Math.min(current, Math.min(left, right));
            }
        }
        return scores;
    }

    private int[][] initScores(int leftDim, int rightDim) {
        var scores = new int[leftDim][rightDim];
        for (var i = 0; i < leftDim; i++) {
            scores[i][0] = i;
        }

        for (var j = 0; j < rightDim; j++) {
            scores[0][j] = j;
        }
        return scores;
    }

    private List<ListItemChange<T>> convertToChanges(int[][] scores, List<T> leftList, List<T> rightList) {
        var i = leftList.size() - 1;
        var j = rightList.size() - 1;

        var changes = new LinkedList<ListItemChange<T>>();

        while (i >= 0 && j >= 0) {
            var scoreTake = scores[i][j];
            var scoreLeft = scores[i][j + 1];
            var scoreRight = scores[i + 1][j];

            var leftValue = leftList.get(i);
            var rightValue = rightList.get(j);

            if (scoreTake <= scoreLeft && scoreTake <= scoreRight) {
                if (!Objects.equals(leftValue, rightValue)) {
                    changes.addFirst(new ItemChanged<>(i, leftValue, rightValue));
                }
                i--;
                j--;
            } else if (scoreLeft < scoreRight) {
                changes.addFirst(new ItemRemoved<>(i, leftValue));
                i--;
            } else {
                changes.addFirst(new ItemAdded<>(j, rightValue));
                j--;

            }
        }

        for (;i >= 0; i--) {
            changes.addFirst(new ItemRemoved<>(i, leftList.get(i)));
        }

        for (;j >= 0; j--) {
            changes.addFirst(new ItemAdded<>(j, rightList.get(j)));
        }

        return changes;
    }
}
