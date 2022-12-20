document.addEventListener('DOMContentLoaded', function() {
    document.getElementById("logout").addEventListener("click", (e) => {
    if(!window.confirm("ログアウトしますか？")) {
        e.preventDefault();
    }
    }, false)
}, false);

document.addEventListener('DOMContentLoaded', function() {
    document.getElementById("delete-btn").addEventListener("click", (e) => {
        if(!window.confirm("投稿を削除しますか？")) {
            e.preventDefault();
        }
    }, false)
}, false);