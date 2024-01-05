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
import lk.ijse.cinnamonProduction.dto.stock;
import lk.ijse.cinnamonProduction.dto.tm.stockTm;
import lk.ijse.cinnamonProduction.model.stockModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

import static java.lang.String.*;


public class stockFormController {
        @FXML
        private TableColumn<?, ?> colcategory;

        @FXML
        private TableColumn<?, ?> colid;

        @FXML
        private TableColumn<?, ?> colname;

        @FXML
        private TableColumn<?, ?> colqty;

        @FXML
        private AnchorPane stockPane;

        @FXML
        private TableView<stock> tablestock;


        @FXML
        private TableColumn<?, ?> tablstock;

        @FXML
        private TextField txtCategory;

        @FXML
        private TextField txtId;

        @FXML
        private TextField txtName;

        @FXML
        private TextField txtQty;

        @FXML
        void btnAddOnAction(ActionEvent event) {
                boolean isvalid = validateStock();
                if(isvalid) {

                        String itemId = txtId.getText();
                        String itemName = txtName.getText();
                        double itemQty = Double.parseDouble(txtQty.getText());
                        String itemCategory = txtCategory.getText();

                        stockTm stockDto = new stockTm(itemId, itemName, itemQty, itemCategory);

                        try {
                                boolean isSaved = stockModel.saveItem(stockDto);
                                if (isSaved) {
                                        new Alert(Alert.AlertType.CONFIRMATION, "item saved!").show();
                                        //clearFields();
                                }
                        } catch (SQLException e) {
                                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                        }
                }else {
                        new Alert(Alert.AlertType.ERROR, "inavlid").show();
                }
        }

        private boolean validateStock() {
                String itemId=txtId.getText();
                boolean isIdValidated=  Pattern.matches("[S][0-9]{3,}",itemId);
                if(!isIdValidated){
                        new Alert(Alert.AlertType.ERROR,"invalid id").show();
                        return  false;
                }

                String itemName=txtName.getText();
                boolean isNameValidated=  Pattern.matches("[A-Za-z]{3,}",itemName);
                if(!isNameValidated){
                        new Alert(Alert.AlertType.ERROR,"invalid name").show();
                        return false;
                }
                return true;
        }

        @FXML
        void btnDeleteOnAction(ActionEvent event) {

                String itemId = txtId.getText();

                try {
                        boolean isDeleted = stockModel.deleteItem(itemId);

                        if(isDeleted) {
                                new Alert(Alert.AlertType.CONFIRMATION, "item deleted!").show();
                        }
                } catch (SQLException e) {
                        new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }

        }

        @FXML
        void btnUpdateOnAction(ActionEvent event) throws SQLException {

                String itemId = txtId.getText();
                String itemName = txtName.getText();
                double itemQty = Double.parseDouble(txtQty.getText());
                String itemCategory = txtCategory.getText();

                boolean isUpdated = stockModel.updateItem(new stockTm(itemId, itemName,itemQty,itemCategory));
                if (isUpdated) {
                        new Alert(Alert.AlertType.CONFIRMATION, "stock updated").show();
                }


        }

        @FXML
        void btnbackOnAction(ActionEvent event) throws IOException {
                Parent anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
                Stage stage = (Stage) stockPane.getScene().getWindow();

                stage.setScene(new Scene(anchorPane));
                stage.setTitle("Dashboard");
                stage.centerOnScreen();


        }
               /* void btnbackOnAction(ActionEvent event) throws IOException {
                Parent stockPane = FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml"));
                Scene scene = new Scene(stockPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                Stage stage1 = (Stage) stockPane.getScene().getWindow();
                stage1.close();


        }
*/


        @FXML
        void btnnextOnAction(ActionEvent event) throws IOException {
                Parent anchorPane = FXMLLoader.load(this.getClass().getResource("/view/cinnamonGrades_form.fxml"));
                Stage stage = (Stage) stockPane.getScene().getWindow();

                stage.setScene(new Scene(anchorPane));
                stage.setTitle("cinnamon Grades");
                stage.centerOnScreen();

        }
        public void initialize(){
                setCellValueFactory();
                loadAllSales();
        }

