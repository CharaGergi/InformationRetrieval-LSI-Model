package myLuceneApp;

// tested for lucene 7.7.2 and jdk13
import java.io.*;
import java.nio.file.Paths;
import java.util.List;

import com.sun.istack.internal.NotNull;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.ClassicSimilarity;
import org.apache.lucene.search.similarities.Similarity;
import org.apache.lucene.store.FSDirectory;
import txtparsing.QueryParsing;


public class SearcherDemo {

    String queryFile = "C://Users//Chara//Desktop//Ανάκτηση//CISI.QRY";
    File results = new File("results.txt");
    BufferedWriter txtWriter;


    public SearcherDemo(int k){
        try{
            String indexLocation = ("index"); //define where the index is stored
            String field = "contents"; //define which field will be searched

            //Access the index using indexReaderFSDirectory.open(Paths.get(index))
            IndexReader indexReader = DirectoryReader.open(FSDirectory.open(Paths.get(indexLocation))); //IndexReader is an abstract class, providing an interface for accessing an index.
            IndexSearcher indexSearcher = new IndexSearcher(indexReader); //Creates a searcher searching the provided index, Implements search over a single IndexReader.
            Similarity similarity = new ClassicSimilarity();
            indexSearcher.setSimilarity(similarity);
            
            //Search the index using indexSearcher
            search(indexSearcher, field, k);
            
            //Close indexReader
            indexReader.close();

            //Close bufferedWriter
            txtWriter.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * Searches the index given a specific user query.
     */
    private void search(IndexSearcher indexSearcher, String field, int k){
        try{
            // define which analyzer to use for the normalization of user's query
            Analyzer analyzer = new EnglishAnalyzer();
            
            // create a query parser on the field "contents"
            QueryParser parser = new QueryParser(field, analyzer);

            //Read the file that contains the queries
            List<String> queries = QueryParsing.parse(queryFile);

            //Create a buffer to write the results
            txtWriter = new BufferedWriter(new FileWriter( results));

            int i=0;
            //For all the queries contained in the file
            while(i<queries.size()){

                // parse the query according to QueryParser
                Query query = parser.parse(queries.get(i));

                // search the index using the indexSearcher
                TopDocs results = indexSearcher.search(query, k);
                ScoreDoc[] hits = results.scoreDocs;
                long numTotalHits = results.totalHits;

                i++;
                //display results
                for(int j=0; j<hits.length; j++){
                    Document hitDoc = indexSearcher.doc(hits[j].doc);
                    txtWriter.write(i +"    0" + "\t" + hitDoc.get("id")+ "\t0"  + "\t"+hits[j].score + "\tsearch\n");
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }

    }

    /**
     * Initialize a SearcherDemo
     */
    public static void main(String[] args){
        SearcherDemo searcherDemo = new SearcherDemo(30);
    }
}
