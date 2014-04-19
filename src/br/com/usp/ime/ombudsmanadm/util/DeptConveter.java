package br.com.usp.ime.ombudsmanadm.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeptConveter {
	
	static Map<String, String> abbrToDesc = new HashMap<>();
	static Map<String, String> descToAbbr = new HashMap<>();
	
	static {
		abbrToDesc.put("CEBIMar", "Centro de Biologia Marinha");
		abbrToDesc.put("CCE", "Centro de Computação Eletrônica da Universidade de São Paulo");
		abbrToDesc.put("CEPEUSP", "Centro de Práticas Esportivas da Universidade de São Paulo");
		abbrToDesc.put("EDUSP", "Editora da Universidade de São Paulo");
		abbrToDesc.put("EACH", "Escola de Artes, Ciências e Humanidades");
		abbrToDesc.put("ECA", "Escola de Comunicações e Artes");
		abbrToDesc.put("EEFE", "Escola de Educação Física e Esporte");
		abbrToDesc.put("EE", "Escola de Enfermagem");
		abbrToDesc.put("EP", "Escola Politécnica");
		abbrToDesc.put("FAU", "Faculdade de Arquitetura e Urbanismo");
		abbrToDesc.put("FCF", "Faculdade de Ciências Farmacêuticas");
		abbrToDesc.put("FD", "Faculdade de Direito");
		abbrToDesc.put("FEA", "Faculdade de Economia, Administração e Contabilidade");
		abbrToDesc.put("FE", "Faculdade de Educação");
		abbrToDesc.put("FFLCH", "Faculdade de Filosofia, Letras e Ciências Humanas");
		abbrToDesc.put("FM", "Faculdade de Medicina");
		abbrToDesc.put("FMVZ", "Faculdade de Medicina Veterinária e Zootecnia");
		abbrToDesc.put("FO", "Faculdade de Odontologia");
		abbrToDesc.put("FSP", "Faculdade de Saúde Pública");
		abbrToDesc.put("FAP", "Fundação Antonio Prudente");
		abbrToDesc.put("HCFMUSP", "Hospital das Clínicas da Faculdade de Medicina da USP");
		abbrToDesc.put("HCFMRP", "Hospital das Clínicas da Faculdade de Medicina de Ribeirão Preto");
		abbrToDesc.put("HU", "Hospital Universitário");
		abbrToDesc.put("IDPC", "Instituto Dante Pazzanese de Cardiologia");
		abbrToDesc.put("IAG", "Instituto de Astronomia, Geofísica e Ciências Atmosféricas");
		abbrToDesc.put("IB", "Instituto de Biociências");
		abbrToDesc.put("ICB", "Instituto de Ciências Biomédicas");
		abbrToDesc.put("IEE", "Instituto de Energia e Ambiente");
		abbrToDesc.put("IEA", "Instituto de Estudos Avançados");
		abbrToDesc.put("IEB", "Instituto de Estudos Brasileiros");
		abbrToDesc.put("IF", "Instituto de Física");
		abbrToDesc.put("IGc", "Instituto de Geociências");
		abbrToDesc.put("IME", "Instituto de Matemática e Estatística");
		abbrToDesc.put("IMESC", "Instituto de Medicina Social e de Criminologia de São Paulo");
		abbrToDesc.put("IMT", "Instituto de Medicina Tropical de São Paulo");
		abbrToDesc.put("IP", "Instituto de Psicologia");
		abbrToDesc.put("IQ", "Instituto de Química");
		abbrToDesc.put("IRI", "Instituto de Relações Internacionais");
		abbrToDesc.put("IO", "Instituto Oceanográfico");
		abbrToDesc.put("MAE", "Museu de Arqueologia e Etnologia");
		abbrToDesc.put("MAC", "Museu de Arte Contemporânea");
		abbrToDesc.put("MZ", "Museu de Zoologia");
		abbrToDesc.put("MP", "Museu Paulista");
		abbrToDesc.put("PUSP-C", "Prefeitura do Campus Usp da Capital");
		abbrToDesc.put("PUSPQSD", "Prefeitura Usp do Quadrilátero Saúde / Direito");
		abbrToDesc.put("RUSP", "Reitoria da Universidade de São Paulo");
		abbrToDesc.put("RC", "Restaurante Central");
		abbrToDesc.put("RF", "Restaurante da Física");
		abbrToDesc.put("RP", "Restaurante da PUSP-C");
		abbrToDesc.put("RQ", "Restaurante das Químicas");
		abbrToDesc.put("RCDU", "Restaurante do Clube da Universidade");
		abbrToDesc.put("SVOC", "Serviço de Verificação de Óbitos da Capital");
		abbrToDesc.put("SIBI", "Sistema Integrado de Bibliotecas");
		abbrToDesc.put("SAS", "Superintendência de Assistência Social");
		abbrToDesc.put("SCS", "Superintendência de Comunicação Social");
		abbrToDesc.put("STI", "Superintendência de Tecnologia da Informação");
		abbrToDesc.put("SEF", "Superintendência do Espaço Físico");
	}
	
	static {
		descToAbbr.put("Centro de Biologia Marinha", "CEBIMar");
		descToAbbr.put("Centro de Computação Eletrônica da Universidade de São Paulo", "CCE");
		descToAbbr.put("Centro de Práticas Esportivas da Universidade de São Paulo", "CEPEUSP");
		descToAbbr.put("Editora da Universidade de São Paulo", "EDUSP");
		descToAbbr.put("Escola de Artes, Ciências e Humanidades", "EACH");
		descToAbbr.put("Escola de Comunicações e Artes", "ECA");
		descToAbbr.put("Escola de Educação Física e Esporte", "EEFE");
		descToAbbr.put("Escola de Enfermagem", "EE");
		descToAbbr.put("Escola Politécnica", "EP");
		descToAbbr.put("Faculdade de Arquitetura e Urbanismo", "FAU");
		descToAbbr.put("Faculdade de Ciências Farmacêuticas", "FCF");
		descToAbbr.put("Faculdade de Direito", "FD");
		descToAbbr.put("Faculdade de Economia, Administração e Contabilidade", "FEA");
		descToAbbr.put("Faculdade de Educação", "FE");
		descToAbbr.put("Faculdade de Filosofia, Letras e Ciências Humanas", "FFLCH");
		descToAbbr.put("Faculdade de Medicina", "FM");
		descToAbbr.put("Faculdade de Medicina Veterinária e Zootecnia", "FMVZ");
		descToAbbr.put("Faculdade de Odontologia", "FO");
		descToAbbr.put("Faculdade de Saúde Pública", "FSP");
		descToAbbr.put("Fundação Antonio Prudente", "FAP");
		descToAbbr.put("Hospital das Clínicas da Faculdade de Medicina da USP", "HCFMUSP");
		descToAbbr.put("Hospital das Clínicas da Faculdade de Medicina de Ribeirão Preto", "HCFMRP");
		descToAbbr.put("Hospital Universitário", "HU");
		descToAbbr.put("Instituto Dante Pazzanese de Cardiologia", "IDPC");
		descToAbbr.put("Instituto de Astronomia, Geofísica e Ciências Atmosféricas", "IAG");
		descToAbbr.put("Instituto de Biociências", "IB");
		descToAbbr.put("Instituto de Ciências Biomédicas", "ICB");
		descToAbbr.put("Instituto de Energia e Ambiente", "IEE");
		descToAbbr.put("Instituto de Estudos Avançados", "IEA");
		descToAbbr.put("Instituto de Estudos Brasileiros", "IEB");
		descToAbbr.put("Instituto de Física", "IF");
		descToAbbr.put("Instituto de Geociências", "IGc");
		descToAbbr.put("Instituto de Matemática e Estatística", "IME");
		descToAbbr.put("Instituto de Medicina Social e de Criminologia de São Paulo", "IMESC");
		descToAbbr.put("Instituto de Medicina Tropical de São Paulo", "IMT");
		descToAbbr.put("Instituto de Psicologia", "IP");
		descToAbbr.put("Instituto de Química", "IQ");
		descToAbbr.put("Instituto de Relações Internacionais", "IRI");
		descToAbbr.put("Instituto Oceanográfico", "IO");
		descToAbbr.put("Museu de Arqueologia e Etnologia", "MAE");
		descToAbbr.put("Museu de Arte Contemporânea", "MAC");
		descToAbbr.put("Museu de Zoologia", "MZ");
		descToAbbr.put("Museu Paulista", "MP");
		descToAbbr.put("Prefeitura do Campus Usp da Capital", "PUSP-C");
		descToAbbr.put("Prefeitura Usp do Quadrilátero Saúde / Direito", "PUSPQSD");
		descToAbbr.put("Reitoria da Universidade de São Paulo", "RUSP");
		descToAbbr.put("Restaurante Central", "RC");
		descToAbbr.put("Restaurante da Física", "RF");
		descToAbbr.put("Restaurante da PUSP-C", "RP");
		descToAbbr.put("Restaurante das Químicas", "RQ");
		descToAbbr.put("Restaurante do Clube da Universidade", "RCDU");
		descToAbbr.put("Serviço de Verificação de Óbitos da Capital", "SVOC");
		descToAbbr.put("Sistema Integrado de Bibliotecas", "SIBI");
		descToAbbr.put("Superintendência de Assistência Social", "SAS");
		descToAbbr.put("Superintendência de Comunicação Social", "SCS");
		descToAbbr.put("Superintendência de Tecnologia da Informação", "STI");
		descToAbbr.put("Superintendência do Espaço Físico", "SEF");
	}
	
	public static String getDept(String word) {
		String result = abbrToDesc.get(word);
		
		if (result == null || result == "") {
			result = descToAbbr.get(word);
			
			if (result == null || result == "") {
				return null;
			} else {
				return abbrToDesc.get(result);
			}
		} else {
			return result;
		}
	}
	
	public static List<String> allDept() {
		List<String> depts = new ArrayList<String>();
		depts.addAll(descToAbbr.values());
		
		return depts;
	}
}
