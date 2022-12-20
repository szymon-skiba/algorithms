package pl.edu.pw.ee.data_structures;

import pl.edu.pw.ee.services.HuffmanTreeNodeSpecs;

import static pl.edu.pw.ee.services.HuffmanTreeNodeSpecs.Direction.*;
import static pl.edu.pw.ee.services.HuffmanTreeNodeSpecs.Type.INTERNAL;

public class HuffmanTreeNode implements Comparable<HuffmanTreeNode> {
    private final int value;
    private char symbol;
    private HuffmanTreeNodeSpecs.Type type = INTERNAL;
    private HuffmanTreeNodeSpecs.Direction code;
    private HuffmanTreeNode left;
    private HuffmanTreeNode right;


    public HuffmanTreeNode(int value) {
        if (value < 1) {
            throw new IllegalArgumentException("Node cant have less then one char");
        }
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public HuffmanTreeNode(int value, char c, HuffmanTreeNodeSpecs.Type type) {
        if (value < 1) {
            throw new IllegalArgumentException("Node cant have less then one char");
        }
        this.symbol = c;
        this.value = value;
        this.type = type;
        this.left = null;
        this.right = null;
    }

    public HuffmanTreeNodeSpecs.Type getType() {
        return type;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public int compareTo(HuffmanTreeNode o) {
        return compare(value, o.getValue());
    }

    private int compare(int x, int y) {
        return (x < y) ? -1 : ((x == y) ? 0 : 1);
    }

    public void setLeft(HuffmanTreeNode left) {
        if (left != null) {
            left.setDirection(LEFT_NODE);
        }
        this.left = left;
    }

    public void setRight(HuffmanTreeNode right) {
        if (right != null) {
            right.setDirection(RIGHT_NODE);
        }
        this.right = right;
    }

    public void setType(HuffmanTreeNodeSpecs.Type type){
        this.type = type;
    }

    public char getCharacter() {
        if (type == INTERNAL) {
            throw new IllegalCallerException("Illegal call: internal nodes don't have symbols.");
        }
        return symbol;
    }

    public void setDirection(HuffmanTreeNodeSpecs.Direction code) {
        this.code = code;
    }

    public HuffmanTreeNode getLeft() {
        return left;
    }

    public HuffmanTreeNode getRight() {
        return right;
    }

    public HuffmanTreeNodeSpecs.Direction getCode() {
        return code;
    }
}
