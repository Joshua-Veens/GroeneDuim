import"./style-D8dTFR_9.js";document.getElementById("add-plant-form").addEventListener("submit",function(t){t.preventDefault();const n={id:document.getElementById("id").value,scientific_name:document.getElementById("scientific_name").value,description:document.getElementById("description").value,bloom_time:document.getElementById("bloom_time").value,height:document.getElementById("height").value,width:document.getElementById("width").value,sun_requirements:document.getElementById("sun_requirements").value,soil_type:document.getElementById("soil_type").value,water_needs:document.getElementById("water_needs").value};fetch("/api/plants",{method:"POST",headers:{"Content-Type":"application/json"},body:JSON.stringify(n)}).then(e=>{if(e.status===201)return e.json();if(e.status===409)return e.text().then(d=>{throw new Error(d)});throw new Error("Failed to add plant.")}).then(e=>{alert("Plant added successfully!"),document.getElementById("add-plant-form").reset()}).catch(e=>{console.error("Error adding plant:",e),alert(e.message)})});
//# sourceMappingURL=addplant-BiYRO0kx.js.map