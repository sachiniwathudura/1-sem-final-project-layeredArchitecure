package lk.ijse.cinnamonProduction.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.cinnamonProduction.dto.userLoginDto;
import lk.ijse.cinnamonProduction.model.userLoginModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class editProfileFormController {
    @FXML
    private AnchorPane editprofilepane;

    @FXML
    private TextField txteleno;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txtfirstname;

    @FXML
    private TextField txtfullname;

    @FXML
    private TextField txtppassword;

    @FXML
    private TextField txtid;

    @FXML
    void btnOnSaveAction(ActionEvent event) throws SQLException {
        boolean isValid = validateUserDetalis();
        if (isValid) {

            String firstname = txtfirstname.getText();
            String userId = txtid.getText();
            String userName = txtfullname.getText();
            String userEmail = txtemail.getText();
            String password = txtppassword.getText();

            var userLoginDto = new userLoginDto(userId, userName, userEmail, password);

            try {
                boolean isSaved = userLoginModel.saveUser(userLoginDto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, " user saved").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "invalid").show();

        }
    }

    @FXML
    void btndeleteOnAction(ActionEvent event) {

        String userId = txtid.getText();

        try {
            boolean isDeleted = userLoginModel.deleteUser(userId);

            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "user deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    private void deleteUser(String userId) {
        try {
            boolean isDeleted = userLoginModel.deleteUser(userId);
            if(isDeleted)
                new Alert(Alert.AlertType.CONFIRMATION, "user deleted!").show();
        } catch (SQLException ex) {
            new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
        }
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Parent anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Stage stage = (Stage) editprofilepane.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("dashBoardForm");
        stage.centerOnScreen();


    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException {
            String firstname = txtfirstname.getText();
            String userId = txtid.getText();
            String userName = txtfullname.getText();
            String userEmail = txtemail.getText();
            String password = txtppassword.getText();

            boolean isUpdated = userLoginModel.updateUser(new userLoginDto(userId,userName,userEmail,password));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "user updated").show();
            }

    }

   /* public void btnBackOnAction(ActionEvent actionEvent) {

    }*/


    private boolean validateUserDetalis() {
        String id=txtid.getText();
        boolean isIdValidated=  Pattern.matches("[U][0-9]{3,}",id);
        if(!isIdValidated){
            new Alert(Alert.AlertType.ERROR,"invalid id").show();
            return  false;
        }
        String name=txtfullname.getText();
        boolean isNameValidated=  Pattern.matches("[A-Za-z]{3,}",name);
        if(!isNameValidated){
            new Alert(Alert.AlertType.ERROR,"invalid name").show();
            return false;
        }
        return true;

    }

    public void btnupdateOnAction(MouseEvent mouseEvent) {
    }
}
