package br.com.easyShop.model
{
	import br.com.easyShop.utils.Constantes;
	
	import mx.collections.ArrayCollection;

	[RemoteClass(alias="br.com.easyShop.model.Marca")]
	public class Marca
	{
		public var pkMarca:Number;
		public var nome:String;
		public var status:int=Constantes.instance.STATUS_ATIVO;
		[ArrayElementType("br.com.easyShop.model.Produto")]
		public var produtos:ArrayCollection = new ArrayCollection();
	}
}