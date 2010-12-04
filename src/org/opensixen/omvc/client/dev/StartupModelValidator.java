/**
 * 
 */
package org.opensixen.omvc.client.dev;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.opensixen.dev.omvc.OSXServiceConnectionHandler;
import org.opensixen.omvc.client.dev.proxy.CentralizedIDGeneratorProxy;
import org.opensixen.osgi.interfaces.IModelValidator;

/**
 * 
 * 
 * @author Eloy Gomez Indeos Consultoria http://www.indeos.es
 * 
 */
public class StartupModelValidator implements IModelValidator {

	private CLogger log = CLogger.getCLogger(getClass());

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.compiere.model.ModelValidator#login(int, int, int)
	 */
	@Override
	public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) {
		try {
			// Register services
			OSXServiceConnectionHandler handler = OSXServiceConnectionHandler.getInstance();
			CentralizedIDGeneratorProxy.getInstance().setServiceConnectionHandler(handler);
		} catch (Exception e) {
			log.warning("No se puede contectar con el servidor OMVC");
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.compiere.model.ModelValidator#initialize(org.compiere.model.
	 * ModelValidationEngine, org.compiere.model.MClient)
	 */
	@Override
	public void initialize(ModelValidationEngine engine, MClient client) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.compiere.model.ModelValidator#getAD_Client_ID()
	 */
	@Override
	public int getAD_Client_ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.compiere.model.ModelValidator#modelChange(org.compiere.model.PO,
	 * int)
	 */
	@Override
	public String modelChange(PO po, int type) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.compiere.model.ModelValidator#docValidate(org.compiere.model.PO,
	 * int)
	 */
	@Override
	public String docValidate(PO po, int timing) {
		// TODO Auto-generated method stub
		return null;
	}

}
