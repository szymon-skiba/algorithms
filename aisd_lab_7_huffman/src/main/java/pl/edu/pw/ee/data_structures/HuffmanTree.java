package pl.edu.pw.ee.data_structures;

import pl.edu.pw.ee.services.HuffmanTreeNodeSpecs;

import java.util.*;

import static pl.edu.pw.ee.services.HuffmanTreeNodeSpecs.Direction.*;
import static pl.edu.pw.ee.services.HuffmanTreeNodeSpecs.Type.LEAF;

public class HuffmanTree {

    private HuffmanTreeNode root;
    private final Map<Character, Integer> frequencies;
    private final int CHAR_FOUND = 1;
    private final int CHAR_NOT_FOUND = -1;


    public HuffmanTree(Map<Character, Integer> frequencies) {
        this.frequencies = frequencies;
        this.root = null;
        build();
    }


    private void build() {
        PriorityQueue<HuffmanTreeNode> q;
        try {
            q = createPriorityQue();
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return;
        }

        HuffmanTreeNode smallestOne;
        HuffmanTreeNode smallestTwo;

        while ((smallestOne = q.poll()) != null) {
            if ((smallestTwo = q.poll()) != null) {
                HuffmanTreeNode node = new HuffmanTreeNode(smallestOne.getValue() + smallestTwo.getValue());
                node.setLeft(smallestOne);
                node.setRight(smallestTwo);
                q.add(node);
            } else {
                if(frequencies.size()==1){
                    root = new HuffmanTreeNode(smallestOne.getValue());
                    root.setType(HuffmanTreeNodeSpecs.Type.ROOT);
                    root.setRight(smallestOne);
                }
                root = smallestOne;
                root.setDirection(ROOT);
                root.setType(HuffmanTreeNodeSpecs.Type.ROOT);
                break;
            }
        }

    }

    private PriorityQueue<HuffmanTreeNode> createPriorityQue() throws IllegalArgumentException {
        PriorityQueue<HuffmanTreeNode> q = new PriorityQueue<>();
        for (Character c : frequencies.keySet()) {
            q.add(new HuffmanTreeNode(frequencies.get(c), c, LEAF));
        }
        return q;
    }


    public ArrayList<Integer> getCode(char character) {
        ArrayList<Integer> path = new ArrayList<>();

        if (findChar(root, character, path) == CHAR_NOT_FOUND) {
            return null;
        }

        path.remove(0);
        return path;
    }

    public int findChar(HuffmanTreeNode node, char character, ArrayList<Integer> path) {

        if (node == null) {
            return CHAR_NOT_FOUND;
        }

        if (node.getType() == LEAF) {
            if (node.getCharacter() == character) {
                path.add(0, node.getCode() == LEFT_NODE ? 0 : 1);
                return CHAR_FOUND;
            }
        }

        if (findChar(node.getRight(), character, path) == CHAR_FOUND) {
            path.add(0, node.getCode() == LEFT_NODE ? 0 : 1);
            return CHAR_FOUND;
        } else if (findChar(node.getLeft(), character, path) == CHAR_FOUND) {
            path.add(0, node.getCode() == LEFT_NODE ? 0 : 1);
            return CHAR_FOUND;
        } else {
            return CHAR_NOT_FOUND;
        }

    }

    public HuffmanTreeNode getRoot() {
        return root;
    }
}
