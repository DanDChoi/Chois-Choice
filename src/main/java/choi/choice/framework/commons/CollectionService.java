package choi.choice.framework.commons;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.Predicate;

import java.util.*;

public class CollectionService {
    public static <O> int cardinality(O object, Iterable<? super O> collector) {
        return CollectionUtils.cardinality(object, collector);
    }


    public static boolean containsAll(Collection<?> coll1, Collection<?> coll2) {
        return CollectionUtils.containsAll(coll1, coll2);
    }

    public static <O> boolean isEmpty(Object object) {

        if (object == null || CollectionUtils.size(object) <= 0) {
            return true;
        }

        return false;
    }

    public static <T> List<T> emptyListIfNull(List<T> list) {
        return ListUtils.emptyIfNull(list);
    }

    public static <T> List<List<T>> partitionList(List<T> list, int size) {
        return ListUtils.partition(list, size);
    }

    public static <E> List<E> unionList(List<? extends E> list1,
                                        List<? extends E> list2) {
        return ListUtils.union(list1, list2);

    }

    public static <E> List<E> sumList(List<? extends E> list1, List<? extends E> list2) {
        return ListUtils.sum(list1, list2);
    }

    public static <E> List<E> intersectionList(List<? extends E> list1,
                                               List<? extends E> list2) {
        return ListUtils.intersection(list1, list2);
    }

    public static <E> List<E> selectList(Collection<? extends E> inputCollection,
                                         Predicate<? super E> predicate) {

        return ListUtils.select(inputCollection, predicate);
    }

    public static <T> List<T> reverseList(List<T> list) {
        return Lists.reverse(list);
    }

    public static <T> List<T> setToList(Set<T> set) {
        List<T> list = Lists.newArrayList();
        list.addAll(set);
        return list;
    }

    public static <E> List<E> iteratortoList(Iterator<? extends E> iterator) {
        return IteratorUtils.toList(iterator);
    }

    public static <E> List<E> collectionToList(Collection<? extends E> collection) {
        return new ArrayList<E>(collection);
    }
}
