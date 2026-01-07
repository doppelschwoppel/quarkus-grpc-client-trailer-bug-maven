package org.acme;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.rpc.ErrorInfo;
import io.quarkus.grpc.GrpcClient;
import io.quarkus.test.junit.QuarkusTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

@QuarkusTest
class HelloGrpcServiceTest {
    @GrpcClient("helloGrpc")
    HelloGrpc helloGrpc;

    @Test
    void testHello() throws InvalidProtocolBufferException {
        io.grpc.StatusRuntimeException e = assertThrows(io.grpc.StatusRuntimeException.class,
                () -> helloGrpc
                        .sayHello(HelloRequest.newBuilder().setName("Neo").build()).await().atMost(Duration.ofSeconds(5)));

        ErrorInfo errorInfo = io.grpc.protobuf.StatusProto.fromThrowable(e).getDetails(0).unpack(ErrorInfo.class);
        assertEquals("stub-error", errorInfo.getReason());
    }

}
