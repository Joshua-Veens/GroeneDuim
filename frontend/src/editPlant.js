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

document.getElementById("search-plant-button").addEventListener("click", function() {
    const plantId = document.getElementById("search-id").value;
    
    fetch(`/api/plants/${plantId}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            "Authorization": "Bearer " + window.localStorage.getItem("token")
        }
    })
    .then(response => response.json())
    .then(data => {
        document.getElementById("id").value = data.id;
        document.getElementById("scientific_name").value = data.scientific_name;
        document.getElementById("description").value = data.description;
        document.getElementById("bloom_time").value = data.bloom_time;
        document.getElementById("height").value = data.height;
        document.getElementById("width").value = data.width;
        document.getElementById("sun_requirements").value = data.sun_requirements;
        document.getElementById("soil_type").value = data.soil_type;
        document.getElementById("water_needs").value = data.water_needs;
        document.getElementById("edit-plant-form").style.display = "block";
    })
    .catch(error => {
        console.error("Error fetching plant:", error);
        alert("Plant not found");
    });
});

document.getElementById("edit-plant-form").addEventListener("submit", function(event) {
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

    fetch(`/api/plants/${plantData.id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            "Authorization": "Bearer " + window.localStorage.getItem("token")
        },
        body: JSON.stringify(plantData)
    })
    .then(response => {
        if (response.status === 200) {
            return response.json();
        } else {
            throw new Error("Failed to update plant.");
        }
    })
    .then(data => {
        alert("Plant updated successfully");
        document.getElementById("edit-plant-form").reset();
        document.getElementById("edit-plant-form").style.display = "none";
    })
    .catch(error => {
        console.error("Error updating plant:", error);
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
