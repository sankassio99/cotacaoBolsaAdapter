package model;

import model.strategy.VerificaCodigoYahooFinance;
import model.strategy.VerificaOrigem;

public class YahooFinanceAdapter implements ServicoCotacaoAlvo {

    private YahooFinance yahooFinance = null;

    @Override
    public String pegarCotacao(String codigoEmpresa) {

        VerificaOrigem verificaOrigem = new VerificaOrigem(new VerificaCodigoYahooFinance());
        String codigoStrategy = verificaOrigem.verificar(codigoEmpresa);

        this.yahooFinance = new YahooFinance(codigoStrategy);
        String cotacao = yahooFinance.cotacaoYahooFinance();
        return cotacao ;
    }

}
