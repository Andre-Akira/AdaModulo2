package org.ada.linkedlist;

import java.util.Iterator;

public class ListaLigada<T> implements Iterable<T> {

    private No<T> inicio;

    private No<T> ultimo;

    public ListaLigada(){
        this.inicio = null;
        this.ultimo = null;
    }

    public boolean isListaVazia(){
        return this.inicio == null;
    }

    public void adicionarNoComeco(T dado){
        if(this.inicio == null){
            this.adicionar(dado);
        } else {
            No<T> novoNo = new No(dado);
            No<T> primeiroElemento = this.inicio;
            novoNo.setProximo(primeiroElemento);
            this.inicio = novoNo;
        }
    }


    public void adicionar(T dado){

        No<T> novoNo = new No(dado);

        if(this.inicio == null){
            this.inicio = novoNo;
            this.ultimo = novoNo;
        } else {
            No ultimoElemento = this.ultimo;
            ultimoElemento.setProximo(novoNo);
            this.ultimo = novoNo;
        }
    }

    public T remover(T dado){

        if(this.inicio == null)
            return null;

        if(this.inicio.getDado().equals(dado)){
            No inicioAnterior = this.inicio;
            this.inicio = this.inicio.getProximo();
            inicioAnterior.setProximo(null);
            inicioAnterior.setDado(null);
            return dado;
        }

        No elementoAnterior = this.inicio;
        No elementoAtual = this.inicio.getProximo();

        while (elementoAtual != null){

            if (elementoAtual.getDado().equals(dado)){
                elementoAnterior.setProximo(elementoAtual.getProximo());
                elementoAtual.setProximo(null);
                elementoAtual.setDado(null);
                return dado;
            }

            elementoAnterior = elementoAtual;
            elementoAtual = elementoAtual.getProximo();
        }

        return null;

    }

    public void display(){
        System.out.println("[");

        No elementoAtual = this.inicio;

        while (elementoAtual != null){
            System.out.println("\t" + elementoAtual.getDado() + " ");
            elementoAtual = elementoAtual.getProximo();
        }
        System.out.println("]");

    }

    public T getInicio() {
        if (inicio == null)
            return null;

        return inicio.getDado();
    }

    @Override
    public Iterator<T> iterator() {
        return new ListaIterator<>(this.inicio);
    }

}
