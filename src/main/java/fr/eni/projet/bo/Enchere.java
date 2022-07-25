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
	private Article article;
	private Utilisateur encherisseur;
	
	public Enchere() {

		super();
	}
	
	// ToString
	@Override
	public String toString() {
		return "Enchere [date=" + date + ", montant=" + montantEnchere + ", article=" + article
				+ ", encherisseur=" + encherisseur + "]";
	}
	public Enchere(LocalDateTime date,int montantEnchere, Article article, Utilisateur encherisseur) {

		this.date = date;
		this.montantEnchere = montantEnchere;
		this.article = article;
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

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Utilisateur getEncherisseur() {
		return encherisseur;
	}

	public void setEncherisseur(Utilisateur encherisseur) {
		this.encherisseur = encherisseur;
	}
	
}