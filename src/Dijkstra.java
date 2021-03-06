import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class Vertex implements Comparable<Vertex> {
    public final String name;
    public Edge[] adjacencies;
    public double minDistance = Double.POSITIVE_INFINITY;
    public Vertex previous;

    public Vertex(String argName) {
        name = argName;
    }

    public String toString() {
        return name;
    }

    public int compareTo(Vertex other) {
        return Double.compare(minDistance, other.minDistance);
    }

}


class Edge {
    public final Vertex target;
    public final double weight;

    public Edge(Vertex argTarget, double argWeight) {
        target = argTarget;
        weight = argWeight;
    }
}

public class Dijkstra {
    public static void computePaths(Vertex source) {
        source.minDistance = 0.;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
        vertexQueue.add(source);

        while (!vertexQueue.isEmpty()) {
            Vertex u = vertexQueue.poll();

            // Visit each edge exiting u
            for (Edge e : u.adjacencies) {
                Vertex v = e.target;
                double weight = e.weight;
                double distanceThroughU = u.minDistance + weight;
                if (distanceThroughU < v.minDistance) {
                    vertexQueue.remove(v);

                    v.minDistance = distanceThroughU;
                    v.previous = u;
                    vertexQueue.add(v);
                }
            }
        }
    }

    public static List<Vertex> getShortestPathTo(Vertex target) {
        List<Vertex> path = new ArrayList<Vertex>();
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
            path.add(vertex);

        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        Vertex v0 = new Vertex("A");
        Vertex v1 = new Vertex("B");
        Vertex v2 = new Vertex("C");
        Vertex v3 = new Vertex("D");
        Vertex v4 = new Vertex("E");
        Vertex v5 = new Vertex("F");
        Vertex v6 = new Vertex("G");
        Vertex v7 = new Vertex("H");
        Vertex v8 = new Vertex("I");
        Vertex v9 = new Vertex("J");
        Vertex v10 = new Vertex("K");
        Vertex v11 = new Vertex("L");
        v0.adjacencies = new Edge[]{new Edge(v1, 21),
                new Edge(v8, 16)};
        v1.adjacencies = new Edge[]{new Edge(v0, 21),
                new Edge(v2, 11),
                new Edge(v3, 9)};
        v2.adjacencies = new Edge[]{new Edge(v1, 11), new Edge(v7, 14)};
        v3.adjacencies = new Edge[]{new Edge(v2, 9),
                new Edge(v5, 21)};
        v4.adjacencies = new Edge[]{new Edge(v10, 6), new Edge(v9, 9)};
        v5.adjacencies = new Edge[]{
                new Edge(v3, 21),
                new Edge(v4, 14)};
        v6.adjacencies = new Edge[]{new Edge(v7, 5),
                new Edge(v9, 5), new Edge(v8, 7)};
        v7.adjacencies = new Edge[]{new Edge(v2, 14), new Edge(v6, 5)};
        v8.adjacencies = new Edge[]{new Edge(v6, 7), new Edge(v0, 16), new Edge(v11, 11)};
        v9.adjacencies = new Edge[]{new Edge(v6, 5), new Edge(v4, 9)};
        v10.adjacencies = new Edge[]{new Edge(v4, 6), new Edge(v11, 16)};
        v11.adjacencies = new Edge[]{new Edge(v10, 16), new Edge(v8, 11)};

        computePaths(v0);

        System.out.println("Distance to " + v5 + ": " + v5.minDistance);
        List<Vertex> path = getShortestPathTo(v5);
        System.out.println("Path: " + path);
    }

}
