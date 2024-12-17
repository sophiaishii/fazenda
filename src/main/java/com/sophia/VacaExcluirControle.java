package com.sophia;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import modelo.Vaca;
import util.Dao;

public class VacaExcluirControle {

    @FXML
    private ComboBox<Vaca> comboVacas; // ComboBox para selecionar a vaca

    private Dao<Vaca> daoVaca;
    private Vaca vacaSelecionada; // Vaca que será excluída

    @FXML
    private void initialize() {
        daoVaca = new Dao<>(Vaca.class);

        // Carrega as vacas cadastradas para o ComboBox
        List<Vaca> vacasCadastradas = daoVaca.listarTodos();
        comboVacas.getItems().setAll(vacasCadastradas);
    }

    @FXML
    private void atualizaVacaSelecionada() {
        vacaSelecionada = comboVacas.getValue();
    }

    @FXML
    private void excluirVaca() throws IOException {
        if (vacaSelecionada == null) {
            mostrarErro("Por favor, selecione uma vaca.");
            return;
        }

        // Excluir a vaca selecionada
        boolean sucesso = daoVaca.excluir("brinco", vacaSelecionada.getBrinco());

        if (sucesso) {
            mostrarSucesso("Vaca excluída com sucesso.");
            App.setRoot("menu");
        } else {
            mostrarErro("Erro ao excluir a vaca.");
        }
    }

    private void mostrarErro(String mensagem) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void mostrarSucesso(String mensagem) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    @FXML
    public void voltarMenu() throws IOException {
        App.setRoot("menu"); // Redireciona para o menu principal
    }
}
