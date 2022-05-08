$(document).ready(function(){

    //limites de la paginacion
    offset = 1;//parametro del inicio de la paginacion
    limit = 12;//parametro final de la paginacion
    cargarMascotas(offset,limit);//metodo para cargar todas las mascotas van de 12 en 12

});

const spinner = document.querySelector("#spinner"); //selecciono las etiquetas del espiner
const anterior = document.querySelector("#anterior"); //selecciono el boton anterior de la paginacion
const siguiente = document.querySelector("#siguiente"); //selecciono el boton antesiguienterior de la paginacion

//evento de anterior pagina
anterior.addEventListener('click', ()=>{

    if(offset != 1){

        const row = document.querySelector(".misColumnas");
        offset -=12;
        limit -=12;
        removerChildNodes(row)
        cargarMascotas(offset,limit);

    }

})

//evento de siguiente pagina
siguiente.addEventListener('click', ()=>{
    
    const row = document.querySelector(".misColumnas");
    offset +=12;
    limit +=12;
    removerChildNodes(row)
    cargarMascotas(offset,limit);

})

//metodo para cargar las mascotas con limites
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

//metodo para remover los elementos hijos
function removerChildNodes(parent){

    while(parent.firstChild){
        parent.removeChild(parent.firstChild)
    }

}

//carga una sola mascota
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

//Metodo para mostrar ventana modal
const showModal = (title, description, imagen, callback) => {
yesBtnLabel = 'Yes';
noBtnLabel = 'Cancel';
    
/**
 * 
 * @param {string} title 
 * @param {string} description content of modal body 
 * @param {string} imagen content of modal body 
 * @param {function} callback callback function when click Yes button
 * 
 */

    var modalWrap = null;


  if (modalWrap !== null) {
    modalWrap.remove();
  }

  modalWrap = document.createElement('div');
  modalWrap.innerHTML = `

    <div class="modal fade" tabindex="1">
      
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header bg-light">
            <h5 class="modal-title text-center w-100">${title}</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body d-flex w-100">
            <img src="${imagen}" alt="" width="300" height="300">
            <div class="container d-block">

              <div class="d-flex">
                <h4 class="me-3"><strong>Sexo:</strong> Macho</h4>
              </div>

              <div class="d-flex">
                <h4 class="me-3"><strong>Tamaño:</strong> Mediano</h4>
              </div>

              <div class="d-flex">
                <h4 class="me-3"><strong>Ubicacion:</strong> Tafi Viejo (Tucuman)</h4>
              </div>

              <div class="d-block">
                <h4 class="me-3"><strong>Descripcion:</strong> </h4> <h5 class="descripcion_mascota"><span>Lorem 
                ipsum dolor sit amet consectetur adipisicing elit. Quo, repellendus! Eveniet, fugit officia 
                ccusantium possimus debitis explicabo quam aperiam dicta.</span> </h5>
              </div>

            </div>
            <!--<p>${description}</p>-->
          </div>
          <div class="modal-footer bg-light">

            <ul class="nav col-md-4 justify-content-end list-unstyled d-flex">

                <li class="ms-3"><a class="text-muted" href="#"><i class="bi-whatsapp"></i></use></svg></a></li> <!--whatsapp-->

                <!----------------------------------------------------------------------------------------------->

            </ul>

            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" hidden>${noBtnLabel}</button>
            <button type="button" class="btn btn-primary modal-success-btn" data-bs-dismiss="modal" hidden>${yesBtnLabel}</button>
          </div>
        </div>
      </div>
    </div>
  `;

  modalWrap.querySelector('.modal-success-btn').onclick = callback;

  document.body.append(modalWrap);

  var modal = new bootstrap.Modal(modalWrap.querySelector('.modal'));
  modal.show();

  //mostrarTutorialBasico();
  
}

function mostrarTutorialBasico(){

    const body = document.querySelector("#prueba");
    body.innerHTML = `

        <div class="spotlight">
      
      
        </div>

        <h1>Estos Son los Contactos<br>del Actual Dueño</h1>

    `;

    body.style.display = "block";

  body.addEventListener('click', ()=>{

    body.style.display = "none";
    //body.setAttribute("hidden","")

})


}







