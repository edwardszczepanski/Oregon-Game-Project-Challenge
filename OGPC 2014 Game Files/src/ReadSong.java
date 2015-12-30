import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class ReadSong 
{
	//Path to the file
	private String path;
	//number of lines in the file
	private int numberOfLines;
	
	public ReadSong (String file_path)
	{
		//Set the path to the file_path that was passed to the class
		path = file_path;
		//Initializes numberOfLines to 0
		numberOfLines = 0;
	}
	
	//Opens, reads, and returns the file info
	public String[] OpenFile() throws IOException
	{
		//Creates a FileReader and BufferedReader to read from the file that you pass it
		FileReader fr = new FileReader(path);
		BufferedReader textReader = new BufferedReader(fr);
		
		//Set the variable to the number of lines in the text file through the function in this class that is defined below
		numberOfLines = readLines();
		//Creates an array of strings with size of how many lines of data there are in the file
		String[] textData = new String[numberOfLines];
		
		//Loop to read the lines from the text file for the song data
		for (int i = 0; i < numberOfLines; i++)
		{
			//Read data from song file and into the string list
			textData[i] = textReader.readLine();
		}
		
		//Close the BufferedReader that was opened
		textReader.close();
		
		//Return the read data from the text file in the form of a string array
		return textData;
	}
	
	//Returns how many lines of data there are in the file
	private int readLines() throws IOException
	{
		//Same as above, creating FileReader and BufferedReader for reading the file
		FileReader file_to_read = new FileReader(path);
		BufferedReader bf = new BufferedReader(file_to_read);
		
		//Temporary variable to hold the read line
		String aLine;
		//While the line is not null or there is nothing on the line
		while ((aLine = bf.readLine()) != null)
		{
			//Add to the numberOfLines to count how many lines there are
			numberOfLines++;
		}
		//Close the BufferedReader
		bf.close();
		
		//Return the number of lines in the text file
		return numberOfLines;
	}
	
	//Converts the array of strings read from the file into an array of ints
	public float[][] toIntList(String[] stringData)
	{
		//Create 2-d int list to return once everything is converted
		float[][] floatData = new float[numberOfLines][3];
		
		//Create a holding variable that will help to convert the String list over to an int list
		String[][] tempList = new String[numberOfLines][4];
		//List of deliminations that will be parsed out of the read strings in the string list that the function is passed
		String delims = "[,()]+";
		//Loop to split the strings read out of the file by the deliminations
		for (int i = 0; i < stringData.length; i++)
		{
			//Splits the strings from the file by the parenthesis and the commas
			tempList[i] = stringData[i].split(delims);
		}
		
		//Two loops to fully convert strings parsed by delims into ints
		for (int i = 0; i < tempList.length; i++)
		{
			for (int j = 0; j < tempList[i].length; j++)
			{
				//Get rid of bad reading and parsing by java (NOTE::When reading from file you will get a new line read as a string/character and demlims does not parse this out or even might create this problem by deliminating out the line as a character)
				if (j != 0)
				{
					//Parses the int out of the strings that were split by the deliminations
					floatData[i][j-1] = Float.parseFloat(tempList[i][j]);
				}
			}
		}
		
		//Returns the 2D int list that was parsed out of the string list
		return floatData;
	}
}