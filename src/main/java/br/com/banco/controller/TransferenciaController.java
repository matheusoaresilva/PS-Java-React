package br.com.banco.controller;

import br.com.banco.dto.TransferenciaDTO;
import br.com.banco.service.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;


    @GetMapping()
    public List<TransferenciaDTO> buscarTransferencias(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim,
            @RequestParam(required = false) String nomeOperadorTransacao,
            @RequestParam(required = false) Long numeroConta) {

        if (numeroConta != null) {
            if (dataInicio != null && dataFim != null && nomeOperadorTransacao != null) {
                return transferenciaService.buscarTransferenciasTodosFiltros(dataInicio, dataFim, nomeOperadorTransacao, numeroConta);
            } else if (nomeOperadorTransacao != null) {
                return transferenciaService.buscarTransferenciasPorNumeroContaENomeOperador(numeroConta, nomeOperadorTransacao);
            } else {
                return transferenciaService.buscarTransferenciaPorNumeroConta(numeroConta);
            }
        }else if (dataInicio != null && dataFim != null){
            return transferenciaService.buscarTransferenciasPorPeriodo(dataInicio, dataFim);
        } else if (nomeOperadorTransacao != null) {
            return transferenciaService.buscarPorNomeOperadorTransacao(nomeOperadorTransacao);
        } else {
            return transferenciaService.buscarTodasTransferencias();
        }
    }

}
