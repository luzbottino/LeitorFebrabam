/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tst.leitorfebrabam.worker;

import br.com.tst.leitorfebrabam.file.Febrabam;
import br.com.tst.leitorfebrabam.model.BilhetacaoV2;
import br.com.tst.leitorfebrabam.model.EnderecoABV2;
import br.com.tst.leitorfebrabam.model.HeaderV2;
import br.com.tst.leitorfebrabam.model.ResumoV2;
import br.com.tst.leitorfebrabam.model.V2;
import br.com.tst.leitorfebrabam.util.CursorToolkitTwo;
import br.com.tst.leitorfebrabam.view.FaturaV2;
import br.com.tst.leitorfebrabam.view.FaturaV2;
import br.com.tst.leitorfebrabam.view.ProgressBar;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingWorker;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author p006184
 */
public class GenerateV2View extends SwingWorker<V2, V2> {

    private final Febrabam febrabam;
    private final ProgressBar progressBar;
    private final JFrame jFrame;
    private V2 v2;
    private List<ResumoV2> resumosV2;
    private List<EnderecoABV2> enderecosABV2;
    private List<BilhetacaoV2> bilhetacoesV2;

    public GenerateV2View(Febrabam febrabam, ProgressBar progressBar, JFrame jFrame) {
        this.febrabam = febrabam;
        this.progressBar = progressBar;
        this.jFrame = jFrame;

//        startWaitCursor();
    }

    @Override
    protected V2 doInBackground() throws Exception {
        v2 = new V2();
        resumosV2 = new ArrayList<>();

        int totalOfLines = febrabam.getTotalOfLines();

        for (int i = 0; i < totalOfLines; i++) {
            setProgress(Math.round(((float) i / (float) totalOfLines) * 100f));
            buildV2(febrabam.getLine());
            febrabam.readLine();
//            Thread.sleep(1);
        }
        v2.setResumosV2(resumosV2);
        v2.setEnderecosABV2(enderecosABV2);

        febrabam.closeFebrabam();
        return v2;
    }

    private void buildV2(String line) {
        if (line.charAt(0) == '0') {
            v2.setHeader(buildHeader(line));
        } else if (line.charAt(0) == '1') {
            resumosV2.add(buildResumo(line));
        } else if (line.charAt(0) == '2') {
            enderecosABV2.add(buildEndereco(line));
        } else if (line.charAt(0) == '3') {
            bilhetacoesV2.add(buildBilhete(line));
        }
    }

    private HeaderV2 buildHeader(String line) {
        HeaderV2 headerV2 = new HeaderV2();

        headerV2.setTipoRegistro(line.substring(0, 1));
        headerV2.setControleSeguencialGravacao(Integer.valueOf(line.substring(1, 13)));
        headerV2.setDtaGeracaoArquivo(formatDate(line.substring(13, 21)));
        headerV2.setOperadora(removeWhiteSpacesInTheEnd(line.substring(21, 36)));
        headerV2.setUfOperadora(line.substring(36, 38));
        headerV2.setCodCliente(removeWhiteSpacesInTheEnd(line.substring(38, 53)));
        headerV2.setNomCliente(removeWhiteSpacesInTheEnd(line.substring(53, 93)));
        headerV2.setCgcCliente(removeWhiteSpacesInTheEnd(line.substring(93, 108)));
        headerV2.setIdentContaUnica(removeWhiteSpacesInTheEnd(line.substring(108, 123)));
        headerV2.setMesReferencia(removeWhiteSpacesInTheEnd(line.substring(123, 133)));
        headerV2.setDtaVencimento(formatDate(line.substring(133, 141)));
        headerV2.setDtaEmissao(formatDate(line.substring(141, 149)));

        return headerV2;
    }

