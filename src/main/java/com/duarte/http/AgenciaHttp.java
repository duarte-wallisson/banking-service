package com.duarte.http;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgenciaHttp {
    private String nome;
    private String razaoSocial;
    private String cnpj;
    private SituacaoCadastral situacaoCadastral;


}
