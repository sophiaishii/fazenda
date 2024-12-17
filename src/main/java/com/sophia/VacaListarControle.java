package com.sophia;

import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Vaca;
import util.Dao;

public class VacaListarControle {

    @FXML
    private TableView<Vaca> tableVacas; // TableView para exibir as vacas

    @FXML
    private TableColumn<Vaca, String> colBrinco; // Coluna para Brinco
    @FXML
    private TableColumn<Vaca, String> colNome;   // Coluna para Nome
    @FXML
    private TableColumn<Vaca, String> colRaca;   // Coluna para Raça

    private Dao<Vaca> daoVaca;

    @FXML
    private void initialize() {
        daoVaca = new Dao<>(Vaca.class);

        // Inicializa as colunas do TableView
        colBrinco.setCellValueFactory(new PropertyValueFactory<>("brinco"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colRaca.setCellValueFactory(new PropertyValueFactory<>("raca"));

        // Preenche o TableView com as vacas cadastradas
        List<Vaca> vacasCadastradas = daoVaca.listarTodos();
        ObservableList<Vaca> vacasObservableList = FXCollections.observableArrayList(vacasCadastradas);
        tableVacas.setItems(vacasObservableList);
    }

    @FXML
    public void voltarMenu() {
        try {
            App.setRoot("menu"); // Volta para o menu principal
        } catch (IOException e) {
            mostrarErro("Erro ao voltar ao menu: " + e.getMessage());
        }
    }

    private void mostrarErro(String mensagem) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
