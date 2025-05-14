package com.duarte.service;

import com.duarte.domain.Agencia;
import com.duarte.exceptions.AgenciaNaoAtivaOuNaoEncontradaException;
import com.duarte.service.http.SituacaoCadastral;
import com.duarte.service.http.SituacaoCadastralHttpService;
import com.duarte.repository.AgenciaRepository;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;


@ApplicationScoped
public class AgenciaService {

    private final SituacaoCadastralHttpService situacaoCadastralHttpService;
    private final AgenciaRepository agenciaRepository;

    @Inject
    public AgenciaService(@RestClient SituacaoCadastralHttpService situacaoCadastralHttpService,
                         AgenciaRepository agenciaRepository) {
        this.situacaoCadastralHttpService = situacaoCadastralHttpService;
        this.agenciaRepository = agenciaRepository;
    }

    @Transactional
    public void cadastrar(Agencia agencia) {
        var agenciaHttp = situacaoCadastralHttpService.buscarPorCnpj(agencia.getCnpj());

        if (agenciaHttp != null && agenciaHttp.getSituacaoCadastral().equals(SituacaoCadastral.ATIVO)) {
            agenciaRepository.persist(agencia);
            Log.info("Agência com o CNPJ " + agencia.getCnpj() + " cadastrada com sucesso!");
        } else {
            Log.info("Agência com o CNPJ " + agencia.getCnpj() + " não cadastrada!");
            throw new AgenciaNaoAtivaOuNaoEncontradaException();
        }
    }

    public Agencia buscarPorId(Long id) {
        return agenciaRepository.findById(id);
    }

    @Transactional
    public void deletar(Long id) {
        Log.info("Agência com o ID " + id + " deletada com sucesso!");
        agenciaRepository.deleteById(id);
    }

    @Transactional
    public void alterar(Agencia agencia) {
        Agencia entidadeExistente = agenciaRepository.findById(agencia.getId());

        if (entidadeExistente != null) {
            entidadeExistente.setNome(agencia.getNome());
            entidadeExistente.setRazaoSocial(agencia.getRazaoSocial());
            entidadeExistente.setCnpj(agencia.getCnpj());
            Log.info("Agência com o ID " + agencia.getId() + " alterada com sucesso!");
        } else {
            throw new IllegalStateException("Agência com ID " + agencia.getId() + " não encontrada");
        }
    }
}
