package Ouvinte;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Date;

import javax.swing.JOptionPane;

import Enuns.EstiloSeriesRegulares;
import Enuns.StatusDeExebicao;
import Model.CentralDeInformacoes;
import Model.Persistencia;
import PersonalizedMessage.MensagemCanal;
import PersonalizedMessage.MensagemException;
import PersonalizedMessage.MensagemPrograma;
import PersonalizedMessage.MensagemUsuario;
import Tela.TelaEditarCadastroDeProgramaSeriesRegulares;
import Tela.TelaListarTodosOsProgramas;
import entity.Canal;
import entity.ProgramaSeriesRegulares;

public class OuvinteTelaEditarDeCadastroDeProgramaSeriesRegulares implements ActionListener {

	Persistencia persistencia = new Persistencia();
	CentralDeInformacoes centralDeInformacoes = persistencia.recuperarCentral();
	private TelaEditarCadastroDeProgramaSeriesRegulares telaCadastroDePrograma;

	public TelaEditarCadastroDeProgramaSeriesRegulares getTelaCadastroDePrograma() {
		return telaCadastroDePrograma;
	}

	public OuvinteTelaEditarDeCadastroDeProgramaSeriesRegulares(TelaEditarCadastroDeProgramaSeriesRegulares tela) {
		this.telaCadastroDePrograma = tela;
	}

	public void actionPerformed(ActionEvent e) {
		new TelaListarTodosOsProgramas(null);
		telaCadastroDePrograma.setVisible(false);
	} // end action

	public void actionPerformedSalvar(ActionEvent e) {

		try {

			SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
			Date data = null;
			String nome = telaCadastroDePrograma.getCampoNomeDoPrograma().getText();
			long id = Long.parseLong(telaCadastroDePrograma.getCampoIDCanal().getText());
			String horario = telaCadastroDePrograma.getCampoHorario().getText();
			String genero = telaCadastroDePrograma.getCampoGenero().getText();
			String temporada = telaCadastroDePrograma.getCampoTemporada().getText();
			String dia = telaCadastroDePrograma.getCampoDiasDaSemana().getText();
			long idPrograma = Long.parseLong(telaCadastroDePrograma.getCampoID().getText());
			dia.toUpperCase();
			DayOfWeek dayOfWeek = DayOfWeek.valueOf(dia);

			if (nome.isBlank() || horario.isBlank() || genero.isBlank() || temporada.isBlank()) {
				MensagemUsuario.usuarioCampoVazio();
			} else {

				Canal canal = centralDeInformacoes.recuperarCanalId(id);

				if (canal != null) {

					String[] opercao = { "Live Action", "Animada" };
					String entrada = (String) JOptionPane.showInputDialog(null, "Estilo Dá Séries: ", "",
							JOptionPane.WARNING_MESSAGE, null, opercao, opercao[0]);
					EstiloSeriesRegulares estilo = null;

					if (opercao[0] == entrada) {
						estilo = EstiloSeriesRegulares.LIVI_ACTION;
					} else {
						estilo = EstiloSeriesRegulares.ANIMADA;
					} // end else

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
					}

					ProgramaSeriesRegulares programa = (ProgramaSeriesRegulares) centralDeInformacoes
							.recuperarProgramaDeTVporId(idPrograma);

					if (programa instanceof ProgramaSeriesRegulares) {
						ProgramaSeriesRegulares ps = (ProgramaSeriesRegulares) programa;

						ps.setNome(nome);
						ps.setStatusDeExebicao(exebicao);
						ps.setCanal(canal);
					//	ps.setDiasDaSemana(dayOfWeek);
						ps.setHorario(horario);
						ps.setDataHiato(data);
						ps.setTemparada(temporada);
						ps.setGenero(genero);
						ps.setEstilo(estilo);

						centralDeInformacoes.adicionarProgramaDeTV(programa);
						persistencia.salvarCentral(centralDeInformacoes);
						MensagemPrograma.programaSalvo();
						new TelaListarTodosOsProgramas(null);
						telaCadastroDePrograma.setVisible(false);
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
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} // end catch
	} // end action
}
// end class
