package com.sophia;

import java.io.IOException;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Producao;
import util.Dao;

public class ProducaoListarPorMesControle {

    @FXML
    private TableView<Producao> tabelaProducao;

    @FXML
    private TableColumn<Producao, String> colData;

    @FXML
    private TableColumn<Producao, Double> colQuantidade;

    @FXML
    private ComboBox<Month> comboMeses;

    private Dao<Producao> daoProducao;

    @FXML
    public void initialize() {
        daoProducao = new Dao<>(Producao.class);

        // Carregar os meses no ComboBox
        comboMeses.getItems().setAll(Month.values());
    }

    @FXML
    public void filtrarPorMes() {
        tabelaProducao.getItems().clear();
        Month mesSelecionado = comboMeses.getValue();

        if (mesSelecionado == null) {
            mostrarErro("Por favor, selecione um mês.");
            return;
        }

        colData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

        // Buscar produções e filtrar pelo mês selecionado
        List<Producao> producoes = daoProducao.listarTodos();
        List<Producao> producoesFiltradas = producoes.stream()
                .filter(p -> p.getData().getMonth() == mesSelecionado) // Filtra pelo mês
                .collect(Collectors.toList()); // Converte para lista

        tabelaProducao.getItems().setAll(producoesFiltradas);
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
