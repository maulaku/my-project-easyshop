import br.com.easyShop.aplicacao.AccordionItem;
import br.com.easyShop.aplicacao.MainEasyShop;
import br.com.easyShop.componentes.modulo.ModuloItem;
import br.com.easyShop.comunicacao.MRemoteObject;
import br.com.easyShop.comunicacao.ResultJava;
import br.com.easyShop.model.Categoria;
import br.com.easyShop.model.Cliente;
import br.com.easyShop.model.Pessoa;
import br.com.easyShop.model.PessoaFisica;
import br.com.easyShop.model.Produto;
import br.com.easyShop.telas.Login;
import br.com.easyShop.telas.desejos.AbaMeuDesejo;
//import br.com.easyShop.telas.produtos.AbaDetalhesProduto;
//<modulo:PainelModulo id="grPainelModulos" x="240" y="186" visible="false" showEffect="{anAparecer}" hideEffect="{anSumir}"  />
import br.com.easyShop.telas.produtos.MeuCarrinho;
import br.com.easyShop.utils.Constantes;
import br.com.mresolucoes.componentes.mre.Alerta;
import br.com.mresolucoes.componentes.mre.MModulo;
import br.com.mresolucoes.imagens.ImagensUtils;
import br.com.mresolucoes.utils.NumberUtil;

import flash.events.Event;
import flash.events.MouseEvent;

import mx.containers.VBox;
import mx.controls.Alert;
import mx.core.UIComponent;
import mx.managers.PopUpManager;

import spark.components.Button;
import spark.components.Label;
import mx.utils.ObjectUtil;

private var painel:Login;
private var meuCarrinho:MeuCarrinho;
private var meuDesejo:AbaMeuDesejo;

private static var clienteGlobal:Cliente; //Cliente Global da Aplicação. Ele é setado pelo Login.
/**
 * Inicializa os componentes e objetos
 */ 

public static function getClienteGlobal():Cliente{
	return clienteGlobal;
}

public function construtor():void
{
	cbBusca.mreServicePesquisa = "ProdutoService.getProdutosNome";
	MRemoteObject.get("CategoriaService.getTodasCategoriasPai", null, resultCategoria);
//	MRemoteObject.get("ProdutoService.getProdutosPromocao", null, resultProduto);
}

//public function resultProduto(result:ResultJava):void
//{
//	try		
//	{		
//		if(result != null)
//		{							
//			var produto:Produto = new Produto();
//			
//			for(var i:int=0;i<result.lista.length;i++)
//			{
//				produto = result.lista.getItemAt(i) as Produto;  
//				var item:ModuloItem = new ModuloItem();
//				
//				item.nome = produto.nome;
//				item.preco = NumberUtil.toString(produto.preco, 2);
//				item.funcaoBotao = carregarModuloProduto;
//				item.produto = produto;
//				item.imagemSource = Constantes.instance.ENDERECO_IMAGEM_PRODUTO+"1.png";
//				
//				grPainelModulos.addModulo(item);
//			}
//			grPainelModulos.visible = true;
//		}
//		else
//		{ 
//			Alerta.abrir(result.lista.length > 0 ? result.lista.getItemAt(0) as String : "Ops, Erro ao carregar categorias", "EasyShop", null, null, null, ImagensUtils.INFO);
//		}
//		
//	} 
//	catch(e:Error)
//	{ 
//		Alerta.abrir("Ops, Ocorreu um erro ao carregar categorias", "EasyShop", null, null, null, ImagensUtils.INFO);
//	}
//	
//}

