AgroWeb.Dieta = (function(){
	
	function Dieta(tabelaIngredientes){
		this.tabelaIngredientes =  tabelaIngredientes;
		
	}
	
	Dieta.prototype.iniciar = function(){
		
	}
	
	function onTabelaIngredientesAtualizado(evento){
	}
}());

$(function(){
	var dieta = new AgroWeb.Dieta(tabelaIngredientes);
	dieta.iniciar;
});