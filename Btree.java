/*
	Rubens Anderson, 362984 - Universidade Federal do Ceara
	Ciencia da cmputacao
	implmentacao arvore B+ (incompleto)
*/

public class Btree {
	private Node head;
    private int n;

    public Btree(int ordem){
        n = ordem;
        head = new Leaf(n);
    }

    public int getOrdem(){
        return n;
    }

    @Override
    public String toString() {
        if (head != null)
            return head.twoString("");
        return "";
    }

    
    public void inserir(Page page){
        Entry e = new Entry<Page>(page.getKey(), page);
        down(head, e);
    }

    // essa funcao so acha a folha que deve ser inserida a pagina
    private void down(Node node, Entry e1){
    
        if(node instanceof Leaf){
            insertFromLeaf(node, e1);
        }else{
            int i = node.find(e1.getKey());
            down((Node)node.getEntry(i).left, e1);
        }
        
    }

    //aqui, ele ja achou a folha onde deve inserir. agora eh verificar se ele pode colocar a pagina ali
    protected void insertFromLeaf(Node node, Entry e1){

        if(!node.isFull()){
            // basta inserir 
            node.inserir(e1);

        }else{
            // irmao de node
            System.out.println("...encheu...");
            Node nodep;
            if(node instanceof Leaf){
                nodep = new Leaf(n);
            }else{
                nodep = new IntNode(n);
            }

            node.split(nodep);

            int i = node.find(e1.getKey());
            if(i >= node.size){
                nodep.inserir(e1);
            }else{  
                node.inserir(e1);
            }
            
            Node pai;
 
            if(!node.hasPai()){                
                pai = new IntNode(n);
                
                node.setPai((IntNode) pai);
                head = pai;            
            }else{ 
                pai = node.pai;
            }

            // a chave dessa entrada deve ser o maximo do left
            Entry e2 = new Entry<Node>(node.getEntry(node.size-1).getKey(), node);
            Entry e3 = new Entry<Node>(nodep.getEntry(nodep.size-1).getKey(), nodep);
                       
            nodep.setPai((IntNode) pai);

            insertFromLeaf(pai, e2);
            insertFromLeaf(pai, e3);
        }
    }
}