package Interfaz;

	import java.awt.Color;
	import java.awt.Graphics;

	import javax.swing.ImageIcon;
	import javax.swing.JLabel;
	import javax.swing.JPanel;
	import javax.swing.border.LineBorder;

	import Logica.Particion;

	@SuppressWarnings("serial")
	public class PanelDibujoMem extends JPanel{
		
		private Particion[] particiones;
		private int memoriaPpal = 0;

		public PanelDibujoMem(Particion[] particiones, int memoriaPpal) {
			this.particiones = particiones;
			this.memoriaPpal = memoriaPpal;
			//setBorder(new LineBorder(new Color(0, 0, 0)));
			//setBackground(verde);
			
		}
		
		private Color verde = new Color(86, 186, 7);
		private Color rojo = new Color(230, 20, 20);
		private Color negro = new Color(0, 0, 0);
		
		public void paint(Graphics g) {
			double tamanoOcupPorcen = (calcularTamOcupado()*100.0)/memoriaPpal;
			
			int drawOcupado = (int) (getHeight()*(tamanoOcupPorcen/100));
			
	    	g.setColor(verde);
	    	g.fillRect(0, 0, getWidth(), getHeight());
	    	
	    	g.setColor(rojo);
	    	g.fillRect(0, getHeight()-drawOcupado, getWidth(), getHeight());
	    	
	    	g.setColor(negro);
	    	g.drawRect(0, 0, getWidth()-1, getHeight());
		}
		
		
		public int calcularTamOcupado() {
			int tam = 0;
			for(int i=0; i<particiones.length; i++) {
				if(particiones[i].getDisponible() == false) {
					tam += particiones[i].getTamano();
				}
			}
			return tam;
		}

		public int calcularFragmentacionInterna() {
			int fragmentacion = 0;
			for (int i=1; i<particiones.length; i++) {
				if(particiones[i].getDisponible() == false) {
					fragmentacion += particiones[i].getTamano()-particiones[i].getProceso().getTamano();
				}
			}
			return fragmentacion;
		}

		public int calcularFragmentacionExterna() {
			int fragmentacion = 0;
			for (int i=1; i<particiones.length; i++) {
				if(particiones[i].getDisponible() == true && particiones[i].getTamano() < 6144) {
					fragmentacion += particiones[i].getTamano();
				}
			}
			return fragmentacion;
		}

		public void imprimir() {
			System.out.println("///////////////");
			for(int i=0; i<particiones.length; i++) {
				System.out.print("ID: " + particiones[i].getId());
				System.out.print(" | Disp: " + particiones[i].getDisponible());
				if(particiones[i].getDisponible()==false) {
					System.out.print(" | PID: " + particiones[i].getProceso().getPID());
					System.out.print(" | Proc: " + particiones[i].getProceso().getNombre());
				}
				System.out.print(" | Tam: " + particiones[i].getTamano());
				System.out.print(" | Ini: " + particiones[i].getInicio());
				System.out.println();
			}
		}
		
		public Particion[] getParticiones() {
			return particiones;
		}

		public void setParticiones(Particion[] particiones) {
			this.particiones = particiones;
		}

		public int getMemoriaPpal() {
			return memoriaPpal;
		}

		public void setMemoriaPpal(int memoriaPpal) {
			this.memoriaPpal = memoriaPpal;
		}
	}


