package fr.eni.encheres.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateurDAO {

	/**
	 * methode permettant d'inserer un nouvel utilisteur en BDD
	 * @param utilisateur une instance d'utilisateur 
	 * @throws DALException propagation d'une erreur de type DALException
	 */
	void insertUtilisateur (Utilisateur utilisateur) throws DALException;
	
	/**
	 * Modifie les informations de profil d'un utilisateur en base de donnee
	 * @throws DALException 
	 */
	void updateUtilisateur (Utilisateur utilisateur) throws DALException; 
	
	/**
	 * Recupere tous les utilisateurs dans la base de donnee
	 * @return List contenant tous les utilisateurs
	 * @throws DALException 
	 */
	List<Utilisateur> getAllUtil() throws DALException;
	
	/**
	 * Recupere un utilisateur en BDD par son ID(no_utilisateur)
	 * @return utilisateur
	 * @throws DALException
	 */
	Utilisateur getById(int id) throws DALException;

	/**
	 * Met a jour les credits des acheteurs quand une enchere est faite
	 * @throws DALException
	 */
	void updateCredit(int argent) throws DALException;

	/**
	 * Supprime un utilisateur (choix de l'utilisateur en cours de supprimer son profil)
	 * @throws DALException
	 */
	void deleteUtilisateur(Utilisateur user) throws DALException;
	
	/**
	 * Permet � un utilisateur de se connecter
	 * @return
	 * @throws DALException
	 * @throws SQLException
	 */
	Utilisateur connectionCompteUtilisateur(String eMailPseudo, String password) throws DALException;
	
}
