package Tela;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import Janelas.JanelaTelaCadastroDePrograma;
import Model.CentralDeInformacoes;
import Model.Persistencia;
import Ouvinte.OuvinteTelaDeCadastroDeProgramaDeRealityShows;
import Ouvinte.OuvinteTelaDeCadastroDeProgramaSeriesRegulares;
import Ouvinte.OuvinteTelaEditarProgramaDeRealityShows;
import entity.Canal;
import entity.ProgramaDeRealityShows;

public class TelaEditarProgramaDeRealityShows extends JanelaTelaCadastroDePrograma {

	Persistencia persistencia = new Persistencia();
	CentralDeInformacoes centralDeInformacoes = persistencia.recuperarCentral();
	OuvinteTelaEditarProgramaDeRealityShows ouvinte = new OuvinteTelaEditarProgramaDeRealityShows(this);

	private JTextField campoNomeDoPrograma;
	private JTextField campoIDCanal;
	private JTextField campoDiasDaSemana;
	private JTextField campoApresentador;
	private JTextField campoTemporada;
	private JFormattedTextField campoHorario;
	private JButton buttonVoltar;
	private JButton buttonSalvar;
	private ProgramaDeRealityShows programaDeRealityShows;
	private JTextField campoIDPrograma;

	public TelaEditarProgramaDeRealityShows(String titulo, ProgramaDeRealityShows programaDeRealityShows) {
		super(titulo);
		this.programaDeRealityShows = programaDeRealityShows;
		adicionarTitulo();
		listarPrograma();
		adicionarJLabel();
		adicionarJTextFild();
		adicionarButtonVoltar();
		adicionarButtonSalvar();
		setVisible(true);
	}

	private void adicionarTitulo() {
		JLabel jLabel = new JLabel("CADASTRO DE PROGRAMA", JLabel.CENTER);
		jLabel.setBounds(0, 0, 900, 50);
		jLabel.setBackground(Color.GRAY);
		jLabel.setOpaque(true);
		add(jLabel);
	}

	private void listarPrograma() {

		DefaultTableModel modelo = new DefaultTableModel();

		modelo.addColumn("ID");
		modelo.addColumn("Nome");
		modelo.addColumn("Tipo De Canal");
		modelo.addColumn("Link Ou Número Do Canal");

		List<Canal> canais = centralDeInformacoes.getTodosOsCanais();

		Collections.sort(canais);

		Object[] linhas = new Object[canais.size()];
		for (Canal canal : canais) {
			Object[] linha = new Object[4];
			linha[0] = canal.getId();
			linha[1] = canal.getNome();
			linha[2] = canal.getTipoDoCanal();
			linha[3] = canal.getLinkOuCanal();
			modelo.addRow(linha);
		}

		JTable tabela = new JTable(modelo);
		JScrollPane painelTabela = new JScrollPane(tabela);
		painelTabela.setBounds(40, 100, 800, 190);
		add(painelTabela);

	}

	private void adicionarJLabel() {

		JLabel nome = new JLabel("Nome Do Programa: ");
		nome.setBounds(40, 300, 130, 30);
		add(nome);

		JLabel id = new JLabel("ID Do Canal: ");
		id.setBounds(40, 350, 130, 30);
		add(id);

		JLabel data = new JLabel("Dia/s Do Programa: ");
		data.setBounds(40, 400, 130, 30);
		add(data);

		JLabel horario = new JLabel("Horario Do Programa: ");
		horario.setBounds(40, 450, 130, 30);
		add(horario);
		
		JLabel apresentador = new JLabel("Nome Do/s Apresentado/res: ");
		apresentador.setBounds(40, 500, 130, 30);
		add(apresentador);
		
		JLabel temporada = new JLabel("Temporada: ");
		temporada.setBounds(40, 550, 130, 30);
		add(temporada);
		
	}

	private void adicionarJTextFild() {

		campoNomeDoPrograma = new JTextField();
		campoNomeDoPrograma.setBounds(200, 300, 200, 30);
		campoNomeDoPrograma.setText(this.programaDeRealityShows.getNome());
		add(campoNomeDoPrograma);

		campoIDCanal = new JTextField();
		campoIDCanal.setBounds(200, 350, 200, 30);
		campoIDCanal.setText(String.valueOf(this.programaDeRealityShows.getCanal().getId()));
		add(campoIDCanal);

		campoDiasDaSemana = new JTextField();
		campoDiasDaSemana.setBounds(200, 400, 200, 30);
	//	campoDiasDaSemana.setText(this.programaDeRealityShows.getDias());
		add(campoDiasDaSemana);
		
		try {
			campoHorario = new JFormattedTextField(new MaskFormatter("##:##"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		campoHorario.setBounds(200, 450, 70, 30);
		campoHorario.setText(this.programaDeRealityShows.getHorario());
		add(campoHorario);
		
		campoApresentador = new JTextField();
		campoApresentador.setBounds(200, 500, 200, 30);
		campoApresentador.setText(this.programaDeRealityShows.getNomeDosApresentadores());
		add(campoApresentador);
		
		campoTemporada = new JTextField();
		campoTemporada.setBounds(200, 550, 200, 30);
		campoTemporada.setText(this.programaDeRealityShows.getTemporada());
		add(campoTemporada);
		
		campoIDPrograma = new JTextField();
		campoIDPrograma.setText(String.valueOf(this.programaDeRealityShows.getId()));
	}

	private void adicionarButtonVoltar() {

		buttonVoltar = new JButton("Voltar");
		buttonVoltar.setBounds(40, 600, 100, 30);
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

	private void adicionarButtonSalvar() {

		buttonSalvar = new JButton("Salvar");
		buttonSalvar.setBounds(720, 600, 100, 30);
		buttonSalvar.addActionListener(salvar());
		add(buttonSalvar);
	}
	

	public ActionListener salvar() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ouvinte.actionPerformedSalvar(e);
			}
		};
	}

	public JTextField getCampoNomeDoPrograma() {
		return campoNomeDoPrograma;
	}

	public JTextField getCampoIDCanal() {
		return campoIDCanal;
	}


	public JTextField getCampoDiasDaSemana() {
		return campoDiasDaSemana;
	}

	public JFormattedTextField getCampoHorario() {
		return campoHorario;
	}

	public JButton getButtonVoltar() {
		return buttonVoltar;
	}

	public JButton getButtonSalvar() {
		return buttonSalvar;
	}

	public JTextField getCampoApresentador() {
		return campoApresentador;
	}

	public JTextField getCampoTemporada() {
		return campoTemporada;
	}

	public JTextField getCampoIDPrograma() {
		return campoIDPrograma;
	}
	
}
