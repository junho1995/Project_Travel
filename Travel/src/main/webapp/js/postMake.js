document.addEventListener('DOMContentLoaded', function() {
    // 게시물 작성 버튼에 클릭 이벤트 추가
    document.getElementById('post-button').addEventListener('click', updatePreview);
    
    // 엔터 키로 줄바꿈 적용
    document.getElementById('post-title').addEventListener('input', updatePreview);
    document.getElementById('post-content').addEventListener('input', updatePreview);
    document.getElementById('post-tags').addEventListener('input', updatePreview);
    document.getElementById('post-image').addEventListener('input', updatePreview);
    });

   