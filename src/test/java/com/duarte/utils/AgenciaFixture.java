package com.duarte.utils;

import com.duarte.domain.Agencia;
import com.duarte.domain.Endereco;
import com.duarte.service.http.AgenciaHttp;

import static com.duarte.service.http.SituacaoCadastral.ATIVO;
import static com.duarte.service.http.SituacaoCadastral.INATIVO;

public class AgenciaFixture {

    public static Agencia criarAgencia() {
        Endereco endereco = new Endereco(1L, "Teste", "Teste", "Teste", 1);
        return new Agencia(1L, "Teste", "Teste", "123", endereco);
    }

    public static AgenciaHttp criarAgenciaHttp() {
        return new AgenciaHttp("Agencia Teste", "Razao Agencia Teste", "123", ATIVO);
    }

    public static AgenciaHttp criarAgenciaHttpInativa() {
        return new AgenciaHttp("Agencia Teste", "Razao social da Agencia Teste", "123", INATIVO);
    }
}
