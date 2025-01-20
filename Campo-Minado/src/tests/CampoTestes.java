package tests;

import org.junit.jupiter.api.Test;
import program.modelo.Campo;
import program.excecao.ExcessaoExplosao;

import java.lang.reflect.GenericArrayType;

import static org.junit.jupiter.api.Assertions.*;

public class CampoTestes {

    @Test
    void testeVizinhoValido() {
        Campo campo = new Campo(3, 2);
        Campo vizinho = new Campo(3, 3);
        boolean resultado = campo.adicionarVizinho(vizinho);

        assertTrue(resultado, "O vizinho deveria ser válido.");
    }

    @Test
    void testeVizinhoInvalido() {
        Campo campo = new Campo(3, 2);
        Campo vizinho = new Campo(5, 5); // Não é vizinho válido
        boolean resultado = campo.adicionarVizinho(vizinho);

        assertFalse(resultado, "O vizinho não deveria ser válido.");
    }

    @Test
    void testeValorPadraoMarcacao() {
        Campo campo = new Campo(3, 2);
        assertFalse(campo.isMarcado(), "Por padrão, o campo não deveria estar marcado.");
    }

    @Test
    void testeAlternarMarcacaoParaMarcado() {
        Campo campo = new Campo(3, 2);
        campo.alternarMarcacao();
        assertTrue(campo.isMarcado(), "O campo deveria estar marcado após alternar.");
    }

    @Test
    void testeAlternarMarcacaoParaNaoMarcado() {
        Campo campo = new Campo(3, 2);
        campo.alternarMarcacao(); // Marca
        campo.alternarMarcacao(); // Desmarca
        assertFalse(campo.isMarcado(), "O campo deveria estar desmarcado após alternar novamente.");
    }

    @Test
    void testeAbrirCampoMarcado() {
        Campo campo = new Campo(3, 2);
        campo.alternarMarcacao(); // Marca o campo
        assertFalse(campo.abrirCampo(), "Não deveria ser possível abrir um campo marcado.");
    }

    @Test
    void testeAbrirCampoNaoMinado() {
        Campo campo = new Campo(3, 2);
        boolean resultado = campo.abrirCampo();
        assertTrue(resultado, "Deveria ser possível abrir um campo não minado.");
    }

    @Test
    void testeAbrirMinadoNaoMarcado() {
        Campo campo = new Campo(3, 2);
        campo.minar();
        assertThrows(ExcessaoExplosao.class, campo::abrirCampo);
    }

    @Test
    void testeVizinhancaSegura() {
        Campo campo = new Campo(3, 2);
        Campo vizinho = new Campo(3, 3);
        campo.adicionarVizinho(vizinho);

        assertTrue(campo.vizinhancaSegura(), "A vizinhança deveria ser segura quando nenhum campo é minado.");
    }

    @Test
    void testarAlternarMarcacao() {

        Campo campo = new Campo(3, 2);
        boolean resultado = campo.alternarMarcacao();
        assertTrue(resultado, "O campo deveria estar marcado");

    }

    @Test
    void testarVizinhancaSegura() {

        Campo campo = new Campo(3, 2);
        boolean resultado = campo.vizinhancaSegura();
        assertTrue(resultado, "O resultado deveria ser 'True'");
    }

    @Test
    void testarIsMarcado() {
        Campo campo = new Campo(3, 2);
        boolean resultado = campo.isMarcado();
        assertFalse(resultado, "O campo deveria estar desmarcado");
    }

    @Test
    void testarIsAberto() {
        Campo campo = new Campo(3, 2);
        boolean resultado = campo.isAberto();

        assertFalse(resultado, "O campo deveria estar fechado.");
    }

    @Test
    void testarObjetivoAlcancado() {
        Campo campo = new Campo(3, 2);
        boolean resultado = campo.objetivoAlcancado();

        assertTrue(resultado, "O objetivo deveria ter sido alcançado");
    }

    @Test
    void testReiniciar() {
        Campo campo = new Campo(3, 2);

        campo.reiniciar();
        assertFalse(campo.isAberto(),"O campo deveria estar fechado.");
        assertFalse(campo.isMinado(),"O campo não deveria estar minado.");
        assertFalse(campo.isMarcado(),"O campo não deveria estar marcado");
    }
}
