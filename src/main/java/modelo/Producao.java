package modelo;

import java.time.LocalDate;

import org.bson.codecs.pojo.annotations.BsonProperty;

public class Producao {
    
    @BsonProperty(value="vaca")
    private Vaca vaca;

    @BsonProperty(value="data")
    private LocalDate data;

    @BsonProperty(value="quantidade")
    private double quantidade;

    public Producao(){}

    public Producao(Vaca vaca, LocalDate data, double quantidade){
        this.vaca = vaca;
        this.data = data;
        this.quantidade = quantidade;
    }

    public Vaca getVaca() {
        return vaca;
    }

    public void setVaca(Vaca vaca) {
        this.vaca = vaca;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

}
