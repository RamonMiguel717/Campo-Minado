package program.visao;
import program.excecao.ExcessaoExplosao;
import program.excecao.SairException;
import program.modelo.Tabuleiro;

import java.util.Scanner;

public class TabuleiroConsole {

    private Tabuleiro tabuleiro;
    private Scanner entrada = new Scanner(System.in);

    public TabuleiroConsole(Tabuleiro tabuleiro){
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
            do {
                System.out.println(tabuleiro);
                int linha  = Integer.parseInt(valorDigitado("Digite a linha:"));
                int coluna = Integer.parseInt(valorDigitado("Digite a coluna:"));

                String alternativa = valorDigitado("1- Abrir\n 2- Marcar/Desmarcar");

                if ("1".equals(alternativa)){
                    tabuleiro.abrir(linha,coluna);

                } else if ("2".equals(alternativa)) {
                    tabuleiro.alterarMarcacao(linha,coluna);
                }


            }while (!tabuleiro.objetivoAlcancado());

            System.out.println("Você ganhou!");
        }catch (ExcessaoExplosao e){
            System.out.println(tabuleiro);
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
