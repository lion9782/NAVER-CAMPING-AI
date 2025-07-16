
// 로그인 및 회원가입 관련 전역 변수
let currentUser = null;

// 앱 초기화
function initializeApp() {
    // 저장된 사용자 정보 확인
    const savedUser = localStorage.getItem('campingGPTUser');
    if (savedUser) {
        currentUser = JSON.parse(savedUser);
        showChatApp();
    } else {
        showAuthScreen();
    }
}

// 인증 화면 표시/숨김
function showAuthScreen() {
    document.getElementById('authScreen').style.display = 'flex';
    document.getElementById('chatApp').style.display = 'none';
}

function showChatApp() {
    document.getElementById('authScreen').style.display = 'none';
    document.getElementById('chatApp').style.display = 'flex';

    if (currentUser) {
        document.getElementById('userName').textContent = currentUser.name;
        document.getElementById('headerUserName').textContent = currentUser.name;
    }

    loadChatHistory();
}

// 로그인/회원가입 폼 전환
function showLogin() {
    document.getElementById('loginForm').classList.add('active');
    document.getElementById('signupForm').classList.remove('active');
}

function showSignup() {
    document.getElementById('signupForm').classList.add('active');
    document.getElementById('loginForm').classList.remove('active');
}

// 로그인 처리
function handleLogin(event) {
    event.preventDefault();

    const email = document.getElementById('loginEmail').value;
    const password = document.getElementById('loginPassword').value;

    if (!email || !password) {
        alert('이메일과 비밀번호를 입력해주세요.');
        return;
    }

    // 임시 로그인 (실제로는 서버 인증 필요)
    currentUser = {
        id: Date.now(),
        name: email.split('@')[0],
        email: email
    };

    localStorage.setItem('campingGPTUser', JSON.stringify(currentUser));
    showChatApp();
}

// 회원가입 처리
function handleSignup(event) {
    event.preventDefault();

    const name = document.getElementById('signupName').value;
    const email = document.getElementById('signupEmail').value;
    const password = document.getElementById('signupPassword').value;
    const confirmPassword = document.getElementById('confirmPassword').value;

    if (!name || !email || !password || !confirmPassword) {
        alert('모든 필드를 입력해주세요.');
        return;
    }

    if (password !== confirmPassword) {
        alert('비밀번호가 일치하지 않습니다.');
        return;
    }

    if (password.length < 6) {
        alert('비밀번호는 6자 이상이어야 합니다.');
        return;
    }

    // 임시 회원가입 (실제로는 서버에 저장 필요)
    currentUser = {
        id: Date.now(),
        name: name,
        email: email
    };

    localStorage.setItem('campingGPTUser', JSON.stringify(currentUser));
    alert('회원가입이 완료되었습니다!');
    showChatApp();
}

// 사용자 메뉴 토글
function toggleUserMenu() {
    const userMenu = document.querySelector('.user-menu');
    userMenu.classList.toggle('open');
}

// 로그아웃
function logout() {
    if (confirm('정말 로그아웃 하시겠습니까?')) {
        currentUser = null;
        localStorage.removeItem('campingGPTUser');
        localStorage.removeItem('campingGPTHistory');
        localStorage.removeItem('campingGPTRooms');
        clearChat();
        showChatApp();
        
        // 사용자 메뉴 닫기
        const userMenu = document.querySelector('.user-menu');
        if (userMenu) {
            userMenu.classList.remove('open');
        }              
        
        alert('로그아웃되었습니다.');
    }
}
