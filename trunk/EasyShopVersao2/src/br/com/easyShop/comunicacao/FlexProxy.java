package br.com.easyShop.comunicacao;

import java.util.Date;

import utils.data.Data;

import flex.messaging.io.BeanProxy;

/**
 * Classe utilizada para fazer a conversao de parametros entre o Flex e o Java
 * Inicializacao: PropertyProxyRegistry.getRegistry().register(Object.class, new FlexProxy()); 
 */
public class FlexProxy extends BeanProxy 
{
	private static final long serialVersionUID = 1L;

	/**
	 * Metodo chamado quando uma parametro deve ser convertido do Java para o Flex
	 * @param instance instancia da classe a ser convertida
	 * @param propertyName nome da propriedade a ser convertida (campo do objeto)
	 * @return valor convertido
	 */
	public Object getValue(Object instance, String propertyName) 
    {
        Class<?> propertyType = getBeanProperty(instance, propertyName).getType();
        Object result = super.getValue(instance, propertyName);

        if((result!=null && result instanceof Double && Double.isNaN((Double)result)) || (result==null && Double.class==propertyType))
        {
        	return new Double(Double.NaN);
        }
        return result;
    }

	/**
	 * Metodo chamado quando uma parametro deve ser convertido do Flex para o Java
	 * @param instance instancia da classe a ser convertida
	 * @param propName nome da propriedade a ser convertida (campo do objeto)
	 * @param value conteudo da propriedade
	 */
    public void setValue(Object instance, String propName, Object value) 
    {
    	if(value!=null && value instanceof Double && Double.isNaN((Double)value))
    	{
    		super.setValue(instance, propName, null);
    	}
    	else if(value!=null && value instanceof Date)
    	{
    		super.setValue(instance, propName, new Data((Date)value));
    	}
    	else
    	{
    		super.setValue(instance, propName, value);
    	}
    }
}