package Janelas;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Janela extends JFrame {

	public Janela(String titulo) {

		setTitle(titulo);
		setSize(700, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addTema();
		setResizable(false);
	}

	private void addTema() {
		try {
			// AQUI VOCE SETA O NOME DA CLASSE REFERENTE A CADA TEMA !
			String tema = "com.jtattoo.plaf.aluminium.AluminiumLookAndFeel";

			// AQUI VC SETA O LOOK AND FEEL
			UIManager.setLookAndFeel(tema);
		} catch (InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException
				| ClassNotFoundException e) {
			System.out.println("Erro LAF : " + e.getMessage());
		}
	}
}
