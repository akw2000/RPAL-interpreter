// package control_structures;

// /**
//  *
//  * @author OshadiPC
//  */
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Scanner;
// public class Tree {
//     private int pos;
//     private List<Node> stack;

//     public Tree() {
//         pos = -1;
//         stack = new ArrayList<>();
//     }

//     public void build_tree(String data, int num) {
//         Node temp = new Node(data);
//         temp.value = data;
//         temp.child = null;
//         temp.sibling = null;
//         if (num == 0) {
//             stack.add(temp);
//             pos++;
//         } else {
//             Node a, b;
//             if (pos == -1) {
//                 System.out.println("\nStack Empty Error : " + data);
//                 System.exit(1);
//             }
//             a = stack.get(stack.size() - 1);
//             stack.remove(stack.size() - 1);
//             --pos;
//             --num;
//             while (num != 0) {
//                 if (pos == -1) {
//                     System.out.println("\nStack Empty Error : ");
//                     System.out.println(data);
//                     System.exit(1);
//                 }
//                 b = stack.get(stack.size() - 1);
//                 stack.remove(stack.size() - 1);
//                 --pos;
//                 --num;
//                 b.sibling = a;
//                 a = b;
//             }
//             temp.child = a;
//             stack.add(temp);
//             pos++;
//         }
//     }

//     public void preorder() {
//         traverse(stack.get(0), 0);
//     }

//     public void traverse(Node root, int n) {
//         if (root != null) {
//             for (int i = 0; i < n; ++i)
//                 System.out.print(".");
//             System.out.println(root.value);
//             traverse(root.child, n + 1);
//             traverse(root.sibling, n);
//         }
//     }

// }