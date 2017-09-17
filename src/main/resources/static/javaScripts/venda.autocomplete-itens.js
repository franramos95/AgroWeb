AgroWeb = AgroWeb || {};

AgroWeb.Autocomplete = (function() {
	
	function Autocomplete() {
		this.NomeInput = $('.js-nome-produto-input');
		var htmlTemplateAutocomplete = $('#template-autocomplete-produto').html();
		this.template = Handlebars.compile(htmlTemplateAutocomplete);
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}
	
	Autocomplete.prototype.iniciar = function() {
		var options = {
			url: function(nome) {
				return this.NomeInput.data('url')'?nome=' + nome;
			}.bind(this),
			getValue: 'nome',
			minCharNumber: 3,
			requestDelay: 300,
			ajaxSettings: {
				contentType: 'application/json'
			},
			template: {
				type: 'custom',
				method: template.bind(this)
			},
			list: {
				onChooseEvent: onItemSelecionado.bind(this)
			}
		};
		
		this.NomeInput.easyAutocomplete(options);
	}
	
	function onItemSelecionado() {
		this.emitter.trigger('item-selecionado', this.NomeInput.getSelectedItemData());
		this.NomeInput.val('');
		this.NomeInput.focus();

	}
	
	function template(nome, produto) {
		produto.valorFormatado = AgroWeb.formatarMoeda(produto.valor);
		return this.template(produto);
	}
	
	return Autocomplete;
	
}());