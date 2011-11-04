package br.com.mresolucoes.componentes.mre
{	
	/*-*-*-*-* Imports *-*-*-*-*/	
	import br.com.mresolucoes.imagens.ImagensUtils;
	
	import mx.controls.Image;
	import mx.controls.ProgressBar;
	import mx.controls.Text;
	import mx.core.UIComponent;
	import mx.events.ModuleEvent;
	import mx.modules.IModuleInfo;
	import mx.modules.ModuleManager;
	
	import spark.components.Group;
	import spark.components.Panel;
	
	/**
	 * Classe que representa um componente que contem um modulo loader, 
	 * barra de progrecao e um painel de erro que e apresentado quando ha alguma 
	 * falha no carregamento do modulo
	 */
	[IconFile("br/com/mresolucoes/icones/ModuleLoader.png")]
	public class MModulo extends Group
	{
		/*-*-*-*-* Variaveis e Objetos Privados *-*-*-*-*/
		[Bindable]
		private var _mreProgressBar:ProgressBar = null;
		private var _mreModule:IModuleInfo = null;
		private var _mreChild:UIComponent = null;
		private var erro:Panel = null;
		
		[Bindable]
		private var _mreMensagem:String = "Falha carregando o módulo.\nTente novamente, caso o problema persista entre em contato com o administrador.";
		[Bindable]
		private var _mreImagem:String = null;
		[Bindable]
		private var _mreUrl:String = null;
		[Bindable]
		private var _mreFuncaoPronto:Function = null;
		
		private var nomeModulo:String = null;
		
		/*-*-*-*-* Construtores *-*-*-*-*/
		public function MModulo()
		{
			this.width = 300;
			this.height = 70;
			
			_mreProgressBar = new ProgressBar();
			_mreProgressBar.left = 25;
			_mreProgressBar.right = 25;
			_mreProgressBar.verticalCenter = 0;
			_mreProgressBar.labelPlacement="top";
			_mreProgressBar.visible = false;
			this.addElement(_mreProgressBar);
		}
				
		/*-*-*-*-* Metodos Publicos *-*-*-*-*/
		/**
		 * Método responsável por carregar um módulo 
		 * @param url - string que contem o endereco do modulo a ser carregado 
		 */
		public function mreLoadModule(url:String="", funcaoPronto:Function=null):void
		{
			if(url!="")
			{
				_mreUrl = url;
			}
			nomeModulo = _mreUrl;
			
			if(_mreUrl == null)
			{
				return;
			}
			else if (_mreModule != null)
			{
				mreUnloadModule();
			}
			
			if(erro != null)
			{
				this.removeElement(erro);
				erro = null;
			}
			
			if(funcaoPronto!=null)
			{
				_mreFuncaoPronto = funcaoPronto;
			}
			
			_mreModule = ModuleManager.getModule(_mreUrl);
	
			_mreProgressBar.source = _mreModule;
			_mreProgressBar.visible = true;
			
			_mreModule.addEventListener(ModuleEvent.READY, moduleReadyHandler);
			_mreModule.addEventListener(ModuleEvent.ERROR, moduleErrorHandler);
			_mreModule.load();
		}
		
		/**
		 * Metodo responsavel por descarregar o modulo
		 */
		public function mreUnloadModule():void
		{
			if (mreChild!=null)
			{
				this.removeElement(mreChild);
				mreChild = null;
			}
			
			if(erro != null)
			{
				this.removeElement(erro);
				erro = null;
			}
			
			if (_mreModule!=null)
			{
				_mreModule.removeEventListener(ModuleEvent.READY, moduleReadyHandler);
				_mreModule.removeEventListener(ModuleEvent.ERROR, moduleErrorHandler);
				
				//_module.unload();
				_mreModule = null;
				_mreProgressBar.setProgress(0, 100);
				_mreProgressBar.visible = false;
			}
		}
		
		/**
		 * Metodo chamado logo apos que um modulo e carregado
		 * @param e - evento para modulos
		 */
		private function moduleReadyHandler(e:ModuleEvent):void
		{
			mreChild = _mreModule.factory.create() as UIComponent;
			
			if (mreChild!=null)
			{
				this.addElement(mreChild);
				_mreProgressBar.setProgress(0, 100);
				_mreProgressBar.visible = false;
			} 
			
			if(_mreFuncaoPronto!=null)
			{
				if(_mreFuncaoPronto.length>0) { _mreFuncaoPronto(this); }
				else { _mreFuncaoPronto(); }
			}
		}
		
		/**
		 * Metodo chamado quando ocorre algum problema no carregamento do modulo
		 * @param e - evento para modulos
		 */
		private function moduleErrorHandler(e:ModuleEvent):void
		{
			this.mreUnloadModule();
			_mreProgressBar.setProgress(0, 100);
			_mreProgressBar.visible = false;
			
			erro = new Panel();
			erro.width = 250;
			erro.height = 170;
			erro.horizontalCenter = 0;
			erro.verticalCenter = 0;
			erro.title = "Carregando: " + nomeModulo.substring(nomeModulo.lastIndexOf('/')+1, nomeModulo.lastIndexOf('.'));
			erro.controlBarContent = [];
			erro.controlBarVisible = true;
			
			var icone:Image = new Image();
			icone.width = 80;
			icone.height = 80;
			icone.left = 10;
			icone.verticalCenter = 0;
			icone.setStyle("verticalAlign", "middle"); 
			icone.setStyle("horizontalAlign", "center");
			icone.scaleContent = false; 
			icone.autoLoad = true;
			icone.maintainAspectRatio = true;
			icone.source = (_mreImagem==null ? ImagensUtils.ERRO_MODULO : _mreImagem);
			erro.addElement(icone);
			
			var texto:Text = new Text();
			texto.width = 140;
			texto.right = 10;
			texto.verticalCenter = 0;
			texto.setStyle("verticalAlign", "middle");
			texto.setStyle("textAlign", "center");
			texto.text = _mreMensagem;
			texto.maintainProjectionCenter = true;
			erro.addElement(texto);
			
			this.addElement(erro);
		}
		
		/*-*-*-*-* Metodos Gets e Sets *-*-*-*-*/
		public function get mreMensagem():String { return _mreMensagem; }
		public function set mreMensagem(msg:String):void { _mreMensagem = msg; }
		
		public function get mreImagem():String { return _mreImagem; }
		public function set mreImagem(path:String):void { _mreImagem = path; }
		
		public function get mreProgressBar():ProgressBar { return _mreProgressBar; }
		public function set mreProgressBar(barra:ProgressBar):void { _mreProgressBar = barra; }
		
		public function get mreUrl():String { return _mreUrl; }
		public function set mreUrl(url:String):void { _mreUrl = url; mreLoadModule(_mreUrl); }
		
		public function get mreModulo():IModuleInfo { return _mreModule; }
		public function set mreModulo(mod:IModuleInfo):void { _mreModule = mod; }

		public function get mreChild():UIComponent { return _mreChild; }
		public function set mreChild(child:UIComponent):void { _mreChild = child; }

	}
}