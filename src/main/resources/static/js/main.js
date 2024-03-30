// document.getElementById('saveButton').addEventListener('click',  );

const save = async () => {
    const url = document.getElementById('url').value;
    const method = document.getElementById('method').value;
    const fileName = document.getElementById('filename').value;
    const content = JSON.parse(document.getElementById('content').value);

    const response = await fetch("/resource/save", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            'url': url,
            'method': method,
            'fileName': fileName,
            'content': content
        })
    });

    if (!response.ok) {
        console.log("error")
    }
}