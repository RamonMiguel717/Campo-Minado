import program.modelo.tabuleiro;
import program.visao.TabuleiroConsole;

public class Aplicacao {

    public static void main(String[] args){
        tabuleiro tabuleiro = new tabuleiro(6,6,6);

        new TabuleiroConsole(tabuleiro);
    }
}
