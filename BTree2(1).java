class BTree2{

    private Node head;
    private int n;

    public Btree2(int ordem){
		n = ordem;
    }

    public void printBtree(){
        
    }
    
    public void inserir(Page page){
        down(head, page);
    }

    //aqui, ele ja achou a folha onde deve inserir. agora eh verificar se ele pode colocar a pagina ali
    protected void insertFromLeaf(IntNode node, Page page){
            // insere a pagina caso tenha espaco
            if(!node.isFull()){
                if(node instanceof Leaf){
                    node.inserir(new Entry <Page>(page.getKey(), page));
                }else{
                    // aqui ele precisa saber quem sao o esquerda e direita... 
                    node.inserir(new Entry <IntNode>(page.getKey(), l, r));
                }
            }else{
                
                Node Lp; // L' irmao de L

                if(node instanceof Leaf){
                    Lp = new Leaf(n);                    
                else{
                    Lp = new IntNode(n);                   
                }

                node.split(Lp);

                // agora existe espaco em node e Lp. procurar para inserir a page
                // find retorna o indice da lista de chaves imediatamente menor que a chave de page.
                int k1 = node.find(page.getKey());

                if(k1 >= node.getSize()){
                    // inserir em Lp
                    if(Lp instanceof Leaf){
                        Lp.inserir(new Entry<Page>(page.getKey(), page));
                    }else{
                        // tem que entrar  l e o r no caso de node
                        Lp.inserir(new Entry<IntNode>(page.getKey(), l, r));                        
                    }
                }else{
                    // inserir em node
                    if(node instanceof Leaf){
                        node.inserir(new Entry<Page>(page.getKey(), page));
                    }else{
                        // tem que entrar  l e o r no caso de node
                        node.inserir(new Entry<IntNode>(page.getKey(), l, r));                        
                    }                    
                }

                // a chave da page deve ser atualizada agora que ela ja foi inserida
                page.setKey(node.getKey(node.getSize()-1));

                IntNode pai;
                if(node.hasPai()){
                    pai = node.getPai();                    
                }else{
                    // colocar dir e esq
                    pai = new IntNode(n, node, Lp);
                }
                // Lp ganha pai
                Lp.setPai(pai);
                // pai ganha filho
                insertFromLeaf(pai, page);
                //restaurando o pai do node
                node.setPai(pai);
            }
        }
    }

    // essa funcao so acha a folha que deve ser inserida a pagina
    private void down(Node node, Page page){
        if(node instanceof IntNode)){
            // nao eh folha.
            // deve descer. a posicao i na lsta do no eh a i-esima maior, logo deve descer na i-1 right ou i left.
            // como pode sair i = size, melhor ir na i-1 right
            
            int i = node.find(page.getKey());
            
            down(node.left(i), page);
        }else{
            insertFromLeaf(node, page);
        }
    }
}