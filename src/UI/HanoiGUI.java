package UI;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import logic.Hanoi;

public class HanoiGUI extends JFrame {
	private static final long serialVersionUID = 1L;
    private Hanoi hanoi;
    private HanoiPanel[] towerPanels = new HanoiPanel[3];
    private JButton[] towerButtons = new JButton[3];
    private JLabel messageLabel = new JLabel(" ");
    private int selectedOrigin = -1;
    private int numDiscos;
    private JPanel mainPanel;
    private JPanel towersContainer;

    public HanoiGUI(int numDiscos) {
        this.numDiscos = numDiscos;
        this.hanoi = new Hanoi(numDiscos);

        setTitle("Torres de Hanoi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setMinimumSize(new Dimension(700, 500));
        setLocationRelativeTo(null);

        // Título
        JLabel titulo = new JLabel("T O R R E S   D E   H A N O I", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 26));
        titulo.setForeground(new Color(44, 62, 80));
        add(titulo, BorderLayout.NORTH);

        mainPanel = new JPanel(new BorderLayout(10, 10));
        towersContainer = new JPanel(new GridLayout(1, 3, 20, 0));
        towersContainer.setBackground(new Color(236, 240, 241));
        mainPanel.add(towersContainer, BorderLayout.CENTER);

        // Paneles visuales de torres
        for (int i = 0; i < 3; i++) {
            towerPanels[i] = new HanoiPanel(hanoi.getTorres().get(i), numDiscos);
            towersContainer.add(towerPanels[i]);
        }

        // Botones de selección
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 3, 20, 0));
        buttonsPanel.setBackground(new Color(236, 240, 241));
        for (int i = 0; i < 3; i++) {
            JButton btn = new JButton("Seleccionar");
            btn.setFont(new Font("Arial", Font.BOLD, 15));
            btn.setFocusPainted(false);
            final int idx = i;
            btn.addActionListener(e -> handleTowerClick(idx));
            towerButtons[i] = btn;
            buttonsPanel.add(btn);
        }
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);

        // Panel inferior: Mensajes y controles
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomPanel.setBackground(new Color(236, 240, 241));

        JButton reiniciarBtn = new JButton("Reiniciar");
        reiniciarBtn.setFont(new Font("Arial", Font.BOLD, 13));
        reiniciarBtn.setBackground(new Color(52, 152, 219));
        reiniciarBtn.setForeground(Color.WHITE);
        reiniciarBtn.addActionListener(e -> showRestartDialog());

        JButton cambiarBtn = new JButton("Cambiar discos");
        cambiarBtn.setFont(new Font("Arial", Font.BOLD, 13));
        cambiarBtn.setBackground(new Color(46, 204, 113));
        cambiarBtn.setForeground(Color.WHITE);
        cambiarBtn.addActionListener(e -> showChangeDisksDialog());

        messageLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        messageLabel.setForeground(new Color(52, 73, 94));
        bottomPanel.add(reiniciarBtn);
        bottomPanel.add(cambiarBtn);
        bottomPanel.add(messageLabel);

        add(bottomPanel, BorderLayout.SOUTH);

        updateTowers();
        setVisible(true);
    }

    private void handleTowerClick(int idx) {
        if (selectedOrigin == -1) {
            if (!hanoi.getTorres().get(idx).isEmpty()) {
                selectedOrigin = idx;
                messageLabel.setText("Selecciona la torre de destino...");
                highlightButton(idx, true);
            } else {
                messageLabel.setText("Selecciona una torre de origen válida.");
            }
        } else {
            if (selectedOrigin == idx) {
                selectedOrigin = -1;
                messageLabel.setText("Origen cancelado.");
                highlightButton(idx, false);
                return;
            }
            boolean ok = hanoi.moverDisco(selectedOrigin, idx);
            if (ok) {
                messageLabel.setText("Movimiento realizado.");
                highlightButton(selectedOrigin, false);
                selectedOrigin = -1;
                updateTowers();
                if (hanoi.completado()) {
                    JOptionPane.showMessageDialog(this, "¡Has ganado!\nMovimientos mínimos: " + ((int) Math.pow(hanoi.getNumDiscos(), 2) - 1), "Victoria", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                messageLabel.setText("Movimiento inválido. Intenta otra vez.");
                highlightButton(selectedOrigin, false);
                selectedOrigin = -1;
            }
        }
    }

    private void highlightButton(int idx, boolean on) {
        for (int i = 0; i < 3; i++) {
            towerButtons[i].setBackground(on && i == idx ? new Color(241, 196, 15) : UIManager.getColor("Button.background"));
            towerButtons[i].setForeground(on && i == idx ? Color.BLACK : Color.DARK_GRAY);
        }
    }

    private void updateTowers() {
        for (int i = 0; i < 3; i++) {
            towerPanels[i].setTower(hanoi.getTorres().get(i));
            towerPanels[i].setNumDiscos(hanoi.getNumDiscos());
        }
        repaint();
    }

    private void showRestartDialog() {
        int opt = JOptionPane.showConfirmDialog(this, "¿Reiniciar con el mismo número de discos?", "Reiniciar", JOptionPane.YES_NO_OPTION);
        if (opt == JOptionPane.YES_OPTION) {
            hanoi.reiniciar(numDiscos);
            selectedOrigin = -1;
            messageLabel.setText("Juego reiniciado.");
            highlightButton(0, false);
            highlightButton(1, false);
            highlightButton(2, false);
            updateTowers();
        }
    }

    private void showChangeDisksDialog() {
        Integer nuevo = null;
        while (nuevo == null) {
            String val = JOptionPane.showInputDialog(this, "¿Cuántos discos quieres? (3-8 recomendado)", numDiscos);
            if (val == null) return;
            try {
                int n = Integer.parseInt(val.trim());
                if (n > 0 && n < 16) {
                    nuevo = n;
                } else {
                    JOptionPane.showMessageDialog(this, "Elige un número entre 1 y 15.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Introduce un número válido.");
            }
        }
        numDiscos = nuevo;
        hanoi.reiniciar(numDiscos);
        selectedOrigin = -1;
        messageLabel.setText("¡Juego reiniciado con " + numDiscos + " discos!");
        highlightButton(0, false);
        highlightButton(1, false);
        highlightButton(2, false);
        updateTowers();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            int numDiscos = 3;
            while (true) {
                String input = JOptionPane.showInputDialog(null, "Introduce el número de discos (3-8 recomendado):", "Torres de Hanoi", JOptionPane.QUESTION_MESSAGE);
                if (input == null) System.exit(0);
                try {
                    numDiscos = Integer.parseInt(input.trim());
                    if (numDiscos > 0 && numDiscos < 16) break;
                    else JOptionPane.showMessageDialog(null, "Elige un número entre 1 y 15.");
                } catch (Exception ignored) {
                    JOptionPane.showMessageDialog(null, "Introduce un número válido.");
                }
            }
            new HanoiGUI(numDiscos);
        });
    }
}