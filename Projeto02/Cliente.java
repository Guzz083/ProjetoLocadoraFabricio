package Projeto02;

import java.text.ParseException;

public class Cliente extends Pessoa {
    private String endereco;

    public Cliente(String nome, String cpf, String endereco) throws ParseException {
        super(nome, cpf);

        this.endereco = endereco;
    }

    public String getEnde() { return endereco; }
    public void setEnde(String nome) { this.endereco = endereco; }


    @Override
    public String getTipo() { return "CLIENTE"; }

    @Override
    public String toString() {
        return super.toString() + " | Enderço: " + endereco;
    }
}