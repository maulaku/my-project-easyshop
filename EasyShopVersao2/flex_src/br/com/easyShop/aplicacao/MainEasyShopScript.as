import br.com.easyShop.aplicacao.AccordionItem;
import br.com.easyShop.aplicacao.MainEasyShop;
import br.com.easyShop.componentes.modulo.ModuloItem;
import br.com.easyShop.comunicacao.MRemoteObject;
import br.com.easyShop.comunicacao.ResultJava;
import br.com.easyShop.model.Categoria;
import br.com.easyShop.model.Cliente;
import br.com.easyShop.model.Desejo;
import br.com.easyShop.model.DesejoProduto;
import br.com.easyShop.model.Pessoa;
import br.com.easyShop.model.PessoaFisica;
import br.com.easyShop.model.Produto;
import br.com.easyShop.model.Usuario;
import br.com.easyShop.telas.Login.Login;
import br.com.easyShop.telas.Login.Logout;
import br.com.easyShop.telas.desejos.AbaMeuDesejo;
import br.com.easyShop.telas.pagamento.ConfirmarCompra;
import br.com.easyShop.telas.pagamento.Pagamentos;
import br.com.easyShop.telas.pagamento.PedidoConfirmado;
import br.com.easyShop.telas.pedidos.AbaDetalheMeuPedido;
import br.com.easyShop.telas.pedidos.AbaMeusPedidos;
import br.com.easyShop.telas.produtos.AbaDetalhesProduto;
import br.com.easyShop.telas.produtos.MeuCarrinho;
import br.com.easyShop.utils.Constantes;
import br.com.mresolucoes.componentes.mre.Alerta;
import br.com.mresolucoes.componentes.mre.MBotao;
import br.com.mresolucoes.componentes.mre.MModulo;
import br.com.mresolucoes.imagens.ImagensUtils;
import br.com.mresolucoes.utils.NumberUtil;

import flash.display.Loader;
import flash.events.Event;
import flash.events.MouseEvent;
import flash.net.URLRequest;

import mx.containers.VBox;
import mx.controls.Alert;
import mx.controls.LinkButton;
import mx.core.UIComponent;
import mx.managers.PopUpManager;
import mx.states.AddChild;
import mx.utils.ObjectUtil;

import spark.components.Button;
import spark.components.Label;

private var painelLogin:Login;
private var painelLogout:Logout;
private var meuCarrinho:MeuCarrinho;
private var meuDesejo:AbaMeuDesejo;
private var painelPagamentos:Pagamentos;
private var confirmarCompra:ConfirmarCompra;
private var pedidoConfirmado:PedidoConfirmado;
private var painelMeusPedidos:AbaMeusPedidos;
private var painelMeusPedidosDetalhe:AbaDetalheMeuPedido;

private var produtoAux:Produto;
private var nomeCategoriaSelecionada:String;
private var nomePaiCategoriaSelecionada:String;
private var accondeonAtual:String;

private static var clienteGlobal:Cliente; //Cliente Global da Aplicação. Ele é setado pelo Login.
private static var usuarioGlobal:Usuario; //Usuario Global da Aplicação. Ele é setado pelo Login.

[Bindable]
private static var produto:Produto;

public static function getProdutoGlobal():Produto
{
	return produto;	
}

/**
 * Inicializa os componentes e objetos
 */ 

public static function getClienteGlobal():Cliente
{
	return clienteGlobal;
}

public static function getUsurioGlobal():Usuario
{
	return usuarioGlobal;
}

public static function setProdutoGlobal(produto2:Produto):void
{
	produto = produto2; 
}

public function construtor():void
{
	modulo.removeAllElements();
	if(clienteGlobal==null){
		btnCarrinho.visible = false;
		btnDesejo.visible = false;
		btnPedido.visible = false;
	}
	grPainelModulos.removeAllElements();
	panelCategorias.title = "Categorias Principais" ;
	cbBusca.mreServicePesquisa = "ProdutoService.getProdutosNome";
	MRemoteObject.get("CategoriaService.getTodasCategoriasPai", null, resultCategoria);
	MRemoteObject.get("ProdutoService.getProdutosPromocao", null, resultProduto);
	cbBusca.mreSetSelectedItem(null);
	cbCategorias.mreSetSelectedItem(null);
}

