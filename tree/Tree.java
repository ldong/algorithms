package tree;

import java.util.Scanner;

/**
 * Created by ldong on 10/23/14.
 */
public class Tree {
    public static Scanner scan = new Scanner(System.in);
    public static enum OrderType{
        PreOrder, InOrder, PostOrder
    }

    /**
     * Pre: 0 1 3 6 7 4 2 5 8 9
     *  In: 6 3 7 1 4 0 2 8 5 9
     * @param args
     */
    public static void main(String[] args){
        System.out.println("Please type your int array here:");
        String[] strs = scan.nextLine().split("\\s*(,|\\s)\\s*");
        int[] arr = getIntArray(strs);
//        printMe(arr);
        System.out.println("Please the name of your traversal, i.e. 'inorder', 'preorder', and 'postorder'");
        String t = scan.nextLine();
        OrderType type;
        if("".equals(t)) {
//            type = getOrderType("preorder");
            type = getOrderType("postorder");
            System.out.println("Default is " + type);
        } else {
            type = getOrderType(t);
        }
        Node root = constructTree(arr, type);

        System.out.println("\nPre Order:");
        Traversal.preOrder(root);

        System.out.println("\nIn Order:");
        Traversal.inOrder(root);

        System.out.println("\nPost Order:");
        Traversal.postOrder(root);

        System.out.println("\nIs BST?");
        System.out.println(isBinarySearchTree(root));
    }

    public static int[] getIntArray(String[] strs){
        int[] values = new int[strs.length];
        for (int i = 0; i < strs.length; ++i) {
            values[i] = Integer.parseInt(strs[i]);
        }
        return values;
    }

    public static OrderType getOrderType(String str){
        OrderType type = OrderType.PreOrder;
        for(OrderType t: OrderType.values()){
            if (t.toString().toLowerCase().equals(str.toLowerCase())) {
                type = t;
                break;
            }
        }
//        System.out.println(type.toString());
        return type;
    }
    public static Node constructTree(int[] arr, OrderType type){
        if(arr.length == 0){
            return null;
        } else if (arr.length == 1){
            return new Node(arr[0]);
        } else {
            if (type.equals(OrderType.PreOrder)) {
                return buildPreOrderTree(arr);
            } else if (type.equals(OrderType.PostOrder)) {
                return buildPostOrderTree(arr);
            } else {
                System.out.println("There is no way to construct a binary tree");
                System.exit(1);
                return null;
            }
        }
    }

    public static Node buildPreOrderTree(int[] arr){
        return buildPreOrderTreeHelper(arr, 0, arr.length-1);
    }

    public static Node buildPreOrderTreeHelper(int[] arr, int start, int end){
        if(start >= end){
            return null;
        } else if (start == end ){
            return new Node(arr[start]);
        } else {
            Node root = new Node(arr[start]);
            int split = start;
            // find the first element that greater than the current node
            while (split <= end) {
                if (arr[split] > arr[start]) {
                    break;
                }
                ++split;
            } //
            root.left = buildPreOrderTreeHelper(arr, start + 1, split - 1);
            root.right = buildPreOrderTreeHelper(arr, split, end);
            return root;
        }
    }

    public static Node buildPostOrderTree(int[] arr) {
        return buildPostOrderTreeHelper(arr, 0, arr.length-1);
    }

    public static Node buildPostOrderTreeHelper(int[] arr, int start, int end) {
        if(start > end){
            return null;
        } else if (start == end ){
            return new Node(arr[end]);
        } else {
            Node root = new Node(arr[end]);
            int split = end;
            // find the first element that less than the current node
            while (split > start) {
                if (arr[split] < arr[end]) {
                    break;
                }
                --split;
            } //
            root.left = buildPostOrderTreeHelper(arr, start, split);
            root.right = buildPostOrderTreeHelper(arr, split+1, end-1);
            return root;
        }
    }

    public static void printMe(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+", ");
        }
        System.out.println();
    }

    public static boolean isBinarySearchTree(Node root){
        return isBinarySearchTree(root, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    public static boolean isBinarySearchTree(Node root, int max, int min){
        if(root == null){
            return true;
        }
        if(root.data > max || root.data < min){
            return false;
        }

        return isBinarySearchTree(root.left, root.data, min) && isBinarySearchTree(root.right, max, root.data);
    }
}
