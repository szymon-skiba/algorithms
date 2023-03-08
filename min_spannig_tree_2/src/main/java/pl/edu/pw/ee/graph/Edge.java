package pl.edu.pw.ee.graph;

import static java.lang.String.format;

public class Edge {

    private final Node src;
    private final Node dest;
    private final double weight;

    public Edge(Node src, Node dest, double weight) {
        validateInput(src, dest);

        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    public Node getSrc() {
        return src;
    }

    public Node getDest() {
        return dest;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return format("Edge[src: %s, dest: %s, weight: %f]", src, dest, weight);
    }

    private void validateInput(Node start, Node end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Input params (start or end) cannot be null!");
        }
    }

}
