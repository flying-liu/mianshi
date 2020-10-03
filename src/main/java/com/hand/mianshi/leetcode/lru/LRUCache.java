package com.hand.mianshi.leetcode.lru;

import java.util.LinkedHashMap;

/**
 * @author fei.liu
 * @date 2020/10/3 13:08
 * @desc
 */

public interface LRUCache<K, V> {

    V get(K key);

    void put(K key, V value);
}
