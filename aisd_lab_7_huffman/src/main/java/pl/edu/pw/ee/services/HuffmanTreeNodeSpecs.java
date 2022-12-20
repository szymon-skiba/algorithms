package pl.edu.pw.ee.services;

public enum HuffmanTreeNodeSpecs {
    ;

    public enum Type {
        INTERNAL,
        LEAF,
        ROOT;
    }

    public enum Direction{

        LEFT_NODE(0),
        RIGHT_NODE(1),
        ROOT(1);

        Direction(int i) {
        }

    }


}
