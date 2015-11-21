
import java.util.ArrayList;

public class DLPaper {
    int ID;
    String title;
    String pAbstract;
    String citation;
    
    public DLPaper(int ID){
        this.ID = ID;
    }
    
    public DLPaper(int ID, String title, String pAbstract, String citation){
        this.ID = ID;
        this.title = title;
        this.pAbstract = pAbstract;
        this.citation = citation;
    }
    
    public String getTitle(){
        return title;
    }
    
    
    
    
    //Gets values from papers table and loads them into the database
    public boolean fetch() throws DLException{
        MySQLDatabase msd = MySQLDatabase.getInstance();        
        String sql = "SELECT * FROM papers WHERE ID = ?;";
        ArrayList<String> paperID = new ArrayList();
        paperID.add(Integer.toString(ID));
        try{
            ArrayList<ArrayList<String>> rs = msd.getData(sql, paperID);
            title = (rs.get(0)).get(1);
            pAbstract = (rs.get(0)).get(2);
            citation = (rs.get(0)).get(3);
            return true;
        }
        catch(RuntimeException e){
            throw new DLException(e, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "SQL string = " + sql, "Error in fetch() of Faculty");
        }
    }
    
    //Loads details of radio-button-selected paper into the user interface to allow editing
    //Used also in search by faculty to display paper details
       
       
    //Modify to include commit/rollback confirm delete
    //Deletes a radio-button-selected paper from the papers table
    //Make sure cascading delete works, otherwise put all deletes in transaction
    public void deletePaper() throws DLException{
        MySQLDatabase msd = MySQLDatabase.getInstance();        
        try{   
            ArrayList values = new ArrayList();
            values.add(ID);
            msd.setData("DELETE FROM authorship WHERE id = ?;", values);
            msd.setData("DELETE FROM paper_keywords WHERE id = ?;", values);
            msd.setData("DELETE FROM papers WHERE id = ?;", values);
        }
        catch(RuntimeException e){
            throw new DLException(e, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Error in deletePaper() of Papers");
        }
    }
    
    //Modify to include commit/rollback would you like to save changes?
    //Updates the papers table to reflect updated details for a paper. Updates keywords in paper_keywords
    public void save(String title, String pAbstract, String citation, String keywords, int facID) throws DLException{
        MySQLDatabase msd = MySQLDatabase.getInstance();        
        try{
            ArrayList values = new ArrayList();
            values.add(title);
            values.add(pAbstract);
            values.add(citation);
            values.add(ID);
            
            ArrayList oldKeywords = new ArrayList();
            oldKeywords.add(ID);
            
            String[] temp = keywords.split(",");
            ArrayList keywordList = new ArrayList();
            keywordList.add(ID);
            keywordList.add(temp[0]);
            
            msd.startTrans();
            msd.setData("UPDATE papers SET title = ?, abstract = ?, citation = ? WHERE id = ?;", values);
            msd.setData("DELETE FROM paper_keywords WHERE id = ?", oldKeywords);
            for(int i = 0; i < temp.length; i++){
                keywordList.set(1, temp[i]);
                msd.setData("INSERT INTO paper_keywords VALUES(?, ?);", keywordList);
            }
            msd.endTrans();
        }
        catch(RuntimeException e){
            throw new DLException(e, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Error in save() of Papers");
        }
    }
    
    
    //Adds a paper to the papers table, adds keywords to paper_keywords table
    public void addPaper(String keywords, int facID) throws DLException{
        MySQLDatabase msd = MySQLDatabase.getInstance();        
        try{
            ArrayList values = new ArrayList();
            values.add(ID);
            values.add(title);
            values.add(pAbstract);
            values.add(citation);
            
            ArrayList authorship = new ArrayList();
            authorship.add(facID);
            authorship.add(ID);
            
            ArrayList keywordList = new ArrayList();          
            String[] temp = keywords.split(",");
            keywordList.add(ID);
            keywordList.add(temp[0]);
            
            msd.startTrans();
            msd.setData("INSERT INTO papers(id, title, abstract, citation) VALUES(?, ?, ?, ?);", values);
            msd.setData("INSERT INTO authorship(facultyID, paperID) VALUES(?, ?)", authorship);
            for(int i = 0; i < temp.length; i++){
                keywordList.set(1, temp[i]);
                msd.setData("INSERT INTO paper_keywords VALUES(?, ?);", keywordList);
            }
            msd.endTrans();
        }
        catch(RuntimeException e){
            throw new DLException(e, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Error in addPaper() of Papers");
        }
    
    }
    
    
    public void getAllPapersdependsonTitle(String title) throws SQLException {
    
    // Nazar
    }


   public void searchByKeyWords(String key) throws SQLException {
   
   
   // Nazar
   }
   
    public static ArrayList<ArrayList<String>> searchByKeywords(String keyword) throws DLException{

        ArrayList<ArrayList<String>> paperList;

        try{
            MySQLDatabase msd = MySQLDatabase.getInstance();  
            ArrayList values = new ArrayList();
            values.add(keyword);
            paperList = msd.getData("SELECT papers.id, title FROM papers JOIN paper_keywords ON papers.id = paper_keywords.id WHERE keyword = ?", values);
            
        }
        catch(RuntimeException e){
            throw new DLException(e, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Error in searchByKeywords() of Faculty");
        }
        return paperList;
    }
    
    
   
    // By Katei separate Keywords
     public ArrayList<String> getPaperByID() throws DLException {
        ArrayList<ArrayList<String>> paper;
        ArrayList<String> returnArray = new ArrayList();
        try{
            MySQLDatabase msd = MySQLDatabase.getInstance();  
            ArrayList values = new ArrayList();
            values.add(ID);
            paper = msd.getData("SELECT title, abstract, citation FROM papers WHERE id = ?", values);
            ArrayList<ArrayList<String>> keywords = msd.getData("SELECT keyword FROM paper_keywords WHERE id = ?", values);
            for(int i = 0; i < paper.get(0).size(); i++)
                returnArray.add(paper.get(0).get(i));
            
            String keyword = "";
            for(int i = 0; i < keywords.size(); i++)
                for(int j = 0; j < keywords.get(0).size(); j++)
                    keyword = keyword + keywords.get(i).get(j) + ",";
            returnArray.add(keyword);
            
        }
        catch(RuntimeException e){
            throw new DLException(e, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Error in editPaper() of Faculty");
        }
        return returnArray;
    }


}