/* Listerners Java */
public function resultProduto(result:ResultJava):void
{
	try		
	{		
		if(result.lista.length != 0)
		{	
			grPainelModulos.removeAllElements();
			var produto:Produto = new Produto();
			for(var i:int=0;i<result.lista.length;i++)
			{				
				produto = result.lista.getItemAt(i) as Produto;  
				var item:ModuloItem = new ModuloItem();
				
				item.nome = produto.nome;
				item.preco = NumberUtil.toString(produto.preco, 2);
				item.modulo = modulo;
				item.produto = produto;
				item.imagemSource = Constantes.instance.ENDERECO_IMAGEM_PRODUTO+NumberUtil.toString(produto.pkProduto)+".jpg";
				item.addEventListener("clickadoModuloItem", lidaModuloItem);
				item.addEventListener("clickadoModuloItemDesejo", adicionaMeuDesejo);
				
				grPainelModulos.addModulo(item);
			}
			
			grPainelModulos.visible = true;
		}
		else{
			Alerta.abrir("Ainda não há produtos na categoria selecionada!", "EasyShop", null, null, null, ImagensUtils.ATENCAO);
		}
	} 
	catch(e:Error)
	{ 
		Alerta.abrir("Ops, Ocorreu um erro ao carregar categorias", "EasyShop", null, null, null, ImagensUtils.INFO);
	}	
}

private function lidaModuloItem(event:Event):void{
	var item:ModuloItem = ((ModuloItem) (event.currentTarget));
	produto = item.produto;
	modulo.mreLoadModule("br/com/easyShop/telas/produtos/AbaDetalhesProduto.swf");
}

public function detalhes(event:Event):void{
	var item:ModuloItem = ((ModuloItem) (event.currentTarget));
	produto = item.produto;
	modulo.mreLoadModule("br/com/easyShop/telas/produtos/AbaDetalhesProduto.swf");
}


public function escutaBotoes(botao:MBotao):void
{
	try
	{
		if (botao == btBuscar)
		{
			if(cbBusca.mreGetSelectedItem() != null)
			{
				produto = cbBusca.mreGetSelectedItem() as Produto;
				modulo.mreLoadModule("br/com/easyShop/telas/produtos/AbaDetalhesProduto.swf");
			}
		}
	}
	catch(e:Error)
	{
		
	}
}
private function adicionaMeuDesejo(event:Event):void{
	
	var item:ModuloItem = ((ModuloItem) (event.currentTarget));
	produto = item.produto;
	
	var desejoProduto:DesejoProduto = new DesejoProduto();
	var desejo:Desejo = new Desejo();
	
	desejo.cliente = ((Cliente) (MainEasyShop.getClienteGlobal()));
	
	desejoProduto.produto = produto;
	desejoProduto.desejo = desejo;
	
	MRemoteObject.get("DesejoProdutoService.inserirDesejo", [desejoProduto], salvarMeusDesejos);
	
}

public function salvarMeusDesejos(result:ResultJava):void{
	
	try		
	{		
		if(result != null)
		{	
			Alerta.abrir("Produto adicionado aos seus desejos com sucesso!!", "EasyShop", null, null, null, ImagensUtils.INFO);
		}
	} 
	catch(e:Error)
	{ 
		Alerta.abrir("Ops, Ocorreu um erro ao adicionar o produto carregar categorias", "EasyShop", null, null, null, ImagensUtils.INFO);
	}		
}

public function resultCategoria(result:ResultJava):void
{
	try		
	{		
		if(result != null)
		{	
			accordion.removeAllElements();
			cbCategorias.mreDataProvider = result.lista;
			var categoria:Categoria = new Categoria();
			var i:int;
			
			for(i=0;i<result.lista.length;i++){
				categoria = ((Categoria) (result.lista[i]));  
				
				var acord:AccordionItem = new AccordionItem();
				acord.height = accordion.height;
				acord.width = accordion.width;
				acord.label = categoria.nome;
				acord.name = categoria.nome;
				acord.categoria = categoria;
				accondeonAtual = categoria.nome;
				acord.image = Constantes.instance.ENDERECO_IMAGEM_CATEGORIA+NumberUtil.toString(categoria.pkCategoria)+".jpg";
				acord.styleName = "gradientHeader";
				accordion.addElement(acord);
				MRemoteObject.get("CategoriaService.getTodasCategoriasSub", [categoria], resultSubCategoria);	
			}
			accordion.selectedIndex = result.lista.length;
		}
		else
		{ 
			Alerta.abrir("Ops, Ocorreu um erro ao carregar categorias", "EasyShop", null, null, null, ImagensUtils.INFO);
		}
		
	} 
	catch(e:Error)
	{ 
		Alerta.abrir("Ops, Ocorreu um erro ao carregar categorias", "EasyShop", null, null, null, ImagensUtils.INFO);
	}	
}

