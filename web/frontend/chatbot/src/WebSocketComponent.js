import React, { useState, useEffect } from 'react';

function WebSocketComponent() {
  const [webSocket, setWebSocket] = useState(null);
  const [message, setMessage] = useState('');

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

  const sendMessage = () => {
    if (webSocket && message !== '') {
      webSocket.send(message);
      setMessage(''); // Clear the message input after sending
    }
  };

  return (
    <div>
      <input
        type="text"
        value={message}
        onChange={(e) => setMessage(e.target.value)}
        placeholder="Type your message here"
      />
      <button onClick={sendMessage}>Send Message</button>
    </div>
  );
}

export default WebSocketComponent;