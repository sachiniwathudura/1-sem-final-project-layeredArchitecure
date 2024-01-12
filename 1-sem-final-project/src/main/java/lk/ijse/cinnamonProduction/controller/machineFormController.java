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
import lk.ijse.cinnamonProduction.bo.custom.Impl1.machineBOImpl;
import lk.ijse.cinnamonProduction.bo.custom.machineBO;

import lk.ijse.cinnamonProduction.dto.machineDto;

import lk.ijse.cinnamonProduction.entity.machine;



import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class machineFormController {
        @FXML
        private AnchorPane machinePane;

        @FXML
        private TextField txtId;

        @FXML
        private TextField txtName;

        @FXML
        private TextField txtStatus;


        @FXML
        private TableColumn<?, ?> colId;

        @FXML
        private TableColumn<?, ?> colName;

        @FXML
        private TableColumn<?, ?> colStatus;


        @FXML
        private TableView<machineDto> tableMachine;

        machineBO MachineBO = new machineBOImpl();

        @FXML
        void btnAddOnAction(ActionEvent event) {
                boolean isvalid = validateMachine();
                if(isvalid) {
                        String machineId = txtId.getText();
                        String machineName = txtName.getText();
                        String machineStatus = txtStatus.getText();

                        var dto = new machineDto(machineId, machineName, machineStatus);

                  //      var model = new machineModel();
                        try {
                                boolean isSaved = MachineBO.saveMachine(dto);
                                if (isSaved) {
                                        new Alert(Alert.AlertType.CONFIRMATION, "machine saved!").show();
                                        clearFields();
                                }
                        } catch (SQLException | ClassNotFoundException e) {
                                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                        }
                }else{
                        new Alert(Alert.AlertType.ERROR, "invalid").show();
                }

        }

        private boolean validateMachine() {
                String id = txtId.getText();
                boolean isIdValidated = Pattern.matches("[M][0-9]{3,}", id);
                if (!isIdValidated) {
                        new Alert(Alert.AlertType.ERROR, "invalid id").show();
                        return false;
                }

                String name = txtName.getText();
                boolean isNameValidated = Pattern.matches("[A-Za-z]{3,}", name);
                if (!isNameValidated) {
                        new Alert(Alert.AlertType.ERROR, "invalid name").show();
                        return false;
                }
                return true;
        }

        private void clearFields() {
                txtId.setText("");
                txtName.setText("");
                txtStatus.setText("");
        }


        @FXML
        void btndeleteOnAction(ActionEvent event) {
                String machineId = txtId.getText();

                try {
                        boolean isDeleted = MachineBO.deleteMachine(machineId);

                        if (isDeleted) {
                                new Alert(Alert.AlertType.CONFIRMATION, "machine deleted!").show();
                        }
                } catch (SQLException | ClassNotFoundException e) {
                        new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }

        }

        @FXML
        void btnupadteOnAction(ActionEvent event) {
                String machineId = txtId.getText();
                String machineName = txtName.getText();
                String machineStatus = txtStatus.getText();


                try {
                        boolean isUpdated =MachineBO.updateMachine(new machineDto(machineId, machineName, machineStatus));
                        if (isUpdated) {
                                new Alert(Alert.AlertType.CONFIRMATION, "upadte machine").show();
                        }
                } catch (SQLException | ClassNotFoundException e) {
                        new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
        }

        @FXML
        void btnBackOnAction(ActionEvent event) throws IOException {
                Parent anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
                Stage stage = (Stage) machinePane.getScene().getWindow();

                stage.setScene(new Scene(anchorPane));
                stage.setTitle("Dashboard");
                stage.centerOnScreen();

        }
        public void initialize(){
                setCellValueFactory();
                loadAllMachine();

        }

        private void loadAllMachine() {
            //    var model = new machineModel();

                ObservableList<machineDto>oblist = FXCollections.observableArrayList();

                try {
                        List<machineDto> dtoList = MachineBO.getAllMachine();
                        for (machineDto dto : dtoList) {
                                oblist.add(
                                        new machineDto(
                                                dto.getMachineId(),
                                                dto.getMachineName(),
                                                dto.getMachineStatus()
                                        )
                                );
                        }
                        tableMachine.setItems(oblist);
                }catch (SQLException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                }
        }



        private void setCellValueFactory() {
                colId.setCellValueFactory(new PropertyValueFactory<>("machineId"));
                colName.setCellValueFactory(new PropertyValueFactory<>("machineName"));
                colStatus.setCellValueFactory(new PropertyValueFactory<>("machineStatus"));
        }

        public void btnEditprofileAction(ActionEvent actionEvent) throws IOException {
                Parent root = FXMLLoader.load(this.getClass().getResource("/view/editProfile_form.fxml"));
                //load(this.getClass().getResource("/view/editProfile_form.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                Stage stage1 = (Stage) machinePane.getScene().getWindow();
                stage1.close();

        }

        public void btnMerchantOnAction(ActionEvent actionEvent) throws IOException {
                Parent root = FXMLLoader.load(this.getClass().getResource("/view/registerMerchant_form.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                Stage stage1 = (Stage) machinePane.getScene().getWindow();
                stage1.close();
        }

        public void btnEmployeeOnAction(ActionEvent actionEvent) throws IOException {
                Parent root = FXMLLoader.load(getClass().getResource("/view/employeeManagement_form.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                Stage stage1 = (Stage) machinePane.getScene().getWindow();
                stage1.close();
        }

        public void btnStockOnAction(ActionEvent actionEvent) throws IOException {
                Parent root = FXMLLoader.load(this.getClass().getResource("/view/stock_form.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                Stage stage1 = (Stage) machinePane.getScene().getWindow();
                stage1.close();
        }

        public void btnSalesOnAction(ActionEvent actionEvent) throws IOException {
                Parent root = FXMLLoader.load(this.getClass().getResource("/view/sales_form.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                Stage stage1 = (Stage) machinePane.getScene().getWindow();
                stage1.close();
        }

        public void btnMachineOnAction(ActionEvent actionEvent) throws IOException {

                Parent root = FXMLLoader.load(this.getClass().getResource("/view/machine_form.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                Stage stage1 = (Stage) machinePane.getScene().getWindow();
                stage1.close();
        }

        public void btnVehicleOnAction(ActionEvent actionEvent) throws IOException {
                Parent root = FXMLLoader.load(this.getClass().getResource("/view/vehical_form.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                Stage stage1 = (Stage) machinePane.getScene().getWindow();
                stage1.close();
        }

        public void btnDashhboardOnAction(ActionEvent actionEvent) throws IOException {

                Parent root = FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                Stage stage1 = (Stage) machinePane.getScene().getWindow();
                stage1.close();

        }

        public void MachineOnAction(ActionEvent actionEvent) {
                String id = txtId.getText();

             //   var model = new machineModel();
                try {
                        machineDto dto =MachineBO.searchMachine(id);

                        if (dto != null){
                                fillFields(dto);
                        }else{
                                new Alert(Alert.AlertType.INFORMATION, "machine not found").show();
                        }

                } catch (SQLException | ClassNotFoundException e){
                        new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
        }

        private void fillFields(machineDto dto) {
                txtId.setText(dto.getMachineId());
                txtName.setText(dto.getMachineName());
                txtStatus.setText(dto.getMachineStatus());
        }
}

