package _17_Tree;

public class Mirrorbst {
    static class Node{
        int data;
        Node left;
        Node right;
       public  Node(int data){
            this.data=data;
            this.left=null;
            this.right=null;
        }
    }
    public static Node mirror(Node root){
        if(root==null){
            return null;
        }
        Node leftsubtree=mirror(root.left);
        Node rightsubtree=mirror(root.right);

        root.left=rightsubtree;
        root.right=leftsubtree;

        return root;
    }
    public static void preorder(Node root){
        if(root==null){
            return;
        }
        System.out.print(root.data+" ");
        preorder(root.left);
        preorder(root.right);
    }
    public static void main(String[] args) {
        Node root=new Node(8);
        root.left=new Node(5);
        root.right=new Node(10);
        root.left.left=new Node(3);
        root.left.right=new Node(6);
        root.right.right=new Node(11);
        
        root=mirror(root);
        preorder(root);
    }
}
