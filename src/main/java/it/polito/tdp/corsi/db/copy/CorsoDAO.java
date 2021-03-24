package it.polito.tdp.corsi.db.copy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import it.polito.tdp.corsi.model.Corso;

public class CorsoDAO {
	
	public List <Corso> getCorsiByPeriodo(Integer periodo){
		String sql = "SELECT * FROM corso WHERE pd = ?";
		List <Corso>result = new ArrayList<Corso>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql); // ci da la possibilità di riempire il punto interrogativo, senza concatenare
			                                                   //la stringa SQL
			st.setInt(1, periodo);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Corso c = new Corso (rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				result.add(c);
			}
			
			rs.close();
			st.close();
			conn.close();
		}
		catch(SQLException e) {
			throw new RuntimeException();
			
		}
		return result;
	}
	
	

	public Map<Corso, Integer> getIscrittiByPeriodo(Integer periodo){
		String sql = "SELECT c.codins, c.nome, c.crediti, c.pd, COUNT(*) AS tot FROM corso c, iscrizione i WHERE c.codins = i.codins AND c.pd =? GROUP BY c.codins, c.nome, c.crediti, c.pd";
		Map <Corso, Integer> result = new HashMap<Corso, Integer>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql); 
			st.setInt(1, periodo);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Corso c = new Corso (rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				Integer n = rs.getInt("tot");
				result.put(c, n);
			}
			
			rs.close();
			st.close();
			conn.close();
		}
		catch(SQLException e) {
			throw new RuntimeException();
			
		}
		return result;
		
	}

}
