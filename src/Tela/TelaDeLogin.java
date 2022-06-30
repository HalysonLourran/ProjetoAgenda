package Tela;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Janelas.JanelaPadrao;
import Ouvinte.OuvinteTelaDeLogin;

public class TelaDeLogin extends JanelaPadrao {
	
	OuvinteTelaDeLogin ouvinte = new OuvinteTelaDeLogin(this);

	private JTextField campoEmail;
	private JPasswordField campoSenha;
	private JButton buttonProseguir;
	private JButton buttonEsqueciSenha;

	public TelaDeLogin(String titulo) {
		super(titulo);
		adicionarTitulo();
		adicionarJLabel();
		adicionarJTextFiled();;
		adicionarButtonProseguir();
		adicionarButtonEsqueciSenha();
		setVisible(true);
	}

	private void adicionarTitulo() {
		ImageIcon icon = new ImageIcon("src/Imagens/cats-removebg-preview.png");
		JLabel jLabel = new JLabel("TELA DE LOGUIN", icon, JLabel.CENTER);
		jLabel.setBounds(0, 0, 700, 70);
		jLabel.setFont(new Font("Impact",Font.ITALIC, 30));
		jLabel.setOpaque(true);
		jLabel.setBackground(new Color(46,139,87));
		jLabel.setForeground(Color.WHITE);
		add(jLabel);
	}
	
	

	private void adicionarJLabel() {

		JLabel email = new JLabel("Email:", JLabel.CENTER);
		email.setBounds(30, 130, 95, 30);
		email.setFont(new Font("Arial Black", Font.ITALIC, 17));
		email.setOpaque(true);
		email.setBackground(new Color(46,139,87));
		email.setForeground(Color.WHITE);
		add(email);

		JLabel senha01 = new JLabel("Senha:", JLabel.CENTER);
		senha01.setBounds(30, 230, 95, 30);
		senha01.setFont(new Font("Arial Black", Font.ITALIC, 17));
		senha01.setOpaque(true);
		senha01.setBackground(new Color(46,139,87));
		senha01.setForeground(Color.WHITE);
		add(senha01);
	}
	
	private void adicionarJTextFiled() {
		
		campoEmail = new JTextField();
		campoEmail.setBounds(130, 130, 200, 30);
		add(campoEmail);
		
		campoSenha = new JPasswordField();
		campoSenha.setBounds(130, 230, 200, 30);
		add(campoSenha);
	}
	
	public void adicionarButtonProseguir() {

		buttonProseguir = new JButton("Prosseguir");
		buttonProseguir.setBounds(520, 400, 130, 30);
		buttonProseguir.addActionListener(proseguir());
		buttonProseguir.setFont(new Font("Arial Black", Font.ITALIC, 14));
		buttonProseguir.setBackground(new Color(46,139,87));
		buttonProseguir.setForeground(Color.WHITE);

		add(buttonProseguir);

	}
	
	public ActionListener proseguir() {
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				ouvinte.actionPerformed(e);
			}
		};
	}

	public void adicionarButtonEsqueciSenha() {

		buttonEsqueciSenha = new JButton("Esqueci Senha");
		buttonEsqueciSenha.setBounds(30, 400, 130, 30);
		buttonEsqueciSenha.setBackground(new Color(46,139,87));
		buttonEsqueciSenha.setForeground(Color.WHITE);
		buttonEsqueciSenha.setFont(new Font("Arial Black", Font.ITALIC, 13));
		buttonEsqueciSenha.addActionListener(senha());
		add(buttonEsqueciSenha);
		
	}
	
	public ActionListener senha() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ouvinte.actionPerformedSenha(e);
				
			}
		};
	}
	
	public JTextField getCampoEmail() {
		return campoEmail;
	}

	public JPasswordField getCampoSenha() {
		return campoSenha;
	}

	public JButton getButtonProseguir() {
		return buttonProseguir;
	}

	public JButton getButtonEsqueciSenha() {
		return buttonEsqueciSenha;
	}
}
