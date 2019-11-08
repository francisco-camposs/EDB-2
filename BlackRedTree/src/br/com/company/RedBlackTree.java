package br.com.company;

public class RedBlackTree <T extends Comparable>{

    private RedBlackNode<T> root;
    private RedBlackNode<T> sentinel;


    public RedBlackTree() {
        sentinel = new RedBlackNode<T>(null);
        sentinel.setRight(sentinel);
        sentinel.setParent(sentinel);
        sentinel.setLeft(sentinel);
        sentinel.setColor(RedOrBlack.BLACK);
        root =sentinel;
    }

    public void insert(T value){
        RedBlackNode<T> node = new RedBlackNode<T>(value);
        node.setRight(sentinel);
        node.setParent(sentinel);
        node.setLeft(sentinel);
        insert(root, node);
    }

    public void insert(RedBlackNode<T> newNode){
        newNode.setRight(sentinel);
        newNode.setParent(sentinel);
        newNode.setLeft(sentinel);
        insert(root, newNode);
    }

    private void insert(RedBlackNode<T> root, RedBlackNode<T> newNode ){
        if (this.root == sentinel){
            this.root = newNode;
            newNode.setParent(sentinel);
            newNode.setColor(RedOrBlack.BLACK);
        } else if (newNode.getValue().compareTo(root.getValue()) > 0){
            if (root.getRight() == sentinel){
                root.setRight(newNode);
                newNode.setParent(root);
                insertFixUp(newNode);
            } else {
                insert(root.getRight(), newNode);
            }
        } else if (newNode.getValue().compareTo(root.getValue()) < 0){
            if (root.getLeft() == sentinel){
                root.setLeft(newNode);
                newNode.setParent(root);
                insertFixUp(newNode);
            } else {
                insert(root.getLeft(), newNode);
            }
        }
    }

    private void insertFixUp(RedBlackNode<T> node) {
        if (node.getParent().getColor() == RedOrBlack.RED){
            if (node.getParent() == node.getParent().getParent().getLeft()){
                RedBlackNode<T> tmp = node.getParent().getParent().getRight();
                if (tmp.getColor() == RedOrBlack.RED){
                    node.getParent().setColor(RedOrBlack.BLACK);
                    tmp.setColor(RedOrBlack.BLACK);
                    node.getParent().getParent().setColor(RedOrBlack.RED);
                    node = node.getParent().getParent();
                } else {
                    if(node == node.getParent().getRight()){
                        node = node.getParent();
                        leftRotation(node);
                    }
                    node.getParent().setColor(RedOrBlack.BLACK);
                    node.getParent().getParent().setColor(RedOrBlack.RED);
                    rightRotation(node.getParent().getParent());
                }
            }
            else {
                RedBlackNode<T> tmp = node.getParent().getParent().getLeft();
                if (tmp.getColor() == RedOrBlack.RED){
                    node.getParent().setColor(RedOrBlack.BLACK);
                    if (tmp != sentinel){
                        tmp.setColor(RedOrBlack.BLACK);
                    }
                    node.getParent().getParent().setColor(RedOrBlack.RED);
                    node = node.getParent().getParent();
                } else {
                    if(node == node.getParent().getLeft()){
                        node = node.getParent();
                        rightRotation(node);
                    }
                    node.getParent().setColor(RedOrBlack.BLACK);
                    node.getParent().getParent().setColor(RedOrBlack.RED);
                    leftRotation(node.getParent().getParent());
                }
            }
            this.root.setColor(RedOrBlack.BLACK);
            insertFixUp(node);
        }
    }

    private void leftRotation(RedBlackNode<T> root){
        RedBlackNode<T> tmp = root.getRight();
        root.setRight(tmp.getLeft());

        if (root.getRight() != sentinel){
            root.getRight().setParent(root);
        }

        tmp.setParent(root.getParent());

        if (root.getParent() == sentinel){
            this.root = tmp;
        }
        else if(root == root.getParent().getLeft()){
            root.getParent().setLeft(tmp);
        } else{
            root.getParent().setRight(tmp);
        }
        tmp.setLeft(root);
        root.setParent(tmp);
    }

    private void rightRotation(RedBlackNode<T> root){
        RedBlackNode<T> tmp = root.getLeft();
        root.setLeft(tmp.getRight());;

       if (tmp.getRight() != sentinel){
           tmp.getRight().setParent(root);
       }

       tmp.setParent(root.getParent());

       if (root.getParent() == sentinel){
           this.root = tmp;
       } else if (root == root.getParent().getLeft()){
           root.getParent().setLeft(tmp);
       } else {
           root.getParent().setRight(tmp);
       }

       tmp.setRight(root);
       root.setParent(tmp);
    }

    public void delete(T value){
        delete(search(value));
    }

