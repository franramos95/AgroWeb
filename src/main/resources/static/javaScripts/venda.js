AgroWeb.Venda = (function(){
	
	function Venda(tabelaItensVenda){
		this.tabelaItensVenda =  tabelaItensVenda;
		
	}
	
	Venda.prototype.iniciar = function(){
		
	}
	
	function onTabelaItensVendaAtualizado(evento){
	}
}());

$(function(){
	var venda = new AgroWeb.Venda(tabelaItensVenda);
	venda.iniciar;
});