        private void setCellValueFactory() {
                colid.setCellValueFactory(new PropertyValueFactory<>("itemId"));
                colname.setCellValueFactory(new PropertyValueFactory<>("itemName"));
                colqty.setCellValueFactory(new PropertyValueFactory<>("itemQty"));
                colcategory.setCellValueFactory(new PropertyValueFactory<>("itemCategory"));
        }

        private void loadAllSales() {
                var model = new stockModel();

                ObservableList<stock> oblist = FXCollections.observableArrayList();

                try {
                        List<stock> dtoList = model.getAllStock();
                        for (stock dto : dtoList) {
                                oblist.add(
                                        new stock(
                                               dto.getItemId(),
                                                dto.getItemName(),
                                                dto.getItemQty(),
                                                dto.getItemCategory()

                                        )
                                );
                        }
                        tablestock.setItems(oblist);
                }catch (SQLException e) {
                        throw new RuntimeException(e);
                }

        }

        public void btnEditProfileOnAction(ActionEvent actionEvent) throws IOException {
                Parent root = FXMLLoader.load(this.getClass().getResource("/view/editProfile_form.fxml"));
                //load(this.getClass().getResource("/view/editProfile_form.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                Stage stage1 = (Stage) stockPane.getScene().getWindow();
                stage1.close();
        }

        public void btnDashboardOnACtion(ActionEvent actionEvent) throws IOException {
                Parent root = FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                Stage stage1 = (Stage) stockPane.getScene().getWindow();
                stage1.close();

        }

        public void btnEmployeeOnAction(ActionEvent actionEvent) throws IOException {
                Parent root = FXMLLoader.load(getClass().getResource("/view/employeeManagement_form.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                Stage stage1 = (Stage) stockPane.getScene().getWindow();
                stage1.close();
        }

        public void btnStockOnAction(ActionEvent actionEvent) throws IOException {
                Parent root = FXMLLoader.load(this.getClass().getResource("/view/stock_form.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                Stage stage1 = (Stage) stockPane.getScene().getWindow();
                stage1.close();
        }

        public void btnMerchantOnAction(ActionEvent actionEvent) throws IOException {
                Parent root = FXMLLoader.load(this.getClass().getResource("/view/registerMerchant_form.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                Stage stage1 = (Stage) stockPane.getScene().getWindow();
                stage1.close();
        }

        public void btnSalesOnAction(ActionEvent actionEvent) throws IOException {

                Parent root = FXMLLoader.load(this.getClass().getResource("/view/sales_form.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                Stage stage1 = (Stage) stockPane.getScene().getWindow();
                stage1.close();
        }

        public void btnMachineOnAction(ActionEvent actionEvent) throws IOException {
                Parent root = FXMLLoader.load(this.getClass().getResource("/view/machine_form.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                Stage stage1 = (Stage) stockPane.getScene().getWindow();
                stage1.close();
        }

        public void btnVehicleOnAction(ActionEvent actionEvent) throws IOException {
                Parent root = FXMLLoader.load(this.getClass().getResource("/view/vehical_form.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                Stage stage1 = (Stage) stockPane.getScene().getWindow();
                stage1.close();
        }

        public void stockOnAction(ActionEvent actionEvent) {
            String id = txtId.getText();

            var model = new stockModel();
            try {
                    stock dto = model.searchStock(id);

                    if (dto != null){
                            fillFields(dto);
                    }else{
                            new Alert(Alert.AlertType.INFORMATION, "stock not found").show();
                    }

            } catch (SQLException e){
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
    }

        private void fillFields(stock dto) {
                txtId.setText(dto.getItemId());
                txtName.setText(dto.getItemName());
                txtQty.setText(valueOf(dto.getItemQty()));
                txtCategory.setText(dto.getItemCategory());
        }
}
