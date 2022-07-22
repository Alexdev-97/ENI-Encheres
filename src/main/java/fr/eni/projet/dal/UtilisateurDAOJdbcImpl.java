package fr.eni.projet.dal;

import java.util.List;
import java.util.Map;

import fr.eni.projet.bo.Utilisateur;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

	@Override
	public List<String> selectAllPseudos() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur selectById(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Utilisateur> selectAll() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Utilisateur utilisateur) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Utilisateur utilisateur) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Utilisateur utilisateur) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, String> selectAllIdentifiants() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String[]> selectAllIdentifiantsUniques() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur selectByPseudo(String pseudo) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String[], String> selectAllPseudoEmail() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur selectByEmail(String email) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

}
