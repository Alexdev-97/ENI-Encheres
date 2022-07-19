package fr.eni.projet.bo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Enchere implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LocalDateTime date;
	private int montantEnchere;
	private ArticleVendu articleVendu;
	private Utilisateur encherisseur;
	
	public Enchere() {

		super();
	}
	
	public Enchere(LocalDateTime date,int montantEnchere, ArticleVendu articleVendu, Utilisateur encherisseur) {

		this.date = date;
		this.montantEnchere = montantEnchere;
		this.articleVendu = articleVendu;
		this.encherisseur = encherisseur;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public int getMontantEnchere() {
		return montantEnchere;
	}

	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	public ArticleVendu getArticle() {
		return articleVendu;
	}

	public void setArticle(ArticleVendu articleVendu) {
		this.articleVendu = articleVendu;
	}

	public Utilisateur getEncherisseur() {
		return encherisseur;
	}

	public void setEncherisseur(Utilisateur encherisseur) {
		this.encherisseur = encherisseur;
	}
	
	// ToString
	@Override
	public String toString() {
		return "Enchere [date=" + date + ", montant=" + montantEnchere + ", articleVendu=" + articleVendu
				+ ", encherisseur=" + encherisseur + "]";
	}
}