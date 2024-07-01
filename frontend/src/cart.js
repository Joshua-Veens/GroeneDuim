document.addEventListener("DOMContentLoaded", () => {
  let cart = JSON.parse(localStorage.getItem('shoppingCart')) || [];
  const cartInfo = document.getElementById("cart-info");

  if (cart.length === 0) {
    cartInfo.innerHTML = "<h2>Er zijn geen planten in je winkelmandje!</h2>";
  } else {
    cart.forEach((plant, index) => {
      cartInfo.innerHTML += `
        <div class="cart-item">
          <h3>${plant.scientific_name}</h3>
          <p>${plant.description}</p>
          <button class="remove-from-cart-button" data-index="${index}">Verwijder</button>
        </div>
      `;
    });

    document.querySelectorAll('.remove-from-cart-button').forEach(button => {
      button.addEventListener('click', (event) => {
        const index = event.target.getAttribute('data-index');
        cart.splice(index, 1);
        localStorage.setItem('shoppingCart', JSON.stringify(cart));
        window.location.reload();
      });
    });
  }

  const plantId = localStorage.getItem('lastViewedPlantId');
  if (plantId) {
    document.getElementById("back-to-plant-info").href = `index.html?id=${plantId}`;
  } else {
    document.getElementById("back-to-plant-info").href = "index.html";
  }
});