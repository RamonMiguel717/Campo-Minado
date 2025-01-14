package program.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class tabuleiro {

private  int colunas;
private  int linhas;
private  int minas;

    private final List<Campo> campos = new ArrayList<>();

    public tabuleiro(int colunas, int linhas, int minas) {
        this.colunas = colunas;
        this.linhas = linhas;
        this.minas = minas;

        gerarCampos();
        associarVizinhos();
        sortearMinas();
    }
    public void abrir(int linha, int coluna){
        campos.parallelStream().filter(campo -> campo.getLinha() == linha && campo.getColuna() == coluna)
                .findFirst().ifPresent(Campo::abrirCampo);
    }public void alterarMarcacao(int linha, int coluna){
        campos.parallelStream().filter(campo -> campo.getLinha() == linha && campo.getColuna() == coluna)
                .findFirst().ifPresent(Campo::alternarMarcacao);
    }


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
        long minasArmadas = 0;
        Predicate<Campo> minado = Campo::isMinado;

        do {
            minasArmadas = campos.stream().filter(minado).count();
            int aleatorio = (int)(Math.random() * campos.size());
            campos.get(aleatorio).minar();
        }while (minasArmadas <minas);
    }
    private boolean objetivoAlcancado(){
        return campos.stream().allMatch(Campo::objetivoAlcancado);
    }
    public void reiniciar(){
        campos.stream().forEach(Campo::reiniciar);
    }

    /*
    Modificarei a função ToString pois futuramente quero criar uma interface gráfica para este jogo, logo não será necesssario
    o uso da classe.
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (int l = 0; l < linhas ; l ++){
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
