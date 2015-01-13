package patlan;
import javax.swing.JPanel;

import java.awt.Toolkit;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Animar extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private Timer timer;
	private int x, y, dx, dy, maxHorizontal, maxVertical;
	private static int SPEED = 5;
	private Proceso m;

	public Animar(Proceso aMover, String lado, int n){
		m = aMover;
		x = 10; 
		y = 60;
		maxHorizontal = 140;
		maxVertical = 70 + (n * 50);
		
		if (lado.equals("Derecha"))		{ dx = SPEED;		dy = 0;				}
		if (lado.equals("Izquierda"))	{ dx = SPEED*-1; 	dy = 0;				}
		if (lado.equals("Arriba"))		{ dx = 0; 			dy = SPEED*-1;		}
		if (lado.equals("Abajo"))		{ dx = 0; 			dy = SPEED;			}

		// Timer
		timer = new Timer(5, this); // cada 5ms
		timer.start();
	}

	public void ubicar(){
		m.setBounds(x, y, 100, 40);
		// Timer
		Toolkit.getDefaultToolkit().sync();
	}
	
	public void actionPerformed(ActionEvent e){ // cada 5ms
		x+=dx;
		y+=dy;
		if(x > maxHorizontal|| x < 10)
			dx = 0;
		if(y > maxVertical|| y < 5)
			dy = 0;
			if(y == maxVertical){
				dx = SPEED;	
			}
		ubicar();
	}
}