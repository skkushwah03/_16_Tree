package _17_Tree;   
 import java.util.*;
 public class Bst {
     static class Node{
         int data;
         Node left;
         Node right;
         Node(int data){
             this.data=data;
             this.left=null;
             this.right=null;
         }
     }
     public static Node insert(Node root,int val){
         if(root==null){
             root=new Node(val);
             return root;
         }
         if(root.data>val){
             //left subtree
             root.left=insert(root.left, val);
         }else{
             //right subtree
             root.right=insert(root.right, val);
         }
         return root;
     }
     public static void inorder(Node root){
         if(root==null){
             return;
         }
         inorder(root.left);
         System.out.print(root.data+" ");
         inorder(root.right);
     }
     public static boolean search(Node root,int key){
         if(root==null){
             return false;
         }
         if(root.data==key){
             return true;
         }
         if(root.data>key){
             return search(root.left, key);
         }else{
             return search(root.right, key);
         }
     }
     public static Node delete(Node root,int val){
         if(root.data>val){
             root.left=delete(root.left, val);
         }else if(root.data<val){
             root.right=delete(root.right, val);
         }else{
             //case 1
             if(root.left==null && root.right==null){
                 return null;
             }
             //case 2
             if(root.left==null){
                 return root.right;
             }else if(root.right==null){
                 return root.left;
             }
             //case 3
             Node IS=InorderSuccessor(root.right);
             root.data=IS.data;
             root.right=delete(root.right, IS.data);
         }
         return root;
     }
     public static Node InorderSuccessor(Node root){
         while(root.left!=null){
             root=root.left;
         }
         return root;
     }
     public static void printInRange(Node root,int k1,int k2){
         if(root==null){
             return;
         }
         if(root.data>=k1 && root.data<=k2){
             printInRange(root.left, k1, k2);
             System.out.print(root.data+" ");
             printInRange(root.right, k1, k2);
         }else if(root.data<k1){
             printInRange(root.right, k1, k2);
         }else{
             printInRange(root.left, k1, k2);
         }
     }
     public static void printRoot2Leaf(Node root,String path){
         if(root==null){
             return;
         }
         if(root.left==null && root.right==null){
             System.out.println(path+root.data);
             return;
         }
         printRoot2Leaf(root.left, path+root.data+" ");
         printRoot2Leaf(root.right, path+root.data+" ");
     }  
     public static boolean isValidBST(Node root,Node min,Node max){
         if(root==null){
             return true;
         }
         if(min!=null && root.data<=min.data){
             return false;
         }else if(max!=null && root.data>=max.data){
             return false;
         }
         return isValidBST(root.left, min, root) && isValidBST(root.right, root, max);
     }  
    
     public static void main(String[] args) {
            int values[]={5,1,3,4,2,7};
            Node root=null;
            for(int i=0;i<values.length;i++){
                root=insert(root, values[i]);
            }
            inorder(root);
            System.out.println();
            if(search(root, 10)){
                 System.out.println("Found");
            }
            printInRange(root, 5, 7);
     }
    }
