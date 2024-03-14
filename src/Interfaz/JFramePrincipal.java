package Interfaz;


import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import java.awt.Font;
import java.awt.Image;

import javax.swing.border.TitledBorder;

import Logica.Particion;
import Logica.Proceso;
import Logica.ProcesosDisponibles;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;


public class JFramePrincipal extends JFrame implements ActionListener{
    
	private String mensaje = "";		//manejo de los mensajes a mostrar
	private String activos [];			//Lita de procesos activos
	private int modelo = 1;				//Modelo seleccionado
	private int asignacion = 1;			//Algoritmos de asignacion
	private int tamOcupado = 0;			//label tamano ocupado
	private int tamLibre = 0;
	private int fragmentacion = 0;
	private boolean compactacion = false;	//Compactacion activa/no activa
	
	private JPanel panelPrincipal;
	
	private JPanel panelProcesos;
	private JToggleButton tglbtnON_OFF;
	private JLabel lblProcesosDisp;
	private JScrollPane scrollLista;
	private JList<String> listaProcesos;
	private JLabel lblSegCodigo;

	
	private JPanel panelModMemoria;
	private ButtonGroup btgModMemoria;
	private JRadioButton rdbtnPEstaticaFijas;
	private JRadioButton rdbtnPDinamicas;
	
	private JPanel panelAsignacion;
	private ButtonGroup btgAsignacion;
	private JRadioButton rdbtnPrimerAjuste;
	private JRadioButton rdbtnMejorAjuste;
	private JRadioButton rdbtnPeorAjuste;
	
	
	private JPanel panelMensajes;
	private JTextPane textMensajes;
	private JScrollPane scrollMensajes;
	
	private JPanel panelMemoria;
	private Label lblTitulo;
	private JLabel lblMemoriaPrincipalpal;
	private PanelDibujoMem dibujoMemoria;
	private JLabel lblMemoriaLibre;
	private JLabel lblKB;
	private JLabel lblMemoriaLibre1;
	private JLabel lblKB1;
	private JLabel fragmentation;
	private JLabel fragmentationKB;
	private PanelDibujoProc dibujoProcesos;
	private JLabel lblInicioMem;
	private JLabel lblFinMemoria;

	private JScrollPane scrollTabla;
	private JLabel lblTablaProcesos;
	
	private JLabel lblprocesosActivos;
	private JScrollPane scrollActivos;
	private JList<String> listaActivos;
	private JLabel lblStarIcon;

	private ArrayList<Proceso> procesosActivos = new ArrayList<>();

	
	public JFramePrincipal() {
		setTitle("Simulador Gestor de Memoria");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 800, 600);
		setLocationRelativeTo(null);
		
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().setBackground(Color.BLUE);
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		//Panel de seleccion de procesos
		panelProcesos = new JPanel();
		panelProcesos.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelProcesos.setBounds(10, 10, 180, 350);
		panelPrincipal.add(panelProcesos);
		panelProcesos.setLayout(null);
		
		tglbtnON_OFF = new JToggleButton();
		ImageIcon iconIniciar = new ImageIcon(JFramePrincipal.class.getResource("/img/iniciar.png"));
		ImageIcon iconDetener = new ImageIcon(JFramePrincipal.class.getResource("/img/detener.png"));
        int nuevoAncho=100;
        int nuevoAlto=30;
		Image imageIniciar = iconIniciar.getImage().getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
		ImageIcon nuevoIconoIniciar = new ImageIcon(imageIniciar);

		Image imageDetener = iconDetener.getImage().getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
		ImageIcon nuevoIconoDetener = new ImageIcon(imageDetener);

		tglbtnON_OFF.setSelectedIcon(nuevoIconoDetener);
		tglbtnON_OFF.setIcon(nuevoIconoIniciar);
		tglbtnON_OFF.setForeground(Color.WHITE);
		tglbtnON_OFF.setBounds(31, 10, nuevoAncho, nuevoAlto);
		tglbtnON_OFF.addActionListener(this);
		panelProcesos.add(tglbtnON_OFF);
		
		lblProcesosDisp = new JLabel("Procesos Disponibles");
		lblProcesosDisp.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProcesosDisp.setBounds(10, 55, 142, 13);
		panelProcesos.add(lblProcesosDisp);
		
