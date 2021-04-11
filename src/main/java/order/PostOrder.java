package order;

import java.util.Stack;

/**
 * @date 2021/4/6 21:33
 */
public class PostOrder {

    public static void postOrderUnRecur(Node head) {
        if (head != null) {
            Stack<Node> nodes = new Stack<>();
            Node h = head;
            nodes.push(head);
            while (!nodes.isEmpty()) {
                Node c = nodes.peek();
                if (c.left != null && h != c.left && h != c.right) {
                    nodes.push(c.left);
                } else if (c.right != null && h != c.right) {
                    nodes.push(c.right);
                } else {
                    h = c;
                    System.out.println(nodes.pop().value + " ");
                }
            }
        }
    }
}
