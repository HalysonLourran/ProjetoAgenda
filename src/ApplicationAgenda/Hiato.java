package ApplicationAgenda;

import java.awt.HeadlessException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import Enuns.StatusDeExebicao;
import Model.CentralDeInformacoes;
import Model.Persistencia;
import PersonalizedMessage.MensagemAgenda;
import entity.Programa;

public class Hiato implements Runnable {

	Persistencia persistencia = new Persistencia();
	CentralDeInformacoes centralDeInformacoes = persistencia.recuperarCentral();

	public Date getData(Date aDate) {
		
		final Calendar myCalendar = Calendar.getInstance();
		myCalendar.setTime(aDate);
		myCalendar.set(Calendar.HOUR_OF_DAY, 0);
		myCalendar.set(Calendar.MINUTE, 0);
		myCalendar.set(Calendar.SECOND, 0);
		myCalendar.set(Calendar.MILLISECOND, 0);
		return myCalendar.getTime();
	} // end Date

	@Override
	public void run() {

		Date hiato = new Date();
		Date date = null;
		SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
		
		for (int i = 0; i < centralDeInformacoes.getTodasAsAgendas().size(); i++) {
			
			if (getData(hiato).equals(centralDeInformacoes.getTodasAsAgendas().get(i).getDataHiato())  
					&& centralDeInformacoes.getTodasAsAgendas().get(i).getStatusDeExebicao().equals(StatusDeExebicao.HIATO)) {
				
				MensagemAgenda.hiatoHoje(hiato, centralDeInformacoes.getTodasAsAgendas().get(i));
				Programa p = centralDeInformacoes.getTodasAsAgendas().get(i);
				
				String[] status = { "Exibição", "Mudar Data", "Finalizado", "Cancelado" };
				String entradaStatus = (String) JOptionPane.showInputDialog(null, "Coloque em modo de exebição... ", "",
						JOptionPane.WARNING_MESSAGE, null, status, null);

				StatusDeExebicao exebicao = null;

				if (status[0] == entradaStatus) {
					exebicao = StatusDeExebicao.EXIBICAO;
				} else if (status[1] == entradaStatus) {
					
					exebicao = StatusDeExebicao.HIATO;
					
					try {
						date = formatar.parse(JOptionPane.showInputDialog("Data de exebição: Separe por barras /. "));
					} catch (HeadlessException e) {
						e.printStackTrace();
					} catch (ParseException e) {
						e.printStackTrace();
					} // end catch
					
				} else if (status[2] == entradaStatus) {
					exebicao = StatusDeExebicao.FINALIZADO;
				} else if (status[3] == entradaStatus) {
					exebicao = StatusDeExebicao.CANCELADO;
				} else {
					exebicao = StatusDeExebicao.HIATO; 
				} // end else
				
				p.setStatusDeExebicao(exebicao);
				if(date != null) {
					p.setDataHiato(date);
				} // end if
				persistencia.salvarCentral(centralDeInformacoes);
			} // end if
		} // end for	
	} // end run
} // end class
