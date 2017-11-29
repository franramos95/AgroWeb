var AgroWeb = AgroWeb || {};

AgroWeb.VendaValorTotal = (function() {
	
	function VendaValorTotal() {
		this.valorTotalInput = $('.js-valor-total');
		this.valorUnitarioInput = $('.js-valor-unitario');
		this.freteInput = $('.js-frete');	
		this.descontoInput = $('.js-desconto');
	}
	
	VendaValorTotal.prototype.iniciar = function() {
		
		this.valorUnitarioInput.on('change', onValoresAlterados.bind(this));
		this.freteInput.on('change', onValoresAlterados.bind(this));
		this.descontoInput.on('change', onValoresAlterados.bind(this));
		
	}
	 
	function onValoresAlterados(evento) {
		
		this.valorUnitario = this.valorUnitarioInput.val();
		this.valorFrete = this.freteInput.val();
		this.valorDesconto = this.descontoInput.val();
				
		var valor = numeral(this.valorUnitario) + (this.valorFrete) - (this.valorDesconto);
		this.valorTotalInput.val(AgroWeb.formatarMoeda(valor));
	}
	
	return VendaValorTotal;
	
}());

$(function(){
	var vendaValorTotal = new AgroWeb.VendaValorTotal();
vendaValorTotal.iniciar();
});