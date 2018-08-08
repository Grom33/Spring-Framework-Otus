package ru.otus.gromov.util;

import ru.otus.gromov.domain.HasId;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SetUtil {
    public static int[] setToStringWithId(List<? extends HasId> list) {
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i).getId();
        }
        return result;
    }

    public static String getStringFromSet(Set<Integer> set) {
        return (set == null) ? "" : set.stream().map(Object::toString).collect(Collectors.joining(","));

    }

    public static Set<Integer> getSetFromString(String string) {
        return (string == null || string.equals("")) ? new HashSet<>() : Arrays.stream(string.trim().split(","))
                .map(r -> Integer.parseInt(r.trim()))
                .collect(Collectors.toSet());

    }

}
