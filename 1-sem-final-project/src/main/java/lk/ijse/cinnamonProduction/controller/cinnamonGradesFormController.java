/*
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.cinnamonProduction.dto.cinnamonGradesDto;
import lk.ijse.cinnamonProduction.dto.tm.cinnamonGradesTm;
import lk.ijse.cinnamonProduction.model.cinnamonGradesModel;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class cinnamonGradesFormController {


    @FXML
    private TextField txtId1;

    @FXML
    private TextField txtName1;

    @FXML
    private TextField txtPrice1;
    @FXML
    private AnchorPane cGradePane;
    @FXML
    private TableColumn<?, ?> col1kgPrice;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<cinnamonGradesDto> tableCinnamonGrade;


        public void  initialize(){
            loadAllCinnamonGrades();
            cetCellValueFactory();
        }

    private void cetCellValueFactory() {
         colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        col1kgPrice.setCellValueFactory(new PropertyValueFactory<>("1KgPrice"));
    }

    private void loadAllCinnamonGrades(){
        var model = new cinnamonGradesModel();

    ObservableList<cinnamonGradesDto> obList = FXCollections.observableArrayList();

        try {
        List<cinnamonGradesDto> dtoList = model.getAllCinnamonGrades();

        for(cinnamonGradesDto dto : dtoList) {
            obList.add(
                    new cinnamonGradesDto(
                            dto.getCGradeId(),
                            dto.getCGradeName(),
                            dto.getC1KgPrice()

                    )
            );
        }
        tableCinnamonGrade.setItems(obList);
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}


      */
/*  void btnAddOnAction(ActionEvent event) {
            String cGradeId = txtId.getText();
            String cGradeName = txtName.getText();
            double c1KgPrice = Double.parseDouble(txtPrice.getText());

            var dto = new cinnamonGradesDto(cGradeId, cGradeName, c1KgPrice);

            var model = new cinnamonGradesModel();
            try {
                boolean isSaved = model.saveCgrade(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "cGrade saved!").show();
                    //clearFields();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }

        }*//*



      */
/*  void btnDeleteOnAction(ActionEvent event) {
            String cGradeId = txtId.getText();

            try {
                boolean isDeleted = cinnamonGradesModel.deleteCgrade(cGradeId);

                if (isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, " cgrade deleted!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }

        }*//*


        */
/* void btnUpdateOnAction(ActionEvent event) {

            String cGradeId = txtId.getText();
            String cGradeName = txtName.getText();
            double c1KgPrice = Double.parseDouble(txtPrice.getText());

            try {
                boolean isUpdated = cinnamonGradesModel.updateCgrade(new cinnamonGradesTm(cGradeId, cGradeName, c1KgPrice));
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "upadte cGrade").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }*//*


        */
