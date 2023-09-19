package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import modelo.Evento;
import modelo.Insumo;
import modelo.Transacao;

public class InsumoDAO implements DAO<Insumo> {
    @Override
    public ArrayList<Insumo> list() {
		return list(null);
    }
    
    public ArrayList<Insumo> list(Evento evento) {
    	Conexao c = Conexao.getInstancia();

    	Connection con = c.conectar();

    	String query;
    	
    	if (evento != null) {
    		query = "SELECT insumos.*, transacoes.* FROM insumos "
    			+ "INNER JOIN transacoes ON insumos.id_insumo = transacoes.insumos_id_insumo "
    			+ "WHERE insumos.eventos_id_evento = ?";
    	} else {
        	query = "SELECT insumos.*, transacoes.* FROM insumos "
        			+ "INNER JOIN transacoes ON insumos.id_insumo = transacoes.insumos_id_insumo";
    	}

    	ArrayList<Insumo> insumos = new ArrayList<Insumo>();
    	try {
			PreparedStatement ps = con.prepareStatement(query);
	    	if (evento != null) {
	    		ps.setInt(0, evento.getId());
	    	}

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("insumos.id_insumo");
				int tipo = rs.getInt("insumos.tipo");
				String nome = rs.getString("insumos.nome");
				String descricao = rs.getString("insumos.descricao");
				
				double valor = rs.getDouble("transacoes.valor");
				LocalDateTime data = rs.getTimestamp("transacoes.data").toLocalDateTime();
				
				Insumo i = new Insumo();
				i.setId(id);
				i.setTipo(tipo);
				i.setNome(nome);
				i.setDescricao(descricao);
				
				Transacao t = new Transacao();
				t.setValor(valor);
				t.setData(data);
				
				i.setTransacao(t);

				insumos.add(i);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
		c.fecharConexao();
		return insumos;
	}

    public int insert(Insumo insumo) {
    	if (insumo != null) {
    		Conexao c = Conexao.getInstancia();
    		
    		Connection con = c.conectar();
    		
    		String query = "INSERT INTO insumos "
    				+ "(tipo, nome, descricao, eventos_id_evento) "
    				+ "VALUES (?, ?, ?, ?)";
    		
    		try {
    			PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
    			
    			ps.setInt(1, insumo.getTipo());
    			ps.setString(2, insumo.getNome());
    			ps.setString(3, insumo.getDescricao());
    			ps.setInt(4, insumo.getEvento().getId());
    			ps.executeUpdate();
    			ResultSet rs = ps.getGeneratedKeys();
    			
                if (rs.next()) {
                    int insertId = rs.getInt(1);
                    insumo.setId(insertId);
                    
                    String query2 = "INSERT INTO transacoes "
            				+ "(valor, data, usuarios_id_usuario, insumos_id_insumo) "
            				+ "VALUES (?, ?, ?, ?)";
                    
                    try {
            			PreparedStatement ps2 = con.prepareStatement(query2, Statement.RETURN_GENERATED_KEYS);
            			ps2.setDouble(1, insumo.getTransacao().getValor());
            			ps2.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            			ps2.setInt(3, UsuarioDAO.getInstance().getUsuarioSelecionado().getId());
            			ps2.setInt(4, insertId);
            			ps2.executeUpdate();
            			

            			ResultSet rs2 = ps.getGeneratedKeys();
                        if (rs2.next()) {
                            int insertId2 = rs.getInt(1);
                            insumo.getTransacao().setId(insertId2);
                        }
            			
            			c.fecharConexao();
                        
            			return insertId;
                    } catch (SQLException e) {
            			e.printStackTrace();
            		}
                }

    			c.fecharConexao();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
        }
        return 0;
    }

    public boolean update(Insumo insumo) {
    	if (insumo != null) {
    		Conexao c = Conexao.getInstancia();
    		
    		Connection con = c.conectar();
    		
    		String query = "UPDATE insumos SET tipo = ?, nome = ?, descricao = ? WHERE id_insumo = ?";
    		
    		try {
    			PreparedStatement ps = con.prepareStatement(query);
    			
    			ps.setInt(1, insumo.getTipo());
    			ps.setString(2, insumo.getNome());
    			ps.setString(3, insumo.getDescricao());
    			ps.setInt(4, insumo.getId());
    			ps.executeUpdate();
    			
        		String query2 = "UPDATE transacoes SET valor = ? WHERE id_transacao = ?";
                
                try {
        			PreparedStatement ps2 = con.prepareStatement(query2);
        			ps2.setDouble(1, insumo.getTransacao().getValor());
        			ps2.setInt(2, insumo.getTransacao().getId());
        			ps2.executeUpdate();
        			c.fecharConexao();
                    
        			return true;
                } catch (SQLException e) {
        			e.printStackTrace();
        		}

    			c.fecharConexao();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
        }
        return false;
    }

    public boolean delete(Insumo insumo) {
    	if (insumo != null) {
        	Conexao c = Conexao.getInstancia();

        	Connection con = c.conectar();

        	String query = "DELETE FROM insumos WHERE id_insumo = ?";
        	
        	try {
        	    PreparedStatement ps = con.prepareStatement(query);
        	    ps.setInt(1, insumo.getId());
    			ps.executeUpdate();
        	    ps.close();

        	    c.fecharConexao();

                return true;
        	} catch (SQLException e) {
        	    e.printStackTrace();
        	}
        }
        return false;
    }

    private static InsumoDAO instance = null;

    public InsumoDAO() {
    	
    }
    
    public static InsumoDAO getInstance() {
        if (instance == null) {
            instance = new InsumoDAO();
        }
        return instance;
    }
}
