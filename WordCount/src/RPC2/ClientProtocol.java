package RPC2;

import java.io.IOException;

import org.apache.hadoop.ipc.VersionedProtocol;

/**
 * DateTime: 2014年12月28日 上午9:32:12
 *
 */
public interface ClientProtocol extends VersionedProtocol {
	//版本号，默认情况下，不同版本号的RPC客户端与Server之间不能相互通信
	public static final long versionID = 1L;
	public String hello(String msg) throws IOException;
}
