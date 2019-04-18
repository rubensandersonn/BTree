class EntryPage extends Entry{
    private Page page;
    public EntryPage(int key){
        super(key);
    }
    public EntryPage(int key, Page p){
        super(key);
        page = p;
    }

    public Page getPage(){
        return page;
    }
}