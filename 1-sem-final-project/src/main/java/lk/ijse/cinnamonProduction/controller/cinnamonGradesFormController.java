
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
import lk.ijse.cinnamonProduction.bo.custom.Impl1.cinnamonGradesBOImpl;
import lk.ijse.cinnamonProduction.bo.custom.cinnamonGradesBO;
import lk.ijse.cinnamonProduction.dto.cinnamonGradesDto;
import lk.ijse.cinnamonProduction.entity.cinnamonGrades;

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


    cinnamonGradesBO CinnamonGradesBO = new cinnamonGradesBOImpl();
    //lk.ijse.cinnamonProduction.dao.custom.cinnamonGradesDAO cinnamonGradesDAO = new cinnamonGradesModel();

    @FXML
    void btnAddOnAction(ActionEvent event)  {
        String cGradeId = txtId1.getText();
        String cGradeName = txtName1.getText();
        double c1KgPrice = Double.parseDouble(txtPrice1.getText());

       // var dto = new cinnamonGrades(cGradeId, cGradeName, c1KgPrice);

       // var model = new cinnamonGradesModel();
        try {
         //   boolean isSaved = model.saveCgrade(dto);
            boolean isSaved =   CinnamonGradesBO.saveCinnamonGrades( new cinnamonGradesDto(cGradeId, cGradeName, c1KgPrice));

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "cGrade saved!").show();
                //clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
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
            boolean isDeleted = CinnamonGradesBO.deleteCinnamonGrades(cGradeId);

            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, " cgrade deleted!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String cGradeId = txtId1.getText();
        String cGradeName = txtName1.getText();
        double c1KgPrice = Double.parseDouble(txtPrice1.getText());

       // var Tm=new cinnamonGradesTm(cGradeId,cGradeName,c1KgPrice);
       // var model=new cinnamonGradesModel();

        try {
            boolean isUpdated = CinnamonGradesBO.updateCinnamonGrades( new cinnamonGradesDto(cGradeId, cGradeName, c1KgPrice));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "upadte cGrade").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void  initialize(){
        loadAllCinnamonGrades();
        cetCellValueFactory();
    }
    private void loadAllCinnamonGrades(){
      //  var model = new cinnamonGradesModel();

        ObservableList<cinnamonGradesDto> obList = FXCollections.observableArrayList();

        try {
            List<cinnamonGradesDto> dtoList = CinnamonGradesBO.getAllCinnamonGrades();

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
        } catch (SQLException | ClassNotFoundException e) {
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

      //  var model = new cinnamonGradesModel();
        try {
            cinnamonGradesDto dto = CinnamonGradesBO.searchCinnamonGrades(id);

            if (dto != null){
                fillFields(dto);
            }else{
                new Alert(Alert.AlertType.INFORMATION, "cGrade not found").show();
            }

            } catch (SQLException | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        }

    private void fillFields(cinnamonGradesDto dto) {
        txtId1.setText(dto.getCGradeId());
        txtName1.setText(dto.getCGradeName());
        txtPrice1.setText(String.valueOf(dto.getC1KgPrice()));
    }
    }
