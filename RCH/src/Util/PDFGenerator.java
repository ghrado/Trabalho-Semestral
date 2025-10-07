package Util;

import Model.Cliente;
import Model.ItemOrdem;
import Model.Ordem;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.File;
import java.io.FileOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Classe para gerar documentos PDF (Cotações e Faturas)
 */
public class PDFGenerator {
    
    private static final String PASTA_DOCUMENTOS = "documentos/";
    private static final Font FONT_TITULO = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
    private static final Font FONT_SUBTITULO = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
    private static final Font FONT_NORMAL = new Font(Font.FontFamily.HELVETICA, 12);
    private static final Font FONT_SMALL = new Font(Font.FontFamily.HELVETICA, 10);
    
    static {
        // Criar pasta de documentos se não existir
        File pasta = new File(PASTA_DOCUMENTOS);
        if (!pasta.exists()) {
            pasta.mkdirs();
        }
    }
    
    /**
     * Gera uma cotação em PDF
     */
    public static String gerarCotacao(Ordem ordem) {
        try {
            String nomeArquivo = PASTA_DOCUMENTOS + "cotacao_" + ordem.getCodigo() + ".pdf";
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(nomeArquivo));
            
            document.open();
            
            // Cabeçalho
            adicionarCabecalho(document, "COTAÇÃO");
            
            // Informações da cotação
            Paragraph info = new Paragraph();
            info.add(new Chunk("Número: ", FONT_SUBTITULO));
            info.add(new Chunk(ordem.getCodigo() + "\n", FONT_NORMAL));
            info.add(new Chunk("Data: ", FONT_SUBTITULO));
            info.add(new Chunk(ordem.getDataCriacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + "\n\n", FONT_NORMAL));
            document.add(info);
            
            // Dados do cliente
            adicionarDadosCliente(document, ordem.getCliente());
            
            // Tabela de itens
            adicionarTabelaItens(document, ordem.getItens());
            
            // Total
            adicionarTotal(document, ordem.getValorTotal());
            
            // Rodapé
            adicionarRodape(document, "Esta cotação é válida por 30 dias.");
            
            document.close();
            return nomeArquivo;
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Gera uma fatura em PDF
     */
    public static String gerarFatura(Ordem ordem) {
        try {
            String nomeArquivo = PASTA_DOCUMENTOS + "fatura_" + ordem.getCodigo() + ".pdf";
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(nomeArquivo));
            
            document.open();
            
            // Cabeçalho
            adicionarCabecalho(document, "FATURA");
            
            // Informações da fatura
            Paragraph info = new Paragraph();
            info.add(new Chunk("Número: ", FONT_SUBTITULO));
            info.add(new Chunk(ordem.getCodigo() + "\n", FONT_NORMAL));
            info.add(new Chunk("Data: ", FONT_SUBTITULO));
            info.add(new Chunk(ordem.getDataCriacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + "\n", FONT_NORMAL));
            info.add(new Chunk("Status: ", FONT_SUBTITULO));
            info.add(new Chunk(ordem.getStatus().toString() + "\n\n", FONT_NORMAL));
            document.add(info);
            
            // Dados do cliente
            adicionarDadosCliente(document, ordem.getCliente());
            
            // Tabela de itens
            adicionarTabelaItens(document, ordem.getItens());
            
            // Total
            adicionarTotal(document, ordem.getValorTotal());
            
            // Rodapé
            adicionarRodape(document, "Obrigado pela sua preferência!");
            
            document.close();
            return nomeArquivo;
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private static void adicionarCabecalho(Document document, String titulo) throws DocumentException {
        Paragraph header = new Paragraph();
        header.add(new Chunk("SISTEMA DE GESTÃO\n", FONT_TITULO));
        header.add(new Chunk(titulo + "\n\n", FONT_TITULO));
        header.setAlignment(Element.ALIGN_CENTER);
        document.add(header);
        
        // Linha separadora
        LineSeparator line = new LineSeparator();
        document.add(new Chunk(line));
        document.add(new Paragraph("\n"));
    }
    
    private static void adicionarDadosCliente(Document document, Cliente cliente) throws DocumentException {
        Paragraph clienteInfo = new Paragraph();
        clienteInfo.add(new Chunk("DADOS DO CLIENTE\n", FONT_SUBTITULO));
        clienteInfo.add(new Chunk("Nome: ", FONT_NORMAL));
        clienteInfo.add(new Chunk(cliente.getNome() + "\n", FONT_NORMAL));
        clienteInfo.add(new Chunk("ID: ", FONT_NORMAL));
        clienteInfo.add(new Chunk(cliente.getId() + "\n", FONT_NORMAL));
        clienteInfo.add(new Chunk("Telefone: ", FONT_NORMAL));
        clienteInfo.add(new Chunk(cliente.getTelefone() + "\n", FONT_NORMAL));
        clienteInfo.add(new Chunk("Email: ", FONT_NORMAL));
        clienteInfo.add(new Chunk(cliente.getEmail() + "\n", FONT_NORMAL));
        clienteInfo.add(new Chunk("Endereço: ", FONT_NORMAL));
        clienteInfo.add(new Chunk(cliente.getEndereco() + "\n\n", FONT_NORMAL));
        document.add(clienteInfo);
    }
    
    private static void adicionarTabelaItens(Document document, List<ItemOrdem> itens) throws DocumentException {
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);
        
        // Cabeçalho da tabela
        PdfPCell cell;
        String[] headers = {"Código", "Descrição", "Quantidade", "Preço Unit.", "Subtotal"};
        for (String header : headers) {
            cell = new PdfPCell(new Phrase(header, FONT_SUBTITULO));
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        }
        
        // Itens
        for (ItemOrdem item : itens) {
            table.addCell(new Phrase(item.getProduto().getCodigo(), FONT_SMALL));
            table.addCell(new Phrase(item.getProduto().getDescricao(), FONT_SMALL));
            table.addCell(new Phrase(String.valueOf(item.getQuantidade()), FONT_SMALL));
            table.addCell(new Phrase(String.format("%.2f MT", item.getPrecoUnitario()), FONT_SMALL));
            table.addCell(new Phrase(String.format("%.2f MT", item.getSubtotal()), FONT_SMALL));
        }
        
        document.add(table);
    }
    
    private static void adicionarTotal(Document document, double total) throws DocumentException {
        Paragraph totalParagraph = new Paragraph();
        totalParagraph.setAlignment(Element.ALIGN_RIGHT);
        totalParagraph.add(new Chunk("TOTAL: ", FONT_SUBTITULO));
        totalParagraph.add(new Chunk(String.format("%.2f MT", total), FONT_TITULO));
        document.add(totalParagraph);
    }
    
    private static void adicionarRodape(Document document, String mensagem) throws DocumentException {
        document.add(new Paragraph("\n\n"));
        Paragraph footer = new Paragraph(mensagem, FONT_SMALL);
        footer.setAlignment(Element.ALIGN_CENTER);
        document.add(footer);
    }
}
