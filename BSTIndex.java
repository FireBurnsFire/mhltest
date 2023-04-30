public class BSTIndex {

    private Node root;

    private class Node {
        private MovieInfo val;
        private Node left;
        private Node right;

        public Node(MovieInfo val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }

        public String key() {
            return this.val.getShortName().toLowerCase();
        }
    }
    
    public static class MovieInfo{
        public String shortName;   //short or simplified name, e.g., Tom Hanks.
        public String fullName;    //full name, e.g., Hanks, Thomas III.
        public int ID;             //integer ID.

        public MovieInfo(int id, String shortName, String fullName) {
           this.ID = id;
           this.shortName = shortName;
           this.fullName = fullName;
        }
        
        public String getShortName() {
        	return this.shortName;
        }
    }

    public BSTIndex() {
        root = null;
    }

    public MovieInfo findExact(String key) {
        key = key.toLowerCase();
        Node node = root;
        while (node != null) {
            int cmp = key.compareTo(node.key());
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                return node.val;
            }
        }
        return null;
    }

    public MovieInfo findPrefix(String prefix) {
        prefix = prefix.toLowerCase();
        Node node = root;
        while (node != null) {
            if (node.key().startsWith(prefix)) {
                return node.val;
            }
            if (prefix.compareTo(node.key()) < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return null;
    }

    public void insert(MovieInfo data) {
        root = insert(root, data);
    }

    private Node insert(Node node, MovieInfo data) {
        if (node == null) {
            return new Node(data);
        }
        
        int cmp = data.getShortName().compareTo(node.val.getShortName());
        
        if (cmp < 0) {
            node.left = insert(node.left, data);
        } 
        else if (cmp > 0) {
            node.right = insert(node.right, data);
        } 
        else {
            node.val = data;
        }
        return node;
    }
}

