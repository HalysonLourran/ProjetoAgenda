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
import Tela.TelaCadastroDeProgramaSeriesRegulares;
import Tela.TelaDeMenu;
import entity.Canal;
import entity.ProgramaSeriesRegulares;

public class OuvinteTelaDeCadastroDeProgramaSeriesRegulares implements ActionListener {

	Persistencia persistencia = new Persistencia();
	CentralDeInformacoes centralDeInformacoes = persistencia.recuperarCentral();
	private TelaCadastroDeProgramaSeriesRegulares telaCadastroDePrograma;

	public TelaCadastroDeProgramaSeriesRegulares getTelaCadastroDePrograma() {
		return telaCadastroDePrograma;
	}

	public OuvinteTelaDeCadastroDeProgramaSeriesRegulares(TelaCadastroDeProgramaSeriesRegulares tela) {
		this.telaCadastroDePrograma = tela;
	}

	public void actionPerformed(ActionEvent e) {
		new TelaDeMenu(null);
		telaCadastroDePrograma.setVisible(false);
	} // end action

	@SuppressWarnings("static-access")
	public void actionPerformedSalvar(ActionEvent e) {

		try {

			SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
			Date data = null;
			String nome = telaCadastroDePrograma.getCampoNomeDoPrograma().getText();
			long id = Long.parseLong(telaCadastroDePrograma.getCampoIDCanal().getText());
			String horario = telaCadastroDePrograma.getCampoHorario().getText();
			String genero = telaCadastroDePrograma.getCampoGenero().getText();
			String temporada = telaCadastroDePrograma.getCampoTemporada().getText();
	        String[] dia = telaCadastroDePrograma.getCampoDiasDaSemana().getText().split(", ");
	    //  DayOfWeek dayOfWeek = DayOfWeek.valueOf(dia.toUpperCase());
	        
	        DayOfWeek[] dayOfWeeks = new DayOfWeek[dia.length];
	        DayOfWeek[] dayOfWeeksFinal = new DayOfWeek[dia.length];
	        for(int i = 0; i < dia.length; i++) {
	        	dayOfWeeksFinal[i] = dayOfWeeks[i].valueOf(dia[i].toUpperCase());
	        	System.out.println(dayOfWeeksFinal[i].toString());
	        }
	        
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

					ProgramaSeriesRegulares programa = new ProgramaSeriesRegulares(nome, exebicao, canal, dayOfWeeksFinal, horario, data, temporada, genero, estilo);
					centralDeInformacoes.adicionarProgramaDeTV(programa);
					persistencia.salvarCentral(centralDeInformacoes);
					MensagemPrograma.programaSalvo();
					new TelaCadastroDeProgramaSeriesRegulares(null);
					telaCadastroDePrograma.setVisible(false);
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
