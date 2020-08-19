package org.jackJew.interview.algo.graph;

public class Dijkstra2 {

    public int[] calculate(int[][] matrix, int from) {
        final int length = matrix.length;
        boolean[] visited = new boolean[length];
        visited[from] = true;

        int[] distances = new int[length];
        for (int i = 0; i < length; i++) {
            distances[i] = matrix[from][i];
        }
        distances[from] = 0;

        int[] pre = new int[length];

        for (int i = 1; i< length; i++) {
            int k = 0;
            int shortest = Integer.MAX_VALUE;
            for (int j = 0; j < length; j++) {
                if (!visited[j] && distances[j] < shortest) {
                    shortest = distances[j];
                    k = j;
                }
            }
            visited[k] = true;
            for (int j = 0; j < length; j++) {
                if (!visited[j] && matrix[k][j] != Integer.MAX_VALUE) {
                    int distance = shortest + matrix[k][j];
                    if (distance < distances[j]) {
                        distances[j] = distance;
                        pre[j] = k;
                    }
                }
            }
        }
        return distances;
    }
}
