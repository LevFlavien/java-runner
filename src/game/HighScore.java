package game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class HighScore {

	static File scoreFile = new File("score.txt");
	
	public static void setHighScore(int score)
	{
		if (score>getHighScore())
		{
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(scoreFile,false));// overwrite Highscore
				writer.write(score+"");
				writer.close();
				System.out.println("NEW HIGHSCORE"+ score +getHighScore());
			}
	    	catch (IOException ex) {
    	        System.err.println("ERROR setting scores to file");
    	}
		}
	}
	
	
	public static int getHighScore()
	    {
			int currentHighScore=0;
			if(!scoreFile.exists())
			{
	    		try {
					scoreFile.createNewFile();
					BufferedWriter writer = new BufferedWriter(new FileWriter(scoreFile,true));// overwrite Highscore
					writer.write("0 ");
					writer.close();
				} catch (IOException ex) {
	    	        System.err.println("ERROR creating file");
	    	}
	    		
			}
			try {
	    		
	    		BufferedReader reader = new BufferedReader(new FileReader(scoreFile));
	    		String line = reader.readLine();
	  
	    		currentHighScore=Integer.parseInt(line.trim());
	    		reader.close();
	    		System.out.println(currentHighScore);
	    	}
	    	catch (IOException ex) {
	    	        System.err.println("ERROR reading scores from file");
	    	}
	    	
			return currentHighScore;
	    }
}
