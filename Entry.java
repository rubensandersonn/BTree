/*
	Rubens Anderson, 362984 - Universidade Federal do Ceara
	Ciencia da cmputacao
	implmentacao arvore B+ (incompleto)
*/

// as entradas podem conter nos ou paginas
class Entry <T>{
	
	private int key;
	public T left = null;	

	public Entry(int key){
		this.key = key;
	}
	
	public Entry(int key, T l){
		this.key = key;
		left = l;
	}

	public int getKey(){
		return key;
	}
	public T getLeft(){
		return left;
	}
	
	public void setLeft(T n){
		left = n;
	}
	public void setKey(int k){
		key = k;
	}
	

	//@Override
	public String twoString(String tabs) {
		String S = "#" + key;
		//S = S + "\n\t[key: " + key + "]";
		String ta = "";
		tabs = tabs + "\t";
		if(left != null){
			if (left instanceof Node){
				Node nn = (Node)left;
				S = S + "[ P: \n" + tabs + nn.twoString(tabs) + "\n" + tabs + "]";
			}else{				
				Page nn = (Page)left;
				S = S + "[ P: \n" + tabs+ nn.twoString(tabs) + "\n" + tabs+ "]";
			}
		}
		
		//S = S + "]";
		return S;
	}
}