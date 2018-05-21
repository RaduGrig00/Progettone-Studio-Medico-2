import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.synth.SynthSeparatorUI;

public class Ordinatore 
{	

//	<--------------------------------------------------------------NODI-------------------------------------------------------------------->
	//funziona quando non deve fare elimina in testa o elimina in coda
	public static void scambia(ListaVisite visite, int pos1, int pos2) throws VisitaException, IOException, FileException 
	{
		if(pos1<=0 || pos1>visite.getElementi() || pos2<=0 ||pos2>visite.getElementi())
			throw new VisitaException("Posizioni non valide");
		Visite a1,a2;
		a1=new Visite(visite.getVisita(pos1));
		a2=new Visite(visite.getVisita(pos2));
	
		visite.inserisciInPosizione(a1, pos2);
		visite.inserisciInPosizione(a2, pos1);
		
		visite.eliminaInPosizione(pos2+2);
		visite.eliminaInPosizione(pos1+1);
	}

	private static ListaVisite copia(ListaVisite visite) throws IOException, ClassNotFoundException 
	{
		ListaVisite v2=new ListaVisite();
		visite.salvaListaVisite("copia.bin");
		v2=v2.caricaListaVisite("copia.bin");
		return v2;
	}
	
	public static ListaVisite selectionSortCrescenteNodi(ListaVisite visite) throws ClassNotFoundException, IOException, FileException, VisitaException
	{
		ListaVisite visitaCopia=copia(visite);
		boolean scambioAvvenuto;
		do
		{
			scambioAvvenuto=false;
			for (int i = 1; i < visitaCopia.getElementi(); i++) 
			{
				if(visitaCopia.getVisita(i).getDataOra().isAfter(visitaCopia.getVisita(i+1).getDataOra()))
				{
					scambia(visitaCopia,i,i+1);
					scambioAvvenuto=true;
				}
						
					
			}
		} while (scambioAvvenuto==true);
		return visitaCopia;
	}
	
	public static ListaVisite selectionSortCrescenteAlfabetoNodi(ListaVisite visite) throws ClassNotFoundException, IOException, FileException, VisitaException
	{
		ListaVisite visitaCopia=copia(visite);
		boolean scambioAvvenuto;
		do
		{
			scambioAvvenuto=false;
			for (int i = 1; i < visitaCopia.getElementi(); i++) 
			{
				if(visitaCopia.getVisita(i).getNomeP().compareTo(visitaCopia.getVisita(i+1).getNomeP())==0)
				{
					if(visitaCopia.getVisita(i).getCognomeP().compareTo(visitaCopia.getVisita(i+1).getCognomeP())==0)
					{						
					}
					else if(visitaCopia.getVisita(i).getCognomeP().compareTo(visitaCopia.getVisita(i+1).getCognomeP())<0)
					{
					}
					else if(visitaCopia.getVisita(i).getCognomeP().compareTo(visitaCopia.getVisita(i+1).getCognomeP())>0)
					{
						scambia(visitaCopia,i,i+1);
						scambioAvvenuto=true;
					}
				}
				else if(visitaCopia.getVisita(i).getNomeP().compareTo(visitaCopia.getVisita(i+1).getNomeP())<0)
				{
				}
				else if(visitaCopia.getVisita(i).getNomeP().compareTo(visitaCopia.getVisita(i+1).getNomeP())>0)
				{
					scambia(visitaCopia,i,i+1);
					scambioAvvenuto=true;
				}
					
			}
		} while (scambioAvvenuto==true);
		return visitaCopia;
	}
}
	
	