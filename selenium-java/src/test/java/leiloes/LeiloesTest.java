package leiloes;

import login.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class LeiloesTest {

    private LeiloesPage paginaDeLeiloes;

    @AfterEach
    public void afterEach(){
        this.paginaDeLeiloes.fechar();
    }

    @Test
    void deveriaCadastrarLeilao(){
        LoginPage paginaDeLogin = new LoginPage();
        paginaDeLogin.preencheFormularioDeLogin("fulano", "pass");
        this.paginaDeLeiloes = paginaDeLogin.efetuarLogin();

        CadastroLeilaoPage paginaDeCadastroDeLeiloes = paginaDeLeiloes.carregarFormulario();

        String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String nome = "Leilao do dia "+ hoje;
        String valor = "500.00";

        this.paginaDeLeiloes = paginaDeCadastroDeLeiloes.cadastrarLeilao(nome, valor, hoje);

        Assertions.assertTrue(paginaDeLeiloes.isLeilaoCadastrado(nome, valor, hoje));
    }
}
