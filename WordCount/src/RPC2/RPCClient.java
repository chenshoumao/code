package RPC2;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

/**
 * DateTime: 2014年12月28日 上午9:52:19
 *
 */
public class RPCClient {
	private static final String HOST = "localhost";
	private static final int PORT = 2181;

	public static void main(String[] args) throws IOException {
		Configuration conf = new Configuration();
		ClientProtocol proxy = RPC.getProxy(ClientProtocol.class, ClientProtocol.versionID,
				new InetSocketAddress(HOST, PORT), conf);
		String result = proxy.hello("world");
		System.out.println(result);
	}
}