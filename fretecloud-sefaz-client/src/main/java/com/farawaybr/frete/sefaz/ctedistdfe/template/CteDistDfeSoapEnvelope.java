package com.farawaybr.frete.sefaz.ctedistdfe.template;

public class CteDistDfeSoapEnvelope {

	public static String envelope(String xml) {
		return String.format(
				"<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:cted=\"http://www.portalfiscal.inf.br/cte/wsdl/CTeDistribuicaoDFe\">\r\n"
						+ "   <soap:Header/>\r\n" + "   <soap:Body>\r\n" + "      <cted:cteDistDFeInteresse>\r\n"
						+ "         <!--Optional:-->\r\n" + "         <cted:cteDadosMsg>%s</cted:cteDadosMsg>\r\n"
						+ "      </cted:cteDistDFeInteresse>\r\n" + "   </soap:Body>\r\n" + "</soap:Envelope>",
				xml);
	}
}
