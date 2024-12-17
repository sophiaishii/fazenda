package com.sophia;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import modelo.Usuario;
import util.Dao;

public class UsuarioIncluirControle {
    
    @FXML
    private TextField campoLogin;
    
    @FXML
    private TextField campoNome;
    
    @FXML
    private TextField campoSenha;
    
    private Usuario usuario;
    private Dao<Usuario> dao;
    
    @FXML
    private void initialize(){
        dao = new Dao(Usuario.class);
    }
    
    @FXML
    private void gravar(){
        usuario = new Usuario();
        usuario.setLogin(campoLogin.getText());
        usuario.setNome(campoNome.getText());
        usuario.setSenha(campoSenha.getText());
        dao.inserir(usuario);
    }
    
    @FXML
    private void cancelar() { 
        try {
            App.setRoot("menu");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}