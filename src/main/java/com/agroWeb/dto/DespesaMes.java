package com.agroWeb.dto;

public class DespesaMes {
	
		private String mes;
		private Integer total;

		public DespesaMes() {
		}

		public DespesaMes(String mes, Integer total) {
			this.mes = mes;
			this.total = total;
		}

		public String getMes() {
			return mes;
		}

		public void setMes(String mes) {
			this.mes = mes;
		}

		public Integer getTotal() {
			return total;
		}

		public void setTotal(Integer total) {
			this.total = total;
		}

	}