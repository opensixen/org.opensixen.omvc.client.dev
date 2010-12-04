
package org.opensixen.omvc.client.dev.proxy;

import java.net.URL;
import java.security.PrivilegedAction;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginException;

import org.compiere.apps.AEnv;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.opensixen.dev.omvc.interfaces.IRemoteCentralizedIDGenerator;
import org.opensixen.dev.omvc.swing.UpdateDialog;
import org.opensixen.omvc.client.Updater;
import org.opensixen.omvc.client.dev.Activator;
import org.opensixen.omvc.client.proxy.RemoteConsoleProxy;
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
	
	/* stuff for the login configuration */
	private static final String JAAS_CONFIG_FILE = "data/jaas_config.txt"; //$NON-NLS-1$
	private static final String CONFIG_PREF = "loginConfiguration"; //$NON-NLS-1$
	private static final String CONFIG_DEFAULT = "omvc"; //$NON-NLS-1$
	
	
	public static CentralizedIDGeneratorProxy getInstance()	{
		if (instance == null)	{
			instance = new CentralizedIDGeneratorProxy();
		}
		
		return instance;
	}
	
	
	private CentralizedIDGeneratorProxy() {
		super();
	}
	
	

	/* (non-Javadoc)
	 * @see org.opensixen.riena.client.proxy.AbstractProxy#afterRegister()
	 */
	@Override
	protected void afterRegister() {
		generator = getService();
	}


	/* (non-Javadoc)
	 * @see org.opensixen.riena.client.proxy.AbstractProxy#getServicePath()
	 */
	@Override
	public String getServicePath() {
		// TODO Auto-generated method stub
		return IRemoteCentralizedIDGenerator.path;
	}	
		
	/* (non-Javadoc)
	 * @see org.opensixen.riena.client.proxy.AbstractProxy#needAuth()
	 */
	@Override
	public boolean needAuth() {
		// TODO Auto-generated method stub
		return true;
	}
	

	/* (non-Javadoc)
	 * @see org.opensixen.riena.client.proxy.AbstractProxy#getJAASConfigurationName()
	 */
	@Override
	public String getJAASConfigurationName() {
		// TODO Auto-generated method stub
		return new DefaultScope().getNode(Activator.PLUGIN_ID).get(CONFIG_PREF, CONFIG_DEFAULT);
	}

	/* (non-Javadoc)
	 * @see org.opensixen.riena.client.proxy.AbstractProxy#getJAASConfigFile()
	 */
	@Override
	public URL getJAASConfigFile() {
		// TODO Auto-generated method stub
		return Activator.getContext().getBundle().getEntry(JAAS_CONFIG_FILE);
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
		try {
			return Subject.doAs(getLoginContext().getSubject(),
					getRunAction(tableName));
		} catch (LoginException e) {
			throw new RuntimeException("Login incorrecto.", e);
		}
	}
	
	private PrivilegedAction getRunAction(final String tableName) {
		return new PrivilegedAction() {
			public Object run() {
				return generator.getNextID(tableName);
			}
		};
	}

	
}
