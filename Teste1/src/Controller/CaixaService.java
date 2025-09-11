/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Fatura;
import Model.Ordem;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 *
 * @author massas
 */
public class CaixaService {
    private Map<String, Fatura> faturas;
    private Vector<Fatura> faturasVector;
    private ArrayList<Fatura> faturasArrayList;
    private Map<Fatura.TipoPagamento, Double> lancamentosDia;
    private VendasService vendasService;
    
    public CaixaService(VendasService vendasService) {
        this.faturas = new HashMap<>();
        this.faturasVector = new Vector<>();
        this.faturasArrayList = new ArrayList<>();
        this.lancamentosDia = new HashMap<>();
        this.vendasService = vendasService;
        inicializarLancamentos();
    }
    
    private void inicializarLancamentos() {
        for (Fatura.TipoPagamento tipo : Fatura.TipoPagamento.values()) {
            lancamentosDia.put(tipo, 0.0);
        }
    }
    
    /**
     * Processa uma ordem de pagamento
     */
    public Fatura processarOrdemPagamento(String codigoOrdem, String caixaId, String caixaNome, 
                                         Fatura.TipoPagamento tipoPagamento) {
        Ordem ordem = vendasService.buscarOrdem(codigoOrdem);
        if (ordem == null || ordem.getStatus() != Ordem.StatusOrdem.PROCESSANDO) {
            return null;
        }
        
        // Criar fatura
        Fatura fatura = new Fatura(
            ordem.getId(),
            ordem.getVendedorId(),
            ordem.getVendedorNome(),
            caixaId,
            caixaNome,
            ordem.getClienteId(),
            ordem.getClienteNome(),
            ordem.getValorTotal(),
            tipoPagamento
        );
        
        // Salvar fatura
        faturas.put(fatura.getCodigo(), fatura);
        faturasVector.add(fatura);
        faturasArrayList.add(fatura);
        
        // Atualizar lançamentos do dia
        double valorAtual = lancamentosDia.get(tipoPagamento);
        lancamentosDia.put(tipoPagamento, valorAtual + fatura.getValorTotal());
        
        // Atualizar status da ordem
        ordem.setStatus(Ordem.StatusOrdem.PAGO);
        
        return fatura;
    }
    
    /**
     * Busca fatura por código
     */
    public Fatura buscarFatura(String codigo) {
        return faturas.get(codigo);
    }
    
    /**
     * Lista todas as faturas
     */
    public List<Fatura> listarFaturas() {
        return new ArrayList<>(faturasArrayList);
    }
    
    /**
     * Obtém lançamentos de pagamentos do dia
     */
    public Map<Fatura.TipoPagamento, Double> getLancamentosDia() {
        return new HashMap<>(lancamentosDia);
    }
    
    /**
     * Obtém lançamentos de pagamentos por data
     */
    public Map<Fatura.TipoPagamento, Double> getLancamentosPorData(LocalDate data) {
        Map<Fatura.TipoPagamento, Double> lancamentos = new HashMap<>();
        
        // Inicializar com zeros
        for (Fatura.TipoPagamento tipo : Fatura.TipoPagamento.values()) {
            lancamentos.put(tipo, 0.0);
        }
        
        // Somar valores das faturas da data
        for (Fatura fatura : faturasArrayList) {
            if (fatura.getDataProcessamento().toLocalDate().equals(data)) {
                Fatura.TipoPagamento tipo = fatura.getTipoPagamento();
                double valorAtual = lancamentos.get(tipo);
                lancamentos.put(tipo, valorAtual + fatura.getValorTotal());
            }
        }
        
        return lancamentos;
    }
    
    /**
     * Obtém faturas por período
     */
    public List<Fatura> getFaturasPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return faturasArrayList.stream()
                .filter(f -> f.getDataProcessamento().isAfter(inicio) && 
                           f.getDataProcessamento().isBefore(fim))
                .collect(Collectors.toList());
    }
    
    /**
     * Obtém faturas por tipo de pagamento
     */
    public List<Fatura> getFaturasPorTipoPagamento(Fatura.TipoPagamento tipoPagamento) {
        return faturasArrayList.stream()
                .filter(f -> f.getTipoPagamento() == tipoPagamento)
                .collect(Collectors.toList());
    }
    
    /**
     * Obtém faturas por tipo de pagamento e período
     */
    public List<Fatura> getFaturasPorTipoPagamentoEPeriodo(Fatura.TipoPagamento tipoPagamento,
                                                          LocalDateTime inicio, LocalDateTime fim) {
        return faturasArrayList.stream()
                .filter(f -> f.getTipoPagamento() == tipoPagamento &&
                           f.getDataProcessamento().isAfter(inicio) && 
                           f.getDataProcessamento().isBefore(fim))
                .collect(Collectors.toList());
    }
    
    /**
     * Calcula total de vendas do dia
     */
    public double getTotalVendasDia() {
        return lancamentosDia.values().stream()
                .mapToDouble(Double::doubleValue)
                .sum();
    }
    
    /**
     * Calcula total de vendas por data
     */
    public double getTotalVendasPorData(LocalDate data) {
        return getLancamentosPorData(data).values().stream()
                .mapToDouble(Double::doubleValue)
                .sum();
    }
    
    /**
     * Fecho de caixa - zera os lançamentos do dia e retorna resumo
     */
    public Map<String, Object> fechoCaixa() {
        Map<String, Object> resumo = new HashMap<>();
        resumo.put("data", LocalDate.now());
        resumo.put("totalVendas", getTotalVendasDia());
        resumo.put("lancamentosPorTipo", new HashMap<>(lancamentosDia));
        resumo.put("quantidadeFaturas", faturasArrayList.size());
        
        // Zerar lançamentos
        inicializarLancamentos();
        
        return resumo;
    }
    
    /**
     * Método recursivo para calcular total de vendas por vendedor
     */
    public double calcularTotalVendasVendedor(String vendedorId) {
        return calcularTotalVendasVendedorRecursivo(faturasArrayList, vendedorId, 0);
    }
    
    private double calcularTotalVendasVendedorRecursivo(List<Fatura> faturas, String vendedorId, int index) {
        if (index >= faturas.size()) {
            return 0.0;
        }
        
        Fatura fatura = faturas.get(index);
        double valorFatura = fatura.getVendedorId().equals(vendedorId) ? fatura.getValorTotal() : 0.0;
        
        return valorFatura + calcularTotalVendasVendedorRecursivo(faturas, vendedorId, index + 1);
    }
    
    /**
     * Obtém estatísticas de vendas por caixa
     */
    public Map<String, Double> getEstatisticasPorCaixa() {
        Map<String, Double> estatisticas = new HashMap<>();
        
        for (Fatura fatura : faturasArrayList) {
            String caixaId = fatura.getCaixaId();
            double valorAtual = estatisticas.getOrDefault(caixaId, 0.0);
            estatisticas.put(caixaId, valorAtual + fatura.getValorTotal());
        }
        
        return estatisticas;
    }
    
}
