package Ouvinte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Enuns.TipoDeCanal;
import Model.CentralDeInformacoes;
import Model.Persistencia;
import Model.ValidacaoDeCadastroDeCanal;
import PersonalizedMessage.MensagemCanal;
import PersonalizedMessage.MensagemException;
import Tela.TelaDeCadastroDeCanal;
import Tela.TelaDeMenu;
import entity.Canal;

public class OuvinteTelaDeCadastroDeCanal implements ActionListener {

	Persistencia persistencia = new Persistencia();
	CentralDeInformacoes centralDeInformacoes = persistencia.recuperarCentral();

	private TelaDeCadastroDeCanal telaDeCadastroDeCanal;

	public TelaDeCadastroDeCanal getTelaDeCadastroDeCanal() {
		return telaDeCadastroDeCanal;
	}

	public OuvinteTelaDeCadastroDeCanal(TelaDeCadastroDeCanal tela) {
		this.telaDeCadastroDeCanal = tela;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		new TelaDeMenu(null);
		telaDeCadastroDeCanal.setVisible(false);

	} // end actionPerformed

	public void actionPerformedsalvar(ActionEvent e) {

		String nome = telaDeCadastroDeCanal.getCampoNome().getText();
		String canalOuLink = telaDeCadastroDeCanal.getCampoLinkNumero().getText();

		String[] status = { "canal aberto de televisão", "broadcasting aberto na internet", "pacote de assinatura",
				"assinatura individual de televisão", "assinatura individual de broadcasting" };
		String entradaStatus = (String) JOptionPane.showInputDialog(null, "Status De Exebição: ", "",
				JOptionPane.WARNING_MESSAGE, null, status, status[0]);

		TipoDeCanal exebicao = null;

		if (status[0] == entradaStatus) {
			exebicao = TipoDeCanal.CANAL_ABERTO_DE_TELEVISAO;
		} else if (status[1] == entradaStatus) {
			exebicao = TipoDeCanal.BROADCASTING_ABERTO_NA_INTERNET;
		} else if (status[2] == entradaStatus) {
			exebicao = TipoDeCanal.PACOTE_DE_ASSINATURA;
		} else if (status[3] == entradaStatus) {
			exebicao = TipoDeCanal.ASSINATURA_INDIVIDUAL_DE_TELEVISAO;
		} else {
			exebicao = TipoDeCanal.ASSINATURA_INDIVIDUAL_DE_BROADCASTING;
		} // end else

		Canal canal = new Canal(nome, exebicao, canalOuLink);
		ValidacaoDeCadastroDeCanal cadastroDeCanal = new ValidacaoDeCadastroDeCanal();
		
		try {

			if (canal.getTipoDoCanal().equals(TipoDeCanal.ASSINATURA_INDIVIDUAL_DE_TELEVISAO)
					|| canal.getTipoDoCanal().equals(TipoDeCanal.CANAL_ABERTO_DE_TELEVISAO)) {

				cadastroDeCanal.testeCanal(canal);
				
			} else {
				cadastroDeCanal.testeCanalLink(canal);
			} // end else
			
			cadastroDeCanal.campoVazio(canal);

			centralDeInformacoes.salvarCanal(canal);
			persistencia.salvarCentral(centralDeInformacoes);
			MensagemCanal.canalSalvo();
			new TelaDeCadastroDeCanal(null);
			telaDeCadastroDeCanal.setVisible(false);

		} catch (Exception erro) {
			MensagemException.exception(erro);
		} // catch
	} // end if
} // end class
