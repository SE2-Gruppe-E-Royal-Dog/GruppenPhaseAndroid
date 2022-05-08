package com.uni.gruppenphaseandroid.communication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import com.google.gson.Gson;
import com.uni.gruppenphaseandroid.communication.dto.Message;
import com.uni.gruppenphaseandroid.communication.dto.MessageType;
import com.uni.gruppenphaseandroid.communication.dto.NewPlayerPayload;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.net.URI;
import java.net.URISyntaxException;

public class ClientTest {
    private final Gson gson = new Gson();

    @Test
    public void givenSendJoinLobbyMessage_expectSendIsCalledWithCorrectString() throws URISyntaxException {
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
    public void givenConnectToServerIsCalled_expectConnectBlockingIsCalled() throws URISyntaxException, InterruptedException {
        var client = new Client(new URI("test"));
        var clientSpy = spy(client);
        doReturn(true).when(clientSpy).connectBlocking();

        clientSpy.connectToServer();
        verify(clientSpy).connectBlocking();
    }
}