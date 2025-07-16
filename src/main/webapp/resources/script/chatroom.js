
// 채팅방 관련 전역 변수
let chatHistory = [];
let currentChatId = null;
let chatRooms = [];
let isFirstMessage = true;

// 새 채팅 시작
function startNewChat() {
    currentChatId = Math.floor(Math.random() * 2147483647).toString();
    isFirstMessage = true;
    clearChat();
    showWelcomeMessage();
    
    // 모든 채팅방 비활성화
    document.querySelectorAll('.chat-item').forEach(item => {
        item.classList.remove('active');
    });
}

// 새 채팅방 생성
function createNewChatRoom(firstMessage) {
    const chatId = Math.floor(Math.random() * 2147483647).toString();
    currentChatId = chatId;

    // 채팅방 제목 생성 (첫 메시지의 처음 20글자)
    const title = firstMessage.length > 20 ? 
        firstMessage.substring(0, 20) + '...' : firstMessage;

    // 새 채팅방 객체 생성
    const newChatRoom = {
        id: chatId,
        title: title,
        preview: firstMessage,
        timestamp: new Date(),
        icon: getChatIcon(firstMessage)
    };

    // 채팅방 배열에 추가 (맨 앞에)
    chatRooms.unshift(newChatRoom);

    // 사이드바에 채팅방 추가
    addChatRoomToSidebar(newChatRoom);

    // 로컬 스토리지에 저장
    saveChatRooms();

    // 첫 번째 메시지 플래그를 false로 설정
    isFirstMessage = false;

    return chatId;
}

// 사이드바에 채팅방 추가
function addChatRoomToSidebar(chatRoom) {
    const chatHistory = document.querySelector('.chat-history');
    
    // 새 채팅방 HTML 생성
    const chatItemHTML = `
        <div class="chat-item active" onclick="selectChat(this)" data-chat-id="${chatRoom.id}">
            <i class="${chatRoom.icon}"></i>
            <div class="chat-info">
                <div class="chat-title">${chatRoom.title}</div>
                <div class="chat-preview">${chatRoom.preview}</div>
            </div>
        </div>
    `;
    
    // 모든 기존 채팅방 비활성화
    document.querySelectorAll('.chat-item').forEach(item => {
        item.classList.remove('active');
    });
    
    // 새 채팅방을 맨 위에 추가
    chatHistory.insertAdjacentHTML('afterbegin', chatItemHTML);
}

// 채팅 아이콘 결정
function getChatIcon(message) {
    const lowerMessage = message.toLowerCase();
    
    if (lowerMessage.includes('준비물') || lowerMessage.includes('장비')) {
        return 'fas fa-backpack';
    } else if (lowerMessage.includes('캠핑장') || lowerMessage.includes('추천')) {
        return 'fas fa-map-marked-alt';
    } else if (lowerMessage.includes('요리') || lowerMessage.includes('레시피') || lowerMessage.includes('음식')) {
        return 'fas fa-utensils';
    } else if (lowerMessage.includes('안전') || lowerMessage.includes('주의')) {
        return 'fas fa-shield-alt';
    } else if (lowerMessage.includes('텐트')) {
        return 'fas fa-campground';
    } else if (lowerMessage.includes('불') || lowerMessage.includes('화재')) {
        return 'fas fa-fire';
    } else {
        return 'fas fa-comments';
    }
}

// 채팅방 저장
function saveChatRooms() {
    localStorage.setItem('campingGPTRooms', JSON.stringify(chatRooms));
}

// 채팅방 로드
function loadChatRooms() {
    const savedRooms = localStorage.getItem('campingGPTRooms');
    if (savedRooms) {
        chatRooms = JSON.parse(savedRooms);
        renderChatRooms();
    }
}

// 채팅방 렌더링
function renderChatRooms() {
    const chatHistory = document.querySelector('.chat-history');
    chatHistory.innerHTML = '';
    
    chatRooms.forEach((room, index) => {
        const isActive = index === 0 ? 'active' : '';
        const chatItemHTML = `
            <div class="chat-item ${isActive}" onclick="selectChat(this)" data-chat-id="${room.id}">
                <i class="${room.icon}"></i>
                <div class="chat-info">
                    <div class="chat-title">${room.title}</div>
                    <div class="chat-preview">${room.preview}</div>
                </div>
            </div>
        `;
        chatHistory.insertAdjacentHTML('beforeend', chatItemHTML);
    });
}

// 채팅 기록 로드
function loadChatHistory() {
    const savedUser = localStorage.getItem('campingGPTUser');
    const savedHistory = localStorage.getItem('campingGPTHistory');
    if (savedHistory) {
        chatHistory = JSON.parse(savedHistory);
    }

    // 채팅방 로드
    loadChatRooms();

    // 채팅방이 없으면 새 채팅 시작
    if (chatRooms.length === 0) {
        startNewChat();
    }
}

// 채팅 선택
function selectChat(element) {
    // 이전 선택 제거
    document.querySelectorAll('.chat-item').forEach(item => {
        item.classList.remove('active');
    });

    // 현재 선택 표시
    element.classList.add('active');

    // 채팅 ID 가져오기
    const chatId = element.getAttribute('data-chat-id');
    currentChatId = chatId;

    // 기존 채팅방을 선택했으므로 첫 번째 메시지가 아님
    isFirstMessage = false;

    // 해당 채팅 내용 로드 (임시로 새 채팅으로 처리)
    clearChat();
    showWelcomeMessage();
}

// 현재 선택된 채팅방 ID 가져오기
function getCurrentChatRoomId() {
    return currentChatId;
}

// 새로 생성된 채팅방 ID 가져오기 (마지막으로 생성된 것)
function getLastCreatedChatRoomId() {
    const activeChatItem = document.querySelector('.chat-item.active');
    if (activeChatItem) {
        return activeChatItem.getAttribute('data-chat-id');
    }
    return currentChatId;
}

// 채팅 기록 저장
function saveChatHistory() {
    localStorage.setItem('campingGPTHistory', JSON.stringify(chatHistory));
}
