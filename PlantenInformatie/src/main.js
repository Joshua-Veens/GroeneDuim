document.addEventListener("DOMContentLoaded", () => {
    const plantId = new URLSearchParams(window.location.search).get("id");

    if (plantId) {
      fetch(`/api/plants/${plantId}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
      })
        .then(response => response.json())
        .then(data => {
          document.getElementById("plant-name").textContent = data.scientific_name;
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
        })
        .catch(error => {
          console.error("Error fetching plant data:", error);
        });
    } else {
      document.getElementById("plant-info").innerHTML = "<p>Plant ID not specified in the URL.</p>";
    }
  });
