package _16_Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

public class Subtree_of_another_tree {
    static class Node {
        int data;
        Node left, right;
        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    // Pair class for top view
    static class Pair {
        Node node;
        int hd;
        Pair(Node node, int hd) {
            this.node = node;
            this.hd = hd;
        }
    }

    // TreeInfo class for diameter
    static class TreeInfo {
        int ht, dia;
        TreeInfo(int ht, int dia) {
            this.ht = ht;
            this.dia = dia;
        }
    }

    // Check if two trees are identical
    public static boolean isIdentical(Node node, Node subroot) {
        if (node == null && subroot == null) return true;
        if (node == null || subroot == null || node.data != subroot.data) return false;
        return isIdentical(node.left, subroot.left) && isIdentical(node.right, subroot.right);
    }

    // Check if subroot is a subtree of root
    public static boolean isSubtree(Node root, Node subroot) {
        if (root == null) return false;
        if (root.data == subroot.data && isIdentical(root, subroot)) return true;
        return isSubtree(root.left, subroot) || isSubtree(root.right, subroot);
    }

    // Top view of tree
    public static void topview(Node root) {
        if (root == null) return;

        Queue<Pair> q = new LinkedList<>();
        TreeMap<Integer, Integer> map = new TreeMap<>();

        q.add(new Pair(root, 0));

        while (!q.isEmpty()) {
            Pair p = q.poll();
            Node curr = p.node;
            int hd = p.hd;

            if (!map.containsKey(hd)) {
                map.put(hd, curr.data);
            }

            if (curr.left != null) q.add(new Pair(curr.left, hd - 1));
            if (curr.right != null) q.add(new Pair(curr.right, hd + 1));
        }

        for (int val : map.values()) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    // Diameter of tree
    public static TreeInfo diameter(Node root) {
        if (root == null) {
            return new TreeInfo(0, 0);
        }

        TreeInfo left = diameter(root.left);
        TreeInfo right = diameter(root.right);

        int myHeight = Math.max(left.ht, right.ht) + 1;

        int diam1 = left.dia;
        int diam2 = right.dia;
        int diam3 = left.ht + right.ht + 1;

        int myDiameter = Math.max(diam3, Math.max(diam1, diam2));

        return new TreeInfo(myHeight, myDiameter);
    }

    public static void Klevel(Node root,int level,int k){
        if(root==null){
            return;
        }
        if(level==k){
            System.out.print(root.data+" ");
            return;
        }
        Klevel(root.left, level+1, k);
        Klevel(root.right, level+1, k);

    } 

    public static Node lowest_common_ancesstor(Node root,int n1,int n2){
        if(root==null || root.data==n1 || root.data==n2){
            return root;
        }
        Node left=lowest_common_ancesstor(root.left, n1, n2);
        Node right=lowest_common_ancesstor(root.right, n1, n2);
        if(left==null){
            return right;
        }
        if(right==null){
            return left;
        }
        return root;

    }
   
    public static boolean getPath(Node root, int n, ArrayList<Node> path) {
    if (root == null) {
        return false;
    }

    // add current node to path
    path.add(root);

    // if current node is the target node
    if (root.data == n) {
        return true;
    }

    // check in left and right subtrees
    boolean foundLeft = getPath(root.left, n, path);
    boolean foundRight = getPath(root.right, n, path);

    if (foundLeft || foundRight) {
        return true;
    }

    // backtrack → remove current node from path
    path.remove(path.size() - 1);
    return false;
}

  // Function to find Lowest Common Ancestor (LCA)
    public static Node lca(Node root, int n1, int n2) {
    ArrayList<Node> path1 = new ArrayList<>();
    ArrayList<Node> path2 = new ArrayList<>();

    // get paths for n1 and n2
    getPath(root, n1, path1);
    getPath(root, n2, path2);

    // compare the paths to find last common node
    int i = 0;
    for (; i < path1.size() && i < path2.size(); i++) {
        if (path1.get(i) != path2.get(i)) {
            break;
        }
    }

    // return last common ancestor
    return path1.get(i - 1);
}
    
   public static Node lca2(Node root, int n1, int n2) {
    if (root == null) {
        return null;
    }

    // If either n1 or n2 matches with root's key, return root
    if (root.data == n1 || root.data == n2) {
        return root;
    }

    // Look for keys in left and right subtrees
    Node leftLca = lca2(root.left, n1, n2);
    Node rightLca = lca2(root.right, n1, n2);

    // If both left and right return non-null, root is the LCA
    if (leftLca != null && rightLca != null) {
        return root;
    }

    // Otherwise return non-null value
    return (leftLca != null) ? leftLca : rightLca;
}

  public static int lcaDist(Node root, int n) {
    if(root == null) {
        return -1;
    }
    if(root.data == n) {
        return 0;
    }
    int leftDist = lcaDist(root.left, n);
    int rightDist = lcaDist(root.right, n);

    if(leftDist == -1 && rightDist == -1) {
        return -1;
    } else if(leftDist == -1) {
        return rightDist + 1;
    } else {
        return leftDist + 1;
    }
}
  public static int distance(Node root, int n1, int n2) {
    Node lcaNode = lca2(root, n1, n2);
    int dist1 = lcaDist(lcaNode, n1);
    int dist2 = lcaDist(lcaNode, n2);
    return dist1 + dist2;
  } 
  public static int KAancestor(Node root, int n, int k) {
    if (root == null) {
        return -1;
    }
    if (root.data == n) {
        return 0;
    }
    int leftdist = KAancestor(root.left, n, k );
    int rightdist= KAancestor(root.right, n, k );
    if(leftdist==-1 && rightdist==-1){
        return -1;
    }
    int max=Math.max(leftdist, rightdist);
    if(max+1==k){
        System.out.println(root.data);
    }
    return max+1;
}
  public static int transform(Node root){
    if(root==null){
        return 0;
    }
    int leftchild=transform(root.left);
    int rightchild=transform(root.right);
    int data=root.data;
    int newleft=root.left==null?0:root.left.data;
    int newright=root.right==null?0:root.right.data;
    root.data=root.left.data+leftchild+root.right.data+rightchild;
    return data;
  }
  public static int sum(Node root){
    if(root==null){
        return 0;
    }
    return sum(root.left)+sum(root.right)+root.data;
}
  public static void preorder(Node root){
    if(root==null){
        return;
    }
    System.out.println(root.data+" ");
     preorder(root.left);
     preorder(root.right);
  }
public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(7);

        Node subroot = new Node(2);
        subroot.left = new Node(4);
        subroot.right = new Node(5);

        // Subtree check
        System.out.println("Is Subtree: " + isSubtree(root, subroot)); // true

        // Top view
        System.out.print("Top View: ");
        topview(root); // 4 2 1 3 7

        // Diameter
        TreeInfo info = diameter(root);
        System.out.println("Diameter: " + info.dia);
    }
}
