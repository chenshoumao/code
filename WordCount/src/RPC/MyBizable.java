package RPC;

import java.io.IOException;

import org.apache.hadoop.ipc.ProtocolSignature;
import org.apache.hadoop.ipc.VersionedProtocol;

public interface MyBizable extends VersionedProtocol{
	long VERSION = 2345245L;
	public static final long versionID = 1L;
	public abstract String hello(String name);

	public abstract ProtocolSignature getProtocolSignature(String arg0,
			long arg1, int arg2) throws IOException;

	public abstract long getProtocolVersion(String arg0, long arg1)
			throws IOException;

}