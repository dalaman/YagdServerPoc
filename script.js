const PORT = "8080"; // FIXME: port num

const webSocket = new WebSocket(`ws://127.0.0.1:${PORT}`);

webSocket.onopen = () => {
    document.getElementById("show").innerHTML += "Connected" + "<br />";
}

webSocket.onmessage = (event) => {
    document.getElementById("show").innerHTML += event.data + "<br />";
}

const send = () => {
    const line = document.getElementById("input");
    webSocket.send(line.value);
    line.value = ""; // clear
}