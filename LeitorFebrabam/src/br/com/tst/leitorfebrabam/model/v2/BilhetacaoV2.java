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
public class BilhetacaoV2 {

    private String tipoRegistro;
    private int controleSequencialGravacao;
    private String dtaVencimento;
    private String dtaEmissao;
    private String identificadorUnicoRecurso;
    private int cnlRecursoReferencia;
    private String Ddd;
    private String numTelefone;
    private String caracteristicaRecurso;
    private String degrauRecurso;
    private String dtaLigacao;
    private int cnlLocalidadeChamada;
    private String nomeLocalidadeChamada;
    private String ufTelefoneChamado;
    private String codNacionalInternacional;
    private String codOperadora;
    private String descOperadora;
    private String codPaisChamado;
    private String areaDdd;
    private String numTelefoneChamado;
    private String conjugadoNumeroTelefoneChamado;
    private String duracaoLigacao;
    private String categoria;
    private String descCategoria;
    private String horLigacao;
    private String tipChamada;
    private String grupoHorTarifario;
    private String descHorTarifario;
    private int degrauLigacao;
    private String sinalValLigacao;
    private String aliquotaICMS;
    private BigDecimal valLigacaoComImposto;
    private String classeServico;

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

    public String getIdentificadorUnicoRecurso() {
        return identificadorUnicoRecurso;
    }

    public void setIdentificadorUnicoRecurso(String identificadorUnicoRecurso) {
        this.identificadorUnicoRecurso = identificadorUnicoRecurso;
    }

    public int getCnlRecursoReferencia() {
        return cnlRecursoReferencia;
    }

    public void setCnlRecursoReferencia(int cnlRecursoReferencia) {
        this.cnlRecursoReferencia = cnlRecursoReferencia;
    }

    public String getDdd() {
        return Ddd;
    }

    public void setDdd(String Ddd) {
        this.Ddd = Ddd;
    }

    public String getNumTelefone() {
        return numTelefone;
    }

    public void setNumTelefone(String numTelefone) {
        this.numTelefone = numTelefone;
    }

    public String getCaracteristicaRecurso() {
        return caracteristicaRecurso;
    }

    public void setCaracteristicaRecurso(String caracteristicaRecurso) {
        this.caracteristicaRecurso = caracteristicaRecurso;
    }

    public String getDegrauRecurso() {
        return degrauRecurso;
    }

    public void setDegrauRecurso(String degrauRecurso) {
        this.degrauRecurso = degrauRecurso;
    }

    public String getDtaLigacao() {
        return dtaLigacao;
    }

    public void setDtaLigacao(String dtaLigacao) {
        this.dtaLigacao = dtaLigacao;
    }

    public int getCnlLocalidadeChamada() {
        return cnlLocalidadeChamada;
    }

    public void setCnlLocalidadeChamada(int cnlLocalidadeChamada) {
        this.cnlLocalidadeChamada = cnlLocalidadeChamada;
    }

    public String getNomeLocalidadeChamada() {
        return nomeLocalidadeChamada;
    }

    public void setNomeLocalidadeChamada(String nomeLocalidadeChamada) {
        this.nomeLocalidadeChamada = nomeLocalidadeChamada;
    }

    public String getUfTelefoneChamado() {
        return ufTelefoneChamado;
    }

    public void setUfTelefoneChamado(String ufTelefoneChamado) {
        this.ufTelefoneChamado = ufTelefoneChamado;
    }

    public String getCodNacionalInternacional() {
        return codNacionalInternacional;
    }

    public void setCodNacionalInternacional(String codNacionalInternacional) {
        this.codNacionalInternacional = codNacionalInternacional;
    }

    public String getCodOperadora() {
        return codOperadora;
    }

    public void setCodOperadora(String codOperadora) {
        this.codOperadora = codOperadora;
    }

    public String getDescOperadora() {
        return descOperadora;
    }

    public void setDescOperadora(String descOperadora) {
        this.descOperadora = descOperadora;
    }

    public String getCodPaisChamado() {
        return codPaisChamado;
    }

    public void setCodPaisChamado(String codPaisChamado) {
        this.codPaisChamado = codPaisChamado;
    }

    public String getAreaDdd() {
        return areaDdd;
    }

    public void setAreaDdd(String areaDdd) {
        this.areaDdd = areaDdd;
    }

    public String getNumTelefoneChamado() {
        return numTelefoneChamado;
    }

