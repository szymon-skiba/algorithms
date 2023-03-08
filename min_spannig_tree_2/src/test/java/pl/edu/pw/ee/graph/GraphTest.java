package pl.edu.pw.ee.graph;

import static java.lang.String.format;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.Test;
import static org.junit.Assert.*;

public class GraphTest {

    @Test
    public void shouldCorrectlyReadSmallBasicFile() {
        // given
        String filename = "correct_small_data.txt";
        String pathToFile = getFilePath(filename);

        // when
        Graph graph = new Graph(pathToFile);
        String graphAsStr = graph.toString();
        System.out.println(graphAsStr);

        // then
        String expected = "Edge[src: Node: B, dest: Node: C, weight: 1,000000]\n"
                + "Edge[src: Node: C, dest: Node: D, weight: 1,000000]\n"
                + "Edge[src: Node: A, dest: Node: B, weight: 3,000000]\n"
                + "Edge[src: Node: A, dest: Node: C, weight: 5,000000]\n"
                + "Edge[src: Node: A, dest: Node: D, weight: 7,000000]\n"
                + "Edge[src: Node: D, dest: Node: E, weight: 7,000000]\n";
        assertEquals(expected, graphAsStr);
    }

    @Test
    public void shouldCorrectlyReadSingleEdge() {
        // given
        String filename = "single_edge.txt";
        String pathToFile = getFilePath(filename);

        // when
        Graph graph = new Graph(pathToFile);
        String graphAsStr = graph.toString();
        System.out.println(graphAsStr);

        // then
        String expected = "Edge[src: Node: A, dest: Node: B, weight: 4,000000]\n";
        assertEquals(expected, graphAsStr);
    }

    @Test
    public void shouldCorrectlyReadExtendedSmall() {
        // given
        String filename = "extended_small_data.txt";
        String pathToFile = getFilePath(filename);

        // when
        Graph graph = new Graph(pathToFile);
        String graphAsStr = graph.toString();
        System.out.println(graphAsStr);

        // then
        String expected = "Edge[src: Node: A, dest: Node: C, weight: 3,162278]\n"
                + "Edge[src: Node: C, dest: Node: D, weight: 6,082763]\n"
                + "Edge[src: Node: B, dest: Node: C, weight: 6,324555]\n"
                + "Edge[src: Node: A, dest: Node: D, weight: 6,403124]\n"
                + "Edge[src: Node: A, dest: Node: B, weight: 7,071068]\n"
                + "Edge[src: Node: D, dest: Node: E, weight: 8,246211]\n";
        assertEquals(expected, graphAsStr);
    }

    @Test
    public void shouldCorrectlyReadExtendedRepatedSmall() {
        // given
        String filename = "extended_repeated_small_data.txt";
        String pathToFile = getFilePath(filename);

        // when
        Graph graph = new Graph(pathToFile);
        String graphAsStr = graph.toString();
        System.out.println(graphAsStr);

        // then
        String expected = "Edge[src: Node: C, dest: Node: D, weight: 2,236068]\n"
                + "Edge[src: Node: A, dest: Node: C, weight: 3,162278]\n"
                + "Edge[src: Node: D, dest: Node: E, weight: 3,162278]\n"
                + "Edge[src: Node: A, dest: Node: D, weight: 5,000000]\n"
                + "Edge[src: Node: B, dest: Node: C, weight: 6,324555]\n"
                + "Edge[src: Node: A, dest: Node: B, weight: 7,071068]\n";
        assertEquals(expected, graphAsStr);
    }

    @Test
    public void shouldCorrectlyMixExtendedSmall() {
        // given
        String filename = "mix_extended_small_data.txt";
        String pathToFile = getFilePath(filename);

        // when
        Graph graph = new Graph(pathToFile);
        String graphAsStr = graph.toString();
        System.out.println(graphAsStr);

        // then
        String expected = "Edge[src: Node: A, dest: Node: C, weight: 3,162278]\n"
                + "Edge[src: Node: C, dest: Node: D, weight: 6,082763]\n"
                + "Edge[src: Node: B, dest: Node: C, weight: 6,324555]\n"
                + "Edge[src: Node: A, dest: Node: D, weight: 6,403124]\n"
                + "Edge[src: Node: A, dest: Node: B, weight: 7,071068]\n"
                + "Edge[src: Node: D, dest: Node: E, weight: 8,246211]\n";
        assertEquals(expected, graphAsStr);
    }

    @Test
    public void shouldCorrectlyRepeatedSmall() {
        // given
        String filename = "correct_repeated_small_data.txt";
        String pathToFile = getFilePath(filename);

        // when
        Graph graph = new Graph(pathToFile);
        String graphAsStr = graph.toString();
        System.out.println(graphAsStr);

        // then
        String expected = "Edge[src: Node: A, dest: Node: C, weight: 1,000000]\n"
                + "Edge[src: Node: B, dest: Node: C, weight: 1,000000]\n"
                + "Edge[src: Node: C, dest: Node: D, weight: 1,000000]\n"
                + "Edge[src: Node: E, dest: Node: D, weight: 1,000000]\n"
                + "Edge[src: Node: A, dest: Node: B, weight: 3,000000]\n"
                + "Edge[src: Node: A, dest: Node: D, weight: 7,000000]\n";
        assertEquals(expected, graphAsStr);
    }

    private String getFilePath(String filename) {
        ClassLoader clsLoader = getClass().getClassLoader();

        try {
            Path path = Paths.get(clsLoader.getResource(filename).toURI());

            return path.toFile().getAbsolutePath();

        } catch (URISyntaxException e) {
            throw new RuntimeException(format("Cannot read file from filename: %s.", filename), e);
        }
    }

}
