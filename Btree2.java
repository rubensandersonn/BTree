

public class Btree2 {
	private Node head;
    private int n;

    public Btree2(int ordem){
        n = ordem;
        head = new Leaf(n);
    }

    @Override
    public String toString() {
        if (head != null)
            return head.toString();
        return "";
    }

    
    public void inserir(Page page){
        down(head, page);
        System.out.println(this.toString());
    }

    // essa funcao so acha a folha que deve ser inserida a pagina
    private void down(Node node, Page page){
    
        if(node instanceof Leaf){
            insertFromLeaf(node, page);
        }else{
            int i = node.find(page.getKey());
            if(i >= node.size){
                if(node.getEntry(i-1).right != null)
                    down((Node)node.getEntry(i-1).right, page);
            }else{  
                down((Node)node.getEntry(i).left, page);
            }
        }
        
    }

    //aqui, ele ja achou a folha onde deve inserir. agora eh verificar se ele pode colocar a pagina ali
    protected void insertFromLeaf(Node node, Page page){
        if(!node.isFull()){
            // basta inserir 
            if (node instanceof Leaf){
                Entry e = new Entry<Page>(page.getKey(), page);
                node.inserir(e);
            }else{
                Entry e = new Entry<IntNode>(page.getKey());
                //e.setKey(node.getEntry(node.size-1).getKey());
                node.inserir(e);
            }

        }else{
           
            Node nodep;
            if(node instanceof Leaf){
                nodep = new Leaf(n);
            }else{
                nodep = new IntNode(n);
            }

            node.split(nodep);

            // a chave dessa entrada deve ser o maximo do left

            Node pai;
            Entry e1;
            if(!node.hasPai()){
                
                pai = new IntNode(n);
                
                int at = 0;
                if(node.size > 0){
                    at = node.size-1;
                } 

                e1 = new Entry<Node>(node.getEntry(at).getKey(), node, nodep);
                e1.setKey(node.getEntry(node.size-1).getKey());
                
                pai.inserir(e1);
                
                node.setPai((IntNode) pai);
                head = pai;
                
            }else{ 
                pai = node.pai;
                e1 = new Entry<Node>(node.getKey(node.size - 1),  node, nodep);
                
                pai.inserir(e1);
            }
            nodep.setPai((IntNode) pai);

            // decidindo se insere no node ou nodep
            int i = node.find(page.getKey());
            if(i >= node.size){
                nodep.inserir(e1);
            }else{  
                node.inserir(e1);
            }

            //down(pai, page);
            //insertFromLeaf(pai, page);
        }
    }
}