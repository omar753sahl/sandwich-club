package com.udacity.sandwichclub.utils;

import java.util.Iterator;

/**
 * Created by Omar on 15-Feb-18 4:45 PM
 */

public class StringUtils {
    public static <T> String join(Iterable<T> list, String separator) {
        StringBuilder builder = new StringBuilder();
        Iterator<T> parts = list.iterator();
        if (parts.hasNext()) {
            builder.append(parts.next());
            while (parts.hasNext()) {
                builder.append(separator).append(parts.next());
            }
        }
        return builder.toString();
    }
}
