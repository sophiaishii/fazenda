package com.sophia;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;

public class MenuControle {
    
    @FXML
    private void producaoExcluir() throws IOException{
        App.setRoot("producaoExcluir");
    }

    @FXML
    private void producaoIncluir() throws IOException{
        App.setRoot("producao");
    }
    
    @FXML
    private void producaoListarPorVaca() throws IOException{
        App.setRoot("producaoListar");
    }

    @FXML
    private void producaoListarPorData() throws IOException{
        App.setRoot("producaoListarData");
    }

    @FXML
    private void producaoListarPorMes() throws IOException{
        App.setRoot("producaoListarMes");
    }

    @FXML
    private void producaoListarPorVacaEMes() throws IOException{
        App.setRoot("producaoListarMesEVaca");
    }

    @FXML
    private void usuarioExcluir() throws IOException{
        App.setRoot("usuarioExcluir");
    }

    @FXML
    private void usuarioAlterar() throws IOException{
        App.setRoot("usuarioAlterar");
    }

    @FXML
    private void usuarioListar() throws IOException{
        App.setRoot("usuarioListar");
    }
    
    @FXML
    private void sair(){
        Platform.exit();
        System.exit(0);
    }
    
    @FXML
    private void vacaAlterar() throws IOException{
        App.setRoot("vacaAlterar");
    }
    
    @FXML
    private void vacaIncluir() throws IOException{
        App.setRoot("vacaIncluir");
    }

    @FXML
    private void vacaExcluir() throws IOException{
        App.setRoot("vacaExcluir");
    }

    @FXML
    private void vacaListar() throws IOException{
        App.setRoot("vacaListar");
    }
     
    
}