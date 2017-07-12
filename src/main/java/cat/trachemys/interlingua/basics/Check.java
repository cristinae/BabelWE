package cat.trachemys.interlingua.basics;

import java.io.File;

/**
 * Collection of checks
 * TODO This should be implemented within a logger, not with prints
 *  
 * @author cristinae
 * @since 05.05.2017
 */
public class Check {

	/**
	 * Check whether the directory exists and can be read.
	 * 
	 * @param dir  
	 * @return true if directory can be read
	 */
	public static boolean dirCanBeRead(File dir) {
		if (dir.isDirectory() && dir.canRead())
			return true;
		System.out.println(dir.toString() + " cannot be read.");
		return false;
	}

	/**
	 * Check that the given object is not null.
	 * 
	 * @param object
	 * @return true if the given object is not null
	 */
	public final static boolean notNull(final Object object){
		if (object == null) {
			System.out.println("Object is null.");
			return true;
		}
		return false;				
	}


}
