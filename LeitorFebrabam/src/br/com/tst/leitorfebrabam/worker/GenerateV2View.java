/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.tst.leitorfebrabam.worker;

import br.com.tst.leitorfebrabam.file.Febrabam;
import java.util.List;
import javax.swing.SwingWorker;

/**
 *
 * @author p006184
 */
public class GenerateV2View extends SwingWorker<List<String>, String>{    
    final Febrabam febrabam;
    public GenerateV2View(Febrabam febrabam) {
        this.febrabam = febrabam;
    }

    @Override
    protected List<String> doInBackground() throws Exception {
        int totalOfLines = febrabam.getTotalOfLines();
        
        for (int i = 0; i < totalOfLines; i++) {
            setProgress(Math.round(((float) i / (float) totalOfLines)*100f));
            createV2(febrabam.getLine());
        }
        
        return null;
    }

    private void createV2(String line) {
        
    }
    
}
