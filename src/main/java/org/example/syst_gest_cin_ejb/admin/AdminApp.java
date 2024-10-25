package org.example.syst_gest_cin_ejb.admin;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.syst_gest_cin_ejb.ejb.CDManagementService;
import org.example.syst_gest_cin_ejb.entity.CD;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

public class AdminApp extends Application {

    private CDManagementService cdManagementService;
    private TableView<CD> cdTable;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            InitialContext ctx = new InitialContext();
            cdManagementService = (CDManagementService) ctx.lookup("java:global/YourProjectName/CDManagementService");
        } catch (NamingException e) {
            e.printStackTrace();
        }

        primaryStage.setTitle("Gestion des CDs - Administrateur");

        cdTable = new TableView<>();
        TableColumn<CD, String> titleColumn = new TableColumn<>("Titre");
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        TableColumn<CD, Boolean> availableColumn = new TableColumn<>("Disponible");
        availableColumn.setCellValueFactory(cellData -> cellData.getValue().availableProperty().asObject());

        cdTable.getColumns().addAll(titleColumn, availableColumn);

        Button listCDsBtn = new Button("Lister tous les CDs");
        listCDsBtn.setOnAction(e -> listAllCDs());

        VBox vbox = new VBox(10, cdTable, listCDsBtn);
        Scene scene = new Scene(vbox, 400, 300);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void listAllCDs() {
        List<CD> cds = cdManagementService.listAllCDs();
        ObservableList<CD> cdObservableList = FXCollections.observableArrayList(cds);
        cdTable.setItems(cdObservableList);
    }
}
