package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Utilisateur;

public class UtilisateurJdbcImpl implements UtilisateurDAO {

	/**attributs qui vont dépendre de ce qui sera enregistré, on entre les requêtes SQL pour chercher/entrer les informations dans la BDD
	*? correspondent aux paramètres qui vont dépendre des informations enregistrées 
	*/
	private static final String SQL_INSERT_UTIL = "INSERT into UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) values (?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE_UTIL = "UPDATE UTILISATEURS SET pseudo =?,nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=?, administrateur=? WHERE  no_utilisateur = ? ;";
	private static final String SQL_UPDATE_CREDIT = "UPDATE UTILISATEURS SET credit =?";
	private static final String SQL_GET_ALL_UTIL = "SELECT * FROM UTILISATEURS";
	private static final String SQL_GET_BY_ID = "SELECT * FROM UTILISATEURS WHERE no_utilisateur = ? ";
	private static final String SQL_GET_USER_BY_MAIL = "SELECT * FROM UTILISATEURS WHERE email = ? AND mot_de_passe = ?";
	private static final String SQL_GET_USER_BY_PSEUDO = "SELECT * FROM UTILISATEURS WHERE pseudo = ? AND mot_de_passe = ?";
	private static final String SQL_DELETE_UTIL = "DELETE FROM UTILISATEURS WHERE no_utilisateur = ? ";

	
	/**
	 * Méthode pour insérer un nouvel utilisateur 
	 * *Prepared Statement créé à partir d'une des méthodes prepareStatement de l'interface Connection
	 * On passe la requête SQL à exécuter
	 * ResultSet utilisé pour stocker le résultat renvoyé par la BDD.

	 */
	@Override
	public void insertUtilisateur(Utilisateur utilisateur) throws DALException {
	
		try (Connection connection = ConnectionProvider.getConnection();
			PreparedStatement stmt = connection.prepareStatement(SQL_INSERT_UTIL);)
		{
		
			stmt.setString(1, utilisateur.getPseudo());
			stmt.setString(2, utilisateur.getNom());
			stmt.setString(3, utilisateur.getPrenom());
			stmt.setString(4, utilisateur.getEmail());
			stmt.setString(5, utilisateur.getTelephone());
			stmt.setString(6, utilisateur.getRue());
			stmt.setString(7, utilisateur.getCodePostal());
			stmt.setString(8, utilisateur.getVille());
			stmt.setString(9, utilisateur.getMotDePasse());
			stmt.setInt(10, utilisateur.getCredit());				
			
		//Si c'est administrateur ? 
		
	} catch (SQLException e) {
		System.out.println(e.getMessage());
		throw new DALException("Erreur lors de l'insertion d'un nouvel utilisateur", e);
	}
	}
	
	
	@Override
	public void updateUtilisateur(Utilisateur utilisateur) throws DALException {
		try (Connection connection = ConnectionProvider.getConnection();
			PreparedStatement stmt = connection.prepareStatement(SQL_UPDATE_UTIL);) {

			
			stmt.setString(1, utilisateur.getPseudo());
			stmt.setString(2, utilisateur.getNom());
			stmt.setString(3, utilisateur.getPrenom());
			stmt.setString(4, utilisateur.getEmail());
			stmt.setString(5, utilisateur.getTelephone());
			stmt.setString(6, utilisateur.getRue());
			stmt.setString(7, utilisateur.getCodePostal());
			stmt.setString(8, utilisateur.getVille());
			stmt.setString(9, utilisateur.getMotDePasse());

			//cas administrateur
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Erreur a l'update de l'utilisateur DAL", e);
		}

	}
	
	/**
	 * Recupere tous les utilisateurs dans la base de donnee
	 * @return Liste contenant tous les utilisateurs
	 * @throws DALException 
	 */
	
