package com.sophia;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Producao;
import modelo.Vaca;
import util.Dao;

public class ProducaoListarControle {

    @FXML
    private TableView<Producao> tabelaProducao;

    @FXML
    private TableColumn<Producao, String> colVaca;

    @FXML
    private TableColumn<Producao, String> colData;

    @FXML
    private TableColumn<Producao, Double> colQuantidade;

    private Dao<Vaca> daoVaca;

    private Dao<Producao> daoProducao;

    private Vaca vacaSelecionada;

    @FXML
    private ComboBox<Vaca> comboVacas;

    List<Producao> producoes;

    @FXML
    public void initialize() {
        daoVaca = new Dao<>(Vaca.class);
        daoProducao = new Dao<>(Producao.class);
        
        List<Vaca> vacasCadastradas = daoVaca.listarTodos();
        comboVacas.getItems().setAll(vacasCadastradas); // Preenche combobox
        
    }

    @FXML
    public void voltarMenu() {
        try {
            App.setRoot("menu"); // Volta para o menu principal
        } catch (IOException e) {
            mostrarErro("Erro ao voltar ao menu: " + e.getMessage());
        }
    }

    @FXML
    public void filtrarPorVaca() {
        tabelaProducao.getItems().clear();
        vacaSelecionada = comboVacas.getValue();
        if (vacaSelecionada == null) {
            mostrarErro("Por favor, selecione uma vaca.");
            return;
        }

        // Configura as colunas da tabela
        colData.setCellValueFactory(new PropertyValueFactory<>("data")); 
        colQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade")); 

        producoes = daoProducao.listarTodos();
        Vaca temp; 
        for (Producao producao : producoes) { // Preenche a tabela com as produções da vaca selecionada
            temp = producao.getVaca();
            if (temp.getBrinco().equals(vacaSelecionada.getBrinco())) {
                tabelaProducao.getItems().add(producao);
            }
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
