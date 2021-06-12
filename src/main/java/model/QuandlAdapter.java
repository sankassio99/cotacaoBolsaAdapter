package model;

import com.jimmoores.quandl.DataSetRequest;
import com.jimmoores.quandl.Row;
import com.jimmoores.quandl.TabularResult;
import com.jimmoores.quandl.classic.ClassicQuandlSession;
import model.strategy.VerificaCodigoQuandl;
import model.strategy.VerificaCodigoYahooFinance;
import model.strategy.VerificaOrigem;

import java.time.format.DateTimeFormatter;

public class QuandlAdapter implements ServicoCotacaoAlvo {

//    private Quandl quardl = null;
    private StringBuilder cotacao = new StringBuilder();

    @Override
    public String pegarCotacao(String codigoEmpresa) {
        char ultimoCaracter = (codigoEmpresa.length()>4) ? codigoEmpresa.charAt(4) : 0 ;
        String codigoEmpresaAdaptado = "WIKI/" + codigoEmpresa ;
        if(ultimoCaracter == '4' || ultimoCaracter == '3') {
            return "Não encontrado!";
        }

        cotacao.append("Cotação da Empresa "+codigoEmpresaAdaptado+" obtida pelo serviço Quandl: http://quandl.com\n");
        ClassicQuandlSession session = ClassicQuandlSession.create();
        DataSetRequest request = DataSetRequest.Builder
                .of(codigoEmpresaAdaptado)
                .withMaxRows(1)
                .build();
        TabularResult result = session.getDataSet(request);
        if(result.size() > 0) {
            Row row = result.get(0);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String date = formatter.format(row.getLocalDate("Date"));
            cotacao.append("Data: "+ date +" Preço: "+ row.getDouble("Close")+"\n");
        }
        cotacao.append("---------------------------------------------------------------------");

        return cotacao.toString();
    }

}
