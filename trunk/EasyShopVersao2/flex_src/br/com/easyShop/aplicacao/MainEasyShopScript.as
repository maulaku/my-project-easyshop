import br.com.easyShop.componentes.ESBotaoAnimado;
import br.com.easyShop.componentes.MListaBotao;
import br.com.easyShop.comunicacao.MRemoteObject;
import br.com.easyShop.comunicacao.ResultJava;
import br.com.easyShop.model.Categoria;
import br.com.easyShop.model.Cliente;
import br.com.easyShop.model.Produto;
import br.com.easyShop.telas.Login;
import br.com.easyShop.telas.produtos.MeuCarrinho;
import br.com.mresolucoes.componentes.mre.Alerta;
import br.com.mresolucoes.componentes.mre.MBotao;
import br.com.mresolucoes.imagens.ImagensUtils;
import br.com.mresolucoes.renders.tabela.MRGridBotao;

import flash.events.Event;
import flash.events.MouseEvent;
import flash.xml.XMLNode;

import mx.collections.ArrayCollection;
import mx.containers.Panel;
import mx.containers.VBox;
import mx.controls.Alert;
import mx.controls.Button;
import mx.controls.Tree;
import mx.core.UIComponent;
import mx.effects.Effect;
import mx.effects.WipeDown;
import mx.events.ChildExistenceChangedEvent;
import mx.events.ListEvent;
import mx.managers.PopUpManager;
import mx.messaging.management.ObjectName;
import mx.utils.ObjectUtil;
import mx.utils.object_proxy;

import org.hamcrest.object.nullValue;

import spark.components.Button;
import spark.components.NavigatorContent;
import spark.components.gridClasses.GridColumn;

private var painel:Login;
private var meuCarrinho:MeuCarrinho;
/**
 * Inicializa os componentes e objetos
 */ 
public function construtor():void
{
	cbBusca.mreServicePesquisa = "ProdutoService.getProdutosNome";
	MRemoteObject.get("CategoriaService.getTodasCategoriasPai", null, resultCategoria);
	
//	var botao:ESBotaoAnimado;
//	var lista:MListaBotao;
//	
//	lista.x = 20;
//	lista.y = 20;
//	lista.width = 400;
//	
//	botao.label="teste";
//	
//	lista.addBotao(botao);
}

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
				
				var novo:NavigatorContent = new NavigatorContent();
				novo.height = panelLateral.height;
				novo.width = panelLateral.width;
				novo.label = categoria.nome;
				var grid:VBox = new VBox();
				novo.addElement(grid);
				panelLateral.addElement(novo);	
				
				var parametros:Array = new Array();
								
				parametros.push(categoria);
				MRemoteObject.get("CategoriaService.getTodasCategoriasSub", parametros, resultSubCategoria);	
				
				arr.push(categoria);
			}
			myTree.dataProvider = arr;
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

public function resultSubCategoria(result:ResultJava):void
{
	
	try		
	{		
		if(result.item != null && (result.item as Boolean)==true)
		{			
			var i:int;
			var categoria:Categoria = new Categoria();
			var categoria2:Categoria = new Categoria();
			
			var array:Array = new Array();
			for(i=0;i<result.lista.length;i++){
				categoria = ((Categoria) (result.lista[i]));  
				var novo:NavigatorContent = new NavigatorContent();
				var grid:VBox = new VBox();
				novo = ((NavigatorContent) (panelLateral.getElementAt((categoria.subCategoria.pkCategoria)-1)));
				grid = ((VBox) (novo.getElementAt(0)));
				var botao:spark.components.Button = new spark.components.Button();
				botao.label = categoria.nome;
				botao.width = panelLateral.width -2;
				grid.addElement(botao);
			}	
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
	modulo.mreLoadModule("br/com/easyShop/telas/pedidos/AbaMeuPedido.swf");
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

private function lidaClickadoLogin(event:Event):void{
	Alert.show("O botão Login do painel dbConf foi clickado!!\nO texto escrito nos campos\n user: "+event.currentTarget.txtUsuario.text+"\npass: "+event.currentTarget.txtSenha.text+"\n\nCerto ??");
	painel.setVisible(false);
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


private function tree_itemClick(evt:ListEvent):void {
	var item:Categoria = ((Categoria) (evt.currentTarget.selectedItem));
	if (myTree.dataDescriptor.isBranch(item)) {
		myTree.selectedItem = null;
	}
	Alert.show("Nome do objeto: " + item.nome);
}

private function tree_labelFunc(item:Object):String {
	var cat:Categoria = new Categoria();
	cat = ((Categoria) (item));
	return cat.nome;
}