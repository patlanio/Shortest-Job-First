package patlan;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelConFondo extends JPanel {
	private static final long serialVersionUID = -708703562328002253L;

	private Image imagen;

	public PanelConFondo() {
		setPreferredSize(new Dimension(100,100));
	}

	public PanelConFondo(String nombreImagen) {
		if (nombreImagen != null) {
			imagen = new ImageIcon(getClass().getResource(nombreImagen)).getImage();
		}
	}

	public PanelConFondo(Image imagenInicial) {
		if (imagenInicial != null) {
			imagen = imagenInicial;
		}
	}

	public void setImagen(String nombreImagen) {
		if (nombreImagen != null) {
			imagen = new ImageIcon(getClass().getResource(nombreImagen)).getImage();
		} else {
			imagen = null;
		}

		repaint();
	}

	public void setImagen(Image nuevaImagen) {
		imagen = nuevaImagen;

		repaint();
	}

	public void paint(Graphics g) {
		if (imagen != null) {
			g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

			setOpaque(false);
		} else {
			setOpaque(true);
		}

		super.paint(g);
	}
}