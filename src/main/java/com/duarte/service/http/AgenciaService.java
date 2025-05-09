package com.duarte.service.http;

import com.duarte.domain.Agencia;
import com.duarte.exceptions.AgenciaNaoAtivaOuNaoEncontradaException;
import com.duarte.http.SituacaoCadastral;
import com.duarte.repository.AgenciaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;


@ApplicationScoped
public class AgenciaService {

    @RestClient
    private SituacaoCadastralHttpService situacaoCadastralHttpService;

    @Inject
    protected AgenciaRepository agenciaRepository;

    @Transactional
    public void cadastrar(Agencia agencia) {
        var agenciaHttp = situacaoCadastralHttpService.buscarPorCnpj(agencia.getCnpj());

        if (agenciaHttp != null && agenciaHttp.getSituacaoCadastral().equals(SituacaoCadastral.ATIVO)) {
            agenciaRepository.persist(agencia);
        } else {
            throw new AgenciaNaoAtivaOuNaoEncontradaException();
        }
    }

    public Agencia buscarPorId(Long id) {
        return agenciaRepository.findById(id);
    }

    @Transactional
    public void deletar(Long id) {
        agenciaRepository.deleteById(id);
    }

    @Transactional
    public void alterar(Agencia agencia) {
        Agencia entidadeExistente = agenciaRepository.findById(agencia.getId());

        if (entidadeExistente != null) {
            entidadeExistente.setNome(agencia.getNome());
            entidadeExistente.setRazaoSocial(agencia.getRazaoSocial());
            entidadeExistente.setCnpj(agencia.getCnpj());
        } else {
            throw new IllegalStateException("Agência com ID " + agencia.getId() + " não encontrada");
        }
    }
}
