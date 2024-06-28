import LoginService from './loginService.js';

let service = new LoginService();

function refresh() {
    const gotobuttons = document.getElementById('gotobuttons');

    if (service.isLoggedIn()) {
        document.forms.login.style = "display:none";
        document.forms.logout.style = "display:block";
        gotobuttons.style = "display:block";
        
        service.getUser().then(user => {
            if (user) {
                document.getElementById('user').textContent = user.username;
            }
        });
    } else {
        document.forms.logout.style = "display:none";
        document.forms.login.style = "display:block";
        gotobuttons.style = "display:none";
    }
}

document.forms.login.addEventListener('submit', e => {
    e.preventDefault();
    service.login(document.forms.login.username.value, document.forms.login.password.value).then(() => {
        window.location.reload();
    }).catch(error => {
        alert(error);
    });
});

document.forms.logout.addEventListener('submit', e => {
    e.preventDefault();
    service.logout().then(() => {
        window.location.reload();
    });
});

refresh();

service.getUser().then(user => {
    if (!user) {
        service.logout();
    }
    refresh();
});
