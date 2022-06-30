package ApplicationAgenda;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

import Enuns.StatusDeExebicao;
import Model.CentralDeInformacoes;
import Model.Mensageiro;
import Model.Persistencia;
import PersonalizedMessage.MensagemEmail;

public class EnviarEmailTarefa extends TimerTask {

	Persistencia persistencia = new Persistencia();
	CentralDeInformacoes centralDeInformacoes = persistencia.recuperarCentral();

	@SuppressWarnings("static-access")
	@Override
	public void run() {

		Date date = new Date();
		SimpleDateFormat formatar = new SimpleDateFormat("hh:m");
		String hora = formatar.format(date);
		StatusDeExebicao status = StatusDeExebicao.EXIBICAO;

		for (int i = 0; i < centralDeInformacoes.getTodasAsAgendas().size(); i++) {

			if (status.equals(centralDeInformacoes.getTodasAsAgendas().get(i).getStatusDeExebicao().EXIBICAO)
					&& hora.equals(centralDeInformacoes.getTodasAsAgendas().get(i).getHorario())) {
				Mensageiro.enviarProgramacaoDeHoje("Vai começar",
						centralDeInformacoes.getTodosOsUsuarios().get(0).getEmail(),
						"Prepare sua Pipoca" + "\n" + centralDeInformacoes.getTodasAsAgendas().get(i).toString());
				MensagemEmail.emailEnviadoUsuario();

			} // end if
		} // end for
	} // end void run
} // end class
