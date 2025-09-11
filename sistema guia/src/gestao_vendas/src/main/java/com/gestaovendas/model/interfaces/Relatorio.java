package com.gestaovendas.model.interfaces;

import java.time.LocalDate;
import java.util.List;

/**
 * Interface para geração de relatórios
 */
public interface Relatorio<T> {
    List<T> gerarRelatorioDiario(LocalDate data);
    List<T> gerarRelatorioSemanal(LocalDate dataInicio, LocalDate dataFim);
    List<T> gerarRelatorioMensal(int mes, int ano);
    boolean exportarParaPDF(List<T> dados, String nomeArquivo);
    void exibirGrafico(List<T> dados);
}

