/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.info.stel.leitorfebrabam.old;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author p006184
 */
public class V2Service {

    private FileWriter header;
    private FileWriter resume;
    private FileWriter adress;
    private FileWriter calls;
    private FileWriter services;
    private FileWriter desconts;
    private FileWriter total;

    public boolean isV2(FileReader file) {
        try {
            BufferedReader readFile = new BufferedReader(file);
            String line = readFile.readLine();
            System.out.println(line.substring(0, 1));
            System.out.println(line.substring(1, 13));
            System.out.println(line.substring(13, 21));
            System.out.println(line.substring(21, 36));
            
            if (line.length() == 350 && line.substring(0, 13).equals("0000000000001")) {
                
                return true;
            }
        } catch (IOException ex) {
            Logger.getLogger(V2Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public void generateV2(FileReader file) {
        
        String processDate = getCurrentDate();
        try {
            BufferedReader readFile = new BufferedReader(file);
            String line = readFile.readLine();
            String operadora = line.substring(21, 36);
            header = new FileWriter("c:\\" + "V2_" + processDate + "_" + operadora + "\\header"+ line.substring(123, 139) + ".txt");
            PrintWriter writeHeader = new PrintWriter(header);
            writeHeader.printf("TESTE");
            header.close();

            while (line != null) {

                if (line.charAt(0) == '0') {

                }
                if (line.charAt(0) == '1') {
                }
                if (line.charAt(0) == '2') {
                }
                if (line.charAt(0) == '3') {
                }
                if (line.charAt(0) == '4') {
                }
                if (line.charAt(0) == '5') {
                }
                if (line.charAt(0) == '9') {
                }

                line = readFile.readLine();
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FebrabamService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FebrabamService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy");
        return sdf.format(new Date(System.currentTimeMillis()));
    }
}
