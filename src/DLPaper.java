
import java.util.ArrayList;
import java.sql.*;

public class DLPaper {
    int ID;
    String title;
    String pAbstract;
    String citation;
    
    public DLPaper(){
        
    }
    
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
    
    public void SetPaperId(int _ID) {
          ID = _ID;
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
    

       
    //Modify to include commit/rollback confirm delete: not done, unnecessary
    //Pop-up confirmation will occur before any SQL happens
    //Deletes a radio-button-selected paper from the papers table
    
    public void deletePaper() throws DLException{
        MySQLDatabase msd = MySQLDatabase.getInstance();        
        try{   
            ArrayList values = new ArrayList();
            values.add(ID);
            msd.startTrans();
            msd.setData("DELETE FROM authorship WHERE paperId = ?;", values);
            msd.setData("DELETE FROM paper_keywords WHERE id = ?;", values);
            msd.setData("DELETE FROM papers WHERE id = ?;", values);
            msd.endTrans();
        }
        catch(RuntimeException e){
            throw new DLException(e, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Error in deletePaper() of Papers");
        }
    }
    
    //Modify to include commit/rollback would you like to save changes? - unnecessary
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
    
    // Updated to be only get keywords, other part is done in fetch
     public String getPaperKeywords() throws DLException {
        String keyword = ""; 
        try{
            MySQLDatabase msd = MySQLDatabase.getInstance();  
            ArrayList values = new ArrayList();
            values.add(ID);
            ArrayList<ArrayList<String>> keywords = msd.getData("SELECT keyword FROM paper_keywords WHERE id = ?", values);
            
            for(int i = 0; i < keywords.size(); i++)
                for(int j = 0; j < keywords.get(0).size(); j++)
                    keyword = keyword + keywords.get(i).get(j) + ",";
            
        }
        catch(RuntimeException e){
            throw new DLException(e, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Error in editPaper() of Faculty");
        }
        return keyword;
    }
     
     public static void searchByTitle(String _title) throws DLException {
            String title = _title; 
            String[] arr = title.split(" ");    
            int size =  arr.length;
      // System.out.println(size);
            for (int i=0;i<size;i++){
       //System.out.println(arr[i]);
       
            String query = "select papers.title from papers where title like '%"+arr[i]+"%' order by id;";
            try{ 
            MySQLDatabase myDB = MySQLDatabase.getInstance();


            ArrayList<ArrayList<String>> result = myDB.getData(query);
  // results.add(result);
            for (int j=0; j < result.size();j++) {
            System.out.println(result.get(j));
                  }

               }
               catch(RuntimeException e){
                   throw new DLException(e, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "SQL string = " + query, "Error in fetch() of Faculty");
        }
        }
        }  
     
}