    private void delete(RedBlackNode<T> node) {
        if (node == sentinel){
            return;
        }

        RedBlackNode<T> tmp = node;
        RedOrBlack originalColorTmp = node.getColor();
        RedBlackNode<T> aux;

        if (node.getLeft() == sentinel){
            aux = node.getRight();
        } else if(node.getRight() == sentinel){
            aux = node.getLeft();
            transplant(node, node.getLeft());
        } else {
            tmp = minimum(node.getRight());
            originalColorTmp = tmp.getColor();
            aux = tmp.getRight();
            if (tmp.getParent() == node){
                aux.setParent(tmp);
            } else {
                transplant(tmp, tmp.getRight());
                tmp.setRight(node.getRight());
                tmp.getRight().setParent(tmp);
            }
            transplant(node, tmp);
            tmp.setLeft(node.getLeft());
            tmp.getLeft().setParent(tmp);
            tmp.setColor(node.getColor());
        }

        if (originalColorTmp == RedOrBlack.BLACK){
            deleteFixUp(aux);
        }

    }

    private void deleteFixUp(RedBlackNode<T> node) {
        while (node != this.root && node.getColor() == RedOrBlack.BLACK) {
            if (node == node.getParent().getLeft()) {
                RedBlackNode<T> aux = node.getParent().getRight();
                if (aux.getColor() == RedOrBlack.RED) {
                    aux.setColor(RedOrBlack.BLACK);
                    node.getParent().setColor(RedOrBlack.RED);
                    leftRotation(node.getParent());
                    aux = node.getParent().getRight();
                }
                if (aux.getLeft().getColor() == RedOrBlack.BLACK && aux.getRight().getColor() == RedOrBlack.BLACK) {
                    aux.setColor(RedOrBlack.RED);
                    node = node.getParent();
                } else {
                    if (aux.getRight().getColor() == RedOrBlack.BLACK) {
                        aux.getLeft().setColor(RedOrBlack.BLACK);
                        aux.setColor(RedOrBlack.RED);
                        rightRotation(aux);
                        aux = node.getParent().getRight();
                    }
                    aux.setColor(node.getParent().getColor());
                    node.getParent().setColor(RedOrBlack.BLACK);
                    aux.getRight().setColor(RedOrBlack.BLACK);
                    leftRotation(node.getParent());
                    node = this.root;
                }
            } else {
                if (node == node.getParent().getRight()) {
                    RedBlackNode<T> aux = node.getParent().getLeft();
                    if (aux.getColor() == RedOrBlack.RED) {
                        aux.setColor(RedOrBlack.BLACK);
                        node.getParent().setColor(RedOrBlack.RED);
                        rightRotation(node.getParent());
                        aux = node.getParent().getLeft();
                    }
                    if (aux.getRight().getColor() == RedOrBlack.BLACK && aux.getLeft().getColor() == RedOrBlack.BLACK) {
                        aux.setColor(RedOrBlack.RED);
                        node = node.getParent();
                    } else {
                        if (aux.getLeft().getColor() == RedOrBlack.BLACK) {
                            aux.getRight().setColor(RedOrBlack.BLACK);
                            aux.setColor(RedOrBlack.RED);
                            leftRotation(aux);
                            aux = node.getParent().getLeft();
                        }
                        aux.setColor(node.getParent().getColor());
                        node.getParent().setColor(RedOrBlack.BLACK);
                        aux.getLeft().setColor(RedOrBlack.BLACK);
                        rightRotation(node.getParent());
                        node = this.root;
                    }
                }
            }
        }
        node.setColor(RedOrBlack.BLACK);
    }


    public RedBlackNode<T> minimum(){
        return minimum(this.root);
    }

    private RedBlackNode<T> minimum(RedBlackNode<T> root) {
        if (root.getLeft() != sentinel){
            return minimum(root.getLeft());
        } else {
            return root;
        }
    }

    public void transplant(RedBlackNode<T> node, RedBlackNode<T> newNode ){
        if (node.getParent() == sentinel){
            this.root = newNode;
        } else if (node == node.getParent().getLeft()){
            node.getParent().setLeft(newNode);
        } else{
            node.getParent().setRight(newNode);
        }
        node.setParent(newNode.getParent());
    }

    public RedBlackNode<T> search(T value){
        return search(new RedBlackNode<T>(value));
    }

    private RedBlackNode<T> search(RedBlackNode<T> value){
        return search(this.root, value);
    }

    private RedBlackNode<T> search(RedBlackNode<T> root, RedBlackNode<T> node){
        if (root != sentinel && root.getValue().compareTo(node) > 0){
            return search(root.getLeft(), node);
        } else if (root != sentinel && root.getValue().compareTo(node) < 0){
                return search(root.getRight(), node);
        } else if (root != sentinel && root.getValue().compareTo(node) == 0){
            return root;
        } else {
            return sentinel;
        }
    }

    public void printAll(){
        printAll(this.root);
    }

    private void printAll(RedBlackNode<T> root){
        if (root.getLeft() != sentinel){
            printAll(root.getLeft());
        }
        System.out.println(root.getColor()+"\n"+root.getValue());
        if (root.getRight() != sentinel){
            printAll(root.getRight());
        }
    }
}
