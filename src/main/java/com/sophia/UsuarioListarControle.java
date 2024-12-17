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
import modelo.Usuario;
import util.Dao;

public class UsuarioListarControle {

    @FXML
    private TableView<Usuario> tableUsuarios; 

    @FXML
    private TableColumn<Usuario, String> colLogin;
    @FXML
    private TableColumn<Usuario, String> colNome;
    @FXML
    private TableColumn<Usuario, String> colSenha;

    private Dao<Usuario> daoUsuario;

    @FXML
    private void initialize() {
        daoUsuario = new Dao<>(Usuario.class);

        // Inicializa as colunas do TableView
        colLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colSenha.setCellValueFactory(new PropertyValueFactory<>("senha"));

        // Preenche o TableView com os usuarios cadastradas
        List<Usuario> usuariosCadastrados = daoUsuario.listarTodos();
        ObservableList<Usuario> usuariosObservableList = FXCollections.observableArrayList(usuariosCadastrados);
        tableUsuarios.setItems(usuariosObservableList);
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
