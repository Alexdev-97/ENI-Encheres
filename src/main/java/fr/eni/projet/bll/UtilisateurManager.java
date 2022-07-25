package fr.eni.encheres.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateurDAO;

public class UtilisateurManager {
	
	//singleton 
	private static UtilisateurManager instanceUtilisateur;
	
	public static UtilisateurManager getInstance(){
		if(instanceUtilisateur==null){
			instanceUtilisateur =  new UtilisateurManager();
		}
		return instanceUtilisateur;
	}
	
	private UtilisateurManager () {
		
	}
	
	List <Utilisateur> listeUtilisateur = new ArrayList<>();
	UtilisateurDAO daoUser = DAOFactory.getUtilisateurDAO(); 
	
	/**
	 * Permet de creer un compte utilisateur si celui-ci n'existe pas deja
	 * @param utilisateur
	 * @throws UtilisateurManagerException
	 */

	public void CreerCompteUtilisateur(Utilisateur utilisateur) throws UtilisateurManagerException {
		
		try {
			daoUser.insertUtilisateur(utilisateur);

		}catch (DALException e) {
			throw new UtilisateurManagerException ("Erreur dans la création de compte",e);
		}

	}

	/**
	 * Permet de modifier un compte deja existant (uniquement possible pour le proprietaire du compte)
	 * @param utilisateur
	 * @throws UtilisateurManagerException
	 */

	public void ModifierCompteUtilisateur(Utilisateur utilisateur) 
			throws UtilisateurManagerException {
		
		try {daoUser.updateUtilisateur(utilisateur);
		
		}catch (DALException e) {
			throw new UtilisateurManagerException ("Erreur dans la modification du profil",e);
			
		}

	}
	/**
	 * permet de recuperer tous les utilisateurs
	 * @return
	 * @throws UtilisateurManagerException
	 */
	public List<Utilisateur> GetAllUtilisateur() throws UtilisateurManagerException {
		
		try {daoUser.getAllUtil();
		}catch (DALException e) {
			throw new UtilisateurManagerException ("Erreur récupération liste des utilisateurs",e);
	}
	
		return listeUtilisateur;
	}
	
	
	/**
	 * Recupere un utilisateur specifique par son ID
	 * @param idUtilisateur
	 * @return
	 * @throws UtilisateurManagerException
	 */
	
	public Utilisateur GetUtilisateurID(int idUtilisateur) throws UtilisateurManagerException {
		
		Utilisateur utilisateur = null;
		
		try {
			daoUser.getById(idUtilisateur);
		} catch (DALException e) {
			throw new UtilisateurManagerException("Erreur dans la récupération de l'identifiant de l'utilisateur ",e);
		}
		return utilisateur;

	}
	/**
	 * Permet de se connecter si on a un compte 
	 * @param email
	 * @param password
	 * @return
	 * @throws UtilisateurManagerException
	 * @throws SQLException
	 */
	public Utilisateur ConnectionCompteUtilisateur(String emailPseudo, String password)
			throws UtilisateurManagerException, SQLException {


		try {
			daoUser.connectionCompteUtilisateur (emailPseudo, password);						
		} catch (DALException e) {
			throw new UtilisateurManagerException("Erreur dans la connexion utilisateur",e);
		}
		return null;

	}
	/**
	 * permet de supprimer un compte
	 * @param utilisateur
	 * @throws UtilisateurManagerException
	 */
	public void SupprimerCompteUtilisateur(Utilisateur utilisateur) throws UtilisateurManagerException {
		try {
			daoUser.deleteUtilisateur(utilisateur);
		} catch (DALException e) {
			throw new UtilisateurManagerException("Erreur dans la suppression du compte",e);
		}

	}
	/**
	 * Verifie que l'encherisseur a les credits necessaires pour encherir
	 * @param utilisateur
	 * @param creditNecessaire
	 * @return
	 */
	public Boolean VerificationCredit(Utilisateur utilisateur, int creditNecessaire) {
		Boolean autorisation = false;

		if (creditNecessaire <= utilisateur.getCredit()) {
			autorisation = true;
		}

		return autorisation;
	}
}
