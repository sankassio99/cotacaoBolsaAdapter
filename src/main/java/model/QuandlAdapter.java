package model;

public class QuandlAdapter implements ServicoCotacaoAlvo {

    private Quandl quardl = null;

    @Override
    public String pegarCotacao(String codigoEmpresa) {
        char ultimoCaracter = (codigoEmpresa.length()>4) ? codigoEmpresa.charAt(4) : 0 ;
        String codigoEmpresaAdaptado = "WIKI/" + codigoEmpresa ;
        if(ultimoCaracter == '4' || ultimoCaracter == '3') {
            codigoEmpresaAdaptado = codigoEmpresa + ".SA";
        }

        this.quardl = new Quandl(codigoEmpresaAdaptado);
        String cotacao = this.quardl.cotacaoAlphaVantage();
        return cotacao ;
    }
}
