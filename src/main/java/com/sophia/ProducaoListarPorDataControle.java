package com.sophia;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Producao;
import util.Dao;

public class ProducaoListarPorDataControle {

    @FXML
    private TableView<Producao> tabelaProducao;

    @FXML
    private TableColumn<Producao, String> colVaca;

    @FXML
    private TableColumn<Producao, String> colData;

    @FXML
    private TableColumn<Producao, Double> colQuantidade;

    @FXML
    private DatePicker datePickerFiltro;

    private Dao<Producao> daoProducao;

    @FXML
    public void initialize() {
        daoProducao = new Dao<>(Producao.class);

        colData.setCellValueFactory(new PropertyValueFactory<>("data")); // Exibe data formatada
        colQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade")); // Exibe quantidade
        colVaca.setCellValueFactory(new PropertyValueFactory<>("vaca")); // Exibe vaca associada, se necessário
    }

    @FXML
    public void filtrarPorData() {
        tabelaProducao.getItems().clear();
        LocalDate dataSelecionada = datePickerFiltro.getValue();

        if (dataSelecionada == null) {
            mostrarErro("Por favor, selecione uma data.");
            return;
        }

        List<Producao> producoes = daoProducao.listarTodos(); // Busca todas as produções

        for (Producao producao : producoes) {
            if (producao.getData().equals(dataSelecionada)) {
                tabelaProducao.getItems().add(producao);
            }
        }
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
