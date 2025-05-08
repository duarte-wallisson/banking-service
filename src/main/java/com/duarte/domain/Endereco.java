package com.duarte.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Endereco {
    private Integer id;
    private String rua;
    private String logradouro;
    private String complemento;
    private Integer numero;
}
