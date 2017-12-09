package com.android.hjalmar.quizapp.utils;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by hjalmar
 * On 04/12/2017.
 */

public class RandomPermutationUtils {

    // Suppresses default constructor, ensuring non-instantiability.
    private RandomPermutationUtils() {
    }

    /* Builds the array [0, 1, ..., n - 1]. */
    private static List<Integer> buildIndicesArray(int n) {
        if (n < 1) {
            throw new InvalidParameterException("Indices array must have positive length");
        }
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            indices.add(i);
        }
        return indices;
    }

    /**
     * Returns random k elements from the input list.
     *
     * @param list The input list
     * @param k    The number of elements to be selected
     * @param <E>  The type of element of the list
     * @return a random list containing k elements from the input list
     */
    public static <E> List<E> getRandomDisposition(List<E> list, int k) {
        if (k < 0) {
            throw new InvalidParameterException("Number of elements selected can't be negative");
        }
        if (k > list.size()) {
            throw new InvalidParameterException("Number of elements selected cannot exceed number of elements of the list");
        }

        List<E> selectedElements = new ArrayList<>();
        List<Integer> indicesArray = buildIndicesArray(list.size());
        Random r = new Random(System.currentTimeMillis());

        for (int i = 0, current_size = indicesArray.size(); i < k; i++) {
            int indexOfIndicesArray = r.nextInt(current_size--);
            int indexOfList = indicesArray.get(indexOfIndicesArray);
            selectedElements.add(list.get(indexOfList));
            Collections.swap(indicesArray, indexOfIndicesArray, current_size);
        }
        return selectedElements;
    }

    /**
     * Returns a random permutation of the input list.
     *
     * @param list The input list
     * @param <E>  The type of element of the list
     * @return a random permutation of the input list
     */
    public static <E> List<E> getRandomPermutation(List<E> list) {
        return getRandomDisposition(list, list.size());
    }

}
