package ru.job4j.pools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParallelSearchTest {

    @Test
    public void testBigArray() {
        Integer[] array = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23};
        int target = 15;
        int result = ParallelSearch.search(array, target);
        assertEquals(7, result);
    }

    @Test
    public void testStringArray() {
        String[] array = {"apple", "banana", "cherry", "date", "fig", "grape"};
        String target = "cherry";
        int result = ParallelSearch.search(array, target);
        assertEquals(2, result);
    }

    @Test
    public void testSmallArray() {
        Integer[] array = {1, 3, 5};
        int target = 5;
        int result = ParallelSearch.search(array, target);
        assertEquals(2, result);
    }

    @Test
    public void testElementNotFound() {
        Integer[] array = {1, 3, 5, 7, 9};
        int target = 2;
        int result = ParallelSearch.search(array, target);
        assertEquals(-1, result);
    }
}
