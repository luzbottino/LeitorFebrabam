/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tst.leitorfebrabam.old;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;

/**
 *
 * @author p006184
 */
public class FebrabamService {

    private FileWriter header;
    private FileWriter resume;
    private FileWriter adress;
    private FileWriter calls;
    private FileWriter services;
    private FileWriter desconts;
    private FileWriter planes;
    private FileWriter ajust;
    private FileWriter nf;
    private FileWriter information;
    private FileWriter trailler;

    public String readFile(FileReader febrabamFile) {

        System.out.println("\n\n");
        System.out.print("MENSAGEM: Gerando folha de ");
        System.out.println("CABEÇALHO");
        System.out.print("MENSAGEM: Gerando folha de ");
        System.out.println("RESUMO");
        System.out.print("MENSAGEM: Gerando folha de ");
        System.out.println("ENDEREÇO");
        System.out.print("MENSAGEM: Gerando folha de ");
        System.out.print("DETALHAMENTO - CHAMADAS");
        System.out.print("MENSAGEM: Gerando folha de ");
        System.out.println("DETALHAMENTO - SERVIÇOS");
        System.out.print("MENSAGEM: Gerando folha de ");
        System.out.println("DESCONTOS");
        System.out.print("MENSAGEM: Gerando folha de ");
        System.out.println("RODAPÉ");

        try {
            
            BufferedReader readFile = new BufferedReader(febrabamFile);
            String line = readFile.readLine();

            if (isV2(line)) {                
                return generateV2(readFile, line);
            } else if (isV3(line)) {                
                return generateV3(readFile, line);
            } else {
                System.out.println("MESANGEM: Arquivo inválido!");
                return null;
            }            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FebrabamService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FebrabamService.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                febrabamFile.close();
            } catch (IOException ex) {
                Logger.getLogger(FebrabamService.class.getName()).log(Level.SEVERE, null, ex);               
            }            
        }      
        
