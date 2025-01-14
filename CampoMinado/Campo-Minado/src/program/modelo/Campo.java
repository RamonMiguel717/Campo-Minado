package program.modelo;

import program.excecao.ExcessaoExplosao;

import java.util.ArrayList;
import java.util.List;

public class Campo {

    private boolean aberto = false;
    private boolean marcado;
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

    public void alternarMarcacao() {
        if (!aberto) {
            marcado = !marcado;
        }
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

    public void setMinado(boolean minado) {
        this.minado = minado;
    }

    public boolean minar(){
        return  minado = true;
    }
    public int getLinha(){
        return linha;
    }
    public int getColuna(){
        return coluna;
    }
    boolean objetivoAlcancado(){
        boolean desvendado = minado && !aberto;
        boolean protegido = minado && marcado;

        return desvendado || protegido;
    }
    long minasNaVizinhanca(){
        return vizinhos.stream().filter(v->v.minado).count();
    }
    void reiniciar(){
        aberto = false;
        minado = false;
        marcado = false;
    }
    public String toString(){
        if(marcado){
            return "x";
        } else if (aberto && minado) {
            return "*";
        } else if (aberto && minasNaVizinhanca()>0) {
            return Long.toString(minasNaVizinhanca());
        } else if (aberto) {
            return "";
        }else return "?";
    }
}
