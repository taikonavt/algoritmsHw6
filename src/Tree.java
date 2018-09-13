public class Tree {
    private class TreeNode {
        private int c;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int c) {
            this.c = c;
        }
        @Override
        public String toString() {
            return Integer.toString(c);
        }
    }

    TreeNode root;

//    public Cat find(int age) {
//        TreeNode current = root;
//        while (current.c.age != age) {
//            if (age < current.c.age)
//                current = current.left;
//            else
//                current = current.right;
//
//            if (current == null)
//                return null;
//        }
//        return current.c;
//    }

    public void insert(int c) {
        TreeNode node = new TreeNode(c);
        if (root == null) {
            root = node;
        } else {
            TreeNode current = root;
            TreeNode previous;
            while (true) {
                previous = current;
                if (c < current.c) {
                    current = current.left;
                    if (current == null) {
                        previous.left = node;
                        return;
                    }
                } else {
                    current = current.right;
                    if (current == null) {
                        previous.right = node;
                        return;
                    }
                }
            }
        }
    }

    public void displayTree() {
        inOrderTravers(root);
    }

    private void inOrderTravers(TreeNode current) {
        if (current != null) {
            inOrderTravers(current.left);
            System.out.println(current);
            inOrderTravers(current.right);
        }
    }

    public boolean delete(int c) {
        TreeNode current = root;
        TreeNode parent = root;
        boolean isLeftChild = true;

        while (current.c != c) {
            parent = current;
            if (c < current.c) {
                current = current.left;
                isLeftChild = true;
            } else {
                current = current.right;
                isLeftChild = false;
            }
            if (current == null)
                return false;
        }

        //if node is a leaf
        if (current.left == null && current.right == null) {
            if (current == root)
                root = null;
            else if (isLeftChild)
                parent.left = null;
            else
                parent.right = null;
        }
        // if one successor
        else if (current.right == null) {
            if (isLeftChild)
                parent.left = current.left;
            else
                parent.right = current.left;
        }
        else if (current.left == null) {
            if (isLeftChild)
                parent.left = current.right;
            else
                parent.right = current.right;
        }
        // if both successors exist
        else {
            TreeNode successor = getSuccessor(current);
            if (current == root)
                root = successor;
            else if (isLeftChild)
                parent.left = successor;
            else
                parent.right = successor;

            successor.left = current.left;
            successor.right = current.right;
        }
        return true;
    }

    private TreeNode getSuccessor(TreeNode node) {
        TreeNode current = node.right;
        TreeNode s = node;
        TreeNode parent = node;
        while (current != null) {
            parent = s;
            s = current;
            current = current.left;
        }
        if (s != node.right) {
            parent.left = s.right;
        }
        return s;
    }

    public void analyze(){
        int[] array = new int[20];

        analyzeNode(root, array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
        int total = 0;
        int unbalanced = 0;
        for (int i = 0; i < array.length; i++) {
            total += array[i];
            if (i > 1)
                unbalanced += array[i];
        }
        System.out.format("Unbalanced %.1f%% trees", ((float) unbalanced / total * 100));
    }

    private int analyzeNode(TreeNode node, int[] count){
        int r;
        int l;

        if (node == null)
            return 0;
        else {
            r = analyzeNode(node.right, count);
            l = analyzeNode(node.left, count);
        }

        if (l != 0 || r != 0)
            count[Math.abs(l - r)] += 1;

        if (r >= l)
            return r + 1;
        else
            return l + 1;
    }
}