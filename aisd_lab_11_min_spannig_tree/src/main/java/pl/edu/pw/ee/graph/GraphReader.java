package pl.edu.pw.ee.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.String.format;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import pl.edu.pw.ee.exception.ReadingGraphFromFileException;

public class GraphReader {

    private final String lineRegex = "^[A-Za-z]+ [A-Za-z]+ \\d+$";
    private final String lineRegexV2 = "^[A-Za-z]+ [A-Za-z]+";
    private final String lineRegexV3 = "^[A-Za-z]+ [-]?\\d+ [-]?\\d+$";
    private final String lineRegexV4 = "^#";
    private final String lineRegexV5 = "^$";
    private final Pattern pattern = Pattern.compile(lineRegex);
    private final Pattern patternV2 = Pattern.compile(lineRegexV2);
    private final Pattern patternV3 = Pattern.compile(lineRegexV3);
    private final Pattern patternV4 = Pattern.compile(lineRegexV4);
    private final Pattern patternV5 = Pattern.compile(lineRegexV5);

    private final String fileToPath;
    private final List<Edge> edges;
    private HashMap<String, int[]> points;

    private int mode = 0;

    public GraphReader(String fileToPath) {
        validateData(fileToPath);

        this.fileToPath = fileToPath;
        this.edges = new ArrayList<>();
        this.points = new HashMap<>();

        readAndSort();
    }

    public List<Edge> getEdges() {
        return edges;
    }

    private void validateData(String fileToPath) {
        if (fileToPath == null) {
            throw new IllegalArgumentException("File to path arg cannot be null!");
        }
    }

    private void readAndSort() {
        readGraphFromFile();
        sortEdgesByPriority();
    }

    private void readGraphFromFile() {
        Edge edge;
        int i = 1;

        try ( BufferedReader reader = new BufferedReader(new FileReader(fileToPath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                edge = parseToEdge(line, i);
                if (edge != null) {
                    addEdge(edge);
                }
                i++;
            }

            if (mode == 1) {
                recalculateEdges();
            }
        } catch (IOException e) {
            throw new ReadingGraphFromFileException("Cannot read file from path!", e);
        }
    }

    private void recalculateEdges() {
        int i = 0;
        for (Edge edge : edges) {
            Node start = edge.getSrc();
            Node end = edge.getDest();
            double weight;

            if (points.containsKey(end.toString()) && points.containsKey(start.toString())) {
                int xe = points.get(end.toString())[0];
                int ye = points.get(end.toString())[1];

                int xs = points.get(start.toString())[0];
                int ys = points.get(start.toString())[1];

                weight = Math.sqrt(Math.pow((xe - xs), 2) + Math.pow((ye - ys), 2));

                edges.set(i, new Edge(start, end, weight));
            }

            i++;
        }
    }

    private void addEdge(Edge newEdge) {
        int i = 0;
        int found = 0;
        for (Edge edge : edges) {
            if (edge.getSrc() == newEdge.getSrc() && edge.getDest() == newEdge.getDest()) {
                edges.set(i, newEdge);
                found = 1;
                break;
            } else if (edge.getSrc().toString().equals(newEdge.getDest().toString()) && edge.getDest().toString().equals(newEdge.getSrc().toString())) {
                edges.set(i, newEdge);
                found = 1;
                break;
            }
            i++;
        }

        if (found == 0) {
            edges.add(newEdge);
        }
    }

    private Edge parseToEdge(String line, int lineNumber) {
        String[] args;
        Node start;
        Node end;
        double weight;

        line = line.trim();
        Matcher matcher = pattern.matcher(line);
        Matcher matcherV2 = patternV2.matcher(line);
        Matcher matcherV3 = patternV3.matcher(line);
        Matcher matcherV4 = patternV4.matcher(line);
        Matcher matcherV5 = patternV5.matcher(line);
        if (matcherV4.find() || matcherV5.find()) {
            return null;
        } else if (matcher.find()) {
            System.out.println(line);
            args = line.split(" ");
            start = new Node(args[0]);
            end = new Node(args[1]);
            weight = Double.parseDouble(args[2]);

            return new Edge(start, end, weight);

        } else if (matcherV2.find()) {
            mode = 1;
            args = line.split(" ");
            start = new Node(args[0]);
            end = new Node(args[1]);
            weight = 1;

            return new Edge(start, end, weight);

        } else if (matcherV3.find()) {
            mode = 1;
            args = line.split(" ");
            start = new Node(args[0]);
            int x = Integer.parseInt(args[1]);
            int y = Integer.parseInt(args[2]);
            int[] point = {x, y};
            points.put(start.toString(), point);

            return null;

        } else {
            throw new ReadingGraphFromFileException(
                    format("Cannot correctly parse line %d from file", lineNumber));
        }
    }

    private void sortEdgesByPriority() {
        EdgeWeightComparator weightComparator = new EdgeWeightComparator();

        Collections.sort(edges, weightComparator);
    }

}
