package lk.ijse.cinnamonProduction.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;



public class userLoginFormController {

    @FXML
    private TextField txtpassword;

    @FXML
    private TextField txtusername;

    @FXML
    private AnchorPane loginPane;


    private String username = "sachini";

    private String password = "1234";


    @FXML
    void btnForgetpasswordOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/forgetPassword_form.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        Stage stage1 = (Stage) loginPane.getScene().getWindow();
        stage1.close();

    }

    /*  @FXML

          void btnLoginOnAction(ActionEvent event)  {
          if (username.equals(txtusername.getText()) && password.equals(txtpassword.getText())) {
              Parent root = null;
              try {
                  root = FXMLLoader.load(getClass().getResource("dashboardFormController.fxml"));

                 // root = load(this.getClass().getResource("/View/dashboardFormController.fxml"));
              } catch (IOException e) {
                  e.printStackTrace();
              }
              Scene scene = new Scene(root);
              Stage stage = new Stage();
              stage.setScene(scene);
              stage.show();


              Stage stage1 = (Stage) loginPane.getScene().getWindow();
              stage1.close();

          }
      }*/
    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        if (username.equals(txtusername.getText()) && password.equals(txtpassword.getText())) {
           Parent root = FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            Stage stage1 = (Stage) loginPane.getScene().getWindow();
            stage1.close();

        } else {
            new Alert(Alert.AlertType.ERROR,"Incorrect password").show();
        }
        }

}


   /* import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.Parent;
    import javafx.scene.Scene;
    import javafx.scene.control.Alert;
    import javafx.scene.control.Button;
    import javafx.scene.control.Label;
    import javafx.scene.control.TextField;
    import javafx.scene.layout.AnchorPane;
    import javafx.stage.Stage;
    import javafx.stage.Window;

    import java.io.IOException;
    public class userLoginFormController  {
@FXML
private Button btnLogin;

@FXML
private Label lblUsername;

@FXML
private TextField txtpassword;

@FXML
private TextField txtusername;

@FXML
private AnchorPane loginPane;
@FXML
private String username="1234";
private String password="1234";

@FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
            if (username.equals(txtusername.getText()) && password.equals(txtpassword.getText())){
            Parent root = FXMLLoader.load(this.getClass().getResource("/View/dashboard_form_.fxml"));
            Scene scene=new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            Stage stage1 = (Stage) loginPane.getScene().getWindow();
            stage1.close();

            } else {
            new Alert(Alert.AlertType.ERROR,"Incorrect password").show();


                  }
    }
    }*/

