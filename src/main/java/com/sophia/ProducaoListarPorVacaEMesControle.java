package com.sophia;

import java.io.IOException;
import java.time.Month;
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

public class ProducaoListarPorVacaEMesControle {

    @FXML
    private TableView<Producao> tabelaProducao;

    @FXML
    private TableColumn<Producao, String> colData;

    @FXML
    private TableColumn<Producao, Double> colQuantidade;

    @FXML
    private ComboBox<Vaca> comboVacas;

    @FXML
    private ComboBox<Month> comboMeses;

    private Dao<Vaca> daoVaca;
    private Dao<Producao> daoProducao;

    @FXML
    public void initialize() {
        daoVaca = new Dao<>(Vaca.class);
        daoProducao = new Dao<>(Producao.class);

        // Carregar vacas no ComboBox
        List<Vaca> vacasCadastradas = daoVaca.listarTodos();
        comboVacas.getItems().setAll(vacasCadastradas);

        // Carregar meses no ComboBox
        comboMeses.getItems().setAll(Month.values());
    }

    @FXML
    public void filtrarPorVacaEMes() {
        tabelaProducao.getItems().clear();

        Vaca vacaSelecionada = comboVacas.getValue();
        Month mesSelecionado = comboMeses.getValue();
        
        if (vacaSelecionada == null || mesSelecionado == null) {
            mostrarErro("Por favor, selecione uma vaca e um mês.");
            return;
        }

        colData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

        // Filtrar produções pela vaca e mês
        List<Producao> producoes = daoProducao.listarTodos();
        Vaca temp;
        for (Producao producao : producoes) {
            temp = producao.getVaca();
            // Se a produção for da vaca selecionada e do mês selecionado, adicionar na tabela
            if (temp.getBrinco().equals(vacaSelecionada.getBrinco()) && producao.getData().getMonth().equals(mesSelecionado)) {
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
