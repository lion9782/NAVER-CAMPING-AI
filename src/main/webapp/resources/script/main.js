// 메인 앱 초기화 및 이벤트 처리

// DOM 로드 완료 후 실행
document.addEventListener('DOMContentLoaded', function() {
    initializeApp();
    setupEventListeners();
});

// 앱 초기화
function initializeApp() {
    initializeAuth();
}

// 이벤트 리스너 설정
function setupEventListeners() {
    // 메시지 입력 엔터 키 이벤트
    const messageInput = document.getElementById('messageInput');
    if (messageInput) {
        messageInput.addEventListener('keypress', handleKeyPress);
    }

    // 페이지 종료 시 로컬 데이터 정리 (게스트 모드)
    window.addEventListener('beforeunload', function() {
        if (!getCurrentUser()) {
            localStorage.removeItem('campingGPTTempHistory');
        }
    });

    // 페이지 숨김 시 로컬 데이터 정리 (모바일 대응)
    document.addEventListener('visibilitychange', function() {
        if (document.visibilityState === 'hidden' && !getCurrentUser()) {
            localStorage.removeItem('campingGPTTempHistory');
        }
    });

    // 새로고침 시 로컬 데이터 정리
    window.addEventListener('unload', function() {
        if (!getCurrentUser()) {
            localStorage.removeItem('campingGPTTempHistory');
        }
    });
}

// 사이드바 토글 (모바일)
function toggleSidebar() {
    const sidebar = document.querySelector('.sidebar');
    sidebar.classList.toggle('open');
}

// 채팅 아이템 선택 (동적 생성용)
function selectChat(element) {
    const chatItems = document.querySelectorAll('.chat-item');
    chatItems.forEach(item => item.classList.remove('active'));
    element.classList.add('active');

    // 채팅 내용 로드 로직은 chatroom.js에서 처리
    const chatTitle = element.querySelector('.chat-title').textContent;
    const chatRoom = chatRooms.find(room => room.title === chatTitle);
    if (chatRoom) {
        selectChatRoom(chatRoom.roomId);
    }
}

// 페이지 로드 시 초기 설정
window.onload = function() {
    // 메시지 입력창 포커스
    const messageInput = document.getElementById('messageInput');
    if (messageInput) {
        messageInput.focus();
    }
};

// 채팅 앱 표시
function showChatApp() {
    document.getElementById('authScreen').style.display = 'none';
    document.getElementById('chatApp').style.display = 'flex';

    if (currentUser) {
        document.getElementById('userName').textContent = currentUser.name;
        document.getElementById('headerUserName').textContent = currentUser.name;

        // 로그인한 사용자의 채팅방 목록 로드
        loadChatRoomsFromServer();
    } else {
        document.getElementById('userName').textContent = '게스트';
        document.getElementById('headerUserName').textContent = '게스트';

        // 게스트 사용자의 로컬 채팅 히스토리 로드
        loadLocalChatHistory();
        renderLocalChatRooms();
    }
}