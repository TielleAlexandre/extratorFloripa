package main;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import dao.RegistroDAO;
import model.Registro;
import model.RegistroBruto;

public class extrator {

	public static void main(String[] args) {
		String rota = "184";	
		String tabela1 = "Dez2019";
		String tabela2 = "rgViagem_dezembro";
		
		List<RegistroBruto> listaRegistros = null;

		try {
			RegistroDAO rgDao = new RegistroDAO();
			listaRegistros = rgDao.listarRegistrosRota(rota,tabela1);
			if (listaRegistros != null) {
				for (RegistroBruto registroBruto : listaRegistros) {
					// System.out.println(registroBruto.getHoraIni()+ " - "
					// +registroBruto.getTempoViagem());
					String dataPartida = tratarData(registroBruto.getDataIni(), registroBruto.getHoraIni());
					String dataChegada = tratarData(registroBruto.getDataFim(), registroBruto.getHoraFim());
					// System.out.println(dataChegada);

					Registro regViagem = new Registro();
					regViagem.setTimeStampDataPartida(transformarTimeStamp(dataPartida));
					regViagem.setTimeStampDataChegada(transformarTimeStamp(dataChegada));
					regViagem.setSentido(tratarSentido(registroBruto.getSentido()));
					regViagem.setLinha(registroBruto.getLinha());
					regViagem.setnVeiculos(Integer.parseInt(registroBruto.getnVeiculo()));
					regViagem.setTotalGiros(Integer.parseInt(registroBruto.getTotalGiros()));
					regViagem.setKmPercorridos(Float.parseFloat(registroBruto.getKmPercorrido()));
					regViagem.setDia_semana(pegarDiaSemana(dataPartida));
					regViagem.setTipo_dia(1);
					DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					regViagem.setData_hora_partida(format.parse(dataPartida));
					regViagem.setData_hora_chegada(format.parse(dataChegada));
					regViagem.setTempoViagem(calcularTempoViagem(dataPartida, dataChegada));
					rgDao.cadastrarRegistro(regViagem, tabela2);
					
				}

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String tratarData(String data, String hora) {
		String dataMontada;

		String[] arrayData = data.split("/");
		dataMontada = arrayData[2] + "-" + arrayData[1] + "-" + arrayData[0] + " " + hora;
		return dataMontada;
	}

	public static Long transformarTimeStamp(String data) throws ParseException {
		TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dataTimeStamp = format.parse(data);
		long timestamp = dataTimeStamp.getTime() / 1000L;
		System.out.println("Data: " + data + " Timestamp: " + timestamp);
		return timestamp;
	}
	
	public static int tratarSentido(String sentido) {
	int sentido_int=0;
		if(sentido.equals("Ida")) {
			sentido_int = 1;			
		}
		if(sentido.equals("Volta")) {
			sentido_int = 2;			
		}
	return sentido_int;	
		
	}
	
	public static int pegarDiaSemana(String date) { // ex 01/08/2019
		int dayWeek = 0;
		GregorianCalendar gc = new GregorianCalendar();
		try {
			gc.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(date));
			switch (gc.get(Calendar.DAY_OF_WEEK)) {
			case Calendar.SUNDAY:
				dayWeek = 7;
				break;
			case Calendar.MONDAY:
				dayWeek = 1;
				break;
			case Calendar.TUESDAY:
				dayWeek = 2;
				break;
			case Calendar.WEDNESDAY:
				dayWeek = 3;
				break;
			case Calendar.THURSDAY:
				dayWeek = 4;
				break;
			case Calendar.FRIDAY:
				dayWeek = 5;
				break;
			case Calendar.SATURDAY:
				dayWeek = 6;

			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dayWeek;
	}

	public static long calcularTempoViagem(String dataInicial_1, String dataFinal_2) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dataInicial1 = format.parse(dataInicial_1);
		Date dataFinal2 = format.parse(dataFinal_2);
				
		Calendar dataInicial = Calendar.getInstance();
		dataInicial.setTime(dataInicial1);

		Calendar dataFinal = Calendar.getInstance();
		dataFinal.setTime(dataFinal2);

		long diferenca = dataFinal.getTimeInMillis() - dataInicial.getTimeInMillis();
		long diferencaSeg = diferenca / 1000; // 
		
		return diferencaSeg;
	}
	
	
	
}