package com.hand.mianshi.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fei.liu
 * @date 2020/10/3 15:04
 * @desc
 */

public class LRUDoubleLinkHashMap<K, V> implements LRUCache<K, V> {

    private int capacity;

    private Map<K, Node<K,V>> map;

    private Node<K,V> dummyHead;

    private Node<K,V> dummyTail;


    /**
     * 初始化赋值
     *
     * @param capacity 初始化容量
     */
    public LRUDoubleLinkHashMap(int capacity){
        this.capacity = capacity;
        map = new HashMap<>(capacity);
        dummyHead = new Node<>();
        dummyTail = new Node<>();
        dummyTail.pre = dummyHead;
        dummyHead.next = dummyTail;
    }

    @Override
    public V get(K key){
        //可以获取到值，将node移至末尾
        if(map.containsKey(key)){
            Node<K, V> node = map.get(key);
            node.pre.next = node.next;
            node.next.pre = node.pre.next;
            moveToTail(node);
            return node.value;
        }
        return null;
    }

    @Override
    public void put(K key, V value){
        //调用get方法已存在移至末尾并重新赋值
        if(get(key) != null){
            map.get(key).value = value;
            return;
        }
        //不存在，新建节点
        Node<K,V> node = new Node<>(key, value);
        map.put(key, node);
        moveToTail(node);
        //判断map是否超容量
        if(map.size() > capacity){
            map.remove(dummyHead.next.key);
            dummyHead.next = dummyHead.next.next;
            dummyHead.next.pre = dummyHead;
        }
    }

    private void moveToTail(Node<K,V> node){
        node.next = dummyTail;
        node.pre = dummyTail.pre;
        node.pre.next = node;
        dummyTail.pre = node;
    }


    private static class Node<K, V> {

        K key;

        V value;

        Node<K, V> pre;

        Node<K, V> next;

        Node(){}


        Node(K key, V value){
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString(){
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }


    public static void main(String[] args){
        LRUDoubleLinkHashMap<Integer,Integer> doubleLinkHashMap = new LRUDoubleLinkHashMap<>(3);

        doubleLinkHashMap.get(1);
        doubleLinkHashMap.put(1, 11);
        doubleLinkHashMap.put(2, 22);
        doubleLinkHashMap.put(3, 33);
        doubleLinkHashMap.get(1);
        doubleLinkHashMap.put(4, 44);
        doubleLinkHashMap.put(5, 55);
        doubleLinkHashMap.get(1);
        doubleLinkHashMap.put(6, 66);


        for(Object node : doubleLinkHashMap.map.values()){
            System.out.println(node);
        }

    }
}