public function resultCategoria(result:ResultJava):void
{
	try		
	{		
		if(result.item != null && (result.item as Boolean)==true)
		{				
			cbCategorias.mreDataProvider = result.lista;
			var categoria:Categoria = new Categoria();
			var categoria2:Categoria = new Categoria();
			var i:int;
			var arr:Array = new Array();
			
			
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
				acord.image= "@Embed('../imagens/aplicacao/fundo.png')";
				acord.styleName = "gradientHeader";
				//acord.addEventListener(MouseEvent.MOUSE_OVER,fakeMouseClick);
				//				var grid:VBox = new VBox();
				//				acord.addElement(grid);
				accordion.addElement(acord);
				
				var parametros:Array = new Array();
				parametros.push(categoria);
				MRemoteObject.get("CategoriaService.getTodasCategoriasSub", parametros, resultSubCategoria);	
				
				arr.push(categoria);
			}
			//accordion.addEventListener(MouseEvent.MOUSE_OVER,fakeMouseClick);
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

private function fakeMouseClick(event:MouseEvent):void {
	var clickEvent:MouseEvent = new MouseEvent(MouseEvent.CLICK, true, false, event.localX, event.localY);
	//dispatchEvent(clickEvent);
}

/* Listeners Modulos */
//public function carregarModuloProduto(object:Object):void
//{
//	produtoAux = object as Produto;
//	grPainelModulos.visible=false;
//	modulo.mreLoadModule("br/com/easyShop/telas/produtos/AbaDetalhesProduto.swf", teste);
//}
//
//
//public function teste(mod:MModulo=null):void
//{
//	for (var i:int=0; i<mod.numChildren; i++)
//	{
//		if (mod.getChildAt(i) is AbaDetalhesProduto)
//		{
//			//			(mod.getChildAt(i) as AbaDetalhesProduto).carregarProduto(produtoAux);	
//			
//			//painel navegação
//			//passar a chamada pra dentro do componente
//		}
//	}
//}

public function resultSubCategoria(result:ResultJava):void
{
	try		
	{		
		if(result.item != null && (result.item as Boolean)==true)
		{			
			var i:int;
			var categoria:Categoria = new Categoria();
			var grid:VBox = new VBox();
			var novo:AccordionItem = new AccordionItem();
			var array:Array = new Array();
			for(i=0;i<result.lista.length;i++){
				categoria = ((Categoria) (result.lista[i]));  
				novo = ((AccordionItem) (accordion.getElementAt((categoria.subCategoria.pkCategoria)-1)));
				var label:Label = new Label();
				label.text = categoria.nome;
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
public function mouseOver(evt:MouseEvent):void{
	aumentar.play([evt.currentTarget]);
}

public function mouseOut(evt:MouseEvent):void{
	diminuir.play([evt.currentTarget]);
}

public function lfProduto(item:Object=null, colunm:Object=null):String
{
	return item != null ? (item as Produto).nome : "null";
}

protected function btTeste_clickHandler():void
{
	modulo.mreLoadModule("br/com/easyShop/telas/produtos/AbaDetalhesProduto.swf");
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

protected function btnEntrar_clickHandler(centrado:Boolean):void
{
	painel = new Login();
	painel.showCloseButton=true;
	painel.setVisible(true);
	painel.addEventListener("clickadoLogin", lidaClickadoLogin);
	painel.addEventListener("clickadoPessoaFisica", lidaClickadoPessoaFisica);
	painel.addEventListener("clickadoPessoaJuridica", lidaClickadoPessoaJuridica);
	PopUpManager.addPopUp(painel, this, true);
	
	centralizarTela(painel);
}

protected function btnCarrinho_clickHandler(event:MouseEvent):void
{
	meuCarrinho = new MeuCarrinho();
	meuCarrinho.showCloseButton=true;
	meuCarrinho.setVisible(true);
	PopUpManager.addPopUp(meuCarrinho, this, true);
	
	centralizarTela(meuCarrinho);
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
//public function lfProduto(item:Object=null, colunm:Object=null):String
//{
//	try
//	{
//		if (item != null && item is Produto)
//		{
//			return (item as Produto).nome;					
//		}
//	}
//	catch(e:Error)
//	{
//		Alerta.abrir("Ops, ocorreu um erro", "Easy Shop", null, null, null, ImagensUtils.INFO);
//	}
//	return "";
//}

private function lidaClickadoLogin(event:Event):void{
	var arr:Array = new Array();
	arr.push(event.currentTarget.txtUsuario.text);
	MRemoteObject.get("ClienteService.getCliente", arr, verificarLogin);
}

private function lidaClickadoPessoaFisica(event:Event):void{
	painel.setVisible(false);
	ScrollBar.setVisible(true);
	modulo.mreLoadModule("br/com/easyShop/telas/cadastros/AbaCadastroClientePessoaFisica.swf");
}

private function lidaClickadoPessoaJuridica(event:Event):void{
	painel.setVisible(false);
	modulo.mreLoadModule("br/com/easyShop/telas/cadastros/AbaCadastroClientePessoaJuridica.swf");
}

public function verificarLogin(result:ResultJava):void
{
	try		
	{	
		var cliente:Cliente = new Cliente();
		var senhaPainel:String;
		var senhaCliente:String;
		cliente = ((Cliente) (result.item));
		senhaPainel = painel.txtSenha.text;
		senhaCliente = cliente.senha;
		Alert.show("Comparação = " + senhaCliente + "==" + senhaCliente);
		if(ObjectUtil.stringCompare(senhaCliente,senhaPainel)){
			painel.setVisible(false);
			Alert.show("2Comparação = " + senhaCliente + "==" + senhaCliente);
			clienteGlobal = cliente;
		}
		else{
			//Alert.show("O botão Login do painel dbConf foi clickado!!\nO texto escrito nos campos\n user: "+event.currentTarget.txtUsuario.text+"\npass: "+event.currentTarget.txtSenha.text+"\n\nCerto ??");
			Alert.show("Cliente ou Senha incorreta!");
		}
	} 
	catch(e:Error)
	{ 
		Alerta.abrir("Ops, Ocorreu um erro ao carregar clientes", "EasyShop", null, null, null, ImagensUtils.INFO);
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