/* void btnbackOnAction(ActionEvent event) throws IOException {
            Parent anchorPane = FXMLLoader.load(getClass().getResource("/view/stock_form.fxml"));
            Stage stage = (Stage) cGradePane.getScene().getWindow();

            stage.setScene(new Scene(anchorPane));
            stage.setTitle("stockForm");
            stage.centerOnScreen();


        }*//*


    public void btnbackOnAction(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent anchorPane = FXMLLoader.load(this.getClass().getResource("/view/stock_form.fxml"));
        Stage stage = (Stage) cGradePane.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("stockForm");
        stage.centerOnScreen();

    }


    @FXML
    public void btnAddOnAction(javafx.event.ActionEvent actionEvent) {
        boolean isValid=validateUtility();
       if(isValid) {
            String cGradeId =txtId1.getText();
            String cGradeName = txtName1.getText();
           double c1KgPrice = Double.parseDouble(txtPrice1.getText());

           var dto = new cinnamonGradesDto(cGradeId, cGradeName, c1KgPrice);

            var model = new cinnamonGradesModel();
            try {
                boolean isSaved = model.saveCgrade(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "cGrade saved!").showAndWait();
                    Parent anchorPane = FXMLLoader.load(this.getClass().getResource("/view/cinnamonGrades_form.fxml"));
                    Stage stage = (Stage) cGradePane.getScene().getWindow();

                    stage.setScene(new Scene(anchorPane));
                    stage.setTitle("cinnamonGrades Form");
                    stage.centerOnScreen();
                    //clearFields();
                }
            } catch (SQLException | IOException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "invalid details").show();
        }
    }


    private boolean validateUtility() {
        String id=txtId1.getText();
      boolean isIdValidated=  Pattern.matches("[G][0-9]{3,}",id);

        String name=txtName1.getText();
        boolean isNameValidated=  Pattern.matches("[A-Za-z]{3,}",name);

        String isPrice=txtPrice1.getText();
        boolean is1kgPriceValidate=Pattern.matches("[0-9]{4,}",isPrice);

      if(!isIdValidated){
          new Alert(Alert.AlertType.ERROR,"invalid id").show();
          return  false;
      }

     else if(!isNameValidated){
        new Alert(Alert.AlertType.ERROR,"invalid name").show();
        return false;
    }

    else if(!is1kgPriceValidate){
        new Alert(Alert.AlertType.ERROR,"invalid 1 kg price").show();
        return false;
    }else {
          return true;
      }
    }

    @FXML
    public void btnUpdateOnAction(javafx.event.ActionEvent actionEvent) {

        String cGradeId = txtId1.getText();
        String cGradeName = txtName1.getText();
        double c1KgPrice = Double.parseDouble(txtPrice1.getText());
        var Tm=new cinnamonGradesTm(cGradeId,cGradeName,c1KgPrice);

        var model=new cinnamonGradesModel();

        try {
            boolean isUpdated =model.updateCgrade(Tm);
            if (isUpdated) {

                new Alert(Alert.AlertType.CONFIRMATION, "update cGrade").showAndWait();
                Parent anchorPane = FXMLLoader.load(this.getClass().getResource("/view/cinnamonGrades_form.fxml"));
                Stage stage = (Stage) cGradePane.getScene().getWindow();

                stage.setScene(new Scene(anchorPane));
                stage.setTitle("stockForm");
                stage.centerOnScreen();
            }
        } catch (SQLException | IOException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
@FXML
    public void btnDeleteOnAction(javafx.event.ActionEvent actionEvent) {
        String cGradeId = txtId1.getText();

        try {
            boolean isDeleted = cinnamonGradesModel.deleteCgrade(cGradeId);

            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, " cgrade deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Parent anchorPane = FXMLLoader.load(this.getClass().getResource("/view/stock_form.fxml"));
        Stage stage = (Stage) cGradePane.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("stockForm");
        stage.centerOnScreen();

    }
    }

*/
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
import lk.ijse.cinnamonProduction.dto.cinnamonGradesDto;
import lk.ijse.cinnamonProduction.dto.tm.cinnamonGradesTm;
import lk.ijse.cinnamonProduction.model.cinnamonGradesModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class cinnamonGradesFormController {

    @FXML
    private AnchorPane cGradePane;

    @FXML
    private TableColumn<?, ?> col1kgPrice;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<cinnamonGradesDto> tableCinnamonGrade;

    @FXML
    private TextField txtId1;

    @FXML
    private TextField txtName1;

    @FXML
    private TextField txtPrice1;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String cGradeId = txtId1.getText();
        String cGradeName = txtName1.getText();
        double c1KgPrice = Double.parseDouble(txtPrice1.getText());

        var dto = new cinnamonGradesDto(cGradeId, cGradeName, c1KgPrice);

        var model = new cinnamonGradesModel();
        try {
            boolean isSaved = model.saveCgrade(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "cGrade saved!").show();
                //clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Parent anchorPane = FXMLLoader.load(this.getClass().getResource("/view/stock_form.fxml"));
        Stage stage = (Stage) cGradePane.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("stockForm");
        stage.centerOnScreen();

    }



    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String cGradeId = txtId1.getText();

        try {
            boolean isDeleted = cinnamonGradesModel.deleteCgrade(cGradeId);

            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, " cgrade deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String cGradeId = txtId1.getText();
        String cGradeName = txtName1.getText();
        double c1KgPrice = Double.parseDouble(txtPrice1.getText());

        var Tm=new cinnamonGradesTm(cGradeId,cGradeName,c1KgPrice);
        var model=new cinnamonGradesModel();

        try {
            boolean isUpdated = model.updateCgrade(Tm);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "upadte cGrade").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void  initialize(){
        loadAllCinnamonGrades();
        cetCellValueFactory();
    }
    private void loadAllCinnamonGrades(){
        var model = new cinnamonGradesModel();

        ObservableList<cinnamonGradesDto> obList = FXCollections.observableArrayList();

        try {
            List<cinnamonGradesDto> dtoList = model.getAllCinnamonGrades();

            for(cinnamonGradesDto dto : dtoList) {
                obList.add(
                        new cinnamonGradesDto(
                                dto.getCGradeId(),
                                dto.getCGradeName(),
                                dto.getC1KgPrice()

                        )
                );
            }
            tableCinnamonGrade.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void cetCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("cGradeId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("cGradeName"));
        col1kgPrice.setCellValueFactory(new PropertyValueFactory<>("c1KgPrice"));
    }

    public void cGradeOnAction(ActionEvent actionEvent) {
        String id = txtId1.getText();

        var model = new cinnamonGradesModel();
        try {
            cinnamonGradesDto dto = model.searchCgrade(id);

            if (dto != null){
                fillFields(dto);
            }else{
                new Alert(Alert.AlertType.INFORMATION, "cGrade not found").show();
            }

            } catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        }

    private void fillFields(cinnamonGradesDto dto) {
        txtId1.setText(dto.getCGradeId());
        txtName1.setText(dto.getCGradeName());
        txtPrice1.setText(String.valueOf(dto.getC1KgPrice()));
    }
    }
