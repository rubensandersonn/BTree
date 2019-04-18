/*
	Rubens Anderson, 362984 - Universidade Federal do Ceara
	Ciencia da cmputacao
	implmentacao arvore B+ (incompleto)
*/

import java.util.*;

public class Teste{

	public static void main(String args[]){
		contact();
	}

	public static void teste1(){

		int N = 3;
		List <Entry> lista = createEntries(N);
		
		System.out.println("\n\tLista...");
		System.out.println(lista.toString());
		
		// instanciando uma folha...
		System.out.println("\n\tFolhas apos split...");
		Leaf l = new Leaf(N);
		Leaf lp = new Leaf(N);
		l.inserirAll(lista);
		//l.split(lp);
		System.out.println(l.toString());
		//System.out.println(lp.toString());

		System.out.println(l.size);
		System.out.println(lista.size());
	}

	public static void contact(){
		String S = "";
		
		int N = 3;
		List <Page> lista = createPages(N);

		Btree BT = new Btree(N);
		for(int i=0; i<lista.size(); i++){
			BT.inserir(lista.get(i));
		}

		System.out.println(lista);

		Scanner entrada = new Scanner(System.in);

		System.out.println("=== Teste da arvore ===");
		
		while(!S.equals("sair")){
			
			System.out.println(BT.toString());
			System.out.println(" Digite pagina ou sair");
			S = entrada.nextLine();

			switch(S){
				case "ordem":
					System.out.println("a ordem da arvore eh: " + BT.getOrdem());
					break;
				case "pagina":
					System.out.println("entre com a chave para a nova pagina");
					int key = entrada.nextInt();

					System.out.println("entre com o conteudo para a nova pagina");
					String obj = entrada.next();

					Page p = new Page(key, obj);
					BT.inserir(p);
					break;
				default:
					//System.out.println(BT.toString());
					break;
			}
		}
		entrada.close();
	}



	public static void contact1(){
		String S = "";
		
		int N = 3;
		List <Page> lista = createPages(N-1);

		Btree BT = new Btree(N);
		for(int i=0; i<lista.size(); i++){
			//BT.inserir(lista.get(i));
		}

		System.out.println(lista);

		Scanner entrada = new Scanner(System.in);

		System.out.println("=== Teste da arvore ===");
		
		while(!S.equals("sair")){
			
			System.out.println(BT.toString());
			System.out.println(" Digite pagina ou sair");
			S = entrada.nextLine();

			switch(S){
				case "ordem":
					System.out.println("a ordem da arvore eh: " + BT.getOrdem());
					break;
				case "pagina":
					System.out.println("entre com a chave para a nova pagina");
					int key = entrada.nextInt();

					System.out.println("entre com o conteudo para a nova pagina");
					String obj = entrada.next();

					Page p = new Page(key, obj);
					BT.inserir(p);
					break;
				default:
					//System.out.println(BT.toString());
					break;
			}
		}
		entrada.close();
	}

	public static List<Node> createIntNos(int total, int ordem){
		List<Node> nos = new LinkedList<Node>();
		for(int i = 0; i < total; i++){
			nos.add(new IntNode(ordem));
		}
		return nos;
	}

	public static List<Page> createPages(int total){
		List<Page> nos = new LinkedList<Page>();
		for(int i = 0; i < total; i++){
			nos.add(new Page(i*3, "p" + i));
		}
		return nos;
	}
	public static List<Entry> createEntries(int total){
		List<Entry> nos = new LinkedList<Entry>();
		for(int i = 0; i < total; i++){
			nos.add(new Entry<Page>(i*3, new Page(i*3, "p" + i)));
		}
		return nos;
	}
}