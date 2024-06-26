import LoginService from './loginService.js';

let service = new LoginService();

function refresh() {
    if (service.isLoggedIn()) {
        document.forms.login.style = "display:none";
        document.forms.logout.style = "display:block";
        service.getUser().then(user => {
            if (user) {
                document.getElementById('user').textContent = user.username;
            }
        });
    } else {
        document.forms.logout.style = "display:none";
        document.forms.login.style = "display:block";
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

document.getElementById("add-plant-form").addEventListener("submit", function(event) {
    event.preventDefault();
    
    const plantData = {
        id: document.getElementById("id").value,
        scientific_name: document.getElementById("scientific_name").value,
        description: document.getElementById("description").value,
        bloom_time: document.getElementById("bloom_time").value,
        height: document.getElementById("height").value,
        width: document.getElementById("width").value,
        sun_requirements: document.getElementById("sun_requirements").value,
        soil_type: document.getElementById("soil_type").value,
        water_needs: document.getElementById("water_needs").value
    };

    fetch('/api/plants', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            "Authorization": "Bearer " + window.localStorage.getItem("token")
        },
        body: JSON.stringify(plantData)
    })
    .then(response => {
        if (response.status === 201) {
            return response.json();
        } else if (response.status === 409) {
            return response.text().then(text => { throw new Error(text); });
        } else if (response.status === 403) {
            return new Error("You are not authorized to add a plant.");
        } else {
            throw new Error("Failed to add plant.");
        }
    })
    .then(data => {
        document.getElementById("add-plant-form").reset();
    })
    .catch(error => {
        console.error("Error adding plant:", error);
        alert(error.message);
    });
});

refresh();

service.getUser().then(user => {
    if (!user) {
        service.logout();
    }
    refresh();
});