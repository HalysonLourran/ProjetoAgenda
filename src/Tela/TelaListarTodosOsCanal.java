package Tela;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Janelas.JanelaPadrao;
import Model.CentralDeInformacoes;
import Model.Persistencia;
import Ouvinte.OuvinteTelaDeListarCanal;
import entity.Canal;

public class TelaListarTodosOsCanal extends JanelaPadrao {

	Persistencia persistencia = new Persistencia();
	CentralDeInformacoes centralDeInformacoes = persistencia.recuperarCentral();
	OuvinteTelaDeListarCanal ouvinte = new OuvinteTelaDeListarCanal(this);

	private JButton buttonVoltar;
	private JButton buttonExcluir;
	private JButton buttonAtualizar;
	private JButton buttonDetalhar;
	private JTextField campoBusca;

	public TelaListarTodosOsCanal(String titulo) {
		super(titulo);
		adicionarJLabel();
		adicionarJButtonVoltar();
		adicionarJButtonAtualizar();
		adicionarJButtonExcluir();
		adicionarJButtonDetalhar();
		listarCanal();
		setVisible(true);
	}

	private void adicionarJLabel() {

		JLabel jLabel = new JLabel("TELA DE LISTAR CANAL, EXCLUIR E ATUALIZAR", JLabel.CENTER);
		jLabel.setBackground(Color.GRAY);
		jLabel.setOpaque(true);
		jLabel.setBounds(0, 0, 700, 50);
		add(jLabel);
	}

	private void adicionarJButtonVoltar() {

		buttonVoltar = new JButton("Voltar");
		buttonVoltar.setBounds(30, 400, 100, 30);
		buttonVoltar.addActionListener(voltar());
		add(buttonVoltar);
	}

	public ActionListener voltar() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 ouvinte.actionPerformed(e);
			}
		};
	}

	private void adicionarJButtonAtualizar() {

		buttonAtualizar = new JButton("Atualizar");
		buttonAtualizar.setBounds(450, 400, 100, 30);
		buttonAtualizar.addActionListener(atualizar());
		add(buttonAtualizar);
	}

	public ActionListener atualizar() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 ouvinte.actionPerformedAtualizar(e);
			}
		};
	}

	private void adicionarJButtonExcluir() {

		buttonExcluir = new JButton("Excluir");
		buttonExcluir.setBounds(570, 400, 100, 30);
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
	
	private void adicionarJButtonDetalhar() {
		
		buttonDetalhar = new JButton("Detalhar");
		buttonDetalhar.setBounds(320, 400, 100, 30);
		buttonDetalhar.addActionListener(detalhar());
		add(buttonDetalhar);
	}
	
	public ActionListener detalhar() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ouvinte.actionPerformedDetalhar(e);
			}
		};
	}

	private void listarCanal() {

		DefaultTableModel modelo = new DefaultTableModel();

		modelo.addColumn("Nome");
		modelo.addColumn("Forma De Assistir");
		modelo.addColumn("Link Do Canal Ou Número");

		List<Canal> canais = centralDeInformacoes.getTodosOsCanais();

		Collections.sort(canais);

		Object[] linhas = new Object[canais.size()];
		for (Canal canal : canais) {
			Object[] linha = new Object[3];
			linha[0] = canal.getNome();
			linha[1] = canal.getTipoDoCanal();
			linha[2] = canal.getLinkOuCanal();
			modelo.addRow(linha);
		}

		JTable tabela = new JTable(modelo);
		JScrollPane painelTabela = new JScrollPane(tabela);
		painelTabela.setBounds(20, 60, 650, 190);
		add(painelTabela);
	}

	public JButton getButtonVoltar() {
		return buttonVoltar;
	}

	public JButton getButtonExcluir() {
		return buttonExcluir;
	}

	public JButton getButtonAtualizar() {
		return buttonAtualizar;
	}

	public JTextField getCampoBusca() {
		return campoBusca;
	}

	public JButton getButtonDetalhar() {
		return buttonDetalhar;
	}
}
