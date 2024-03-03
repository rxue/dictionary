import React, { useState, useEffect } from 'react';

function WebSocketComponent() {
  const [webSocket, setWebSocket] = useState(null);

  useEffect(() => {
    // Create WebSocket connection.
    const socket = new WebSocket('ws://localhost/dictionary/ws/chat');

    // Connection opened
    socket.addEventListener('open', function (event) {
      console.log('WebSocket is open now.');
    });

    // Listen for messages
    socket.addEventListener('message', function (event) {
      console.log('Message from server ', event.data);
    });

    // Update the state with the WebSocket reference
    setWebSocket(socket);

    // Clean up on component unmount
    return () => {
      socket.close();
    };
  }, []); // Empty array means this effect runs only once on mount

  // Function to send data through the WebSocket
  const sendData = (data) => {
    if (webSocket) {
      webSocket.send(data);
    }
  };

  return (
    <div>
      <button onClick={() => sendData('Hello Server!')}>Send Data</button>
    </div>
  );
}

export default WebSocketComponent;