package Ouvinte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Model.CentralDeInformacoes;
import Model.Persistencia;
import PersonalizedMessage.MensagemCanal;
import PersonalizedMessage.MensagemException;
import Tela.TelaDeMenu;
import Tela.TelaListarTodosOsCanal;
import entity.Canal;

public class OuvinteTelaDeListarCanal implements ActionListener {

	Persistencia persistencia = new Persistencia();
	CentralDeInformacoes centralDeInformacoes = persistencia.recuperarCentral();
	private TelaListarTodosOsCanal telaDeListarCanal;

	public TelaListarTodosOsCanal getTelaDeListarCanal() {
		return telaDeListarCanal;
	}

	public OuvinteTelaDeListarCanal(TelaListarTodosOsCanal tela) {
		this.telaDeListarCanal = tela;
	}

	public void actionPerformed(ActionEvent e) {
		new TelaDeMenu(null);
		telaDeListarCanal.setVisible(false);
	}

	public void actionPerformedExcluir(ActionEvent e) {
		try {
			String nome = JOptionPane.showInputDialog("Informe o nome do Canal: ");
			Canal canal = centralDeInformacoes.recuperarCanal(nome);
			if (canal != null) {
				centralDeInformacoes.excluirCanal(canal);
				MensagemCanal.canalExcluido();
				persistencia.salvarCentral(centralDeInformacoes);
				telaDeListarCanal.setVisible(false);
				new TelaListarTodosOsCanal(null);
			} else {
				MensagemCanal.canalNaoEncontardo();
			}
		} catch (NumberFormatException erro) {
			MensagemException.numberFormatException(erro);
		}
	}

	public void actionPerformedAtualizar(ActionEvent e) {

		String nome = JOptionPane.showInputDialog("Informe o nome do Canal: ");
		Canal canal = centralDeInformacoes.recuperarCanal(nome);
		if (canal != null) {
			new Tela.TelaEditarCadastroDeCanal(null, canal);
			telaDeListarCanal.setVisible(false);
		} else {
			MensagemCanal.canalNaoEncontardo();
		}

	}

	public void actionPerformedDetalhar(ActionEvent e) {

		String nome = JOptionPane.showInputDialog("Nome Do Canal");
		Canal canal = centralDeInformacoes.recuperarCanal(nome);

		if (canal != null) {
			MensagemCanal.detalharCanal(canal);
		} else {
			MensagemCanal.canalNaoEncontardo();
		}
	}
}
