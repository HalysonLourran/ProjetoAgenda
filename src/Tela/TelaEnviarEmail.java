package Tela;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Janelas.JanelaPadrao;
import Ouvinte.OuvinteTelaDeEnviarEmail;

public class TelaEnviarEmail extends JanelaPadrao{

    OuvinteTelaDeEnviarEmail ouvinte = new OuvinteTelaDeEnviarEmail(this);

    private JTextArea areaMensagem;
    private JTextField assunto;
    private JTextField email;
    private JButton enviar;
    private JButton voltar;

    public TelaEnviarEmail(String titulo) {
        super(titulo);
        adicionarTitulo();
        adicionarJLabel();
        adicionarCampo();
        adicionarBotaoEnviar();
        adicionarBotaoVoltar();
        setVisible(true);
    }
    private void adicionarTitulo(){
        JLabel enviarEmail = new JLabel("ENVIAR EMAIL", JLabel.CENTER);
        enviarEmail.setBounds(0, 0, 700, 40);
        enviarEmail.setOpaque(true);
        enviarEmail.setBackground(Color.GRAY);
        add(enviarEmail);
    }
    private void adicionarJLabel(){

        JLabel assunto = new JLabel("Assunto:");
        assunto.setBounds(20, 70, 100, 30);
        add(assunto);

        JLabel emailCliente = new JLabel("Emial:");
        emailCliente.setBounds(20, 150, 100, 30);
        add(emailCliente);

        JLabel mensagem = new JLabel("Mensagem: ");
        mensagem.setBounds(20, 230, 100, 30);
        add(mensagem);
    }

    private void adicionarCampo(){

        assunto = new JTextField();
        assunto.setBounds(150, 70, 250, 30);
        add(assunto);

        email = new JTextField();
        email.setBounds(150, 150, 250, 30);
        add(email);

        areaMensagem = new JTextArea();
        JScrollPane painel = new JScrollPane(areaMensagem);
        painel.setBounds(150, 230, 250, 90);
        areaMensagem.setLineWrap(true);
        areaMensagem.setWrapStyleWord(true);
        add(painel);
    }

    private void adicionarBotaoEnviar(){

        enviar = new JButton("Enviar Email");
        enviar.setIcon(new ImageIcon("icones/icons8-gmail-20.png"));
        enviar.setBounds(480, 400, 130, 30);  // esquerda para direita, B, C, comprimento, altura;
        enviar.addActionListener(enviar());
        add(enviar);
    }

    public ActionListener enviar(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ouvinte.actionPerformedEnviar(e, "Enviar Email");
            }
        };
    }

    private void adicionarBotaoVoltar(){

        voltar = new JButton("Voltar");
        voltar.setIcon(new ImageIcon("icones/icons8-rotate-left-20.png"));
        voltar.setBounds(20, 400, 130, 30);  // esquerda para direita, B, C, comprimento, altura
        voltar.addActionListener(voltar());
        add(voltar);
    }

    public ActionListener voltar(){

        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ouvinte.actionPerformedVoltar(e, "Voltar");
            }
        };
    }

    public JTextField getEmail(){
        return email;
    }
    public JTextField getAssunto(){
        return assunto;
    }
    public JTextArea getAreaMensagem(){
        return areaMensagem;
    }
    public JButton getVoltar() {
        return voltar;
    }
    public JButton getEnviar() {
        return enviar;
    }
}
