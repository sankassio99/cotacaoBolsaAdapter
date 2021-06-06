package model;

import model.strategy.VerificaCodigoAlphaVantage;
import model.strategy.VerificaCodigoYahooFinance;
import model.strategy.VerificaOrigem;

public class AlphaVantageAdapter implements ServicoCotacaoAlvo{

    private AlphaVantage alphaVantage = null;

    @Override
    public String pegarCotacao(String codigoEmpresa) {
        VerificaOrigem verificaOrigem = new VerificaOrigem(new VerificaCodigoAlphaVantage());
        String codigoStrategy = verificaOrigem.verificar(codigoEmpresa);

        this.alphaVantage = new AlphaVantage(codigoStrategy);
        String cotacao = alphaVantage.cotacaoAlphaVantage();
        return cotacao ;
    }
}
