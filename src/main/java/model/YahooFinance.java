package model;

//YahooFinance
import io.github.mainstringargs.yahooFinance.*;
import io.github.mainstringargs.yahooFinance.domain.FinancialData;

import java.time.format.DateTimeFormatter;
import java.util.Date;

public class YahooFinance {

    private String codigoEmpresa ;
    private StringBuilder cotacao = new StringBuilder();

    Date data = new Date(System.currentTimeMillis());

    public YahooFinance(String codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }

    public String cotacaoYahooFinance(){
        cotacao.append("Cotação da Empresa "+ codigoEmpresa
                +" obtida pelo serviço Yahoo Finance: https://finance.yahoo.com\n");
        YahooFinanceUrlBuilder builder =
                new YahooFinanceUrlBuilder().modules(YahooFinanceModules.values()).symbol(codigoEmpresa);

        YahooFinanceRequest request = new YahooFinanceRequest();
        YahooFinanceData financeData = request.getFinanceData(request.invoke(builder));

        FinancialData financials = financeData.getFinancialData();
        if (financials != null) {
            cotacao.append("Data:"+ data  +" Preço: "+ financials.getCurrentPrice().getRaw() +" "+ financials.getFinancialCurrency() + "\n");
        }

        cotacao.append(builder.getURL());
        cotacao.append("https://query1.finance.yahoo.com/v8/finance/chart/"+codigoEmpresa+"?period1=1546311600&period2=1556593200&interval=1d&includePrePost=False");
        cotacao.append("\n---------------------------------------------------------------------");

        return cotacao.toString() ;
    }



}
