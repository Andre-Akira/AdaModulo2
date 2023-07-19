package org.ada;


import org.ada.dto.AirQuality;
import org.ada.map.Mapa;
import org.ada.sort_collections.Ordenacao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class CadastroDeRegistroAr {
    private int ultimoIdRegistro = 0;
    private List<AirQuality> listaDeAirQualities = new LinkedList<>();
    private Mapa<Integer, AirQuality> mapaIdRegistro = new Mapa<>();
    private Mapa<String, AirQuality> mapaRegistroPorNome = new Mapa<>();

    private final EntradaDeDados leitor;
    private final String DIGITE_OPCAO_DESEJADA = "Digite a opção desejada: ";
    private final String OPCAO_SAIR = "x";
    private final String OPCAO_CADASTRAR_REGISTRO = "1";
    private final String OPCAO_LISTAR_REGISTROS = "2";
    private final String OPCAO_CADASTRAR_EM_LOTE = "3";
    private final String OPCAO_BUSCA_POR_ID = "4";
    private final String OPCAO_BUSCA_POR_STATION = "5";

    private final String OPCAO_ORDENACAO = "6";

    public CadastroDeRegistroAr(EntradaDeDados leitor){
        this.leitor = leitor;
        iniciaApp();
    }

    public void processar(){

            String opcaoDigitada = obterEntradaDoUsuario(leitor);

            while(!escolheuSair(opcaoDigitada)){
                tratarOpcaoSelecionada(opcaoDigitada);
                opcaoDigitada = obterEntradaDoUsuario(leitor);
            }

        finalizaApp();

    }

    private void tratarOpcaoSelecionada(String opcaoDigitada) {
        switch (opcaoDigitada){
            case OPCAO_SAIR:
                break;
            case OPCAO_CADASTRAR_REGISTRO:
                this.inserirRegistro(construirRegistro(++ultimoIdRegistro, leitor));
                System.out.println("Cadastro realizado com sucesso!");
                pularLinha(2);
                break;
            case OPCAO_LISTAR_REGISTROS:
                listarRegistro();
                pularLinha(2);
                break;
            case OPCAO_CADASTRAR_EM_LOTE:
                carregarRegistroEmLote();
                break;
            case OPCAO_BUSCA_POR_ID:
                buscaPorIdHashMap();
                break;
            case OPCAO_BUSCA_POR_STATION:
                buscaPorTitleHashMap();
                break;
            case OPCAO_ORDENACAO:
                classificar();
                break;
            default:
                opcaoInvalida();
                break;
        }
    }

    private List<AirQuality> removerDuplicados(List<AirQuality> lista){
        return new ArrayList<>(new HashSet<>(lista));
    }

    private void carregarRegistroEmLote(){
        List<AirQuality> novosAirQualities =
                new CarregarDadosExternos().carregarRegistroAr();

        this.inserirRegistro(removerDuplicados(novosAirQualities));

    }

    private void inserirRegistro(List<AirQuality> airQualities){
        for (AirQuality airQuality : airQualities){
            inserirRegistro(airQuality);
        }
    }

    private void classificar(){
        System.out.print("Classificando: ");
        // Criar uma instância da classe Ordenacao
        Ordenacao<AirQuality> ordenacao = new Ordenacao<>();

        List<AirQuality> listaOrdenada = ordenacao.classificar(this.listaDeAirQualities);

        // Imprimir a lista ordenada
        for (AirQuality filme : listaOrdenada) {
            System.out.println(filme);
        }

    }

    private void inserirRegistro(AirQuality airQuality){
        this.listaDeAirQualities.add(airQuality);
        this.mapaIdRegistro.add(airQuality.id(), airQuality);
        String title = airQuality.Station().toLowerCase();
        this.mapaRegistroPorNome.add(title, airQuality);

    }
    public void pularLinha(int numeroDeLinhas){
        for (int i = 1; i <= numeroDeLinhas; i++) {
            System.out.println();
        }
    }

    private void buscaPorId(){
        System.out.print("Digite o id do Registro: ");
        Integer id = leitor.obterEntradaAsInt();
        for (AirQuality airQuality : listaDeAirQualities){
            System.out.println("Pesquisou na Lista: " + id);
            if(id.equals(airQuality.id())){
                System.out.println("Registro localizado!");
                System.out.println(airQuality);
                return;
            }
        }
        System.out.println("Nenhum Registro localizado para o id: " + id);
    }

    private void buscaPorIdHashMap(){
        System.out.print("Digite o id do Registro: ");
        Integer id = leitor.obterEntradaAsInt();
        AirQuality airQuality = this.mapaIdRegistro.get(id);
        if(airQuality != null){
            System.out.println("Registro localizado!");
            System.out.println(airQuality);
        } else {
            System.out.println("Nenhum Registro localizado para o id: " + id);
        }
    }

    private void buscaPorTitleHashMap(){
        System.out.print("Digite o titulo do Registro: ");
        String title = leitor.obterEntrada().toLowerCase();
        AirQuality airQuality = this.mapaRegistroPorNome.get(title);
        if(airQuality != null){
            System.out.println("Registro(s) localizado(s)!");
            System.out.println(airQuality);

        } else {
            System.out.println("Nenhum Registro localizado para o nome: " + title);
        }
    }

    private void listarRegistro(){
        StringBuilder sb = new StringBuilder();

        if (listaDeAirQualities.isEmpty()) {
            sb.append("[]");
        } else {
            sb.append("[\n");
            for (AirQuality funcionario : listaDeAirQualities) {
                sb.append("\t").append(funcionario).append(",\n");
            }
            sb.setLength(sb.length() - 2); // Remover a vírgula extra após o último funcionário
            sb.append("\n]");
        }

        System.out.println(sb);
    }

    private boolean escolheuSair(String opcaoDigitada){
        return opcaoDigitada.equals(OPCAO_SAIR);
    }

    private String obterEntradaDoUsuario(EntradaDeDados leitor){
        carregaMenu();
        System.out.print(DIGITE_OPCAO_DESEJADA);
        return leitor.obterEntrada().toLowerCase();
    }

    private AirQuality construirRegistro(Integer id, EntradaDeDados leitor){
        System.out.println("Cadastro de novo Registro");
        System.out.print("Informe o Station: ");
        String station = leitor.obterEntrada();
        System.out.print("Informe o Benzene: ");
        String benzene = leitor.obterEntrada();
        System.out.print("Informe o CO: ");
        String CO = leitor.obterEntrada();
        System.out.print("Informe o PM10: ");
        String PM10 = leitor.obterEntrada();
        System.out.print("Informe o NO2: ");
        String NO2 = leitor.obterEntrada();
        return new AirQuality(id, station, benzene, CO, PM10, NO2 );
    }

    private void finalizaApp(){
        System.out.println("Até logo!!");
    }

    private void opcaoInvalida(){
        System.out.println("Opção INVÁLIDA. Tente novamente.");
    }

    private void iniciaApp(){
        carregaNomeApp();
    }

    private void carregaMenu() {
        System.out.println("********  DIGITE A OPÇÃO DESEJADA   ******");
        System.out.println("1 - CADASTRAR REGISTRO");
        System.out.println("2 - LISTAR REGISTROS");
        System.out.println("3 - CADASTRO EM LOTE (CSV)");
        System.out.println("4 - PESQUISAR POR ID");
        System.out.println("5 - PESQUISAR POR STATION");
        System.out.println("6 - ORDERNAR LISTA");
        System.out.println("X - SAIR");
    }

    private void carregaNomeApp(){
        System.out.println("******************************************");
        System.out.println("******* CADASTRO DE REGISTRO ***************");
        System.out.println("******************************************");
    }

}
