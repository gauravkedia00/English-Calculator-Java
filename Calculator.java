import java.util.*;
import java.lang.String;

public class Calculator {
	
	public static void main(String args[]){
		String stopwords;
		String operations;
		int N = 100;
		int M = 10000;
		//Key:Value Pair list for Translation
		List<Pair> numbers = new ArrayList<Pair>();
		
		//To store Sentence as a List of Words
		List<String> words_list = new ArrayList<String>();
		
		//Original or Main List
		List<String> words_values = new ArrayList<String>();
		
		//List of All operations
		List<String> operations_list = new ArrayList<String>();
		
		//List of possible stopwords as per Test Case
		List<String> stopwords_list = new ArrayList<String>();
		
		//List Containing only Keys and Not Value for checking purpose
		List<String> keys = new ArrayList<String>();
		
		List<String> words_list_final = new ArrayList<String>();
		List<String> results = new ArrayList<String>();
		//String[] operations_list = {"plus", "minus", "multiplied", "divided"};
		//String[] stopwords_list = {"and", "by"};
		
		operations_list.add(new String("plus"));
		operations_list.add(new String("minus"));
		operations_list.add(new String("multiplied"));
		operations_list.add(new String("divided"));
		
		stopwords_list.add(new String("and"));
		stopwords_list.add(new String("by"));
		
		numbers.add(new Pair("zero",0));
		numbers.add(new Pair("one",1));
		numbers.add(new Pair("two",2));
		numbers.add(new Pair("three",3));
		numbers.add(new Pair("four",4));
		numbers.add(new Pair("five",5));
		numbers.add(new Pair("six",6));
		numbers.add(new Pair("seven",7));
		numbers.add(new Pair("eight",8));
		numbers.add(new Pair("nine",9));
		numbers.add(new Pair("ten",10));
		numbers.add(new Pair("eleven",11));
		numbers.add(new Pair("twelve",12));
		numbers.add(new Pair("thirteen",13));
		numbers.add(new Pair("fourteen",14));
		numbers.add(new Pair("fifteen",15));
		numbers.add(new Pair("sixteen",16));
		numbers.add(new Pair("seventeen",17));
		numbers.add(new Pair("eighteen",18));
		numbers.add(new Pair("nineteen",19));
		numbers.add(new Pair("twenty",20));
		numbers.add(new Pair("thirty",30));
		numbers.add(new Pair("forty",40));
		numbers.add(new Pair("fifty",50));
		numbers.add(new Pair("sixty",60));
		numbers.add(new Pair("seventy",70));
		numbers.add(new Pair("eighty",80));
		numbers.add(new Pair("ninety",90));
		numbers.add(new Pair("hundred",100));
		numbers.add(new Pair("thousand",1000));
		
		//Test String
		String request;
		System.out.println("Enter your string");
		Scanner in = new Scanner(System.in);
		request = in.nextLine();
		
		//Split the String as separate words
		String[] words = request.split(" ");
		for(int i=0; i < words.length; i++){
			words_list.add(new String(words[i]));
		}

		/*
		for(Pair pair : numbers) {
			System.out.println(pair.getKey() + ": " + pair.getValue());
		}
		*/
		
		//print out all the elements of a List
		/*
		for(int i=0; i < numbers.size(); i++){
			System.out.println(numbers.get(i).getKey());
		}
		*/
		//System.out.println(Arrays.toString(words));
		//System.out.println(words_values);
		//System.out.println(operations_list);
		//System.out.println(stopwords_list);
		//System.out.println(Arrays.toString(operations));
		
		//List containing all elements of only Key from a Pair
		for(int j=0; j < numbers.size(); j++){
			keys.add(new String(numbers.get(j).getKey()));
		}
		
		//Steps to check if the String is Valid
		for(int i=0; i < words.length; i++){
			if( ( keys.contains(new String(words[i])) ) || ( operations_list.contains(new String(words[i])) ) || ( stopwords_list.contains(new String(words[i])) ) )
			{	
				for(int j=0; j < numbers.size(); j++){
					//Check Numbers
					if(words[i].equals(numbers.get(j).getKey().toString()))
					{
						String first = numbers.get(j).getValue().toString();
						words_values.add(new String(first));
						break;
					}
					//Check Operations
					//else if (operations_list.contains(words[i]))
					else if ( operations_list.contains(new String(words[i])) )
					{
						operations = words[i];
						words_values.add(new String(operations));
						break;
					}
					//Check Stopwords
					//else if (stopwords_list.contains(words[i]))
					else if ( stopwords_list.contains(new String(words[i])) )
					{
						stopwords = words[i];
						words_values.add(new String(stopwords));
						break;
					}
				}
			}
			//Null for Invalid
			else
			{
				words_values = new ArrayList<String>();
				break;
			}
		}
		//System.out.println(words_values);
		
		//Stopwords Removal as we don't need them
		for(int j=0; j < words_values.size(); j++){
			if ( words_values.get(j).equals("and") || words_values.get(j).equals("by") )
			{
				words_values.remove(j);
			}
		}
		
		//Steps to get actual two variable
		int temp = 0;
		for(int j=0; j < words_values.size(); j++){
			if ( words_values.get(j).equals("100") || words_values.get(j).equals("1000") )
			{
				temp = Integer.parseInt(words_values.get(j-1)) * Integer.parseInt(words_values.get(j));
				words_values.remove(j-1);
				words_values.set(j-1,Integer.toString(temp));
			}
		}
		//To get variable value Before Operation
		int x = 0;
		for(int i=0; i < words_values.size(); i++){
			if ( operations_list.contains(new String(words_values.get(i))) )
			{
				int k = i;
				int temp2 = 0;
				for (int l=0; l < k; l++)
				{
					temp2 = temp2 + Integer.parseInt(words_values.get(l));
				}
				//words_list_final.add(new String(Integer.toString(temp2)));
				x = temp2;
				break;
			}
		}
		//To get variable value After Operation
		int y = 0;
		for(int j=0; j < words_values.size(); j++){
			if ( operations_list.contains(new String(words_values.get(j))) )
			{
				int k = j;
				int temp3 = 0;
				for (int l=words_values.size()-1; l > k; l--)
				{
					temp3 = temp3 + Integer.parseInt(words_values.get(l));
				}
				//words_list_final.add(new String(Integer.toString(temp3)));
				y = temp3;
				break;
			}
		}
		//System.out.println("Variable One is " +x + " Variable Two is " +y);
		int z = 0;
		int rem = 0;
		if(words_values.contains("plus") && x<=100 && y<=100)
			z = x + y;
		else if (words_values.contains("minus") && x<=100 && y<=100)
			z = x - y;
		else if (words_values.contains("multiplied") && x<=100 && y<=100)
			z = x * y;	
		else if (words_values.contains("divided") && x<=10000 && y<=10000)
		{
			z = x / y;
			rem = x%y;
		}
		else
			words_values = new ArrayList<String>();

		String ans = Integer.toString(z);
		//System.out.println("Output " +ans);
		char[] stringToCharArray = ans.toCharArray();
		for (char output : stringToCharArray) {
			words_list_final.add(new String(Character.toString(output)));
		}
		//System.out.println(words_list_final);
		String temp4;
		if(words_list_final.size() == 3)
		{
			temp4 = words_list_final.get(1) + "0";
			words_list_final.set(1,temp4);
		}
		else if(words_list_final.size() == 4)
		{
			temp4 = words_list_final.get(2) + "0";
			words_list_final.set(2,temp4);
		}
		else if(words_list_final.size() == 2)
		{
			temp4 = words_list_final.get(0) + "0";
			words_list_final.set(0,temp4);
		}
		
		//Convert numbers to letters
		for(int i=0; i < words_list_final.size(); i++){
			for(int j=0; j < numbers.size(); j++){
				if(words_list_final.get(i).equals(numbers.get(j).getValue().toString()))
				{
					String temp5 = numbers.get(j).getKey().toString();
					words_list_final.set(i,temp5);
					break;
				}
			}
		}
		
		if(words_list_final.size() == 4)
		{
			words_list_final.add(1,"thousand");
			words_list_final.add(3,"hundred and");
		}
		else if(words_list_final.size() == 3)
		{
			words_list_final.add(1,"hundred and");
		}
		
		if(words_list_final.get(words_list_final.size()-1).equals("zero") && words_list_final.size() != 1)
			words_list_final.remove(words_list_final.size()-1);
		
		for(int i=0; i < words_list_final.size(); i++)
		{
			if(words_list_final.get(i).equals("00"))
			{
				words_list_final.remove(i);
			}
		}
		
		if(words_list_final.get(0).equals("-0") )
			words_list_final.set(0,"minus");
		
		
		if(words_list_final.get(words_list_final.size()-1).equals("hundred and") )
			words_list_final.set(words_list_final.size()-1,"hundred");
		
		System.out.println(words_list_final);
	}
}

class Pair {
	private String key;
	private Integer value;
	public Pair(String key, Integer value){
		this.key = key;
		this.value = value;
	}
	public String getKey() { return key; }
	public Integer getValue() { return value; }
}
