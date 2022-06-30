package Tela;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import Janelas.JanelaPadrao;
import Ouvinte.OuvinteTelaDeCadastroDeCanal;

public class TelaDeCadastroDeCanal extends JanelaPadrao {

	OuvinteTelaDeCadastroDeCanal ouvinte = new OuvinteTelaDeCadastroDeCanal(this);

	private JTextField campoNome;
	private JTextField campoLinkNumero;
	private JButton buttonVoltar;
	private JButton buttonSalvar;

	public TelaDeCadastroDeCanal(String titulo) {
		super(titulo);
		titulo();
		adicionarJLabel();
		adicionarJTextField();
		adicionarJButtonVoltar();
		adicionarJButtonSalvar();
		setVisible(true);

	}

	private void titulo() {
		JLabel jLabel = new JLabel("TELA DE CADASTRO DE CANAL", JLabel.CENTER);
		jLabel.setOpaque(true);
		jLabel.setBackground(Color.GRAY);
		jLabel.setBounds(0, 0, 700, 50);
		add(jLabel);
	}

	private void adicionarJLabel() {

		JLabel nomeDoCanal = new JLabel("Nome Do Canal: ");
		nomeDoCanal.setBounds(30, 100, 120, 30);
		add(nomeDoCanal);

	/*	JLabel tipoDoCanal = new JLabel("Tipo Do Canal: ");
		tipoDoCanal.setBounds(30, 140, 120, 30);
		add(tipoDoCanal);
    */
		JLabel formarDeAssistir = new JLabel("Link Ou Número Do Canal: ");
		formarDeAssistir.setBounds(30, 180, 170, 30);
		add(formarDeAssistir);

	}

	private void adicionarJTextField() {

		campoNome = new JTextField();
		campoNome.setBounds(200, 100, 200, 30);
		add(campoNome);

		campoLinkNumero = new JFormattedTextField();
		campoLinkNumero.setBounds(200, 180, 200, 30);
		add(campoLinkNumero);
	}

	private void adicionarJButtonVoltar() {

		buttonVoltar = new JButton("Voltar");
		buttonVoltar.setBounds(30, 400, 100, 30);
		buttonVoltar.addActionListener(voltar());
		add(buttonVoltar);
	}

	public ActionListener voltar() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ouvinte.actionPerformed(e);
			}
		};
	}

	private void adicionarJButtonSalvar() {

		buttonSalvar = new JButton("Salvar");
		buttonSalvar.setBounds(550, 400, 100, 30);
		buttonSalvar.addActionListener(salvar());
		add(buttonSalvar);
	}

	public ActionListener salvar() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ouvinte.actionPerformedsalvar(e);
			}
		};
	}

	public JTextField getCampoNome() {
		return campoNome;
	}

	public JTextField getCampoLinkNumero() {
		return campoLinkNumero;
	}

	public JButton getButtonVoltar() {
		return buttonVoltar;
	}

	public JButton getButtonSalvar() {
		return buttonSalvar;
	}
}
