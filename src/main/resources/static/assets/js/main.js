const deleteButton = document.getElementById("deleteButton");
deleteButton.addEventListener("click", deleteData);

// 진행상황 보여주기
function showProgress() {
    // SSE(Server Sent Event)
    const source = new EventSource("/progress");
    const progressDiv = document.getElementById("progress");

    source.onmessage = function (event) {
        progressDiv.innerText = event.data;
    };

    source.onerror = function () {
        source.close();
        progressDiv.innerText = "오류가 발생했습니다.";
    };
}

// playlist 안에서 선택한 비디오 삭제
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

    // 한개도 선택안하고 버튼눌렀을때
    if (selectedItems.length < 1) {
        alert("최소 한개이상 선택해주세요!");
        return;
    }

    const deleteConfirm = confirm("선택한 비디오들을 삭제 하시겠습니까?");

    if (deleteConfirm) {
        showProgress(selectedItems.length);

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
                location.reload(); // 목록 최신화를 위한 새로고침
                return response;
            }
            throw new Error('Request failed!');
        }).catch(error => {
            console.log(error);
        });
    }
}