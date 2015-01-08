import java.net.MalformedURLException;
import java.net.URL;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.xmlrpc.android.XMLRPCException;


public class Banque {
	public static void main(String[]args)throws MalformedURLException,XMLRPCException{
		String name_base="AndroidOpenErp";
		String pwd_base="xmlrpcandroid";
		URL url_common=new URL("http://localhost:8069/xmlrpc/object");
		XmlRpcClientConfigImpl config_common=new XmlRpcClientConfigImpl(); 
		config_common.setEnabledForExtensions(true);
		config_common.setServerURL(url_common);
		
		XmlRpcClient client_object=new XmlRpcClient();
		
		client_object.setConfig(config_common);
		Object[] params_common=new Object[]{name_base,"admin",pwd_base};
		
		try {
			Object resultat_Connexion = client_object.execute("login",params_common);
			System.out.println("id user:"+resultat_Connexion+"\n");
		} catch (XmlRpcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
