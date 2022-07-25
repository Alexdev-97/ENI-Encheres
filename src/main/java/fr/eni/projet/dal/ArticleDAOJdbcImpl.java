package fr.eni.projet.dal;

import java.util.List;

import fr.eni.projet.bo.Article;


public class ArticleDAOJdbcImpl implements ArticleDAO {


	private static final String sqlSelectById =
			"select *" 
			+" from ARTICLES_VENDUS"
			+ " where no_article = ?";
	private static final String sqlSelectAll =
			"select *" 
			+" from ARTICLES_VENDUS";
	private static final String sqlInsert =
			"insert "
			+ "into ARTICLES_VENDUS(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial,prix_vente, no_vendeur, no_acheteur, no_categorie, etat_vente  )"
			+ " values(?,?,?,?,?,?,?,?,?,?)";

	private static final String sqlSelectByCategorie =
			"select *" 
			+" from ARTICLES_VENDUS"
			+ " where no_categorie = ?";
	//TODO finir la requete et la corriger
	private static final String sqlSelectByFiltres =
			"select *" 
			+" from ARTICLES_VENDUS"
			+ " where no_categorie = ? and (description like \'%?%\' or nom_article like \'%?%\')";
	//TODO finir la requete et la corriger

	@Override
	public Article selectById(int id) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Article Article = null;
		try {
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.prepareStatement(sqlSelectById);
			rqt.setInt(1, id);
			rs = rqt.executeQuery();
			if (rs.next()){		
				Article = new Article(
						rs.getInt("no_article"),
						rs.getString("nom_article"),
						rs.getString("description"),
						rs.getDate("date_debut_encheres").toLocalDate(),
						rs.getDate("date_fin_encheres").toLocalDate(),
						rs.getInt("prix_initial"),
						rs.getInt("prix_vente"),
						rs.getInt("etat_vente")
						);
			}
			//set des vendeur et acheteur
			UtilisateurDAO utilistateurDAO = DAOFactory.getUtilisateurDAO();
			Article.setVendeur(utilistateurDAO.selectById(rs.getInt("no_vendeur")));
			Article.setAcheteur(utilistateurDAO.selectById(rs.getInt("no_acheteur")));
			
			//set de la categorie
			CategorieDAO categorieDAO = DAOFactory.getCategorieDAO();
			Article.setCategorie(categorieDAO.selectById(rs.getInt("no_categorie")));
			
			//set du retrait
			RetraitDAO retraitDAO = DAOFactory.getRetraitDAO();
			Article.setLieuRetrait(retraitDAO.selectByIdArticle(rs.getInt("no_article")));
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null){
					rs.close();
				}
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return Article;
	}

	@Override
	public List<Article> selectAll() throws DALException {
		Connection cnx = null;
		Statement rqt = null;
		ResultSet rs = null;
		List<Article> articles = new ArrayList<>();
		try {
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.createStatement();
			rs = rqt.executeQuery(sqlSelectAll);
			while (rs.next()){		
				Article Article = new Article(
						rs.getInt("no_article"),
						rs.getString("nom_article"),
						rs.getString("description"),
						rs.getDate("date_debut_encheres").toLocalDate(),
						rs.getDate("date_fin_encheres").toLocalDate(),
						rs.getInt("prix_initial"),
						rs.getInt("prix_vente"),
						rs.getInt("etat_vente")
						);
				//set des vendeur et acheteur
				UtilisateurDAO utilistateurDAO = DAOFactory.getUtilisateurDAO();
				Article.setVendeur(utilistateurDAO.selectById(rs.getInt("no_vendeur")));
				Article.setAcheteur(utilistateurDAO.selectById(rs.getInt("no_acheteur")));
				
				//set de la categorie
				CategorieDAO categorieDAO = DAOFactory.getCategorieDAO();
				Article.setCategorie(categorieDAO.selectById(rs.getInt("no_categorie")));
				
				//set du retrait
				RetraitDAO retraitDAO = DAOFactory.getRetraitDAO();
				Article.setLieuRetrait(retraitDAO.selectByIdArticle(rs.getInt("no_article")));
				
				//ajout de l'article à la liste
				articles.add(Article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null){
					rs.close();
				}
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return articles;
	}

	@Override
	public void insert(Article article) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		java.sql.Date sqlDateDebut = java.sql.Date.valueOf(article.getDateDebutEncheres());  
		java.sql.Date sqlDateFin = java.sql.Date.valueOf(article.getDateFinEncheres());  
		try {
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			rqt.setString(1, article.getNomArticle());
			rqt.setString(2, article.getDescription());
			rqt.setDate(3, sqlDateDebut);
			rqt.setDate(4, sqlDateFin);
			rqt.setInt(5, article.getMiseAPrix());
			rqt.setInt(6, article.getPrixVente());
			rqt.setInt(7, article.getVendeur().getNoUtilisateur());
			rqt.setInt(8, article.getAcheteur().getNoUtilisateur());
			rqt.setInt(9, article.getCategorie().getNoCategorie());
			rqt.setInt(10, article.getEtatVente());
			
			int nbRows = rqt.executeUpdate();
			if(nbRows == 1){
				ResultSet rs = rqt.getGeneratedKeys();
				if(rs.next()){
					article.setNoArticle(rs.getInt(1));
				}
			}
		}catch(SQLException e){
			System.out.println(e);
			throw new DALException("La creation de l'utilisateur a echoue - " + article.getNomArticle(), e);
		}
		finally {
			try {
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("La fermeture de la connexion a echoue - ", e);
			}

		}
		
	}

	@Override
	public void delete(Article Article) throws DALException {
		
	}

	@Override
	public void update(Article Article) throws DALException {
		
	}

	@Override
	public List<Article> selectByMotCle(String motCle) throws DALException {
		return null;
	}
	
	@Override
	public List<Article> selectByCategorie(int noCategorie) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		List<Article> articles = new ArrayList<>();
		try {
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.prepareStatement(sqlSelectByCategorie);
			rqt.setInt(1, noCategorie);
			rs = rqt.executeQuery();
			if (rs.next()){		
				Article Article = new Article(
						rs.getInt("no_article"),
						rs.getString("nom_article"),
						rs.getString("description"),
						rs.getDate("date_debut_encheres").toLocalDate(),
						rs.getDate("date_fin_encheres").toLocalDate(),
						rs.getInt("prix_initial"),
						rs.getInt("prix_vente"),
						rs.getInt("etat_vente")
						);
				//set des vendeur et acheteur
				UtilisateurDAO utilistateurDAO = DAOFactory.getUtilisateurDAO();
				Article.setVendeur(utilistateurDAO.selectById(rs.getInt("no_vendeur")));
				Article.setAcheteur(utilistateurDAO.selectById(rs.getInt("no_acheteur")));
				
				//set de la categorie
				CategorieDAO categorieDAO = DAOFactory.getCategorieDAO();
				Article.setCategorie(categorieDAO.selectById(rs.getInt("no_categorie")));
				
				//set du retrait
				RetraitDAO retraitDAO = DAOFactory.getRetraitDAO();
				Article.setLieuRetrait(retraitDAO.selectByIdArticle(rs.getInt("no_article")));
				
				//ajout de l'article à la liste
				articles.add(Article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null){
					rs.close();
				}
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return articles;
	}

	//TODO A tester
	@Override
	public List<Article> selectByFiltres(String motCle, int noCategorie) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		List<Article> articles = new ArrayList<>();
		try {
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.prepareStatement(sqlSelectByFiltres);
			rqt.setInt(1, noCategorie);
			rqt.setString(2, motCle);
			rqt.setString(3, motCle);
			rs = rqt.executeQuery();
			if (rs.next()){		
				Article Article = new Article(
						rs.getInt("no_article"),
						rs.getString("nom_article"),
						rs.getString("description"),
						rs.getDate("date_debut_encheres").toLocalDate(),
						rs.getDate("date_fin_encheres").toLocalDate(),
						rs.getInt("prix_initial"),
						rs.getInt("prix_vente"),
						rs.getInt("etat_vente")
						);
				//set des vendeur et acheteur
				UtilisateurDAO utilistateurDAO = DAOFactory.getUtilisateurDAO();
				Article.setVendeur(utilistateurDAO.selectById(rs.getInt("no_vendeur")));
				Article.setAcheteur(utilistateurDAO.selectById(rs.getInt("no_acheteur")));
				
				//set de la categorie
				CategorieDAO categorieDAO = DAOFactory.getCategorieDAO();
				Article.setCategorie(categorieDAO.selectById(rs.getInt("no_categorie")));
				
				//set du retrait
				RetraitDAO retraitDAO = DAOFactory.getRetraitDAO();
				Article.setLieuRetrait(retraitDAO.selectByIdArticle(rs.getInt("no_article")));
				
				//ajout de l'article à la liste
				articles.add(Article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null){
					rs.close();
				}
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return articles;
	}

	@Override
	public List<Article> selectVenteByPseudo(String pseudo) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		List<Article> articles = new ArrayList<>();		
		try {
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.prepareStatement(sqlSelectVenteByPseudo);
			UtilisateurDAO utilistateurDAO = DAOFactory.getUtilisateurDAO();
			rqt.setInt(1, utilistateurDAO.selectByPseudo(pseudo).getNoUtilisateur());
			rs = rqt.executeQuery();
			if (rs.next()){		
				Article Article = new Article(
						rs.getInt("no_article"),
						rs.getString("nom_article"),
						rs.getString("description"),
						rs.getDate("date_debut_encheres").toLocalDate(),
						rs.getDate("date_fin_encheres").toLocalDate(),
						rs.getInt("prix_initial"),
						rs.getInt("prix_vente"),
						rs.getInt("etat_vente")
						);
				//set des vendeur et acheteur
				Article.setVendeur(utilistateurDAO.selectById(rs.getInt("no_vendeur")));
				Article.setAcheteur(utilistateurDAO.selectById(rs.getInt("no_acheteur")));
				
				//set de la categorie
				CategorieDAO categorieDAO = DAOFactory.getCategorieDAO();
				Article.setCategorie(categorieDAO.selectById(rs.getInt("no_categorie")));
				
				//set du retrait
				RetraitDAO retraitDAO = DAOFactory.getRetraitDAO();
				Article.setLieuRetrait(retraitDAO.selectByIdArticle(rs.getInt("no_article")));
				
				//ajout de l'article à la liste
				articles.add(Article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null){
					rs.close();
				}
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return articles;
	}

	@Override
	public List<Article> selectVenteByPseudoAndEtat(String pseudo, final int ETAT) throws DALException{
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		List<Article> articles = new ArrayList<>();		
		try {
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.prepareStatement(sqlSelectVenteByPseudoAndETAT);
			UtilisateurDAO utilistateurDAO = DAOFactory.getUtilisateurDAO();
			rqt.setInt(1, utilistateurDAO.selectByPseudo(pseudo).getNoUtilisateur());
			rqt.setInt(2, ETAT);
			rs = rqt.executeQuery();
			if (rs.next()){		
				Article Article = new Article(
						//renseigne les propriétés non POJO 
						rs.getInt("no_article"),
						rs.getString("nom_article"),
						rs.getString("description"),
						rs.getDate("date_debut_encheres").toLocalDate(),
						rs.getDate("date_fin_encheres").toLocalDate(),
						rs.getInt("prix_initial"),
						rs.getInt("prix_vente"),
						rs.getInt("etat_vente")
						);
				
				//set des vendeur et acheteur
				Article.setVendeur(utilistateurDAO.selectById(rs.getInt("no_vendeur")));
				Article.setAcheteur(utilistateurDAO.selectById(rs.getInt("no_acheteur")));
				
				//set de la categorie
				CategorieDAO categorieDAO = DAOFactory.getCategorieDAO();
				Article.setCategorie(categorieDAO.selectById(rs.getInt("no_categorie")));
				
				//set du retrait
				RetraitDAO retraitDAO = DAOFactory.getRetraitDAO();
				Article.setLieuRetrait(retraitDAO.selectByIdArticle(rs.getInt("no_article")));
				
				//ajout de l'article à la liste
				articles.add(Article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null){
					rs.close();
				}
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return articles;
	}

	@Override
	public List<Article> selectByMotCleAndEtat(String motCle, int ETAT) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		List<Article> articles = new ArrayList<>();
		try {
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.prepareStatement(sqlSelectByMotCleAndEtat);
			rqt.setInt(1, ETAT);
			rqt.setString(2, "%"+motCle+"%");
			rs = rqt.executeQuery();
			if (rs.next()){		
				Article Article = new Article(
						rs.getInt("no_article"),
						rs.getString("nom_article"),
						rs.getString("description"),
						rs.getDate("date_debut_encheres").toLocalDate(),
						rs.getDate("date_fin_encheres").toLocalDate(),
						rs.getInt("prix_initial"),
						rs.getInt("prix_vente"),
						rs.getInt("etat_vente")
						);

				//set des vendeur et acheteur
				UtilisateurDAO utilistateurDAO = DAOFactory.getUtilisateurDAO();
				Article.setVendeur(utilistateurDAO.selectById(rs.getInt("no_vendeur")));
				Article.setAcheteur(utilistateurDAO.selectById(rs.getInt("no_acheteur")));
				
				//set de la categorie
				CategorieDAO categorieDAO = DAOFactory.getCategorieDAO();
				Article.setCategorie(categorieDAO.selectById(rs.getInt("no_categorie")));
				
				//set du retrait
				RetraitDAO retraitDAO = DAOFactory.getRetraitDAO();
				Article.setLieuRetrait(retraitDAO.selectByIdArticle(rs.getInt("no_article")));
				
				//ajout de l'article à la liste
				articles.add(Article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e);
		} finally {
			try {
				if (rs != null){
					rs.close();
				}
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return articles;
	}

	@Override
	public List<Article> selectAll() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Article selectById(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Article article) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Article article) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Article article) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Article> selectByMotCle(String motCle) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> selectByCategorie(int noCategorie) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> selectByFiltres(String motCle, int noCategorie) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> selectVenteByPseudo(String pseudo) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}	

}}