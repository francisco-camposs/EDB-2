package br.com.waldson.aula15;

public class AvlTree<Value extends Indexable> {
    private Node<Value> root;

    public void insert(Value value) {
        root = insert(new Node<Value>(value), root);
    }
    private Node<Value> insert(Node<Value> node, Node<Value> parent) {
        if (parent == null){
            return node;
        } if (parent.getValue().getKey() == node.getValue().getKey()){
            parent.setValue(node.getValue());
            return parent;
        }
        if (node.getValue().getKey() < parent.getValue().getKey()){
            parent.setLeft(insert(node,parent.getLeft()));
        } else {
            parent.setRight(insert(node,parent.getRight()));
        }
        return balance(parent);
    }

    private Node<Value> balance(Node<Value> node) {
        int balanceFactor = node.getBalanceFactor();
        if ( balanceFactor >= -1 && balanceFactor <=1){
            return node;
        } if (balanceFactor > 1){
            if (node.getLeft().getBalanceFactor() > 0){
                //LL
                return rotateRight(node);
            } else {
                //LR
                node.setLeft(rotateLeft(node.getLeft()));
                return rotateRight(node);
            }
        } else {
            if (node.getRight().getBalanceFactor() < 0){
                //LL
                return rotateLeft(node);
            } else {
                //LR
                node.setRight(rotateRight(node.getRight()));
                return rotateLeft(node);

            }
        }
    }

    public boolean isBalanced(){
        if (root == null){
            return true;
        }
        return Math.abs(root.getBalanceFactor()) <= 1;
    }

    private Node<Value> rotateLeft(Node<Value> node) {
        Node<Value> newRoot = node.getRight();
        node.setRight(newRoot.getLeft());
        newRoot.setLeft(node);
        return newRoot;
    }

    private Node<Value> rotateRight(Node<Value> node) {
        Node<Value> newRoot = node.getLeft();
        node.setLeft(newRoot.getRight());
        newRoot.setRight(node);
        return newRoot;
    }

    public void remove() {

    }

    public Value search(int key) {

        return null;
    }
}