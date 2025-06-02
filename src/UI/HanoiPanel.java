package UI;


import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import logic.Aro;
import logic.Tower;

public class HanoiPanel extends JPanel {
	private static final long serialVersionUID = 1L;
    private Tower tower;
    private int numDiscos;

    public HanoiPanel(Tower tower, int numDiscos) {
        this.tower = tower;
        this.numDiscos = numDiscos;
        setBackground(new Color(245, 245, 245));
        setPreferredSize(new Dimension(180, 320));
    }

    public void setTower(Tower tower) {
        this.tower = tower;
        repaint();
    }

    public void setNumDiscos(int numDiscos) {
        this.numDiscos = numDiscos;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        int baseY = height - 30;
        int postWidth = 8;
        int postHeight = height - 60;

        // Dibujar el poste
        g.setColor(new Color(160, 82, 45));
        g.fillRect((width - postWidth) / 2, baseY - postHeight, postWidth, postHeight);

        // Dibujar la base
        g.setColor(new Color(139, 69, 19));
        g.fillRect(15, baseY, width - 30, 10);

        // Dibujar los aros
        int diskHeight = Math.min(28, (postHeight - 10) / Math.max(numDiscos, 1));
        int level = 0;
        int[] palette = {
                0x3498db, 0xe74c3c, 0x2ecc71, 0xf1c40f, 0x9b59b6, 0x1abc9c, 0xe67e22, 0x34495e
        };

        java.util.List<Aro> disks = new java.util.ArrayList<>(tower.getTorre());
        for (int i = disks.size() - 1; i >= 0; i--) {
            Aro disk = disks.get(i);
            int diskSize = disk.getSize();
            int maxDiskWidth = width - 40;
            int minDiskWidth = 36;
            int diskWidth = minDiskWidth + (maxDiskWidth - minDiskWidth) * (diskSize - 1) / Math.max(numDiscos - 1, 1);

            int x = (width - diskWidth) / 2;
            int y = baseY - 10 - diskHeight * (level + 1);

            g.setColor(new Color(palette[(diskSize - 1) % palette.length]));
            g.fillRoundRect(x, y, diskWidth, diskHeight - 4, 18, 18);

            g.setColor(Color.DARK_GRAY);
            g.drawRoundRect(x, y, diskWidth, diskHeight - 4, 18, 18);

            // Número del disco 
            g.setColor(Color.WHITE);
            g.setFont(g.getFont().deriveFont(Font.BOLD, 15f));
            String txt = String.valueOf(diskSize);
            int strW = g.getFontMetrics().stringWidth(txt);
            g.drawString(txt, x + (diskWidth - strW) / 2, y + diskHeight / 2 + 4);

            level++;
        }
    }
}