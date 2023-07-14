package service.utils;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class StreamUtils {
    public static <E> Collection<E> filterAndCollect(Stream<E> stream, Predicate<E> predicate) {
        return stream.filter(predicate).toList();
    }

    public static <T> List<T> mergeLists(List<T>... lists) {

        Function<List<List<T>>, List<T>> mergeAll = listsF -> {
            List<T> lastList = new ArrayList<>();
            listsF.forEach(lastList::addAll);
            return lastList;
        };
        return mergeAll.apply(Arrays.stream(lists).toList());
    }

    public static <K,V> Map<K,V> mergeMaps(Map<K,V>... maps) {

        Function<List<Map<K,V>>, Map<K,V>> mergeAll = listsF -> {
            Map<K,V> lastMap = new LinkedHashMap <>();
            listsF.forEach(map -> {
                map.forEach((k,v) -> {
                    if(!lastMap.containsKey(k) && !lastMap.containsValue(v)) lastMap.put(k, v);
                });
            });
            return lastMap;
        };
        return mergeAll.apply(Arrays.stream(maps).toList());
    }

    public static void main(String[] args) {
        Map<Integer, String> map1 = Map.of(1, "Joao", 2, "Pedro", 3, "Lima");
        Map<Integer, String> map2 = Map.of(1, "Celular", 5, "PC", 6, "Java");
        Map<Integer, String> map3 = Map.of(4, "?", 7, "Gato", 8, "Cachorro");

        mergeMaps(map1, map2, map3).forEach((k, v) -> System.out.println("Key = " + k + " | Value = " + v));

        System.out.println(List.of("Joao", "Lima").toArray(new String[]{}).length);

    }

}
