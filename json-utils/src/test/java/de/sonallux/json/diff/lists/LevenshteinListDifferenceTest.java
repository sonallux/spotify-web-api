package de.sonallux.json.diff.lists;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LevenshteinListDifferenceTest {

    @Test
    void test1() {
        var left = List.of(1,2,3,4,5,6);
        var right = List.of(2,3,0,5,6,7);

        var valueChanges = new LevenshteinListDifference<Integer>().listDifferences(left, right);

        assertThat(valueChanges).containsExactly(
                new ItemRemoved<>(0, 1),
                new ItemChanged<>(3, 4, 0),
                new ItemAdded<>(5, 7)
        );
    }

    @Test
    void test2() {
        var left = List.of(1,2,3);
        var right = List.of(2,3,4);

        var valueChanges = new LevenshteinListDifference<Integer>().listDifferences(left, right);

        assertThat(valueChanges).containsExactly(
                new ItemRemoved<>(0, 1),
                new ItemAdded<>(2, 4)
        );
    }

    @Test
    void test3() {
        var left = List.of(1,1,2,2);
        var right = List.of(0,1,1,2);

        var valueChanges = new LevenshteinListDifference<Integer>().listDifferences(left, right);

        assertThat(valueChanges).containsExactly(
                new ItemAdded<>(0, 0),
                new ItemRemoved<>(3, 2)
        );
    }

    @Test
    void test4() {
        var left = List.of(1,1,1);
        var right = List.of(1,1,2,1);

        var valueChanges = new LevenshteinListDifference<Integer>().listDifferences(left, right);

        assertThat(valueChanges).containsExactly(
                new ItemAdded<>(2, 2)
        );
    }

    @Test
    void test5() {
        var left = List.of(1,1,2,1);
        var right = List.of(1,1,1);

        var valueChanges = new LevenshteinListDifference<Integer>().listDifferences(left, right);

        assertThat(valueChanges).containsExactly(
                new ItemRemoved<>(2, 2)
        );
    }

    @Test
    void test6() {
        var left = List.of(1,2);
        var right = List.of(2,3);

        var valueChanges = new LevenshteinListDifference<Integer>().listDifferences(left, right);

        assertThat(valueChanges).containsExactly(
                new ItemChanged<>(0, 1, 2),
                new ItemChanged<>(1, 2, 3)
        );
    }

    @Test
    void test6WithDifferentPenalty() {
        var left = List.of(1,2);
        var right = List.of(2,3);

        var valueChanges = new LevenshteinListDifference<Integer>(2).listDifferences(left, right);

        assertThat(valueChanges).containsExactly(
                new ItemRemoved<>(0, 1),
                new ItemAdded<>(1, 3)
        );
    }

    @Test
    void test7() {
        var left = List.of(2,3);
        var right = List.of(1,2);

        var valueChanges = new LevenshteinListDifference<Integer>().listDifferences(left, right);

        assertThat(valueChanges).containsExactly(
                new ItemChanged<>(0, 2, 1),
                new ItemChanged<>(1, 3, 2)
        );
    }

    @Test
    void test7WithDifferentPenalty() {
        var left = List.of(2,3);
        var right = List.of(1,2);

        var valueChanges = new LevenshteinListDifference<Integer>(2).listDifferences(left, right);

        assertThat(valueChanges).containsExactly(
                new ItemAdded<>(0, 1),
                new ItemRemoved<>(1, 3)
        );
    }
}
