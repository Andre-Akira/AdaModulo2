package org.ada.sort_collections;

import java.util.ArrayList;
import java.util.List;

public class Ordenacao<T extends Comparable<T>> {

    public List<T> classificar(List<T> lista){

        if(lista.size() <= 1)
            return lista;

        int posicaoPivo = lista.size()-1;
        T pivo =  lista.get(posicaoPivo);
        List<T> menor = new ArrayList<>();
        List<T> maior = new ArrayList<>();

        for (int i = 0; i < posicaoPivo; i++) {
            if(pivo.compareTo(lista.get(i)) > 0){
                menor.add(lista.get(i));
            } else {
                maior.add(lista.get(i));
            }
        }

        return unir(classificar(menor), pivo , classificar(maior));

    }

    private List<T> unir(List<T> menor, T pivo, List<T> maior) {

        List<T> resultado = new ArrayList<>();

        for(T item : menor){
            resultado.add(item);
        }

        resultado.add(pivo);

        for(T item : maior){
            resultado.add(item);
        }

        return resultado;

    }

}
