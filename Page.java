/*
	Rubens Anderson, 362984 - Universidade Federal do Ceara
	Ciencia da cmputacao
	implmentacao arvore B+ (incompleto)
*/

public class Page{
    private int key;
    private String obj;

    public Page(int key){
        this.key = key;
    }
    public Page(int key, String obj){
        this.key = key;
        this.obj = obj;
    }
    public int getKey(){
        return key;
    }
    public String getObj(){
        return obj;
    }
    public void setObj(String obj){
        this.obj = obj;
    }
    public void setKey(int key){
        this.key = key;
    }

    
    public String twoString(String tabs) {
        
        //tabs = tabs + "\t";
        return tabs + "(" + key + ", " + obj + ")";
    }
}