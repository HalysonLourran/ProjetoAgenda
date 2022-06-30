package Ouvinte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Enuns.TipoDeCanal;
import Model.CentralDeInformacoes;
import Model.Persistencia;
import PersonalizedMessage.MensagemCanal;
import Tela.TelaEditarCadastroDeCanal;
import Tela.TelaListarTodosOsCanal;
import entity.Canal;

public class OuvinteTelaEditarCadastroDeCanal implements ActionListener {

	Persistencia persistencia = new Persistencia();
	CentralDeInformacoes centralDeInformacoes = persistencia.recuperarCentral();

	private TelaEditarCadastroDeCanal telaEditarCadastroDeCanal;

	public TelaEditarCadastroDeCanal getTelaEditarCadastroDeCanal() {
		return telaEditarCadastroDeCanal;
	}

	public OuvinteTelaEditarCadastroDeCanal(TelaEditarCadastroDeCanal tela) {
		this.telaEditarCadastroDeCanal = tela;
	}

	public void actionPerformed(ActionEvent e) {

		String nome = telaEditarCadastroDeCanal.getCampoNome().getText();
		String linkOuCanal = telaEditarCadastroDeCanal.getCampoNumeroOuLink().getText();
		long id = Long.parseLong(telaEditarCadastroDeCanal.getCampoid().getText());

		Canal canal = centralDeInformacoes.recuperarCanalId(id);

		String[] status = { "canal aberto de televis�o", "broadcasting aberto na internet", "pacote de assinatura", "assinatura individual de televis�o", "assinatura individual de broadcasting" };
		String entradaStatus = (String) JOptionPane.showInputDialog(null, "Status De Exebi��o: ", "",
				JOptionPane.WARNING_MESSAGE, null, status, status[0]);

		TipoDeCanal exebicao = null;

		if (status[0] == entradaStatus) {
			exebicao = TipoDeCanal.CANAL_ABERTO_DE_TELEVISAO;
		} else if (status[1] == entradaStatus) {
			exebicao = TipoDeCanal.BROADCASTING_ABERTO_NA_INTERNET;
		} else if (status[2] == entradaStatus) {
			exebicao = TipoDeCanal.PACOTE_DE_ASSINATURA;
		} else if(status[3] == entradaStatus) {
			exebicao = TipoDeCanal.ASSINATURA_INDIVIDUAL_DE_TELEVISAO;
		}else {
			exebicao = TipoDeCanal.ASSINATURA_INDIVIDUAL_DE_BROADCASTING;
		} 
		
		
		if (canal != null) {
			canal.setNome(nome);
			canal.setTipoDoCanal(exebicao);
			canal.setLinkOuCanal(linkOuCanal);
			persistencia.salvarCentral(centralDeInformacoes);
			MensagemCanal.canalAtualizado();
			new TelaListarTodosOsCanal(null);
			telaEditarCadastroDeCanal.setVisible(false);
		} else {
			MensagemCanal.canalNaoEncontardo();
		}
	}

	public void actionPerformedVoltar(ActionEvent e) {
		new TelaListarTodosOsCanal(null);
		telaEditarCadastroDeCanal.setVisible(false);
	} // end action
}
