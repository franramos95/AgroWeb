AgroWeb.TabelaItens = (function() {
	
	function TabelaItens(autocomplete) {
		this.autocomplete = autocomplete;
		this.tabelaProdutoContainer = $('.js-tabela-produto-container');
	}
	
	TabelaItens.prototype.iniciar = function() {
		this.autocomplete.on('item-selecionado', onItemSelecionado.bind(this));
	}
	
	function onItemSelecionado(evento, item) {
		var resposta = $.ajax({
			url: 'item',
			method: 'POST',
			data: {
				codigoProduto: item.id
			}
		});
		
		resposta.done(onItemAdicionadoNoServidor.bind(this));
	}
	
	function onItemAdicionadoNoServidor(html){
		this.tabelaProdutoContainer.html(html);
	}
	
	return TabelaItens;
	
}());

$(function() {
	
	var autocomplete = new AgroWeb.Autocomplete();
	autocomplete.iniciar();
	
	var tabelaItens = new AgroWeb.TabelaItens(autocomplete);
	tabelaItens.iniciar();
	
});