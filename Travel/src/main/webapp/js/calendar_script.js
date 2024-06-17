// 움직일 수 있는 동그라미 구현
    let draggableCircle = document.getElementById('draggableCircle');
    let offsetX, offsetY;
    let isDragging = false;

    // 마우스 다운 이벤트 리스너 등록
    draggableCircle.addEventListener('mousedown', startDrag);
    // 마우스 업 이벤트 리스너 등록
    document.addEventListener('mouseup', endDrag);
    // 마우스 이동 이벤트 리스너 등록
    document.addEventListener('mousemove', drag);

    // 드래그 시작 함수
    function startDrag(e) {
      e.preventDefault(); // 기본 동작 방지
      isDragging = true;
      offsetX = e.clientX - draggableCircle.getBoundingClientRect().left;
      offsetY = e.clientY - draggableCircle.getBoundingClientRect().top;
    }

    // 드래그 종료 함수
    function endDrag() {
      isDragging = false;
    }

    // 드래그 함수
    function drag(e) {
      if (isDragging) {
        draggableCircle.style.left = e.clientX - offsetX + 'px';
        draggableCircle.style.top = e.clientY - offsetY + 'px';
      }
    }