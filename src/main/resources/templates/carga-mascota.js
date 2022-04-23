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

    var i = mascota.id;

    if(i >= offset && i<=limit){
            
        spinner.style.display = "block";

        console.log(mascota);

        const col = document.createElement('div')
        col.className ="col"

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

        const boton1 = document.createElement('a')
        boton1.className = "btn btn-primary"
        boton1.textContent = "Adoptar"

        const boton2 = document.createElement('a')
        boton2.className = "btn btn-primary ms-2"
        boton2.textContent = "Sobre Mi"

        botonesInterno.append(boton1,boton2)

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
