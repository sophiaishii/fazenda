package com.sophia;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import modelo.Usuario;
import util.Dao;

public class CadastroControle {

    @FXML
    private TextField campoLogin;

    @FXML
    private TextField campoNome;

    @FXML
    private PasswordField campoSenha;
    
    private List<Usuario> usuariosCadastrados;

    private Dao<Usuario> dao;

    @FXML
    private void fazerCadastro() {
        String login = campoLogin.getText();
        String nome = campoNome.getText();
        String senha = campoSenha.getText();
        Usuario usuario = new Usuario(login, nome, senha);

        dao = new Dao(Usuario.class); 
        usuariosCadastrados = dao.listarTodos(); // Busca todos os usuários cadastrados

        if (login.isEmpty() || nome.isEmpty() || senha.isEmpty()) {
            exibirErro("Por favor, preencha todos os campos.");
            return;
        } 

        // Verifica se o login já existe
        for (Usuario u : usuariosCadastrados) {
            if (u.getLogin().equals(login)) { 
                exibirErro("Este login já está em uso.");
                return;
            }
        }
        dao.inserir(usuario);
        // Limpa os campos
        campoLogin.clear();
        campoNome.clear();
        campoSenha.clear();
        exibirSucesso("Usuário cadastrado com sucesso!");
    }

    // Método para exibir pop-up de erro
    private void exibirErro(String mensagem) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro de Cadastro");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    // Método para exibir pop-up de sucesso
    private void exibirSucesso(String mensagem) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Cadastro Realizado");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    @FXML
    private void moverParaLogin() throws IOException {
        App.setRoot("login");
    }
}
