package produto;

public class Produto {
	private int id;
	private String descricao;
	private double valor;
	
	Produto(){
	}
	public Produto(int id,String descricao, double valor){
		this.setId(id);
		this.setDescricao(descricao);
		this.setValor(valor);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	
}
