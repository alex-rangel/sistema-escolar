package excecoes;

public class ExcecaoPersonalizada extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ExcecaoPersonalizada (String msg) {
		super(msg);
	}
	
}
