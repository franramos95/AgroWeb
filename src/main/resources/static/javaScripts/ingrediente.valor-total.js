var AgroWeb = AgroWeb || {};

AgroWeb.IngredienteValorTotal = (function() {
	
	function IngredienteValorTotal() {
		this.valorTotalInput = $('.js-valor-total');
		this.valorUnitarioInput = $('.js-valor-unitario');
		this.quantidadeInput = $('.js-quantidade');	
	}
	
	IngredienteValorTotal.prototype.iniciar = function() {
		
		this.valorUnitarioInput.on('change', onValoresAlterados.bind(this));
		this.quantidadeInput.on('change', onValoresAlterados.bind(this));
		
	}
	 
	function onValoresAlterados(evento) {
		
		this.valorUnitario = this.valorUnitarioInput.val();
		this.valorQuantidade = this.quantidadeInput.val();
				
		var valor = numeral(this.valorUnitario) * (this.valorQuantidade);
		this.valorTotalInput.val(AgroWeb.formatarMoeda(valor));
	}
	
	return IngredienteValorTotal;
	
}());

$(function(){
	var ingredienteValorTotal = new AgroWeb.IngredienteValorTotal();
ingredienteValorTotal.iniciar();
});