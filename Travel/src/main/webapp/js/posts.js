function goToPost(postId) {
    // 게시글의 ID에 해당하는 URL을 생성합니다.
    var postUrl = 'post' + postId + '.html'; // 예를 들어, post1.html, post2.html, ...

    // 생성된 URL로 이동합니다.
    window.location.href = postUrl;
}