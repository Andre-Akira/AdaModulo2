package org.ada.linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaIterator<T> implements Iterator<T>{

    private No<T> atualNo;

    public ListaIterator(No<T> primeiroNo){
        this.atualNo = primeiroNo;
    }

    @Override
    public boolean hasNext() {
        return this.atualNo != null;
    }

    @Override
    public T next() {
        if(!this.hasNext()) throw new NoSuchElementException();
        No<T> atual = this.atualNo;
        this.atualNo = this.atualNo.getProximo();
        return atual.getDado();
    }
}
