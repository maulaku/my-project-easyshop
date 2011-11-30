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
import br.com.easyShop.model.Usuario;
import br.com.easyShop.utils.Constantes;
import br.com.mresolucoes.componentes.mre.Alerta;
import br.com.mresolucoes.componentes.mre.MBotao;
import br.com.mresolucoes.imagens.ImagensUtils;

import flash.events.Event;
import flash.events.MouseEvent;
import flash.net.FileFilter;
import flash.net.FileReference;

import mx.charts.chartClasses.DataDescription;
import mx.collections.ArrayCollection;
import mx.collections.ArrayList;
import mx.collections.IList;
import mx.controls.Alert;

private var obj_FileReference:FileReference;
private var myFilter:FileFilter = new FileFilter("Imagens (*.jpg; *.jpeg; *.gif; *.png;","*.jpg; *.jpeg; *.gif; *.png;");

[Bindable]
public var dados:ArrayCollection = new ArrayCollection();
[Bindable]
public var dadosEndereco:ArrayCollection = new ArrayCollection();

public var tiposContato:ArrayCollection = new ArrayCollection([
	{nome:"Telefone", tipo:Constantes.instance.TIPO_CONTATO_TELEFONE},
	{nome:"Celular", tipo:Constantes.instance.TIPO_CONTATO_CELULAR},
	{nome:"Fax", tipo:Constantes.instance.TIPO_CONTATO_FAX},
	{nome:"Email", tipo:Constantes.instance.TIPO_CONTATO_EMAIL}]);

public var tiposEndereco:ArrayCollection = new ArrayCollection([
	{nome:Constantes.instance.RESIDENCIA, tipo:Constantes.instance.TIPO_RESIDENCIA},
	{nome:Constantes.instance.APARTAMENTO, tipo:Constantes.instance.TIPO_APARTAMENTO},
	{nome:Constantes.instance.COMERCIAL, tipo:Constantes.instance.TIPO_COMERCIAL}]);


public function construtor():void
{
	MRemoteObject.get("PaisService.getTodosPaises", null, preencherPais);

	cboContato.mreDataProvider = tiposContato;
	cboTipo.mreDataProvider = tiposEndereco;

	cboContato.selectedIndex = 0;
	cboTipo.selectedIndex = 0;
	preencherSexo();
}

private function preencherSexo():void{
	var arr:ArrayCollection = new ArrayCollection();
	arr.addItem("Masculino");
	arr.addItem("Feminino");
	sexo.dataProvider = arr;
	sexo.selectedIndex = 0;
}

private function preencherEndereco():void{
	var arr:ArrayCollection = new ArrayCollection();
	arr.addItem(Constantes.instance.RESIDENCIA);
	arr.addItem(Constantes.instance.APARTAMENTO);
	arr.addItem(Constantes.instance.COMERCIAL);
	cboTipo.dataProvider = arr;
}

