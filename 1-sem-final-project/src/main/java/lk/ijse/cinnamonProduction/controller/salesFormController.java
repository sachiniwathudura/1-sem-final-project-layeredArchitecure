package lk.ijse.cinnamonProduction.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import lk.ijse.cinnamonProduction.bo.custom.Impl1.salesBOImpl;
import lk.ijse.cinnamonProduction.bo.custom.salesBO;

import lk.ijse.cinnamonProduction.dto.salesDto;

import lk.ijse.cinnamonProduction.entity.sales;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class salesFormController {

    @FXML
    private AnchorPane salesPane;
            @FXML
            private TextField txtdate;

            @FXML
            private TextField txtsalesNo;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colSalesNo;

    @FXML
    private TableView<salesDto> tableSales;

    salesBO SalesBO = new salesBOImpl();

    @FXML
    void btnEditProfileOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/editProfile_form.fxml"));
        //load(this.getClass().getResource("/view/editProfile_form.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        Stage stage1 = (Stage) salesPane.getScene().getWindow();
        stage1.close();

    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/employeeManagement_form.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        Stage stage1 = (Stage) salesPane.getScene().getWindow();
        stage1.close();

    }

    @FXML
    void btnMachineOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/machine_form.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        Stage stage1 = (Stage) salesPane.getScene().getWindow();
        stage1.close();

    }

    @FXML
    void btnMerchantOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/registerMerchant_form.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        Stage stage1 = (Stage) salesPane.getScene().getWindow();
        stage1.close();

    }
    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        Stage stage1 = (Stage) salesPane.getScene().getWindow();
        stage1.close();

    }

    @FXML
    void btnSalesOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/sales_form.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        Stage stage1 = (Stage) salesPane.getScene().getWindow();
        stage1.close();
    }

    @FXML
    void btnStockOnActon(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(this.getClass().getResource("/view/stock_form.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        Stage stage1 = (Stage) salesPane.getScene().getWindow();
        stage1.close();

    }

    public void initialize(){
        setCellValueFactory();
        loadAllSales();
    }

    private void setCellValueFactory() {
        colSalesNo.setCellValueFactory(new PropertyValueFactory<>("salesNo"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    private void loadAllSales() {
        //var model = new salesModel();

        ObservableList<salesDto> oblist = FXCollections.observableArrayList();

        try {
            List<salesDto> dtoList = SalesBO.getAllSales();
            for (salesDto dto : dtoList) {
                oblist.add(
                        new salesDto(
                                dto.getSalesNo(),
                                dto.getDate()
                        )
                );
            }
            tableSales.setItems(oblist);
        }catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
            void btnAddOnAction(ActionEvent event) throws SQLException {
                //boolean isvalid = validateSales();
               // if(isvalid) {

                    String salesNo = txtsalesNo.getText();
                   String date = txtdate.getText();


                    salesDto salesDto = new salesDto(salesNo, date);

        boolean isSaved = false;
        try {
            isSaved = SalesBO.saveSales(salesDto);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (isSaved) {
                        new Alert(Alert.AlertType.CONFIRMATION, "sales saved!").show();
                        //clearFields();
                    }
                //}else {
                    //new Alert(Alert.AlertType.ERROR, "invalid").show();

                //}

            }

   /* private boolean validateSales() {
        String date =txtdate.getText();
        boolean isIdValidated=  Pattern.matches("[0-9]{3,}",date);
        if(!isIdValidated){
            new Alert(Alert.AlertType.ERROR,"invalid id").show();
            return  false;
        }

        String salesNo=txtsalesNo.getText();
        boolean isNameValidated=  Pattern.matches("[A-Za-z]{3,}",salesNo);
        if(!isNameValidated){
            new Alert(Alert.AlertType.ERROR,"invalid name").show();
            return false;
        }
        return true;
    }
*/
    @FXML
            void btnUpdateOnAction(ActionEvent event) {

                String salesNo = txtsalesNo.getText();
                String date = txtdate.getText();

                try{
                boolean isUpdated = SalesBO.updateSales(new salesDto(salesNo, date));
                if(isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "sales updated").show();

                }
                } catch (SQLException | ClassNotFoundException e){
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
            }

            @FXML
            void btnbackOnAction(ActionEvent event) throws IOException {
                Parent root = FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                Stage stage1 = (Stage) salesPane.getScene().getWindow();
                stage1.close();


            }

            @FXML
            void btnnextOnAction(ActionEvent event) throws IOException {
                Parent root = FXMLLoader.load(this.getClass().getResource("/view/company_form.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                Stage stage1 = (Stage) salesPane.getScene().getWindow();
                stage1.close();

            }

    public void btnVehicleOnACTION(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/vehical_form.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        Stage stage1 = (Stage) salesPane.getScene().getWindow();
        stage1.close();
    }

    public void salesOnAction(ActionEvent actionEvent) {
        String id = txtsalesNo.getText();

       // var model = new salesModel();
        try {
            salesDto dto = SalesBO.searchSales(id);

            if (dto != null){
                fillFields(dto);
            }else{
                new Alert(Alert.AlertType.INFORMATION, "sales not found").show();
            }

        } catch (SQLException | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void fillFields(salesDto dto) {
        txtsalesNo.setText(dto.getSalesNo());
        txtdate.setText(String.valueOf(dto.getDate()));
    }
}
