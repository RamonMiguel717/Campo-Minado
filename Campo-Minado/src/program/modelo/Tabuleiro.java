package program.modelo;

import program.excecao.ExcessaoExplosao;
import program.validacao.ValidarInteiro;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Tabuleiro {

private  int colunas;
private  int linhas;
private  int minas;

    private final List<Campo> campos = new ArrayList<>();

    public Tabuleiro(int colunas, int linhas, int minas) {
        this.colunas = colunas;
        this.linhas = linhas;
        this.minas = minas;

        gerarCampos();
        associarVizinhos();
        sortearMinas();
    }
    public static Tabuleiro setParametros() {

        int tamanho = ValidarInteiro.validar("Digite o numero de linhas e colunas (LxL)");
        int bombas = ValidarInteiro.validar("Digite o numero de bombas: ");

        return new Tabuleiro(tamanho, tamanho, bombas);
    }
    // Função recebe linha/Coluna e abre o quadrado referente ao valor inserido.
    public void abrir(int linha, int coluna){
        try {
            campos.parallelStream().filter(campo -> campo.getLinha() == linha && campo.getColuna() == coluna)
                    .findFirst().ifPresent(Campo::abrirCampo);
        }catch (ExcessaoExplosao e ){
            campos.forEach(c -> c.setAberto(true));
            throw e;
        }
    }
    public void alterarMarcacao(int linha, int coluna){
        campos.parallelStream().filter(campo -> campo.getLinha() == linha && campo.getColuna() == coluna)
                .findFirst().ifPresent(Campo::alternarMarcacao);
    }
    //
    private void gerarCampos() {
        for(int linha = 0;linha < linhas; linha ++){
            for (int coluna = 0;coluna<colunas;coluna++){
                campos.add(new Campo(linha,coluna));
            }
        }
    }
    private void associarVizinhos() {
        for (Campo c1: campos){
            for (Campo c2:campos){
            c1.adicionarVizinho(c2);
            }
        }
    }
    private void sortearMinas() {
        long minasArmadas;
        Predicate<Campo> minado = Campo::isMinado;

        do {
            int aleatorio = (int)(Math.random() * campos.size());
            campos.get(aleatorio).minar();
            minasArmadas = campos.stream().filter(minado).count();

        }while (minasArmadas <minas);
        if (minas > colunas * linhas) {
            throw new IllegalArgumentException("Número de minas maior que o número de campos disponíveis.");
        }

    }
    public boolean objetivoAlcancado(){
        return campos.stream().allMatch(Campo::objetivoAlcancado);
    }
    public void reiniciar(){
        campos.forEach(Campo::reiniciar);
    }

    /*
    Modificarei a função ToString, pois futuramente quero criar uma "interface" gráfica para este jogo, logo não será necesssario
    o uso da classe.
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("  ");
        for(int c = 0; c< colunas; c++){
            sb.append(" ");
            sb.append(c);
            sb.append(" ");
        }
        sb.append("\n");

        int i = 0;
        for (int l = 0; l < linhas ; l ++){

            sb.append(l);
            sb.append(" ");
            for (int c = 0 ; c < colunas; c ++){
                sb.append(" ");
                sb.append(campos.get(i));
                sb.append(" ");
                i++;
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
