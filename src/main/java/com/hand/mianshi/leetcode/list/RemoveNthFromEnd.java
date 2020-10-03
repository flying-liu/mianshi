package com.hand.mianshi.leetcode.list;

import java.util.List;

/**
 * @author fei.liu
 * @date 2020/10/3 22:25
 * @desc 删除链表倒数第N个节点
 */

public class RemoveNthFromEnd {


    public ListNode removeNode(ListNode head,int n){
        ListNode dummyHead = new ListNode(0);

        dummyHead.next = head;

        ListNode first = dummyHead;

        ListNode second = dummyHead;

        while(n >= 0){
            n -- ;
            first = first.next;
        }

        while(first != null){
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummyHead.next;
    }



    private static class ListNode{
        int val;

        ListNode next;

        ListNode(int val){
            this.val = val;
        }
    }


    public static void main(String[] args){
        RemoveNthFromEnd removeNthFromEnd = new RemoveNthFromEnd();

            ListNode listNode = new ListNode(1);

        ListNode dummyHead = new ListNode(0);
        dummyHead.next = listNode;
//
//        listNode.next = new ListNode(2);
//
//        listNode.next.next = new ListNode(3);
//
//        listNode.next.next.next = new ListNode(4);

        for(int i = 1; i < 5 ; i ++){
            ListNode curList = new ListNode(i + 1);
            listNode.next = curList;
            listNode = listNode.next;
        }

        ListNode nodeHead = removeNthFromEnd.removeNode(dummyHead.next, 1);
        while(nodeHead != null){
            System.out.println(nodeHead.val);
            nodeHead = nodeHead.next;
        }
    }
}
