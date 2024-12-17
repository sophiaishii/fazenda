package com.sophia;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import modelo.Producao;
import modelo.Vaca;
import util.Dao;

public class ProducaoControle {

    @FXML
    private ComboBox<Vaca> campoVaca;
    @FXML
    private DatePicker campoData;
    @FXML
    private TextField campoQuantidade;

    private Dao<Producao> daoProducao;
    private Dao<Vaca> daoVaca;

    // Inicializa o controlador
    public void initialize() {
        daoProducao = new Dao<>(Producao.class);
        daoVaca = new Dao<>(Vaca.class);
        
        // Preenche o ComboBox com todas as vacas
        List<Vaca> vacasCadastradas = daoVaca.listarTodos();
        campoVaca.getItems().addAll(vacasCadastradas); // Adiciona todas as vacas ao ComboBox
    }

    @FXML
    public void salvarProducao() {
        Vaca vacaSelecionada = campoVaca.getValue();
        LocalDate dataSelecionada = campoData.getValue();
        String quantidadeText = campoQuantidade.getText();
        
        if (vacaSelecionada == null || dataSelecionada == null || quantidadeText.isEmpty()) {
            mostrarErro("Por favor, preencha todos os campos!");
            return;
        }

        try {
            double quantidade = Double.parseDouble(quantidadeText); // Converte para double
            
            Producao producao = new Producao(vacaSelecionada, dataSelecionada, quantidade);
            daoProducao.inserir(producao);

            mostrarSucesso("Produção salva com sucesso!");
            limparCampos();
        } catch (NumberFormatException e) {
            mostrarErro("Quantidade em litros inválida.");
        }
    }

    // Método para limpar os campos
    private void limparCampos() {
        campoVaca.getSelectionModel().clearSelection();
        campoData.setValue(null);
        campoQuantidade.clear();
    }

    // Exibe um alerta de erro
    private void mostrarErro(String mensagem) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    // Exibe um alerta de sucesso
    private void mostrarSucesso(String mensagem) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    // Método para voltar ao menu
    @FXML
    public void voltarMenu() throws IOException {
        App.setRoot("menu");
    }
}
