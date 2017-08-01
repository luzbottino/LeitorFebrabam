/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tst.leitorfebrabam.worker;

import br.com.tst.leitorfebrabam.file.Febrabam;
import br.com.tst.leitorfebrabam.model.HeaderV2;
import br.com.tst.leitorfebrabam.model.V2;
import java.util.List;
import javax.swing.SwingWorker;

/**
 *
 * @author p006184
 */
public class GenerateV2View extends SwingWorker<List<String>, String> {

    private final Febrabam febrabam;
    private V2 v2;

    public GenerateV2View(Febrabam febrabam) {
        this.febrabam = febrabam;
    }

    @Override
    protected List<String> doInBackground() throws Exception {
        int totalOfLines = febrabam.getTotalOfLines();

        for (int i = 0; i < totalOfLines; i++) {
            setProgress(Math.round(((float) i / (float) totalOfLines) * 100f));
            buildV2(febrabam.getLine());
        }

        return null;
    }

    private void buildV2(String line) {
        if (line.charAt(0) == '0') {
            buildHeader(line);
        }
    }

    private void buildHeader(String line) {
        HeaderV2 headerV2 = new HeaderV2();
        headerV2.setTipoRegistro(line.charAt(0));        
        headerV2.setControleSeguencialGravacao(line.charAt(13));
        headerV2.setDtaGeracaoArquivo(line.substring(13, 21));
        headerV2.setOperadora(line.substring(21, 36));
        headerV2.setUfOperadora(line.substring(36, 38));
        headerV2.setCodCliente(line.substring(38, 53));
        headerV2.setNomCliente(line.substring(53, 93));
        headerV2.setCgcCliente(line.substring(93, 108));
        headerV2.setIdentContaUnica(line.substring(108, 133));
        headerV2.setDtaVencimento(line.substring(133, 141));
        headerV2.setDtaEmissao(line.substring(141, 149));
    }

}
