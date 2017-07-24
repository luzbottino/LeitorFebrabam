/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tst.leitorfebrabam.model;

import java.math.BigDecimal;

/**
 *
 * @author p006184
 */
public class Fatura {

    private String identificadorContaUnica;
    private String numeroFatura;
    private String dataEmissao;
    private String mesRefencia;
    private String dataVencimento;

    private BigDecimal valTotalChamadasResumo;
    private BigDecimal valTotalChamadasCalculadoComImposto;
    private BigDecimal valTotalChamadasCalculadoSemImposto;

    private BigDecimal valTotalServicosResumo;
    private BigDecimal valTotalServicosCalculadoComImposto;
    private BigDecimal valTotalServicosCalculadoSemImposto;

    private BigDecimal valTotalImpostosResumo;

    private BigDecimal valTotalDescontos;

    public String getIdentificadorContaUnica() {
        return identificadorContaUnica;
    }

    public void setIdentificadorContaUnica(String identificadorContaUnica) {
        this.identificadorContaUnica = identificadorContaUnica;
    }

    public String getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(String dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getMesRefencia() {
        return mesRefencia;
    }

    public void setMesRefencia(String mesRefencia) {
        this.mesRefencia = mesRefencia;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public BigDecimal getValTotalChamadasResumo() {
        return valTotalChamadasResumo;
    }

    public void setValTotalChamadasResumo(BigDecimal valTotalChamadasResumo) {
        this.valTotalChamadasResumo = valTotalChamadasResumo;
    }

    public BigDecimal getValTotalChamadasCalculadoComImposto() {
        return valTotalChamadasCalculadoComImposto;
    }

    public void setValTotalChamadasCalculadoComImposto(BigDecimal valTotalChamadasCalculadoComImposto) {
        this.valTotalChamadasCalculadoComImposto = valTotalChamadasCalculadoComImposto;
    }

    public BigDecimal getValTotalChamadasCalculadoSemImposto() {
        return valTotalChamadasCalculadoSemImposto;
    }

    public void setValTotalChamadasCalculadoSemImposto(BigDecimal valTotalChamadasCalculadoSemImposto) {
        this.valTotalChamadasCalculadoSemImposto = valTotalChamadasCalculadoSemImposto;
    }

    public BigDecimal getValTotalServicosResumo() {
        return valTotalServicosResumo;
    }

    public void setValTotalServicosResumo(BigDecimal valTotalServicosResumo) {
        this.valTotalServicosResumo = valTotalServicosResumo;
    }

    public BigDecimal getValTotalServicosCalculadoComImposto() {
        return valTotalServicosCalculadoComImposto;
    }

    public void setValTotalServicosCalculadoComImposto(BigDecimal valTotalServicosCalculadoComImposto) {
        this.valTotalServicosCalculadoComImposto = valTotalServicosCalculadoComImposto;
    }

    public BigDecimal getValTotalServicosCalculadoSemImposto() {
        return valTotalServicosCalculadoSemImposto;
    }

    public void setValTotalServicosCalculadoSemImposto(BigDecimal valTotalServicosCalculadoSemImposto) {
        this.valTotalServicosCalculadoSemImposto = valTotalServicosCalculadoSemImposto;
    }

    public BigDecimal getValTotalImpostosResumo() {
        return valTotalImpostosResumo;
    }

    public void setValTotalImpostosResumo(BigDecimal valTotalImpostosResumo) {
        this.valTotalImpostosResumo = valTotalImpostosResumo;
    }

    public BigDecimal getValTotalDescontos() {
        return valTotalDescontos;
    }

    public void setValTotalDescontos(BigDecimal valTotalDescontos) {
        this.valTotalDescontos = valTotalDescontos;
    }

    public String getNumeroFatura() {
        return numeroFatura;
    }

    public void setNumeroFatura(String numeroFatura) {
        this.numeroFatura = numeroFatura;
    }

}
