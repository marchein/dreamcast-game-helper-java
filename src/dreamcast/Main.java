package dreamcast;

import java.io.IOException;

import dreamcast.model.DataInitializer;
import dreamcast.model.Model;
import dreamcast.presenter.Presenter;
import dreamcast.view.ViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = initApplication();
        Scene scene = new Scene(loader.getRoot());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dreamcast");
        primaryStage.setWidth(950);
        primaryStage.show();
    }

    public FXMLLoader initApplication() throws IOException {
        Model model = new Model();
        Presenter presenter = new Presenter();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/layout.fxml"));
        loader.load();
        ViewController viewController = loader.getController();
        viewController.setPresenter(presenter);
        presenter.setModel(model);
        presenter.setView(viewController);
        new DataInitializer(presenter);
        return loader;
    }

    public static void main(String[] args) {
        try {
            launch(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
