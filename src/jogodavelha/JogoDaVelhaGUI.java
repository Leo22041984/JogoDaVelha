// Classe JogoDaVelhaGUI 
package jogodavelha;

import javax.swing.*;

public class JogoDaVelhaGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Jogo da Velha");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        TabuleiroPainel tabuleiro = new TabuleiroPainel();
        frame.add(tabuleiro);

        frame.setVisible(true);
    }
}
