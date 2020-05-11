package Subway;

import MultiGraph.IGraph;
import MultiGraph.INode;
import MultiGraph.MultiGraphADT;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MetroMapParser {
    private MultiGraphADT<Station, Line> graph;

    public MetroMapParser() {
        graph = new MultiGraphADT<>();
    }
    public void addStation(Station station) {
        graph.addNode(station);
    }

    public void addLine(Line line) {
        Station start = graph.findNode(x -> x.getId().equals(line.startId));
        Station end = graph.findNode(x -> x.getId().equals(line.endId));

        graph.addEdge(start, end, line);
    }

    public MultiGraphADT<Station, Line> generateGraphFromFile(String filename)
            throws IOException, BadFileException {
        BufferedReader fileInput = new BufferedReader(new FileReader(filename));
        String line = fileInput.readLine();
        StringTokenizer st;
        Integer stationID;
        String stationName;
        String lineName;
        String outboundID, inboundID;

        ArrayList<Station> stations = new ArrayList<>();
        ArrayList<Line> lines = new ArrayList<>();

        while (line != null) {

            //STUDENT :
            //
            //in this loop, you must collect the information necessary to
            //construct your graph, and you must construct your graph as well.
            //how and where you do this will depend on the design of your graph.
            //


            //StringTokenizer is a java.util Class that can break a string into tokens
            // based on a specified delimiter.  The default delimiter is " \t\n\r\f" which
            // corresponds to the space character, the tab character, the newline character,
            // the carriage-return character and the form-feed character.
            st = new StringTokenizer(line);

            //We want to handle empty lines effectively, we just ignore them!
            if (!st.hasMoreTokens()) {
                line = fileInput.readLine();
                continue;
            }

            //from the grammar, we know that the Station ID is the first token on the line
            stationID = Integer.parseInt(st.nextToken());

            if (!st.hasMoreTokens()) {
                throw new BadFileException("no station name");
            }

            //from the grammar, we know that the Station Name is the second token on the line.
            stationName = st.nextToken();
            if (!st.hasMoreTokens()) {
                throw new BadFileException("station is on no lines");
            }


            while (st.hasMoreTokens()) {
                lineName = st.nextToken();

                if (!st.hasMoreTokens()) {
                    throw new BadFileException("poorly formatted line info");
                }

                outboundID = st.nextToken();

                if (!st.hasMoreTokens()) {
                    throw new BadFileException("poorly formatted adjacent stations");
                }

                inboundID = st.nextToken();
                if (!outboundID.equals("0")) {
                    lines.add(new Line(lineName, stationID, Integer.parseInt(outboundID)));
                }
                if (!inboundID.equals("0")) {
                    lines.add(new Line(lineName, Integer.parseInt(inboundID), stationID));
                }
            }
            stations.add(new Station(stationID, stationName));

            //System.out.println(line);
            line = fileInput.readLine();
        }

        //System.out.println(stations.size());
        //System.out.println(lines.size());

        for (Station station: stations) {
            addStation(station);
        }
        for (Line subwayLine: lines) {
            addLine(subwayLine);
        }

        //System.out.println(graph.nodeCount());

        return graph;
    }


    public ArrayList<Station> listStations(String filename)
            throws IOException, BadFileException {
        BufferedReader fileInput = new BufferedReader(new FileReader(filename));
        String line = fileInput.readLine();
        StringTokenizer st;
        Integer stationID;
        String stationName;
        String lineName;
        String outboundID, inboundID;

        ArrayList<Station> stations = new ArrayList<>();
        ArrayList<Line> lines = new ArrayList<>();

        while (line != null) {

            //STUDENT :
            //
            //in this loop, you must collect the information necessary to
            //construct your graph, and you must construct your graph as well.
            //how and where you do this will depend on the design of your graph.
            //


            //StringTokenizer is a java.util Class that can break a string into tokens
            // based on a specified delimiter.  The default delimiter is " \t\n\r\f" which
            // corresponds to the space character, the tab character, the newline character,
            // the carriage-return character and the form-feed character.
            st = new StringTokenizer(line);

            //We want to handle empty lines effectively, we just ignore them!
            if (!st.hasMoreTokens()) {
                line = fileInput.readLine();
                continue;
            }

            //from the grammar, we know that the Station ID is the first token on the line
            stationID = Integer.parseInt(st.nextToken());

            if (!st.hasMoreTokens()) {
                throw new BadFileException("no station name");
            }

            //from the grammar, we know that the Station Name is the second token on the line.
            stationName = st.nextToken();

            if (!st.hasMoreTokens()) {
                throw new BadFileException("station is on no lines");
            }


            while (st.hasMoreTokens()) {
                lineName = st.nextToken();

                if (!st.hasMoreTokens()) {
                    throw new BadFileException("poorly formatted line info");
                }

                outboundID = st.nextToken();

                if (!st.hasMoreTokens()) {
                    throw new BadFileException("poorly formatted adjacent stations");
                }

                inboundID = st.nextToken();
                if (!outboundID.equals("0")) {
                    lines.add(new Line(lineName, stationID, Integer.parseInt(outboundID)));
                }
                if (!inboundID.equals("0")) {
                    lines.add(new Line(lineName, Integer.parseInt(inboundID), stationID));
                }
            }
            stations.add(new Station(stationID, stationName));

            //System.out.println(line);
            line = fileInput.readLine();
        }

        //System.out.println(stations.size());
        //System.out.println(lines.size());

        for (Station station: stations) {
            addStation(station);
        }
        for (Line subwayLine: lines) {
            addLine(subwayLine);
        }

        //System.out.println(graph.nodeCount());
        return stations;
    }


}
