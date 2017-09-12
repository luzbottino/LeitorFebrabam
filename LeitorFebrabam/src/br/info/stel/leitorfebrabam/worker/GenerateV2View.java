/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.info.stel.leitorfebrabam.worker;

import br.info.stel.leitorfebrabam.file.Febrabam;
import br.info.stel.leitorfebrabam.model.v2.BilhetacaoV2;
import br.info.stel.leitorfebrabam.model.v2.DescontoV2;
import br.info.stel.leitorfebrabam.model.v2.EnderecoABV2;
import br.info.stel.leitorfebrabam.model.v2.HeaderV2;
import br.info.stel.leitorfebrabam.model.v2.ResumoV2;
import br.info.stel.leitorfebrabam.model.v2.ServicoV2;
import br.info.stel.leitorfebrabam.model.v2.TraillerV2;
import br.info.stel.leitorfebrabam.model.V2;
import br.info.stel.leitorfebrabam.util.CursorToolkitTwo;
import br.info.stel.leitorfebrabam.view.v2.FaturaV2;
import br.info.stel.leitorfebrabam.view.ProgressBar;
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
    private List<ServicoV2> servicosV2;
    private List<DescontoV2> descontosV2;

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
        enderecosABV2 = new ArrayList<>();
        bilhetacoesV2 = new ArrayList<>();
        servicosV2 = new ArrayList<>();
        descontosV2 = new ArrayList<>();

        int totalOfLines = febrabam.getTotalOfLines();

        for (int i = 0; i < totalOfLines; i++) {
            setProgress(Math.round(((float) i / (float) totalOfLines) * 100f));
            buildV2(febrabam.getLine());
            febrabam.readLine();
        }
        v2.setResumosV2(resumosV2);
        v2.setEnderecosABV2(enderecosABV2);
        v2.setBilhetacoesV2(bilhetacoesV2);
        v2.setServicosV2(servicosV2);
        v2.setDescontosV2(descontosV2);

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
        } else if (line.charAt(0) == '4') {
            servicosV2.add(buildServico(line));
        } else if (line.charAt(0) == '5') {
            descontosV2.add(buildDesconto(line));
        } else if (line.charAt(0) == '9') {
            v2.setTrailler(buildTrailler(line));
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
        bilhetacaoV2.setDuracaoLigacao(line.substring(171, 177));
        bilhetacaoV2.setCategoria(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(177, 180))));
        bilhetacaoV2.setDescCategoria(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(180, 230))));
        bilhetacaoV2.setHorLigacao(formatHorCall(line.substring(230, 236)));
        bilhetacaoV2.setTipChamada(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(236, 237))));
        bilhetacaoV2.setGrupoHorTarifario(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(237, 238))));
        bilhetacaoV2.setDescHorTarifario(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(238, 263))));
        bilhetacaoV2.setDegrauLigacao(Integer.valueOf(line.substring(263, 265)));
        bilhetacaoV2.setSinalValLigacao(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(265, 266))));
        bilhetacaoV2.setAliquotaICMS(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(266, 271))));
        bilhetacaoV2.setValLigacaoComImposto(formatMonetary(line.substring(271, 284)));
        bilhetacaoV2.setClasseServico(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(284, 292))));

        return bilhetacaoV2;
    }

    private ServicoV2 buildServico(String line) {
        ServicoV2 servicoV2 = new ServicoV2();

        servicoV2.setTipoRegistro(line.substring(0, 1));
        servicoV2.setControleSequencialGravacao(Integer.valueOf(line.substring(1, 13)));
        servicoV2.setDtaVencimento(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(formatDate(line.substring(13, 21)))));
        servicoV2.setDtaEmissao(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(21, 29))));
        servicoV2.setIdentUnicoRecurso(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(29, 54))));
        servicoV2.setCnlRecursoReferencia(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(54, 59))));
        servicoV2.setDdd(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(59, 61))));
        servicoV2.setNumTelefone(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(61, 71))));
        servicoV2.setCaracteristicaRecurso(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(71, 86))));
        servicoV2.setDtaServico(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(formatDate(line.substring(86, 94)))));
        servicoV2.setCnlLocalidadeChamada(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(94, 99))));
        servicoV2.setNomeLocalidadeChamada(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(99, 124))));
        servicoV2.setUfTelefoneChamado(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(124, 126))));
        servicoV2.setCodNacionalInternacional(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(126, 128))));
        servicoV2.setCodOperadora(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(128, 130))));
        servicoV2.setDescOperadora(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(130, 150))));
        servicoV2.setCodPaisChamado(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(150, 153))));
        servicoV2.setAreaDdd(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(153, 157))));
        servicoV2.setNumTelefoneChamado(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(157, 167))));
        servicoV2.setConjugadoTelefoneChamado(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(167, 168))));
        servicoV2.setDuracaoLigacao(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(168, 175))));
        servicoV2.setHorarioLigacao(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(formatHorCall(line.substring(175, 181)))));
        servicoV2.setGrupoCategoria(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(181, 184))));
        servicoV2.setDescGrupoCategoria(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(184, 214))));
        servicoV2.setCategoria(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(214, 217))));
        servicoV2.setDescCategoria(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(217, 257))));
        servicoV2.setSinalValLigacao(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(257, 258))));
        servicoV2.setValLigacao(formatMonetary(line.substring(258, 271)).toString());
        servicoV2.setClasseServico(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(271, 276))));

        return servicoV2;
    }

    private DescontoV2 buildDesconto(String line) {
        DescontoV2 descontoV2 = new DescontoV2();

        descontoV2.setTipoRegistro(line.substring(0, 1));
        descontoV2.setControleSequencialGravacao(Integer.valueOf(line.substring(1, 13)));
        descontoV2.setDtaVencimento(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(formatDate(line.substring(13, 21)))));
        descontoV2.setDtaEmissao(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(formatDate(line.substring(21, 29)))));
        descontoV2.setIdentUnicoRecurso(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(29, 54))));
        descontoV2.setIdentContaUnica(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(54, 79))));
        descontoV2.setCnlRecursoReferencia(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(79, 84))));
        descontoV2.setDdd(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(84, 86))));
        descontoV2.setNumTelefone(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(86, 96))));
        descontoV2.setGrupoCategoria(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(96, 99))));
        descontoV2.setDescGrupoCategoria(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(99, 179))));
        descontoV2.setSinalValLigacao(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(179, 180))));
        descontoV2.setBaseCalcDesconto(formatMonetary(line.substring(180, 193)).toString());
        descontoV2.setPercentualDesconto(formatPercent(line.substring(193, 198)).toString());
        descontoV2.setValLigacao(formatMonetary(line.substring(198, 211)).toString());
        descontoV2.setDtaInicioAcerto(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(formatDate(line.substring(211, 219)))));
        descontoV2.setHorInicioAcerto(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(formatHorCall(line.substring(219, 225)))));
        descontoV2.setDtaFimAcerto(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(formatDate(line.substring(225, 233)))));
        descontoV2.setHorFimAcerto(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(formatHorCall(line.substring(233, 239)))));
        descontoV2.setClasseServico(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(239, 244))));

        return descontoV2;
    }

    private TraillerV2 buildTrailler(String line) {
        TraillerV2 traillerV2 = new TraillerV2();

        traillerV2.setTipoRegistro(line.substring(0, 1));
        traillerV2.setControleSequencialGravacao(Integer.valueOf(line.substring(1, 13)));
        traillerV2.setCodCliente(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(13, 28))));
        traillerV2.setIdentContaUnica(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(28, 53))));
        traillerV2.setDtaVencimento(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(formatDate(line.substring(53, 61)))));
        traillerV2.setDtaEmissao(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(formatDate(line.substring(61, 69)))));
        traillerV2.setQtdRegistros(Integer.valueOf(line.substring(69, 81)));
        traillerV2.setQtdLinhasTelefonicas(Integer.valueOf(line.substring(81, 93)));
        traillerV2.setSinalTotal(verifyWhiteSpaces(removeWhiteSpacesInTheEnd(line.substring(93, 94))));
        traillerV2.setValTotal(formatMonetary(line.substring(94, 107)));
        
        System.out.println(traillerV2.toString());
        
        return traillerV2;
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

    private BigDecimal formatPercent(String value) {
        return new BigDecimal(value.substring(0, 3) + "." + value.substring(3, 5));
    }

    //Veifica se o campo veio em branco
    private String verifyWhiteSpaces(String substring) {
        if (StringUtils.isWhitespace(substring)) {
            return "NÃO INFORMADO";
        }
        return substring;
    }

    /**
     * *
     * O parâmetro segue o padrão 6,1 sendo 5 posições com inteiro representando
     * os minutos e uma casa decimal representando o décimo de minuto. O formato
     * da duração é 00:00:00
     *
     * @param string
     * @return
     */
    private String formatCallDuration(String string) {
        //Calcula os segundos da ligação
        String secondsFormat = getSeconds(string);
        //Resgata os minutos do parâmetro
        int minutes = Integer.valueOf(string.substring(0, 5));
        if (minutes >= 60) {
            String hoursFormat = getHours(minutes);
            String minutesFormat = getMinutes(minutes);

            return hoursFormat + ":" + minutesFormat + ":" + secondsFormat;
        }

        return "00:" + string.substring(2, 4) + ":" + secondsFormat;
    }

    private String getSeconds(String string) {
        BigDecimal seconds;
        BigDecimal sixty = new BigDecimal("60");
        BigDecimal decimal = new BigDecimal("10");
        //Verifica se os segundos está com valor zero
        if (string.substring(5).equals("0")) {
            return "00";
        }
        //Resgata a última posição do paramêtro string que representa o décimo de minuto
        BigDecimal decimalSeconds = new BigDecimal(string.substring(5));
        //Multiplica o décimo de minuto por 60
        seconds = decimalSeconds.multiply(sixty);
        //Divide o resultado por 10
        seconds = seconds.divide(decimal);

        //Retorna em texto os segundos da ligação
        return seconds.toString();
    }

    private String getHours(int minutes) {
        BigDecimal sixty = new BigDecimal(60);
        //Divide os minutos por 60 para resgatar as horas
        BigDecimal hours = new BigDecimal(minutes).divide(sixty, 6, BigDecimal.ROUND_CEILING);

        //Caso o valor tenha casa decimal, a condição abaixo retorna apenas o inteiro
        if (hours.toString().contains(".")) {
            int hoursFormat = Integer.valueOf(hours.toString().substring(0, StringUtils.indexOf(hours.toString(), ".")));
            if (hoursFormat >= 10) {
                return String.valueOf(hoursFormat);
            } else {
                return "0" + hoursFormat;
            }
        }
        int hoursFormat = Integer.valueOf(hours.toString());

        if (hoursFormat >= 10) {
            return String.valueOf(hoursFormat);
        } else {
            return "0" + String.valueOf(hoursFormat);
        }

    }

    private String getMinutes(int minutes) {
        BigDecimal sixty = new BigDecimal("60");
        //Divide os minutos por 60 para resgatar as horas
        BigDecimal hours = new BigDecimal(minutes).divide(sixty, 6, BigDecimal.ROUND_CEILING);

        //Caso o valor tenha casa decimal, calcula os minutos
        if (hours.toString().contains(".")) {
            //Resgata apenas as casas decimais da hora com ponto
            String decimalPart = hours.toString().substring(hours.toString().indexOf("."), hours.toString().length());

            BigDecimal minutesFormat = new BigDecimal("0" + decimalPart);
            minutesFormat = minutesFormat.multiply(sixty);

            //Retorna os minutos sem as casas decimais
            minutes = Integer.valueOf(minutesFormat.toString().substring(0, StringUtils.indexOf(minutesFormat.toString(), ".")));

            if (minutes >= 10) {
                return String.valueOf(minutes);
            } else {
                return "0" + String.valueOf(minutes);
            }
        }

        return "00";
    }

    /**
     * *
     * Retorna o parâmetro no formato 00:00:00
     *
     * @param substring
     * @return
     */
    private String formatHorCall(String substring) {
        return substring.substring(0, 2) + ":" + substring.substring(2, 4) + ":" + substring.substring(4, 6);
    }

}