    public void setNumTelefoneChamado(String numTelefoneChamado) {
        this.numTelefoneChamado = numTelefoneChamado;
    }

    public String getConjugadoNumeroTelefoneChamado() {
        return conjugadoNumeroTelefoneChamado;
    }

    public void setConjugadoNumeroTelefoneChamado(String conjugadoNumeroTelefoneChamado) {
        this.conjugadoNumeroTelefoneChamado = conjugadoNumeroTelefoneChamado;
    }

    public String getDuracaoLigacao() {
        return duracaoLigacao;
    }

    public void setDuracaoLigacao(String duracaoLigacao) {
        this.duracaoLigacao = duracaoLigacao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescCategoria() {
        return descCategoria;
    }

    public void setDescCategoria(String descCategoria) {
        this.descCategoria = descCategoria;
    }

    public String getHorLigacao() {
        return horLigacao;
    }

    public void setHorLigacao(String horLigacao) {
        this.horLigacao = horLigacao;
    }

    public String getTipChamada() {
        return tipChamada;
    }

    public void setTipChamada(String tipChamada) {
        this.tipChamada = tipChamada;
    }

    public String getGrupoHorTarifario() {
        return grupoHorTarifario;
    }

    public void setGrupoHorTarifario(String grupoHorTarifario) {
        this.grupoHorTarifario = grupoHorTarifario;
    }

    public String getDescHorTarifario() {
        return descHorTarifario;
    }

    public void setDescHorTarifario(String descHorTarifario) {
        this.descHorTarifario = descHorTarifario;
    }

    public int getDegrauLigacao() {
        return degrauLigacao;
    }

    public void setDegrauLigacao(int degrauLigacao) {
        this.degrauLigacao = degrauLigacao;
    }

    public String getSinalValLigacao() {
        return sinalValLigacao;
    }

    public void setSinalValLigacao(String sinalValLigacao) {
        this.sinalValLigacao = sinalValLigacao;
    }

    public String getAliquotaICMS() {
        return aliquotaICMS;
    }

    public void setAliquotaICMS(String aliquotaICMS) {
        this.aliquotaICMS = aliquotaICMS;
    }

    public BigDecimal getValLigacaoComImposto() {
        return valLigacaoComImposto;
    }

    public void setValLigacaoComImposto(BigDecimal valLigacaoComImposto) {
        this.valLigacaoComImposto = valLigacaoComImposto;
    }

    public String getClasseServico() {
        return classeServico;
    }

    public void setClasseServico(String classeServico) {
        this.classeServico = classeServico;
    }

    @Override
    public String toString() {
        return "BilhetacaoV2{" + "tipoRegistro=" + tipoRegistro + ", controleSequencialGravacao=" + controleSequencialGravacao + ", dtaVencimento=" + dtaVencimento + ", dtaEmissao=" + dtaEmissao + ", identificadorUnicoRecurso=" + identificadorUnicoRecurso + ", cnlRecursoReferencia=" + cnlRecursoReferencia + ", Ddd=" + Ddd + ", numTelefone=" + numTelefone + ", caracteristicaRecurso=" + caracteristicaRecurso + ", degrauRecurso=" + degrauRecurso + ", dtaLigacao=" + dtaLigacao + ", cnlLocalidadeChamada=" + cnlLocalidadeChamada + ", nomeLocalidadeChamada=" + nomeLocalidadeChamada + ", ufTelefoneChamado=" + ufTelefoneChamado + ", codNacionalInternacional=" + codNacionalInternacional + ", codOperadora=" + codOperadora + ", descOperadora=" + descOperadora + ", codPaisChamado=" + codPaisChamado + ", areaDdd=" + areaDdd + ", numTelefoneChamado=" + numTelefoneChamado + ", conjugadoNumeroTelefoneChamado=" + conjugadoNumeroTelefoneChamado + ", duracaoLigacao=" + duracaoLigacao + ", categoria=" + categoria + ", descCategoria=" + descCategoria + ", horLigacao=" + horLigacao + ", tipChamada=" + tipChamada + ", grupoHorTarifario=" + grupoHorTarifario + ", descHorTarifacio=" + descHorTarifario + ", degrauLigacao=" + degrauLigacao + ", sinalValLigacao=" + sinalValLigacao + ", aliquotaICMS=" + aliquotaICMS + ", valLigacaoComImposto=" + valLigacaoComImposto + ", classeServico=" + classeServico + '}';
    }

}
