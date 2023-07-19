package org.ada.map;

import org.ada.linkedlist.ListaLigada;

import java.util.Iterator;

public class MapaIterator<K, V> implements Iterator<NoMap<K, V>> {

    private int indiceAtual;
    private Iterator<NoMap<K, V>> listaIterator;
    private Mapa<K, V> mapa;

    public MapaIterator(Mapa<K, V> mapa) {
        this.mapa = mapa;
        indiceAtual = 0;
        listaIterator = null;
    }

    @Override
    public boolean hasNext() {
        if (listaIterator != null) {
            boolean hasNext = listaIterator.hasNext();
            if(hasNext)
                return true;
        }

        while (indiceAtual < mapa.getTamanho()) {
            ListaLigada<NoMap<K, V>> lista = mapa.getTabela()[indiceAtual];
            indiceAtual++;
            if (!lista.isListaVazia()) {
                listaIterator = lista.iterator();
                return listaIterator.hasNext();
            }
        }

        return false;
    }

    @Override
    public NoMap<K, V> next() {
        return listaIterator.next();
    }

}
