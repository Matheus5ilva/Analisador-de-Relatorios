package br.com.analisador.core.utils;

import br.com.analisador.domain.model.Resultado;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GeradorPDFUtil {

    public String gerarPDF(Resultado resultado) {
        String caminhoDoArquivo = "";

        try {
            Document document = new Document();
            caminhoDoArquivo = File.createTempFile(resultado.getNomeRelatorio() + "-" + resultado.getEmpresa().getId(), ".pdf").getAbsolutePath();
            PdfWriter.getInstance(document, new FileOutputStream(caminhoDoArquivo));
            document.open();

            Font fontTitulo = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
            Font fontParagrafo = new Font(Font.FontFamily.TIMES_ROMAN, 12);

            Paragraph tituloDoPDF = new Paragraph(resultado.getNomeRelatorio(), fontTitulo);
            tituloDoPDF.setAlignment(Element.ALIGN_CENTER);
            document.add(tituloDoPDF);

            Paragraph paragrafo = new Paragraph(resultado.getAnalise(), fontParagrafo);
            document.add(paragrafo);

            document.close();

            // Agenda a exclusão do arquivo após 15 minutos
            ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
            final String finalCaminhoDoArquivo = caminhoDoArquivo;
            executor.schedule(() -> {
                try {
                    Files.deleteIfExists(Path.of(finalCaminhoDoArquivo));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }, 15, TimeUnit.MINUTES);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return caminhoDoArquivo;
    }
}
