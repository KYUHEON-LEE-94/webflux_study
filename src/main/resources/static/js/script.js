const eventSource = new EventSource('/sse/subscribe');

eventSource.addEventListener('connect', (e) => {
    const { data: receivedConnectData } = e;
    console.log('connect event data: ',receivedConnectData);  // "connected!"
});

let clientWebSocket = new WebSocket("ws://localhost:8081/ws");
clientWebSocket.onopen = function() {
    console.log("clientWebSocket.onopen", clientWebSocket);
    console.log("clientWebSocket.readyState", "websocketstatus");
    clientWebSocket.send("event-me-from-browser");
}
clientWebSocket.onclose = function(error) {
    console.log("clientWebSocket.onclose", clientWebSocket, error);
    events("Closing connection");
}
clientWebSocket.onerror = function(error) {
    console.log("clientWebSocket.onerror", clientWebSocket, error);
    events("An error occured");
}
clientWebSocket.onmessage = function(error) {
    console.log("clientWebSocket.onmessage", clientWebSocket, error);
    events(error.data);
}
function events(responseEvent) {
    document.querySelector(".events").innerHTML += responseEvent + "<br>";
}