public function resultSubCategoria(result:ResultJava):void
{
	var i:int;
	var categoria:Categoria;
	var grid:VBox = new VBox();
	var novo:AccordionItem;
	
	try		
	{		
		if(result.lista.length != 0)
		{						
			for(i=0;i<result.lista.length;i++){
				categoria = ((Categoria) (result.lista[i]));
				novo = ((AccordionItem) (accordion.getChildByName(categoria.subCategoria.nome)));
				var label:Label = new Label();
				label.text = categoria.nome;
				label.buttonMode = true;
				label.id = "" + categoria.pkCategoria;
				label.accessibilityName = "" + categoria.subCategoria.pkCategoria;
				label.addEventListener(MouseEvent.CLICK,abrirCatalogos);
				grid.addElement(label);
			}	
			novo.addElement(grid);
		}
		else{			
			novo = ((AccordionItem) (accordion.getChildByName(accondeonAtual)));
			var label2:Label = new Label();
			label2.text = novo.categoria.nome;
			label2.buttonMode = true;
			label2.id = "" + novo.categoria.pkCategoria;
			label2.accessibilityName = "" + novo.categoria.nome;
			label2.addEventListener(MouseEvent.CLICK,abrirCatalogos);
			grid.addElement(label2);
			novo.addElement(grid);
		}
	} 
	catch(e2:Error)
	{ 
		Alerta.abrir("Ops, Ocorreu um erro ao carregar sub categorias", "EasyShop", null, null, null, ImagensUtils.INFO);
	}	
}

private function abrirCatalogos(event:MouseEvent):void {
	var categoria:Label = ((Label) (event.currentTarget));
	var string:String = categoria.id;
	nomeCategoriaSelecionada = string;
	nomePaiCategoriaSelecionada = categoria.accessibilityName;
	MRemoteObject.get("CategoriaService.getTodasCategoriasSubString", [string], resultCategoria2);
}

public function resultCategoria2(result:ResultJava):void
{
	try		
	{		
		if(result.lista.length != 0)
		{	
			MRemoteObject.get("CategoriaService.getTodasCategoriasSubString", [nomePaiCategoriaSelecionada], resultCategoria3);
		}
		else
		{ 
			MRemoteObject.get("ProdutoService.getProdutosCategoria", [nomeCategoriaSelecionada], resultProduto);
		}
	} 
	catch(e:Error)
	{ 
		Alerta.abrir("Ops, Ocorreu um erro ao carregar categorias", "EasyShop", null, null, null, ImagensUtils.INFO);
	}	
}

public function resultCategoria3(result:ResultJava):void
{
	try		
	{		
		if(result != null)
		{	
			accordion.removeAllElements();
			var categoria:Categoria = new Categoria();
			var i:int;
			
			for(i=0;i<result.lista.length;i++){
				categoria = ((Categoria) (result.lista[i]));  
				panelCategorias.title = categoria.subCategoria.nome ;
				var acord:AccordionItem = new AccordionItem();
				acord.height = accordion.height;
				acord.width = accordion.width;
				acord.label = categoria.nome;
				acord.categoria = categoria;
				acord.name = categoria.nome;
				acord.image = "@Embed('../imagens/botoes/back.png')";
				acord.styleName = "gradientHeader";
				accordion.addElement(acord);
				MRemoteObject.get("CategoriaService.getTodasCategoriasSub", [categoria], resultSubCategoria);	
			}
			accordion.selectedIndex = result.lista.length;
		}
		else
		{ 
			Alerta.abrir("Ops, Ocorreu um erro ao carregar categorias", "EasyShop", null, null, null, ImagensUtils.INFO);
		}
	} 
	catch(e:Error)
	{ 
		Alerta.abrir("Ops, Ocorreu um erro ao carregar categorias", "EasyShop", null, null, null, ImagensUtils.INFO);
	}	
}

public function mouseOver(evt:MouseEvent):void{
	aumentar.play([evt.currentTarget]);
}

public function mouseOut(evt:MouseEvent):void{
	diminuir.play([evt.currentTarget]);
}

protected function btnPedido_clickHandler():void{

	painelMeusPedidos = new AbaMeusPedidos();
	painelMeusPedidos.showCloseButton=true;
	painelMeusPedidos.setVisible(true);
    painelMeusPedidos.addEventListener("clicadoVerDetalhes", btnDetalhesPedido_clickHandler);
	PopUpManager.addPopUp(painelMeusPedidos, this, true);
	
	centralizarTela(painelMeusPedidos);
}