private function preencherContato():void{
	var arr:ArrayCollection = new ArrayCollection();
	arr.addItem(Constantes.instance.CONTATO_TELEFONE);
	arr.addItem(Constantes.instance.CONTATO_CELULAR);
	arr.addItem(Constantes.instance.CONTATO_EMAIL);
	arr.addItem(Constantes.instance.CONTATO_FAX);
	cboContato.dataProvider = arr;
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

private function indexEndereco(result:int):int{
	var index:int;
	index = result;
	return index;
}

private function indexTipoEndereco(tipo:String):void{
	
	var arr:Array = new Array();
	arr.push(tipo);
	
	MRemoteObject.get("TipoEndereco.getIndexTipoEndereco", arr, indexEndereco);
}

protected function Salvar():void
{
	var cliente:Cliente = new Cliente();
	var pessoaFisica:PessoaFisica = new PessoaFisica();
	var pessoa:Pessoa = new Pessoa();
	var usuario:Usuario = new Usuario();
	var i:int;
	
	pessoaFisica.apelido = apelido.text;
	pessoaFisica.nome = nome.text;
	pessoaFisica.sexo = sexo.selectedItem;
	pessoaFisica.cpf = cpf.mreTextoSemMascara;
	pessoaFisica.rg = rg.mreTextoSemMascara;
	pessoaFisica.dataNascimento = dataDeNascimento.selectedDate;
	pessoaFisica.status = Constantes.instance.STATUS_ATIVO;
	
	pessoa.status = Constantes.instance.STATUS_ATIVO;
	pessoa.pessoaFisica = pessoaFisica;
	
	for(i=0;i<dadosEndereco.length;i++)
	{
		var endereco:Endereco = new Endereco();
		var obj:Object = new Object();
		obj = dadosEndereco[i];
		
		endereco.bairro = obj.campo3;
		endereco.cep = obj.campo5;
		endereco.cidade = obj.campo7;
		endereco.complemento = obj.campo6;
		endereco.logradouro = obj.campo2;
		endereco.numero = obj.campo4;
		endereco.pessoa = pessoa;
		endereco.tipo = obj.campo1.tipo;
		endereco.status = Constantes.instance.STATUS_ATIVO;
		
		pessoa.enderecos.addItem(endereco);
	}
	
	
	for(i =0; i < dados.length; i++){
		var contato:Contato = new Contato();
		var obj2:Object = new Object();
		obj2 = dados[i];
		
		contato.contato = obj2.campo2;
		contato.tipo = obj2.campo1.tipo;
		contato.pessoa = pessoa;
		contato.status = Constantes.instance.STATUS_ATIVO;
		
		pessoa.contatos.addItem(contato);
	}
	
	usuario.login = login.text;
	usuario.senha = senha.text;
	usuario.pessoa = pessoa;
	usuario.status = Constantes.instance.STATUS_ATIVO;
	
	pessoa.usuarios.addItem(usuario);
	
	cliente.pessoa = pessoa;
	cliente.status = Constantes.instance.STATUS_ATIVO;
	
	MRemoteObject.get("ClienteService.salvarCliente",[cliente],confirmarCadastro);
}

public function confirmarCadastro(result:ResultJava):void
{
	try		
	{
			Alerta.abrir("Cliente cadastrado com sucesso!", "EasyShop", null, null, null, ImagensUtils.FELIZ);	
	} 
	catch(e:Error)
	{ 
		Alerta.abrir("Ops, Ocorreu um erro ao cadastrar o Cliente!", "EasyShop", null, null, null, ImagensUtils.INFO);
	}	
}

private function preencherPais(result:ResultJava):void{
	cboPais.dataProvider = result.lista;
}

private function listarCidades(event:Event):void{

	var item:Object = cboEstado.selectedItem;

	var arr:Array = new Array();
	arr.push((Estado) (item));
	MRemoteObject.get("CidadeService.getTodasCidades", arr, preencherCidades);
}

private function listarEstados(event:Event):void{
	
	var item:Object = cboPais.selectedItem;

	var arr:Array = new Array();
	arr.push((Pais) (item));
	MRemoteObject.get("EstadoService.getTodosEstados", arr, preencherEstado);
}

private function inserirContatoTabela():void{
	
	     var temp:Object;

		 temp=new Object();
		 temp.campo1=cboContato.selectedItem;
		 temp.campo1label = labelContato(cboContato.selectedItem);
		 temp.campo2=txtContato.text;

		 dados.addItem(temp);
		 txtContato.text="";
		 
		 cboContato.removeChild(cboContato.selectedItem);
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

private function inserirEnderecoTabela():void{
	var temp:Object = new Object();
	var label:Object = new Object();
	
	temp.campo1 = cboTipo.selectedItem;
	label = ((Object) (cboTipo.selectedItem));
	temp.campo1label = label.nome;
	temp.campo2 = logradouro.text;
	temp.campo3 = bairro.text;
	temp.campo4 = numero.text;
	temp.campo5 = cep.mreTextoSemMascara;
	temp.campo6 = complemento.text;
	temp.campo7 = cboCidade.selectedItem;
	temp.campo7label = labelCidade(cboCidade.selectedItem);
	temp.campo8 = cboEstado.selectedItem;
	temp.campo8label = labelEstado(cboEstado.selectedItem);
	temp.campo9 = cboPais.selectedItem;
	temp.campo9label = labelPais(cboPais.selectedItem);
	
	dadosEndereco.addItem(temp);
	txtContato.text = "";
	logradouro.text = "";
	bairro.text = "";
	numero.text = "";
	cep.text = "";
	complemento.text = "";
	
	cboTipo.removeChild(cboTipo.selectedItem);
}

private function deleteEnderecoLinha(linha:int):void {
	
	if(linha>=0 && linha<dadosEndereco.length) {
		dadosEndereco.removeItemAt(linha);
		dadosEndereco.refresh();
	}
	else {
		Alert.show("Selecione a linha primeiro")
	}
}

private function labelPais(item:Object):String {
	var cat:Pais = new Pais();
	cat = ((Pais) (item));
	return cat.nome;
}

private function preencherEstado(result:ResultJava):void{
	cboEstado.dataProvider = result.lista;
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

private function labelTipo(item:Object):String {
	return item.nome;
}

private function labelContato(item:Object):String {
	return item.nome;
}