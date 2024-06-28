import"./style-2Ke8x5G0.js";document.addEventListener("DOMContentLoaded",()=>{const n=new URLSearchParams(window.location.search).get("id");n?fetch(`/api/plants/${n}`,{method:"GET",headers:{"Content-Type":"application/json"}}).then(e=>e.json()).then(e=>{document.getElementById("plant-name").textContent=e.scientific_name;const t=`
            <h1>Planteninformatie</h1>
            <p><strong>Wetenschappelijke naam:</strong> ${e.scientific_name}</p>
            <p><strong>Beschrijving:</strong> ${e.description}</p>
            <ul>
              <li><strong>Bloeitijd:</strong> ${e.bloom_time}</li>
              <li><strong>Hoogte:</strong> ${e.height}</li>
              <li><strong>Breedte:</strong> ${e.width}</li>
              <li><strong>Zonvereisten:</strong> ${e.sun_requirements}</li>
              <li><strong>Bodemtype:</strong> ${e.soil_type}</li>
              <li><strong>Waterbehoefte:</strong> ${e.water_needs}</li>
            </ul>
          `;document.getElementById("plant-info").innerHTML=t}).catch(e=>{console.error("Error fetching plant data:",e)}):document.getElementById("plant-info").innerHTML="<p>Plant ID not specified in the URL.</p>"});
//# sourceMappingURL=main-ac-OeFV7.js.map
