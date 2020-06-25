package api;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Stupid {
    static int width = 200;
    static int height = 400;
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.GREEN);
                g.fillRect(0, 0, width, height);
                int rectanglewidth = width - 60;
                int rectangleheight = height - 60;

                int longHorizontal = (rectangleheight * 2 >= rectanglewidth) ? 0 : 1;
                int longVertical = 1 - longHorizontal;

                int hWidth = (int)(rectangleheight * 2);
                int vHeight = (int)(0.5 * rectanglewidth);
                int hPosition = (int)(0.5 * width - 0.5 * hWidth);
                int vPosition = (int)(0.5 * height - 0.5 * vHeight);

                int cHPosition = hPosition * longHorizontal + 30 * longVertical;
                int cVPosition = 30 * longHorizontal + vPosition * longVertical;
                int cWidth = hWidth * longHorizontal + rectanglewidth * longVertical;
                int cHeight = rectangleheight * longHorizontal + vHeight * longVertical;

                g.setColor(Color.RED);
                g.fillRect(cHPosition + (int)(0.33 * cWidth),  cVPosition, (int)(0.67 * cWidth), cHeight);
                System.out.println(String.format("cHPosition: %d cVPosition: %d cWidth %d cHeight %d", cHPosition, cVPosition, cWidth, cHeight));
                g.setColor(Color.WHITE);
                g.fillRect(cHPosition,cVPosition, (int)(0.33 * cWidth), cHeight);
                for(int i = 0; i < 9; i++) {
                    g.fillPolygon(new int[]{cHPosition + (int)(0.33 * cWidth), cHPosition + (int)(0.33 * cWidth), cHPosition + (int)(cWidth * 0.43)}, new int[]{(int)(cVPosition + i * cHeight / 9.0), (int)(cVPosition + (i + 1)*cHeight / 9.0), (int)(cVPosition + (1/18.0 + i/9.0) * cHeight)}, 3);
                }
            }
        };
        panel.setPreferredSize(new Dimension(width, height));
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                System.out.println(String.format("width: %d, height: %d", ((JFrame)e.getComponent()).getContentPane().getWidth(), ((JFrame)e.getComponent()).getContentPane().getHeight()));
                width = ((JFrame)e.getComponent()).getContentPane().getWidth();
                height = ((JFrame)e.getComponent()).getContentPane().getHeight();
                frame.repaint();
            }
        });
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }


}
