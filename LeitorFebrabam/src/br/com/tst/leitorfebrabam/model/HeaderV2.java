/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tst.leitorfebrabam.model;

/**
 *
 * @author p006184
 */
public class HeaderV2 {

    private String tipoRegistro;
    private int controleSeguencialGravacao;
    private String dtaGeracaoArquivo;
    private String operadora;
    private String ufOperadora;
    private String codCliente;
    private String nomCliente;
    private String cgcCliente;
    private String identContaUnica;
    private String mesReferencia;
    private String dtaVencimento;
    private String dtaEmissao;

    public String getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(String tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public int getControleSeguencialGravacao() {
        return controleSeguencialGravacao;
    }

    public void setControleSeguencialGravacao(int controleSeguencialGravacao) {
        this.controleSeguencialGravacao = controleSeguencialGravacao;
    }

    public String getDtaGeracaoArquivo() {
        return dtaGeracaoArquivo;
    }

    public void setDtaGeracaoArquivo(String dtaGeracaoArquivo) {
        this.dtaGeracaoArquivo = dtaGeracaoArquivo;
    }

    public String getOperadora() {
        return operadora;
    }

    public void setOperadora(String operadora) {
        this.operadora = operadora;
    }

    public String getUfOperadora() {
        return ufOperadora;
    }

    public void setUfOperadora(String ufOperadora) {
        this.ufOperadora = ufOperadora;
    }

    public String getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(String codCliente) {
        this.codCliente = codCliente;
    }

    public String getNomCliente() {
        return nomCliente;
    }

    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }

    public String getCgcCliente() {
        return cgcCliente;
    }

    public void setCgcCliente(String cgcCliente) {
        this.cgcCliente = cgcCliente;
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

    public String getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(String mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    @Override
    public String toString() {
        return "HeaderV2{" + "tipoRegistro=" + tipoRegistro + ", controleSeguencialGravacao=" + controleSeguencialGravacao + ", dtaGeracaoArquivo=" + dtaGeracaoArquivo + ", operadora=" + operadora + ", ufOperadora=" + ufOperadora + ", codCliente=" + codCliente + ", nomCliente=" + nomCliente + ", cgcCliente=" + cgcCliente + ", identContaUnica=" + identContaUnica + ", mesReferencia=" + mesReferencia + ", dtaVencimento=" + dtaVencimento + ", dtaEmissao=" + dtaEmissao + '}';
    }

}
