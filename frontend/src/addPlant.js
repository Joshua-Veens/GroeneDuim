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
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(plantData)
    })
    .then(response => {
        if (response.status === 201) {
            return response.json();
        } else if (response.status === 409) {
            return response.text().then(text => { throw new Error(text); });
        } else {
            throw new Error("Failed to add plant.");
        }
    })
    .then(data => {
        alert("Plant added successfully!");
        document.getElementById("add-plant-form").reset();
    })
    .catch(error => {
        console.error("Error adding plant:", error);
        alert(error.message);
    });
});
