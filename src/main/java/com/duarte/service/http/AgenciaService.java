package com.duarte.service.http;

import com.duarte.domain.Agencia;
import com.duarte.exceptions.AgenciaNaoAtivaOuNaoEncontradaException;
import com.duarte.http.SituacaoCadastral;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.ArrayList;


@ApplicationScoped
public class AgenciaService {

    @RestClient
    private SituacaoCadastralHttpService situacaoCadastralHttpService;

    private List<Agencia> agencias = new ArrayList<>();

    public void cadastrar(Agencia agencia) {
        var agenciaHttp = situacaoCadastralHttpService.buscarPorCnpj(agencia.getCnpj());

        if (agenciaHttp.getSituacaoCadastral().equals(SituacaoCadastral.ATIVO)) {
            agencias.add(agencia);
        } else {
            throw new AgenciaNaoAtivaOuNaoEncontradaException();
        }
    }

}
