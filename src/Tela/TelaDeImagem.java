package Tela;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.itextpdf.text.List;

import Janelas.Janela;
import Model.CentralDeInformacoes;
import Model.Persistencia;
public class TelaDeImagem extends Janela {

	Persistencia persistencia = new Persistencia();
	CentralDeInformacoes centralDeInformacoes = persistencia.recuperarCentral();

	public TelaDeImagem(String titulo) {
		super(titulo);
		adicionarComponenteLista();
		adicionarComponenteLista();
		setVisible(true);
	}

	public void adicionarComponenteLista() {

		String nome[] = new String[1000];
		JList lista = new JList(nome);

		JLabel imagem = new JLabel();
		
		for(int i = 0; i < centralDeInformacoes.getTodosOsProgramas().size(); i++) {
		   nome[i] = centralDeInformacoes.getTodosOsProgramas().get(i).getNome();
		}
		
		add(BorderLayout.WEST, lista);
		add(BorderLayout.CENTER, imagem);
		
		lista.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int index = lista.getSelectedIndex();
				String nomePrograma = nome[index];
				imagem.setIcon(new ImageIcon(getClass().getResource("/PastaPrograma/"+nomePrograma+".png")));
			}
		});
	}
}
