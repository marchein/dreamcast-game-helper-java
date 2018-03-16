package dreamcast.model;

import java.util.LinkedList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model {

    private LinkedList<Game> gamesList = new LinkedList<>();

    private LinkedList<Game> japGamesList = new LinkedList<>();

    private LinkedList<Game> usGamesList = new LinkedList<>();

    private LinkedList<Game> palGamesList = new LinkedList<>();

    public void addGame(Game g) {
        gamesList.add(g);

        if (g.hasJapRelease()) {
            japGamesList.add(g);
        }

        if (g.hasPalRelease()) {
            palGamesList.add(g);
        }

        if (g.hasUsRelease()) {
            usGamesList.add(g);
        }

        // System.out.println("Added: " + g);

        // System.out.println(g.getMinPlayers() + " - " + g.getMaxPlayers());
    }

    public ObservableList<Game> getList(String list) {
        if (list.equals("jap")) {
            return FXCollections.observableList(japGamesList);
        } else if (list.equals("pal")) {
            return FXCollections.observableList(palGamesList);
        } else if (list.equals("us")) {
            return FXCollections.observableList(usGamesList);
        } else {
            return FXCollections.observableList(gamesList);
        }
    }

    public ObservableList<String> getRegions() {
        LinkedList<String> regions = new LinkedList<String>();
        regions.add("Alle Regionen");
        regions.add("Europe");
        regions.add("Japan");
        regions.add("USA");
        return FXCollections.observableList(regions);
    }

    // public ObservableList<String> getMaxPLayers() {
    // LinkedList<String> players = new LinkedList<String>();
    // players.add("Anzahl egal");
    // players.add("1");
    // players.add("2");
    // players.add("3");
    // players.add("4");
    // players.add("5");
    // return FXCollections.observableList(players);
    // }

}
