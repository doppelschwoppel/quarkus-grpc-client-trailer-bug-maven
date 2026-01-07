package org.acme;

import com.google.rpc.ErrorInfo;
import com.google.rpc.Status;
import io.grpc.protobuf.StatusProto;
import io.quarkus.grpc.GrpcService;

import io.smallrye.mutiny.Uni;

@GrpcService
public class HelloGrpcService implements HelloGrpc {

    @Override
    public Uni<HelloReply> sayHello(HelloRequest request) {
        return Uni.createFrom().failure(() -> {
            ErrorInfo errorInfo = ErrorInfo.newBuilder()
                    .setDomain("org.acme.test")
                    .setReason("stub-error")
                    .build();

            return StatusProto.toStatusRuntimeException(
                    Status.newBuilder()
                            .setCode(io.grpc.Status.INTERNAL.getCode().value())
                            .setMessage("this is a test error")
                            .addDetails(com.google.protobuf.Any.pack(errorInfo))
                            .build()
            );
        });
    }



}
