package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Registro;
import model.RegistroBruto;

public class RegistroDAO {
	
	public List<RegistroBruto> listarRegistrosRota(String linha, String tabela) throws Exception {

		String sql = "select * from " + tabela
				+ " where linha=" +linha
		        + " order by DataIni, HoraIni";

		PreparedStatement pStatement = null;
		Connection connection = null;
		List<RegistroBruto> listaRegistros = null;
		ResultSet rs = null;
		try {
			connection = new ConexaoBDFloripa().getConnection();
			pStatement = connection.prepareStatement(sql);
			rs = pStatement.executeQuery();

			if (rs != null) {
				listaRegistros = new ArrayList<RegistroBruto>();
				while (rs.next()) {
					RegistroBruto rgBruto = new RegistroBruto();
					rgBruto.setDataIni(rs.getString("DataIni"));
					rgBruto.setHoraIni(rs.getString("HoraIni"));
					rgBruto.setDataFim(rs.getString("DataFim"));
					rgBruto.setHoraFim(rs.getString("HoraFim"));
					rgBruto.setSentido(rs.getString("Sentido"));
					rgBruto.setLinha(rs.getString("Linha"));
					rgBruto.setnVeiculo(rs.getString("NoVeículo"));
					rgBruto.setTempoViagem(rs.getString("DuraçãoViagem"));
					rgBruto.setTotalGiros(rs.getString("TotalGiros"));
					rgBruto.setKmPercorrido(rs.getString("KmPerc"));
					listaRegistros.add(rgBruto);
				}

			}

		} catch (SQLException e) {
			throw new Exception("Erro ao listar registros:" + e);
		} finally {

			try {
				if (pStatement != null) {
					pStatement.close();
				}
			} catch (SQLException e) {
				throw new Exception("Erro ao fechar pStatement:" + e);
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new Exception("Erro ao fechar conexão:" + e);
			}
		}
		return listaRegistros;

	}
	
	public boolean cadastrarRegistro(Registro registro, String tabela) throws Exception {
		
	    PreparedStatement pStatement = null;
		Connection connection =null;
		boolean retorno;
		String sql = "INSERT INTO "+ tabela +" (dataPartidaTimeStamp,dataChegadaTimeStamp,sentido,linha,nVeiculos,totalGiros,dia_semana, tipoDia,dataPartida,dataChegada,duracao,km)"
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
				
		try {
			connection = new ConexaoBDFloripa().getConnection();
		    pStatement = connection.prepareStatement(sql);
		    pStatement.setLong(1, registro.getTimeStampDataPartida());
		    pStatement.setLong(2, registro.getTimeStampDataChegada());
		    pStatement.setInt(3, registro.getSentido());
		    pStatement.setString(4, registro.getLinha());
		    pStatement.setInt(5, registro.getnVeiculos());
		    pStatement.setInt(6, registro.getTotalGiros());
		    pStatement.setInt(7, registro.getDia_semana());
		    pStatement.setInt(8, registro.getTipo_dia());
		    pStatement.setTimestamp(9, new Timestamp(registro.getData_hora_partida().getTime()));
		    pStatement.setTimestamp(10, new Timestamp(registro.getData_hora_chegada().getTime()));
		    pStatement.setLong(11, registro.getTempoViagem());
		    pStatement.setFloat(12, registro.getKmPercorridos());
		    
		    retorno=pStatement.execute();
		    		
	    } catch (SQLException e) {
	        e.printStackTrace();
	    	throw new Exception("Erro ao cadastrar registro: " + e.getMessage());
	        
	    }finally{	    
	        try {
	           if(pStatement != null){pStatement.close();}
	        } catch (SQLException e) {
	            throw new Exception("Erro ao fechar pStatement:" + e);
	        }try {
	        if(connection != null){connection.close();}
	        } catch (SQLException e) {
	             throw new Exception("Erro ao fechar conexão:" + e);
	        }
	    }
	   return retorno;
	
	}

}
