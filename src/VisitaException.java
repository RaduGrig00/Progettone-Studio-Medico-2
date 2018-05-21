
public class VisitaException extends Exception 
{
	private String messaggio;
	
	public VisitaException(String messaggio)
	{
		this.messaggio=messaggio;
	}
	
	public String toString()
	{
		return messaggio;
	}
}
