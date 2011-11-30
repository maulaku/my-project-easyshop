import br.com.easyShop.aplicacao.AccordionItem;
import br.com.easyShop.aplicacao.ImportsMain;
import br.com.easyShop.aplicacao.MainEasyShop;
import br.com.easyShop.componentes.modulo.ProdutoItem;
import br.com.easyShop.comunicacao.MRemoteObject;
import br.com.easyShop.comunicacao.ResultJava;
import br.com.easyShop.model.Categoria;
import br.com.easyShop.model.Cliente;
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
import br.com.easyShop.telas.produtos.MeuCarrinho;
import br.com.easyShop.utils.Constantes;
import br.com.mresolucoes.componentes.mre.Alerta;
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

private var importsMain:ImportsMain = null;
private var nomeCategoriaSelecionada:String;
private var nomePaiCategoriaSelecionada:String;
private var accondeonAtual:String;

private static var clienteGlobal:Cliente; //Cliente Global da Aplicação. Ele é setado pelo Login.
private static var usuarioGlobal:Usuario; //Usuario Global da Aplicação. Ele é setado pelo Login.

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

public function construtor():void
{
	clienteGlobal = null;
	usuarioGlobal = null;
	btnCarrinho.visible = false;
	btnDesejo.visible = false;
	btnPedido.visible = false;
	cbBusca.mreServicePesquisa = "ProdutoService.getProdutosNome";
	MRemoteObject.get("CategoriaService.getTodasCategoriasPai", null, resultCategoria);
	MRemoteObject.get("ProdutoService.getProdutosPromocao", null, resultProduto);
}
 
/* Listerners Java */
public function resultProduto(result:ResultJava):void
{
	try		
	{		
		if(result != null)
		{	
			grPainelModulos.removeAllElements();
			var produto:Produto = new Produto();
			for(var i:int=0;i<result.lista.length;i++)
			{				
				produto = result.lista.getItemAt(i) as Produto;  
				var item:ProdutoItem = new ProdutoItem();
				
				item.nome = produto.nome;
				item.preco = NumberUtil.toString(produto.preco, 2);
				item.modulo = modulo;
				item.produto = produto;
				item.imagemSource = Constantes.instance.ENDERECO_IMAGEM_PRODUTO+NumberUtil.toString(produto.pkProduto)+".jpg";
			
				grPainelModulos.addModulo(item);
			}
			
			grPainelModulos.visible = true;
		}
		else
		{ 
			Alerta.abrir(result.lista.length > 0 ? result.lista.getItemAt(0) as String : "Ops, Erro ao carregar categorias", "EasyShop", null, null, null, ImagensUtils.INFO);
		}
		
	} 
	catch(e:Error)
	{ 
		Alerta.abrir("Ops, Ocorreu um erro ao carregar categorias", "EasyShop", null, null, null, ImagensUtils.INFO);
	}
	
}

public function resultCategoria(result:ResultJava):void
{
	try		
	{		
		if(result != null)
		{	
			accordion.removeAllElements();
			menuDinamico.removeAllElements();
			cbCategorias.mreDataProvider = result.lista;
			var categoria:Categoria = new Categoria();
			var i:int;
			
			for(i=0;i<result.lista.length;i++){
				categoria = ((Categoria) (result.lista[i]));  
				var novoBotao:spark.components.Button = new spark.components.Button();
				novoBotao.height = 40;
				novoBotao.useHandCursor = true;
				novoBotao.addEventListener(MouseEvent.MOUSE_OUT,mouseOut);
				novoBotao.addEventListener(MouseEvent.MOUSE_OVER,mouseOver);
				novoBotao.label = categoria.nome;          
				menuDinamico.addChild(novoBotao);
				
				var acord:AccordionItem = new AccordionItem();
				acord.height = accordion.height;
				acord.width = accordion.width;
				acord.label = categoria.nome;
				acord.image = "@Embed('../imagens/botoes/back.png')";
				acord.styleName = "gradientHeader";
				accordion.addElement(acord);
				accondeonAtual = "" + i;
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
	try		
	{		
		if(result != null)
		{			
			var i:int;
			var categoria:Categoria;
			var grid:VBox = new VBox();
			var novo:AccordionItem;
			var array:Array = new Array();
			
			for(i=0;i<result.lista.length;i++){
				categoria = ((Categoria) (result.lista[i]));
				var verificou:int;
				verificou = parseInt(accondeonAtual);
				Alert.show("cheguei aqui1: " + verificou);
				novo = ((AccordionItem) (accordion.getElementAt(verificou)));
				//Alert.show("cheguei aqui2: "+categoria.nome);
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
		else
		{ 
			Alerta.abrir(result.lista.length > 0 ? result.lista.getItemAt(0) as String : "Ops, Erro ao carregar sub categorias", "EasyShop", null, null, null, ImagensUtils.INFO);
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
//	Alert.show("Categoria Selecionada " + nomeCategoriaSelecionada+ "Categoria Pai: " +nomePaiCategoriaSelecionada);
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
				
				var acord:AccordionItem = new AccordionItem();
				acord.height = accordion.height;
				acord.width = accordion.width;
				acord.label = categoria.nome;
				acord.image = "@Embed('../imagens/botoes/back.png')";
				acord.styleName = "gradientHeader";
				accordion.addElement(acord);
				accondeonAtual = "" + i;
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

protected function btnPedido_clickHandler():void
{
	modulo.mreLoadModule("br/com/easyShop/telas/pedidos/AbaMeusPedidos.swf");
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
		var diferencaAltura:Number = componente.screen.height - componente.height - 900;
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
	btnCarrinho.visible = false;
	btnDesejo.visible = false;
	btnPedido.visible = false;
}

private function lidaClickadoLogin(event:Event):void{
	MRemoteObject.get("ClienteService.getCliente",[event.currentTarget.txtUsuario.text], verificarLogin);
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
		var cliente:Cliente = new Cliente();
		cliente = ((Cliente) (result.item));
		MRemoteObject.get("UsuarioService.getUsuarioId",[cliente.pessoa.pkPessoa], verificarLogin2);
		clienteGlobal = cliente;
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
		var senhaPainel:String;
		var senhaCliente:String;
		senhaPainel = painelLogin.txtSenha.text;
		usuario = ((Usuario) (result.item));
		senhaCliente = usuario.senha;
		if((ObjectUtil.stringCompare(senhaCliente,senhaPainel))==0){
			painelLogin.setVisible(false);
			usuarioGlobal = usuario;
			btnCarrinho.visible = true;
			btnDesejo.visible = true;
			btnPedido.visible = true;
		}
		else{
			clienteGlobal = null;
			Alerta.abrir("Cliente ou Senha inválida", "EasyShop", null, null, null, ImagensUtils.ATENCAO);
		}
	} 
	catch(e:Error)
	{ 
		clienteGlobal = null;
		Alerta.abrir("Cliente ou Senha inválida", "EasyShop", null, null, null, ImagensUtils.ATENCAO);
	}	
}

protected function btnDesejo_clickHandler(event:MouseEvent):void
{
	meuCarrinho = new MeuCarrinho();
	meuDesejo = new AbaMeuDesejo();
	meuDesejo.showCloseButton=true;
	meuDesejo.setVisible(true);
	PopUpManager.addPopUp(meuDesejo, this, true);
	
	centralizarTela(meuDesejo);
}