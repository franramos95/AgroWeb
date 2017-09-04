var AgroWeb = AgroWeb || {};

AgroWeb.Autocomplete = (function() {
	
	function Autocomplete() {
		this.nomeInput = $('.js-nome-ingrediente-input');
		var htmlTemplateAutoComplete = $('#template-autocomplete-ingrediente').html(); 
		this.template = Handlebars.compile(htmlTemplateAutoComplete);
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}
	
	Autocomplete.prototype.iniciar = function() {
		var options = {
				url: function(nome){
					return this.nomeInput.data('url') + '?nome=' + nome;
				}.bind(this),
				getValue: 'nome',
				minCharNumber: 3,
				requestDelay: 300,
				ajaxSettings: {
					contentType:'application/json'
				},
				template: {
					type: 'custom',
					method: template.bind(this) 
				},
				list: {
					onChooseEvent: onItemSelecionado.bind(this)
				}
		};
		
		this.nomeInput.easyAutocomplete(options);
	}
	
	function onItemSelecionado(){
		this.emitter.trigger('item-selecionado', this.nomeInput.getSelectedItemData());
		this.nomeInput.val('');
		this.nomeInput.focus();
	}
	
	function template(nome, ingrediente){
			ingrediente.valorFormatado = AgroWeb.formatarMoeda(ingrediente.valorUnitario);
			return this.template(ingrediente);
	}
	
	return Autocomplete
	
}());
