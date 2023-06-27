const deleteButton = document.getElementById('deleteButton');
deleteButton.addEventListener('click', deleteData);

function deleteData() {
    const selectedData = document.getElementById("playlistSelect")
    const url = '/playlist/delete';

    fetch(url, {
        method: 'DELETE',
        body: JSON.stringify(selectedData),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(response => {
        if (response.ok) {
            return response.json();
        }
        throw new Error('Request failed!');
    }).catch(error => {
        console.log(error);
    });
}