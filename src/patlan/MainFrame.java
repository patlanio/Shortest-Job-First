package patlan;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;

//Timer Imports
import java.awt.Toolkit;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class MainFrame extends JFrame implements ActionListener{	
	private static final long serialVersionUID = 1L;
	private int numProcesosActivos, numProcesosCreados;
	private ArrayList<Proceso> cola;
	private Proceso ejecutando, menor;
	private Timer timer;
	
	public MainFrame() {
		
		try {
			JFrame.setDefaultLookAndFeelDecorated(true);
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			setBounds(700, 10, 275, 710);
			this.setResizable(false);
			this.setTitle("Shortest Job First");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			cola = new ArrayList<Proceso>();
			timer = new Timer(5, this);
			timer.start();
			
			JButton btnNuevoProceso = new JButton("Nuevo Proceso");
			btnNuevoProceso.setBounds(5, 5, 110, 110);
			btnNuevoProceso.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					agregarProceso();
				}
			});
			getContentPane().setLayout(null);
			getContentPane().add(btnNuevoProceso);
			
			PanelConFondo panelCPU = new PanelConFondo();
			panelCPU.setImagen("cpu.png");
			panelCPU.setBounds(120, 5, 110, 110);
			getContentPane().add(panelCPU);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void agregarProceso() {
		Proceso nuevo = new Proceso(numProcesosCreados);
		numProcesosActivos++;
		numProcesosCreados++;
		
		if(numProcesosActivos == 1){
			nuevo.iniciar();
			ejecutando = nuevo;
			menor = nuevo;
		}
		
		cola.add(nuevo);
		nuevo.setBounds(10, 10, 100, 40);
		getContentPane().add(nuevo);
		nuevo.animar("Abajo", numProcesosCreados);
		
		if (cola.size() == numProcesosActivos ){
			imprimir();
		}
	}
	
	public void buscarMenor(){
		menor = cola.get(0);
		int i=0;
		for (i = 0; i< cola.size(); i++){
			
			if(!cola.get(i).getEstado().equals("Terminado")){
				if(menor.getMinuto() <= cola.get(i).getMinuto() && cola.get(i).getSegundo() <= menor.getSegundo()){
					menor = cola.get(i);
				}
			}else{
				cola.remove(i);
				numProcesosActivos--;
				i=0;
			}
			
		}
		
		if (ejecutando!= menor){
			if (!ejecutando.getEstado().equals("Terminado")){
				ejecutando.bloquear();
			}
			menor.iniciar();
		}
		ejecutando = menor;
		
		Toolkit.getDefaultToolkit().sync();
	}
	
	public void imprimir(){
		for (int i = 0; i< cola.size(); i++){
			System.out.print(cola.get(i).getNombre() +" = "+ cola.get(i).getMinuto() + ":" + + cola.get(i).getSegundo() + " > ");
		}
		System.out.println();
	}

	public void actionPerformed(ActionEvent e){
		
		if(cola.size() > 0){
			buscarMenor();
		}
		
	}
}