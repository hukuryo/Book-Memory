(function () {
    var httpRequest;
    document.getElementById("ajaxButton").addEventListener('click', makeRequest);
  
    function makeRequest() {
      var userInput = document.getElementById('inputField').value;
  
      var query = userInput.split(' ').join('+');
  
      httpRequest = new XMLHttpRequest();
  
      if (!httpRequest) {
        alert('Giving up :( Cannot create an XMLHTTP instance');
        return false;
      }
  
      httpRequest.onreadystatechange = alertContents;
  
      httpRequest.open('GET', `https://www.googleapis.com/books/v1/volumes?q=${query}`);
  
      httpRequest.send();
    }
  
    function alertContents() {
      if (httpRequest.readyState === XMLHttpRequest.DONE) {
        if (httpRequest.status === 200) {
          var obj = httpRequest.responseText;
  
          var parsed = JSON.parse(obj); //ループのオブジェクト
  
          for (i = 0; i < parsed.items.length; i++) {
            var ul = document.getElementById('books'); //APIで出力させる要素
          if(typeof parsed.items[i].volumeInfo.imageLinks === "undefined"){
            var li = document.createElement('li');
            li.innerHTML = "<a class='bookLink' href='" + parsed.items[i].volumeInfo.previewLink + "' target='_blank' rel='noopener noreferrer'><div class='bookImage'><div class='bookImageInner'><img src='https://dubdesign.net/wp-content/uploads/2022/03/noimage.jpg' alt='" + parsed.items[i].volumeInfo.title + "'></div></div><p class='bookPublishedDate'>" + parsed.items[i].volumeInfo.publishedDate + "</p><p class='bookTitle'>" + parsed.items[i].volumeInfo.title + "</p></a>" ;
            ul.appendChild(li);}
          else {
            var li = document.createElement('li');
            li.innerHTML = "<a class='bookLink' href='" + parsed.items[i].volumeInfo.previewLink + "' target='_blank' rel='noopener noreferrer'><div class='bookImage'><div class='bookImageInner'><img src='" + parsed.items[i].volumeInfo.imageLinks.thumbnail + "' alt='" + parsed.items[i].volumeInfo.title + "'></div></div><p class='bookPublishedDate'>" + parsed.items[i].volumeInfo.publishedDate + "</p><p class='bookTitle'>" + parsed.items[i].volumeInfo.title + "</p></a>" ;
            ul.appendChild(li);};		
          }
        } else {
          console.log('There was a problem with the request.');
        }
      }
    }
  })();
  
  // ボタンクリックで無効化
  document.getElementById("ajaxButton").addEventListener('click', () => {
    const btn = document.getElementById("ajaxButton");
    btn.disabled=true;
  });
  
  // クリアで検索有効化
  document.getElementById("clearButton").addEventListener('click', () => {
    const btn = document.getElementById("ajaxButton");
    btn.disabled=false;
    // 検索結果を消す
    const ul = document.getElementById("books");
    const len = ul.children.length ;
  for ( var i = 0 ; i < len ; i ++ ) {
    ul.removeChild ( ul.children [ 0 ] ) ;}
    // 検索のフィールドを消す
    const textareaForm = document.getElementById("inputField");
    textareaForm.value = '';
  });


    document.getElementById("delete-btn").addEventListener("click", (e) => {
        if(!window.confirm("投稿を削除しますか？")) {
            e.preventDefault();
        }
    }, false);
  
  
    document.getElementById("logout").addEventListener("click", (e) => {
        if(!window.confirm("ログアウトしますか？")) {
            e.preventDefault();
        }
    }, false);