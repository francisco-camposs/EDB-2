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
                fixUpInsert(newNode);
            } else {
                insert(root.getRight(), newNode);
            }
        } else if (newNode.getValue().compareTo(root.getValue()) < 0){
            if (root.getLeft() == sentinel){
                root.setLeft(newNode);
                newNode.setParent(root);
                fixUpInsert(newNode);
            } else {
                insert(root.getLeft(), newNode);
            }
        }
    }

    private void fixUpInsert(RedBlackNode<T> node) {
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
            fixUpInsert(node);
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

    public void remove(T value){

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
