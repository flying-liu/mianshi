package com.hand.mianshi.leetcode;

import java.util.LinkedHashMap;

/**
 * @author fei.liu
 * @date 2020/10/3 15:00
 * @desc
 */

public class LRULinkedHashMap<K,V> implements LRUCache<K,V> {

    private int capacity;

    private LinkedHashMap<K, V> map;


    public LRULinkedHashMap(int capacity){
        this.capacity = capacity;
        //accessOrder = true,这就是基于访问的顺序，get一个元素后，这个元素被加到最后(使用了LRU 最近最少被使用的调度算法)
        map = new LinkedHashMap<>((int) ((capacity / 0.75) + 1), 0.75f, true);
    }

    @Override
    public V get(K key){
        return map.get(key);
    }

    @Override
    public void put(K key ,V value){
        map.put(key,value);
        if(map.size() > capacity){
            map.remove(map.entrySet().iterator().next().getKey());
        }
    }


    public static void main(String[] args){
        LRULinkedHashMap<Integer,Integer> lruCache = new LRULinkedHashMap<>(3);

        lruCache.put(1,22);
        lruCache.put(2,34);
        lruCache.put(3,45);
        lruCache.get(1);

        System.out.println(lruCache.map);
    }
}
