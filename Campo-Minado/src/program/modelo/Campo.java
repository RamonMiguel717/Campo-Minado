package program.modelo;

import program.excecao.ExcessaoExplosao;

import java.util.ArrayList;
import java.util.List;

public class Campo {

    private boolean aberto = false;
    private boolean marcado = false;
    private boolean minado;
    private int linha;
    private int coluna;

    private List<Campo> vizinhos = new ArrayList<>();

    public Campo(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    public boolean adicionarVizinho(Campo vizinho) {
        boolean linhaDif = linha != vizinho.linha;
        boolean colunaDif = coluna != vizinho.coluna;
        boolean diagonal = linhaDif && colunaDif;

        int varLinha = Math.abs(linha - vizinho.linha);
        int varColuna = Math.abs(coluna - vizinho.coluna);

        if (varLinha <= 1 && varColuna <= 1 && (linhaDif || colunaDif)) {
            vizinhos.add(vizinho);
            return true;
        }
        return false;
    }
/*   Metodo que altena a marcação caso haja uma bomba que não foi aberta ainda. */
    public boolean alternarMarcacao() {
        if (!aberto) {
            marcado = !marcado;
        }
        return marcado;
    }

    public boolean abrirCampo() {
        if (!aberto && !marcado) {
            aberto = true;

            if (minado) {
                throw new ExcessaoExplosao(); // Defina essa exceção ou importe uma existente.
            }

            if (vizinhancaSegura()) {
                vizinhos.forEach(Campo::abrirCampo);
            }
            return true;
        }
        return false;
    }

    public boolean vizinhancaSegura() {
        return vizinhos.stream().noneMatch(v -> v.minado);
    }

    public boolean isMarcado() {
        return marcado;
    }

    // Getter e setter para atributos, se necessário
    public boolean isAberto() {
        return aberto;
    }
    public boolean isMinado(){
        return minado;
    }

    void setAberto(boolean aberto){
        this.aberto = !aberto;
    }

    public void setMinado(boolean minado) {
        this.minado = minado;
    }

    public boolean minar(){
        return  minado = true;
    }

    //Getters para lnha e coluna
    public int getLinha(){
        return linha;
    }
    public int getColuna(){
        return coluna;
    }

    //Metodo que define se o usuário alcançou seu objetivo
    public boolean objetivoAlcancado(){

/*
O objetivo só pode ser alcançado caso:

    - O campo não minado esteja aberto
    - O campo minado esteja marcado
 */
        boolean desvendado = !minado && aberto;
        boolean protegido = minado && marcado;
        return desvendado || protegido;

    }
// Esta é a função responsável por fazer a contagem da quantidade de minas na proximidade.
    long minasNaVizinhanca(){
        return vizinhos.stream().filter(v->v.minado).count();
    }
    public void reiniciar(){
        aberto = false;
        minado = false;
        marcado = false;
    }
    //Método que cria o mapa com os simbolos.
    public String toString(){
        if(marcado){
            return "x";
        } else if (aberto && minado) {
            return "*";
        } else if (aberto && minasNaVizinhanca()>0) {
            return Long.toString(minasNaVizinhanca());
        } else if (aberto) {
            return " ";
        }else return "?";
    }
}
