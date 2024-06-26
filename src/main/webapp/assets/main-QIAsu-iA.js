(function(){const e=document.createElement("link").relList;if(e&&e.supports&&e.supports("modulepreload"))return;for(const t of document.querySelectorAll('link[rel="modulepreload"]'))s(t);new MutationObserver(t=>{for(const n of t)if(n.type==="childList")for(const i of n.addedNodes)i.tagName==="LINK"&&i.rel==="modulepreload"&&s(i)}).observe(document,{childList:!0,subtree:!0});function r(t){const n={};return t.integrity&&(n.integrity=t.integrity),t.referrerPolicy&&(n.referrerPolicy=t.referrerPolicy),t.crossOrigin==="use-credentials"?n.credentials="include":t.crossOrigin==="anonymous"?n.credentials="omit":n.credentials="same-origin",n}function s(t){if(t.ep)return;t.ep=!0;const n=r(t);fetch(t.href,n)}})();document.addEventListener("DOMContentLoaded",()=>{const o=new URLSearchParams(window.location.search).get("id");o?fetch(`/api/plants/${o}`).then(e=>e.json()).then(e=>{document.getElementById("plant-name").textContent=e.scientific_name;const r=`
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
          `;document.getElementById("plant-info").innerHTML=r}).catch(e=>{console.error("Error fetching plant data:",e)}):document.getElementById("plant-info").innerHTML="<p>Plant ID not specified in the URL.</p>"});
//# sourceMappingURL=main-QIAsu-iA.js.map
