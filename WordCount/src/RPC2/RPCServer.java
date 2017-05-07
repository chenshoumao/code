package RPC2;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.RPC.Server;

/**
 * DateTime: 2014年12月28日 上午9:47:05
 *
 */
public class RPCServer {
	private static final String HOST = "localhost";
	private static final int PORT = 2181;
	public static void main(String[] args) throws IOException {
		Configuration conf = new Configuration();

		Server server = new RPC.Builder(conf).setProtocol(ClientProtocol.class)
				.setInstance(new ClientProtocolImpl()).setBindAddress(HOST)
				.setNumHandlers(2)
				.setPort(PORT).build();
		server.start();
	}
}