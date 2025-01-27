package program.validacao;

import java.util.Scanner;

public class ValidarInteiro {
    public static int validar(String mensagem){
        Scanner teclado = new Scanner(System.in);
        int numero = 0;
        boolean valido = false;

        while (!valido){
            System.out.println(mensagem);
            try {
                numero = Integer.parseInt(teclado.nextLine());
                valido = true;
            }catch (NumberFormatException e){
                System.out.println("Entrada inválida, tente novamente.");
            }
        }
        return numero;
    }
}
