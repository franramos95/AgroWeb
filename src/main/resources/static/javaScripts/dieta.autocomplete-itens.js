AgroWeb = AgroWeb || {};

AgroWeb.Autocomplete = (function(){
	
	function Autocomplete(){
		this.idOuNomeInput = $('.js-id-nome-ingrediente-input');
		var htmlTemplateAutocomplete = $('#template-autocomplete-ingrediente').html();
		this.template = Handlebars.compile(htmlTemplateAutocomplete);
		  this.emitter = $({});
		  this.on = this.emitter.on.bind(this.emitter);
	}
	
	Autocomplete.prototype.iniciar = function(){
		var options = {
				   url: function(idOuNome) {
				    return this.idOuNomeInput.data('url') + '?idOuNome=' + idOuNome;
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
				  
				  this.idOuNomeInput.easyAutocomplete(options);
	}
	
	function onItemSelecionado() {
		this.emitter.trigger('item-selecionado', this.idOuNomeInput.getSelectedItemData());
		this.idOuNomeInput.val('');
		this.idOuNomeInput.focus('');
	}
	
	return Autocomplete
}());