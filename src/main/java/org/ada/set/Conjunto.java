package org.ada.set;

import org.ada.map.Mapa;
import org.ada.map.NoMap;

import java.util.Iterator;
import java.util.List;

public class Conjunto<T> implements Iterable<T> {

    private Mapa<T, Object> mapa;
    private static Object OBJETO_VAZIO = new Object();

    public Conjunto(){
        this.mapa = new Mapa<>();
    }
    public Conjunto(List<T> dados){
        this();
        for (T dado : dados){
            this.mapa.add(dado, OBJETO_VAZIO);
        }
    }

    public void add(T dado){
        this.mapa.add(dado, OBJETO_VAZIO);
    }

    public boolean contem(T dado){
        return this.mapa.get(dado) != null;
    }


    @Override
    public Iterator<T> iterator() {
        return new ConjuntoIterator(this.mapa);
    }


    private class ConjuntoIterator<T> implements Iterator<T> {

        private Iterator iterator;

        public ConjuntoIterator(Mapa mapa){
            this.iterator = mapa.iterator();
        }

        @Override
        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        @Override
        public T next() {
            NoMap<T, Object> no = (NoMap<T, Object>) this.iterator.next();
            return no.getChave();
        }
    }
}
