var AgroWeb = AgroWeb || {};

AgroWeb.MaskMoney = (function(){
	function MaskMoney(){
		this.decimal = $('.js-decimal');
		this.plain = $('.js-plain')
	}
	
	MaskMoney.prototype.enable = function(){
		this.decimal.maskMoney({ decimal:',', thousands: '.'});
		this.plain.maskMoney({precision: 0, thousands: '.'});
	}
	
	return MaskMoney;
	
}());

AgroWeb.MaskPhoneNumber = (function() {
	
	function MaskPhoneNumber() {
		this.inputPhoneNumber = $('.js-phone-number');
	}
	
	MaskPhoneNumber.prototype.enable = function() {
		var maskBehavior = function (val) {
		  return val.replace(/\D/g, '').length === 11 ? '(00) 00000-0000' : '(00) 0000-00009';
		};
		
		var options = {
		  onKeyPress: function(val, e, field, options) {
		      field.mask(maskBehavior.apply({}, arguments), options);
		    }
		};
		
		this.inputPhoneNumber.mask(maskBehavior, options);
	}
	
	return MaskPhoneNumber;
	
}());

AgroWeb.MaskCep = (function() {
	
	function MaskCep() {
		this.inputCep = $('.js-cep');
	}
	
	MaskCep.prototype.enable = function() {
		this.inputCep.mask('00.000-000');
	}
	
	return MaskCep;
	
}());

AgroWeb.MaskDate = (function() {
	
	function MaskDate() {
		this.inputDate = $('.js-date');
	}
	
	MaskDate.prototype.enable = function() {
		this.inputDate.mask('00/00/0000');
		this.inputDate.datepicker({
			orientation: 'bottom',
			language: 'pt-BR',
			autoclose: true
		});
	}
	
	return MaskDate;
	
}());

AgroWeb.Security = (function() {
	
	function Security() {
		this.token = $('input[name=_csrf]').val();
		this.header = $('input[name=_csrf_header]').val();
	}
	
	Security.prototype.enable = function() {
		$(document).ajaxSend(function(event, jqxhr, settings) {
			jqxhr.setRequestHeader(this.header, this.token);
		}.bind(this));
	}
	
	return Security;
	
}());

numeral.language('pt-br');

AgroWeb.formatarMoeda = function(valor) {
	return numeral(valor).format('0,0.00');
}

AgroWeb.recuperarValor = function(valorFormatado) {
	return numeral().unformat(valorFormatado);
}

$(function() {
	var maskMoney = new AgroWeb.MaskMoney(); 
	maskMoney.enable();
	
	var maskPhoneNumber = new AgroWeb.MaskPhoneNumber();
	maskPhoneNumber.enable();
	
	var maskCep = new AgroWeb.MaskCep();
	maskCep.enable();	
	
	var maskDate = new AgroWeb.MaskDate();
	maskDate.enable();
	
	var security = new AgroWeb.Security();
	security.enable();
}); 