		listaProcesos = new JList(generarListaProcesos());
		listaProcesos.setFont(new Font("Tahoma", Font.PLAIN, 10));
		listaProcesos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollLista = new JScrollPane();
		scrollLista.setViewportView(listaProcesos);
		scrollLista.setBounds(10, 75, 160, 120);
		panelProcesos.add(scrollLista);
		
		
		//Panel Modelos de Memoria
		panelModMemoria = new JPanel();
		panelModMemoria.setBorder(new TitledBorder(null, "Tipo de Memoria", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelModMemoria.setBounds(10, 370, 180, 183);
		panelPrincipal.add(panelModMemoria);
		panelModMemoria.setLayout(null);
		
		rdbtnPEstaticaFijas = new JRadioButton("<html>Particiones estaticas<br />fijas</html>");
		rdbtnPEstaticaFijas.setSelected(true);
		rdbtnPEstaticaFijas.setBounds(10, 24, 152, 29);
		panelModMemoria.add(rdbtnPEstaticaFijas);
		
		
		
		rdbtnPDinamicas = new JRadioButton("Particiones Dinamicas");
		rdbtnPDinamicas.setBounds(10, 86, 160, 40);
		panelModMemoria.add(rdbtnPDinamicas);
		ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(rdbtnPEstaticaFijas);
        buttonGroup.add(rdbtnPDinamicas);	
	
		
		//Panel Algoritmo Asignacion
		panelAsignacion = new JPanel();
		panelAsignacion.setBorder(new TitledBorder(null, "Algoritmo de Gestion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelAsignacion.setBounds(200, 370, 180, 183);
		panelPrincipal.add(panelAsignacion);
		panelAsignacion.setLayout(null);
		
		rdbtnPrimerAjuste = new JRadioButton("Primer Ajuste");
		rdbtnPrimerAjuste.setToolTipText("Asignar el primer fragmento libre que tenga el tamaño suficiente.");
		rdbtnPrimerAjuste.setSelected(true);
		rdbtnPrimerAjuste.setBounds(10, 34, 103, 21);
		panelAsignacion.add(rdbtnPrimerAjuste);
		
		rdbtnMejorAjuste = new JRadioButton("Mejor Ajuste");
		rdbtnMejorAjuste.setToolTipText("Asignar el fragmento mas pequeño que tenga el tamaño suficiente.");
		rdbtnMejorAjuste.setBounds(10, 74, 103, 21);
		panelAsignacion.add(rdbtnMejorAjuste);
		
		rdbtnPeorAjuste = new JRadioButton("Peor Ajuste");
		rdbtnPeorAjuste.setToolTipText("Asignar el fragmento mas grande.");
		rdbtnPeorAjuste.setBounds(10, 114, 103, 21);
		panelAsignacion.add(rdbtnPeorAjuste);
		
		btgAsignacion = new ButtonGroup();
		btgAsignacion.add(rdbtnPrimerAjuste);
		btgAsignacion.add(rdbtnMejorAjuste);
		btgAsignacion.add(rdbtnPeorAjuste);
		
		//Panel de Mensajes
		panelMensajes = new JPanel();
		panelMensajes.setBorder(new TitledBorder(null, "Mensajes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelMensajes.setBounds(390, 370, 386, 183);
		panelPrincipal.add(panelMensajes);
		panelMensajes.setLayout(null);
		
		textMensajes = new JTextPane();
		textMensajes.setEditable(false);
		
		scrollMensajes = new JScrollPane();
		scrollMensajes.setViewportView(textMensajes);
		scrollMensajes.setBounds(15, 25, 356, 143);
		panelMensajes.add(scrollMensajes);
		
		//Panel Grafico Memoria
		panelMemoria = new JPanel();
		panelMemoria.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelMemoria.setBounds(200, 10, 576, 350);
		panelPrincipal.add(panelMemoria);
		panelMemoria.setLayout(null);
		
		lblTitulo = new Label("Gestion de Memoria");
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 16));
		lblTitulo.setAlignment(Label.CENTER);
		lblTitulo.setBounds(5, 5, 566, 29);
		panelMemoria.add(lblTitulo);
		
		lblStarIcon = new JLabel("");
		lblStarIcon.setIcon(new ImageIcon(JFramePrincipal.class.getResource("/img/start.png")));
		lblStarIcon.setBounds(150, 40, 420, 286);
		panelMemoria.add(lblStarIcon);
		
		//dibujoProcesos();
	}
	
	//Dibujar particiones
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void dibujoProcesos() {
		lblStarIcon = new JLabel("");
		lblStarIcon.setIcon(new ImageIcon(JFramePrincipal.class.getResource("/img/start.png")));
		lblStarIcon.setBounds(270, -20, 420, 286);
		panelMemoria.add(lblStarIcon);
		//Proccesos en memoria dibujo
		lblMemoriaPrincipalpal = new JLabel("Memoria Principal");
		lblMemoriaPrincipalpal.setBounds(25, 207, 126, 13);
		panelMemoria.add(lblMemoriaPrincipalpal);
		
		dibujoProcesos = new PanelDibujoProc(modelo, asignacion);
		panelMemoria.add(dibujoProcesos);
		dibujoProcesos.setBounds(25, 230, 531, 80);
		
		//Dibujo mamoria libre y ocupada
		lblMemoriaLibre = new JLabel("Memoria Ocupada");
		lblMemoriaLibre.setBounds(135, 45, 115, 13);
		lblMemoriaLibre.setVisible(true);
		panelMemoria.add(lblMemoriaLibre);

		lblKB = new JLabel("KB");
		lblKB.setBounds(135, 68, 65, 13);
		panelMemoria.add(lblKB);

		lblMemoriaLibre1 = new JLabel("Memoria Libre");
		lblMemoriaLibre1.setBounds(135, 85, 115, 13);
		lblMemoriaLibre1.setVisible(true);
		panelMemoria.add(lblMemoriaLibre1);

		lblKB1 = new JLabel("KB");
		lblKB1.setBounds(135, 105, 65, 13);
		panelMemoria.add(lblKB1);

		fragmentation = new JLabel("<html>Fragmentación<br>Generada</html>");
		fragmentation.setBounds(135, 125, 115, 30);
		fragmentation.setVisible(true);
		panelMemoria.add(fragmentation);

		fragmentationKB = new JLabel("KB");
		fragmentationKB.setBounds(135, 160,115,13);
		panelMemoria.add(fragmentationKB);

		dibujoMemoria = dibujoProcesos.getDibujoMemLibre();
		dibujoMemoria.setBounds(45, 45, 80, 150);
		panelMemoria.add(dibujoMemoria);
		
		lblInicioMem = new JLabel("0 KB");
		lblInicioMem.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblInicioMem.setHorizontalAlignment(SwingConstants.CENTER);
		lblInicioMem.setBounds(10, 315, 45, 13);
		panelMemoria.add(lblInicioMem);
		
		lblFinMemoria = new JLabel("16384 KB");
		lblFinMemoria.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblFinMemoria.setHorizontalAlignment(SwingConstants.CENTER);
		lblFinMemoria.setBounds(525, 315, 45, 13);
		panelMemoria.add(lblFinMemoria);
		
		
		//Lista de procesos activos
		lblprocesosActivos = new JLabel("Procesos Activos");
		lblprocesosActivos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblprocesosActivos.setBounds(10, 220, 160, 13);
		panelProcesos.add(lblprocesosActivos);
		
		actualizarActivos();
		
		listaActivos = new JList(activos);
		listaActivos.setFont(new Font("Tahoma", Font.PLAIN, 10));
		listaActivos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollActivos = new JScrollPane();
		scrollActivos.setViewportView(listaActivos);
		scrollActivos.setBounds(10, 240, 160, 100);
		panelProcesos.add(scrollActivos);
		
		lblSegCodigo = new JLabel("Codigo:");
		lblSegCodigo.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblSegCodigo.setBounds(5, 197, 45, 13);
		panelProcesos.add(lblSegCodigo);
		
	}
	
	//Manejo eventos
	public void actionPerformed(ActionEvent event) {
		
		//Boton iniciar/detener
		if(event.getSource() == tglbtnON_OFF){
					
			JToggleButton tglbON_OFF = (JToggleButton)event.getSource();

            if(tglbON_OFF.isSelected()){
            	lblStarIcon.setVisible(false);
            	
            	//Modelo seleccionado
            	if(rdbtnPEstaticaFijas.isSelected())
            		modelo = 1;
            	else if(rdbtnPDinamicas.isSelected())
            		modelo = 2;
            	
            	
            	//Metodo de asignacion
            	if(rdbtnPrimerAjuste.isSelected())
            		asignacion = 1;
            	else if(rdbtnMejorAjuste.isSelected())
            		asignacion = 2;
            	else if(rdbtnPeorAjuste.isSelected())
            		asignacion = 3;
            	
            	
            	//Segun modelo seleccionado se asigna el titulo
            	if(modelo == 1) {
            		lblTitulo.setText("Particiones Estaticas Fijas");
            	}else if(modelo == 2){
            		lblTitulo.setText("Particiones Dinamicas");
            	}
            	
            	desabilitarIniciado();
            	dibujoProcesos();
            	
            	
            	mensaje = "Sistema Operativo cargado.";
            	textMensajes.setText(mensaje);
            	
            	panelProcesos.revalidate();
            	panelProcesos.repaint();
            	
            }else {
            	mensaje = mensaje + "\nSistema Operativo detenido.";
            	textMensajes.setText(mensaje);
            	//habilitarDetenido();
            	this.dispose();
            	JFramePrincipal frame = new JFramePrincipal();
            	frame.setVisible(true);
            }
            
        	actualizarActivos();
        	panelMemoria.repaint();
        	listaActivos.setListData(activos);
        	scrollActivos.revalidate();
            
            tamOcupado = dibujoMemoria.calcularTamOcupado();
            lblKB.setText(Integer.toString(tamOcupado) + " KB");
			iniciarSimulacionGestionMemoria();
		}
		
	}

	public void iniciarSimulacionGestionMemoria(){
		Thread actionThread = new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				try {
					Thread.sleep((int) (Math.random() * 1700 + 300));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				SwingUtilities.invokeLater(() -> {
					int accion = (int) (Math.random() * 2 + 1);

					if (accion == 1) {
						int seleccionado = (int) (Math.random() * 10);
						boolean agregado = false;

						if (seleccionado != -1) {

							//Proceso seleccionado
							Proceso proceso = new ProcesosDisponibles().getDisponibles()[seleccionado];

							//Asigancion segun modelo
							if (modelo == 1) {
								agregado = dibujoProcesos.getParticionesEstFijas().añadirProceso(proceso, asignacion);
								fragmentationKB.setText(Integer.toString(dibujoMemoria.calcularFragmentacionInterna()) + " KB");
							} else if (modelo == 2) {
								agregado = dibujoProcesos.getParticionesDinamicas().añadirProceso(proceso, asignacion, compactacion);
								//Se agrega para que se actualice el dibujo de memoria libre
								dibujoProcesos.getDibujoMemLibre().setParticiones(dibujoProcesos.getParticionesDinamicas().getParticiones());
								fragmentationKB.setText(Integer.toString(dibujoMemoria.calcularFragmentacionExterna()) + " KB");
							}

							if (agregado) {
								mensaje = mensaje + "\n" + proceso.getNombre() + " - PID:" + proceso.getPID() + " agregado.";
							}
							else {
								mensaje = mensaje + "\nProceso no agregado (Sin memoria, excede tamaño de particiones o segmentos no validos)";
							}
							textMensajes.setText(mensaje);

							actualizarActivos();
							panelMemoria.repaint();
							listaActivos.setListData(activos);
							scrollActivos.revalidate();

						} else {
							mensaje = mensaje + "\nSeleccione Proceso";
							textMensajes.setText(mensaje);
						}
						tamOcupado = dibujoMemoria.calcularTamOcupado();
						tamLibre = 16384-tamOcupado;
						lblKB.setText(Integer.toString(tamOcupado) + " KB");
						lblKB1.setText(Integer.toString(tamLibre) + " KB");
					}else{

						int seSelecciono = (int) (Math.random() * procesosActivos.size() + 1);

						boolean eliminado = false;

						if (seSelecciono != -1 ) {
							//Extraer PID del proceso seleccionado
							String seleccionado = String.valueOf( procesosActivos.get(seSelecciono).getPID() );

							int PID = procesosActivos.get(seSelecciono).getPID();

							//Asigancion segun modelo
							if(modelo == 1) {
								eliminado = dibujoProcesos.getParticionesEstFijas().eliminarProceso(PID);
								fragmentationKB.setText(Integer.toString(dibujoMemoria.calcularFragmentacionInterna()) + " KB");
							}else if (modelo == 2) {
								eliminado = dibujoProcesos.getParticionesDinamicas().eliminarProceso(PID);
								//Se agrega para que se actualice el dibujo de memoria libre
								dibujoProcesos.getDibujoMemLibre().setParticiones(dibujoProcesos.getParticionesDinamicas().getParticiones());
								fragmentationKB.setText(Integer.toString(dibujoMemoria.calcularFragmentacionExterna()) + " KB");
							}


							if(eliminado) {
								mensaje = mensaje + "\n" + seleccionado + " eliminado.";
								procesosActivos.remove(seSelecciono);
							}else {
								mensaje = mensaje + "\nSeleccione un proceso activo (S.O. No se puede eliminar).";
							}
							textMensajes.setText(mensaje);

							actualizarActivos();
							panelMemoria.repaint();
							listaActivos.setListData(activos);
							scrollActivos.revalidate();
						}

						tamOcupado = dibujoMemoria.calcularTamOcupado();
						tamLibre = 16384-tamOcupado;
						lblKB.setText(Integer.toString(tamOcupado) + " KB");
						lblKB1.setText(Integer.toString(tamLibre) + " KB");
					}
				});

			}
		});
		actionThread.start();
	}
	
	
	public void actualizarActivos() {
		//Generar lista de procesos activos a partir de las particiones
		if(modelo == 1)
			activos = generarListaActivos(dibujoProcesos.getParticionesEstFijas().getParticiones());
		
		else if (modelo == 2)
			activos = generarListaActivos(dibujoProcesos.getParticionesDinamicas().getParticiones());
		
	}
	
	
	public String[] generarListaProcesos() {
		ProcesosDisponibles procesosDisponibles = new ProcesosDisponibles();
		
		String listaProcesos[] = new String[procesosDisponibles.getDisponibles().length];
		
		for (int i = 0; i < listaProcesos.length; i++ ) {
			listaProcesos[i] = procesosDisponibles.getDisponibles()[i].getNombre() 
							   + " ("+ procesosDisponibles.getDisponibles()[i].getTamano()
							   + "KB) ";
		}
		
		return listaProcesos;
	}
	
	//Generar lista de procesos activos
	public String[] generarListaActivos(Particion[] particiones) {
		
		ArrayList<String> lista = new ArrayList<String>();
		
		boolean existe = false;
		//int PID = Integer.parseInt(seleccionado.substring(indice+4, seleccionado.length()));

		for(int i = 0; i<particiones.length; i++) {
			if(particiones[i].getDisponible()==false) {
				//Verifica que el proceso aun no este en lista(en paginacion se repiten procesos por las paginas)
				for(int j=0; j<lista.size();j++) {
					int indice = lista.get(j).indexOf("PID:");
					if(particiones[i].getProceso().getPID() == Integer.parseInt(lista.get(j).substring(indice+4, lista.get(j).length())) )
						existe = true;
				}
				if(existe == false) {
					Proceso proceso = new Proceso(particiones[i].getProceso().getPID(), particiones[i].getProceso().getNombre(), particiones[i].getProceso().getTamano(), particiones[i].getProceso().getColor());
					lista.add(particiones[i].getProceso().getNombre()
							  + " (" + particiones[i].getProceso().getTamano() + "KB)"
							  + " PID:" + particiones[i].getProceso().getPID());
					procesosActivos.add(proceso);
				}
			}
			existe = false;
		}
		//Canversion ArrayList en Array
		String[] listaActivos = lista.toArray(new String[0]);
		
		return listaActivos;
	}
	
	
	
	public void desabilitarIniciado() {
		rdbtnPEstaticaFijas.setEnabled(false);
		rdbtnPDinamicas.setEnabled(false);
		rdbtnPrimerAjuste.setEnabled(false);
		rdbtnMejorAjuste.setEnabled(false);
		rdbtnPeorAjuste.setEnabled(false);
	}
	
	
	
	public void habilitarDetenido() {
		rdbtnPEstaticaFijas.setEnabled(true);
		rdbtnPDinamicas.setEnabled(true);
		
		rdbtnPrimerAjuste.setEnabled(true);
		rdbtnMejorAjuste.setEnabled(true);
		rdbtnPeorAjuste.setEnabled(true);
	}
}
			