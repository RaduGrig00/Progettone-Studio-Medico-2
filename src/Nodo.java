import java.io.Serializable;

public class Nodo implements Serializable
{
	private Visite info;
	private Nodo link;
	
	public Nodo(Visite visita)
	{
		setInfo(visita);
		link=null;
	}

	public Visite getInfo() 
	{
		return info;
	}

	public void setInfo(Visite info) 
	{
		this.info = info;
	}

	public Nodo getLink() 
	{
		return link;
	}

	public void setLink(Nodo link) 
	{
		this.link = link;
	}
	
	
}
