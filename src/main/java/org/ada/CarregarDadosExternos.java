package org.ada;


import org.ada.dto.AirQuality;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CarregarDadosExternos {

    private static String CAMINHO_ARQUIVO = "src/main/resources/sp_air_quality.csv";

    public List<AirQuality> carregarRegistroAr(){

        List<AirQuality> airQualities = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(CAMINHO_ARQUIVO))) {

            br.readLine(); // Remover o cabeçalho

            String linha;
            while ((linha = br.readLine()) != null) {
                //String[] dados = linha.split(",");
                String[] partes = linha.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                int id = Integer.parseInt(partes[0]);
                String title = partes[1].replace("\"", "");
                String overview = partes[2].replace("\"", "");
                String original_language = partes[3];
                String vote_count = partes[4];
                String vote_average = partes[5];

                AirQuality airQuality = new AirQuality(id,title,overview,original_language,vote_count,vote_average );
                airQualities.add(airQuality);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return airQualities;

    }

}
