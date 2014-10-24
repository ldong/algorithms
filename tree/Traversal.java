package tree;

/**
 * Created by ldong on 10/23/14.
 */
public class Traversal {
    public static void preOrder(Node root){
        if(root == null) return;
        System.out.print(root.data +", ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public static void inOrder(Node root){
        if(root == null) return;
        inOrder(root.left);
        System.out.print(root.data +", ");
        inOrder(root.right);
    }

    public static void postOrder(Node root){
        if(root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data +", ");
    }
    /**
     * @TODO
     */
    public static void levelOrder(Node root){

    }
}
