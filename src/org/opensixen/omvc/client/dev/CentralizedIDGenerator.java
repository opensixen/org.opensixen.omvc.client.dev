package org.opensixen.omvc.client.dev;

import org.opensixen.omvc.client.dev.proxy.CentralizedIDGeneratorProxy;
import org.opensixen.osgi.interfaces.ICentralizedIDGenerator;


public class CentralizedIDGenerator implements ICentralizedIDGenerator {
		
	private static CentralizedIDGeneratorProxy generator;
	
	@Override
	public int getNextID(String tableName, String description) {
		if (generator == null)	{
			generator = CentralizedIDGeneratorProxy.getInstance();
		}
		
		return generator.getNextID(tableName);
	}

}
