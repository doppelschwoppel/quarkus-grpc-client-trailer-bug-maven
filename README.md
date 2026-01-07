# quarkus-grpc-client-trailer-bug
Reproducer for trailers not being propagated by quarkus-grpc-client

set 

`quarkus.grpc.clients.helloGrpc.use-quarkus-grpc-client=true`

and then
`HelloGrpcServiceTest`
fails
