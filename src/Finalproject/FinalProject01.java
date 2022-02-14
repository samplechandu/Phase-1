package Finalproject;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class FinalProject01 {
	static String DIRECTORY;
	File folder_name;
	
	public FinalProject01() {
		DIRECTORY = System.getProperty("user.directory");
		folder_name = new File(DIRECTORY+"/files");
		if(!folder_name.exists())
			folder_name.mkdirs();
		System.out.println("DIRECTORY : "+ folder_name.getAbsolutePath());
	}
	private static final String WELCOME_PROMPT = "----Phase-1Project----";
	private static final String MAIN_MENU_PROMPT = "\nMAIN MENU - Select any of the following: \n" +
	                                                  "1 -> List files in directory\n" +
			                                          "2 -> ADD, Delete or Search\n" +
	                                                  "3 -> Exit Program";
	private static final String SECONDARY_MENU_PROMPT = "\nSelect any of the following: \n" +
	                                                      "a -> Add a data\n" +
			                                              "b -> Delete a data\n" +
	                                                      "c -> Search a data\n"+
			                                              "d -> Back";
	void showPrimaryMenu() {
		System.out.println(MAIN_MENU_PROMPT);
		try(Scanner sc = new Scanner(System.in)) {
			int option = sc.nextInt();
			switch(option) {
			case 1: {
				showFiles();
				showPrimaryMenu();
			}
			case 2: {
				showSecondaryMenu();
			}
			case 3: {
				System.out.println("Thank you -> Visit Again");
				System.exit(0);
			}
			default: showPrimaryMenu();
			}
		}
		catch (Exception e) {
			System.out.println("Please enter 1.2 or 3");
			showPrimaryMenu();
		}
	}
	 void showSecondaryMenu()  {
		 System.out.println(SECONDARY_MENU_PROMPT);
		 try(Scanner sc = new Scanner(System.in)) {
			 char[] input = sc.nextLine().toLowerCase().trim().toCharArray();
			 char option = input[0];
			 switch (option) {
			 case 'a': {
				 System.out.println("Adding data----Please enter the data name:");
				 
				 String filename = sc.next().trim().toLowerCase();
							try {
								addFile(filename);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				 break;
			 }
			 case 'b': {
				 System.out.println("Deleting data----Please enter the data name:");
				 String filename = sc.next().trim();
				 deleteFile(filename);
				 break;
			 }
			 case 'c': {
				 System.out.println("Searching data----Please enter the data name:");
				 String filename = sc.next().trim();
				 searchFile(filename);
				 break;
			 }
			 case 'd': {
				 System.out.println("Going Back to MAIN menu");
				 showPrimaryMenu();
				 break;
			 }
			 default : System.out.println("Please enter a,b,c or d");
			 }
			 showSecondaryMenu();
		 }
	}
	 void showFiles() {
		 if(folder_name.list().length==0)
			 System.out.println("no files found"); 
		 else {
			 String[] list = folder_name.list();
			 System.out.println("The dates in "+ folder_name +" are :");
			 Arrays.sort(list);
			 for(String str:list) {
				 System.out.println(str);
			 }
	 }
}
	 void addFile(String filename) throws IOException {
		 File filepath = new File(folder_name +"/"+filename);
		 String[] list = folder_name.list();
		 for(String file:list) {
			 if(filename.equalsIgnoreCase(file)) {
				 System.out.println("data "+filename+ "already exists at" +folder_name);
				 return;
			 }
		 }
		 filepath.createNewFile();
		 System.out.println("Data "+filename+" added to "+folder_name);
	 }
	 void deleteFile(String filename) {
		 File filepath = new File(folder_name +"/"+filename);
		 String[] list = folder_name.list();
		 for(String file:list) {
			 if(filename.equals(file) && filepath.delete()) {
				 System.out.println("data " +filename+ "deleted from" +folder_name);
				 return;
			 }
		 }
		 System.out.println("Delete operation failed. File not Found"); }
	 void searchFile(String filename) {
		 File filepath = new File(folder_name +"/"+filename);
		 String[] list = folder_name.list();
		 for(String file:list) {
			 if(filename.equals(file)) {
				 System.out.println("Found: data " +filename+ "already exists at" +folder_name);
				 return;
			 }
		 }
		 System.out.println("Data not found"); }
	 public static void main(String[] args) {
		 System.out.println(WELCOME_PROMPT);
		 FinalProject01 menu = new FinalProject01();
		 menu.showPrimaryMenu();
	 
}


}

