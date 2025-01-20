
import program.modelo.Tabuleiro;
import program.visao.TabuleiroConsole;


public class Aplicacao {

    public static void main(String[] args) {
        Tabuleiro tabuleiro = Tabuleiro.setParametros(); // Configuração do tabuleiro
        TabuleiroConsole tabuleiroConsole = new TabuleiroConsole(tabuleiro); // Inicialização do tabuleiro.
    }
}
