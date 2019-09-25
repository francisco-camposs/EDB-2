package com.company.arvoreBuscaBinaria;

public class ArvoreBuscaBinaria {

    public class Node{

        private Node left;
        private Node right;
        private int value;


        public Node(int value) {
            this.value = value;
        }

        public void insert(Node node) {
            if (node.value >this.value){
                if (right == null){
                    this.right = node;
                }else {
                    this.right.insert(node);
                }
            } else if (node.value < this.value) {
                if (this.left == null) {
                    this.left = node;
                } else {
                    this.left.insert(node);
                }
            }
        }

        private Node search(int i) {
            if (i == this.value){
                return this;
            }
            if (i < this.value){
                if (left == null){
                    return null;
                } else {
                    return left.search(i);
                }
            }
            else {
                if (right == null){
                    return null;
                } else {
                    return right.search(i);
                }
            }
        }

        public void viewAllNode() {
            if (left != null){
                left.viewAllNode();
            }
            System.out.print("Value: "+value+" ");
            if (right != null){
                right.viewAllNode();
            }
        }

        public boolean isLeaf(){
            return (left == null && right == null);
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }


        @Override
        public String toString() {
            return "Value: "+ value;
        }
    }

    private Node root;

    public ArvoreBuscaBinaria() {
    }

    public ArvoreBuscaBinaria(Node node){
        root = node;
    }

    public ArvoreBuscaBinaria(int value) {
        root = new Node(value);
    }

    public Node search(int i) {
        if (root == null){
            return null;
        }
        return root.search(i);
    }

    public void insert(Node node){
        if (root == null){
            root = node;
            return;
        }
        root.insert(node);
    }

    public void insert(int value){
        Node n = new Node(value);
        insert(n);
    }

    public void viewTree(){
        if (root != null){
            root.viewAllNode();
        }
    };

    private Node maxValue(Node node){
        if(node.right == null){
            return node;
        }
        return maxValue(node.getRight());
    }

    private Node minValue(Node node){
        if(node.left == null){
            return node;
        }
        return minValue(node.getLeft());
    }

    public Node father(int i){
        Node tmp = search(i);
        if (root != null){
            return father(root, tmp);
        } else {
            return null;
        }
    };

    private Node father(Node root, Node node){
        if (root.getValue() > node.getValue()){
            if(root.getLeft().getValue() != node.value){
                return father(root.left, node);
            }
            return root;
        } else if (root.getValue() < node.getValue() ){
            if(root.getRight().getValue() != node.value){
                return father(root.right, node);
            }
            return root;
        } else {
            return null;
        }
    }

    public void remove(int i) {

        remove(root, i);
    }

    private void removeRoot(){
        if (this.root.isLeaf()){
            this.root = null;
        } else if (this.root.left == null){
            this.root = this.root.right;
        } else if (this.root.right == null){
            this.root = this.root.left;
        } else {
            Node aux1;
            aux1 = this.root.left;
            maxValue(aux1).right = this.root.right;
            this.root = aux1;
        }
        return;
    }

    private void removeChild(Node Father, Node root){
        if (root.isLeaf()){
            if (Father.right == root){
                Father.right = null;
            } else {
                Father.left = null;
            }
        } else if(Father.left == null){
            Father.right = root.right;
        } else if (Father.right == null){
            Father.left = root.left;
        } else {
            ArvoreBuscaBinaria tree = new ArvoreBuscaBinaria(root);
            tree.removeRoot();
            if (Father.left == root){
                Father.left = tree.root;
            } if (Father.right == root){
                Father.right = tree.root;
            }
        }
    }

    private void remove(Node root, int i) {
        Node Father;
        if (this.root == null){
            return;
        } if (root == null){
            System.out.println("Value not found in tree.");
        } else if (root.getValue() < i){
            remove(root.getRight(), i);
        } else if (root.getValue() > i){
            remove(root.getLeft(),i);
        } else {
            Father = father(i);
            if (Father == null){
                removeRoot();
                return;
            } else {
                removeChild(Father, root);
                return;
            }
        }
    }

    public Node getRoot(){
        return root;
    }
}