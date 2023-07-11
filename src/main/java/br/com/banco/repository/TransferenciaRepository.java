package br.com.banco.repository;

import br.com.banco.entity.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {


    @Query("SELECT t FROM Transferencia t JOIN t.conta c WHERE c.id = :numeroConta")
    List<Transferencia> findAllByNumeroConta(@Param("numeroConta") Long numeroConta);

    @Query("SELECT t FROM Transferencia t WHERE t.nomeOperadorTransacao = :nomeOperadorTransacao")
    List<Transferencia> findAllByNomeOperadorTransacao(@Param("nomeOperadorTransacao") String nomeOperadorTransacao);

    @Query("SELECT t FROM Transferencia t JOIN t.conta c WHERE c.id = :numeroConta " +
            "AND (:nomeOperadorTransacao IS NULL OR t.nomeOperadorTransacao = :nomeOperadorTransacao)")
    List<Transferencia> findAllByNumeroContaAndNomeOperadorTransacao(
            @Param("numeroConta") Long numeroConta,
            @Param("nomeOperadorTransacao") String nomeOperadorTransacao
    );

    List<Transferencia> findByDataTransferenciaBetween(LocalDateTime dataInicio, LocalDateTime dataFim);

    @Query("SELECT t FROM Transferencia t WHERE (:numeroConta IS NULL OR t.conta.id = :numeroConta) "
            + "AND (:dataInicio IS NULL OR :dataFim IS NULL OR t.dataTransferencia BETWEEN :dataInicio AND :dataFim) "
            + "AND (:nomeOperadorTransacao IS NULL OR t.nomeOperadorTransacao = :nomeOperadorTransacao)")
    List<Transferencia> findAllPorDataTransferenciaENomeOperadorTransacaoENumeroConta(
            @Param("dataInicio") LocalDateTime dataInicio,
            @Param("dataFim") LocalDateTime dataFim,
            @Param("nomeOperadorTransacao") String nomeOperadorTransacao,
            @Param("numeroConta") Long numeroConta);

}
