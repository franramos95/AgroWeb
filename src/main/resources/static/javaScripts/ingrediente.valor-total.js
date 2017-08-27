var AgroWeb = AgroWeb || {};

AgroWeb.IngredienteValorTotal = (function() {
	
	function IngredienteValorTotal() {
		this.valorTotalInput = $('.js-valor-total');
		this.valorUnitarioInput = $('.js-valor-unitario')
		this.quantidadeInput = $('.js-quantidade');	

		this.valorUnitario = this.valorUnitarioInput.val();
		this.valorQuantidade = this.quantidadeInput.val();
	}
	
	IngredienteValorTotal.prototype.iniciar = function() {
		
		this.valorUnitarioInput.on('change', onValoresAlterados.bind(this));
		this.quantidadeInput.on('change', onValoresAlterados.bind(this));
		
	}
	 
	function onValoresAlterados(evento) {
				
		var valor = numeral(this.valorUnitario) * this.valorQuantidade;
		console.log(numeral(this.valorUnitario));
		console.log(this.valorQuantidade);
		console.log(valor);
		this.valorTotalInput.val(valor);
	}
	
	return IngredienteValorTotal;
	
}());

$(function(){
	var ingredienteValorTotal = new AgroWeb.IngredienteValorTotal();
ingredienteValorTotal.iniciar();
});