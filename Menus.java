/* Lunes, 21-Mayo-12

Cuevas Lamas Laura Sofia
Velarde Humbert Johana P.
*/

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;

public class Menus extends Frame
{
	File exist = new File("Datos.dat");
	static MenuItem guardar = new MenuItem ("Guardar", new MenuShortcut('G'));
	MenuItem salir = new MenuItem ("Salir", new MenuShortcut('S'));

	MenuItem leer = new MenuItem ("Nuevo", new MenuShortcut('N'));
	static	MenuItem consultar = new MenuItem ("Consultar", new MenuShortcut('C'));
	static	MenuItem buscar = new MenuItem ("Buscar", new MenuShortcut('B'));
	
	Menu archivo = new Menu("Archivo");
	Menu datos = new Menu("Datos");
	
	MenuBar barraMenu = new MenuBar();
	Date hoy = new Date();
	String patron = "EEEE dd-MMM-yyyy, HH:mm:ss";
	SimpleDateFormat formato = new SimpleDateFormat(patron);
	String salida = formato.format(hoy);
	
	Label mensaje1 = new Label("Selecciona una opción");
	Label mensaje2 = new Label(salida);

	public Menus()
	{
		acciones();
		archivo.add(guardar);
		archivo.addSeparator();
		archivo.add(salir);
		
		datos.add(leer);
		datos.add(consultar);
		datos.add(buscar);

		barraMenu.add(archivo);				
		barraMenu.add(datos);
	
		setMenuBar(barraMenu);
		add(mensaje1);
		add(mensaje2);
		mensaje1.reshape(75, 50, 300, 60);
		mensaje2.reshape(100, 100, 500, 60);
		pack();
		show();
		repaint();
		addWindowListener(new VerificaVentana());
	}

	void acciones()
	{
		guardar.addActionListener(new VerificaVentana());
		salir.addActionListener(new VerificaVentana());
		guardar.setEnabled(false);

		leer.addActionListener(new VerificaVentana());
		consultar.addActionListener(new VerificaVentana());
		consultar.setEnabled(exist.exists());
		
		buscar.addActionListener(new VerificaVentana());
		buscar.setEnabled(false);
	}
	
	public static void main(String args[]) {
		Menus ventana = new Menus();
		ventana.setTitle("Gestion de nombres");
		ventana.setBackground(java.awt.Color.white);
		ventana.setExtendedState(6);
		ventana.setSize(300,250);
		ventana.setVisible(true);
	}
}
