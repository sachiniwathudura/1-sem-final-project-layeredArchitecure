package lk.ijse.cinnamonProduction.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.cinnamonProduction.dto.company;
import lk.ijse.cinnamonProduction.model.companyModel;
import lk.ijse.cinnamonProduction.model.employeeManagementModel;
import lombok.SneakyThrows;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class DashboardFormController {


    @FXML
    private TableColumn<?, ?> colemail;

    @FXML
    private TableColumn<?, ?> colname;

    @FXML
    private Label lblToatal;

    @FXML
    private TableView<company> tablcompanydetails;

    @FXML
    private AnchorPane dashboardPane;


    @FXML
    private PieChart pieChart;


    private final employeeManagementModel EmployeeManagementModel = new employeeManagementModel();
    private final companyModel CompanyModel = new companyModel();

    @SneakyThrows
    public void initialize() throws SQLException {
        countEmployeeFrame();
        viewCompanyTable();
        setCellValueFactory1();

        ObservableList<PieChart.Data> observableList = FXCollections.observableArrayList(
                new PieChart.Data("Alba", 100),
                new PieChart.Data("C/5", 50),
                new PieChart.Data("M/4", 40),
                new PieChart.Data("H/5", 30),
                new PieChart.Data("special", 20),
                new PieChart.Data("C/4 M/5", 10)

        );
        pieChart.setData(observableList);
        /*applyCustomColorSequence(
                observableList,
                "aqua",
                "bisque",
                "chocolate",
                "coral",
                "crimson",
                "pink"
        );*/
        pieChart.setMinSize(280, 280);
        // pieChart.setMaxSize(600, 600);

       /* @Override public void start(Stage stage) {
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data("Grapefruit", 13),
                    new PieChart.Data("Oranges",    25),
                    new PieChart.Data("Plums",      10),
                    new PieChart.Data("Pears",      22),
                    new PieChart.Data("Apples",     30)
            );

            final PieChart chart = new PieChart(pieChartData);
            chart.setLegendVisible(false);

            stage.setScene(new Scene(chart));
            stage.show();

            applyCustomColorSequence(
                    pieChartData,
                    "aqua",
                    "bisque",
                    "chocolate",
                    "coral",
                    "crimson"
            );
        }

        private void applyCustomColorSequence(ObservableList<PieChart.Data> pieChartData, String... pieColors) {
            int i = 0;
            for (PieChart.Data data : pieChartData) {
                data.getNode().setStyle("-fx-pie-color: " + pieColors[i % pieColors.length] + ";");
                i++;
            }
        }*/


    }

    /*private void applyCustomColorSequence(ObservableList<PieChart.Data> observableList, String... pieColors) {
        int i = 0;
        for (PieChart.Data data : observableList) {
            String color = pieColors[i % pieColors.length];

            // Set color for the slice
            data.getNode().setStyle("-fx-pie-color: " + color + ";");

            // Set color for the legend symbol
            setLegendColor(data, color);

            i++;
        }
    }*/

   /* private void setLegendColor(PieChart.Data data, String color) {
        try {
            // Using reflection to access the private method setLegendColor
            var method = PieChart.Data.class.getDeclaredMethod("setLegendColor", String.class);
            method.setAccessible(true);
            method.invoke(data, color);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    private void setCellValueFactory1() {
        colname.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("companyEmail"));
    }

    private void viewCompanyTable() {
        var model = new companyModel();

        ObservableList<company> obList  = FXCollections.observableArrayList();

        try {
            List<company> dtoList = model.getAllCompany();

            for(company dto : dtoList) {
                obList.add(
                        new company(
                                dto.getCompanyId(),
                                dto.getCompanyName(),
                                dto.getCompanyEmail()

                        )
                );
            }
           tablcompanydetails.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void countEmployeeFrame() throws SQLException {
        String count= String.valueOf(EmployeeManagementModel.countEmployee());
        this.lblToatal.setText(count);


    }


    

    public void btnMerchantOnAction(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/registerMerchant_form.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        Stage stage1 = (Stage) dashboardPane.getScene().getWindow();
        stage1.close();
    }

    public void btnEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/employeeManagement_form.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        Stage stage1 = (Stage) dashboardPane.getScene().getWindow();
        stage1.close();
    }

    public void btnStockOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/stock_form.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        Stage stage1 = (Stage) dashboardPane.getScene().getWindow();
        stage1.close();
    }

    public void btnSalesOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/sales_form.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        Stage stage1 = (Stage) dashboardPane.getScene().getWindow();
        stage1.close();
    }

    public void btnMachineOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/machine_form.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        Stage stage1 = (Stage) dashboardPane.getScene().getWindow();
        stage1.close();

    }


    public void btnVehicleOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/vehical_form.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        Stage stage1 = (Stage) dashboardPane.getScene().getWindow();
        stage1.close();
    }

    public void btnOnEditProfileAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/editProfile_form.fxml"));
                //load(this.getClass().getResource("/view/editProfile_form.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        Stage stage1 = (Stage) dashboardPane.getScene().getWindow();
        stage1.close();
    }

    public void btnReportOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/report_form.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        Stage stage1 = (Stage) dashboardPane.getScene().getWindow();
        stage1.close();


    }


}
