package PersonalizedMessage;

import javax.swing.JOptionPane;

import entity.Canal;

public class MensagemCanal {

	public static void canalOpcaoInvalida() {
		JOptionPane.showConfirmDialog(null, "Op��o errada.");
	}

	public static void canalSalvo() {
		JOptionPane.showMessageDialog(null, "Canal Salvo Com Sucesso!");
	}

	public static void canalExcluido() {
		JOptionPane.showMessageDialog(null, "Canal Exclu�do Com Sucesso!");
	}

	public static void canalNaoEncontardo() {
		JOptionPane.showMessageDialog(null, "Canal N�o Encontrado!");
	}

	public static void canalAtualizado() {
		JOptionPane.showMessageDialog(null, "Canal Atualizado Com Sucesso!");
	}

	public static void detalharCanal(Canal canal) {
		JOptionPane.showMessageDialog(null, canal.toString());
	}
}
