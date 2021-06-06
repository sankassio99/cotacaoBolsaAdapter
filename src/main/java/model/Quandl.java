
package model;

//Quandl
import com.jimmoores.quandl.*;
import com.jimmoores.quandl.classic.ClassicQuandlSession;

import java.time.format.DateTimeFormatter;

public class Quandl {

    private String codigoEmpresa ;
    private StringBuilder cotacao = new StringBuilder();

    public Quandl(String codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }

    public String cotacaoAlphaVantage() {
        cotacao.append("Cotação da Empresa "+codigoEmpresa+" obtida pelo serviço Quandl: http://quandl.com\n");
        ClassicQuandlSession session = ClassicQuandlSession.create();
        DataSetRequest request = DataSetRequest.Builder
                .of(codigoEmpresa)
                .withMaxRows(1)
                .build();
        TabularResult result = session.getDataSet(request);
        if(result.size() > 0) {
            Row row = result.get(0);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String date = formatter.format(row.getLocalDate("Date"));
            cotacao.append("Data: "+ date +" Preço: "+ row.getDouble("Close")+"\n");
            //System.out.println(result.toPrettyPrintedString());
        }
        cotacao.append("---------------------------------------------------------------------");

        return cotacao.toString();
    }

}
