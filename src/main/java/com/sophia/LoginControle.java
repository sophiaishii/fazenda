package com.sophia;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import modelo.Usuario;
import util.Dao;

public class LoginControle {

    @FXML
    private TextField campoLogin;

    @FXML
    private PasswordField campoSenha;

    private Dao<Usuario> dao;

    @FXML
    private void fazerLogin() throws IOException {
        String login = campoLogin.getText();
        String senha = campoSenha.getText();

        dao = new Dao(Usuario.class);

        
        if (login.isEmpty() || senha.isEmpty()) { // Verifica se o login e a senha não estão vazios
            exibirErro("Por favor, preencha todos os campos.");
        } else {
            // Verifica se o login e a senha tem no banco de dados
            Usuario usuario = dao.buscarPorChave("login", login);
            if (usuario != null && usuario.getSenha().equals(senha)) {
                App.setRoot("menu");
            } else {
                exibirErro("Login ou senha inválidos!");
            }
        }
    }
     private void exibirErro(String mensagem) {
        // Cria o Alert de erro
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro de Login");
        alert.setHeaderText(null);  // Tira o título (opcional)
        alert.setContentText(mensagem); // Mensagem de erro

        // Exibe o pop-up
        alert.showAndWait();
    }

    @FXML
    private void fazerCadastro() throws IOException {
        App.setRoot("cadastro");
    }
}
