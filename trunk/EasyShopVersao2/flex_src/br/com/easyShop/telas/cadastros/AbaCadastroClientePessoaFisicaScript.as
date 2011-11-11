import br.com.mresolucoes.componentes.mre.Alerta;
import br.com.mresolucoes.componentes.mre.MBotao;

import flash.events.Event;
import flash.net.FileFilter;
import flash.net.FileReference;

private var obj_FileReference:FileReference;
private var myFilter = new FileFilter("Imagens (*.jpg; *.jpeg; *.gif; *.png;","*.jpg; *.jpeg; *.gif; *.png;");

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