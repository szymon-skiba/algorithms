package pl.edu.pw.ee;

import pl.edu.pw.ee.services.DataStreamFormat;
import pl.edu.pw.ee.utils.BitFormat;
import pl.edu.pw.ee.utils.CharFormat;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class HuffmanTree {

    HuffmanTreeNode root;
    HashMap<Character,String> codeMap;


    public HuffmanTree(){
        root = new HuffmanTreeNode();
    }

    public void add(Character c){

    }

    public void build(Map<Character, Integer> frequencies) {
    }

    public String getCode(Character character) {
        return null;
    }
}
