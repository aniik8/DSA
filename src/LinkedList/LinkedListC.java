package LinkedList;

import java.util.List;

public class LinkedListC {
    public static void main(String[] args) {
      Node n = new Node(3);
      Node n2 = new Node(2);
      n2.next= n;
      Node n3 = new Node(10);
      n3.next = n2;
      Node temp = n3;
      // basic traversing of a linkedList;
      while(temp!= null){
          System.out.print(temp.data + "-> ");
          temp = temp.next;
      }
    }
    // delete second node
    public boolean hasCycle(ListNode head) {
        // using fast and slow pointers where fast moves 2 and slow moves 1
        ListNode fast = head;
        ListNode slow = head;
        // condition means that the cycle does not exit so exit
        while(fast != null && fast.next != null)
        {
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow)
                return true;

        }
        return false;
    }
    // length of a cycle in a linkedlist
    static int countNodesinLoop(Node head)
    {
        // detect a cycle code and then count the length of a cycle
        Node slow = head;
        Node fast = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(slow == fast){
                Node temp = slow;
                int n = 0;
                do{
                    n++;
                    temp = temp.next;
                }while(temp != slow );
                return n;
            }
        }
        return 0;
    }
    // detect the starting point of the cycle in the linked-list
    public ListNode detectCycle(ListNode head) {
        // calculate the length of the cycle
        ListNode slow = head;
        ListNode fast = head;
        int n = 0;
//                countNodesinLoop(slow);
        // now done with the size of cycle, move the pointers
        ListNode first = head;
        ListNode second = head;
        while(n > 0){
            n--;
            second = second.next;
        }
        while(second != first){
            second = second.next;
            first = first.next;
        }
        return first;
    }

// merge two sorted List
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null)
            return list2;
        if(list2 == null)
            return list1;
        ListNode newList = new ListNode(0);
        ListNode head = newList;
        while(list1 != null && list2 != null){
            if(list1.data < list2.data){
                head.next = list1;
                list1 = list1.next;
                head = head.next;
            }
            else{
                head.next = list2;
                list2 = list2.next;
                head = head.next;
            }
        }

        while(list1 != null){
            head.next = list1;
            list1 = list1.next;
            head = head.next;
        }
        while(list2 != null){
            head.next = list2;
            list2 = list2.next;
            head = head.next;
        }
        return newList.next;
    }
// sorting two given linkedLists
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode mid = middleNode(head);
        ListNode left = sortList(head);
        ListNode right = sortList(mid);

        return mergeTwoLists(left, right);
    }
    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    // reverse linkedList 2
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(left == right)
            return head;
        ListNode current = head;
        ListNode previous = null;
        for (int i = 0; current != null && i < left - 1; i++) {
            previous = current;
            current = current.next;
        }
        ListNode last = previous;
        ListNode newHead = current;

        // reverse the list now
        ListNode next = current.next;
        for (int i = 0; current != null && i < right - left + 1; i++) {
            current.next = previous;
            previous = current;
            current = next;
            if(next != null){
                next = next.next;
            }
        }
        if(last != null)
            last.next =previous;
        else head = previous;
        return head;
    }
}
class Node{
    int data;
    Node next;
    Node(int data){
        this.data = data;
    }
}
class ListNode {
    int data;
    ListNode next;

    ListNode(int data) {
        this.data = data;
    }
}