package dreamcast.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Game {

    private SimpleStringProperty title;

    private SimpleStringProperty genre;

    private SimpleStringProperty developer;

    private SimpleStringProperty publisher;

    private SimpleStringProperty exclusives;

    private SimpleStringProperty jap;

    private SimpleStringProperty na;

    private SimpleStringProperty pal;

    private SimpleStringProperty players;

    private SimpleIntegerProperty minPlayers;

    private SimpleIntegerProperty maxPlayers;

    public Game getGame() {
        return this;
    }

    @Override
    public String toString() {
        return title.getValue();
    }

    public Game(String title, String genre, String developer, String publisher, String exclusives, String jap, String na, String pal, String players) {
        this.title = new SimpleStringProperty(title);
        this.genre = new SimpleStringProperty(genre);
        this.developer = new SimpleStringProperty(developer);
        this.publisher = new SimpleStringProperty(publisher);
        this.exclusives = new SimpleStringProperty(exclusives);
        this.jap = new SimpleStringProperty(jap);
        this.na = new SimpleStringProperty(na);
        this.pal = new SimpleStringProperty(pal);
        this.players = new SimpleStringProperty(players);

        if (title.equals("Sega Smash Pack Vol. 1")) {
            this.genre = new SimpleStringProperty("Miscellaneous, Compilation");
            this.players = new SimpleStringProperty("1");
        }

        if (title.equals("Speed Devils: Online Racing")) {
            this.players = new SimpleStringProperty("1-5");
        }

        reformatPlayers();
    }

    public void reformatPlayers() {
        if (players.getValue().length() > 0) {
            String[] playersSplit = players.getValue().split("-");
            this.minPlayers = new SimpleIntegerProperty(Integer.parseInt(playersSplit[0]));
            if (playersSplit.length > 1) {
                this.maxPlayers = new SimpleIntegerProperty(Integer.parseInt(playersSplit[1]));
            } else {
                this.maxPlayers = minPlayers;
            }
        }
    }

    public SimpleStringProperty getTitle() {
        return title;
    }

    public SimpleStringProperty getGenre() {
        return genre;
    }

    public SimpleStringProperty getDeveloper() {
        return developer;
    }

    public SimpleStringProperty getNumberOfPlayers() {
        return players;
    }

    public boolean hasJapRelease() {
        return !jap.getValue().equals("Unreleased");
    }

    public boolean hasPalRelease() {
        return !pal.getValue().equals("Unreleased");
    }

    public boolean hasUsRelease() {
        return !na.getValue().equals("Unreleased");
    }

    public int getMinPlayers() {
        return minPlayers.getValue();
    }

    public int getMaxPlayers() {
        return maxPlayers.getValue();
    }
}
