public class BLPaper extends DLPaper {

    public BLPaper(){
        
    }
    
    public BLPaper(int ID){
        super(ID);
    }
    
    public BLPaper(int ID, String title, String pAbstract, String citation){
        super(ID, title, pAbstract, citation);
    }
    
	@Override
	public String toString() {
		return title;
	}

}







