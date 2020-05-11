package Subway;

import MultiGraph.INode;
import MultiGraph.MultiGraphADT;

import java.io.IOException;
import java.util.*;

public class Driver {
    private MultiGraphADT<Station, Line> graph;
    private Station getStationInput(Scanner scan) throws NoSuchStationException {
        String userInput = scan.nextLine();
        Integer stationId;
        try {
            stationId = Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            if (!userInput.equals("exit")) {
                System.out.println("Please enter a number");
            }
            throw e;
        }
        Station station = (Station) graph.findNode(x -> x.getId() == stationId);
        if (station == null) {
            System.out.println("A station with this ID does not exist");
            throw new NoSuchStationException();
        }
        System.out.println(station.stationName);

        return station;
    }

    public static void main(String[] args) {
        MetroMapParser parser = new MetroMapParser();
        Driver routeFinder = new Driver();
        ArrayList<Station> stations = new ArrayList<>(); // array
        try {
            routeFinder.graph = parser.generateGraphFromFile("bostonmetro.txt");
            //stations = parser.listStations("bostonmetro.txt"); // array
           // System.out.println(stations);
        } catch (IOException | BadFileException e) {
            System.out.println(e);
            System.exit(1);
        }

        Scanner input = new Scanner(System.in);
        String userInput = "";
        while (!userInput.equals("exit")) {
            System.out.println("Please enter your starting station");
            Station start = null;
            try {
                start = routeFinder.getStationInput(input);
            } catch (NumberFormatException | NoSuchStationException e) {
                continue;
            }

            System.out.println("Please enter your final destination");
            Station end = null;
            try {
                end = routeFinder.getStationInput(input);
            } catch (NumberFormatException | NoSuchStationException e) {
                continue;
            }
            System.out.println("Start station: " + start.stationName);
            System.out.println("End station: " + end.stationName);
            ArrayList<Station> route = bfs(routeFinder.graph, start, end);
            /*for (Station i : route) {
                System.out.println(i.stationName);
            }
            */
            System.out.println(routePlan(routeFinder.graph, route));
            System.out.println("Press enter to plan another route or type 'exit' to finish");
            userInput = input.nextLine();
        }
    }

    public static ArrayList<Station> bfs(MultiGraphADT subway, Station start, Station end) {

        ArrayList<Station> path = new ArrayList<>();
        if (start.getId().equals(end.getId())){
            path.add(start);
            return path;
        }
        LinkedList<Station> queue = new LinkedList<>();
        HashMap<Station, Station> hm = new HashMap<>();
        queue.addFirst(start);
        while (!queue.isEmpty()) {
            Station current = queue.removeFirst();
            if (current.getId().equals(end.getId())) {
                do {
                    path.add(current);
                    current = hm.get(current);
                }
                while (!current.getId().equals(start.getId()));
                path.add(start);
                Collections.reverse(path);
                return path;

            } else {
                ArrayList<Station> adj = subway.getAdjacentNodes(current);
                for (Station neighbour : adj) {
                    if (!hm.containsKey(neighbour)) {
                        hm.put(neighbour, current);
                        queue.add(neighbour);
                    }
                }
            }
        }
        return path;
    }

    public static String routePlan(MultiGraphADT subway, ArrayList<Station> path){
        String plan = "At station " + path.get(0).stationName +", take the ";
        Line line = (Line) subway.findEdge(path.get(0), path.get(1));
        plan = plan + line.lineName + " line in the direction of " + path.get(1).stationName +"\n";
        int lastChange = 0;
        int noStops = 0;

        for(int i = 1; i< path.size()-1; i++){
            if(!subway.findEdge(path.get(i), path.get(i+1)).getLabel().equals(line.lineName)){
                if(i+2 < path.size() && subway.findEdge(path.get(i+1), path.get(i+2)).getLabel().equals(line.lineName)){

                }
                else {
                    noStops = i - lastChange;
                    plan = plan + ("Ride " + (noStops) + " stops \n" );
                    lastChange = i;
                    plan += "At station " + path.get(i).stationName + ", take the ";
                    line = (Line) subway.findEdge(path.get(i), path.get(i + 1));
                    plan = plan + line.lineName + " line in the direction of " + path.get(i + 1).stationName + "\n";
                }
            }
        }
        noStops = path.size() - (lastChange +1);
        plan = plan + "Ride " + noStops + " stops to destination, "+ path.get(path.size()-1).stationName;

        return plan;
    }
}


