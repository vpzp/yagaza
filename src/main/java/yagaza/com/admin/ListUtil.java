package yagaza.com.admin;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListUtil {
    public static <T> List<List<T>> convertArrayToList(List<T>[] array) {
        return Arrays.stream(array)
                .collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T>[] convertListToArray(List<List<T>> list) {
        if (list == null) {
            return null; // 리스트가 null인 경우 null 반환
        }
        return list.toArray(new List[0]);
    }
}
