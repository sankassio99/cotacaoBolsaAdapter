package model;

public class AlphaVantageAdapter implements ServicoCotacaoAlvo{

    private AlphaVantage alphaVantage = null;

    @Override
    public String pegarCotacao(String codigoEmpresa) {
        char ultimoCaracter = (codigoEmpresa.length()>4) ? codigoEmpresa.charAt(4) : 0 ;
        String codigoEmpresaAdaptado = codigoEmpresa ;
        if(ultimoCaracter == '4' || ultimoCaracter == '3') {
            codigoEmpresaAdaptado = codigoEmpresa + ".SA";
        }
        this.alphaVantage = new AlphaVantage(codigoEmpresaAdaptado);
        String cotacao = alphaVantage.cotacaoAlphaVantage();
        return cotacao ;
    }
}
