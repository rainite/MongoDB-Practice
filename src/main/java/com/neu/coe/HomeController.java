package com.neu.coe;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.util.JSON;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(HttpServletRequest request){
	
		ArrayList<Movie> movielist = new ArrayList<Movie>();
        String sel = request.getParameter("selection");
        String desc = request.getParameter("desc");

        //1.Establish a connection to MongoDB
        //Note: Mongo class is superseded by MongoClient in 3.4
        MongoClient client = new MongoClient();

        //Step 2. Point to the DB you want to access
        //Note: DB class is superseded by MongoDatabase
        MongoDatabase db = client.getDatabase("movieLens");

        //Step 3. Get a reference to the Collection you want
        //Note: DBCollection is superseded by MongoCollection
        MongoCollection<Document> stocks = db.getCollection("movies"); 

        //Step 4. Query and Process the results    
        BasicDBObject query = new BasicDBObject(sel, desc);
        MongoCursor<Document> cursor;
        
		System.out.println(sel);

        
        if(desc == "" || desc == null){
    		System.out.println("IN null");

        	cursor = stocks.find().iterator();
        	while(cursor.hasNext()){
        		Document temp = cursor.next();
        		int movieid = Integer.parseInt(temp.get("MovieID").toString());
        		String title = temp.get("Title").toString();
        		String genre = temp.get("Genres").toString();
        		Movie movie = new Movie(movieid,title,genre);
        		movielist.add(movie);
        	}
        }else{
        	if (sel.equals("title")){
        		System.out.println("IN title");

              	cursor = stocks.find(Filters.regex("Title", desc )).iterator();
            	while(cursor.hasNext()){
            		Document temp = cursor.next();
            		int movieid = Integer.parseInt(temp.get("MovieID").toString());
            		String title = temp.get("Title").toString();
            		String genre = temp.get("Genres").toString();
            		System.out.println(movieid + title + genre);
            		Movie movie = new Movie(movieid,title,genre);
            		movielist.add(movie);
            	}

        		
        	}else if(sel.equals("movieid")){
        		System.out.println("IN movieid");

        		cursor = stocks.find(Filters.eq("MovieID", Integer.parseInt(desc))).iterator();
            	while(cursor.hasNext()){
            		Document temp = cursor.next();
            		int movieid = Integer.parseInt(temp.get("MovieID").toString());
            		String title = temp.get("Title").toString();
            		String genre = temp.get("Genres").toString();
            		System.out.println(movieid + title + genre);
            		Movie movie = new Movie(movieid,title,genre);
            		movielist.add(movie);
            	}
        	}else if(sel.equals("genre")){
        		System.out.println("IN genre");

        		cursor = stocks.find(Filters.regex("Genres", desc )).iterator();
            	while(cursor.hasNext()){
            		Document temp = cursor.next();
            		int movieid = Integer.parseInt(temp.get("MovieID").toString());
            		String title = temp.get("Title").toString();
            		String genre = temp.get("Genres").toString();
            		System.out.println(movieid + title + genre);
            		Movie movie = new Movie(movieid,title,genre);
            		movielist.add(movie);
            	}
        	}
  
        }
        client.close();
		return new ModelAndView("search-result","movielist",movielist);
	}
	
}
