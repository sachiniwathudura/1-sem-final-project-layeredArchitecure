package lk.ijse.cinnamonProduction.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.cinnamonProduction.bo.custom.Impl1.companyBOImpl;
import lk.ijse.cinnamonProduction.bo.custom.companyBO;

import lk.ijse.cinnamonProduction.dto.companyDto;

import lk.ijse.cinnamonProduction.entity.company;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class companyFormController {
    public TextField txtName;
    @FXML
        private TableColumn<?, ?> colEmail;

        @FXML
        private TableColumn<?, ?> colId;

        @FXML
        private TableColumn<?, ?> colName;

        @FXML
        private AnchorPane companyPane;

        @FXML
        private TableView<companyDto> tableCompany;

        @FXML
        private TextField txtEmail;
      @FXML
        private TextField txtId;

            companyBO CompanyBO = new companyBOImpl();

        private void clearFields() {
                txtId.setText("");
                txtName.setText("");
                txtEmail.setText("");
        }
        public void initialize(){
                setCellValueFactory();
                loadAllCompany();

        }

        private void setCellValueFactory() {
                colId.setCellValueFactory(new PropertyValueFactory<>("companyId"));
                colName.setCellValueFactory(new PropertyValueFactory<>("companyName"));
                colEmail.setCellValueFactory(new PropertyValueFactory<>("companyEmail"));
        }
     /*   @FXML
        void btnBackOnAction(ActionEvent event) throws IOException {
                Parent anchorPane = FXMLLoader.load(getClass().getResource("/view/sales_form.fxml"));
                Stage stage = (Stage) companyPane.getScene().getWindow();

                stage.setScene(new Scene(anchorPane));
                stage.setTitle("Dashboard");
                stage.centerOnScreen();

        }*/
        private void loadAllCompany(){
               // var model = new companyModel();

                ObservableList<companyDto> obList  = FXCollections.observableArrayList();

                        try {
                                List<companyDto> dtoList = CompanyBO.getAllCompany();

                                for(companyDto dto : dtoList) {
                                        obList.add(
                                                new companyDto(
                                                        dto.getCompanyId(),
                                                        dto.getCompanyName(),
                                                        dto.getCompanyEmail()

                                                )
                                        );
                                }
                                tableCompany.setItems(obList);
                        } catch (SQLException | ClassNotFoundException e) {
                                throw new RuntimeException(e);
                        }
                }



        public void btnUpdateOnAction(javafx.event.ActionEvent actionEvent) {
                String companyId = txtId.getText();
                String companyName = txtName.getText();
                String companyEmail = txtEmail.getText();
               //  var model=new companyModel();
                 var Tm=new companyDto(companyId,companyName,companyEmail);
                try {
                        boolean isUpdated = CompanyBO.updateCompany(Tm);
                        if (isUpdated) {
                                new Alert(Alert.AlertType.CONFIRMATION, "update company").showAndWait();
                                Parent anchorPane = FXMLLoader.load(this.getClass().getResource("/view/company_form.fxml"));
                                Stage stage = (Stage) companyPane.getScene().getWindow();

                                stage.setScene(new Scene(anchorPane));
                                stage.setTitle("company Form");
                                stage.centerOnScreen();

                        }
                } catch (SQLException | IOException | ClassNotFoundException e) {
                        new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
        }

        public void btnDeleteOnAction(javafx.event.ActionEvent actionEvent) {
                String companyId = txtId.getText();
             //   var model=new companyModel();

                try {
                        boolean isDeleted = CompanyBO.deleteCompany(companyId);

                        if (isDeleted) {
                                new Alert(Alert.AlertType.CONFIRMATION, "company deleted!").show();
                        }
                } catch (SQLException | ClassNotFoundException e) {
                        new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
        }

        public void btnAddOnAction(javafx.event.ActionEvent actionEvent) {
                boolean isValid = validateCompany();
               if(isValid) {
                        String companyId = txtId.getText();
                        String companyName = txtName.getText();
                        String companyEmail = txtEmail.getText();

                        System.out.println(txtName.getText());

                        var dto = new companyDto(companyId, companyName, companyEmail);

                      // var model = new companyModel();
                       try {
                                boolean isSaved = CompanyBO.saveCompany(dto);
                               if (isSaved) {
                                       new Alert(Alert.AlertType.CONFIRMATION, "company saved!").showAndWait();
                                        Parent anchorPane = FXMLLoader.load(this.getClass().getResource("/view/company_form.fxml"));
                                        Stage stage = (Stage) companyPane.getScene().getWindow();

                                        stage.setScene(new Scene(anchorPane));
                                        stage.setTitle("company form");
                                      stage.centerOnScreen();
                                        clearFields();
                               }
                       } catch (SQLException | IOException | ClassNotFoundException e) {
                               new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                       }
                }else {
                      new Alert(Alert.AlertType.ERROR, "invalid ").show();
                }
        }

        private boolean validateCompany() {
                String id=txtId.getText();
                boolean isIdValidated=  Pattern.matches("[C][0-9]{3,}",id);

                String name= txtName.getText();
                boolean isNameValidated=  Pattern.matches("[A-Za-z]{3,}",name);

                if(!isIdValidated){
                        new Alert(Alert.AlertType.ERROR,"invalid id").show();
                        txtId.requestFocus();
                        return  false;
                }
           else if(!isNameValidated){
                        new Alert(Alert.AlertType.ERROR,"invalid name").show();
                        txtName.requestFocus();
                        return false;
                }else {
                        return true;
                }
        }



        public void btnBackOnAction(javafx.event.ActionEvent actionEvent) throws IOException {
                Parent anchorPane = FXMLLoader.load(this.getClass().getResource("/view/sales_form.fxml"));
                Stage stage = (Stage) companyPane.getScene().getWindow();

                stage.setScene(new Scene(anchorPane));
                stage.setTitle("Dashboard");
                stage.centerOnScreen();
        }
}

