package dreamcast.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import dreamcast.presenter.Presenter;

public class DataInitializer {

    private Connection c = null;

    private Statement stmt = null;

    public DataInitializer(Presenter presenter) {

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/dreamcast/model/games.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM games;");

            while (rs.next()) {
                String title = rs.getString("title");
                String genre = rs.getString("genre");
                String developer = rs.getString("developer");
                String publisher = rs.getString("publisher");
                String exclusives = rs.getString("exclusives");
                String jap = rs.getString("jp");
                String na = rs.getString("na");
                String pal = rs.getString("pal");
                String players = rs.getString("players");

                // System.out.println("Title = " + title);
                // System.out.println("Genre = " + genre);
                // System.out.println("Developer = " + developer);
                // System.out.println("Publisher = " + publisher);
                // System.out.println("Exclusive = " + exclusives);
                // System.out.println("JAP = " + jap);
                // System.out.println("NA = " + na);
                // System.out.println("PAL = " + pal);
                // System.out.println("Players = " + players);
                // System.out.println();

                Game game = new Game(title, genre, developer, publisher, exclusives, jap, na, pal, players);

                presenter.getModel().addGame(game);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

}
