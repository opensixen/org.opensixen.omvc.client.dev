package org.opensixen.omvc.client.dev;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PrivilegedAction;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginException;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.compiere.apps.AEnv;
import org.compiere.util.Env;
import org.opensixen.omvc.client.proxy.RemoteConsoleProxy;
import org.opensixen.osgi.AbstractMenuAction;
import org.opensixen.osgi.interfaces.IMenuAction;

public class CommitMenuAction  extends AbstractMenuAction implements IMenuAction,ActionListener {
	JMenuItem mExportMetadata;
	
	@Override
	public void addAction(JMenuBar menuBar) {
		if (Env.getAD_Client_ID(Env.getCtx()) != 0)	{
			return;
		}
		JMenu menu = getMenu(menuBar, "Tools");
		
		if (menu == null)	{
			return;
		}
		
		mExportMetadata = AEnv.addMenuItem("Commit Metadata", "Commit Metadata", null, menu, this);		
	}

	public void actionPerformed(ActionEvent e) {
		// Run app in secure context
		try {
			Subject.doAs(RemoteConsoleProxy.getInstance().getLoginContext().getSubject(),
					getRunAction());
		} catch (LoginException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}		
		
	}
	
	private PrivilegedAction getRunAction() {
		return new PrivilegedAction() {
			public Object run() {
				CommitDialog dialog = new CommitDialog(null);
				dialog.setVisible(true);
				return null;
			}
		};
	}
	

}