        return null;
    }

    private boolean isV2(String line) {
        if (line.length() == 350 && line.substring(0, 13).equals("0000000000001")) {

            return true;
        }
        return false;
    }

    private boolean isV3(String line) {
        if (line.length() == 350 && line.substring(164, 168).equals("V3R0")) {
            return true;
        }
        return false;
    }

    private String generateV2(BufferedReader readFile, String line) {

        String processDate = getCurrentDate();
        String operator = line.substring(21, 36).replaceAll(" ", "");
        String dtaFileGeneration = line.substring(13, 21);
        String directoryName = ("C:" + File.separatorChar + "V2_" + processDate + "_" + operator + File.separatorChar + dtaFileGeneration);
//        String directoryName = (File.separatorChar + "home" + File.separatorChar + "luis" + File.separatorChar + "V2_" + processDate + "_" + operator + File.separatorChar + dtaFileGeneration);
        if (!new File(directoryName).exists()) {
            new File(directoryName).mkdirs();
        }

        try {
            header = new FileWriter(directoryName + File.separatorChar + "header.txt");
            PrintWriter write = new PrintWriter(header);
            formatHeader(write, "HEADER");

            resume = new FileWriter(directoryName + File.separatorChar + "resumo.txt");
            write = new PrintWriter(resume);
            formatHeader(write, "RESUMO");
            BigDecimal totalValueResume = new BigDecimal("0.0");
            BigDecimal totalValueICMS = new BigDecimal("0.0");
            BigDecimal totalValueImpostos = new BigDecimal("0.0");

            adress = new FileWriter(directoryName + File.separatorChar + "endereco.txt");
            write = new PrintWriter(adress);
            formatHeader(write, "ENDEREÇO");

            calls = new FileWriter(directoryName + File.separatorChar + "chamadas.txt");
            write = new PrintWriter(calls);
            formatHeader(write, "DETALHAMENTO - CHAMADAS");
            BigDecimal totalValueCalls = new BigDecimal("0.0");

            services = new FileWriter(directoryName + File.separatorChar + "servicos.txt");
            write = new PrintWriter(services);
            formatHeader(write, "DETALHAMENTO - SERVICOS");
            BigDecimal totalValueServices = new BigDecimal("0.0");

            desconts = new FileWriter(directoryName + File.separatorChar + "descontos.txt");
            write = new PrintWriter(desconts);
            formatHeader(write, "DESCONTOS");
            BigDecimal totalValueDescontos = new BigDecimal("0.0");

            trailler = new FileWriter(directoryName + File.separatorChar + "trailler.txt");
            write = new PrintWriter(trailler);
            formatHeader(write, "TRAILLER");

            while (line != null) {

                if (line.charAt(0) == '0') {
                    write = new PrintWriter(header);
                    write.print("Tipo de registro: ");
                    write.println(line.substring(0, 1));
                    write.print("Controle sequencial de gravação: ");
                    write.println(line.substring(1, 13));
                    write.print("Data de geração do arquivo: ");
                    write.println(formatDate(line.substring(13, 21)));
                    write.print("Identificação da empresa operadora: ");
                    write.println(line.substring(21, 36));
                    write.print("UF da operadora: ");
                    write.println(line.substring(36, 38));
                    write.print("Código do cliente: ");
                    write.println(line.substring(38, 53));
                    write.print("Nome do cliente: ");
                    write.println(line.substring(53, 93));
                    write.print("CGC do cliente: ");
                    write.println(line.substring(93, 108));
                    write.print("Identificador de conta única: ");
                    write.println(line.substring(108, 123));
                    write.print("Mês de referência: ");
                    write.println(formatMesReferencia(line.substring(123, 133)));
                    write.print("Data de vencimento: ");
                    write.println(formatDate(line.substring(133, 141)));
                    write.print("Data de Emissão: ");
                    write.println(formatDate(line.substring(141, 149)));

                }
                if (line.charAt(0) == '1') {
                    totalValueResume = totalValueResume.add(formatValueComImposto(line.substring(301, 314)));
                    totalValueICMS = totalValueICMS.add(formatValueComImposto(line.substring(261, 274)));
                    totalValueImpostos = totalValueImpostos.add(formatValueComImposto(line.substring(275, 288)));

                    write = new PrintWriter(resume);
                    write.println(separator());
                    write.print("NÚMERO DO RECURSO: ");
                    write.println(line.substring(111, 121));
                    write.println("");
                    write.print("Tipo de registro: ");
                    write.println(line.substring(0, 1));
                    write.print("Controle sequencial de gravação: ");
                    write.println(line.substring(1, 13));
                    write.print("Identificador de conta única: ");
                    write.println(line.substring(13, 38));
                    write.print("Data de vencimento: ");
                    write.println(formatDate(line.substring(38, 46)));
                    write.print("Data de emissão: ");
                    write.println(formatDate(line.substring(46, 54)));
                    write.print("Identificador único do recurso (NRC): ");
                    write.println(line.substring(54, 79));
                    write.print("CNL do recurso em referência: ");
                    write.println(line.substring(79, 84));
                    write.print("Nome da localidade: ");
                    write.println(line.substring(84, 109));
                    write.print("DDD: ");
                    write.println(line.substring(109, 111));
                    write.print("Nº do telefone: ");
                    write.println(line.substring(111, 121));
                    write.print("Tipo de serviço: ");
                    write.println(line.substring(121, 125));
                    write.print("Descrição do tipo de serviço: ");
                    write.println(line.substring(125, 160));
                    write.print("Característica do recurso: ");
                    write.println(line.substring(160, 175));
                    write.print("Degrau do recurso: ");
                    write.println(line.substring(175, 177));
                    write.print("Velocidade do recurso: ");
                    write.println(line.substring(177, 182));
                    write.print("Unidade da velocidade do recurso: ");
                    write.println(line.substring(182, 186));
                    write.print("Início da assinatura: ");
                    write.println(formatDate(line.substring(186, 194)));
                    write.print("Fim da assinatura: ");
                    write.println(formatDate(line.substring(194, 202)));
                    write.print("Início do serviço medido: ");
                    write.println(formatDate(line.substring(202, 210)));
                    write.print("Fim do serviço medido: ");
                    write.println(formatDate(line.substring(210, 218)));
                    write.print("Unidade de consumo: ");
                    write.println(line.substring(218, 223));
                    write.print("Quantidade de consumo: ");
                    write.println(line.substring(223, 230));
                    write.print("Sinal valor consumo: ");
                    write.println(line.substring(230, 231));
                    write.print("Valor consumo: ");
                    write.println(formatValueComImposto(line.substring(231, 244)));
                    write.print("Sinal assinatura: ");
                    write.println(line.substring(244, 245));
                    write.print("Valor assinatura: ");
                    write.println(formatValueComImposto(line.substring(245, 258)));
                    write.print("Alíquota: ");
                    write.println(line.substring(258, 260));
                    write.print("Sinal ICMS: ");
                    write.println(line.substring(260, 261));
                    write.print("Valor ICMS: ");
                    write.println(formatValueComImposto(line.substring(261, 274)));
                    write.print("Sinal valor total de impostos: ");
                    write.println(line.substring(274, 275));
                    write.print("Valor total de impostos: ");
                    write.println(formatValueComImposto(line.substring(275, 288)));
                    write.print("Nº da nota fiscal: ");
                    write.println(line.substring(288, 300));
                    write.print("Sinal valor da conta: ");
                    write.println(line.substring(300, 301));
                    write.print("Valor da conta: ");
                    write.println(formatValueComImposto(line.substring(301, 314)));

                }
                if (line.charAt(0) == '2') {

                    write = new PrintWriter(adress);

                    write.println(separator());
                    write.print("NÚMERO DO RECURSO: ");
                    write.println(line.substring(40, 50));
                    write.println();
                    write.print("Tipo de registro: ");
                    write.println(line.substring(0, 1));
                    write.print("Controle sequencial de gravação: ");
                    write.println(line.substring(1, 13));
                    write.print("Identificador único do recurso: ");
                    write.println(line.substring(13, 38));
                    write.print("DDD: ");
                    write.println(line.substring(38, 40));
                    write.print("Número do telefone: ");
                    write.println(line.substring(40, 50));
                    write.print("Característica do recurso: ");
                    write.println(line.substring(50, 65));
                    write.print("CNL do recurso ENDEREÇO PONTA A: ");
                    write.println(line.substring(65, 70));
                    write.print("Nome da localidade ENDEREÇO PONTA A: ");
                    write.println(line.substring(70, 90));
                    write.print("UF da localidade PONTA A: ");
                    write.println(line.substring(90, 92));
                    write.print("Endereço da PONTA A: ");
                    write.println(line.substring(92, 122));
                    write.print("Nº do endereco da PONTA A: ");
                    write.println(line.substring(122, 127));
                    write.print("Complemento da PONTA A: ");
                    write.println(line.substring(127, 137));
                    write.print("Bairro da PONTA A: ");
                    write.println(line.substring(137, 157));
                    write.print("CNL do recurso ENDEREÇO PONTA B: ");
                    write.println(line.substring(157, 162));
                    write.print("Nome da localidade ENDEREÇO PONTA B: ");
                    write.println(line.substring(162, 182));
                    write.print("UF da localidade PONTA B: ");
                    write.println(line.substring(182, 184));
                    write.print("Endereço da PONTA B: ");
                    write.println(line.substring(184, 214));
                    write.print("Nº do endereco da PONTA B: ");
                    write.println(line.substring(214, 219));
                    write.print("Complemento da PONTA B: ");
                    write.println(line.substring(219, 229));
                    write.print("Bairro da PONTA B: ");
                    write.println(line.substring(229, 249));

                }
                if (line.charAt(0) == '3') {
                    totalValueCalls = totalValueCalls.add(formatValueComImposto(line.substring(271, 284)));
                    write = new PrintWriter(calls);
                    write.println(separator());
                    write.print("NÚMERO DO RECURSO: ");
                    write.println(line.substring(61, 71));
                    write.println();
                    write.print("Tipo de registro: ");
                    write.println(line.substring(0, 1));
                    write.print("Controle sequencial de gravação: ");
                    write.println(line.substring(1, 13));
                    write.print("Data de vencimento: ");
                    write.println(formatDate(line.substring(13, 21)));
                    write.print("Data de emissao: ");
                    write.println(formatDate(line.substring(21, 29)));
                    write.print("Identificador unico do recurso (NRC): ");
                    write.println(line.substring(29, 54));
                    write.print("CNL do recurso em referencia: ");
                    write.println(line.substring(54, 59));
                    write.print("DDD: ");
                    write.println(line.substring(59, 61));
                    write.print("Número do telefone: ");
                    write.println(line.substring(61, 71));
                    write.print("Caracteristica do recurso: ");
                    write.println(line.substring(71, 86));
                    write.print("Degrau do recurso: ");
                    write.println(line.substring(86, 88));
                    write.print("Data da ligacao: ");
                    write.println(formatDate(line.substring(88, 96)));
                    write.print("CNL da localidade chamada: ");
                    write.println(line.substring(96, 101));
                    write.print("Nome da localidade chamada: ");
                    write.println(line.substring(101, 126));
                    write.print("UF do telefone chamado: ");
                    write.println(line.substring(126, 128));
                    write.print("Codigo nacional/internacional: ");
                    write.println(line.substring(128, 130));
                    write.print("Codigo da operadora: ");
                    write.println(line.substring(130, 132));
                    write.print("Descricao da operadora: ");
                    write.println(line.substring(132, 152));
                    write.print("Codigo do pais chamado: ");
                    write.println(line.substring(152, 155));
                    write.print("Area/DDD: ");
                    write.println(line.substring(155, 159));
                    write.print("NUMERO DO TELEFONE CHAMADO: ");
                    write.println(line.substring(159, 169));
                    write.print("Conjugado do numero telefone chamado: ");
                    write.println(line.substring(169, 171));
                    write.print("Duracao da ligacao: ");
                    write.println(formatCallDuration(line.substring(171, 177)));
                    write.print("Categoria: ");
                    write.println(line.substring(177, 180));
                    write.print("Descricao da categoria: ");
                    write.println(line.substring(180, 230));
                    write.print("Horario da ligacao: ");
                    write.println(formatHour(line.substring(230, 236)));
                    write.print("Tipo de chamada: ");
                    write.println(line.substring(236, 237));
                    write.print("Grupo horario tarifario: ");
                    write.println(line.substring(237, 238));
                    write.print("Descricao do horario tarifario: ");
                    write.println(line.substring(238, 263));
                    write.print("Degrau da ligacao: ");
                    write.println(line.substring(263, 265));
                    write.print("Sinal valor da ligacao: ");
                    write.println(line.substring(265, 266));
                    write.print("Aliquota ICMS: ");
                    write.println(formatPercentValue(line.substring(266, 271)));
                    write.print("Valor da ligacao com imposto: ");
                    write.println(formatValueComImposto(line.substring(271, 284)));

                }
                if (line.charAt(0) == '4') {
                    totalValueServices = totalValueServices.add(formatValueComImposto(line.substring(258, 271)));
                    write = new PrintWriter(services);
                    write.println(separator());
                    write.print("NÚMERO DO RECURSO: ");
                    write.println(line.substring(61, 71));
                    write.println();
                    write.print("Tipo de registro: ");
                    write.println(line.substring(0, 1));
                    write.print("Controle sequencial de gravação: ");
                    write.println(line.substring(1, 13));
                    write.print("Data de vencimento: ");
                    write.println(formatDate(line.substring(13, 21)));
                    write.print("Data de emissao: ");
                    write.println(formatDate(line.substring(21, 29)));
                    write.print("Identificador unico do recurso (NRC): ");
                    write.println(line.substring(29, 54));
                    write.print("CNL do recurso em referencia: ");
                    write.println(line.substring(54, 59));
                    write.print("DDD: ");
                    write.println(line.substring(59, 61));
                    write.print("Número do telefone: ");
                    write.println(line.substring(61, 71));
                    write.print("Caracteristica do recurso: ");
                    write.println(line.substring(71, 86));
                    write.print("Data do servico: ");
                    write.println(formatDate(line.substring(86, 94)));
                    write.print("CNL da localidade chamada: ");
                    write.println(line.substring(94, 99));
                    write.print("Nome da localidade chamada: ");
                    write.println(line.substring(99, 124));
                    write.print("UF do telefone chamado: ");
                    write.println(line.substring(124, 126));
                    write.print("Código nacional/internacional: ");
                    write.println(line.substring(126, 128));
                    write.print("Codigo da operadora: ");
                    write.println(line.substring(128, 130));
                    write.print("Descricao da operadora: ");
                    write.println(line.substring(130, 150));
                    write.print("Codigo do pais chamado: ");
                    write.println(line.substring(150, 153));
                    write.print("Area/DDD: ");
                    write.println(line.substring(153, 157));
                    write.print("Numero do telefone chamado: ");
                    write.println(line.substring(157, 167));
                    write.print("Conjugado do telefone chamado: ");
                    write.println(line.substring(167, 169));
                    write.print("Duracao da ligacao: ");
                    write.println(line.substring(169, 175));
                    write.print("Horario da ligacao: ");
                    write.println(formatHour(line.substring(175, 181)));
                    write.print("Grupo da categoria: ");
                    write.println(line.substring(181, 184));
                    write.print("Descricao do grupo da categoria: ");
                    write.println(line.substring(184, 214));
                    write.print("Categoria: ");
                    write.println(line.substring(214, 217));
                    write.print("Descricao da categoria: ");
                    write.println(line.substring(217, 257));
                    write.print("Sinal do valor da ligacao: ");
                    write.println(line.substring(257, 258));
                    write.print("Valor da ligacao: ");
                    write.println(formatValueComImposto(line.substring(258, 271)));
                    write.print("Classe de servico: ");
                    write.println(line.substring(271, 276));
                }
                if (line.charAt(0) == '5') {
                    write = new PrintWriter(desconts);
                    totalValueDescontos = totalValueDescontos.add(formatValueComImposto(line.substring(198, 211)));
                    write.println(separator());
                    write.print("NÚMERO DO RECURSO: ");
                    write.println(line.substring(86, 96));
                    write.println();
                    write.print("DESCRICAO DO DESCONTO: ");
                    write.println(line.substring(99, 179));
                    write.println();
                    write.print("Tipo de registro: ");
                    write.println(line.substring(0, 1));
                    write.print("Controle sequencial de gravação: ");
                    write.println(line.substring(1, 13));
                    write.print("Data de vencimento: ");
                    write.println(formatDate(line.substring(13, 21)));
                    write.print("Data de emissao: ");
                    write.println(formatDate(line.substring(21, 29)));
                    write.print("Identificador unico do recurso (NRC): ");
                    write.println(line.substring(29, 54));
                    write.print("Identificador conta unica: ");
                    write.println(line.substring(54, 79));
                    write.print("CNL do recurso em referencia: ");
                    write.println(line.substring(79, 84));
                    write.print("DDD: ");
                    write.println(line.substring(84, 86));
                    write.print("Numero do telefone: ");
                    write.println(line.substring(86, 96));
                    write.print("Grupo da categoria: ");
                    write.println(line.substring(96, 99));
                    write.print("Descricao do grupo da categoria: ");
                    write.println(line.substring(99, 179));
                    write.print("Sinal valor da ligacao: ");
                    write.println(line.substring(179, 180));
                    write.print("Base de calculo desconto: ");
                    write.println(line.substring(180, 193));
                    write.print("Percentual de desconto: ");
                    write.println(formatPercentValue(line.substring(193, 198)));
                    write.print("Valor da ligacao: ");
                    write.println(formatValueComImposto(line.substring(198, 211)));
                    write.print("Data inicio do acerto: ");
                    write.println(formatDate(line.substring(211, 219)));
                    write.print("Hora inicio do acerto: ");
                    write.println(formatHour(line.substring(219, 225)));
                    write.print("Data fim do acerto: ");
                    write.println(formatDate(line.substring(225, 233)));
                    write.print("Hora fim do acerto: ");
                    write.println(formatHour(line.substring(233, 239)));
                    write.print("Classe de servico: ");
                    write.println(line.substring(239, 244));
                }
                if (line.charAt(0) == '9') {
                    write = new PrintWriter(trailler);
                    write.print("VALOR TOTAL: ");
                    write.println(formatValueComImposto(line.substring(94, 107)));
                    write.println();
                    write.print("Tipo de registro: ");
                    write.println(line.substring(0, 1));
                    write.print("Controle sequencial de gravação: ");
                    write.println(line.substring(1, 13));
                    write.print("Código do cliente: ");
                    write.println(line.substring(13, 28));
                    write.print("Identificacao conta unica: ");
                    write.println(line.substring(28, 53));
                    write.print("Data de vencimento: ");
                    write.println(line.substring(53, 61));
                    write.print("Data de emissão: ");
                    write.println(line.substring(61, 69));
                    write.print("Quantidade de registros: ");
                    write.println(line.substring(69, 81));
                    write.print("Quantidade de linhas: ");
                    write.println(line.substring(81, 93));
                    write.print("Sinal total: ");
                    write.println(line.substring(93, 94));

                }

                line = readFile.readLine();
            }

            write = new PrintWriter(resume);
            write.println(separator());
            write.println(separator());
            write.print("VALOR TOTAL: ");
            write.println(totalValueResume);
            write.print("VALOR TOTAL ICMS: ");
            write.println(totalValueICMS);
            write.print("VALOR TOTAL IMPOSTOS: ");
            write.print(totalValueImpostos);

            write = new PrintWriter(calls);
            write.println(separator());
            write.println(separator());
            write.print("VALOR TOTAL: ");
            write.println(totalValueCalls);

            write = new PrintWriter(services);
            write.println(separator());
            write.println(separator());
            write.print("VALOR TOTAL: ");
            write.println(totalValueServices);

            write = new PrintWriter(desconts);
            write.println(separator());
            write.println(separator());
            write.print("VALOR TOTAL: ");
            write.println(totalValueDescontos);

            header.close();
            resume.close();
            adress.close();
            calls.close();
            services.close();
            desconts.close();
            trailler.close();
        } catch (IOException ex) {
            Logger.getLogger(FebrabamService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return directoryName;
    }

    private String generateV3(BufferedReader readFile, String line) {

        
        int progress = 0;
        String processDate = getCurrentDate();
        String operator = line.substring(72, 87).replaceAll(" ", "");
        String dtaFileGeneration = line.substring(53, 61);
        String directoryName = ("C:" + File.separatorChar + "V3_" + processDate + "_" + operator + File.separatorChar + dtaFileGeneration);
//        String directoryName = (File.separatorChar + "home" + File.separatorChar + "luis" + File.separatorChar + "V2_" + processDate + "_" + operator + File.separatorChar + dtaFileGeneration);        
        
        if (!new File(directoryName).exists()) {
            new File(directoryName).mkdirs();
        }
        
        try {
            header = new FileWriter(directoryName + File.separatorChar + "header.txt");
            PrintWriter write = new PrintWriter(header);
            formatHeader(write, "HEADER");

            resume = new FileWriter(directoryName + File.separatorChar + "resumo.txt");
            write = new PrintWriter(resume);
            formatHeader(write, "RESUMO");
            BigDecimal totalValueResume = new BigDecimal("0.0");
            BigDecimal totalValueResumeCalls = new BigDecimal("0.0");
            BigDecimal totalValueResumeServices = new BigDecimal("0.0");
            BigDecimal totalValueImpostos = new BigDecimal("0.0");

            adress = new FileWriter(directoryName + File.separatorChar + "endereco.txt");
            write = new PrintWriter(adress);
            formatHeader(write, "ENDEREÇO");

            calls = new FileWriter(directoryName + File.separatorChar + "chamadas.txt");
            write = new PrintWriter(calls);
            formatHeader(write, "DETALHAMENTO - CHAMADAS");
            BigDecimal totalValueCalls = new BigDecimal("0.0");
            BigDecimal totalValueCallsComImposto = new BigDecimal("0.0");

            services = new FileWriter(directoryName + File.separatorChar + "servicos.txt");
            write = new PrintWriter(services);
            formatHeader(write, "DETALHAMENTO - SERVICOS");
            BigDecimal totalValueServices = new BigDecimal("0.0");
            BigDecimal totalValueServicesComImposto = new BigDecimal("0.0");

            desconts = new FileWriter(directoryName + File.separatorChar + "descontos.txt");
            write = new PrintWriter(desconts);
            formatHeader(write, "DESCONTOS");
            BigDecimal totalValueDescontos = new BigDecimal("0.0");

            planes = new FileWriter(directoryName + File.separatorChar + "planos.txt");
            write = new PrintWriter(planes);
            formatHeader(write, "PLANOS");

            ajust = new FileWriter(directoryName + File.separatorChar + "planos.txt");
            write = new PrintWriter(ajust);
            formatHeader(write, "AJUSTES");

            nf = new FileWriter(directoryName + File.separatorChar + "nota-fiscal.txt");
            write = new PrintWriter(nf);
            formatHeader(write, "NOTA FISCAL");

            information = new FileWriter(directoryName + File.separatorChar + "informativos.txt");
            write = new PrintWriter(information);
            formatHeader(write, "INFORMATIVOS");

            trailler = new FileWriter(directoryName + File.separatorChar + "trailler.txt");
            write = new PrintWriter(trailler);
            formatHeader(write, "TRAILLER");

            while (line != null) {

                if (line.charAt(0) == '0') {
                    write = new PrintWriter(header);
                    write.print("Tipo de Registro: ");
                    write.println(line.substring(0, 2));
                    write.print("Controle Seqüencial de gravação: ");
                    write.println(line.substring(2, 14));
                    write.print("Identificador Conta Única: ");
                    write.println(line.substring(14, 39));
                    write.print("Data de Emissão da Fatura: ");
                    write.println(formatDate(line.substring(39, 47)));
                    write.print("Mês Referência: ");
                    write.println(line.substring(47, 53));
                    write.print("Data Geração do Arquivo: ");
                    write.println(formatDate(line.substring(53, 61)));
                    write.print("Data de Vencimento da fatura: ");
                    write.println(formatDate(line.substring(61, 69)));
                    write.print("Código da Operadora: ");
                    write.println(line.substring(69, 72));
                    write.print("Nome Operadora: ");
                    write.println(line.substring(72, 87));
                    write.print("CNPJ Operadora: ");
                    write.println(line.substring(87, 102));
                    write.print("UF da Operadora: ");
                    write.println(line.substring(102, 104));
                    write.print("Código do Cliente: ");
                    write.println(line.substring(104, 119));
                    write.print("Nome do Cliente: ");
                    write.println(line.substring(119, 149));
                    write.print("CNPJ do Cliente: ");
                    write.println(line.substring(149, 164));
                    write.print("Versão do formato: ");
                    write.println(line.substring(164, 168));
                    write.print("Número da Fatura:");
                    write.println(line.substring(168, 184));
                    write.print("Codigo de Barras:");
                    write.println(line.substring(184, 234));
                    write.print("Codigo de Cobrança:");
                    write.println(line.substring(234, 236));
                    write.print("Descrição Cobrança:");
                    write.println(line.substring(236, 256));
                    write.print("Banco Cobrança:");
                    write.println(line.substring(256, 260));
                    write.print("Agência Cobrança:");
                    write.println(line.substring(260, 264));
                    write.print("Conta Corrente Cobrança:");
                    write.println(line.substring(264, 274));
                    write.print("Reservado para Fisco:");
                    write.println(line.substring(274, 309));
                }
                if (line.charAt(0) == '1') {
                    totalValueResume = totalValueResume.add(formatValueComImposto(line.substring(178, 191)));
                    totalValueResumeCalls = totalValueResumeCalls.add(formatValueComImposto(line.substring(128, 141)));
                    totalValueResumeServices = totalValueResumeServices.add(formatValueSemImposto(line.substring(150, 165)));
                    totalValueImpostos = totalValueImpostos.add(formatValueComImposto(line.substring(165, 178)));

                    write = new PrintWriter(resume);
                    write.println(separator());
                    write.print("IDENTIFICADOR ÚNICO DO RECURSO:");
                    write.println(line.substring(53, 78));
                    write.println();
                    write.print("Tipo de Registro:");
                    write.println(line.substring(0, 2));
                    write.print("Controle Seqüencial de gravação:");
                    write.println(line.substring(2, 14));
                    write.print("Identificador Conta Única:");
                    write.println(line.substring(14, 39));
                    write.print("Data de Emissão da Fatura:");
                    write.println(line.substring(39, 47));
                    write.print("Mês Referência:");
                    write.println(line.substring(47, 53));
                    write.print("Identificador Único do Recurso:");
                    write.println(line.substring(53, 78));
                    write.print("CNL  do Recurso:");
                    write.println(line.substring(78, 83));
                    write.print("Numero do recurso:");
                    write.println(line.substring(83, 99));
                    write.print("Modalidade de serviço do recurso:");
                    write.println(line.substring(99, 103));
                    write.print("Data da Ativação do Recurso:");
                    write.println(line.substring(103, 111));
                    write.print("Data da Desativação do Recurso:");
                    write.println(line.substring(111, 119));
                    write.print("Quantidade de Registros de Chamada:");
                    write.println(line.substring(119, 128));
                    write.print("Valor Total dos Registros de Chamada com Impostos:");
                    write.println(formatValueComImposto(line.substring(128, 141)));
                    write.print("Quantidade de Registros de Serviço:");
                    write.println(line.substring(141, 150));
                    write.print("Valor Total dos Registros de Serviço com Impostos:");
                    write.println(formatValueSemImposto(line.substring(150, 165)));
                    write.print("Valor Total de Impostos:");
                    write.println(formatValueComImposto(line.substring(165, 178)));
                    write.print("Valor Total da Conta do Recurso com Impostos:");
                    write.println(formatValueComImposto(line.substring(178, 191)));
                    write.print("Degrau do Recurso:");
                    write.println(line.substring(191, 193));
                    write.print("Velocidade do Recurso:");
                    write.println(line.substring(193, 198));
                    write.print("Unidade da Velocidade do Recurso:");
                    write.println(line.substring(198, 202));
                    write.print("Data de Vencimento:");
                    write.println(line.substring(202, 210));

                }
                if (line.charAt(0) == '2') {
                    write = new PrintWriter(adress);
                    write.println(separator());
                    write.print("Tipo de Registro: ");
                    write.println(line.substring(0, 2));
                    write.print("Controle Seqüencial de gravação: ");
                    write.println(line.substring(2, 14));
                    write.print("Identificador Conta Única: ");
                    write.println(line.substring(14, 39));
                    write.print("Data de Emissão: ");
                    write.println(line.substring(39, 47));
                    write.print("Mês Referência: ");
                    write.println(line.substring(47, 53));
                    write.print("Identificador Único do Recurso: ");
                    write.println(line.substring(53, 78));
                    write.print("Numero do recurso: ");
                    write.println(line.substring(78, 94));
                    write.print("CNL do recurso Endereço Ponta A: ");
                    write.println(line.substring(94, 99));
                    write.print("Nome da Localidade do Endereço Ponta A: ");
                    write.println(line.substring(99, 114));
                    write.print("UF da Localidade Ponta A: ");
                    write.println(line.substring(114, 116));
                    write.print("Endereço da Ponta A: ");
                    write.println(line.substring(116, 146));
                    write.print("Nº do endereço da Ponta A: ");
                    write.println(line.substring(146, 151));
                    write.print("Complemento da Ponta A: ");
                    write.println(line.substring(151, 159));
                    write.print("Bairro da Ponta A: ");
                    write.println(line.substring(159, 169));
                    write.print("CNL do recurso Endereço Ponta B: ");
                    write.println(line.substring(169, 174));
                    write.print("Nome da Localidade Endereço Ponta B: ");
                    write.println(line.substring(174, 189));
                    write.print("UF da Localidade Ponta B: ");
                    write.println(line.substring(189, 191));
                    write.print("Endereço da Ponta B: ");
                    write.println(line.substring(191, 221));
                    write.print("Nº do endereço da Ponta B: ");
                    write.println(line.substring(221, 226));
                    write.print("Complemento da Ponta B: ");
                    write.println(line.substring(226, 234));
                    write.print("Bairro da Ponta B: ");
                    write.println(line.substring(234, 244));
                    write.print("CNL do recurso Endereço Ponta C: ");
                    write.println(line.substring(244, 249));
                    write.print("Nome da Localidade Endereço Ponta C: ");
                    write.println(line.substring(249, 264));
                    write.print("UF da Localidade Ponta C: ");
                    write.println(line.substring(264, 266));
                    write.print("Endereço da Ponta C: ");
                    write.println(line.substring(266, 296));
                    write.print("Nº do endereço da Ponta C: ");
                    write.println(line.substring(296, 301));
                    write.print("Complemento da Ponta C: ");
                    write.println(line.substring(301, 309));
                    write.print("Bairro da Ponta C: ");
                    write.println(line.substring(309, 319));
                }
                if (line.charAt(0) == '3') {
                    totalValueCalls = totalValueCalls.add(formatValueComImposto(line.substring(250, 265)));
                    totalValueCallsComImposto = totalValueCallsComImposto.add(formatValueComImposto(line.substring(237, 250)));

                    write = new PrintWriter(calls);
                    write.println(separator());

                    write.print("Tipo de Registro: ");
                    write.println(line.substring(0, 2));
                    write.print("Controle Seqüencial de gravação: ");
                    write.println(line.substring(2, 14));
                    write.print("Identificador Conta Única: ");
                    write.println(line.substring(14, 39));
                    write.print("Data de Emissão: ");
                    write.println(line.substring(39, 47));
                    write.print("Mês Referência: ");
                    write.println(line.substring(47, 53));
                    write.print("Identificador Único do Recurso: ");
                    write.println(line.substring(53, 78));
                    write.print("CNL da área local onde o terminal estava em uso durante a chamada.: ");
                    write.println(line.substring(78, 83));
                    write.print("Numero do recurso: ");
                    write.println(line.substring(83, 99));
                    write.print("Data da Ligação: ");
                    write.println(line.substring(99, 107));
                    write.print("CNL da Localidade de Destino da Chamada: ");
                    write.println(line.substring(107, 112));
                    write.print("Nome da Localidade de Destino da Chamada: ");
                    write.println(line.substring(112, 137));
                    write.print("UF do Telefone de destino da Chamada: ");
                    write.println(line.substring(137, 139));
                    write.print("Código Nacional/Internacional: ");
                    write.println(line.substring(139, 141));
                    write.print("Código de Seleção da Prestadora - CSP: ");
                    write.println(line.substring(141, 143));
                    write.print("Nome Operadora CSP: ");
                    write.println(line.substring(143, 163));
                    write.print("Número do Telefone Chamado: ");
                    write.println(line.substring(163, 180));
                    write.print("Código da Operadora de Roaming: ");
                    write.println(line.substring(180, 185));
                    write.print("Operadora a qual o terminal de destino está vinculado (portabilidade): ");
                    write.println(line.substring(185, 188));
                    write.print("Duração da Ligação: ");
                    write.println(line.substring(188, 195));
                    write.print("Código da Categoria Chamada: ");
                    write.println(line.substring(195, 198));
                    write.print("Sigla da Categoria Chamada: ");
                    write.println(line.substring(198, 201));
                    write.print("Descrição da Categoria Chamada: ");
                    write.println(line.substring(201, 226));
                    write.print("Horário da ligação: ");
                    write.println(line.substring(226, 232));
                    write.print("Alíquota ICMS: ");
                    write.println(line.substring(232, 237));
                    write.print("Valor da ligação com imposto: ");
                    write.println(formatValueComImposto(line.substring(237, 250)));
                    write.print("Valor da ligação sem imposto: ");
                    write.println(formatValueSemImposto(line.substring(250, 265)));
                    write.print("Tipo NF: ");
                    write.println(line.substring(265, 266));
                    write.print("Nº da Nota Fiscal: ");
                    write.println(line.substring(266, 278));
                    write.print("Tipo de Chamada (TC): ");
                    write.println(line.substring(278, 279));
                    write.print("Grupo Horário Tarifário: ");
                    write.println(line.substring(279, 280));
                    write.print("Descrição do Horário Tarifário: ");
                    write.println(line.substring(280, 295));
                    write.print("Degrau da ligação: ");
                    write.println(line.substring(295, 297));
                }
                if (line.charAt(0) == '4') {
                    totalValueServices = totalValueServices.add(formatValueComImposto(line.substring(192, 207)));
                    totalValueServicesComImposto = totalValueServicesComImposto.add(formatValueComImposto(line.substring(179, 192)));

                    write = new PrintWriter(services);
                    write.println(separator());
                    write.print("Tipo de Registro: ");
                    write.println(line.substring(0, 2));
                    write.print("Controle Seqüencial de gravação: ");
                    write.println(line.substring(2, 14));
                    write.print("Identificador Conta Única: ");
                    write.println(line.substring(14, 39));
                    write.print("Data de Emissão: ");
                    write.println(line.substring(39, 47));
                    write.print("Mês Referência: ");
                    write.println(line.substring(47, 53));
                    write.print("Identificador Único do Recurso: ");
                    write.println(line.substring(53, 78));
                    write.print("CNL da área local onde o terminal estava em uso durante a chamada.: ");
                    write.println(line.substring(78, 83));
                    write.print("Numero do recurso: ");
                    write.println(line.substring(83, 99));
                    write.print("Data do Serviço: ");
                    write.println(line.substring(99, 107));
                    write.print("Código Nacional/Internacional: ");
                    write.println(line.substring(107, 109));
                    write.print("Número do Telefone Destino: ");
                    write.println(line.substring(109, 126));
                    write.print("Código da Operadora de Roaming: ");
                    write.println(line.substring(126, 131));
                    write.print("Operadora a qual o terminal de destino está vinculado (portabilidade): ");
                    write.println(line.substring(131, 134));
                    write.print("Quantidade Utilizada: ");
                    write.println(line.substring(134, 140));
                    write.print("Unidade do serviço: ");
                    write.println(line.substring(140, 142));
                    write.print("Horário do Serviço: ");
                    write.println(line.substring(142, 148));
                    write.print("Código da Categoria Serviço: ");
                    write.println(line.substring(148, 151));
                    write.print("Sigla da Categoria Serviço: ");
                    write.println(line.substring(151, 154));
                    write.print("Descrição da Categoria Serviço: ");
                    write.println(line.substring(154, 179));
                    write.print("Valor do serviço com impostos: ");
                    write.println(formatValueComImposto(line.substring(179, 192)));
                    write.print("Valor do serviço sem impostos: ");
                    write.println(formatValueSemImposto(line.substring(192, 207)));
                    write.print("Tipo NF: ");
                    write.println(line.substring(207, 208));
                    write.print("Nº da Nota Fiscal: ");
                    write.println(line.substring(208, 220));

                }
                if (line.charAt(0) == '5') {

                }
                if (line.charAt(0) == '6') {

                }
                if (line.charAt(0) == '7') {

                }
                if (line.charAt(0) == '8') {
                    write = new PrintWriter(nf);
                    write.println(separator());
                    write.print("Tipo de Registro: ");
                    write.println(line.substring(0, 2));
                    write.print("Controle Seqüencial de gravação: ");
                    write.println(line.substring(2, 14));
                    write.print("Identificador Conta Única: ");
                    write.println(line.substring(14, 39));
                    write.print("Data de Emissão: ");
                    write.println(line.substring(39, 47));
                    write.print("Mês Referência: ");
                    write.println(line.substring(47, 53));
                    write.print("Data de Vencimento da NF: ");
                    write.println(line.substring(53, 61));
                    write.print("Código da Operadora: ");
                    write.println(line.substring(61, 64));
                    write.print("Nome Operadora: ");
                    write.println(line.substring(64, 79));
                    write.print("CNPJ Operadora: ");
                    write.println(line.substring(79, 94));
                    write.print("Valor da NF com impostos: ");
                    write.println(formatValueComImposto(line.substring(94, 107)));
                    write.print("Tipo de NF: ");
                    write.println(line.substring(107, 108));
                    write.print("Nº da Nota Fiscal: ");
                    write.println(line.substring(108, 120));
                    
                }
                if (line.substring(0, 2) == "90") {

                }
                if (line.substring(0, 2) == "99") {

                }               
                
                line = readFile.readLine();
            }

            write = new PrintWriter(resume);
            write.println(separator());
            write.println(separator());
            write.print("VALOR TOTAL DE IMPOSTOS: ");
            write.println(totalValueImpostos);
            write.print("VALOR TOTAL DE CHAMADAS: ");
            write.println(totalValueResumeCalls);
            write.print("VALOR TOTAL DE SERVIÇOS: ");
            write.println(totalValueResumeServices);
            write.print("VALOR TOTAL DO RECURSO: ");
            write.println(totalValueResume);

            write = new PrintWriter(calls);
            write.println(separator());
            write.println(separator());
            write.print("TOTAL DE LIGAÇÃO COM IMPOSTO: ");
            write.println(totalValueCallsComImposto);
            write.print("TOTAL DE LIGAÇÃO SEM IMPOSTO: ");
            write.println(totalValueCalls);

            write = new PrintWriter(services);
            write.println(separator());
            write.println(separator());
            write.print("TOTAL DO SERVICO COM IMPOSTO: ");
            write.println(totalValueServicesComImposto);
            write.print("TOTAL DO SERVICO SEM IMPOSTO: ");
            write.println(totalValueServices);

            header.close();
            resume.close();
            adress.close();
            calls.close();
            services.close();
            desconts.close();
            planes.close();
            ajust.close();
            nf.close();
            information.close();
            trailler.close();
        } catch (IOException ex) {
            Logger.getLogger(FebrabamService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return directoryName;
    }

    private String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy");
        return sdf.format(new Date(System.currentTimeMillis()));
    }

    private String formatDate(String date) {
        return date.substring(6, 8) + "/" + date.substring(4, 6) + "/" + date.substring(0, 4);
    }

    private String formatMesReferencia(String date) {
        return date.substring(0, 2) + "/" + date.substring(2, 6);
    }

    private void formatHeader(PrintWriter write, String headerName) {
        for (int i = 0; i < 131; i++) {
            write.print("#");
        }
        write.println();
        write.print("#");
        for (int i = 0; i < ((130 - headerName.length()) / 2); i++) {
            write.print("-");
        }
        write.print(headerName);
        for (int i = 0; i < ((129 - headerName.length()) / 2); i++) {
            write.print("-");
        }
        write.println("#");
        for (int i = 0; i < 131; i++) {
            write.print("#");
        }
        write.println("\n\n");
    }

    private String separator() {
        String separator = new String();
        for (int i = 0; i < 131; i++) {
            separator += "-";
        }
        return separator;
    }

    private BigDecimal formatValueComImposto(String value) {
        return new BigDecimal(value.substring(0, 11) + "." + value.substring(11, 13));
    }
    
    private BigDecimal formatValueSemImposto(String value) {
        return new BigDecimal(value.substring(0, 11) + "." + value.substring(11, 15));
    }

    private String formatCallDuration(String duration) {
        String secondsTxt = duration.substring(5, 6);
        String minutesTxt = duration.substring(0, 5);
        String hoursTxt;
        String result;
        BigDecimal secondsNumber = new BigDecimal("0");
        BigDecimal minutesNumber = new BigDecimal("0");
        if (Integer.parseInt(secondsTxt) > 0) {
            secondsNumber = new BigDecimal(secondsTxt).multiply(new BigDecimal("60")).divide(new BigDecimal("10"));
            if (secondsNumber.toString().length() <= 1) {
                secondsTxt = "0" + secondsNumber.toString();
            } else {
                secondsTxt = secondsNumber.toString();
            }
        } else {
            secondsTxt = "00";
        }
        if (Integer.parseInt(minutesTxt) >= 60) {
            minutesNumber = new BigDecimal(minutesTxt).divide(new BigDecimal("60"), 2, RoundingMode.UP);
            if (minutesNumber.toString().contains(".")) {
                int comma = minutesNumber.toString().indexOf(".");
                hoursTxt = minutesNumber.toString().substring(0, comma);
                if (hoursTxt.length() < 2) {
                    hoursTxt = "0" + hoursTxt;
                }
                minutesTxt = minutesNumber.toString().substring(comma + 1);
                if (minutesTxt.length() < 1) {
                    minutesTxt = minutesTxt + "0";
                }
            } else {
                hoursTxt = "00";
                minutesTxt = minutesNumber.toString();
            }
        } else {
            hoursTxt = "00";
            minutesTxt = String.valueOf((Integer.parseInt(minutesTxt)));
            if (minutesTxt.length() < 2) {
                minutesTxt = "0" + minutesTxt;
            }
        }
        return result = hoursTxt + ":" + minutesTxt + ":" + secondsTxt;
    }

    private String formatHour(String hour) {
        return hour.substring(0, 2) + ":" + hour.substring(2, 4) + ":" + hour.substring(4, 6);
    }

    private BigDecimal formatPercentValue(String value) {
        return new BigDecimal(value.substring(0, 3) + "." + value.substring(3, 5));
    }


    private int lengthFile(BufferedReader readFile) {
        int length = 0;
        try {
            while(readFile.readLine() != null){
                length++;
            }
        } catch (IOException ex) {
            Logger.getLogger(FebrabamService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return length;
    }
}
