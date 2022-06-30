package application;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import Model.CentralDeInformacoes;
import Model.Persistencia;
import Tela.TelaDeCadastroDeUsuario;
import Tela.TelaDeLogin;

public class Main {

	public static void main(String[] args) {

		/*
		 * Scanner leitor = new Scanner(System.in);
		 * 
		 * System.out.println("Informe um dia"); String dia = leitor.nextLine();
		 * Enuns.DayOfWeek dayOfWeekE = Enuns.DayOfWeek.valueOf(dia.toUpperCase());
		 * System.out.println(dayOfWeekE); leitor.close();
		 */
		
		String a = null;
		
		Persistencia persistencia = new Persistencia();
		CentralDeInformacoes centralDeInformacoes = persistencia.recuperarCentral();

		if (centralDeInformacoes.getTodosOsUsuarios().size() > 0) {
			new TelaDeLogin(null);
		} else {
			new TelaDeCadastroDeUsuario(null);
		}

		LocalDate date = LocalDate.now();
		DayOfWeek dow = date.getDayOfWeek();

		System.out.println("Date  = " + date);
		System.out.println("Dow   = " + dow + "; value = " + dow.getValue());

		// Get DayOfWeek display name in different locale.
		Locale locale = new Locale("id", "ID");
		String indonesian = dow.getDisplayName(TextStyle.SHORT, locale);
		System.out.println("ID = " + indonesian);
	}
}
