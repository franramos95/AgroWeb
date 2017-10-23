var AgroWeb = AgroWeb || {};

AgroWeb.GraficoDespesaPorMes = (function() {
	
	function GraficoDespesaPorMes() {
		this.ctx = $('#graficoDespesaPorMes')[0].getContext('2d')
	}
	
	GraficoDespesaPorMes.prototype.iniciar = function() {
		$.ajax({
			url: 'despesas/totalPorMes',
			method: 'GET', 
			success: onDadosRecebidos.bind(this)
		});
	}
	
	function onDadosRecebidos(despesaMes) {
		var meses = [];
		var valores = [];
		despesaMes.forEach(function(obj) {
			meses.unshift(obj.mes);
			valores.unshift(obj.total);
		});
		
		var graficoDespesaPorMes = new Chart(this.ctx, {
		    type: 'line',
		    data: {
		    	labels: meses,
		    	datasets: [{
		    		label: 'Despesas por mês',
		    		backgroundColor: "rgba(26,179,148,0.5)",
	                pointBorderColor: "rgba(26,179,148,1)",
	                pointBackgroundColor: "#fff",
	                data: valores
		    	}]
		    },
		});
}
	
	return GraficoDespesaPorMes;
}());

$(function() {
	var graficoDespesaPorMes = new AgroWeb.GraficoDespesaPorMes();
	graficoDespesaPorMes.iniciar();
});

AgroWeb.GraficoMortalidadePorMes = (function() {
	
	function GraficoMortalidadePorMes() {
		this.ctx = $('#graficoMortalidadePorMes')[0].getContext('2d')
	}
	
	GraficoMortalidadePorMes.prototype.iniciar = function() {
		$.ajax({
			url: 'animal/totalPorMes',
			method: 'GET', 
			success: onDadosRecebidos.bind(this)
		});
	}
	
	function onDadosRecebidos(mortalidadeMes) {
		var meses = [];
		var valores = [];
		mortalidadeMes.forEach(function(obj) {
			meses.unshift(obj.mes);
			valores.unshift(obj.total);
		});
		
		var GraficoMortalidadePorMes = new Chart(this.ctx, {
		    type: 'line',
		    data: {
		    	labels: meses,
		    	datasets: [{
		    		label: 'Mortalidade por mês',
		    		backgroundColor: "rgba(26,179,148,0.5)",
	                pointBorderColor: "rgba(26,179,148,1)",
	                pointBackgroundColor: "#fff",
	                data: valores
		    	}]
		    },
		});
}
	
	return GraficoMortalidadePorMes;
}());

$(function() {
	var graficoMortalidadePorMes = new AgroWeb.GraficoMortalidadePorMes();
	graficoMortalidadePorMes.iniciar();
});
