package lk.ijse.cinnamonProduction;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.fxml.FXMLLoader.load;

public class AppInitializer extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
      //  stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml"))));
        stage.setScene(new Scene(load(this.getClass().getResource("/view/userLogin_form.fxml"))));
        stage.setTitle("useLogin form");
        stage.centerOnScreen();

        stage.show();
    }
}
