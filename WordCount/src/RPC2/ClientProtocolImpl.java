package RPC2;

import java.io.IOException;

import org.apache.hadoop.ipc.ProtocolSignature;

/**
 * DateTime: 2014年12月28日 上午9:41:38
 *
 */
public class ClientProtocolImpl implements ClientProtocol {

	@Override
	public long getProtocolVersion(String protocol, long clientVersion)
			throws IOException {
		return versionID;
	}

	@Override
	public ProtocolSignature getProtocolSignature(String protocol,
			long clientVersion, int clientMethodsHash) throws IOException {
		return new ProtocolSignature(versionID, null);
	}

	@Override
	public String hello(String msg) throws IOException {
		return "hello " + msg;
	}
	
	
}