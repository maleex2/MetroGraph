package Subway;

import MultiGraph.MultiGraphADT;
import Subway.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;


class edgeNodeTest {

    Driver route = new Driver();
    MetroMapParser parser = new MetroMapParser();
    MultiGraphADT<Station, Line> graph;
    ArrayList<Station> path = new ArrayList<>();

    @Test
    void main() {
        try {
            graph = parser.generateGraphFromFile("bostonmetro.txt");
        } catch (IOException | BadFileException e) {
            System.out.println(e);
            System.exit(1);
        }
        System.out.println("Constructing a graph.");

        int nodesNum = graph.nodeCount();
        int edgesNum = graph.edgeCount();

        System.out.println("Number of nodes: " + nodesNum);
        Assertions.assertEquals(nodesNum, 124);
        System.out.println("Number of edges: " + edgesNum);
        Assertions.assertEquals(edgesNum, 260);
        System.out.println("Adding nodes with IDs \"125\", \"126\" and \"127\"");
        Station station1 = new Station(125,"TestStation1");
        Station station2 = new Station(126,"TestStation2");
        Station station3 = new Station(127,"TestStation3");
        graph.addNode(station1);
        graph.addNode(station2);
        graph.addNode(station3);
        nodesNum = graph.nodeCount();
        System.out.println("Number of nodes is now: " + nodesNum);
        Assertions.assertEquals(nodesNum, 127);

        System.out.println("Connecting the nodes..");
        Line line1 = new Line("TestLine1",125,126);
        Line line2 = new Line("TestLine2",126,127);

        line1.connect(station1,station2);
        line2.connect(station2,station3);
        graph.addEdge(station1,station2,line1);
        graph.addEdge(station2,station3,line2);

        edgesNum = graph.edgeCount();
        System.out.println("Number of edges is now: " + edgesNum);

        // new array to store adjacent nodes in order to get their IDs
        ArrayList<Station> adj = graph.getAdjacentNodes(station2);
        ArrayList<Integer> result = new ArrayList<>();

        for (Station a:adj){
            result.add(a.stationId);
        }

        System.out.println("Adjacent nodes to station with ID \"2\": " + result);

        // used just to check "result"
        ArrayList<Integer> checkAdj = new ArrayList<>();
        checkAdj.add(125);
        checkAdj.add(127);

        Assertions.assertEquals(result, checkAdj);




//        System.out.println("Start station: " + 1);
//        System.out.println("End station: " + 127);
//        ArrayList<Station> path = route.bfs(graph, 1, 127);
//        System.out.println(route.routePlan(route.graph, route));
//        System.out.println(route.routePlan(route.graph, route));












        }


    }