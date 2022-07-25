package fr.eni.projet.dal;

import java.util.List;

import fr.eni.projet.bo.Article;


public interface ArticleDAO {
	/**
	 * Retourne la liste de tous les articles dans la base de donnee
	 */
	public List<Article> selectAll() throws DALException;
	/**
	 * Selectionner un article par son id
	 */		
	public Article selectById(int id) throws DALException;
	
	/**
	 * Inserer un nouvel article dans la base de donnée 
	 */	
	public void insert(Article article) throws DALException;
	
	/**
	 * Supprimer un article dans la base de donnée 
	 */		
	public void delete(Article article) throws DALException;
	
	/**
	 * Modifier un article dans la base de donnée 
	 */
	public void update(Article article) throws DALException;
	
	/**
	 * Selectionner les articles par mot cle
	 * On recherche le mot cle dans le nom et la description
	 */
	public List<Article> selectByMotCle(String motCle) throws DALException;
	
	/**
	 * Selectionner les articles par categorie
	 */		
	public List<Article> selectByCategorie(int noCategorie) throws DALException;
		
	/**
	 * Selectionner les articles par mot cle (nom et description) ET categorie
	 */
	public List<Article> selectByFiltres(String motCle, int noCategorie) throws DALException;

	/**
	 * Retourne la liste des articles vendus par un utilisateur en fonction de son pseudo
	 */
	public List<Article> selectVenteByPseudo(String pseudo) throws DALException;

	}