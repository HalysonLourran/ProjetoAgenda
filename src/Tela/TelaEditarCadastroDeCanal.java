package Tela;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Janelas.JanelaPadrao;
import Ouvinte.OuvinteTelaEditarCadastroDeCanal;
import entity.Canal;

public class TelaEditarCadastroDeCanal extends JanelaPadrao {

	OuvinteTelaEditarCadastroDeCanal ouvinte = new OuvinteTelaEditarCadastroDeCanal(this);
	
	private JTextField campoNome;
	private JTextField campoFormaDeAssistir;
	private JTextField campoNumeroOuLink;
	private JTextField campoid;
	private JButton buttonSalvar;
	private JButton buttonVoltar;
	private Canal canal;
	
	public TelaEditarCadastroDeCanal(String titulo, Canal canal) {
		super(titulo);
		this.canal = canal;
		setVisible(true);
		adicionarTitulo();
		adicionarJLabel();
		adicionarJTextFiled();
		adicionarJButtonsalvar();
		adicionarJButtonVoltar();
	}
	
	private void adicionarTitulo() {
		
		JLabel jLabel = new JLabel("TELA DE CADASTRO DE CANAL", JLabel.CENTER);
		jLabel.setBounds(0, 0, 700, 50);
		jLabel.setBackground(Color.GRAY);
		jLabel.setOpaque(true);
		add(jLabel);
	}
	
	private void adicionarJLabel() {
		
		JLabel nome = new JLabel("Nome Do Canal: ");
		nome.setBounds(30, 80, 140, 40);
		add(nome);
		
	/*	JLabel formaDeAssitir = new JLabel("Forma De Assistir: "); 
		formaDeAssitir.setBounds(30, 140, 140, 40);
		add(formaDeAssitir);
	*/	
	    JLabel canalOuLink = new JLabel("Número Do Canal Ou LinK: ");
		canalOuLink.setBounds(30, 200, 160, 40);
     	add(canalOuLink);
	}

	private void adicionarJTextFiled() {
		
		campoNome = new JTextField();
		campoNome.setBounds(200, 80, 200, 40);
		campoNome.setText(this.canal.getNome());
		add(campoNome);
		
	/*	campoFormaDeAssistir = new JTextField();
		campoFormaDeAssistir.setBounds(200, 140, 200, 40);
		campoFormaDeAssistir.setText(this.canal.getTipoDoCanal());
		add(campoFormaDeAssistir);
	*/	
		campoNumeroOuLink = new JTextField();
		campoNumeroOuLink.setBounds(200, 200, 200, 40);
		campoNumeroOuLink.setText(this.canal.getLinkOuCanal());
		add(campoNumeroOuLink);
		
		campoid = new JTextField();
		campoid.setText(String.valueOf(this.canal.getId()));
	}
	
	public void adicionarJButtonsalvar() {
		
		buttonSalvar = new JButton("Salvar");
		buttonSalvar.setBounds(540, 390, 100, 30);
		buttonSalvar.addActionListener(salvar());
		add(buttonSalvar);
		
	}
	
	public ActionListener salvar() {
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				ouvinte.actionPerformed(e);
			}
		};
	}
	
	public void adicionarJButtonVoltar() {
		
		buttonVoltar = new JButton("Voltar");
		buttonVoltar.setBounds(30, 390, 100, 30);
		buttonVoltar.addActionListener(voltar());
		add(buttonVoltar);
	}
	
	public ActionListener voltar(){
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				ouvinte.actionPerformedVoltar(e);
			}
		};
	}
	
	public JTextField getCampoNome() {
		return campoNome;
	}

	public JTextField getCampoFormaDeAssistir() {
		return campoFormaDeAssistir;
	}

	public JTextField getCampoNumeroOuLink() {
		return campoNumeroOuLink;
	}

	public JButton getButtonSalvar() {
		return buttonSalvar;
	}

	public JButton getButtonVoltar() {
		return buttonVoltar;
	}

	public JTextField getCampoid() {
		return campoid;
	}

	public void setCampoid(JTextField campoid) {
		this.campoid = campoid;
	}
}

