const wsPrivateUrl = 'ws://localhost:8080/websocket-private';

const stompPrivateClient = new StompJs.Client({
    brokerURL: wsPrivateUrl
});

stompPrivateClient.onConnect = (frame) => {
    setPrivateConnected(true);
    console.log('Private Connected: ' + frame);
    // stompPrivateClient.subscribe('/topic/greetings', (greeting) => {
    //     showGreeting(JSON.parse(greeting.body).content);
    // });
};

stompPrivateClient.onWebSocketError = (error) => {
    console.error('Error with private websocket', error);
    console.error('url: ', wsPrivateUrl);
};

stompPrivateClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

function setPrivateConnected(connected) {
    $("#privateConnect").prop("disabled", connected);
    $("#privateDisconnect").prop("disabled", !connected);
}

function privateConnect() {
    stompPrivateClient.activate();
}

function privateDisconnect() {
    stompPrivateClient.deactivate();
    setPrivateConnected(false);
    console.log("Private Disconnected");
}

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $( "#privateConnect" ).click(() => privateConnect());
    $( "#privateDisconnect" ).click(() => privateDisconnect());
});

