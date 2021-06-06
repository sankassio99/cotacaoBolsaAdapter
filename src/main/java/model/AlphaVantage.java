package model;

import org.patriques.*;
import org.patriques.output.AlphaVantageException;
import org.patriques.output.quote.StockQuotesResponse;
import org.patriques.output.quote.data.StockQuote;

import java.time.format.DateTimeFormatter;

public class AlphaVantage {

    private String codigoEmpresa ;
    private StringBuilder cotacao = new StringBuilder();

    public AlphaVantage(String codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }

    public String cotacaoAlphaVantage(){
        cotacao.append("Cotação da Empresa "+this.codigoEmpresa+" obtida pelo serviço Alpha Vantage: http://www.alphavantage.co%n\n");

        /*
        Verifica se existe uma variável de ambiente para a chave da API do serviço Alpha Vantage.
        Você pode se cadastrar e obter uma chave em http://www.alphavantage.co
        */
        final String s = System.getenv("ALPHAVANTAGE_APIKEY");
        final String apiKey = s == null ? "50M3AP1K3Y" : s;
        final int timeout = 3000;
        AlphaVantageConnector apiConnector = new AlphaVantageConnector(apiKey, timeout);

        //Permite obter a cotação (quotes) de ações (stocks).
        StockQuotes stockQuotes = new StockQuotes(apiConnector);

        try {
            StockQuotesResponse response = stockQuotes.quote(this.codigoEmpresa);
            StockQuote stock = response.getStockQuote();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            cotacao.append("Data:"+ formatter.format(stock.getLatestTradingDay()) +" Preço: "+ stock.getPrice());
        } catch (AlphaVantageException e) {
            cotacao.append("Erro ao solicitar cotação da empresa " + this.codigoEmpresa + ": " + e.getMessage());
        }

        cotacao.append("\n---------------------------------------------------------------------");

        return cotacao.toString();
    }



}
