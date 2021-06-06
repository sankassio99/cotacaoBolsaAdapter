package model;

public class YahooFinanceAdapter implements ServicoCotacaoAlvo {

    private YahooFinance yahooFinance = null;

    @Override
    public String pegarCotacao(String codigoEmpresa) {
        char ultimoCaracter = (codigoEmpresa.length()>4) ? codigoEmpresa.charAt(4) : 0 ;
        String codigoEmpresaAdaptado = codigoEmpresa ;
        if(ultimoCaracter == '4' || ultimoCaracter == '3') {
            codigoEmpresaAdaptado = codigoEmpresa + ".SA";
        }

        this.yahooFinance = new YahooFinance(codigoEmpresaAdaptado);
        String cotacao = yahooFinance.cotacaoYahooFinance();
        return cotacao ;
    }

}
