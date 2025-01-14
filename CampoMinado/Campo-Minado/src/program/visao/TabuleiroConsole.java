package program.visao;
import program.excecao.ExcessaoExplosao;
import program.excecao.SairException;
import program.modelo.tabuleiro;

import java.util.Scanner;

public class TabuleiroConsole {

    private tabuleiro tabuleiro;
    private Scanner entrada = new Scanner(System.in);
    public TabuleiroConsole(tabuleiro tabuleiro){
        this.tabuleiro = tabuleiro;

        executarJogo();
    }

    private void executarJogo() {

        try{
            boolean continuar = true;

            while (continuar){
                loopJogo();

                System.out.println("Outra partida?(S/n)");
                String resposta = entrada.nextLine();
                if ("n".equalsIgnoreCase(resposta)){
                    continuar= false;
                }else{tabuleiro.reiniciar();}
            }
        }catch (SairException e){
            System.out.println("Saindo do jogo");
        }finally {
            entrada.close();
        }
    }

    private void loopJogo() {
        try{
            while (!tabuleiro.objetivoAlcancado()){
                System.out.println(tabuleiro);

                String digitado  = valorDigitado("Digite as linhas");
            }

            System.out.println("Você ganhou!");
        }catch (ExcessaoExplosao e){
            System.out.println("Você perdeu!");
        }
    }

    private String valorDigitado(String texto){
        System.out.print(texto);
        String digitado = entrada.nextLine();

        if ("sair".equalsIgnoreCase(digitado)){
            throw new SairException();
        }
        return digitado;
    }
}
