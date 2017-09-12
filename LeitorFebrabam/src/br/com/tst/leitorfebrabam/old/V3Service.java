/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tst.leitorfebrabam.old;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author p006184
 */
public class V3Service {

    public boolean readFile(FileReader febrabamFile, Fatura fatura) {
        BufferedReader readFile = new BufferedReader(febrabamFile);
        try {
            String line = readFile.readLine();
            if (isV2(line)) {
                return false;
            } else if (isV3(line)) {
                generateV3(readFile, line, fatura);
                return true;
            } else {
                return false;
            }
        } catch (IOException ex) {
            Logger.getLogger(V3Service.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    private boolean isV2(String line) {
        if (line.length() == 350 && line.substring(0, 13).equals("0000000000001")) {

            return true;
        }
        return false;
    }

    private boolean isV3(String line) {
        if (line.length() == 350 && line.substring(164, 168).equals("V3R0")) {
            return true;
        }
        return false;
    }

    private void generateV3(BufferedReader readFile, String line, Fatura fatura) {
        BigDecimal valTotalChamadasResumo = new BigDecimal("0.0");
        BigDecimal valTotalServicosResumo = new BigDecimal("0.0");
        BigDecimal valTotalImpostosResumo = new BigDecimal("0.0");
        
        BigDecimal valTotalChamadasCalculadoComImposto = new BigDecimal("0.0");
        BigDecimal valTotalChamadasCalculadoSemImposto = new BigDecimal("0.0");
        
        BigDecimal valTotalServicosCalculadoComImposto = new BigDecimal("0.0");
        BigDecimal valTotalServicosCalculadoSemImposto = new BigDecimal("0.0");
        
        BigDecimal valTotalDescontos = new BigDecimal("0.0");
        
        
        
        try {

            while (line != null) {
                if (line.charAt(0) == '0') {
                    fatura.setIdentificadorContaUnica(line.substring(14, 39));
                    fatura.setNumeroFatura(line.substring(168, 184));
                    fatura.setDataEmissao(line.substring(39, 47));
                    fatura.setMesRefencia(line.substring(47, 53));
                    fatura.setDataVencimento(line.substring(61, 69));
                }if (line.charAt(0) == '1') {
                    valTotalChamadasResumo = valTotalChamadasResumo.add(formatValueComImposto(line.substring(128, 141)));
                    valTotalServicosResumo = valTotalServicosResumo.add(formatValueImpostoResumo(line.substring(150, 165)));
                    valTotalImpostosResumo = valTotalImpostosResumo.add(formatValueComImposto(line.substring(165, 178)));
                    
                }if (line.charAt(0) == '3') {
                    valTotalChamadasCalculadoComImposto = valTotalChamadasCalculadoComImposto.add(formatValueComImposto(line.substring(237, 250)));
                    valTotalChamadasCalculadoSemImposto = valTotalChamadasCalculadoSemImposto.add(formatValueSemImposto(line.substring(250, 265)));
                }
                if (line.charAt(0) == '4') {
                    valTotalServicosCalculadoComImposto = valTotalServicosCalculadoComImposto.add(formatValueComImposto(line.substring(179, 192)));
                    valTotalServicosCalculadoSemImposto = valTotalServicosCalculadoSemImposto.add(formatValueSemImposto(line.substring(192, 207)));
                }
                if (line.charAt(0) == '5') {
                    valTotalDescontos = valTotalDescontos.add(formatValueComImposto(line.substring(158, 171)));
                }

                line = readFile.readLine();
            }
            
            fatura.setValTotalChamadasResumo(valTotalChamadasResumo);
            fatura.setValTotalServicosResumo(valTotalServicosResumo);
            fatura.setValTotalImpostosResumo(valTotalImpostosResumo);
            
            fatura.setValTotalChamadasCalculadoComImposto(valTotalChamadasCalculadoComImposto);
            fatura.setValTotalChamadasCalculadoSemImposto(valTotalChamadasCalculadoSemImposto);
            
            fatura.setValTotalServicosCalculadoComImposto(valTotalServicosCalculadoComImposto);
            fatura.setValTotalServicosCalculadoSemImposto(valTotalServicosCalculadoSemImposto);
            
            fatura.setValTotalDescontos(valTotalDescontos);
        } catch (IOException ex) {
            Logger.getLogger(V3Service.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }    
    
    private BigDecimal formatValueComImposto(String value) {
        return new BigDecimal(value.substring(0, 11) + "." + value.substring(11, 13));
    }
    
    private BigDecimal formatValueSemImposto(String value) {
        return new BigDecimal(value.substring(0, 11) + "." + value.substring(11, 15));
    }
    
    private BigDecimal formatValueImpostoResumo(String value) {
        return new BigDecimal(value.substring(0, 13) + "." + value.substring(13, 15));
    }

}
