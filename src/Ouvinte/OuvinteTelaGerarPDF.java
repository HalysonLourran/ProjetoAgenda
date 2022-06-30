package Ouvinte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.CentralDeInformacoes;
import Model.GeradorDeRelatorio;
import Model.Persistencia;
import PersonalizedMessage.MensagemPDF;
import Tela.TelaDeMenu;
import Tela.TelaGerarPDF;

public class OuvinteTelaGerarPDF implements ActionListener {

	
	Persistencia persistencia = new Persistencia();
	CentralDeInformacoes centralDeInformacoes = persistencia.recuperarCentral();
	
	public TelaGerarPDF telaGerarPDF;
	
	public TelaGerarPDF getTelaGerarPDF() {
		return telaGerarPDF;
	}
	
	public OuvinteTelaGerarPDF(TelaGerarPDF tela) {
		this.telaGerarPDF = tela;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {	
		new TelaDeMenu(null);
		telaGerarPDF.setVisible(false);	
	}

	public void actionPerformedGerarPDF(ActionEvent e) {
		GeradorDeRelatorio.obterProgramacaoDeUmCanal(centralDeInformacoes.getTodosOsProgramas());
		MensagemPDF.PDFCriado();
	}
}
