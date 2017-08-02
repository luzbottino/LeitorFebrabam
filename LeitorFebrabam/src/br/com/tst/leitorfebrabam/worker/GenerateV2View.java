/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tst.leitorfebrabam.worker;

import br.com.tst.leitorfebrabam.file.Febrabam;
import br.com.tst.leitorfebrabam.model.HeaderV2;
import br.com.tst.leitorfebrabam.model.V2;
import br.com.tst.leitorfebrabam.view.FaturaV2;
import br.com.tst.leitorfebrabam.view.FaturaV2_v2;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author p006184
 */
public class GenerateV2View extends SwingWorker<V2, V2> {

    private final Febrabam febrabam;
    private V2 v2;

    public GenerateV2View(Febrabam febrabam) {
        this.febrabam = febrabam;
    }

    @Override
    protected V2 doInBackground() throws Exception {
        v2 = new V2();
        
        int totalOfLines = febrabam.getTotalOfLines();

        for (int i = 0; i < totalOfLines; i++) {
            setProgress(Math.round(((float) i / (float) totalOfLines) * 100f));
            buildV2(febrabam.getLine());
        }
        System.out.println(v2.toString());

        return v2;
    }

    private void buildV2(String line) {
        if (line.charAt(0) == '0') {
            v2.setHeader(buildHeader(line));
        }
    }

    private HeaderV2 buildHeader(String line) {
        HeaderV2 headerV2 = new HeaderV2();
        headerV2.setTipoRegistro(line.charAt(0));        
        headerV2.setControleSeguencialGravacao(line.charAt(13));
        headerV2.setDtaGeracaoArquivo(formatDate(line.substring(13, 21)));
        headerV2.setOperadora(removeWhiteSpacesInTheEnd(line.substring(21, 36)));
        headerV2.setUfOperadora(line.substring(36, 38));
        headerV2.setCodCliente(removeWhiteSpacesInTheEnd(line.substring(38, 53)));
        headerV2.setNomCliente(removeWhiteSpacesInTheEnd(line.substring(53, 93)));
        headerV2.setCgcCliente(removeWhiteSpacesInTheEnd(line.substring(93, 108)));
        headerV2.setIdentContaUnica(removeWhiteSpacesInTheEnd(line.substring(108, 123)));
        headerV2.setMesReferencia(removeWhiteSpacesInTheEnd(line.substring(123, 133)));
        headerV2.setDtaVencimento(formatDate(line.substring(133, 141)));
        headerV2.setDtaEmissao(formatDate(line.substring(141, 149)));
        
        return headerV2;
    }

    private String removeWhiteSpacesInTheEnd(String substring) {
        return StringUtils.stripEnd(substring, " ");
    }
    
    private String formatDate(String substring) {
        return substring.substring(6, 8) + "/" + substring.substring(4, 6) + "/" + substring.substring(0, 4);
    }
    
    @Override
    protected void done() {
        try {
            V2 v2 = get();
            new FaturaV2_v2(v2).setVisible(true);
        } catch (InterruptedException ex) {
            Logger.getLogger(GenerateV2View.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(GenerateV2View.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

