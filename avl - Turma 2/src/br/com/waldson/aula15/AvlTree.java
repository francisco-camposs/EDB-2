package br.com.waldson.aula15;

public class AvlTree<Value extends Indexable> {
    private Node<Value> root;

    public void remove(int key) {
        root = remove(root, key);
    }

    private Node<Value> remove(Node<Value> root, int key) {
        if (root == null){
            return root;
        }
        if (root.getValue().getKey() == key) {
            if (root.getLeft() == null) {
                return root.getRight();
            }
            if (root.getRight() == null) {
                return root.getLeft();
            }
            int balanceFactor = root.getBalanceFactor();
            if (balanceFactor >= 0) {
                // pendendo do lado esquerdo
                Value max = getMaxValue(root.getLeft());
                root.setValue(max);
                root.setLeft(remove(root.getLeft(), max.getKey()));
                return root;
            } else {
                Value min = getMinValue(root.getRight());
                root.setValue(min);
                root.setRight(remove(root.getRight(), min.getKey()));
            }
            return root;
        }

        if (key < root.getValue().getKey()){
            root.setLeft(remove(root.getLeft(), key));
        } else {
            root.setRight(remove(root.getRight(), key));
        }
        return root;
    }

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

    public Value search(int key) {
        return search(root, key);
    }

    private Value search(Node<Value> root, int key) {
        if (root == null){
            return null;
        }
        if (root.getValue().getKey() == key) {
            return root.getValue();
        } else if(key < root.getValue().getKey()){
            return search(root.getLeft(), key);
        }
        return search(root.getRight(), key);
    }

    private Value getMinValue(){
        return getMinValue(root);
    }

    private Value getMinValue(Node<Value> root) {
        if (root == null){
            return null;
        }
        if (root.getLeft() == null){
            return root.getValue();
        }
        return getMinValue(root.getLeft());
    }

    private Value getMaxValue() {
        return getMaxValue(root);
    }

    private Value getMaxValue(Node<Value> root) {
        if (root == null){
            return null;
        }
        if (root.getRight() == null){
            return root.getValue();
        }
        return getMinValue(root.getRight());
    }
}
