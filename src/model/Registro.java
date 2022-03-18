package model;

import java.util.Date;

public class Registro {
	
	private long timeStampDataPartida;
	private long timeStampDataChegada;
	private int sentido;
	private String linha;
	private int nVeiculos;
	private int tempoViagemSegundos;
	private Long  tempoViagem;
	private int totalGiros;
	private float kmPercorridos;
	private int dia_semana;
	private int tipo_dia;
	private int turno;
    private Date data_hora_partida;
    private Date data_hora_chegada;
	
	public long getTimeStampDataPartida() {
		return timeStampDataPartida;
	}
	public void setTimeStampDataPartida(long timeStampDataPartida) {
		this.timeStampDataPartida = timeStampDataPartida;
	}
	public long getTimeStampDataChegada() {
		return timeStampDataChegada;
	}
	public void setTimeStampDataChegada(long timeStampDataChegada) {
		this.timeStampDataChegada = timeStampDataChegada;
	}
	public int getSentido() {
		return sentido;
	}
	public void setSentido(int sentido) {
		this.sentido = sentido;
	}
	public String getLinha() {
		return linha;
	}
	public void setLinha(String linha) {
		this.linha = linha;
	}
	public int getnVeiculos() {
		return nVeiculos;
	}
	public void setnVeiculos(int nVeiculos) {
		this.nVeiculos = nVeiculos;
	}
	public int getTempoViagemSegundos() {
		return tempoViagemSegundos;
	}
	public void setTempoViagemSegundos(int tempoViagemSegundos) {
		this.tempoViagemSegundos = tempoViagemSegundos;
	}
	public Long getTempoViagem() {
		return tempoViagem;
	}
	public void setTempoViagem(Long tempoViagemHora) {
		this.tempoViagem = tempoViagemHora;
	}
	public int getTotalGiros() {
		return totalGiros;
	}
	public void setTotalGiros(int totalGiros) {
		this.totalGiros = totalGiros;
	}
	public float getKmPercorridos() {
		return kmPercorridos;
	}
	public void setKmPercorridos(float kmPercorridos) {
		this.kmPercorridos = kmPercorridos;
	}
	public int getDia_semana() {
		return dia_semana;
	}
	public void setDia_semana(int dia_semana) {
		this.dia_semana = dia_semana;
	}
	public int getTipo_dia() {
		return tipo_dia;
	}
	public void setTipo_dia(int tipo_dia) {
		this.tipo_dia = tipo_dia;
	}
	public int getTurno() {
		return turno;
	}
	public void setTurno(int turno) {
		this.turno = turno;
	}
	public Date getData_hora_partida() {
		return data_hora_partida;
	}
	public void setData_hora_partida(Date data_hora_partida) {
		this.data_hora_partida = data_hora_partida;
	}
	public Date getData_hora_chegada() {
		return data_hora_chegada;
	}
	public void setData_hora_chegada(Date data_hora_chegada) {
		this.data_hora_chegada = data_hora_chegada;
	}	
	
	

}
