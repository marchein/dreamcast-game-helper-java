package dreamcast.view;

import java.net.URL;
import java.util.ResourceBundle;

import dreamcast.model.Game;
import dreamcast.presenter.Presenter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class ViewController implements Initializable {
    @FXML
    private ComboBox<String> region;

    // @FXML
    // private ComboBox<String> numberOfPlayers;

    @FXML
    private TableView<Game> tableView;

    private TableColumn<Game, String> titleColumn;

    private TableColumn<Game, String> genreColumn;

    private TableColumn<Game, String> developerColumn;

    private TableColumn<Game, String> numberOfPlayersColumn;

    private Presenter presenter;

    public void initView() {
        try {
            tableView.setItems(presenter.getModel().getList("all"));
            tableView.refresh();
            region.setItems(presenter.getModel().getRegions());
            region.getSelectionModel().select(0);
            // numberOfPlayers.setItems(presenter.getModel().getMaxPLayers());
            // numberOfPlayers.getSelectionModel().select(0);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        titleColumn = new TableColumn<Game, String>("Titel");
        genreColumn = new TableColumn<Game, String>("Genre");
        developerColumn = new TableColumn<Game, String>("Entwickler");
        numberOfPlayersColumn = new TableColumn<Game, String>("Spieler");

        // tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        titleColumn.setPrefWidth(400);
        genreColumn.setPrefWidth(215);
        developerColumn.setPrefWidth(200);
        numberOfPlayersColumn.setPrefWidth(100);

        titleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Game, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Game, String> game) {
                return game.getValue().getTitle();
            }
        });

        genreColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Game, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Game, String> game) {
                return game.getValue().getGenre();
            }
        });

        developerColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Game, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Game, String> game) {
                return game.getValue().getDeveloper();
            }
        });

        numberOfPlayersColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Game, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Game, String> game) {
                return game.getValue().getNumberOfPlayers();
            }
        });

        tableView.getColumns().addAll(titleColumn, genreColumn, developerColumn, numberOfPlayersColumn);

        ChangeListener<String> regionListener = new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                if (arg2.equals("Japan")) {
                    tableView.setItems(presenter.getModel().getList("jap"));
                } else if (arg2.equals("Europe")) {
                    tableView.setItems(presenter.getModel().getList("pal"));
                } else if (arg2.equals("USA")) {
                    tableView.setItems(presenter.getModel().getList("us"));
                } else {
                    tableView.setItems(presenter.getModel().getList("all"));
                }
                tableView.refresh();
            }
        };

        region.valueProperty().addListener(regionListener);
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

}
