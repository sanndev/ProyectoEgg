$(document).ready(function(){

    offset = 1;
    limit = 12;
    cargarMascotas(offset,limit);

});

const spinner = document.querySelector("#spinner");
const anterior = document.querySelector("#anterior");
const siguiente = document.querySelector("#siguiente");

anterior.addEventListener('click', ()=>{

    if(offset != 1){

        const row = document.querySelector(".misColumnas");
        offset -=12;
        limit -=12;
        removerChildNodes(row)
        cargarMascotas(offset,limit);

    }

})

siguiente.addEventListener('click', ()=>{
    
    const row = document.querySelector(".misColumnas");
    offset +=12;
    limit +=12;
    removerChildNodes(row)
    cargarMascotas(offset,limit);

})


async function cargarMascotas(offset,limit){


    console.log("offset: " + offset)
    console.log("limit: " + limit)

  const row = document.querySelector(".misColumnas");

  const request = await fetch('https://jsonplaceholder.typicode.com/photos', {
    method: 'GET',
    headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
    }

  });
  const mascotas = await request.json(); //lo transforman a json

  for(let mascota of mascotas){  

    spinner.style.display = "block";

    var i = mascota.id;

    if(i >= offset && i<=limit){
            
        console.log(mascota);

        const col = document.createElement('div')
        col.className ="col"
        col.setAttribute("data-aos","fade-up") //efecto de las cards

        const card = document.createElement('div')
        card.className ="card shadow-sm"
        
        const card_body = document.createElement('div')
        card_body.className = "card-body"

        const img = document.createElement('img')
        img.src = mascota.url //cambiar luego de prueba con placeHolder

        const nombre = document.createElement('h5')
        nombre.className = "card-title text-center text-dark"
        nombre.textContent = mascota.title  //cambiar luego de prueba con placeHolder

        const descripcion = document.createElement('p')
        descripcion.className = "card-title text-center text-dark"
        descripcion.textContent = mascota.descripcion  //cambiar luego de prueba con placeHolder

        const botones = document.createElement('div')
        botones.className = "d-flex justify-content-center align-items-center"

        const botonesInterno = document.createElement('div')
        botonesInterno.className = "btn-group d-flex justify-content-center"

        const boton1 = document.createElement('button')
        boton1.className = "btn btn-primary botones-cards"
        boton1.textContent = "Leer Mas"
        boton1.setAttribute("data-toggle","modal")
        boton1.setAttribute("data-target","#exampleModalCenter")
        boton1.setAttribute("onclick",'CargarUnaMascota(' + mascota.id + ')') //cambiar por el metodo de consulta

        //const boton2 = document.createElement('a')
        //boton2.className = "btn btn-primary ms-2"
        //boton2.textContent = "Sobre Mi"

        botonesInterno.append(boton1)

        botones.append(botonesInterno)

        card_body.append(nombre,descripcion,botones)

        card.append(img,card_body)

        col.append(card)

        row.append(col)

    }

  }

  spinner.style.display = "none";

}

function removerChildNodes(parent){

    while(parent.firstChild){
        parent.removeChild(parent.firstChild)
    }

}

async function CargarUnaMascota(id){

    const requestMascota = await fetch('https://jsonplaceholder.typicode.com/photos/'+ id +'', {
      method: 'GET',
      headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
      }
  
    });
    const mascota = await requestMascota.json(); //lo transforman a json

    console.log("---------------------------------------------------------------")

    console.log(mascota)

    showModal(mascota.title,mascota.title,mascota.url) //modificar a la hora de agregar los datos de la mascota


}

//---------------------------------------------------------------------------------


const showModal = (title, description, imagen, yesBtnLabel = 'Yes', noBtnLabel = 'Cancel', callback) => {

    
/**
 * 
 * @param {string} title 
 * @param {string} description content of modal body 
 * @param {string} imagen content of modal body 
 * @param {string} yesBtnLabel label of Yes button 
 * @param {string} noBtnLabel label of No button 
 * @param {function} callback callback function when click Yes button
 */

    var modalWrap = null;


  if (modalWrap !== null) {
    modalWrap.remove();
  }

  modalWrap = document.createElement('div');
  modalWrap.innerHTML = `
    <div class="modal fade" tabindex="-1">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header bg-light">
            <h5 class="modal-title">${title}</h5>

            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <img src="${imagen}" alt="">
            <p>${description}</p>
          </div>
          <div class="modal-footer bg-light">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">${noBtnLabel}</button>
            <button type="button" class="btn btn-primary modal-success-btn" data-bs-dismiss="modal">${yesBtnLabel}</button>
          </div>
        </div>
      </div>
    </div>
  `;

  modalWrap.querySelector('.modal-success-btn').onclick = callback;

  document.body.append(modalWrap);

  var modal = new bootstrap.Modal(modalWrap.querySelector('.modal'));
  modal.show();
}








