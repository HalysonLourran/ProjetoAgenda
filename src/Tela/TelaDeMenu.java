package Tela;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import ApplicationAgenda.EnviarEmailTarefa;
import ApplicationAgenda.Hiato;
import Janelas.JanelaPadrao;
import Ouvinte.OuvinteTelaDeMenu;

public class TelaDeMenu extends JanelaPadrao {

    OuvinteTelaDeMenu ouvinte = new OuvinteTelaDeMenu(this);
	
	private JButton buttonExcluir;
	private JMenuBar jMenuBar;

	public TelaDeMenu(String titulo) {
		super(titulo);
		adicionarTitulo();
		excluirUsuario();
		adicionarMenu();
		tarefa();
		adicionarImageMenu();
		setVisible(true);
	}

	private void adicionarTitulo() {
		ImageIcon icon = new ImageIcon("src/Imagens/cats-removebg-preview.png");
		JLabel jLabel = new JLabel("TELA DE MENU",icon, JLabel.CENTER);
		jLabel.setBounds(0, 0, 700, 70);
		jLabel.setFont(new Font("Impact",Font.ITALIC, 30));
		jLabel.setOpaque(true);
		jLabel.setBackground(new Color(46,139,87));
		jLabel.setForeground(Color.WHITE);
		add(jLabel);
	}
	
	public void tarefa() {
		
		Timer timer = new Timer();
		EnviarEmailTarefa emailTarefa = new EnviarEmailTarefa();
		timer.scheduleAtFixedRate(emailTarefa, 0, 100000);
		System.out.println("TTT");
		
		Hiato hiato = new Hiato();
		Thread thread = new Thread(hiato);
		thread.start();
	}

	private void excluirUsuario() {

		buttonExcluir = new JButton("Excluir Usuário");
		buttonExcluir.setBounds(500, 370, 150, 30);
		buttonExcluir.setFont(new Font("Arial Black", Font.ITALIC, 14));
		buttonExcluir.setBackground(new Color(46,139,87));
		buttonExcluir.setForeground(Color.WHITE);
		buttonExcluir.addActionListener(excluir());
		add(buttonExcluir);
	}

	public ActionListener excluir() {
		return new ActionListener() {

			public void actionPerformed(ActionEvent e) {
                 ouvinte.actionPerformedExcluir(e);
			}
		};
	}
	
	
	public void adicionarImageMenu() {
		JLabel imagem = new JLabel();
        imagem.setIcon(new ImageIcon("src/Imagens/xx.png"));
        imagem.setBounds(0, 30, 700, 450);
        add(imagem);

		
	}

	private void adicionarMenu() {

		jMenuBar = new JMenuBar();
		setJMenuBar(jMenuBar);
		
		JMenu menuOp = new JMenu("Opçoes");
		menuOp.setFont(new Font("Arial Black", Font.ITALIC, 12));
		menuOp.setOpaque(true);
		menuOp.setBackground(new Color(46,139,87));
		menuOp.setForeground(new Color(46,139,87));
		jMenuBar.add(menuOp);

		JMenuItem cadastroDeCanal = new JMenuItem("Cadastrar Canal");
		cadastroDeCanal.setFont(new Font("Arial Black", Font.ITALIC, 12));
		cadastroDeCanal.setBackground(new Color(60,179,113));
		cadastroDeCanal.setForeground(Color.WHITE);
		menuOp.add(cadastroDeCanal);
		cadastroDeCanal.addActionListener(ouvinte);
		
		JMenuItem listarCanal = new JMenuItem("Listar Canal");
		menuOp.add(listarCanal);
		listarCanal.setFont(new Font("Arial Black", Font.ITALIC, 12));
		listarCanal.setBackground(new Color(46,139,87));
		listarCanal.setForeground(Color.WHITE);
		listarCanal.addActionListener(ouvinte);	
		
		JMenuItem cadastrarPrograma = new JMenuItem("Cadastrar Programas");
		menuOp.add(cadastrarPrograma);
		cadastrarPrograma.setFont(new Font("Arial Black", Font.ITALIC, 12));
		cadastrarPrograma.setBackground(new Color(60,179,113));
		cadastrarPrograma.setForeground(Color.WHITE);
		cadastrarPrograma.addActionListener(ouvinte);
		
		JMenuItem listarPrograma = new JMenuItem("Listar Programas");
		menuOp.add(listarPrograma);
		listarPrograma.setFont(new Font("Arial Black", Font.ITALIC, 12));
		listarPrograma.setBackground(new Color(46,139,87));
		listarPrograma.setForeground(Color.WHITE);
		listarPrograma.addActionListener(ouvinte);
		
		JMenuItem gerarPDF = new JMenuItem("Gerar PDF");
		menuOp.add(gerarPDF);
		gerarPDF.setFont(new Font("Arial Black", Font.ITALIC, 12));
		gerarPDF.setBackground(new Color(60,179,113));
		gerarPDF.setForeground(Color.WHITE);
		gerarPDF.addActionListener(ouvinte);
		
		JMenuItem foto = new JMenuItem("Foto");
		menuOp.add(foto);
		foto.setFont(new Font("Arial Black", Font.ITALIC, 12));
		foto.setBackground(new Color(46,139,87));
		foto.setForeground(Color.WHITE);
		foto.addActionListener(ouvinte);
		
		JMenuItem agenda = new JMenuItem("Minha Agenda");
		menuOp.add(agenda);
		agenda.setFont(new Font("Arial Black", Font.ITALIC, 12));
		agenda.setBackground(new Color(60,179,113));
		agenda.setForeground(Color.WHITE);
		agenda.addActionListener(ouvinte);
		
		JMenuItem email = new JMenuItem("Enviar Minha Agenda Por Email");
		menuOp.add(email);
		email.setFont(new Font("Arial Black", Font.ITALIC, 12));
		email.setBackground(new Color(46,139,87));
		email.setForeground(Color.WHITE);
		email.addActionListener(ouvinte);
		
	}

	public JButton getButtonExcluir() {
		return buttonExcluir;
	}
}
