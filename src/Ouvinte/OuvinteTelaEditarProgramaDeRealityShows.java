package Ouvinte;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import Enuns.StatusDeExebicao;
import Model.CentralDeInformacoes;
import Model.Persistencia;
import PersonalizedMessage.MensagemCanal;
import PersonalizedMessage.MensagemException;
import PersonalizedMessage.MensagemPrograma;
import PersonalizedMessage.MensagemUsuario;
import Tela.TelaEditarProgramaDeRealityShows;
import Tela.TelaListarTodosOsProgramas;
import entity.Canal;
import entity.Programa;
import entity.ProgramaDeRealityShows;

public class OuvinteTelaEditarProgramaDeRealityShows implements ActionListener {

	Persistencia persistencia = new Persistencia();
	CentralDeInformacoes centralDeInformacoes = persistencia.recuperarCentral();
	private TelaEditarProgramaDeRealityShows telaEditarProgramaDeRealityShows;

	public TelaEditarProgramaDeRealityShows telaEditarProgramaDeRealityShows() {
		return telaEditarProgramaDeRealityShows;
	}

	public OuvinteTelaEditarProgramaDeRealityShows(TelaEditarProgramaDeRealityShows tela) {
		this.telaEditarProgramaDeRealityShows = tela;
	}

	public void actionPerformed(ActionEvent e) {
		new TelaListarTodosOsProgramas(null);
		telaEditarProgramaDeRealityShows.setVisible(false);
	} // end action

	public void actionPerformedSalvar(ActionEvent e) {

		try {

			SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
			Date data = null;

			String nome = telaEditarProgramaDeRealityShows.getCampoNomeDoPrograma().getText();
			String nomeDoApresentador = telaEditarProgramaDeRealityShows.getCampoApresentador().getText();
			long idCanal = Long.parseLong(telaEditarProgramaDeRealityShows.getCampoIDCanal().getText());
			String horario = telaEditarProgramaDeRealityShows.getCampoHorario().getText();
			String temporada = telaEditarProgramaDeRealityShows.getCampoTemporada().getText();
			long idPrograma = Long.parseLong(telaEditarProgramaDeRealityShows.getCampoIDPrograma().getText());

			if (nome.isBlank() || horario.isBlank() || nomeDoApresentador.isBlank() || temporada.isBlank()) {
				MensagemUsuario.usuarioCampoVazio();
			} else {

				Canal canal = centralDeInformacoes.recuperarCanalId(idCanal);

				if (canal != null) {

					String[] status = { "Exibição", "Hiato", "Finalizado", "Cancelado" };
					String entradaStatus = (String) JOptionPane.showInputDialog(null, "Estilo Dá Séries: ", "",
							JOptionPane.WARNING_MESSAGE, null, status, status[0]);

					StatusDeExebicao exebicao = null;

					if (status[0] == entradaStatus) {
						exebicao = StatusDeExebicao.EXIBICAO;
					} else if (status[1] == entradaStatus) {
						exebicao = StatusDeExebicao.HIATO;
						data = formatar.parse(JOptionPane.showInputDialog("Data de exebição: Separe por barras /. "));

					} else if (status[2] == entradaStatus) {
						exebicao = StatusDeExebicao.FINALIZADO;
					} else {
						exebicao = StatusDeExebicao.CANCELADO;
					} // end else
					
					Programa programa = centralDeInformacoes.recuperarProgramaDeTVporId(idPrograma);

					if (programa instanceof ProgramaDeRealityShows) {
						ProgramaDeRealityShows pr = (ProgramaDeRealityShows) programa;

						if (programa != null) {

							pr.setNome(nome);
							pr.setNomeDosApresentadores(nomeDoApresentador);
							pr.setStatusDeExebicao(exebicao);
							pr.setCanal(canal);
							pr.setDiasDaSemana(null);
							pr.setHorario(horario);
							pr.setDataHiato(data);
							pr.setTemporada(temporada);
							
							persistencia.salvarCentral(centralDeInformacoes);
							MensagemPrograma.programaAtualizado();
							new TelaListarTodosOsProgramas(null);
							telaEditarProgramaDeRealityShows.setVisible(false);
						}
					} 
					
				} else {
					MensagemCanal.canalNaoEncontardo();
				} // end else
			} // end if
		} catch (NumberFormatException number) {
			MensagemException.numberFormatException(number);
		} catch (HeadlessException e1) {
			e1.printStackTrace();
		} catch (ParseException e1) {
			e1.printStackTrace();
		} // end catch
	} // end action
} // end class
