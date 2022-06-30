package Ouvinte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Model.CentralDeInformacoes;
import Model.Mensageiro;
import Model.Persistencia;
import PersonalizedMessage.MensagemEmail;
import PersonalizedMessage.MensagemException;
import PersonalizedMessage.MensagemUsuario;
import Tela.TelaDeLogin;
import Tela.TelaDeMenu;
import entity.Usuario;

public class OuvinteTelaDeLogin implements ActionListener {

	Persistencia persistencia = new Persistencia();
	CentralDeInformacoes centralDeInformacoes = persistencia.recuperarCentral();
	private TelaDeLogin telaDeLogin;

	public TelaDeLogin getTelaDeLogin() {
		return telaDeLogin;
	}

	public OuvinteTelaDeLogin(TelaDeLogin tela) {
		this.telaDeLogin = tela;
	}

	public void actionPerformed(ActionEvent proseguir) {

		String email = telaDeLogin.getCampoEmail().getText();
		String senha = telaDeLogin.getCampoSenha().getText();

		try {

			if (email.isBlank() || senha.isBlank()) {
				MensagemUsuario.usuarioCampoVazio();
			} else {
				if (centralDeInformacoes.isLogin(email, senha)) {
					new TelaDeMenu(null);
					telaDeLogin.setVisible(false);
				} else {
					MensagemUsuario.usuarioNaoEncontrado();
				} // end else
			} // end else
		} catch (NullPointerException e) {
			MensagemException.nullPointerException(e);
		} // end catch
	} // actionPerformed

	public void actionPerformedSenha(ActionEvent senha) {

		String nomeEmail = JOptionPane.showInputDialog(telaDeLogin, "Informe o nome do usuário ou um email: ");
		Usuario usuario = centralDeInformacoes.recuperarUsuario(nomeEmail);

		if (!nomeEmail.contains("@")) {

			if (usuario != null) {
				Mensageiro.enviarProgramacaoDeHoje("Seus Dados Do APP", usuario.getEmail(), usuario.toString());
				MensagemEmail.emailEnviadoUsuario();
			} else {
				MensagemUsuario.usuarioNaoEncontrado();
			} // end else

		} else {
			
			Mensageiro.enviarProgramacaoDeHoje("Seus Dados Do APP", nomeEmail, centralDeInformacoes.getTodosOsUsuarios().toString());
			MensagemEmail.emailEnviadoUsuario();	
		} // end else
	} // end actionPerformedSenha
} // end class
