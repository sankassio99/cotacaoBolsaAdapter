package model.strategy;

public class VerificaOrigem {

    VerificaRetorno verificaRetorno ;

    public String verificar(String codigoEmpresa){

        String codigoStrategy = verificaRetorno.verificaCodigo(codigoEmpresa);
        return codigoStrategy ;

    }

    public VerificaOrigem(VerificaRetorno verificaRetorno) {
        this.verificaRetorno = verificaRetorno;
    }
}
