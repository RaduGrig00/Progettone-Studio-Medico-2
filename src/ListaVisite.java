import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ListaVisite implements Serializable
{
	private Nodo head;
	private int elementi;
	
	//Costruttore lista visite
	public ListaVisite()
	{
		head=null;
		elementi=0;
	}
	
	public ListaVisite(ListaVisite i)
	{
		this.head=i.head;
		this.elementi=i.elementi;
	}
	
	//getter che restituisce elementi
	public int getElementi()
	{
		return elementi;
	}
	
	private Nodo creaNodo(Visite visita1, Nodo link)
	{
		Nodo nodo= new Nodo(visita1);
		nodo.setLink(link);
		return nodo;
	}
	
	private Nodo getLinkPosizione(int posizione) throws VisitaException 
	{
		
		Nodo p;
		int n;
		p=head;
		n=1;
		
		if (posizione<1 || posizione>getElementi())
			throw new VisitaException("Posizione non valida");
		if (elementi==0)
			throw new VisitaException("Lista vuota");
			
		while(p.getLink()!=null && n<posizione)
		{
			p=p.getLink();	
			n++;
		}
		
		return p;
	}
	
	public void inserisciInTesta (Visite visita1)
	{
		
		Nodo p=creaNodo(visita1, head);
		head=p;
		elementi++;
	}
	
	public String toString()
	{
		String risultato="Head";
		if (elementi==0)
			return risultato+="-->";
		Nodo p=head;
		while (p!=null)
		{
			risultato+="-->"+p.getInfo().toString();
			p=p.getLink();
		}
		return risultato;
	}
	
	public void inserisciInCoda(Visite visita1) throws VisitaException 
	{
		if(elementi==0)
		{
			inserisciInTesta(visita1);
			return;
		}
		
		Nodo pn=creaNodo(visita1, null);
		Nodo p=getLinkPosizione(elementi);
		p.setLink(pn);
		elementi++;	
	}
	
	public void inserisciInPosizione(Visite visita1,int posizione) throws VisitaException 
	{
		if (posizione<=1)
		{
			inserisciInTesta(visita1);
			return;
		}
		if (posizione>elementi)
		{
			inserisciInCoda(visita1);
			return;
		}
		
		Nodo pn=creaNodo(visita1, getLinkPosizione(posizione));
		Nodo precedente=getLinkPosizione(posizione-1);
		precedente.setLink(pn);
		elementi++;
	}
	
	public void eliminaInTesta(int posizione) throws VisitaException, IOException, FileException
	{
		if (elementi==0)
			throw new VisitaException("Lista vuota");
		head=head.getLink();
		elementi--;	
	}
	
	public void eliminaInCoda(int posizione) throws VisitaException, FileException, IOException
	{
		if (elementi==0)
			throw new VisitaException("Lista vuota");
		if (elementi==1)
		{
			eliminaInTesta(posizione);
			return;
		}
		
		Nodo p=getLinkPosizione(elementi-1);
		p.setLink(null);
		elementi--;
	}
	
	public void eliminaInPosizione(int posizione) throws VisitaException, IOException, FileException
	{
		if (elementi==0)
			throw new VisitaException("Lista vuota");
		
		if (posizione<=0 || posizione>elementi)
			throw new VisitaException("Posizione non valida");
	
		if (posizione==1)
		{
			eliminaInTesta(posizione);
			return;
		}
		if (posizione==elementi)
		{
			eliminaInCoda(posizione);
			return;
		}
		
		Nodo p;
		p=getLinkPosizione(posizione);
		Nodo precedente=getLinkPosizione(posizione-1);
		precedente.setLink(p.getLink());
		elementi--;
		
	}
	
	public void eliminaVisita(int Id) throws VisitaException, IOException, FileException
	{
		if(elementi==0)
			throw new VisitaException("Lista vuota");
		for (int i = 1; i <= elementi; i++) 
		{
				boolean avvenutaEliminazione = false;
				if((i==1)&&(getLinkPosizione(i).getInfo().getId()==Id))
				{
					esportaCSV("PrenotazioniAnnullate.txt",Id);
					eliminaInTesta(Id);
					avvenutaEliminazione=true;
					return;
				}
				
				if((i==elementi)&&(getLinkPosizione(i).getInfo().getId()==Id))
				{
					esportaCSV("PrenotazioniAnnullate.txt",Id);
					eliminaInCoda(Id);
					avvenutaEliminazione=true;
					return;
				}
				if(avvenutaEliminazione==false)
					{
						if(getLinkPosizione(i).getInfo().getId()==Id)
						{
							
							Nodo p=getLinkPosizione(Id);
							Nodo precedente=getLinkPosizione(Id-1);
							precedente.setLink(p.getLink());
							elementi--;
							esportaCSV("PrenotazioniAnnullate.txt",Id);
						}
					}
				if(elementi>0 && getLinkPosizione(i).getInfo().getId()==Id)
					i=0;
				}
				
	}
	
	/*public void eliminaInPosizione1(int Id) throws VisitaException, IOException, FileException, ClassNotFoundException
	{
		boolean isElim=false;
		if (elementi==0)
			throw new VisitaException("Lista vuota");
		
		
		for (int i = 1; i <= elementi; i++)
		{
			if((i==1) && (getLinkPosizione(i).getInfo().getId()==Id))
			{
				esportaCSV("PrenotazioniAnnullate.txt",Id);
				eliminaInTesta(Id);
				isElim=true;
				return;
			}
			
			if (i==getElementi() && getLinkPosizione(i).getInfo().getId()==Id) 
			{
				esportaCSV("PrenotazioniAnnullate.txt",Id);
				eliminaInTesta(Id);
				isElim=true;
				return;
			}
			
			if(isElim=false)
			{
				if(getLinkPosizione(i).getInfo().getId()==Id)
				{
					Nodo p;
					p=getLinkPosizione(Id);
					Nodo precedente=getLinkPosizione(Id-1);
					precedente.setLink(p.getLink());
					elementi--;
					esportaCSV("eliminati.txt",Id);
				}
			}
			
			if(elementi>0 && getLinkPosizione(i).getInfo().getId()==Id)
				i=0;
		}
	}
	*/
	public String visita (int posizione) throws VisitaException 
	{
		if (elementi==0)
			throw new VisitaException("Lista vuota");
		
		if (posizione<=0 || posizione>elementi)
			throw new VisitaException("Posizione non valida");
		
		Nodo p=getLinkPosizione(posizione);
		return p.getInfo().toString();		
	}
	
	public Visite getVisita (int posizione) throws VisitaException 
	{
		if (elementi==0)
			throw new VisitaException("Lista vuota");
		
		if (posizione<=0 || posizione>elementi)
			throw new VisitaException("Posizione non valida");
		
		Nodo p=getLinkPosizione(posizione);
		return p.getInfo();		
	}
	/*public void eseguiVisita(int Id) throws VisitaException
	{
		if(elementi==0)
			throw new VisitaException("Lista vuota");
		Nodo p=head;
	
		
		while(p!=null)
		{
			if(p.getInfo().getId()==Id)
			{
				System.out.println(p.getInfo().toString());
				p.getInfo().setVisitaSvolta(true);
			}
			p=p.getLink();
		}
	}
	*/
	public void eseguiVisita(int Id) throws VisitaException
	{
		if(elementi==0)
			throw new VisitaException("Lista vuota");
		Nodo p=head;
	
		for (int i = 1; i <= elementi; i++) 
		{
			if(p.getInfo().getId()==Id)
			p.getInfo().setVisitaSvolta(true);
			System.out.println(p.getInfo().toString());
		}
		{
			if(p.getInfo().getId()!=Id)
			System.out.println("Nessuna visita con questo Id: "+Id);
		}
	}
	
	public void esportaCSV (String nomeFile, int posizione) throws IOException, VisitaException, FileException
	{
		Visite visita1;
		String visitaCSV;
		
		TextFile file= new TextFile (nomeFile,'W');
		
		visita1=getVisita(posizione);
		visitaCSV=visita1.getId()+";"+visita1.getNomeP()+";"+visita1.getCognomeP()+";"+visita1.getNomeM()+";"+visita1.getCognomeM()+";"+visita1.getDataOra().getYear()+";"+visita1.getDataOra().getMonth()+";"+visita1.getDataOra().getDayOfWeek()+";"+visita1.getDataOra().getHour()+";"+visita1.getDataOra().getMinute()+";";
		file.toFile(visitaCSV);
		
		file.closeFile();
		
	}
	/*public void esportaCSV (String nomeFile) throws IOException, VisitaException, FileException
	{
		TextFile file= new TextFile (nomeFile,'W');
		String visitaCSV;
		Visite visita1;
		
		if(getElementi()==0)
			throw new VisitaException("Lista vuota, impossibile completare l'operazione");
		
		for (int i = 1; i <= getElementi(); i++) 
		{
			visita1=getVisita(i);
			visitaCSV=visita1.getId()+";"+visita1.getNomeP()+";"+visita1.getCognomeP()+";"+visita1.getNomeM()+";"+visita1.getCognomeM()+";"+visita1.getDataOra().getYear()+";"+visita1.getDataOra().getMonth()+";"+visita1.getDataOra().getDayOfWeek()+";"+visita1.getDataOra().getHour()+";"+visita1.getDataOra().getMinute()+";";
			file.toFile(visitaCSV);
		}
		file.closeFile();
		
	}*/

	public ListaVisite importaCSV (String nomeFile) throws IOException, FileException, VisitaException, EccezioneTextFileEOF
	{
		ListaVisite listaVisite=new ListaVisite();
		TextFile file=new TextFile(nomeFile,'R');
		String rigaLetta;
		String[] elementiVisite;
		Visite visita1 = null;
		
			try 
			{
				while(true)
				{
					rigaLetta=file.fromFile();
					elementiVisite=rigaLetta.split(";");
					int Id = visita1.getId();
					String NomeP = visita1.getNomeP();
					String CognomeP = visita1.getCognomeP();
					String NomeM = visita1.getNomeM();
					String CognomeM = visita1.getCognomeM();
					visita1 = new Visite(Id, NomeP, CognomeP, NomeM, CognomeM);
					listaVisite.inserisciInCoda(visita1);
				}
				
			} 
			catch (FileException e) 
			{
				if (e.toString().compareTo("End of file")==0)
					file.closeFile();
				else
					throw new FileException(e.toString());
			}
		
			return listaVisite;		
			
	}
	
	public void salvaListaVisite(String nomeFile) throws IOException
	{
		FileOutputStream file=new FileOutputStream(nomeFile);
		ObjectOutputStream writer=new ObjectOutputStream(file);
		
		writer.writeObject(this);
		writer.flush();
		writer.close();
		
	}
	
	public ListaVisite caricaListaVisite (String nomeFile) throws IOException, ClassNotFoundException
	{
		FileInputStream file=new FileInputStream(nomeFile);
		ObjectInputStream reader= new ObjectInputStream(file);
		ListaVisite listaVisite;
		listaVisite=(ListaVisite)(reader.readObject());
		file.close();
		return listaVisite;
	}

}
	
	

