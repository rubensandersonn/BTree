/*
	Rubens Anderson, 362984 - Universidade Federal do Ceara
	Ciencia da cmputacao
	implmentacao arvore B+ (incompleto)

	No folha da arvore
*/

class Leaf extends Node{
    public Leaf next;
    public Leaf(int N){
		super.N = N;
	}
	public Leaf(int N, IntNode pai){
		super.N = N;
		super.pai = pai;
	}
	public Leaf(int N, Leaf next){
		super.N = N;
		this.next = next;
	}
	
	public String twoString(String tabs) {
		return "Leaf " + super.twoString(tabs);
	}

}