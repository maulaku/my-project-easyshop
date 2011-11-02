package br.com.easyShop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipoContato", schema="easy")
public class TipoContato {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long pkTipoContato;
	private String nomeTipo;
	
	public long getPkTipoContato() {
		return pkTipoContato;
	}
	
	public void setPkTipoContato(long pkTipoContato) {
		this.pkTipoContato = pkTipoContato;
	}
	
	public String getNomeTipo() {
		return nomeTipo;
	}
	
	public void setNomeTipo(String nomeTipo) {
		this.nomeTipo = nomeTipo;
	}
	
	public String toString(){  
	        return this.nomeTipo;  
	} 
}
