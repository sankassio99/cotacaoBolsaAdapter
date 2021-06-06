package model.strategy;

public class VerificaCodigoYahooFinance implements VerificaRetorno{
    @Override
    public String verificaCodigo(String codigoEmpresa) {
        char ultimoCaracter = (codigoEmpresa.length()>4) ? codigoEmpresa.charAt(4) : 0 ;
        String codigoEmpresaAdaptado = codigoEmpresa ;
        if(ultimoCaracter == '4' || ultimoCaracter == '3') {
            codigoEmpresaAdaptado = codigoEmpresa + ".SA";
        }
        return codigoEmpresaAdaptado ;
    }
}
