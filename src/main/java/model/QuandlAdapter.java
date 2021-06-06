package model;

import model.strategy.VerificaCodigoQuandl;
import model.strategy.VerificaCodigoYahooFinance;
import model.strategy.VerificaOrigem;

public class QuandlAdapter implements ServicoCotacaoAlvo {

    private Quandl quardl = null;

    @Override
    public String pegarCotacao(String codigoEmpresa) {
        VerificaOrigem verificaOrigem = new VerificaOrigem(new VerificaCodigoQuandl());
        String codigoStrategy = verificaOrigem.verificar(codigoEmpresa);

        this.quardl = new Quandl(codigoStrategy);
        String cotacao = this.quardl.cotacaoAlphaVantage();
        return cotacao ;
    }
}
