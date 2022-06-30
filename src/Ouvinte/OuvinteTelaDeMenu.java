package Ouvinte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Model.CentralDeInformacoes;
import Model.Persistencia;
import PersonalizedMessage.MensagemException;
import PersonalizedMessage.MensagemUsuario;
import Tela.TelaAgendaDePrograma;
import Tela.TelaCadastroDeProgramaContinuo;
import Tela.TelaCadastroDeProgramaDeRealityShows;
import Tela.TelaCadastroDeProgramaSeriesRegulares;
import Tela.TelaDeCadastroDeCanal;
import Tela.TelaDeImagem;
import Tela.TelaDeMenu;
import Tela.TelaEnviarEmail;
import Tela.TelaGerarPDF;
import Tela.TelaListarTodosOsCanal;
import Tela.TelaListarTodosOsProgramas;
import entity.Usuario;

public class OuvinteTelaDeMenu implements ActionListener {

	Persistencia persistencia = new Persistencia();
	CentralDeInformacoes centralDeInformacoes = persistencia.recuperarCentral();

	private TelaDeMenu telaDeMenu;

	public TelaDeMenu getTelaDeMenu() {
		return telaDeMenu;
	}

	public OuvinteTelaDeMenu(TelaDeMenu tela) {
		this.telaDeMenu = tela;
	}

	public void actionPerformed(ActionEvent clique) {

		String comando = clique.getActionCommand();
		if (comando.equals("Cadastrar Canal")) {
			
			new TelaDeCadastroDeCanal(null);
			telaDeMenu.setVisible(false);
			
		} else if (comando.equals("Listar Canal")) {
			
			new TelaListarTodosOsCanal(null);
			telaDeMenu.setVisible(false);
			
		} else if (comando.equals("Cadastrar Programas")) {

			String[] opercao = { "Programa Séries Regulares", "Programa De RealityShows", "Programa Continuo" };
			String entrada = (String) JOptionPane.showInputDialog(null, "Qual Tipo De Programa Você Deseja Cadastrar: ",
					"", JOptionPane.WARNING_MESSAGE, null, opercao, opercao[0]);
			if (opercao[0] == entrada) {
				new TelaCadastroDeProgramaSeriesRegulares(null);
				telaDeMenu.setVisible(false);
			} else if (opercao[1] == entrada) {
                new TelaCadastroDeProgramaDeRealityShows(null);
				telaDeMenu.setVisible(false);
			} else {
				new TelaCadastroDeProgramaContinuo(null);
				telaDeMenu.setVisible(false);
			}
					
		} else if (comando.equals("Listar Programas")) {
			
			telaDeMenu.setVisible(false);
			new TelaListarTodosOsProgramas(null);
			
		} else if (comando.equals("Gerar PDF")) {
			
			new TelaGerarPDF(null);
			telaDeMenu.setVisible(false);
			
		} else if(comando.equals("Foto")) {
			
			new TelaDeImagem(null);
			telaDeMenu.setVisible(false);
			
		} else if(comando.equals("Minha Agenda")) {
			
			new TelaAgendaDePrograma(null);
			telaDeMenu.setVisible(false);

		} else if(comando.equals("Enviar Minha Agenda Por Email")) {
			
			new TelaEnviarEmail(null);
			telaDeMenu.setVisible(false);
		}
	}

	public void actionPerformedExcluir(ActionEvent clique) {

		try {

			String nome = JOptionPane.showInputDialog("informe o nome:");
			Usuario usuario = centralDeInformacoes.recuperarUsuario(nome);

			if (usuario != null) {
				centralDeInformacoes.excluirUsuario(usuario);
				persistencia.salvarCentral(centralDeInformacoes);
				MensagemUsuario.usuarioExcluir();
				System.exit(0);
			} else {
				MensagemUsuario.usuarioNaoEncontrado();
			}

		} catch (NullPointerException e) {
			MensagemException.nullPointerException(e);
		}
	}
}
