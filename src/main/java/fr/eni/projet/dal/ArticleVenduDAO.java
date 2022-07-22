package fr.eni.projet.dal;

import java.util.List;

import fr.eni.projet.bo.ArticleVendu;

public interface ArticleVenduDAO {
	/**
	 * Selectionner un article par son id
	 */		
	public ArticleVendu selectById(int id) throws DALException;
		
	/**
	 * Selectionner tous les articles
	 */		
	public List<ArticleVendu> selectAll() throws DALException;
		
	/**
	 * Inserer un nouvel article
	 */		
	public void insert(ArticleVendu article) throws DALException;
		
	/**
	 * Supprimer un article
	 */		
	public void delete(ArticleVendu articleVendu) throws DALException;
		
	/**
	 * Modifier un article
	 */
	public void update(ArticleVendu articleVendu) throws DALException;
		
	/**
	 * Selectionner les articles par mot cle
	 * On recherche le mot cle dans le nom et la description
	 */
	public List<ArticleVendu> selectByMotCle(String motCle) throws DALException;

	/**
	 * Selectionner les articles par mot cle et l'état
	 * On recherche le mot cle dans le nom et la description
	 */
	public List<ArticleVendu> selectByMotCleAndEtat(String motCle, final int ETAT) throws DALException;
	
	/**
	 * Selectionner les articles par categorie
	 */		
	public List<ArticleVendu> selectByCategorie(int noCategorie) throws DALException;
		
	/**
	 * Selectionner les articles par mot cle (nom et description) ET categorie
	 */
	public List<ArticleVendu> selectByFiltres(String motCle, int noCategorie) throws DALException;

	/**
	 * Retourne la liste des articles vendus par un utilisateur en fonction de son pseudo
	 */
	public List<ArticleVendu> selectVenteByPseudo(String pseudo) throws DALException;

	/**
	 * Retourne la liste des ventes en fonction du pseudo de l'utilisateur et de l'état de la vente
	 */
	public List<ArticleVendu> selectVenteByPseudoAndEtat(String pseudo, final int ETAT) throws DALException;
}
