package Tela;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import Janelas.JanelaPadrao;
import Ouvinte.OuvinteTelaGerarPDF;

public class TelaGerarPDF extends JanelaPadrao {

	OuvinteTelaGerarPDF ouvinte = new OuvinteTelaGerarPDF(this);

	private JButton buttonGerarPDF;
	private JButton buttonVoltar;

	public TelaGerarPDF(String titulo) {
		super(titulo);
		adicionarTitulo();
		adicionarJButtonPDF();
		adicionarJButtonVoltar();
		setVisible(true);
	}

	private void adicionarTitulo() {

		JLabel titulo = new JLabel("TELA GERAR PDF", JLabel.CENTER);
		titulo.setBackground(Color.GRAY);
		titulo.setOpaque(true);
		titulo.setBounds(0, 0, 700, 50);
		add(titulo);

	}

	private void adicionarJButtonPDF() {

		buttonGerarPDF = new JButton("Gerar PDF De Todos Os Programas");
		buttonGerarPDF.setBounds(200, 200, 300, 30);
		buttonGerarPDF.addActionListener(gerarPDF());
		add(buttonGerarPDF);
	}

	public ActionListener gerarPDF() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ouvinte.actionPerformedGerarPDF(e);
			}
		};
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

	public JButton getButtonGerarPDF() {
		return buttonGerarPDF;
	}
}
