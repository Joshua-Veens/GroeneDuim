import"./style-Htvu6hAP.js";import{L as s}from"./loginService-C4Qp4EcO.js";let n=new s;function o(){n.isLoggedIn()?(document.forms.login.style="display:none",document.forms.logout.style="display:block",n.getUser().then(t=>{t&&(document.getElementById("user").textContent=t.username)})):(document.forms.logout.style="display:none",document.forms.login.style="display:block")}document.forms.login.addEventListener("submit",t=>{t.preventDefault(),n.login(document.forms.login.username.value,document.forms.login.password.value).then(()=>{window.location.reload()}).catch(e=>{alert(e)})});document.forms.logout.addEventListener("submit",t=>{t.preventDefault(),n.logout().then(()=>{window.location.reload()})});document.getElementById("search-plant-button").addEventListener("click",function(){const t=document.getElementById("search-id").value;fetch(`/api/plants/${t}`,{method:"GET",headers:{"Content-Type":"application/json",Authorization:"Bearer "+window.localStorage.getItem("token")}}).then(e=>e.json()).then(e=>{const l=`
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
        `;document.getElementById("plant-info").innerHTML=l,document.getElementById("plant-info").style.display="block",document.getElementById("remove-plant-button").style.display="block"}).catch(e=>{console.error("Error fetching plant:",e),alert("Plant not found")})});document.getElementById("remove-plant-button").addEventListener("click",function(){const t=document.getElementById("search-id").value;fetch(`/api/plants/${t}`,{method:"DELETE",headers:{"Content-Type":"application/json",Authorization:"Bearer "+window.localStorage.getItem("token")}}).then(e=>{if(e.status===200)return e.json();throw new Error("Failed to remove plant. Status: "+e.status)}).then(e=>{console.log("Plant removed successfully:",e),alert("Plant removed successfully"),document.getElementById("plant-info").reset(),document.getElementById("plant-info").style.display="none",document.getElementById("remove-plant-button").style.display="none"}).catch(e=>{console.error("Error removing plant:",e),alert(e.message)})});o();n.getUser().then(t=>{t||n.logout(),o()});
//# sourceMappingURL=removeplant-CECPKjfG.js.map
