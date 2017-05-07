package RPC;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.VersionedProtocol;

public class MyClient {
	public static void main(String[] args) throws IOException {
//		 MyBizable proBiz =  (MyBizable)RPC.waitForProxy(//ion, 
//				 MyBizable.class,
//				 MyBizable.VERSION ,
//				new InetSocketAddress(MyServer.ADDRESS,MyServer.PORTS), 
//				new Configuration());
//		 final String result = proBiz.hello("csm");
//		 System.out.println("result is " + result);
//		 RPC.stopProxy(proBiz);
		 
		 Configuration conf = new Configuration();
		 MyBizable proxy = RPC.getProxy(MyBizable.class, MyBizable.versionID,
					new InetSocketAddress(MyServer.ADDRESS,MyServer.PORTS), conf);
			String result = proxy.hello("world");
	}
}
