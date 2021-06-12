package model;

import io.github.mainstringargs.yahooFinance.YahooFinanceData;
import io.github.mainstringargs.yahooFinance.YahooFinanceModules;
import io.github.mainstringargs.yahooFinance.YahooFinanceRequest;
import io.github.mainstringargs.yahooFinance.YahooFinanceUrlBuilder;
import io.github.mainstringargs.yahooFinance.domain.FinancialData;
import model.strategy.VerificaCodigoYahooFinance;
import model.strategy.VerificaOrigem;

import java.util.Date;

public class YahooFinanceAdapter implements ServicoCotacaoAlvo {

    private StringBuilder cotacao = new StringBuilder();
    Date data = new Date(System.currentTimeMillis());

    @Override
    public String pegarCotacao(String codigoEmpresa) {

        char ultimoCaracter = (codigoEmpresa.length()>4) ? codigoEmpresa.charAt(4) : 0 ;
        String codigoEmpresaAdaptado = codigoEmpresa ;
        if(ultimoCaracter == '4' || ultimoCaracter == '3') {
            codigoEmpresaAdaptado = codigoEmpresa + ".SA";
        }

        this.cotacao.append("Cotação da Empresa "+ codigoEmpresaAdaptado
                +" obtida pelo serviço Yahoo Finance: https://finance.yahoo.com\n");
        YahooFinanceUrlBuilder builder =
                new YahooFinanceUrlBuilder().modules(YahooFinanceModules.values()).symbol(codigoEmpresaAdaptado);

        YahooFinanceRequest request = new YahooFinanceRequest();
        YahooFinanceData financeData = request.getFinanceData(request.invoke(builder));

        FinancialData financials = financeData.getFinancialData();
        if (financials != null) {
            this.cotacao.append("Data:"+ data  +" Preço: "+ financials.getCurrentPrice().getRaw() +" "+ financials.getFinancialCurrency() + "\n");
        }

        this.cotacao.append(builder.getURL());
        this.cotacao.append("https://query1.finance.yahoo.com/v8/finance/chart/"
                +codigoEmpresa+"?period1=1546311600&period2=1556593200&interval=1d&includePrePost=False");
        this.cotacao.append("\n---------------------------------------------------------------------");

        return this.cotacao.toString() ;
    }

}
