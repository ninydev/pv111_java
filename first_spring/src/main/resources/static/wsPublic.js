const wsPublicUrl = 'ws://localhost:8080/websocket-public';

const stompPublicClient = new StompJs.Client({
    brokerURL: wsPublicUrl
});

stompPublicClient.onConnect = (frame) => {
    setConnected(true);
    console.log('Public Connected: ' + frame);
    stompPublicClient.subscribe('/topic/greetings', (greeting) => {
        console.log('/topic/greetings');
        showGreeting(JSON.parse(greeting.body).content);
    });
};

stompPublicClient.onWebSocketError = (error) => {
    console.error('Error with public  websocket', error);
    console.error('url: ', wsPublicUrl);
};

stompPublicClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    stompPublicClient.activate();
}

function disconnect() {
    stompPublicClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompPublicClient.publish({
        destination: "/app/hello",
        body: JSON.stringify({'name': $("#name").val()})
    });
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $( "#connect" ).click(() => connect());
    $( "#disconnect" ).click(() => disconnect());
    $( "#send" ).click(() => sendName());
});

