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
import modelo.Usuario;
import util.Dao;

public class UsuarioAlterarControle {

    @FXML
    private ComboBox<Usuario> comboUsuarios; // ComboBox para selecionar o usuário

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoLogin;

    @FXML
    private TextField campoSenha;

    private Dao<Usuario> daoUsuario;
    private Usuario usuarioSelecionado;

    @FXML
    private void initialize() {
        daoUsuario = new Dao<>(Usuario.class);

        // Carrega os usuários cadastrados no ComboBox
        List<Usuario> usuariosCadastrados = daoUsuario.listarTodos();
        ObservableList<Usuario> itensComboBox = FXCollections.observableArrayList(usuariosCadastrados);
        comboUsuarios.setItems(itensComboBox);
    }

    @FXML
    private void atualizaCampos() {
        usuarioSelecionado = comboUsuarios.getValue();
        if (usuarioSelecionado != null) {
            campoNome.setText(usuarioSelecionado.getNome());
            campoLogin.setText(usuarioSelecionado.getLogin());
            campoSenha.setText(usuarioSelecionado.getSenha());
        }
    }

    @FXML
    private void gravar() throws IOException {
        if (usuarioSelecionado == null) {
            mostrarErro("Por favor, selecione um usuário.");
            return;
        }

        if (campoNome.getText().isBlank() || campoLogin.getText().isBlank() || campoSenha.getText().isBlank()) {
            mostrarErro("Preencha todos os campos obrigatórios.");
            return;
        }

        // Atualiza os dados do usuário
        Usuario novoUsuario = new Usuario(
                campoLogin.getText(),
                campoNome.getText(),
                campoSenha.getText()
        );

        daoUsuario.alterar("login", usuarioSelecionado.getLogin(), novoUsuario);

        mostrarSucesso("Usuário atualizado com sucesso!");
        App.setRoot("menu");
       
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
        App.setRoot("menu");
    }
}
