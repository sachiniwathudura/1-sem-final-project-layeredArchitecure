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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.cinnamonProduction.dto.employee;
import lk.ijse.cinnamonProduction.model.cinnamonGradesModel;
import lk.ijse.cinnamonProduction.model.employeeManagementModel;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;


public class employeeManagementFormController {

    @FXML
    private AnchorPane employPane;

    @FXML
    private TableColumn<?, ?> coladdress;

    @FXML
    private TableColumn<?, ?> colid;

    @FXML
    private TableColumn<?, ?> colname;

    @FXML
    private TableColumn<?, ?> colstatus;

    @FXML
    private TableColumn<?, ?> colteleno;

    @FXML
    private TextField txtStatus;

    @FXML
    private ImageView employeePane;

    @FXML
    private TableView<employee> tableview;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTeleNo;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        boolean isValid = validateEmployee();
        if (isValid) {
            String empId = txtId.getText();
            String empName = txtName.getText();
            String empAddress = txtAddress.getText();
            String empTeleNo = txtTeleNo.getText();
            String empStatus = (String)txtStatus.getText();


            var dto = new employee(empId, empName, empAddress, empTeleNo, empStatus);

            var model = new employeeManagementModel();
            try {
                boolean isSaved = model.saveEmployee(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "employee saved!").show();
                    //clearFields();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }

        }
    }

    @FXML
    void btnbackOnAction(ActionEvent event) throws IOException {
        Parent anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Stage stage = (Stage) employeePane.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard");
        stage.centerOnScreen();

    }


  /*  void btndeleteOnAction(ActionEvent event) {
        String empId = txtId.getText();

        try {
            boolean isDeleted = employeeManagementModel.deleteEmployee(empId);

            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "employee deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }*/


   /* void btnupadteOnAction(ActionEvent event) {
        String empId = txtId.getText();
        String empName = txtName.getText();
        String empAddress = txtAddress.getText();
        String empTeleNo = txtTeleNo.getText();
        String empStatus = comboxstaus.getAccessibleText();
        var dto =new employeeManagementDTo(empId,empName,empAddress,empTeleNo,empStatus);
        var model=new employeeManagementModel();

        try {
            boolean isUpdated = employeeManagementModel.updateEmployee(dto);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "upadte employee").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }*/

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String empId = txtId.getText();

        try {
            boolean isDeleted = employeeManagementModel.deleteEmployee(empId);

            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "employee deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String empId = txtId.getText();
        String empName = txtName.getText();
        String empAddress = txtAddress.getText();
        String empTeleNo = txtTeleNo.getText();
        String empStatus = txtStatus.getAccessibleText();
        var dto =new employee(empId,empName,empAddress,empTeleNo,empStatus);
        var model=new employeeManagementModel();

        try {
            boolean isUpdated = employeeManagementModel.updateEmployee(dto);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "upadte employee").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }
    public void initialize(){
        setCellValueFactory();
        loadAllEmployee();

    }

    private void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colname.setCellValueFactory(new PropertyValueFactory<>("empName"));
        coladdress.setCellValueFactory(new PropertyValueFactory<>("empAddress"));
        colteleno.setCellValueFactory(new PropertyValueFactory<>("empTeleNo"));
        colstatus.setCellValueFactory(new PropertyValueFactory<>("empStatus"));
    }
    private void loadAllEmployee() {
        var model = new employeeManagementModel();

        ObservableList<employee> obList  = FXCollections.observableArrayList();

            try {
                 List<employee> dtoList = model.getAllEmployee();

                    for(employee dto : dtoList) {
                        obList.add(
                                new employee(
                                        dto.getEmpId(),
                                        dto.getEmpName(),
                                        dto.getEmpAddress(),
                                        dto.getEmpTeleNo(),
                                        dto.getEmpStatus()

                                )
                         );
                    }

            tableview.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnupadteOnAction(ActionEvent actionEvent) {
        String empId = txtId.getText();
        String empName = txtName.getText();
        String empAddress = txtAddress.getText();
        String empTeleNo = txtTeleNo.getText();
        String empStatus = txtStatus.getAccessibleText();
        var dto =new employee(empId,empName,empAddress,empTeleNo,empStatus);
        var model=new employeeManagementModel();

        try {
            boolean isUpdated = employeeManagementModel.updateEmployee(dto);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "upadte employee").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btndeleteOnAction(ActionEvent actionEvent) {
        String empId = txtId.getText();

        try {
            boolean isDeleted = employeeManagementModel.deleteEmployee(empId);

            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "employee deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }
    }


    private boolean validateEmployee() {
        String id=txtId.getText();
        boolean isIdValidated=  Pattern.matches("[E][0-9]{3,}",id);
        if(!isIdValidated){
            new Alert(Alert.AlertType.ERROR,"invalid id").show();
            return  false;
        }
        String name=txtName.getText();
        boolean isNameValidated=  Pattern.matches("[A-Za-z]{3,}",name);
        if(!isNameValidated){
            new Alert(Alert.AlertType.ERROR,"invalid name").show();
            return false;
        }
        return true;
    }

    public void btnEditProfileOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/editProfile_form.fxml"));
        //load(this.getClass().getResource("/view/editProfile_form.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        Stage stage1 = (Stage) employeePane.getScene().getWindow();
        stage1.close();

    }

    public void btnMerchantOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/registerMerchant_form.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        Stage stage1 = (Stage) employeePane.getScene().getWindow();
        stage1.close();
    }

    public void btnEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/employeeManagement_form.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        Stage stage1 = (Stage) employeePane.getScene().getWindow();
        stage1.close();
    }

    public void btnStockOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/stock_form.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        Stage stage1 = (Stage) employeePane.getScene().getWindow();
        stage1.close();
    }

    public void btnSalesOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/sales_form.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        Stage stage1 = (Stage) employeePane.getScene().getWindow();
        stage1.close();
    }

    public void btnMachineOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/machine_form.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        Stage stage1 = (Stage) employeePane.getScene().getWindow();
        stage1.close();
    }

    public void btnVehicalaOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/vehical_form.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        Stage stage1 = (Stage) employeePane.getScene().getWindow();
        stage1.close();
    }

    public void btnDashboardOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        Stage stage1 = (Stage) employeePane.getScene().getWindow();
        stage1.close();
    }

    public void employeeOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();

        var model = new employeeManagementModel();
        try {
            employee dto = model.searchEmployee(id);

            if (dto != null){
                fillFields(dto);
            }else{
                new Alert(Alert.AlertType.INFORMATION, "cGrade not found").show();
            }

        } catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void fillFields(employee dto) {
        txtId.setText(dto.getEmpId());
        txtName.setText(dto.getEmpName());
        txtAddress.setText(dto.getEmpAddress());
        txtTeleNo.setText(dto.getEmpTeleNo());

    }
}

