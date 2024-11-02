// Load header with application name and dynamic time
function updateTime() {
    const now = new Date();
    document.getElementById("header").innerHTML = `
        <span>Auca Library Management System</span>
        <span>${now.toLocaleTimeString()}</span>
    `;
}

setInterval(updateTime, 1000);
