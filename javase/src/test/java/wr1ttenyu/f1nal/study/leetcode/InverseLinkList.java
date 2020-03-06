package wr1ttenyu.f1nal.study.leetcode;

public class InverseLinkList {

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode curNode = head;
        ListNode nextFirst = head.next;
        head.next = null;
        ListNode nextSecond;
        while (nextFirst != null) {
            nextSecond = nextFirst.next;
            nextFirst.next = curNode;
            if (nextSecond == null) {
                break;
            }
            curNode = nextFirst;
            nextFirst = nextSecond;
        }
        return nextFirst;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}