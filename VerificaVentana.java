/* Lunes, 21-Mayo-12

Cuevas Lamas Laura Sofia
Velarde Humbert Johana P.
*/

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class VerificaVentana extends Frame implements ActionListener, WindowListener
{
	static	String[] nombre,nombres;
	static	String noms;
	static	int i,l,n;
	static	File exist = new File("Datos.dat");
	public void actionPerformed(ActionEvent e)
	{
	int op = e.getActionCommand().compareTo(" ");
	System.out.println(op);
		switch(op)
		{
			case 39: escribeArchivo(); break; //Guardar
			case 51: System.exit(0); break; //Salir
			case 46: leerNombre(); break; //Nuevo
			case 35: leeArchivo(); break; //Consultar
			case 34: buscarNombres(); break; //Buscar
		}
	}	
	public void windowClosing(WindowEvent we)	{ System.exit(0); }	
	public void windowIconified(WindowEvent we)	{ }	
	public void windowOpened(WindowEvent we)	{ }	
	public void windowClosed(WindowEvent we)	{ }	
	public void windowDeiconified(WindowEvent we)	{ }	
	public void windowActivated(WindowEvent we)	{ }	
	public void windowDeactivated(WindowEvent we)	{ }

	public static void leerNombre()
	{
		n = LeerDatos.capJ(n,"Dame la cantidad de nombres:");
		nombre = new String[n];
		LeerDatos.out("Nombres","Lectura de lista de nombres ");
		for(i=0;i<nombre.length;i++) { nombre[i]=LeerDatos.capJ(nombre[i],"Dame el nombre "+(i+1)); }
		Menus.guardar.setEnabled(true);
	}
	
	public static void escribeArchivo()
	{
		DataOutputStream sal=null;
		try
		{
			sal = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(exist)));
			l = nombre.length;
			sal.writeInt(l);
			nombres = nombre;
			for(i=0;i<nombre.length;i++) { sal.writeUTF(nombres[i]); }
		}
		catch (IOException e) { System.err.println("Error: "+e.getMessage()); }
		finally
		{
			Menus.consultar.setEnabled(exist.exists());
			LeerDatos.out("Estado", "¡Datos guardados con éxito en el archivo!");
			try { if(sal!=null)sal.close(); }
			catch (IOException e) { System.err.println("Error: "+e.getMessage()); }
		}
	}
	
	public static void leeArchivo()
	{
		DataInputStream ent=null;
		try
		{
			ent = new DataInputStream(new BufferedInputStream(new FileInputStream(exist)));
			l = ent.readInt();
			nombres = new String[l];
			for(i=0;i<l;i++) { nombres[i]=ent.readUTF(); }
		}
		catch (IOException e) { System.err.println("Error: "+e.getMessage()); }
		finally
		{
			noms = "";
			for(int i=0;i<l;i++)
				noms = noms.concat(nombres[i]+"\n\t");
				LeerDatos.out("Estado", "Datos leídos con éxito del archivo:\n\t"+noms);
				Menus.buscar.setEnabled(true);
			try { if(ent!=null)ent.close(); }
			catch (IOException e) { System.err.println("Error: "+e.getMessage()); }
		}
	}
	
	public static void buscarNombres()
	{
		int p = -1;
		String nom = "";
		nom = LeerDatos.capJ(nom,"Dame el nombre a buscar");
		for(i=0;i<nombres.length;i++)
			if(nombres[i].compareToIgnoreCase(nom)==0)
				p = i;
			if (p>=0)
				LeerDatos.out("Encontrado","El nombre "+nom+" esta en la posicion "+(p+1));
			else
				LeerDatos.out("Aviso","El nombre no fue encontrado");
	}
}
