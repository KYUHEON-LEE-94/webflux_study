const eventSource = new EventSource('/sse/subscribe');

eventSource.addEventListener('connect', (e) => {
    const { data: receivedConnectData } = e;
    console.log('connect event data: ',receivedConnectData);  // "connected!"
});