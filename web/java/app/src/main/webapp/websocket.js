function addRow(data) {
        let table = document.getElementById('dataTable');
        let row = table.insertRow(-1);
        let cell = row.insertCell();
        let newText = document.createTextNode(data);
        cell.appendChild(newText);
    }
const socket = new WebSocket('ws://localhost/dictionary/ws/sharedhistory');
const contentDiv = document.getElementById('shared-history');
socket.onopen = (event) => {
          //contentDiv.innerHTML += 'connected :)';
};
socket.onmessage = (event) => {
    console.log(event.data);
    addRow(event.data);
};
