package fr.eni.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projet.dal.ConnectionProvider;

/**
 * Servlet implementation class TestPoolConnexion
 */
@WebServlet("/TestPoolConnexion")
public class TestPoolConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestPoolConnexion() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

		Connection cnx = null;
		try {
			cnx= ConnectionProvider.getConnection();
			System.out.println("connexion ok");
		} catch (SQLException e) {

			System.err.println("connexion ko"+ e.getCause());
			//e.printStackTrace();
		}
		finally {
			try {
				ConnectionProvider.seDeconnecter(cnx);
			} catch (SQLException e) {
				System.err.println("probleme de fermeture de connexion"+ e.getCause());
				//e.printStackTrace();
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

	}

}
