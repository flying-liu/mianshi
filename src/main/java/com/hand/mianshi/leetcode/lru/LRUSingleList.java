package com.hand.mianshi.leetcode.lru;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author fei.liu
 * @date 2020/10/3 17:23
 * @desc
 */

public class LRUSingleList<K, V> implements LRUCache<K, V> {

    private Map<K, V> map;

    private LinkedList<K> linkedList;

    private int capacity;

    public LRUSingleList(int capacity){
        this.capacity = capacity;
        linkedList = new LinkedList<>();
        map = new HashMap<>(capacity);
    }

    @Override
    public V get(K key){
        if(map.containsKey(key)){
            linkedList.remove(key);
            linkedList.addLast(key);
            return map.get(key);
        }
        return null;
    }

    @Override
    public void put(K key, V value){
        map.put(key, value);
        if(map.containsKey(key)){
            linkedList.remove(key);
            linkedList.addLast(key);
        }
        if(map.size() > capacity){
            map.remove(linkedList.getFirst());
            linkedList.removeFirst();
        }
    }

    public static void main(String[] args){
        LRUSingleList<Integer, Integer> singleList = new LRUSingleList<>(3);
        singleList.put(1, 22);
        singleList.put(2, 34);
        singleList.put(3, 45);
        singleList.get(1);
        singleList.put(4, 44);
        singleList.put(3, 23);
        singleList.put(5, 55);
        System.out.println(singleList.linkedList);
        System.out.println(singleList.map);

    }

}
