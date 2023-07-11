package br.com.banco.service;

import br.com.banco.dto.TransferenciaDTO;
import br.com.banco.entity.Transferencia;
import br.com.banco.repository.TransferenciaRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransferenciaService {

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<TransferenciaDTO> buscarTodasTransferencias(){
        List<Transferencia> transferencias = transferenciaRepository.findAll();
        return transferencias.stream()
                .map(transferencia -> modelMapper.map(transferencia, TransferenciaDTO.class))
                .collect(Collectors.toList());
    }

    public List<TransferenciaDTO> buscarTransferenciasPorNumeroContaENomeOperador(
            Long numeroConta, String nomeOperadorTransacao) {
        List<Transferencia> transferencias = transferenciaRepository.findAllByNumeroContaAndNomeOperadorTransacao(
                numeroConta, nomeOperadorTransacao);

        return transferencias.stream()
                .map(transferencia -> modelMapper.map(transferencia, TransferenciaDTO.class))
                .collect(Collectors.toList());
    }

    public List<TransferenciaDTO> buscarTransferenciasPorPeriodo(LocalDateTime dataInicio, LocalDateTime dataFim) {
        List<Transferencia> transferencias = transferenciaRepository.findByDataTransferenciaBetween(dataInicio, dataFim);
        return transferencias.stream()
                .map(transferencia -> modelMapper.map(transferencia, TransferenciaDTO.class))
                .collect(Collectors.toList());
    }

    public List<TransferenciaDTO> buscarTransferenciaPorNumeroConta(Long id){
        List<Transferencia> listTransferencias = transferenciaRepository.findAllByNumeroConta(id);

        return listTransferencias.stream()
                .map(transferencia -> modelMapper.map(transferencia, TransferenciaDTO.class))
                .collect(Collectors.toList());
    }

    public List<TransferenciaDTO> buscarPorNomeOperadorTransacao(String nomeOperadorTransacao){
        List<Transferencia> listTransferencias = transferenciaRepository.findAllByNomeOperadorTransacao(nomeOperadorTransacao);

        return listTransferencias.stream()
                .map(transferencia -> modelMapper.map(transferencia, TransferenciaDTO.class))
                .collect(Collectors.toList());
    }

    public List<TransferenciaDTO> buscarTransferenciasTodosFiltros(
            LocalDateTime dataInicio, LocalDateTime dataFim, String nomeOperadorTransacao, Long numeroConta) {
        List<Transferencia> listTransferencias = transferenciaRepository.findAllPorDataTransferenciaENomeOperadorTransacaoENumeroConta(
                dataInicio, dataFim, nomeOperadorTransacao, numeroConta);

        return listTransferencias.stream()
                .map(transferencia -> modelMapper.map(transferencia, TransferenciaDTO.class))
                .collect(Collectors.toList());
    }

}
