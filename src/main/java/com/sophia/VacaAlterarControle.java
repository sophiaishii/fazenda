package com.sophia;

import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import modelo.Vaca;
import util.Dao;

public class VacaAlterarControle {

    @FXML
    private ComboBox<Vaca> comboVacas;
    private Dao<Vaca> daoVaca;
    private Vaca selecionada;

    @FXML
    private TextField campoNome;
    @FXML
    private TextField campoRaca;
    
    @FXML
    private void initialize() {
        daoVaca = new Dao(Vaca.class);
        List<Vaca> vacasCadastradas = daoVaca.listarTodos();
        System.out.println(vacasCadastradas);
        ObservableList<Vaca> itensComboBox = FXCollections.observableArrayList(vacasCadastradas);
        comboVacas.setItems(itensComboBox);
    }
    
    @FXML
    private void atualizaCampos() {
        selecionada = comboVacas.getValue();
        campoNome.setText(selecionada.getNome());
        campoRaca.setText(selecionada.getRaca());
    }

    @FXML
    private void gravar() throws IOException {
        daoVaca = new Dao(Vaca.class);
        if (campoRaca.getText().isBlank()) {
            mostrarErro("Preencha os campos obrigatórios");
            return;
        }
        Vaca nova = new Vaca(selecionada.getBrinco(), campoNome.getText(), campoRaca.getText());
        daoVaca.alterar("brinco", selecionada.getBrinco(), nova);
        mostrarSucesso("Vaca cadastrada");
        App.setRoot("menu");
    }


    private void mostrarErro(String mensagem) {
        // Cria o Alert de erro
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);  // Tira o título (opcional)
        alert.setContentText(mensagem); // Mensagem de erro

        // Exibe o pop-up
        alert.showAndWait();
    }

    private void mostrarSucesso(String mensagem) {
        // Cria o Alert de erro
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText(null);  // Tira o título (opcional)
        alert.setContentText(mensagem); // Mensagem de erro

        // Exibe o pop-up
        alert.showAndWait();
    }

    @FXML
    public void voltarMenu() throws IOException {
        App.setRoot("menu");
    }
        
}
