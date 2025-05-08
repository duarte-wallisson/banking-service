package com.duarte.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Agencia {
    private Integer id;
    private String nome;
    private String razaoSocial;
    private String cnpj;
    private Endereco endereco;
}
