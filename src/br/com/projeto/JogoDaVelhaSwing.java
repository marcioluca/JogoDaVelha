package br.com.projeto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JogoDaVelhaSwing extends JFrame implements ActionListener {

    private JButton[][] botoes = new JButton[3][3];
    private boolean jogadorX = true;
    private int jogadas = 0;

    public JogoDaVelhaSwing() {
        setTitle("Jogo da Velha");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        inicializarBotoes();

        setVisible(true);
    }

    private void inicializarBotoes() {
        Font fonte = new Font("Arial", Font.BOLD, 60);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botoes[i][j] = new JButton("");
                botoes[i][j].setFont(fonte);
                botoes[i][j].addActionListener(this);
                add(botoes[i][j]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton click = (JButton) e.getSource();

        if (!click.getText().equals("")) {
            return; // Casa já preenchida
        }

        click.setText(jogadorX ? "X" : "O");
        jogadas++;

        if (verificarVencedor()) {
            JOptionPane.showMessageDialog(this, "Jogador " + (jogadorX ? "X" : "O") + " venceu!");
            reiniciarJogo();
        } else if (jogadas == 9) {
            JOptionPane.showMessageDialog(this, "Empate!");
            reiniciarJogo();
        }

        jogadorX = !jogadorX;
    }

    private boolean verificarVencedor() {
        String simbolo = jogadorX ? "X" : "O";

        // Verifica linhas, colunas e diagonais
        for (int i = 0; i < 3; i++) {
            if (botoes[i][0].getText().equals(simbolo) &&
                    botoes[i][1].getText().equals(simbolo) &&
                    botoes[i][2].getText().equals(simbolo)) return true;

            if (botoes[0][i].getText().equals(simbolo) &&
                    botoes[1][i].getText().equals(simbolo) &&
                    botoes[2][i].getText().equals(simbolo)) return true;
        }

        if (botoes[0][0].getText().equals(simbolo) &&
                botoes[1][1].getText().equals(simbolo) &&
                botoes[2][2].getText().equals(simbolo)) return true;

        if (botoes[0][2].getText().equals(simbolo) &&
                botoes[1][1].getText().equals(simbolo) &&
                botoes[2][0].getText().equals(simbolo)) return true;

        return false;
    }

    private void reiniciarJogo() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botoes[i][j].setText("");
            }
        }
        jogadas = 0;
        jogadorX = true;
    }

    public static void main(String[] args) {
        new JogoDaVelhaSwing();
    }
}
