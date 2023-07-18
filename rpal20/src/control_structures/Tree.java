/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control_structures;

/**
 *
 * @author OshadiPC
 */
import java.util.ArrayList;
import java.util.List;

public class Tree {
    private int pos;
    private List<Node> stack;

    public Tree() {
        pos = -1;
        stack = new ArrayList<>();
    }

    public void build_tree(String data, int num) {
        Node temp = new Node(data);
        temp.setChild(null);
        temp.setSibling(null);
        if (num == 0) {
            stack.add(temp);
            pos++;
        } else {
            Node a, b;
            if (pos == -1) {
                System.out.println("\nStack Empty Error : " + data);
                System.exit(1);
            }
            a = stack.get(stack.size() - 1);
            stack.remove(stack.size() - 1);
            --pos;
            --num;
            while (num != 0) {
                if (pos == -1) {
                    System.out.println("\nStack Empty Error : ");
                    System.out.println(data);
                    System.exit(1);
                }
                b = stack.get(stack.size() - 1);
                stack.remove(stack.size() - 1);
                --pos;
                --num;
                b.setSibling(a);
                a = b;
            }
            temp.setChild(a);
            stack.add(temp);
            pos++;
        }
    }

    public void preorder() {
        traverse(stack.get(0), 0);
    }

    public void traverse(Node root, int n) {
        if (root != null) {
            for (int i = 0; i < n; ++i)
                System.out.print(".");
            System.out.println(root.getValue());
            traverse(root.getChild(), n + 1);
            traverse(root.getSibling(), n);
        }
    }

}