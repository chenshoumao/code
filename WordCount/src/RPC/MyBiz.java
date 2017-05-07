package RPC;

import java.io.IOException;

import org.apache.hadoop.ipc.ProtocolSignature;
import org.apache.hadoop.ipc.VersionedProtocol;

public class MyBiz implements MyBizable{
	 
	@Override
	public String hello(String name) {
		return "hello " + name;
	}

	@Override
	public ProtocolSignature getProtocolSignature(String arg0, long arg1,
			int arg2) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getProtocolVersion(String arg0, long arg1) throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	 
}