    private ResumoV2 buildResumo(String line) {
        ResumoV2 resumoV2 = new ResumoV2();

        resumoV2.setTipoRegistro(line.substring(0, 1));
        resumoV2.setControleSequencialGravacao(Integer.valueOf(line.substring(1, 13)));
        resumoV2.setIdentificadorContaUnica(removeWhiteSpacesInTheEnd(line.substring(13, 38)));
        resumoV2.setDtaVencimento(formatDate(line.substring(38, 46)));
        resumoV2.setDtaEmissao(formatDate(line.substring(46, 54)));
        resumoV2.setIdentificadorUnicoRecurso(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(54, 79))));
        resumoV2.setCnlRecursoReferencia(Integer.valueOf(line.substring(79, 84)));
        resumoV2.setNomeLocalidade(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(84, 109))));
        resumoV2.setDdd(verifyWhiteSpaces(line.substring(109, 111)));
        resumoV2.setNumTelefone(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(111, 121))));
        resumoV2.setTipoServico(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(121, 125))));
        resumoV2.setDescTipoServico(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(125, 160))));
        resumoV2.setCaracteristicaRecurso(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(160, 175))));
        resumoV2.setDegrauRecurso(verifyWhiteSpaces(line.substring(175, 177)));
        resumoV2.setVelocidadeRecurso(verifyWhiteSpaces(line.substring(177, 182)));
        resumoV2.setUnidadeVelocidadeRecurso(verifyWhiteSpaces(line.substring(182, 186)));
        resumoV2.setInicioPeriodoAssinatura(formatDate(line.substring(186, 194)));
        resumoV2.setFimPeriodoAssinatura(formatDate(line.substring(194, 202)));
        resumoV2.setInicioPeriodoServicoMedido(formatDate(line.substring(202, 210)));
        resumoV2.setFimPeriodoServicoMedido(formatDate(line.substring(210, 218)));
        resumoV2.setUnidadeConsumo(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(218, 223))));
        resumoV2.setQtdConsumo(Integer.valueOf(line.substring(223, 230)));
        resumoV2.setSinalValConsumo(verifyWhiteSpaces(line.substring(230, 231)));
        resumoV2.setValConsumo(formatMonetary(line.substring(231, 244)));
        resumoV2.setSinalAssinatura(verifyWhiteSpaces(line.substring(244, 245)));
        resumoV2.setValAssinatura(formatMonetary(line.substring(245, 258)));
        resumoV2.setAliquota(verifyWhiteSpaces(line.substring(258, 260)));
        resumoV2.setSinalICMS(verifyWhiteSpaces(line.substring(260, 261)));
        resumoV2.setValICMS(formatMonetary(line.substring(261, 274)));
        resumoV2.setSinalValTotalOutrosImpostos(verifyWhiteSpaces(line.substring(274, 275)));
        resumoV2.setValTotalImpostos(formatMonetary(line.substring(275, 288)));
        resumoV2.setNumNotaFiscal(verifyWhiteSpaces(line.substring(288, 300)));
        resumoV2.setSinalValConta(verifyWhiteSpaces(line.substring(300, 301)));
        resumoV2.setValConta(formatMonetary(line.substring(301, 314)));

        return resumoV2;
    }
    //PRECISA SER COMPLETADO
    private EnderecoABV2 buildEndereco(String line) {
        EnderecoABV2 enderecoABV2 = new EnderecoABV2();

        enderecoABV2.setTipoRegistro(line.substring(0, 1));
        enderecoABV2.setControleSequencialGravacao(Integer.valueOf(line.substring(1, 13)));
        enderecoABV2.setIdentificadorUnicoRecurso(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(13, 38))));
        enderecoABV2.setDdd(verifyWhiteSpaces(line.substring(38, 40)));
        enderecoABV2.setNumTelefone(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(40, 50))));
        enderecoABV2.setCaracteristicaRecurso(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(50, 65))));
        enderecoABV2.setCnlRecursoEnderecoPontaA(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(65, 70))));
        enderecoABV2.setNomeLocalidadeEnderecoPontaA(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(70, 90))));

        return enderecoABV2;
    }

    private BilhetacaoV2 buildBilhete(String line) {
        BilhetacaoV2 bilhetacaoV2 = new BilhetacaoV2();
        
        bilhetacaoV2.setTipoRegistro(line.substring(0, 1));
        bilhetacaoV2.setControleSequencialGravacao(Integer.valueOf(line.substring(1, 13)));
        bilhetacaoV2.setDtaVencimento(formatDate(line.substring(13, 21)));
        bilhetacaoV2.setDtaEmissao(formatDate(line.substring(21, 29)));
        bilhetacaoV2.setIdentificadorUnicoRecurso(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(29, 54))));
        bilhetacaoV2.setCnlRecursoReferencia(Integer.valueOf(line.substring(54, 59)));
        bilhetacaoV2.setDdd(verifyWhiteSpaces(verifyWhiteSpaces(line.substring(59, 61))));
        bilhetacaoV2.setNumTelefone(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(61, 71))));
        bilhetacaoV2.setCaracteristicaRecurso(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(71, 86))));
        bilhetacaoV2.setDegrauRecurso(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(86, 88))));
        bilhetacaoV2.setDtaLigacao(formatDate(line.substring(88, 96)));
        bilhetacaoV2.setCnlLocalidadeChamada(Integer.valueOf(line.substring(96, 101)));
        bilhetacaoV2.setNomeLocalidadeChamada(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(101, 126))));
        bilhetacaoV2.setUfTelefoneChamado(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(126, 128))));
        bilhetacaoV2.setCodNacionalInternacional(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(128, 130))));
        bilhetacaoV2.setCodOperadora(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(130, 132))));
        bilhetacaoV2.setDescOperadora(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(132, 152))));
        bilhetacaoV2.setCodPaisChamado(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(152, 155))));
        bilhetacaoV2.setAreaDdd(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(155, 159))));
        bilhetacaoV2.setNumTelefoneChamado(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(159, 169))));
        bilhetacaoV2.setConjugadoNumeroTelefoneChamado(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(169, 171))));
//        bilhetacaoV2.setDuracaoLigacao(formatCallDuration((line.substring(159, 169))));
        
        
        
        
        
        System.out.println(bilhetacaoV2.toString());
        return bilhetacaoV2;                
    }

    @Override
    protected void done() {
        try {
            V2 v2 = get();
            new FaturaV2(v2).setVisible(true);

        } catch (InterruptedException ex) {
            Logger.getLogger(GenerateV2View.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (ExecutionException ex) {
            Logger.getLogger(GenerateV2View.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            progressBar.setVisible(false);
        }
    }

    private void startWaitCursor() {
        CursorToolkitTwo.startWaitCursor(jFrame.getRootPane());
    }

    private void stopWaitCursor() {
        CursorToolkitTwo.stopWaitCursor(jFrame.getRootPane());
    }

    //Remove espaços em branco após último carectere
    private String removeWhiteSpacesInTheEnd(String substring) {
        return StringUtils.stripEnd(substring, " ");
    }

    private String formatDate(String substring) {
        return substring.substring(6, 8) + "/" + substring.substring(4, 6) + "/" + substring.substring(0, 4);
    }

    //Formata para o padrão monetário do LayoutFebrabam V2 11 casas com 2 decimais
    private BigDecimal formatMonetary(String value) {
        return new BigDecimal(value.substring(0, 11) + "." + value.substring(11, 13));
    }

    //Veifica se o campo veio em branco
    private String verifyWhiteSpaces(String substring) {
        if (StringUtils.isWhitespace(substring)) {
            return "NÃO INFORMADO";
        }
        return substring;
    }

//    private String formatCallDuration(String string) {
//        
//    }

}
