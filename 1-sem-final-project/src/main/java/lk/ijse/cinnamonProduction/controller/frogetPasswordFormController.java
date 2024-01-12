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
import lk.ijse.cinnamonProduction.dao.custom.Impl1.forgetPasswordDAOImpl;


import lk.ijse.cinnamonProduction.viewTDM.forgetPasswordTm;

import javax.swing.*;
import java.io.IOException;

public class frogetPasswordFormController {

    @FXML
    private AnchorPane forgetpasswordPane;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtOTP;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtpassword1;

    private JToggleButton btnResetPassword;

    @FXML
    void btnresetPasswordOnAction(ActionEvent event) {
        String Email=txtEmail.getText();
        String pw=   txtPassword.getText();
        String cpw=txtpassword1.getText();
        String EOTP=txtOTP.getText();


      var tm=new forgetPasswordTm(Email,pw,cpw,EOTP);
        var model=new forgetPasswordDAOImpl();

        try{

            if(pw.equals(cpw)&&(!txtOTP.equals(null))&&!Email.isEmpty()&&(!pw.isEmpty())&&(!cpw.isEmpty())){

                new Alert(Alert.AlertType.CONFIRMATION,"password reset successFully").show();

                Parent root= FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"));
                Scene scene=new Scene(root);
                Stage stage=new Stage();
                stage.setScene(scene);
                stage.show();

                Stage stage1=(Stage)forgetpasswordPane.getScene().getWindow();
                stage1.close();

            }else {
                new Alert(Alert.AlertType.ERROR,"you should fill all the field to get the new password").show();
                if (txtEmail.equals(null)) {
                    txtEmail.requestFocus();
                } else {
                    if (txtOTP.equals(null)) {
                        txtOTP.requestFocus();
                    } else {
                        if (txtPassword.equals(null)) {

                            txtPassword.requestFocus();
                        } else {
                            if (txtpassword1.equals(null)) {
                                txtpassword1.requestFocus();
                            } else {
                                btnResetPassword.requestFocus();
                            }
                        }
                    }
                }

            }

        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();

        }
    }





    @FXML
    void btnsendOTPOnAction(ActionEvent event) {

        String OTP = txtOTP.getText();
        var model = new forgetPasswordDAOImpl();
        try {
            if (!OTP.isEmpty()) {
                new Alert(Alert.AlertType.CONFIRMATION, "send OTP\n your OTP is : " + OTP).show();
            } else {
                new Alert(Alert.AlertType.ERROR, "you are not type otp").show();
                txtOTP.requestFocus();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Parent anchorPane = FXMLLoader.load(getClass().getResource("/view/userLogin_form.fxml"));
        Stage stage = (Stage) forgetpasswordPane.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("user Login");
        stage.centerOnScreen();
    }
}