	@Override
	public List<Utilisateur> getAllUtil() throws DALException {
		List<Utilisateur> liste = new ArrayList<>();
		ResultSet rst = null;

		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement stmt = connection.prepareStatement(SQL_GET_ALL_UTIL);) {

			rst = stmt.executeQuery();
			while (rst.next()) {
				Utilisateur util = CreerUtilisateur(rst);				
				liste.add(util);
			}
		} catch (SQLException e) {
			throw new DALException("Erreur a la recuperation de tous les utilisateurs DAL", e);
		}
		return liste;

	}

	/**
	 * Recupere un utilisateur en BDD par son ID(no_utilisateur)
	 * @return utilisateur
	 * @throws DALException
	 */
	
	@Override
	public Utilisateur getById(int no_utilisateur) throws DALException {
		
		Utilisateur user = null;
		ResultSet rst = null;

		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement stmt = connection.prepareStatement(SQL_GET_BY_ID);) {

			stmt.setInt(1, no_utilisateur);
			rst = stmt.executeQuery();
		} catch (SQLException e) {
			throw new DALException("Erreur a la recuperation d'un utilisateur par son no_utilisateur", e);
		}
		return user;
	}
	
	
	/**
	 * Met a jour les credits des acheteurs quand une enchere est faite
	 * @throws DALException
	 */
	@Override
	public void updateCredit(int argent) throws DALException {
		Utilisateur user = new Utilisateur();
		try (Connection connection = ConnectionProvider.getConnection();
			PreparedStatement stmt = connection.prepareStatement(SQL_UPDATE_CREDIT);) {
			user.setCredit(argent);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Erreur dans la mise a jour des credits DAL",e);
		}
	}
		
	/**
	 * Supprimer son compte 
	 * @throws DALException
	 */
	@Override
	public void deleteUtilisateur(Utilisateur user) throws DALException {
		try (Connection connection = ConnectionProvider.getConnection();
			PreparedStatement stmt = connection.prepareStatement(SQL_DELETE_UTIL);) {
			System.out.println(user.getNoUtilisateur());
			stmt.setInt(1, user.getNoUtilisateur());			
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Erreur dans la suppression d'un utilisateur",e);

		}

	}


	/**Objet de ResultSet maintient le curseur sur les données de résultat. Par défaut avant la première ligne des données de résultat
	 * executeQuery execute une requête de type interrogation et 
	 * renvoie un objet de type ResultSet qui contient les données issues de l'exécution de la requête
	*/
	
	@Override
	public Utilisateur connectionCompteUtilisateur(String eMailPseudo, String password)
			throws DALException {
		
		Utilisateur user = null;
		ResultSet rst = null;
		String saisie = null;
	
		try (Connection connection = ConnectionProvider.getConnection();
			PreparedStatement stmt = connection.prepareStatement(saisie);) {
			stmt.setString(1, eMailPseudo);
			stmt.setString(2, password);
			rst = stmt.executeQuery();
			
		}	if (rst.getString("eMailPseudo").equals(user.getEmail())) {
		saisie = SQL_GET_USER_BY_MAIL;
		} else {
		saisie = SQL_GET_USER_BY_PSEUDO;
		}
			//if car un seul enregistrement 
			if (rst.next()) {
				if (rst.getString("mot_de_passe").equals(password)) {
					if (rst.getString("email").equals(eMailPseudo) || (rst.getString("pseudo").equals(eMailPseudo))) {
						user = CreerUtilisateur(rst);
					
				}
			
			} catch (SQLException e){
			throw new DALException("Erreur Connection au compte Utilisateur",e);
			}
			return user;
		}
	}
	private Utilisateur CreerUtilisateur(ResultSet rst) throws SQLException {
		Utilisateur util = new Utilisateur();
					util.setNoUtilisateur(rst.getInt("no_utilisateur"));		
		util.setPseudo(rst.getString("pseudo"));
		util.setNom(rst.getString("nom"));
		util.setPrenom(rst.getString("prenom"));
		util.setEmail(rst.getString("email"));
		util.setTelephone(rst.getString("telephone"));
		util.setRue(rst.getString("rue"));
		util.setCodePostal(rst.getString("code_postal"));
		util.setVille(rst.getString("ville"));
		util.setMotDePasse(rst.getString("mot_de_passe"));
		util.setCredit(rst.getInt("credit"));
		if (rst.getInt("administrateur") == 0) {
			util.setAdministrateur(false);
		} else {
			util.setAdministrateur(true);
		}

		return util;
	}
}
