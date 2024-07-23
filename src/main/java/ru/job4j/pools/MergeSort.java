package ru.job4j.pools;

/**
 * Класс реализует рекурсивную сортировку слиянием (merge sort).
 * Сортировка не изменяет исходный порядок элементов, а возвращает новый отсортированный массив
 */
public class MergeSort {

    public static int[] sort(int[] array) {
        return sort(array, 0, array.length - 1);
    }

    /**
     * Если массив из одного элемента, возвращаем элемент
     * В массиве более одного элемента - находим середину,
     * объединяем отсортированные части сортируя левую и правую части
     */
    private static int[] sort(int[] array, int from, int to) {
        if (from == to) {
            return new int[] {array[from] };
        }

        int middle = (from + to) / 2;
        return merge(
                sort(array, from, middle),
                sort(array, middle + 1, to)
        );
    }

    /**
     * Метод объединения двух отсортированных массивов
     */
    public static int[] merge(int[] left, int[] right) {
        int leftI = 0;
        int rightI = 0;
        int resultI = 0;
        int[] result = new int[left.length + right.length];
        while (resultI != result.length) {
            if (leftI == left.length) {
                result[resultI++] = right[rightI++];
            } else if (rightI == right.length) {
                result[resultI++] = left[leftI++];
            } else if (left[leftI] <= right[rightI]) {
                result[resultI++] = left[leftI++];
            } else {
                result[resultI++] = right[rightI++];
            }
        }
        return result;
    }
}