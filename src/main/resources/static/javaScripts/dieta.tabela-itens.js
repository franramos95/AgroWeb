AgroWeb.TabelaItens = (function(){
	
	fuction TabelaItens(autocomplete){
		this.autocomplete = autocomplete;
		this.tabelaIngredientesContainer = $('.js-tabela-ingredientes-container');
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}
	
	TabelaItens.prototype.iniciar = function() {
		  this.autocomplete.on('item-selecionado', onItemSelecionado.bind(this));
		  
		  bindQuantidade.call(this);
		  bindTabelaItem.call(this);
	}
	
	function onItemSelecionado(evento, item) {
		  var resposta = $.ajax({
		   url: 'item',
		   method: 'POST',
		   data: {
		    idIngrediente: ingrediente.id
		   }
		  });
		  
		  resposta.done(onItemAtualizadoNoServidor.bind(this));
		 }
	
	function onItemAtualizadoNoServidor(html) {
		this.tabelaIngredientesContainer.html(html);
		
		 bindQuantidade.call(this);
		 
		 var tabelaItem = bindTabelaItem.call(this);
		 this.emitter.trigger('tabela-itens-atualizada');
	}
	
	function onDoubleClick(evento) {
		  $(this).toggleClass('solicitando-exclusao');
	}	
}());