package com.interviews.ds.graph;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class GraphTraversal {

    public void DFS(Graph<Integer> graph) {
        Set<Long> visited = new HashSet<Long>();
        for (Vertex<Integer> vertex : graph.getAllVertex()) {
            if (!visited.contains(vertex.getId())) {
                DFSUtil(vertex, visited);
            }
        }

    }

    private void DFSUtil(Vertex<Integer> v, Set<Long> visited) {
        visited.add(v.getId());
        System.out.print(v.getId() + " ");
        for (Vertex<Integer> vertex : v.getAdjacentVertexes()) {
            if (!visited.contains(vertex.getId()))
                DFSUtil(vertex, visited);
        }
    }

    public void BFS(Graph<Integer> graph) {
        Set<Long> visited = new HashSet<Long>();
        Queue<Vertex<Integer>> q = new LinkedList<Vertex<Integer>>();

        for (Vertex<Integer> vertex : graph.getAllVertex()) {
            if (!visited.contains(vertex.getId())) {
                q.add(vertex);
                visited.add(vertex.getId());
                while (q.size() != 0) {
                    Vertex<Integer> vq = q.poll();
                    System.out.print(vq.getId() + " ");
                    for (Vertex<Integer> v : vq.getAdjacentVertexes()) {
                        if (!visited.contains(v.getId())) {
                            q.add(v);
                            visited.add(v.getId());
                        }
                    }
                }
            }
        }

    }

}

