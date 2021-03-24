package it.polito.tdp.corsi.model;

import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.db.copy.CorsoDAO;

public class Model {
	
	private CorsoDAO corsoDao;
	
	public Model () {
		corsoDao = new CorsoDAO();
	}
	
	public List <Corso> getCorsiByPeriodo(Integer pd){
		return corsoDao.getCorsiByPeriodo(pd); //fa da passacarte verso il DAO
	}
	
	public Map <Corso, Integer> getIscrittiByPerido (Integer periodo){
		return corsoDao.getIscrittiByPeriodo(periodo);
	}
	
}
