import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.beans.*;
import java.util.Random;

class Dado{
  private Integer resultado;

  public void lanzarDado(){
    Random ran = new Random();
    resultado = ran.nextInt(6) + 1;
  }

  public Integer valor() {
    return resultado;
  }
}

class Juego{
  public Boolean inicio = Boolean.TRUE;
  public Integer sum;
  public Integer punto;

  public void iniciar(){
    Dado dado1 = new Dado();
    Dado dado2 = new Dado();

    JFrame f = new JFrame();

    int origen_x = 300;
    int origen_y = 300;
    int dimension_boton = 100;

    JLabel d1 = new JLabel(), d2 = new JLabel();

    d1.setIcon(new ImageIcon("dado.jpg"));
    d1.setBounds(80, 50, 200, 200);
    d2.setIcon(new ImageIcon("dado.jpg"));
    d2.setBounds(300, 50, 200, 200);

    f.add(d1);
    f.add(d2);

    ActionListener procesar_clicks = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        dado1.lanzarDado();
        dado2.lanzarDado();
        d1.setIcon(new ImageIcon(dado1.valor() + ".png"));
        d2.setIcon(new ImageIcon(dado2.valor() + ".png"));

        sum = dado1.valor() + dado2.valor();

        if (inicio) {
          inicio = Boolean.FALSE;
          if (sum == 2 || sum == 3 || sum == 12) {
            JOptionPane.showMessageDialog(null, "Perdites, vuelve a intentarlo", "PERDEDOR",
                JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
          } else if (sum == 7 || sum == 11) {
            JOptionPane.showMessageDialog(null, "¡¡Ganaste!! Buena suerte", "GANADOR", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
          } else {
            JLabel txt = new JLabel();
            punto = sum;
            JOptionPane.showMessageDialog(null, "Sacaste punto. Continúa lanzando", "PUNTO",
                JOptionPane.INFORMATION_MESSAGE);
            txt.setText("PUNTO= " + punto);
            txt.setFont(new Font("courier", Font.BOLD, 40));
            txt.setBounds(175, 300, 250, 80);
            f.add(txt);
          }
        } else {
          if (sum == punto) {
            JOptionPane.showMessageDialog(null, "¡¡Ganaste!! Buena suerte", "GANADOR", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
          } else if (sum == 7) {
            JOptionPane.showMessageDialog(null, "Perdites, vuelve a intentarlo", "PERDEDOR",
                JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
          } else {
            JOptionPane.showMessageDialog(null, "Tienes otro intento. Continúa lanzando", "OTRO INTENTO",
                JOptionPane.INFORMATION_MESSAGE);
          }
        }
        f.repaint();
        f.revalidate();
      }
    };

    JButton b = new JButton();
    b.setText("LANZAR");
    b.setBounds(origen_x - dimension_boton / 2, 2 * origen_y - (3 / 2) * dimension_boton, dimension_boton,
        dimension_boton / 2);
    b.addActionListener(procesar_clicks);

    f.add(b);

    f.setSize(600, 600);
    f.setLayout(null);
    f.setVisible(true);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}

public class Main{
  public static void main(String[] args) {
    Juego dados = new Juego();
    dados.iniciar();
  }
}
