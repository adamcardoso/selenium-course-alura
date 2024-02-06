package leiloes;

import login.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class LeiloesTest {

    private LeiloesPage paginaDeLeiloes;
    private CadastroLeilaoPage paginaDeCadastroDeLeiloes;

    @BeforeEach
    public void beforeEach(){
        LoginPage paginaDeLogin = new LoginPage();
        paginaDeLogin.preencheFormularioDeLogin("fulano", "pass");

        this.paginaDeLeiloes = paginaDeLogin.efetuarLogin();
        this.paginaDeCadastroDeLeiloes = paginaDeLeiloes.carregarFormulario();
    }

    @AfterEach
    public void afterEach(){
        this.paginaDeLeiloes.fechar();
        this.paginaDeCadastroDeLeiloes.fechar();
    }

    @Test
    void deveriaCadastrarLeilao(){
        String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String nome = "Leilao do dia "+ hoje;
        String valor = "500.00";

        this.paginaDeLeiloes = paginaDeCadastroDeLeiloes.cadastrarLeilao(nome, valor, hoje);

        Assertions.assertTrue(paginaDeLeiloes.isLeilaoCadastrado(nome, valor, hoje));
    }

    @Test
    void deveriaValidarCadastroDeLeilao() {
        this.paginaDeLeiloes = paginaDeCadastroDeLeiloes.cadastrarLeilao("", "", "");

        Assertions.assertFalse(this.paginaDeCadastroDeLeiloes.isPaginaAtual());
        Assertions.assertTrue(this.paginaDeLeiloes.isPaginaAtual());
        Assertions.assertTrue(this.paginaDeCadastroDeLeiloes.isPaginaDeValidacaoVisiveis());
    }
}
