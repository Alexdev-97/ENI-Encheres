package fr.eni.projet.bo;

import java.io.Serializable;
import java.time.LocalDate;

public class ArticleVendu implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String nom;
	private String description;
	private LocalDate dateDebutEncheres;
	private LocalDate dateFinEncheres;
	private int miseAPrix;
	private int prixVente;
	private Categorie categorie;
	private Utilisateur utilisateur;
	private Retrait Retrait;

	// CONSTRUCTEURS


	public ArticleVendu() {
		
	}

	
	public ArticleVendu(String nom, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			int miseAPrix, Utilisateur utilisateur, Categorie categorie) {
		super();
		this.nom = nom;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.categorie = categorie;
		this.utilisateur = utilisateur;
		this.Retrait = utilisateur.Retrait();
		
	}

	public ArticleVendu(String nom, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			int miseAPrix, int prixVente, Categorie categorie, Utilisateur utilisateur, Retrait Retrait) {
		this(nom,description,dateDebutEncheres,dateFinEncheres,miseAPrix,utilisateur,categorie);
		this.Retrait=Retrait;
		
	}
	
	

	public ArticleVendu(String nom, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			int miseAPrix, int prixVente, Categorie categorie, Utilisateur utilisateur) {
		this(nom,description,dateDebutEncheres,dateFinEncheres,miseAPrix,prixVente,categorie,utilisateur,utilisateur.Retrait());
			
	}
	
	

	public ArticleVendu(int id, String nom, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			int miseAPrix, int prixVente, Categorie categorie, Utilisateur utilisateur, Retrait Retrait) {
		this(nom, description, dateDebutEncheres, dateFinEncheres, miseAPrix, prixVente, categorie, utilisateur, Retrait);
		this.id = id;

	}


	// GETTERS AND SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}

	public LocalDate getDateFinEncheres() {
		return dateFinEncheres;
	}

	public void setDateFinEncheres(LocalDate dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	public int getMiseAPrix() {
		return miseAPrix;
	}

	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	public int getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Utilisateur getutilisateur() {
		return utilisateur;
	}

	public void setutilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Retrait getRetrait() {
		return Retrait;
	}

	public void setRetrait(Retrait Retrait) {
		this.Retrait = Retrait;
	}



	/**
	 * Méthode ToString 
	 */
	@Override
	public String toString() {
		return "ArticleVendu [id=" + id + ", nom=" + nom + ", description=" + description + ", dateDebutEncheres="
				+ dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix=" + miseAPrix
				+ ", prixVente=" + prixVente + ", categorie=" + categorie + ", utilisateur=" + utilisateur + ", etatVente="
				+ ", Retrait=" + Retrait + "]";
	}

}