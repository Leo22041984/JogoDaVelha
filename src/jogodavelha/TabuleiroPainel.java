// Classe TabuleiroPainel 
package jogodavelha;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TabuleiroPainel extends JPanel implements ActionListener {
    private JogoDaVelha jogo;
    private JButton[][] botoes;
    private JButton reiniciarButton;

    public TabuleiroPainel() {
        jogo = new JogoDaVelha();
        setLayout(new BorderLayout());

        JPanel botoesPanel = new JPanel();
        botoesPanel.setLayout(new GridLayout(3, 3));
        botoes = new JButton[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botoes[i][j] = new JButton("");
                botoes[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                botoes[i][j].setFocusPainted(false);
                botoes[i][j].addActionListener(this);
                botoesPanel.add(botoes[i][j]);
            }
        }

        reiniciarButton = new JButton("Reiniciar Jogo");
        reiniciarButton.setFont(new Font("Arial", Font.PLAIN, 20));
        reiniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reiniciarJogo(); // Metodo para reiniciar o jogo a qualquer momento da partida.
            }
        });

        add(botoesPanel, BorderLayout.CENTER);
        add(reiniciarButton, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton botaoClicado = (JButton) e.getSource();
        int linha = -1, coluna = -1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (botaoClicado == botoes[i][j]) {
                    linha = i;
                    coluna = j;
                    break;
                }
            }
        }

        if (linha != -1 && coluna != -1) {
            if (jogo.fazerJogada(linha, coluna)) {
                botaoClicado.setText(Character.toString(jogo.getJogadorAtual()));
                char vencedor = jogo.verificarVencedor(); // chamada do metodo para verificar o ganhador.

                if (vencedor != ' ') {
                    String mensagem;
                    if (vencedor == 'T') {
                        mensagem = "Empate!";
                    } else {
                        mensagem = "Parabéns você venceu!";
                    }
                    JOptionPane.showMessageDialog(this, mensagem);
                    // Desabilita todos os botões após o jogo terminar
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            botoes[i][j].setEnabled(false);
                        }
                    }
                } else if (jogo.tabuleiroCheio()) { // Chamada de metodo para verificar se tabuleiro esta cheio.
                    JOptionPane.showMessageDialog(this, "Empate!");
                }
            }
        }
    }

    public void reiniciarJogo() {
        jogo.inicializarTabuleiro();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botoes[i][j].setText("");
                botoes[i][j].setEnabled(true);
            }
        }
    }
}
      