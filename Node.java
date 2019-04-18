/*
	Rubens Anderson, 362984 - Universidade Federal do Ceara
	Ciencia da cmputacao
	implmentacao arvore B+ (incompleto)
*/

import java.lang.Math;
import java.util.*;

/*
suposicao: Se eu tenho espaco, entao eu tenho filhos na esquerda e direita ou eu sou a raiz.
*/ 

abstract class Node{
	
	protected int size = 0;
	protected int N;
	protected List <Entry> list = Collections.synchronizedList(new LinkedList <Entry>());
	protected Entry last;
	public IntNode pai = null;

		// 	===ABSTRATAS===

		// ===GETERS===

	public IntNode getPai(){
		return pai;
	}

	
	public Entry getEntry(int i){
		size = list.size();
		if(i >= size){
			return last;
		}
		return list.get(i);

	}


		// ===SETTERS===

	public void setPai(IntNode pai){
		this.pai = pai;
	}
		// ===STATUS===

	public boolean isFull(){
		return (size >= N-1);
	}

	// ===FUNCTIONS===

	// find retorna o indice da lista de chaves imediatamente menor que a chave key.
	// retorna -1 se o no esta vazio
	// pode retornar uma posicao a mais que nao existe
	public int find(int key){
		size = list.size();
		if(size == 0){
			return 0;
		}	
				
		for(int i = 0; i < size; i++){
			Entry e = list.get(i);
			
			if(key <= e.getKey()){				
				return i;
			}
		}
		return size;
	}
	// pelo que eu sei, passa um objto complexo por referencia... testar
	public void split(Node node){		
		List<Entry> l = split();
		node.inserirAll(l);
		// right do ultimo deve apontar pro node
		size = list.size();
	}

	// deve retornar metade das suas entradas
	// unsafe operations...
	private List<Entry> split(){
		
		List<Entry> l = Collections.synchronizedList(new LinkedList<Entry>());		
		
		size = list.size();
		int id = (int)Math.floor(size/2);
		l.add(last);
		for(int i = id; i < size-1; i++){
			l.add(list.remove(list.size()-1));
		}
		// right do ultimo deve apontar pro irmao
		size = list.size();
		last = list.remove(size-1);

		return l;
	}


	public Node left(int key){
		int index = find(key);
		return (Node)list.get(index).getLeft();
	}
	
	public int getKey(int i){
		if(i < size){
			return list.get(i).getKey();			
		}
		return 0;
	}

	public int getSize(){
		return list.size();
	}

	public boolean hasPai(){
		return (pai != null);
	}

	public void inserirAll(List <Entry> l){
		for(int i = 0; i < l.size(); i++){
			inserir(l.get(i));
		}
		//size = list.size();
	}

	public void inserir(Entry e){
		/*
			ha 3 casos: quero inserir a) no inicio, b) no meio, c) no fim da lista
			no inicio: list(0).left = e.right
			no meio: list(key-1).right = e.left; list(key).left = e.right
			no fim: list(key).right = e.left
		*/
		//size = list.size();
		if(size >= N){
			return;
		}
		if(size == 0){
			list.add(e);
			size++;
			return;
		}

		int key = find(e.getKey());

		if (key >= size){
			if(last != null){
				list.add(find(last.getKey()), last);
			}
			last = e;		
		}else{			
			list.add(key, e);
		}
		size = list.size();
	}

	public String twoString(String tabs) {
		
		String s = tabs + "\t";
		
		String S = " nchaves: " + size + "\n" + tabs + "{\n ";
		for(int i = 0; i < list.size(); i++){
			S = S + s + list.get(i).twoString(tabs) + "\n";
		}
		if(last != null)
			S = S + s + "last: "+last.twoString(tabs) + "\n";
		S = S + "\n" + tabs + "}\n";
		return S;
	}


}