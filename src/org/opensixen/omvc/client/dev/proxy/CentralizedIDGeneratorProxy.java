
package org.opensixen.omvc.client.dev.proxy;

import org.compiere.model.MSysConfig;
import org.opensixen.dev.omvc.interfaces.IRemoteCentralizedIDGenerator;
import org.opensixen.riena.client.proxy.AbstractProxy;

/**
 * 
 * 
 * @author Eloy Gomez
 * Indeos Consultoria http://www.indeos.es
 *
 */
public class CentralizedIDGeneratorProxy extends AbstractProxy<IRemoteCentralizedIDGenerator>{

	private IRemoteCentralizedIDGenerator generator;
	
	private static CentralizedIDGeneratorProxy instance;
	
	public static CentralizedIDGeneratorProxy getInstance()	{
		if (instance == null)	{
			instance = new CentralizedIDGeneratorProxy();
		}
		
		return instance;
	}
	
	
	public CentralizedIDGeneratorProxy() {
		super();
		generator = getService();
	}

	/* (non-Javadoc)
	 * @see org.opensixen.riena.client.proxy.AbstractProxy#getServicePath()
	 */
	@Override
	public String getServicePath() {
		// TODO Auto-generated method stub
		return null;
	}	
	
	
	/**
	 * @return
	 * @see org.opensixen.riena.interfaces.IRienaService#testService()
	 */
	public boolean testService() {
		return generator.testService();
	}


	/**
	 * @param tableName
	 * @return
	 * @see org.opensixen.dev.omvc.interfaces.IRemoteCentralizedIDGenerator#getNextID(java.lang.String)
	 */
	public int getNextID(String tableName) {
		return generator.getNextID(tableName);
	}		
}
