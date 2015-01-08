import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Vector;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.xmlrpc.android.XMLRPCException;


public class Rpc {
	public static void main(String[]args)throws MalformedURLException,XMLRPCException{
		
		//***********************************************connexion au serveur open erp******************************************
		
		String name_base="AndroidOpenErp";
		String pwd_base="xmlrpcandroid";
		URL url_common=new URL("http://localhost:8069/xmlrpc/common");
		XmlRpcClientConfigImpl config_common=new XmlRpcClientConfigImpl();//necessite le jar org-apache-xmlrpc 
		config_common.setEnabledForExtensions(true);
		config_common.setServerURL(url_common);
		XmlRpcClient client_common=new XmlRpcClient();
		client_common.setConfig(config_common);
		Object[] params_common=new Object[]{name_base,"admin",pwd_base};
		Object resultat_Connexion=false;
		try {
			resultat_Connexion = client_common.execute("login",params_common);
			System.out.println("vous etes connecter\n");
		} catch (XmlRpcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//****************************extraire les noms de base donnée**************************************************
		
		URL url_db=new URL("http://localhost:8069/xmlrpc/db");
		XmlRpcClientConfigImpl config_db=new XmlRpcClientConfigImpl();//necessite le jar org-apache-xmlrpc 
		config_db.setEnabledForExtensions(true);
		config_db.setServerURL(url_db);
		XmlRpcClient client_db=new XmlRpcClient();
		client_db.setConfig(config_db);
		
		Vector<Object> params_db=new Vector<Object>();
		
		
		try {
			Object[] resultat_db=(Object[])client_db.execute("list",params_db);
			System.out.println("liste de base de donnée:");
			for(int i=0;i<resultat_db.length;i++)
				System.out.println(resultat_db[i]);
		} catch (XmlRpcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//*****************************gestion de la banque****************************************


			
		
				URL url_object=new URL("http://localhost:8069/xmlrpc/object");
				//connecter a la base de donne 
				XmlRpcClientConfigImpl config_object=new XmlRpcClientConfigImpl();//necessite le jar org-apache-xmlrpc 
				config_object.setEnabledForExtensions(true);
				config_object.setServerURL(url_object);
				XmlRpcClient client_object=new XmlRpcClient();
				client_object.setConfig(config_object);
				
				//id user
				Object id_user=resultat_Connexion;
				
				//************************gestion des comptes financiers****************************
				
				ArrayList<Object> domain_search=new ArrayList<Object>();
				Object[] c1=new Object[]{"code","like","51"};
				domain_search.add(c1);
				Object[] params_search=new Object[]{name_base,id_user,pwd_base,"account.account","search",domain_search};
				Object[] resultat_search=null;
				try {
					resultat_search=(Object[])client_object.execute("execute",params_search);
					for(int i=0;i<resultat_search.length;i++);
					//	System.out.println("id compte trouvé: "+resultat_search[i]);
					
				} catch (XmlRpcException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
		
		//******************************afficher compte financiers***************************************
			
				
				Object[] ids=resultat_search;
				Object[] champs=new Object[]{"code","name","debit","credit","balance"};
				Object[]params_read=new Object[]{name_base,id_user,pwd_base,"account.account","read",ids,champs};
				Object[] resultat_read;
				try {
					resultat_read = (Object[])client_object.execute("execute",params_read);
					for(int i=0;i<resultat_read.length;i++)
						System.out.println(" "+String.valueOf(resultat_read[i]));
					
				} catch (XmlRpcException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				
	}

}
