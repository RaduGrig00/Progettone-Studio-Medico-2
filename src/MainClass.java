import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class MainClass 
{

	public static void main(String[] args) 
	{
		String [] elenco= {
				"1 --> Registra una nuova visita",
				"2 --> Elimina visita",
				"3 --> Eseguire una visita",
				"4 --> Visualizza le visite in ordine alfabetico per il nome dei pazienti",
				"5 --> Visualizza le visite in ordine cronologico",
				"0 --> Esci e Salva"};
		
		ConsoleInput console= new ConsoleInput();
		Menu menu= new Menu(elenco);
		ListaVisite listaVisite = new ListaVisite();
		String nomefile= "listaVisite.bin";
			
		
		
		try 
		{
			listaVisite= listaVisite.caricaListaVisite(nomefile);
		} 
		catch (ClassNotFoundException e) 
		{
			System.out.println("Impossibile caricare oggetti di tipo visite");
		}
		catch (IOException e) 
		{
			System.out.println("Impossibile accedere al file");
		}
		System.out.println("Lettura corretta");
		try 
		{
			listaVisite.salvaListaVisite(nomefile);
		} 
		catch (IOException e2) 
		{
			System.out.println("Errore salvataggio");
		}
		
		int scelta=0;
		
		do
		{
			scelta=menu.scelta();
			switch (scelta) 
		{
		
				case 1:
					{
			
						Visite v1 = new Visite();
						LocalDateTime dataOra = LocalDateTime.of(1, 1, 1, 1, 1);
						try 
						{
							System.out.println("Inserisci Id visita: ");
							v1.setId(console.readInt());
							System.out.println("Inserisci nome del paziente");
							v1.setNomeP(console.readString());
							System.out.println("Inserisci cognome del paziente");
							v1.setCognomeP(console.readString());
							System.out.println("Inserisci nome del medico");
							v1.setNomeM(console.readString());
							System.out.println("Inserisci cognome del medico");
							v1.setCognomeM(console.readString());
							System.out.println("Inserisci l'anno in cui è stata prevista la visita(numero): ");
							dataOra=dataOra.plusYears(console.readInt()-1);
							System.out.println("Inserisci il mese (numero): ");
							dataOra=dataOra.plusMonths(console.readInt()-1);
							System.out.println("Inserisci il giorno (numero): ");
							dataOra=dataOra.plusDays(console.readInt()-1);
							System.out.println("Inserisci l'ora (numero): ");
							dataOra=dataOra.plusHours(console.readInt()-1);
							System.out.println("Inserisci il minuto (numero): ");
							dataOra=dataOra.plusMinutes(console.readInt()-1);
							v1.setDataOra(dataOra);
							listaVisite.inserisciInTesta(v1);
							listaVisite.salvaListaVisite("listaVisite.bin");
						}
						catch (NumberFormatException e) 
						{
							System.out.println("Formato dato inserito non corretto, registrazione visita.");
							break;
						} 
						catch (IOException e) 
						{
							e.printStackTrace();
							break;
						}
			
						break;
			
					}
				case 2:
				{
			
					System.out.println("Inserisci l'Id della visita che vuoi eliminare: ");
					try 
					{
						listaVisite.eliminaVisita(console.readInt());
					} 
					catch (NumberFormatException e) 
					{
						System.out.println("Formato dato inserito non corretto, eliminazione visita fallita");
						break;
					} 
					catch (VisitaException e) 
					{
						System.out.println(e.toString());
						break;
					} 
					catch (IOException e) 
					{
						System.out.println("Errore di lettura o scrittura");
						break;
					} catch (FileException e) 
					{
						System.out.println(e.toString());
						break;
					} 
					try 
					{
						listaVisite.salvaListaVisite(nomefile);
					} 
					catch (IOException e) 
					{
						System.out.println("Errore salvataggio");
						break;
					}
					System.out.println("Salvataggio avvenuto con successo");
					break;
				}	
				case 3:
				{
					int i = 0;
					System.out.println("Inserire l'id della visita che si desidera eseguire");
					ConsoleInput k=new ConsoleInput();
					try
					{
						i=k.readInt();
					} 
					catch (Exception e) 
					{
						System.out.println("Impossibile trovare il file");
						break;
					}
					try 
					{
						listaVisite.eseguiVisita(i);
					} 
					catch (VisitaException e) 
					{
						System.out.println("Visita non valida");
						break;
					}
					break;
				}
				case 4:
					if(listaVisite.getElementi()==0)
						System.out.println("Nessuna visita presente, effettuare l'operazione di deserializzazione sulla data desiderata");
					else
					{
						System.out.println("VISUALIZZA VISITE IN BASE ALL'ORDINE ALFABETICO");
						try 
						{
							listaVisite=Ordinatore.selectionSortCrescenteAlfabetoNodi(listaVisite);
							System.out.println("visualizzazione visite con nome e cognome dei pazienti crescente");
							System.out.println(listaVisite.toString());
						}
						catch (VisitaException e) 
						{
							System.out.println(e.toString());
						} 
						catch (ClassNotFoundException e) 
						{
							System.out.println("Impossibile caricare oggetti di tipo ListaVisite");
						}
						catch (IOException e) 
						{
							System.out.println("Impossibile completare il caricamento delle visite");
						}
						catch (FileException e) 
						{
							System.out.println("File non trovato");
						}
					}
					
					break;
				case 5:
					if(listaVisite.getElementi()==0)
						System.out.println("Nessuna data presente, effettuare l'operazione di deserializzazione sulla data desiderata");
					else
					{
						System.out.println("VISUALIZZA VISITE IN BASE ALLA DATA E ALL'ORA");
						try
						{
							listaVisite=Ordinatore.selectionSortCrescenteNodi(listaVisite);
							System.out.println("visualizzazione visite con data e orario crescente");
							System.out.println(listaVisite.toString());
						} 
						catch (VisitaException e) 
						{
							System.out.println(e.toString());
						} 
						catch (ClassNotFoundException e)
						{
							System.out.println("Impossibile caricare oggetti di tipo ListaVisite");
						} 
						catch (IOException e)
						{
							System.out.println("Impossibile completare il caricamento delle visite");
						} 
						catch (FileException e) 
						{
							System.out.println("File non trovato");
						}
					}
					
					break;
					
				default:
					break;
				}
	}while(scelta!=0);
	System.out.println("Sei uscito dall'applicazione");
}
}
	