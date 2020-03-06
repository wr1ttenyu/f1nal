package wr1ttenyu.f1nal.study.nio;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

class Wr1ThreadFactory implements ThreadFactory {

    private AtomicInteger threadNum = new AtomicInteger(0);

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, "wr1-server worker num:" + threadNum.getAndIncrement());
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

/**
 * Definition for singly-linked list.
 */

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode l1HeadNode = l1;
        ListNode l1CurNode = l1;
        ListNode l1PreNode = null;
        ListNode l2HeadNode = l2;
        ListNode l2NextNode;
        while (l2HeadNode != null) {
            l2NextNode = l2HeadNode.next;
            if(l2HeadNode.val < l1CurNode.val) {
                if(l1CurNode == l1HeadNode) {
                    l1HeadNode = l2HeadNode;
                    l1PreNode = l2HeadNode;
                }
                l2HeadNode.next = l1CurNode;
                if(l1PreNode != null && l1PreNode != l2HeadNode) {
                    l1PreNode.next = l2HeadNode;
                    l1PreNode = l2HeadNode;
                }
                l2HeadNode = l2NextNode;
            } else {
                if(l1CurNode.next == null) {
                    l1CurNode.next = l2HeadNode;
                    break;
                }
                l1PreNode = l1CurNode;
                l1CurNode = l1CurNode.next;
            }
        }
        return l1HeadNode;
    }
}