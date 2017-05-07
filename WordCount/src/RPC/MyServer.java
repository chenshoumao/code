package RPC;

import java.io.IOException;

import org.apache.hadoop.HadoopIllegalArgumentException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.RPC.Server;
 
public class MyServer {
	static final String ADDRESS = "localhost";
	static final int PORTS = 12345;
	public static void main(String[] args) throws HadoopIllegalArgumentException, IOException {
		Configuration conf = new Configuration();

		Server server = new RPC.Builder(conf).setProtocol(MyBizable.class)
				.setInstance(new MyBiz()).setBindAddress(ADDRESS)
				.setNumHandlers(2)
				.setPort(PORTS).build();
		server.start();
	}	
}
