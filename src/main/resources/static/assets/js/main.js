const deleteButton = document.getElementById("deleteButton");
deleteButton.addEventListener("click", deleteData);

function deleteData() {
    const selectedItems = []; // 선택된 항목들 담을 배열
    const csrfToken = document.head.querySelector("meta[name='_csrf']").content;
    const csrfHeader = document.head.querySelector("meta[name='_csrf_header']").content;

    // 체크되어있는 항목들의 value값 selectedItems 배열에 담기
    const playlistItems = document.getElementsByClassName("playlistItem");

    for (let i = 0; i < playlistItems.length; i++) {
        if (playlistItems[i].checked) {
            selectedItems.push(playlistItems[i].value);
        }
    }

    if (selectedItems.length < 1) {
        alert("최소 한개이상 선택해주세요!");
        return;
    }

    fetch("/playlists/delete", {
        method: "DELETE",
        headers: {
            "header": csrfHeader,
            "Content-Type": "application/json",
            "X-CSRF-TOKEN": csrfToken, // CSRF 토큰
        },
        body: JSON.stringify(selectedItems),
        credentials: "include" //인증 정보를 요청에 포함
    }).then(response => {
        if (response.ok) {
            return response;
        }
        throw new Error('Request failed!');
    }).catch(error => {
        console.log(error);
    });
}