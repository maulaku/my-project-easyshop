import br.com.easyShop.comunicacao.MRemoteObject;
import br.com.easyShop.comunicacao.ResultJava;
import br.com.easyShop.model.Cidade;
import br.com.easyShop.model.Cliente;
import br.com.easyShop.model.Contato;
import br.com.easyShop.model.Endereco;
import br.com.easyShop.model.Estado;
import br.com.easyShop.model.Pais;
import br.com.easyShop.model.Pessoa;
import br.com.easyShop.model.PessoaFisica;
import br.com.mresolucoes.componentes.mre.Alerta;
import br.com.mresolucoes.componentes.mre.MBotao;

import flash.events.Event;
import flash.events.MouseEvent;
import flash.net.FileFilter;
import flash.net.FileReference;

import mx.collections.ArrayCollection;
import mx.collections.IList;

private var obj_FileReference:FileReference;
private var myFilter:FileFilter = new FileFilter("Imagens (*.jpg; *.jpeg; *.gif; *.png;","*.jpg; *.jpeg; *.gif; *.png;");

public function construtor():void
{
	MRemoteObject.get("PaisService.getTodosPaises", null, preencherPais);
	
	var arr:ArrayCollection = new ArrayCollection();
	arr.addItem("Masculino");
	arr.addItem("Feminino");
	sexo.dataProvider = arr;
}

private function abreJanela(): void{
	obj_FileReference = new FileReference();
	obj_FileReference.browse([myFilter]);
	obj_FileReference.addEventListener(Event.SELECT,carregarFoto);
	obj_FileReference.addEventListener(Event.COMPLETE,mostraFoto);
}

private function carregarFoto(e:Event):void{
	obj_FileReference.load();
}

private function mostraFoto(e:Event):void{
	//lblImagem.loaderInfo(obj_FileReference.data);
}

protected function Salvar():void
{
//	var cliente:Cliente = new Cliente();
//	var endereco:Endereco = new Endereco();
//	var contato:Contato = new Contato();
	var pessoaFisica:PessoaFisica = new PessoaFisica();
//	var pessoa:Pessoa = new Pessoa();
	
	pessoaFisica.apelido = apelido.text;
	pessoaFisica.nome = nome.text;
	pessoaFisica.sexo = sexo.selectedItem;
	pessoaFisica.cpf = cpf.text;
	pessoaFisica.rg = rg.text;
	pessoaFisica.dataNascimento = ((Date) (dataDeNascimento.data));
	
	var arr:Array = new Array();
	arr.push(pessoaFisica);
	MRemoteObject.get("PessoaFisica.inserirPessoaFisica", arr);
	
}

private function preencherPais(result:ResultJava):void{
	cboPais.dataProvider = result.lista;
	
	if(result.lista != null){
		var arr:Array = new Array();
		arr.push((Pais) (result.lista.getItemAt(0)));
		MRemoteObject.get("EstadoService.getTodosEstadosPais", arr, preencherEstado);
	}	
}

private function labelPais(item:Object):String {
	var cat:Pais = new Pais();
	cat = ((Pais) (item));
	return cat.nome;
}

private function preencherEstado(result:ResultJava):void{
	cboEstado.dataProvider = result.lista;
	
	if(result.lista != null){
		var arr:Array = new Array();
		arr.push((Estado) (result.lista.getItemAt(0)));
		MRemoteObject.get("CidadeService.getTodasCidadesEstado", arr, preencherCidades);
	}	
}

private function labelEstado(item:Object):String {
	var cat:Estado = new Estado();
	cat = ((Estado) (item));
	return cat.nome;
}

private function preencherCidades(result:ResultJava):void{
	cboCidade.dataProvider = result.lista;
}

private function labelCidade(item:Object):String {
	var cat:Cidade = new Cidade();
	cat = ((Cidade) (item));
	return cat.nome;
}