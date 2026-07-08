public class AddLinkedListNumbers {
    public static void main(String[] args) {
        //l1 =[9]
        //l2 = [1,9,9,9,9,9,9,9,9,9]

        ListNode l1 = new ListNode(9, null);
        ListNode l2 = new ListNode(1, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, null)))))))));
        Solution solution = new Solution();
        ListNode result = solution.addTwoNumbers(l1, l2);
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}


//      Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        long val1 = 0;
        long val2 = 0;
        int count = 0;
        boolean bool1 = false;
        boolean bool2 = false;
        while (true) {
            if (null != l1) {
                val1 = val1 + l1.val * (Math.powExact(10, count));
                l1 = l1.next;
            } else {
                bool1 = true;
            }

            if (null != l2) {
                val2 = val2 + l2.val * (Math.powExact(10, count));
                l2 = l2.next;
            } else {
                bool2 = true;
            }
            count++;
            if (bool1 && bool2)
                break;
        }
        System.out.println(val1+val2);

        String addition = String.valueOf(val1 + val2);
        ListNode sum = new ListNode();
        for (int i = 0; i < addition.length(); i++) {
            char c = addition.charAt(i);
            if (i == 0) {
                sum = new ListNode(Character.getNumericValue(c));
            } else {
                sum = new ListNode(Character.getNumericValue(c), sum);
            }
        }

        return sum;
    }
}
