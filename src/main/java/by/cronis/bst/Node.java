package by.cronis.bst;

public class Node {
    Node left;
    Node right;
    Integer value;

    static boolean nodeExists(Node node) {
        return node != null && node.value != null;
    }

    static void createNode(Node node, int value) {
        node.left = new Node();
        node.right = new Node();
        node.value = value;
    }

    static void insert(Node node, int value) {
        if (!nodeExists(node)) {
            createNode(node, value);
        } else if (value < node.value) {
            insert(node.left, value);
        } else {
            insert(node.right, value);
        }
    }

    static Node search(Node node, int value) {
        if (!nodeExists(node)) {
            return null;
        }
        if (node.value == value) {
            return node;
        }
        if (value < node.value) {
            return search(node.left, value);
        }
        return search(node.right, value);
    }

    static Node getMin(Node node) {
        if (!nodeExists(node)) {
            return null;
        }
        if (!nodeExists(node.left)) {
            return node;
        }
        return getMin(node.left);
    }

    static Node getMax(Node node) {
        if (!nodeExists(node)) {
            return null;
        }
        if (!nodeExists(node.right)) {
            return node;
        }
        return getMax(node);
    }

    static void inOrderTraversal(Node node) {
        if (!nodeExists(node)) {
            return;
        }
        inOrderTraversal(node.left);
        System.out.println(node.value);
        inOrderTraversal(node.right);
    }

    static void postOrderTraversal(Node node) {
        if (!nodeExists(node)) {
            return;
        }
        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        System.out.println(node.value);
    }

    static void preOrderTraversal(Node node) {
        if (!nodeExists(node)) {
            return;
        }
        System.out.println(node.value);
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
    }

    static boolean remove(Node root, int value) {
        Node nodeToDelete = search(root, value);
        if (!nodeExists(nodeToDelete)) {
            return false;
        }

        int childrenCount = getChildrenCount(nodeToDelete);
        if (childrenCount < 2) {
            removeNodeWithOneOrZeroChild(nodeToDelete);
        } else {
            Node minNode = getMin(nodeToDelete.right);
            nodeToDelete.value = minNode.value;
            removeNodeWithOneOrZeroChild(minNode);
        }
        return true;
    }

    private static void transplantNode(Node toNode, Node fromNode) {
        toNode.value = fromNode.value;
        toNode.left = fromNode.left;
        toNode.right = fromNode.right;
    }

    private static int getChildrenCount(Node node) {
        int count = 0;
        if (nodeExists(node.left)) {
            count++;
        }
        if (nodeExists(node.right)) {
            count++;
        }
        return count;
    }

    private static Node getChildOrNill(Node node) {
        return nodeExists(node.left) ? node.left : node.right;
    }

    private static void removeNodeWithOneOrZeroChild(Node nodeToDelete) {
        Node childOrNill = getChildOrNill(nodeToDelete);
        transplantNode(nodeToDelete, childOrNill);
    }
}
