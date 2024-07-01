import"./style-DHhJK1-u.js";document.addEventListener("DOMContentLoaded",()=>{let e=JSON.parse(localStorage.getItem("shoppingCart"))||[];const o=document.getElementById("cart-info");e.length===0?o.innerHTML="<h2>Er zijn geen planten in je winkelmandje!</h2>":(e.forEach((t,n)=>{o.innerHTML+=`
        <div class="cart-item">
          <h3>${t.scientific_name}</h3>
          <p>${t.description}</p>
          <button class="remove-from-cart-button" data-index="${n}">Verwijder</button>
        </div>
      `}),document.querySelectorAll(".remove-from-cart-button").forEach(t=>{t.addEventListener("click",n=>{const i=n.target.getAttribute("data-index");e.splice(i,1),localStorage.setItem("shoppingCart",JSON.stringify(e)),window.location.reload()})}));const a=localStorage.getItem("lastViewedPlantId");a?document.getElementById("back-to-plant-info").href=`index.html?id=${a}`:document.getElementById("back-to-plant-info").href="index.html"});
//# sourceMappingURL=cart-BmFbJbS_.js.map
