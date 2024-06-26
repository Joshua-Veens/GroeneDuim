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
        const plantInfo = `
          <h1>Planteninformatie</h1>
          <p><strong>Wetenschappelijke naam:</strong> ${data.scientific_name}</p>
          <p><strong>Beschrijving:</strong> ${data.description}</p>
          <ul>
            <li><strong>Bloeitijd:</strong> ${data.bloom_time}</li>
            <li><strong>Hoogte:</strong> ${data.height}</li>
            <li><strong>Breedte:</strong> ${data.width}</li>
            <li><strong>Zonvereisten:</strong> ${data.sun_requirements}</li>
            <li><strong>Bodemtype:</strong> ${data.soil_type}</li>
            <li><strong>Waterbehoefte:</strong> ${data.water_needs}</li>
          </ul>
        `;
        document.getElementById("plant-info").innerHTML = plantInfo;
        document.getElementById("plant-info").style.display = "block";
        document.getElementById("remove-plant-button").style.display = "block";
    })
    .catch(error => {
        console.error("Error fetching plant:", error);
        alert("Plant not found");
    });
});

document.getElementById("remove-plant-button").addEventListener("click", function() {
    const plantId = document.getElementById("search-id").value;

    fetch(`/api/plants/${plantId}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            "Authorization": "Bearer " + window.localStorage.getItem("token")
        },
    })
    .then(response => {
        if (response.status === 200) {
            return response.json();
        } else {
            throw new Error("Failed to remove plant. Status: " + response.status);
        }
    })
    .then(data => {
        console.log("Plant removed successfully:", data); // Log to check data received
        alert("Plant removed successfully");
        document.getElementById("plant-info").reset();
        document.getElementById("plant-info").style.display = "none";
        document.getElementById("remove-plant-button").style.display = "none";
    })
    .catch(error => {
        console.error("Error removing plant:", error);
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
