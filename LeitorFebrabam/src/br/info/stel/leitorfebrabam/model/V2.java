/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.info.stel.leitorfebrabam.model;

import br.info.stel.leitorfebrabam.model.v2.DescontoV2;
import br.info.stel.leitorfebrabam.model.v2.ServicoV2;
import br.info.stel.leitorfebrabam.model.v2.ResumoV2;
import br.info.stel.leitorfebrabam.model.v2.HeaderV2;
import br.info.stel.leitorfebrabam.model.v2.TraillerV2;
import br.info.stel.leitorfebrabam.model.v2.BilhetacaoV2;
import br.info.stel.leitorfebrabam.model.v2.EnderecoABV2;
import java.util.List;

/**
 *
 * @author p006184
 */
public class V2 {

    private HeaderV2 header;
    private List<ResumoV2> resumosV2;
    private List<EnderecoABV2> enderecosABV2;
    private List<BilhetacaoV2> bilhetacoesV2;
    private List<ServicoV2> servicosV2;
    private List<DescontoV2> descontosV2;
    private TraillerV2 trailler;

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

    public List<EnderecoABV2> getEnderecosABV2() {
        return enderecosABV2;
    }

    public void setEnderecosABV2(List<EnderecoABV2> enderecosABV2) {
        this.enderecosABV2 = enderecosABV2;
    }

    public List<BilhetacaoV2> getBilhetacoesV2() {
        return bilhetacoesV2;
    }

    public void setBilhetacoesV2(List<BilhetacaoV2> bilhetacoesV2) {
        this.bilhetacoesV2 = bilhetacoesV2;
    }

    public List<ServicoV2> getServicosV2() {
        return servicosV2;
    }

    public void setServicosV2(List<ServicoV2> servicosV2) {
        this.servicosV2 = servicosV2;
    }

    public List<DescontoV2> getDescontosV2() {
        return descontosV2;
    }

    public void setDescontosV2(List<DescontoV2> descontosV2) {
        this.descontosV2 = descontosV2;
    }

    public TraillerV2 getTrailler() {
        return trailler;
    }

    public void setTrailler(TraillerV2 trailler) {
        this.trailler = trailler;
    }

    @Override
    public String toString() {
        return "V2{" + "header=" + header + ", resumosV2=" + resumosV2 + ", enderecosABV2=" + enderecosABV2 + ", bilhetacoesV2=" + bilhetacoesV2 + ", servicosV2=" + servicosV2 + ", descontosV2=" + descontosV2 + ", trailler=" + trailler + '}';
    }

}
