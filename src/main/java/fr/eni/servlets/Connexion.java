package fr.eni.encheres.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class Connexion
 */
@WebServlet("/Connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	//import session et récupération identifiant/mdp du formulaire 
	String emailPseudo = request.getParameter("emailPseudo");
	String password = request.getParameter ("password");
	
	HttpSession session = request.getSession();
	
	session.setAttribute ("emailPseudo", emailPseudo);
	session.setAttribute("password", password);
	
	
	
	Utilisateur utilisateur = null;
	
	//quand on se connecte
	if (request.getParameter(emailPseudo)!=null) {
		try {
			
			
		}
	}
	
	
	
	
	}
}
