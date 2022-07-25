package fr.eni.encheres.bll;

import fr.eni.encheres.dal.DALException;

public class UtilisateurManagerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UtilisateurManagerException(String message) {
		super(message);
	}

	public UtilisateurManagerException(String message, DALException e) {
		super(message,e);
	}
	
	
}
