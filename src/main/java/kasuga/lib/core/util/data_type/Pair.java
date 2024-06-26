package kasuga.lib.core.util.data_type;

import kasuga.lib.core.annos.Util;

/**
 * A simple data struct that contains two elements.
 * @param <K> Type of first data.
 * @param <V> Type of second data.
 * 一个简易的数据结构，包含两个元素
 * @param <K> 第一个数据的类型
 * @param <V> 第二个数据的类型
 */
@Util
public class Pair<K, V> {
    K first;
    V second;
    protected Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }

    public static <K, V> Pair<K, V> of(K first, V second) {
        return new Pair<K, V>(first, second);
    }


    public K getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }

    public Object get(boolean isFirst) {
        return isFirst ? first : second;
    }
}
