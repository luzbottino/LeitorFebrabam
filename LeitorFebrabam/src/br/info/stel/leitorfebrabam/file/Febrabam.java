/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.info.stel.leitorfebrabam.file;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author p006184
 */
public class Febrabam {
    
    private String path;
    private BufferedReader br;
    private String line;

    public void openFile(String path) {
        this.path = path;
        try {
            br = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Febrabam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void readLine() throws IOException {
        line = br.readLine();
    }
    public String getLine(){
        return line;
    }
    
    public void closeFebrabam(){
        try {
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(Febrabam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isV2() throws IOException {

        if (line.length() == 350 && line.substring(0, 13).equals("0000000000001")) {
            return true;
        }

        return false;
    }

    public boolean isV3() throws IOException {

        if (line.length() == 350 && line.substring(164, 168).equals("V3R0")) {
            return true;
        }

        return false;
    }
    
    public int getTotalOfLines() throws IOException{
        BufferedReader is = new BufferedReader(new FileReader(path));        
        int count = 0;        
        
        while(is.readLine() != null){
            count++;
        }
        
        is.close();
        return count;                
    }
}