private function btnDetalhesPedido_clickHandler(event:Event):void
{
	painelMeusPedidos.visible = false;
	painelMeusPedidosDetalhe = new AbaDetalheMeuPedido();
	painelMeusPedidosDetalhe.showCloseButton=true;
	painelMeusPedidosDetalhe.setVisible(true);
	PopUpManager.addPopUp(painelMeusPedidosDetalhe, this, true);
	centralizarTela(painelMeusPedidosDetalhe);
}

private function enviaCliente():Cliente{
	
	var cliente:Cliente = new Cliente();
	
	cliente = MainEasyShop.getClienteGlobal();
	
	var pessoa:Pessoa = new Pessoa();
	var pessoaFisica:PessoaFisica = new PessoaFisica();
	
	pessoaFisica.pkPessoaFisica =1;
	
	pessoa.pessoaFisica = pessoaFisica;
	
	pessoa.pkPessoa =1;
	
	cliente.pkCliente =1;
	cliente.pessoa = pessoa;
	
	return cliente;
}

protected function btnEntrar_clickHandler():void
{
	if(clienteGlobal==null){
		painelLogin = new Login();
		painelLogin.showCloseButton=true;
		painelLogin.setVisible(true);
		painelLogin.addEventListener("clickadoLogin", lidaClickadoLogin);
		painelLogin.addEventListener("clickadoPessoaFisica", lidaClickadoPessoaFisica);
		painelLogin.addEventListener("clickadoPessoaJuridica", lidaClickadoPessoaJuridica);
		PopUpManager.addPopUp(painelLogin, this, true);
		
		centralizarTela(painelLogin);
	}
	else{
		painelLogout = new Logout();
		painelLogout.showCloseButton=true;
		painelLogout.setVisible(true);
		painelLogout.addEventListener("clickadoLogout", lidaClickadoLogout);
		painelLogout.addEventListener("clickadoPessoaFisica", lidaClickadoPessoaFisica2);
		painelLogout.addEventListener("clickadoPessoaJuridica", lidaClickadoPessoaJuridica2);
		PopUpManager.addPopUp(painelLogout, this, true);
		
		centralizarTela(painelLogout);
	}
}

protected function btnCarrinho_clickHandler(event:MouseEvent):void
{
	meuCarrinho = new MeuCarrinho();
	meuCarrinho.showCloseButton=true;
	meuCarrinho.setVisible(true);
	meuCarrinho.addEventListener("clickadoFinalizarCarrinho", btnPagamentos_clickHandler);
	meuCarrinho.addEventListener("clickadoContinuarComprando", continuarComprando);
	PopUpManager.addPopUp(meuCarrinho, this, true);
	
	centralizarTela(meuCarrinho);
}

private function btnPagamentos_clickHandler(event:Event):void
{
	meuCarrinho.visible = false;
	painelPagamentos = new Pagamentos();
	painelPagamentos.showCloseButton=true;
	painelPagamentos.setVisible(true);
	painelPagamentos.addEventListener("clickadoAvancar", clickadoAvancar);
	painelPagamentos.addEventListener("clickadoVoltarMeuCarrinho", clickadoVoltarMeuCarrinho);
	PopUpManager.addPopUp(painelPagamentos, this, true);
	
	centralizarTela(painelPagamentos);
}

private function clickadoAvancar(event:Event):void
{
	painelPagamentos.visible = false;
	confirmarCompra = new ConfirmarCompra();
	confirmarCompra.showCloseButton=true;
	confirmarCompra.setVisible(true);
	confirmarCompra.addEventListener("clickadoConfirmarCompra", clickadoConfirmarCompra);
	confirmarCompra.addEventListener("clickadoVoltarMeuCarrinho", clickadoVoltarMeuCarrinho);
	PopUpManager.addPopUp(confirmarCompra, this, true);
	
	centralizarTela(confirmarCompra);
}

private function clickadoConfirmarCompra(event:Event):void
{
	confirmarCompra.visible = false;
	pedidoConfirmado = new PedidoConfirmado();
	pedidoConfirmado.showCloseButton=true;
	pedidoConfirmado.setVisible(true);
	PopUpManager.addPopUp(pedidoConfirmado, this, true);
	
	centralizarTela(pedidoConfirmado);
}

