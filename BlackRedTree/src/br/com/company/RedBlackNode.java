package br.com.company;

public class RedBlackNode<T extends Comparable> {

    private T value;
    private RedBlackNode<T> left;
    private RedBlackNode<T> right;
    private RedBlackNode<T> parent;
    private RedOrBlack color;

    public RedBlackNode(T value) {
        this.value = value;
        color = RedOrBlack.RED;
    }

    public RedBlackNode(T value, RedOrBlack color) {
        this.value = value;
        this.color = color;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public RedBlackNode<T> getLeft() {
        return left;
    }

    public void setLeft(RedBlackNode<T> left) {
        this.left = left;
    }

    public RedBlackNode<T> getRight() {
        return right;
    }

    public void setRight(RedBlackNode<T> right) {
        this.right = right;
    }

    public RedBlackNode<T> getParent() {
        return parent;
    }

    public void setParent(RedBlackNode<T> parent) {
        this.parent = parent;
    }

    public RedOrBlack getColor() {
        return color;
    }

    public void setColor(RedOrBlack color) {
        this.color = color;
    }
}
