package br.com.easyShop.aplicacao
{
	import br.com.easyShop.model.*;
	import br.com.easyShop.telas.produtos.AbaDetalhesProduto;
	import br.com.easyShop.utils.GlobalEasyShop;
	import br.com.mresolucoes.componentes.mre.Alerta;
	import br.com.mresolucoes.componentes.mre.MModulo;

	public class ImportsMain
	{
		public var alerta:Alerta = null;
		public var mModulo:MModulo = null;
		public var global:GlobalEasyShop = null;
		public var abaDetalhesProduto:AbaDetalhesProduto = null;
		public var carrinho:Carrinho = null;
		public var carrinhoProduto:CarrinhoProduto = null;
		public var categoria:Categoria = null;
		public var cidade:Cidade = null;
		public var cliente:Cliente = null;
		public var contato:Contato = null;
		public var desejo:Desejo = null;
		public var desejoProduto:DesejoProduto = null;
		public var endereco:Endereco = null;
		public var estado:Estado = null;
		public var marca:Marca = null;
		public var pais:Pais = null;
		public var pedido:Pedido = null;
		public var pedidoProduto:PedidoProduto = null;
		public var perfilPagamento:PerfilPagamento = null;
		public var pessoa:Pessoa = null;
		public var pessoaFisica:PessoaFisica = null;
		public var pessoaJuridica:PessoaJuridica = null;
		public var preferencia:Preferencia = null;
		public var produto:Produto = null;
		public var tela:Tela = null;
		public var tipoPermissao:TipoPermissao = null;
		public var usuario:Usuario = null;
		public var usuarioTela:UsuarioTela = null;
	}
}