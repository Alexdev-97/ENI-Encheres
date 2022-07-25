package fr.eni.projet.dal;

import java.sql.Connection;

import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.dal.DALException;
import fr.eni.projet.bo.Article;
import fr.eni.projet.bo.Enchere;

public interface EnchereDAO {
	
	/**
	 * Insère une nouvelle enchere
	 * @param enchere
	 * @throws DALException
	 */
	public void insert(Enchere enchere) throws DALException;
	/**
	 * Insère une nouvelle enchère en BDD
	 * @throws DALException
	 */
	public void insertEnchere(Enchere enchere, Utilisateur utilisateur, Vente vente) throws DALException;
	
	/**
	 * Verifie si l'enchereur a deja fait une enchere sur cette vente

	 * @return null si pas deja d'enchere, l'enchere si elle existe
	 * @throws DALException 
	 */
	public Enchere verification(Connection connection, Utilisateur enchereur, Vente vente) throws DALException;
	
	/**
	 * insere une enchere en BDD

	 */
	public int creationEnchere (Connection connection,Enchere enchere);
	
	/**
	 * Met à jour une ancienne enchere

	 */
	public Integer updateEnchere (Connection connection,Enchere enchere, Vente vente);
	
	/**
	 * réserve les credit de l'offre d'enchere

	 */
	public Integer retraitCredit (Connection connection, Enchere enchere);
	
	/**
	 * Redonne les credits reserves a l'ancien meilleur enchereur

	 */
	public Integer retourCredit (Connection connection, Vente vente);
	
	/**
	 * Met a jour la vente en BDD

	 */
	public void updateVente (Connection connection, Enchere enchere, Utilisateur enchereur);

	
}
