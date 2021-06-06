package model.strategy;

public class VerificaCodigoQuandl implements VerificaRetorno{
    @Override
    public String verificaCodigo(String codigoEmpresa) {
        char ultimoCaracter = (codigoEmpresa.length()>4) ? codigoEmpresa.charAt(4) : 0 ;
        String codigoEmpresaAdaptado = "WIKI/" + codigoEmpresa ;
        if(ultimoCaracter == '4' || ultimoCaracter == '3') {
            return "404";
        }
        return codigoEmpresaAdaptado;
    }
}
