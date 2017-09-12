/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tst.leitorfebrabam.model.v2;

import java.math.BigDecimal;

/**
 *
 * @author p006184
 */
public class TraillerV2 {

    private String tipoRegistro;
    private int controleSequencialGravacao;
    private String codCliente;
    private String identContaUnica;
    private String dtaVencimento;
    private String dtaEmissao;
    private int qtdRegistros;
    private int qtdLinhasTelefonicas;
    private String sinalTotal;
    private BigDecimal valTotal;

    public String getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(String tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public int getControleSequencialGravacao() {
        return controleSequencialGravacao;
    }

    public void setControleSequencialGravacao(int controleSequencialGravacao) {
        this.controleSequencialGravacao = controleSequencialGravacao;
    }

    public String getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(String codCliente) {
        this.codCliente = codCliente;
    }

    public String getIdentContaUnica() {
        return identContaUnica;
    }

    public void setIdentContaUnica(String identContaUnica) {
        this.identContaUnica = identContaUnica;
    }

    public String getDtaVencimento() {
        return dtaVencimento;
    }

    public void setDtaVencimento(String dtaVencimento) {
        this.dtaVencimento = dtaVencimento;
    }

    public String getDtaEmissao() {
        return dtaEmissao;
    }

    public void setDtaEmissao(String dtaEmissao) {
        this.dtaEmissao = dtaEmissao;
    }

    public int getQtdRegistros() {
        return qtdRegistros;
    }

    public void setQtdRegistros(int qtdRegistros) {
        this.qtdRegistros = qtdRegistros;
    }

    public int getQtdLinhasTelefonicas() {
        return qtdLinhasTelefonicas;
    }

    public void setQtdLinhasTelefonicas(int qtdLinhasTelefonicas) {
        this.qtdLinhasTelefonicas = qtdLinhasTelefonicas;
    }

    public String getSinalTotal() {
        return sinalTotal;
    }

    public void setSinalTotal(String sinalTotal) {
        this.sinalTotal = sinalTotal;
    }

    public BigDecimal getValTotal() {
        return valTotal;
    }

    public void setValTotal(BigDecimal valTotal) {
        this.valTotal = valTotal;
    }

    @Override
    public String toString() {
        return "TraillerV2{" + "tipoRegistro=" + tipoRegistro + ", controleSequencialGravacao=" + controleSequencialGravacao + ", codCliente=" + codCliente + ", identContaUnica=" + identContaUnica + ", dtaVencimento=" + dtaVencimento + ", dtaEmissao=" + dtaEmissao + ", qtdRegistros=" + qtdRegistros + ", qtdLinhasTelefonicas=" + qtdLinhasTelefonicas + ", sinalTotal=" + sinalTotal + ", valTotal=" + valTotal + '}';
    }

}
