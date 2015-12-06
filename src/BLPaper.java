public class BLPaper extends DLPaper {

    /* Default Constructor */
    public BLPaper(){
        
    }
    
    /*Pramaterized Constructor*/
    public BLPaper(int ID){
        super(ID);
    }
    
    public BLPaper(int ID, String title, String pAbstract, String citation){
        super(ID, title, pAbstract, citation);
    }
        /* Get paper's title* to combobox*/
	@Override
	public String toString() {
		return title;
	}

}







