package model;

import model.strategy.VerificaCodigoAlphaVantage;
import model.strategy.VerificaCodigoYahooFinance;
import model.strategy.VerificaOrigem;
import org.patriques.AlphaVantageConnector;
import org.patriques.StockQuotes;
import org.patriques.output.AlphaVantageException;
import org.patriques.output.quote.StockQuotesResponse;
import org.patriques.output.quote.data.StockQuote;

import java.time.format.DateTimeFormatter;

public class AlphaVantageAdapter implements ServicoCotacaoAlvo{

//    private AlphaVantage alphaVantage = null;
    private StringBuilder cotacao = new StringBuilder();

    @Override
    public String pegarCotacao(String codigoEmpresa) {
        char ultimoCaracter = (codigoEmpresa.length()>4) ? codigoEmpresa.charAt(4) : 0 ;
        String codigoEmpresaAdaptado = codigoEmpresa ;
        if(ultimoCaracter == '4' || ultimoCaracter == '3') {
            codigoEmpresaAdaptado = codigoEmpresa + ".SA";
        }
        cotacao.append("Cotação da Empresa "+codigoEmpresaAdaptado+" obtida pelo serviço Alpha Vantage: http://www.alphavantage.co%n\n");

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
            StockQuotesResponse response = stockQuotes.quote(codigoEmpresaAdaptado);
            StockQuote stock = response.getStockQuote();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            cotacao.append("Data:"+ formatter.format(stock.getLatestTradingDay()) +" Preço: "+ stock.getPrice());
        } catch (AlphaVantageException e) {
            cotacao.append("Erro ao solicitar cotação da empresa " + codigoEmpresaAdaptado + ": " + e.getMessage());
        }

        cotacao.append("\n---------------------------------------------------------------------");

        return cotacao.toString();
    }
}
