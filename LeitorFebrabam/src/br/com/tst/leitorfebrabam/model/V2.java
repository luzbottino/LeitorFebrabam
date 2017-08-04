/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tst.leitorfebrabam.model;

import java.util.List;

/**
 *
 * @author p006184
 */
public class V2 {

    private HeaderV2 header;
    private List<ResumoV2> resumosV2;

    public HeaderV2 getHeader() {
        return header;
    }

    public void setHeader(HeaderV2 header) {
        this.header = header;
    }

    public List<ResumoV2> getResumosV2() {
        return resumosV2;
    }

    public void setResumosV2(List<ResumoV2> resumosV2) {
        this.resumosV2 = resumosV2;
    }

    @Override
    public String toString() {
        return "V2{" + "header=" + header + ", resumosV2=" + resumosV2 + '}';
    }

}
