package com.se2.communication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

import com.google.gson.Gson;
import com.se2.communication.dto.Message;
import com.se2.communication.dto.MessageType;
import com.se2.communication.dto.NewPlayerPayload;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.net.URI;
import java.net.URISyntaxException;

class ClientTest {
    private final Gson gson = new Gson();

    @Test
    void givenSendJoinLobbyMessage_expectSendIsCalledWithCorrectString() throws URISyntaxException {
        var client = new Client(new URI("test"));
        var clientSpy = spy(client);
        doNothing().when(clientSpy).send(anyString());

        var message = new Message();
        message.setType(MessageType.JOIN_LOBBY);

        var payload = new NewPlayerPayload("player-1");
        message.setPayload(gson.toJson(payload));

        clientSpy.send(message);

        var captor = ArgumentCaptor.forClass(String.class);

        verify(clientSpy).send(captor.capture());

        assertThat(captor.getValue()).isEqualTo("{\"type\":\"JOIN_LOBBY\",\"payload\":\"{\\\"playerName\\\":\\\"player-1\\\"}\"}");
    }

    @Test
    void givenConnectToServerIsCalled_expectConnectBlockingIsCalled() throws URISyntaxException, InterruptedException {
        var client = new Client(new URI("test"));
        var clientSpy = spy(client);
        doReturn(true).when(clientSpy).connectBlocking();

        clientSpy.connectToServer();
        verify(clientSpy).connectBlocking();
    }
}