package Projeto02;

public class Veiculo {

    public enum Tipo { CARRO, MOTO, VAN }
    public enum Status { DISPONIVEL, ALUGADO, MANUTENCAO }

    private String placa;
    private String marca;
    private String modelo;
    private int ano;
    private double diaria;
    private Tipo tipo;
    private Status status;

    public Veiculo(String placa, String marca, String modelo,
                   int ano, double diaria, Tipo tipo) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.diaria = diaria;
        this.tipo = tipo;
        this.status = Status.DISPONIVEL;
    }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }

    public double getDiaria() { return diaria; }
    public void setDiaria(double diaria) { this.diaria = diaria; }

    public Tipo getTipo() { return tipo; }
    public void setTipo(Tipo tipo) { this.tipo = tipo; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    @Override
    public String toString() {
        return "[" + tipo + "] " + marca + " " + modelo
                + " (" + ano + ") | Placa: " + placa
                + " | Diária: R$ " + String.format("%.2f", diaria)
                + " | Status: " + status;
    }
}