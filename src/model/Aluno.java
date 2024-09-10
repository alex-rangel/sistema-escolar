package model;

public class Aluno extends Usuario{
	private int matriculaAluno;
	private String nome;
	private String turno;
	private String turma;
	
	public Aluno() {
		super();
	}
	
	public Aluno(int id, String login, String senha, int tipo, int matriculaAluno, String nome, String turno, String turma) {
		super(id, login, senha, tipo);
		this.matriculaAluno = matriculaAluno;
		this.nome = nome;
		this.turno = turno;
		this.turma = turma;
	}

	public int getMatriculaAluno() {
		return matriculaAluno;
	}

	public void setMatriculaAluno(int matriculaAluno) {
		this.matriculaAluno = matriculaAluno;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}
	
	@Override
	public String toString() {
		return super.toString()+"\n"+
			   "Matrícula aluno: "+this.matriculaAluno+"\n"+
			   "Nome: "+this.nome+"\n"+
			   "Turno: "+this.turno+"\n"+
			   "Turma: "+this.turma;
	}
	
	@Override
	public void mostrar() {
		System.out.println("ID: "+ this.getId());
		System.out.println("Nome: "+this.nome);
		System.out.println("Matrícula: "+this.matriculaAluno);
		System.out.println("Turno: "+this.turno);
		System.out.println("Turma: "+this.turma);
		System.out.println("Tipo: "+this.getTipo());
		System.out.println("Login: "+this.getLogin());
		System.out.println("Senha: *****");
	}
	
	
	
	
	
	
}
