package Ouvinte;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.CentralDeInformacoes;
import Model.Mensageiro;
import Model.Persistencia;
import PersonalizedMessage.MensagemEmail;
import Tela.TelaDeMenu;
import Tela.TelaEnviarEmail;

public class OuvinteTelaDeEnviarEmail implements ActionListener {

    private TelaEnviarEmail telaEnviarEmail;

    Persistencia persistencia = new Persistencia();
    CentralDeInformacoes central = persistencia.recuperarCentral();

    public OuvinteTelaDeEnviarEmail(TelaEnviarEmail tela){
        this.telaEnviarEmail = tela;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void actionPerformedEnviar(ActionEvent e, String enviar_email) {

    	try {
    		
            String emailCleinte = telaEnviarEmail.getEmail().getText();
            String assunto = telaEnviarEmail.getAssunto().getText();
            String mensagem = telaEnviarEmail.getAreaMensagem().getText();
            
            Mensageiro.enviarProgramacaoDeHoje(assunto, emailCleinte, mensagem + central.getTodasAsAgendas().toString());
            MensagemEmail.emailEnviadoUsuario();

    	} catch (Exception erro) {
		    MensagemEmail.emailErro();
		}
    }

    public void actionPerformedVoltar(ActionEvent e, String voltar) {
    	
            new TelaDeMenu(null);
            telaEnviarEmail.setVisible(false);
     
    }
}
