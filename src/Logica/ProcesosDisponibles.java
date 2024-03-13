package Logica;


	import java.awt.Color;

import Logica.Proceso;

	public class ProcesosDisponibles {
		
		public ProcesosDisponibles() {
			cargarProcesos();
		}
		
		private Proceso disponibles[] = new Proceso[11];

		
		  //Crea la lista de procesos disponibles
		 
		private void cargarProcesos() {
			disponibles[0] = new Proceso(1, "Proceso 1", 6144, new Color(244, 176, 132));
			disponibles[1] = new Proceso(2, "Proceso 2", 5120, new Color(255, 217, 102));
			disponibles[2] = new Proceso(3, "Proceso 3", 4096, new Color(155, 194, 230));
			disponibles[3] = new Proceso(4, "Proceso 4", 3072, new Color(169, 208, 142));
			disponibles[4] = new Proceso(5, "Proceso 5", 2048, new Color(153, 153, 255));
			disponibles[5] = new Proceso(6, "Proceso 6", 1024, new Color(113, 255, 196));
			disponibles[6] = new Proceso(7, "Proceso 7", 512, new Color(255, 143, 146));
			disponibles[7] = new Proceso(8, "Proceso 8", 256, new Color(192, 192, 192));
			disponibles[8] = new Proceso(9, "Proceso 9", 128, new Color(197, 161, 125));
			disponibles[9] = new Proceso(10, "Proceso 10", 64, new Color(234, 160, 215));
			disponibles[10] = new Proceso(11, "Proceso 11", 32, new Color(204, 208, 130));
		}
		
		public Proceso[] getDisponibles() {
			return disponibles;
		}

		public void setDisponibles(Proceso[] disponibles) {
			this.disponibles = disponibles;
		}

}
