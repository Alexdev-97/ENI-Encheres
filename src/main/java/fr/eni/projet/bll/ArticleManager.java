package fr.eni.projet.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.projet.bo.Article;
import fr.eni.projet.dal.ArticleDAO;
import fr.eni.projet.dal.DALException;
import fr.eni.projet.dal.DAOFactory;

public class ArticleManager {

	/**
	 * Constructeur de ArticleManager
	 */

	private ArticleDAO articleDAO;
	public ArticleManager() {
		this.articleDAO=DAOFactory.getArticleDAO();
	}

	/**
	 * Recherche un article dans la base de donnée grâce à son id
	 */
	public Article getArticleID(int Articleid) {

		Article article = null;

		try {
			articleDAO.selectById(Articleid);
		} catch (DALException e) {
			e.printStackTrace();		}
		return article;
	}

	/**
	 * Ajoute un article dans la base de donnée 
	 */	 

	public void addArticle(Article article) {
		try {
			articleDAO.insert(article);
		} catch (DALException e) {
			e.printStackTrace();
		}
	}

	/**
	 * met a jour l'Article dans la base de donnees
	 */
	public void updateArticle(Article article) {
		try {
			articleDAO.update(article);
		} catch (DALException e) {
			e.printStackTrace();
		}
	}

	/**
	 * supprime l'Article dans la base de donnees
	 */
	public void delete(Article article) {
		try {
			articleDAO.delete(article);
		} catch (DALException e) {
			e.printStackTrace();
		}
	}	

	/**
	 * Retourne la list de tous les articles
	 */

	public List<Article> getAllArticles() {
		List<Article> tousLesArticles = new ArrayList<Article>();
		try {
			tousLesArticles =  articleDAO.selectAll();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tousLesArticles;
	}

	/**
	 * Retourne les articles vendus par mots cles (nom et description) ET categorie
	 */

	public List<Article> selectByFiltres (String motCle, int noCategorie) {
		List<Article> ArticleParFiltres = new ArrayList<Article>();
		try {
			ArticleParFiltres =  articleDAO.selectByFiltres(motCle, noCategorie);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ArticleParFiltres;
	}

	/**
	 * Retourne la liste de tous les articles vendus par catégorie
	 */
	public List<Article> selectByCategorie(int noCategorie) {
		List<Article> ArticleParCategorie = new ArrayList<Article>();
		try {
			ArticleParCategorie =  articleDAO.selectByCategorie(noCategorie);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ArticleParCategorie;
	}
	/**
	 * Retourne la liste de tous les articles vendus par un utilisateur en fonction de son pseudo.
	 */
	public List<Article> selectVenteByPseudo(String pseudo){
		List<Article> toutesLesVentes = new ArrayList<Article>();
		try {
			toutesLesVentes = articleDAO.selectVenteByPseudo(pseudo);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return toutesLesVentes;	

	}

}