package com.android.hjalmar.quizapp.utils;

import java.util.Collections;
import java.util.List;

/**
 * Created by hjalmar
 * On 02/12/2017.
 */

public final class StringUtils {

    // Suppresses default constructor, ensuring non-instantiability.
    private StringUtils() {
    }

    /**
     * Checks that the input list of strings does not contain empty arguments.
     *
     * @param sa Input string array
     * @return true if the list is not empty but contains at least an empty element
     */
    public static boolean hasEmptyArgument(List<String> sa) {
        for (String a : sa) {
            if (a.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if two lists of strings contain the same elements.
     *
     * @param listA The first list
     * @param listB The second list
     * @return true if the two lists contain the same elements
     */
    public static boolean containSameElements(List<String> listA, List<String> listB) {
        if (listA.size() != listB.size()) {
            return false;
        }

        Collections.sort(listA);
        Collections.sort(listB);
        return listA.equals(listB);
    }
}
