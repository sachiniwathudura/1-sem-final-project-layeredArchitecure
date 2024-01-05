package lk.ijse.cinnamonProduction.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import lk.ijse.cinnamonProduction.db.DbConnection;
import lk.ijse.cinnamonProduction.dto.machineDto;
import lk.ijse.cinnamonProduction.dto.registerMerchantDto;
import lk.ijse.cinnamonProduction.dto.tm.registerMerchantTm;
import lk.ijse.cinnamonProduction.model.machineModel;
import lk.ijse.cinnamonProduction.model.registerMerchantModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class registerMerchantFormController {

    @FXML
    private AnchorPane merchantPane;

    @FXML
    private TableColumn<?, ?> colhomeno;

    @FXML
    private TableColumn<?, ?> colid;

    @FXML
    private TableColumn<?, ?> colname;

    @FXML
    private TableColumn<?, ?> colstreet;

    @FXML
    private TableColumn<?, ?> colteleno;

    @FXML
    private TableColumn<?, ?> coltown;

    @FXML
    private TableColumn<?, ?> colcategory;

    @FXML
    private TableView<registerMerchantDto> tableview;


    @FXML
    private TextField txtId;

    @FXML
    private TextField txtCategory1;

    @FXML
    private TextField txthomeno;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txtstreet;

    @FXML
    private TextField txtteleno;

    @FXML
    private TextField txttown;


    @FXML
    void btnAddOnAction(ActionEvent event) {
        boolean isvalid = validateMerchant();
        if(isvalid) {
            String merchantId = txtId.getText();
            String merchantName = txtname.getText();
            String merchantTeleNo = txtteleno.getText();
            String homeNo = txthomeno.getText();
            String street = txtstreet.getText();
            String town = txttown.getText();
            String merchantCategory = txtCategory1.getText();

            var dto = new registerMerchantDto(merchantId, merchantName, merchantTeleNo, merchantCategory, homeNo, street, town);

            var model = new registerMerchantModel();

            try {
                boolean isSaved = model.saveMerchant(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "merchant saved!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }else{
            new Alert(Alert.AlertType.ERROR,"invalid").show();
        }
           /* InputStream resource = this.getClass().getResourceAsStream("/report/merchantReport.jrxml");
            try {
                JasperReport jasperReport = JasperCompileManager.compileReport(resource);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DbConnection.getInstance().getConnection());
                JasperViewer.viewReport(jasperPrint, false);
            } catch (Exception e) {
                e.printStackTrace();
            }*/

    }

    private boolean validateMerchant() {
        String id=txtId.getText();
        boolean isIdValidated=  Pattern.matches("[H][0-9]{3,}",id);
        if(!isIdValidated){
            new Alert(Alert.AlertType.ERROR,"invalid id").show();
            return  false;
        }

        String name=txtname.getText();
        boolean isNameValidated=  Pattern.matches("[A-Za-z]{3,}",name);
        if(!isNameValidated){
            new Alert(Alert.AlertType.ERROR,"invalid name").show();
            return false;
        }
        return true;
    }


    @FXML
    void btnbackOnAction(ActionEvent event) throws IOException {
        Parent anchorPane = FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml"));
        Stage stage = (Stage) merchantPane.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("dashBoardForm");
        stage.centerOnScreen();


    }

    @FXML
    void btndeleteOnAction(ActionEvent event) {
        String merchantId = txtId.getText();

        try {
            boolean isDeleted = registerMerchantModel.deleteMerchant(merchantId);

            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "merchant deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnupadteOnAction(ActionEvent event) throws SQLException {
        String merchantId = txtId.getText();
        String merchantName = txtname.getText();
        String homeNo = txthomeno.getText();
        String street = txtstreet.getText();
        String town  = txttown.getText();
        String merchantTeleNo = txtteleno.getText();
        String merchantCategory = txtCategory1.getText();

        boolean isUpdated = registerMerchantModel.updateMerchant( new registerMerchantTm(merchantId, merchantName, merchantTeleNo, merchantCategory, homeNo, street, town));
        if (isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "merchant updated").show();
        }

    }
    public void initialize(){
        setCellValueFactory();
        loadAllMerchant();
    }

    private void loadAllMerchant() {
        var model = new registerMerchantModel();

        ObservableList<registerMerchantDto> oblist = FXCollections.observableArrayList();

        try {
            List<registerMerchantDto> dtoList = model.getAllMerchant();
            for (registerMerchantDto dto : dtoList) {
                oblist.add(
                        new registerMerchantDto(
                                dto.getMerchantId(),
                                dto.getMerchantName(),
                                dto.getHomeNo(),
                                dto.getStreet(),
                                dto.getTown(),
                                dto.getMerchantTeleNo(),
                                dto.getMerchantCategory()
                        )
                );
            }
            tableview.setItems(oblist);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("merchantId"));
        colname.setCellValueFactory(new PropertyValueFactory<>("merchantName"));
        colhomeno.setCellValueFactory(new PropertyValueFactory<>("homeNo"));
        colstreet.setCellValueFactory(new PropertyValueFactory<>("street"));
        coltown.setCellValueFactory(new PropertyValueFactory<>("town"));
        colteleno.setCellValueFactory(new PropertyValueFactory<>("merchantTeleNo"));
        colcategory.setCellValueFactory(new PropertyValueFactory<>("merchantCategory"));

    }

    public void btnEditProfileOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/editProfile_form.fxml"));
        //load(this.getClass().getResource("/view/editProfile_form.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        Stage stage1 = (Stage) merchantPane.getScene().getWindow();
        stage1.close();
    }

    public void btnMerchantOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/registerMerchant_form.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        Stage stage1 = (Stage) merchantPane.getScene().getWindow();
        stage1.close();
    }


    public void btnSalesOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/sales_form.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        Stage stage1 = (Stage) merchantPane.getScene().getWindow();
        stage1.close();

    }


    public void btnMachineOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/machine_form.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        Stage stage1 = (Stage) merchantPane.getScene().getWindow();
        stage1.close();
    }

    public void btnVehicleOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/vehical_form.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        Stage stage1 = (Stage) merchantPane.getScene().getWindow();
        stage1.close();
    }

    public void btnEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/employeeManagement_form.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        Stage stage1 = (Stage) merchantPane.getScene().getWindow();
        stage1.close();
    }

    public void btnDashboardOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        Stage stage1 = (Stage) merchantPane.getScene().getWindow();
        stage1.close();

    }

    public void btnSatockOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/stock_form.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        Stage stage1 = (Stage) merchantPane.getScene().getWindow();
        stage1.close();
    }

    public void merchantOnAction(ActionEvent actionEvent) {

        String id = txtId.getText();

        var model = new registerMerchantModel();
        try {
            registerMerchantDto dto = model.searchMerchant(id);

            if (dto != null){
                fillFields(dto);
            }else{
                new Alert(Alert.AlertType.INFORMATION, "merchant not found").show();
            }

        } catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void fillFields(registerMerchantDto dto) {
        txtId.setText(dto.getMerchantId());
        txtname.setText(dto.getMerchantName());
        txtteleno.setText(dto.getMerchantTeleNo());
        txthomeno.setText(dto.getHomeNo());
        txtstreet.setText(dto.getStreet());
        txttown.setText(dto.getTown());
        txtCategory1.setText(dto.getMerchantCategory());
    }
}
