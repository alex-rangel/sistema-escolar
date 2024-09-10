package model;

public class Professor extends Usuario {
	
	private int matriculaProfessor;
	private String nome;
	private String especialidade;
	
	public Professor() {
		super();
	}

	public Professor(int id,String login,String senha,int tipo, int matriculaProfessor, String nome, String especialidade) {
		super(id, login, senha, tipo);
		this.matriculaProfessor = matriculaProfessor;
		this.nome = nome;
		this.especialidade = especialidade;
	}

	
	public int getMatriculaProfessor() {
		return matriculaProfessor;
	}

	public void setMatriculaProfessor(int matriculaProfessor) {
		this.matriculaProfessor = matriculaProfessor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
	
	
	@Override
	public String toString() {
		return super.toString()+"\n"+
			   "Matrícula aluno: "+this.matriculaProfessor+"\n"+
			   "Nome: "+this.nome+"\n"+
			   "Turno: "+this.especialidade;
	}
	
	@Override
	public void mostrar() {
		System.out.println("ID: "+ this.getId());
		System.out.println("Nome: "+this.nome);
		System.out.println("Matrícula: "+this.matriculaProfessor);
		System.out.println("Turno: "+this.especialidade);
		System.out.println("Tipo: "+this.getTipo());
		System.out.println("Login: "+this.getLogin());
		System.out.println("Senha: *****");
	}
	
}
