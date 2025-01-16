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
//Função que executa o loop do jogo, caso perca, caso ganhe, esta função será executada
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
/*
Esta função realiza o Loop do jogo em si. Pergunta a linha e coluna que o usuário quer inserir e realiza as opeações
relacionadas.
 */
    private void loopJogo() {
        try{
            while (!tabuleiro.objetivoAlcancado()){
                System.out.println(tabuleiro);
//A função está correta, realizar o teste unitário pra validar depois.
                int linha  = Integer.parseInt(valorDigitado("Digite a linha:"));
                int coluna = Integer.parseInt(valorDigitado("Digite a coluna:"));

                tabuleiro.abrir(linha-1,coluna-1);

            }

            System.out.println("Você ganhou!");
        }catch (ExcessaoExplosao e){
            System.out.println("Você perdeu!");
        }
    }
//Verifica a informação inserida, função criada para permitir o usuário sair do jogo durante sua execução
//Essa função futuramente deverá ser trocada por uma interface.
    private String valorDigitado(String texto){
        System.out.print(texto);
        String digitado = entrada.nextLine();

        if ("sair".equalsIgnoreCase(digitado)){
            throw new SairException();
        }
        return digitado;
    }
}