private function clickadoVoltarMeuCarrinho(event:Event):void
{
	meuCarrinho.visible = true;
	painelPagamentos.visible = false;
	confirmarCompra.visible = false
}

private function continuarComprando(event:Event):void
{
	meuCarrinho.visible = false;
	meuCarrinho = null;
}

public static function centralizarTela(componente:UIComponent):void {
	if (componente != null) {
		var diferencaLargura:Number = componente.screen.width - componente.width;
		var diferencaAltura:Number = componente.screen.height - componente.height - 800;
		componente.x = componente.screen.x + (diferencaLargura / 2);
		componente.y = componente.screen.y + (diferencaAltura / 2);
	}
}

/* Label Function */
public function lfProduto(item:Object=null, colunm:Object=null):String
{
	try
	{
		if (item != null && item is Produto)
		{
			return (item as Produto).nome;					
		}
	}
	catch(e:Error)
	{
		Alerta.abrir("Ops, ocorreu um erro", "Easy Shop", null, null, null, ImagensUtils.INFO);
	}
	return "";
}

private function lidaClickadoLogout(event:Event):void{
	clienteGlobal = null;
	usuarioGlobal = null;
	painelLogout.setVisible(false);
	painelLogin.toolTip = "Login/Cadastrar";
	btnCarrinho.visible = false;
	btnDesejo.visible = false;
	btnPedido.visible = false;
}

private function lidaClickadoLogin(event:Event):void{
	MRemoteObject.get("ClienteService.getCliente",[event.currentTarget.txtUsuario.text, event.currentTarget.txtSenha.text], verificarLogin);
}

private function lidaClickadoPessoaFisica(event:Event):void{
	painelLogin.setVisible(false);
	modulo.mreLoadModule("br/com/easyShop/telas/cadastros/AbaCadastroClientePessoaFisica.swf");
}

private function lidaClickadoPessoaJuridica(event:Event):void{
	painelLogin.setVisible(false);
	modulo.mreLoadModule("br/com/easyShop/telas/cadastros/AbaCadastroClientePessoaJuridica.swf");
}

private function lidaClickadoPessoaFisica2(event:Event):void{
	painelLogout.setVisible(false);
	modulo.mreLoadModule("br/com/easyShop/telas/cadastros/AbaCadastroClientePessoaFisica.swf");
}

private function lidaClickadoPessoaJuridica2(event:Event):void{
	painelLogout.setVisible(false);
	modulo.mreLoadModule("br/com/easyShop/telas/cadastros/AbaCadastroClientePessoaJuridica.swf");
}

public function verificarLogin(result:ResultJava):void
{
	try		
	{
		if(result.item != null){
			var cliente:Cliente = new Cliente();
			cliente = ((Cliente) (result.item));
			MRemoteObject.get("UsuarioService.getUsuarioId",[cliente.pessoa.pkPessoa], verificarLogin2);
			clienteGlobal = cliente;
		}
		else{
			Alerta.abrir("Cliente ou Senha inválida", "EasyShop", null, null, null, ImagensUtils.ATENCAO);
		}
	} 
	catch(e:Error)
	{ 
		Alerta.abrir("Cliente ou Senha inválida", "EasyShop", null, null, null, ImagensUtils.ATENCAO);
	}	
}

public function verificarLogin2(result:ResultJava):void
{
	try		
	{	
		var usuario:Usuario = new Usuario();
		usuario = ((Usuario) (result.item));
		
		painelLogin.setVisible(false);
		usuarioGlobal = usuario;
		painelLogin.toolTip = "Logout/Cadastrar";
		btnCarrinho.visible = true;
		btnDesejo.visible = true;
		btnPedido.visible = true;
	} 
	catch(e:Error)
	{ 
		clienteGlobal = null;
		Alerta.abrir("Cliente ou Senha inválida", "EasyShop", null, null, null, ImagensUtils.ATENCAO);
	}	
}

protected function btnDesejo_clickHandler(event:MouseEvent):void
{
	meuDesejo = new AbaMeuDesejo();
	meuDesejo.showCloseButton=true;
	meuDesejo.setVisible(true);
	meuDesejo.addEventListener("clickadoComprar", clickadoComprarDesejo);
	PopUpManager.addPopUp(meuDesejo, this, true);
	
	centralizarTela(meuDesejo);
}

private function clickadoComprarDesejo(event:Event):void{
	meuDesejo.setVisible(false);
	produto = AbaMeuDesejo.produto;
	modulo.mreLoadModule("br/com/easyShop/telas/produtos/AbaDetalhesProduto.swf");
}
