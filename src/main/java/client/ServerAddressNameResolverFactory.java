package client;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import io.grpc.Attributes;
import io.grpc.EquivalentAddressGroup;
import io.grpc.NameResolver;
import io.grpc.NameResolver.Args;

public class ServerAddressNameResolverFactory extends NameResolver.Factory{

	final List<EquivalentAddressGroup> addresses;

    public ServerAddressNameResolverFactory(SocketAddress... addresses) {
        this.addresses = Arrays.stream(addresses)
                .map(EquivalentAddressGroup::new)
                .collect(Collectors.toList());
    }

	public NameResolver newNameResolver(URI targetUri, Attributes params) {
		// TODO Auto-generated method stub
		return null;
	}	
	@Override
    public NameResolver newNameResolver(URI notUsedUri, NameResolver.Args args) {
        return new NameResolver() {
            @Override
            public String getServiceAuthority() {
                return "fakeAuthority";
            }
            public void shutdown() {
            }
			@Override
			public void start(Listener2 listener) {
				listener.onResult(ResolutionResult.newBuilder().setAddresses(addresses).setAttributes(Attributes.EMPTY).build());
				
			}
        };
    }

    @Override
    public String getDefaultScheme() {
        return "multiaddress";
    }

}

