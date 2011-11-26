import br.com.easyShop.aplicacao.MainEasyShop;
import br.com.easyShop.comunicacao.MRemoteObject;
import br.com.easyShop.comunicacao.ResultJava;
import br.com.easyShop.model.CarrinhoProduto;
import br.com.easyShop.model.Endereco;
import br.com.easyShop.model.Produto;
import br.com.easyShop.utils.Constantes;
import br.com.mresolucoes.componentes.mre.Alerta;
import br.com.mresolucoes.imagens.ImagensUtils;

import flash.events.Event;
import flash.events.MouseEvent;
import flash.net.FileFilter;
import flash.net.FileReference;

import flashx.textLayout.formats.Float;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.controls.Image;
import mx.managers.PopUpManager;

[Bindable]
public var dados:ArrayCollection = new ArrayCollection();
private var i:int;
[Bindable]
private static var frete:int;
[Bindable]
private static var total:int;
[Bindable]
private static var enderecoSelecionado:int;
[Bindable]
private static var enderecoEscolhido:Endereco;
[Bindable]
private static var carrinho:ArrayCollection;

public function construtor():void
{
	MRemoteObject.get("CarrinhoProdutoService.getCarrinhoProdutos", [MainEasyShop.getClienteGlobal()], resultCarrinho);
	MRemoteObject.get("EnderecoService.getEnderecosCliente", [MainEasyShop.getClienteGlobal().pessoa], resultEnderecos);
}

public static function getEnderecoSelecionado():int{
	return enderecoSelecionado;
}

public static function getCarrinho():ArrayCollection{
	return carrinho;
}

public static function getEnderecoEscolhido():Endereco{
	return enderecoEscolhido;
}

public static function getValorCarrinho():int{
	return total;
}

public static function getValorFrete():int{
	return frete;
}

public function resultCarrinho(result:ResultJava):void
{
	try		
	{		
		if(result != null)
		{
			var i:int;
			var carrinhoProduto:CarrinhoProduto;
			var temp:Object;
			//			var imagem:Image = new Image();
			//			imagem.source();
			//			imagem.addEventListener(MouseEvent.CLICK,deleteLinha);
			for(i=0;i<result.lista.length;i++)
			{
				carrinhoProduto = new CarrinhoProduto();
				carrinhoProduto = ((CarrinhoProduto) (result.lista[i]));
				temp = new Object();
				temp.campo1 = carrinhoProduto.produto;
				temp.campo1label = carrinhoProduto.produto.nome;
				temp.campo2 = "1";
				temp.campo3 = "R$ " + carrinhoProduto.produto.preco;
				temp.campo4 = "R$ " + carrinhoProduto.produto.preco;
				temp.campo5 = "Remover";
				
				dados.addItem(temp);	
				total = total + carrinhoProduto.produto.preco;
			}
			
			frete = 5 * result.lista.length;  //Por padrão, o calculo do frete é feito por a quantidade de TIPOS DE PRODUTO vezes R$ 5
			lblFrete.text = "R$ " + frete + ",00";
			lblValorCarrinho.text = "R$ " + total + ",00";
			lblValorTotal.text = "R$ " + (frete+total) + ",00";
			
		}
		else
		{ 
			Alerta.abrir(result.lista.length > 0 ? result.lista.getItemAt(0) as String : "Ops, Erro ao carregar carrinho", "EasyShop", null, null, null, ImagensUtils.INFO);
		}
		
	} 
	catch(e:Error)
	{ 
		Alerta.abrir("Ops, Ocorreu um erro ao carregar carrinho", "EasyShop", null, null, null, ImagensUtils.INFO);
	}
}

public function resultEnderecos(result:ResultJava):void
{
	try		
	{		
		if(result != null)
		{
			var endereco:Endereco = new Endereco();
			var arr:ArrayCollection = new ArrayCollection();
			
			for(i=0;i<result.lista.length;i++){
				endereco = ((Endereco) (result.lista[i]));
				arr.addItem(endereco);
			}
			cboEnderecoEntrega.mreDataProvider = arr;
			cboEnderecoEntrega.selectedIndex = 0;
		}
		else
		{ 
			Alerta.abrir(result.lista.length > 0 ? result.lista.getItemAt(0) as String : "Ops, Erro ao carregar enderecos", "EasyShop", null, null, null, ImagensUtils.INFO);
		}
		
	} 
	catch(e:Error)
	{ 
		Alerta.abrir("Ops, Ocorreu um erro ao carregar enderecos", "EasyShop", null, null, null, ImagensUtils.INFO);
	}
}

protected function btnFinalizarCarrinho_clickHandler(event:MouseEvent):void
{
	enderecoSelecionado = cboEnderecoEntrega.selectedIndex;
	enderecoEscolhido = cboEnderecoEntrega.selectedItem;
	carrinho = dados;
	this.dispatchEvent(new Event("clickadoFinalizarCarrinho"));	
}

protected function btnContinuarComprando_clickHandler(event:MouseEvent):void
{
	this.dispatchEvent(new Event("clickadoContinuarComprando"));	
}

private function deleteLinha(linha:int):void {
	
	if(linha>=0 && linha<dados.length) {
		dados.removeItemAt(linha);
		dados.refresh();
	}
	else {
		Alert.show("Selecione a linha primeiro")
	}
}

private function labelEndereco(item:Object):String {
	var endereco:Endereco = new Endereco();
	endereco = ((Endereco) (item));
	
	if((endereco.tipo) == (Constantes.instance.TIPO_RESIDENCIA)){
		return (Constantes.instance.RESIDENCIA);
	}
	if((endereco.tipo) == (Constantes.instance.TIPO_APARTAMENTO)){
		return (Constantes.instance.APARTAMENTO);
	}
	if((endereco.tipo) == (Constantes.instance.TIPO_COMERCIAL)){
		return (Constantes.instance.COMERCIAL);
	}
	return "";
}
