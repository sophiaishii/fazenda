package com.sophia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import modelo.Usuario; // Modelo de usuário com login, nome e senha
import util.Dao;

public class UsuarioExcluirControle {

    @FXML
    private ComboBox<Usuario> comboUsuarios; // ComboBox para selecionar o usuário

    private Dao<Usuario> daoUsuario;
    private Usuario usuarioSelecionado; // Usuário que será excluído

    @FXML
    private void initialize() {
        daoUsuario = new Dao<>(Usuario.class);

        // Carrega os usuários cadastrados para o ComboBox
        List<Usuario> usuariosCadastrados = daoUsuario.listarTodos();
        List<String> logins = new ArrayList<>();
        for (Usuario usuario : usuariosCadastrados) {
            logins.add(usuario.getLogin());
        }
        comboUsuarios.getItems().setAll(usuariosCadastrados);
    }

    @FXML
    private void atualizaUsuarioSelecionado() {
        usuarioSelecionado = comboUsuarios.getValue();
    }

    @FXML
    private void excluirUsuario() throws IOException {
        if (usuarioSelecionado == null) {
            mostrarErro("Por favor, selecione um usuário.");
            return;
        }

        // Excluir o usuário selecionado
        boolean sucesso = daoUsuario.excluir("login", usuarioSelecionado.getLogin());

        if (sucesso) {
            mostrarSucesso("Usuário excluído com sucesso.");
            App.setRoot("menu");
        } else {
            mostrarErro("Erro ao excluir o usuário.");
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
