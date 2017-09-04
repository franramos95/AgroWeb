AgroWeb.TabelaItens = (function(){
	
	function TabelaItens(autocomplete){
		this.autocomplete = autocomplete;
		this.tabelaIngredientesContainer = $('.js-tabela-ingredientes-container');
	}
	
	TabelaItens.prototype.iniciar = function() {
		this.autocomplete.on('item-selecionado', onItemSelecionado.bind(this));
	}
	
	function onItemSelecionado(evento, item){
		var resposta = $.ajax({
			url: 'item',
			method: 'POST',
			data: {
				idIngrediente: item.id
			}
		});
		
		resposta.done(onItemAdicionadoNoServidor.bind(this));
	}
	
	function onItemAdicionadoNoServidor(html){
		this.tabelaIngredientesContainer.html(html);
		$('.js-tabela-ingrediente-quantidade-item').on('change', onQuantidadeItemAlterado.bind(this));
		$('.js-tabela-item').on('dblclick', onDoubleClick);
		$('.js-exclusao-item-btn').on('click', onExclusaoItemClick.bind(this))
	}
	
	function onQuantidadeItemAlterado(evento) {
		var input = $(evento.target);
		var quantidade = input.val();
		var idIngrediente = input.data('codigo-ingrediente');
		
		var resposta = $.ajax({
			url: 'item/' + idIngrediente,
			method: 'PUT',
			data: {
				quantidade: quantidade
			}
		});
		
		resposta.done(onItemAdicionadoNoServidor.bind(this));
	}
	
	function onDoubleClick(evento){
		$(this).toggleClass('solicitando-exclusao');
	}
	
	function onExclusaoItemClick(evento){
		var idIngrediente = $(evento.target).data('codigo-ingrediente');
		var resposta = $.ajax({
			url: 'item/' + idIngrediente,
			method: 'DELETE'
		});
		
		resposta.done(onItemAdicionadoNoServidor.bind(this));
	}
	
	return TabelaItens;
	
}());

$(function() {
	
	var autocomplete = new AgroWeb.Autocomplete();
	autocomplete.iniciar();
	
	var tabelaItens = new AgroWeb.TabelaItens(autocomplete);
	tabelaItens.iniciar();
	
});