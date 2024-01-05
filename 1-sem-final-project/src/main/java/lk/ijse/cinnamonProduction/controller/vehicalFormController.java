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
import lk.ijse.cinnamonProduction.dto.stockDto;
import lk.ijse.cinnamonProduction.dto.tm.cinnamonGradesTm;
import lk.ijse.cinnamonProduction.dto.tm.vehicalTm;
import lk.ijse.cinnamonProduction.dto.vehicalDto;
import lk.ijse.cinnamonProduction.model.cinnamonGradesModel;
import lk.ijse.cinnamonProduction.model.stockModel;
import lk.ijse.cinnamonProduction.model.vehicalModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class vehicalFormController {

        @FXML
        private TextField txtname;

        @FXML
        private TextField txtstatus;

        @FXML
        private AnchorPane vehicalPane;

        @FXML
        private TableColumn<?, ?> colNo;

        @FXML
        private TableColumn<?, ?> colStatus;

        @FXML
        private TableView<vehicalDto> tablevehicle;

        @FXML
        void btnAddOnAction(ActionEvent event) {
                String vehicleNo = txtname.getText();
                String vehiclestatus = txtstatus.getText();


                var vehicalDto = new vehicalDto(vehicleNo,vehiclestatus);

                try{
                        boolean isSaved =  vehicalModel.saveVehical(vehicalDto);
                        if (isSaved){
                                new Alert(Alert.AlertType.CONFIRMATION, " vehical saved").show();
                        }
                }catch ( SQLException e){
                        new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }

        }

        @FXML
        void btnDeleteOnAction(ActionEvent event) {
                String vehicalNo = txtname.getText();

                try {
                        boolean isDeleted = vehicalModel.deleteVehicl(vehicalNo);

                        if(isDeleted) {
                                new Alert(Alert.AlertType.CONFIRMATION, "vehical deleted!").show();
                        }
                } catch (SQLException e) {
                        new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
        }

        @FXML
        void btnUpdateOnAction(ActionEvent event) {
                String vehicleNo = txtname.getText();
                String vehiclestatus = txtstatus.getText();


                var Tm=new vehicalTm(vehicleNo,vehiclestatus);
                var model=new vehicalModel();

                try {
                        boolean isUpdated = model.updateVehical(Tm);
                        if (isUpdated) {
                                new Alert(Alert.AlertType.CONFIRMATION, "upadte vehical").show();
                        }
                } catch (SQLException e) {
                        new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }


        }
        @FXML
        void btnBackOnAction(ActionEvent event) throws IOException {
                Parent anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
                Stage stage = (Stage) vehicalPane.getScene().getWindow();

                stage.setScene(new Scene(anchorPane));
                stage.setTitle("Dashboard");
                stage.centerOnScreen();

        }

    public void btnEditProileOnAction(ActionEvent actionEvent) throws IOException {
            Parent root = FXMLLoader.load(this.getClass().getResource("/view/editProfile_form.fxml"));
            //load(this.getClass().getResource("/view/editProfile_form.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            Stage stage1 = (Stage) vehicalPane.getScene().getWindow();
            stage1.close();
    }

        public void btnMerchantOnAction(ActionEvent actionEvent) throws IOException {
                Parent root = FXMLLoader.load(this.getClass().getResource("/view/registerMerchant_form.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                Stage stage1 = (Stage) vehicalPane.getScene().getWindow();
                stage1.close();
        }

        public void btnEmployeeOnAction(ActionEvent actionEvent) throws IOException {
                Parent root = FXMLLoader.load(getClass().getResource("/view/employeeManagement_form.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                Stage stage1 = (Stage) vehicalPane.getScene().getWindow();
                stage1.close();
        }

        public void btnStockOnAction(ActionEvent actionEvent) throws IOException {
                Parent root = FXMLLoader.load(this.getClass().getResource("/view/stock_form.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                Stage stage1 = (Stage) vehicalPane.getScene().getWindow();
                stage1.close();
        }

        public void btnSalesOnAction(ActionEvent actionEvent) throws IOException {
                Parent root = FXMLLoader.load(this.getClass().getResource("/view/sales_form.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                Stage stage1 = (Stage) vehicalPane.getScene().getWindow();
                stage1.close();
        }

        public void btnMachineOnAction(ActionEvent actionEvent) throws IOException {
                Parent root = FXMLLoader.load(this.getClass().getResource("/view/machine_form.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                Stage stage1 = (Stage) vehicalPane.getScene().getWindow();
                stage1.close();
        }

        public void btnVehicleOnAction(ActionEvent actionEvent) throws IOException {
                Parent root = FXMLLoader.load(this.getClass().getResource("/view/vehical_form.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                Stage stage1 = (Stage) vehicalPane.getScene().getWindow();
                stage1.close();
        }

        public void btnDashboardOnAction(ActionEvent actionEvent) throws IOException {

                Parent root = FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                Stage stage1 = (Stage) vehicalPane.getScene().getWindow();
                stage1.close();
        }

    public void vehicalOnAction(ActionEvent actionEvent) {
            String id = txtname.getText();

            var model = new vehicalModel();
            try {
                    vehicalDto dto = model.searchVehical(id);

                    if (dto != null){
                            fillFields(dto);
                    }else{
                            new Alert(Alert.AlertType.INFORMATION, "vehical not found").show();
                    }

            } catch (SQLException e){
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
    }

        private void fillFields(vehicalDto dto) {
                txtname.setText(dto.getVehicalNo());
                txtstatus.setText(dto.getVehicalStatus());
        }

        public void initialize(){
                setCellValueFactory();
                loadAllVehical();
        }

        private void loadAllVehical() {
                var model = new vehicalModel();

                ObservableList<vehicalDto> oblist = FXCollections.observableArrayList();

                try {
                        List<vehicalDto> dtoList = model.getAllVehical();
                        for (vehicalDto dto : dtoList) {
                                oblist.add(
                                        new vehicalDto(
                                                dto.getVehicalNo(),
                                                dto.getVehicalStatus()
                                        )
                                );
                        }
                        tablevehicle.setItems(oblist);
                }catch (SQLException e) {
                        throw new RuntimeException(e);
                }
        }

        private void setCellValueFactory() {
                colNo.setCellValueFactory(new PropertyValueFactory<>("vehicalNo"));
                colStatus.setCellValueFactory(new PropertyValueFactory<>("vehicalStatus"));

        }
}
