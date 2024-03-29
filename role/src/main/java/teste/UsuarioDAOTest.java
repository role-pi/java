package teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import controle.UsuarioDAO;
import modelo.Evento;
import modelo.Usuario;

public class UsuarioDAOTest {
	private UsuarioDAO usuarioDao; 
    private Usuario usuario;
    
    public UsuarioDAOTest() {
    	this.usuarioDao = UsuarioDAO.getInstance();
    	this.usuario = new Usuario(0, "Nome", "teste@email.com");
    }
    
    @Test
	public void testListUsuarios() {
    	usuarioDao.clear();
    	
    	Usuario usuario1 = new Usuario(0, "Usuario 1", "teste1@email.com");
		usuarioDao.insert(usuario1);
		
		Usuario usuario2 = new Usuario(0, "Usuario 2", "teste2@email.com");
		usuarioDao.insert(usuario2);
		
		ArrayList<Usuario> usuarios = usuarioDao.list();
		assertEquals(usuarios.size(), 2);
	}
    
	@Test
	public void testInsertUsuario() {
		int insertId = usuarioDao.insert(usuario);
		assertTrue(insertId > 0);
	}
	
	@Test
	public void testSelectUsuario() {
		int insertId = usuarioDao.insert(usuario);
        Usuario usuarioInsert = usuarioDao.list().stream()
                .filter(u -> u.getId() == insertId)
                .findFirst()
                .orElse(null);
        assertNotNull(usuarioInsert);	
    }
	
	@Test
	public void testUpdateUsuario() {
		int insertId = usuarioDao.insert(usuario);
		usuario.setId(insertId);
	    usuario.setNome("Novo nome teste");
	    usuario.setEmail("novoteste@email.com");
	    assertTrue(usuarioDao.update(usuario));
	    Usuario usuarioUpdate = usuarioDao.list().stream()
	            .filter(u -> u.getId() == insertId)
	            .findFirst()
	            .orElse(null);
	    assertEquals("Novo nome teste", usuarioUpdate.getNome());
	    assertEquals("novoteste@email.com", usuarioUpdate.getEmail());
	}
	
	@Test
	public void testDeleteUsuario() {
		int insertId = usuarioDao.insert(usuario);
		usuario.setId(insertId);
        assertTrue(usuarioDao.delete(usuario));
        Usuario usuarioDeleted = usuarioDao.list().stream()
                .filter(u -> u.getId() == insertId)
                .findFirst()
                .orElse(null);
        assertNull(usuarioDeleted);
	}
}

