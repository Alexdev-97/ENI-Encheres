package fr.eni.projet.bo;

import java.io.Serializable;

public class Categorie implements Serializable {
		
		private static final long serialVersionUID = 1L;


		private int id;
		private String libelle;

		// Constructeur

		@Override
		public String toString() {
			return "Categorie [id=" + id + ", libelle=" + libelle + "]";
		}
		public Categorie(int id, String libelle) {
			this.id = id;
			this.libelle = libelle;
		}



		/**
		 * 
		 */
		public Categorie() {
		}

		/**
		 * Libelle
		 */
		public Categorie(String libelle) {
			this.libelle = libelle;
		}

		// Accesseurs mutateur
		/**
		 * @return the id
		 */
		public int getId() {
			return id;
		}

		/**
		 * @param id the id to set
		 */
		public void setId(int id) {
			this.id = id;
		}

		/**
		 * @return the libelle
		 */
		public String getLibelle() {
			return libelle;
		}

		/**
		 * @param libelle the libelle to set
		 */
		public void setLibelle(String libelle) {
			this.libelle = libelle;
	
		}
	}
