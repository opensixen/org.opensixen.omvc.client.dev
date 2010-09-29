package org.opensixen.omvc.client.dev;

import org.opensixen.dev.omvc.OSXServiceConnectionHandler;
import org.opensixen.omvc.client.dev.proxy.CentralizedIDGeneratorProxy;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator implements BundleActivator  {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.opensixen.omvc.client.dev"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	
	/**
	 * The constructor
	 */
	public Activator() {

	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {		
		plugin = this;
		
		OSXServiceConnectionHandler handler = new OSXServiceConnectionHandler();
		CentralizedIDGeneratorProxy.setServiceConnectionHandler(handler);		
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

}
