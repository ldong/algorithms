package tree;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by ldong on 10/23/14.
 */
public class ConstructBST {
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
//        System.out.println("Please type your int array here:");
//        String[] strs = scan.nextLine().split("\\s*(,|\\s)\\s*");
//        int[] pre = Tree.getIntArray(strs);

//        int[] pre = {10, 7, 6, 9, 12};
        int[] pre = {13, 7, 6, 9, 12, 17};
        Tree.printMe(pre);

        Node root = getBST(pre);
//        Traversal.preOrder(root);
        ArrayList<Node> n = new ArrayList<Node>();
        n.add(root);
        printLevel(n);
    }

    /**
     * Assume all places have valid ints
     * Assume no repeats and length >= 1
     * @param pre
     * @return
     */
    public static Node getBST(int[] pre) {
        Node head = new Node(pre[0]);
        for (int i = 1; i < pre.length; i++) {
            addNode(head, new Node(pre[i]));
        }
        return head;
    }

    public static void addNode(Node h, Node n) {
        Node curr = h;
        while (true) {
            if (n.data > curr.data) {
                if (curr.right == null) {
                    curr.right = n;
                    return;
                } else {
                    curr = curr.right;
                }
            } else {
                if (curr.left == null) {
                    curr.left = n;
                    return;
                } else {
                    curr = curr.left;
                }
            }
        }
    }


    public static void printLevel(ArrayList<Node> n){
        ArrayList<Node> next = new ArrayList<Node>();
        for (Node t: n) {
            System.out.print(t.data+" ");
            if (t.left!= null)
                next.add(t.left);
            if (t.right!=null)
                next.add(t.right);
        }
        System.out.println();
        if (next.size()!=0)
            printLevel(next);
    }

}
