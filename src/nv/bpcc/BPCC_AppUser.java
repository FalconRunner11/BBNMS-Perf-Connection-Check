/**
 *  This object class contains username and password information for a given user of the application.
 */

package nv.bpcc;

public class BPCC_AppUser {
	
	//-----------------------------------------------------------------//
	
	/** Declare and initialize final variables **/
	
	
	
	//-----------------------------------------------------------------//
	
	/** Declare fields **/
	
	private String username;
	private String password;
	
	private boolean hasPassword;
	
	//-----------------------------------------------------------------//
	
	/** Constructors **/
	
	protected BPCC_AppUser(String inc_username) {
		username = inc_username;
		hasPassword = false;
	}
	
	protected BPCC_AppUser(String inc_username, String inc_password) {
		username = inc_username;
		password = inc_password;
		hasPassword = true;
	}
	
	//-----------------------------------------------------------------//
	
	/** Abstract methods **/
	
	
	
	//-----------------------------------------------------------------//
	
	/** Implemented methods **/
	
	
	
	//-----------------------------------------------------------------//
	
	/** Accessor methods **/
	
	protected String getUsername() {
		return username;
	}
	
	protected String getPassword() {
		return password;
	}
	
	protected boolean hasPassword() {
		return hasPassword;
	}
	
	//-----------------------------------------------------------------//
	
	/** Mutator methods **/
	
	
	
	//-----------------------------------------------------------------//
	
	/** Protected methods **/
	
	
	
	//-----------------------------------------------------------------//
	
	/** Private methods **/
	
	
	
	//-----------------------------------------------------------------//
	
}
