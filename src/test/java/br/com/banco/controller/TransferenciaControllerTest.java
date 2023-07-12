package br.com.banco.controller;

import br.com.banco.dto.TransferenciaDTO;
import br.com.banco.service.TransferenciaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class TransferenciaControllerTest {

    @Mock
    private TransferenciaService transferenciaService;

    @InjectMocks
    private TransferenciaController transferenciaController;


    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private String nomeOperadorTransacao;
    private Long numeroConta;

    @BeforeEach
    public void setUp(){
        dataInicio = LocalDateTime.now();
        dataFim = LocalDateTime.now();
        nomeOperadorTransacao = "Beltrano";
        numeroConta = 1L;

    }

    @Test
    public void buscarTransferenciasDeveRetornarTransferenciaComTodosFiltros(){
        List<TransferenciaDTO> expected = new ArrayList<>();

        when(transferenciaService.buscarTransferenciasTodosFiltros(dataInicio, dataFim, nomeOperadorTransacao, numeroConta))
                .thenReturn(expected);

        // Act
        List<TransferenciaDTO> result = transferenciaController.buscarTransferencias(dataInicio, dataFim, nomeOperadorTransacao, numeroConta);

        // Assert
        assertEquals(expected, result);
        verify(transferenciaService).buscarTransferenciasTodosFiltros(dataInicio, dataFim, nomeOperadorTransacao, numeroConta);
        verifyNoMoreInteractions(transferenciaService);
    }

    @Test
    public void buscarTransferenciaDeveRetornarTodasTransferenciasSemFiltros(){
        List<TransferenciaDTO> expected = new ArrayList<>();

        when(transferenciaService.buscarTodasTransferencias())
                .thenReturn(expected);

        List<TransferenciaDTO> result = transferenciaController.buscarTransferencias(null, null, null, null);

        assertEquals(expected, result);
        verify(transferenciaService).buscarTodasTransferencias();
        verifyNoMoreInteractions(transferenciaService);
    }

    @Test
    public void buscarTransferenciaDeveRetornarTransferenciasPorNumeroContaENomeOperador(){
        List<TransferenciaDTO> expected = new ArrayList<>();

        when(transferenciaService.buscarTransferenciasPorNumeroContaENomeOperador(numeroConta, nomeOperadorTransacao))
                .thenReturn(expected);

        List<TransferenciaDTO> result = transferenciaController.buscarTransferencias(null, null, nomeOperadorTransacao, numeroConta);

        assertEquals(expected, result);
        verify(transferenciaService).buscarTransferenciasPorNumeroContaENomeOperador(numeroConta, nomeOperadorTransacao);
        verifyNoMoreInteractions(transferenciaService);
    }


    @Test
    public void buscarTransferenciaDeveRetornarTransferenciaPorPeriodo(){
        List<TransferenciaDTO> expected = new ArrayList<>();

        when(transferenciaService.buscarTransferenciasPorPeriodo(dataInicio, dataFim))
                .thenReturn(expected);

        List<TransferenciaDTO> result = transferenciaController.buscarTransferencias(dataInicio, dataFim, null, null);

        assertEquals(expected, result);
        verify(transferenciaService).buscarTransferenciasPorPeriodo(dataInicio, dataFim);
        verifyNoMoreInteractions(transferenciaService);
    }


    @Test
    public void buscarTransferenciaDeveRetornarTransferenciaPorNumeroConta(){
        List<TransferenciaDTO> expected = new ArrayList<>();

        when(transferenciaService.buscarTransferenciaPorNumeroConta(numeroConta))
                .thenReturn(expected);

        List<TransferenciaDTO> result = transferenciaController.buscarTransferencias(null, null,null, numeroConta);

        assertEquals(expected, result);
        verify(transferenciaService).buscarTransferenciaPorNumeroConta(numeroConta);
        verifyNoMoreInteractions(transferenciaService);
    }

    @Test
    public void buscarTransferenciaDeveRetornarTransferenciaPorNomeOperadorTransacao(){
        List<TransferenciaDTO> expected = new ArrayList<>();

        when(transferenciaService.buscarPorNomeOperadorTransacao(nomeOperadorTransacao))
                .thenReturn(expected);

        List<TransferenciaDTO> result = transferenciaController.buscarTransferencias(null, null, nomeOperadorTransacao, null);

        assertEquals(expected, result);
        verify(transferenciaService).buscarPorNomeOperadorTransacao(nomeOperadorTransacao);
        verifyNoMoreInteractions(transferenciaService);
    }


}
