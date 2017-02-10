/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.generatore;

/**
 *
 * @author palancaf
 */
public class Colonna {
    int ordine;
    String nome;
    ETipo tipo;
    IGenerator generator;

    public Colonna(int ordine, String nome,ETipo tipo, IGenerator generator){
        this.ordine=ordine;
        this.nome=nome;
        this.tipo= tipo;
        this.generator=generator;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ETipo getTipo() {
        return tipo;
    }

   

    public IGenerator getGenerator() {
        return generator;
    }

    public void setGenerator(IGenerator generator) {
        this.generator = generator;
    }

    public int getOrdine() {
        return ordine;
    }

}
