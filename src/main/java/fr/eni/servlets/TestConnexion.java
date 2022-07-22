package fr.eni.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.dal.ConnectionProvider;
import fr.eni.projet.dal.jdbc.UtilisateurDAOJdbcImpl;

/**
public class TestConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	       
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
			doRequest(request, response);
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doRequest(request, response);
		
			
		}
		protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			UtilisateurDAOJdbcImpl test = new UtilisateurDAOJdbcImpl();
	        Utilisateur u = new Utilisateur();
	        u.setNom("NORATH");
	        u.setPrenom("Jimmy");
	        u.setPseudo("Cote");
	        u.setEmail("jimmy.NORATH@campus-eni.fr");
	        u.setTelephone("0600000000");
	        u.setRue("Avenue Marcel Sambat");
	        u.setCodePostal("44100");
	        u.setVille("Nantes");
	        u.setMotDePasse("essaie");
	        u.setCredit(1000);
	        u.setisAdministrateur(true);
	        try {
	        	UtilisateurDAOJdbcImpl.insert(u);
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	        response.getWriter().append("Served at: ").append(request.getContextPath());
		}

	}

*/