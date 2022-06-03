package Utilities;

public class FileOps {
	public static String getExtensionByStringHandling(String filename) {
	     if(filename.contains("."))
	     {
	    	 return filename.substring(filename.lastIndexOf(".")+1);
	     }
		 return null;
	}

}
