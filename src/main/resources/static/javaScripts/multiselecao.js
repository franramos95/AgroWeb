AgroWeb = AgroWeb || {};


AgroWeb.MultiSelecao = (function(){
	
	function MultiSelecao() {
		this.statusBtn = $('.js-satatus-btn');
		this.selecaoCheckBox = $('.js-selecao');
		this.selecaoTodosCheckbox = $('.js-selecao-todos');
	}
	
	MultiSelecao.prototype.iniciar = function(){
		this.statusBtn.on('click', onStatusBtnClicado.bind(this));
		this.selecaoTodosCheckbox.on('click', onSelecaoTodosClicado.bind(this));
		this.selecaoCheckBox.on('click', onSelecaoClicado.bind(this));
	}
	
	function onStatusBtnClicado(event){
		var botaoClicado = $(event.currentTarget);
		var status = botaoClicado.data('status');
		var url = botaoClicado.data('url');
		
		var cheackBoxSelecionados = this.selecaoCheckBox.filter(':checked');
		var codigos = $.map(cheackBoxSelecionados, function(c){
			return $(c).data('codigo');
		});
		
		if(codigos.length > 0){
			$.ajax({
				url: url,
				method: 'PUT',
				data: {
					codigos: codigos,
					status: status
				},
				sucess: function(){
					window.location.reload();
				}
			});
		}

	}
	
	function onSelecaoTodosClicado() {
		var status = this.selecaoTodosCheckbox.prop('checked');
		this.selecaoCheckBox.prop('checked',status);
		statusBotaoAcao.call(this, status);
	}
	
	function onSelecaoClicado(){
		var selecaoCheckBoxChecados = this.selecaoCheckBox.filter(':checked');
		this.selecaoTodosCheckbox.prop('checked',selecaoCheckBoxChecados.length >= this.selecaoCheckBox.length);
		statusBotaoAcao.call(this, selecaoCheckBoxChecados.length);
	}
	
	function statusBotaoAcao(ativar){
		ativar ? this.statusBtn.removeClass('disabled') : this.statusBtn.addClass('disabled');
	}
	
	return MultiSelecao;
	
}());

$(function() {
	var multiselecao = new AgroWeb.MultiSelecao();
	multiselecao.iniciar();
});