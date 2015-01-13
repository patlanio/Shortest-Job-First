package patlan;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

public class Proceso extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L;
	private Thread cronometro;
	private JLabel lblTiempo, lblP;
	private int min = 0, seg = 0, retardo = 1000;
	private String estado = "Listo";

	public Proceso(int num) {
		setBorder(UIManager.getBorder("Button.border"));
		setLayout(null);
		setBackground(Color.white);
		
		lblP = new JLabel("Proceso "+num);
		lblP.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblP.setBounds(2, 2, 90, 25);
		add(lblP);
		
		setTiempoRandom();
		lblTiempo = new JLabel("Restante: "+min+":"+seg);
		lblTiempo.setBounds(2, 25, 90, 15);
		add(lblTiempo);
		
		System.out.println("Listo   > "+lblP.getText()+" en "+min+":"+seg);
		cronometro = new Thread(this);
	}

	public void run() {
		while (estado.equals("Ejecutando")){
			
			for (int minutos = min; minutos >=0; minutos--){
				for (int segundos = seg; segundos >=0; segundos--){
					min = minutos; seg = segundos;
					lblTiempo.setText("Restante: "+min+":"+seg);
					//System.out.println(lblP.getText()+ " > Restante = " +min+":"+seg);
					
					if (estado.equals("Bloqueado")) {
						segundos++;
						if (segundos == 60){
							segundos = 0;
							minutos ++;
						}
					}
					
					if (minutos == 0 && segundos == 0) {
						parar();
						//this.setVisible(false);
					}
					
					try {
						cronometro.sleep(retardo);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
		}
	}
	
	
	private void setTiempoRandom(){
		seg = 5 + (int) (Math.random()*	20);
		min = (int) (Math.random()*1);
	}
	
	public int getMinuto(){
		return min;
	}
	
	public int getSegundo(){
		return seg;
	}
	
	public String getNombre(){
		return lblP.getText();
	}
	
	public String getEstado(){
		return estado;
	}
	
	public void iniciar(){
		if(estado.equals("Listo")) cronometro.start();
		estado = "Ejecutando";
		setBackground(Color.GREEN);
		System.out.println("Inicia  > "+lblP.getText()+" en "+min+":"+seg);
	}
	
	public void parar(){
		estado = "Terminado";
		setBackground(Color.RED);
		System.out.println("Termina > "+lblP.getText());
	}
	
	public void bloquear(){
		estado = "Bloqueado";
		setBackground(Color.ORANGE);
		System.out.println("Bloqueo  > "+lblP.getText()+" en "+min+":"+seg);
	}
	
	public void esperar(int m, int s){
		try {
			Thread.sleep(m*60000+s*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void animar(String lado, int n){
		Animar a = new Animar (this, lado, n);
	}
	
}
