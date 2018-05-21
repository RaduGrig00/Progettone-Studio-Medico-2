import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
public class Visite implements Serializable
{
	//Attributi
	private int id;
	private String nomeP;
	private String cognomeP;
	private String nomeM;
	private String cognomeM;
	private Boolean visitaSvolta=false;
	private LocalDateTime dataOra;
	
	
	//Costruttori
	public Visite (int id, String nomeP,String cognomeP,String nomeM,String cognomeM)
	{
		setId(id);
		setNomeP(nomeP);
		setCognomeP(cognomeP);
		setNomeM(nomeM);
		setCognomeM(cognomeM);
		setVisitaSvolta(visitaSvolta);
		setDataOra(null);
	}
	
	public Visite (Visite i)
	{
		setId(i.getId());
		setNomeP(i.getNomeP());
		setCognomeP(i.getCognomeP());
		setNomeM(i.getCognomeM());
		setCognomeM(i.getCognomeM());
		setVisitaSvolta(i.getVisitaSvolta());
		setDataOra(i.getDataOra());
	}
	
	public Visite()
	{
		
		setId(0);
		setNomeP("");
		setCognomeP("");
		setNomeM("");
		setCognomeM("");
		setVisitaSvolta(false);
		setDataOra(null);
	}
	//getter e setter
	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getNomeP() 
	{
		return nomeP;
	}

	public void setNomeP(String nomeP) 
	{
		this.nomeP = nomeP;
	}

	public String getCognomeP() 
	{
		return cognomeP;
	}

	public void setCognomeP(String cognomeP) 
	{
		this.cognomeP = cognomeP;
	}

	public String getNomeM() 
	{
		return nomeM;
	}

	public void setNomeM(String nomeM) 
	{
		this.nomeM = nomeM;
	}

	public String getCognomeM() 
	{
		return cognomeM;
	}

	public void setCognomeM(String cognomeM) 
	{
		this.cognomeM = cognomeM;
	}

	public Boolean getVisitaSvolta() 
	{
		return visitaSvolta;
	}

	public void setVisitaSvolta(Boolean visitaSvolta) 
	{
		this.visitaSvolta = visitaSvolta;
	}

	public LocalDateTime getDataOra() 
	{
		return dataOra;
	}

	public void setDataOra(LocalDateTime dataOra) 
	{
		this.dataOra = dataOra;
	}
	
	public String toString()
	{
		return getId()+"/"+" Nome e cognome del paziente: "+getNomeP()+" "+getCognomeP()+"/ Nome e cognome del medico: "+getNomeM()+" "+getCognomeM()+"/ Data e ora della visita: "+getDataOra()+".";
	}

	
	/*public String toString() 
	{
		return(getId()+" "+getNomeP()+" "+getCognomeP()+getNomeM()+" "+getCognomeM()+" "+getVisitaSvolta()+" "+getData());
	}*/
	

}
