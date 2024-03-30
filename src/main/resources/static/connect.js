const save = async () => {
    const url = document.getElementById('url').value;
    const method = document.getElementById('method').value;
    const resourcePath = document.getElementById('resourcePath').value;

    const response = await fetch("/resource/save", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: {
            'url': url,
            'method': method,
            'resourcePath': resourcePath
        }
    });
    return response.json();
}
