/* COSTANTI */

const apiPhotos =  'http://localhost:8080/api/photos';
const apiMessages = 'http://localhost:8080/api/messages';

let search = 0;

const root = document.getElementById("root");





/* FUNZIONI */


const doSearch = () => {
    search = document.getElementById("search").value;
    getPhotos(search);
}

// funzione che renderizza la card della pizza
const renderPhoto = (element) => {
    return `
    <div class="card mt-4" style="width: 95%;">
        <img src="${element.url}" class="card-img-top" style="height: 200px" alt="...">
        <div class="card-body">
            <h5 class="overflow card-title text-center">${element.title}</h5>
            <p class="overflow card-text text-center">${element.description}</p>
            <div class="d-flex justify-content-center">
                <a href="create.html" class="btn btn-primary"  >Details(to be implemented)</a>
            </div>
        </div>
    </div>
    `
    
} 


// funzione che renderizza la lista delle foto
const renderPhotoList = (data) => {
    let content;
    if(data.length >0 ) {
        content = '<div class="d-flex flex-wrap justify-content-around">';
        data.forEach((element) => {
            content += '<div class="picture-card d-flex">';
            content += renderPhoto(element);
            content += '</div>';
        });
        content += '</div>';
    } else {
        content = '<div class="alert alert-info">The list is empty</div>';
    }
    root.innerHTML = content;
};

//funzione che chiama l'API e ottiene il json con l'array di pizzas
const getPhotos = async (search) => {
    
    if (!search == 0) {
        try {
            const apiUrlSearch = 'http://localhost:8080/api/photos?search=' + search;
            const response = await axios.get(apiUrlSearch);
            renderPhotoList(response.data)
        }catch(error){
            console.log(error);
        }
    } else {
        try {
            const response = await axios.get(apiPhotos);
            renderPhotoList(response.data)
        }catch(error){
            console.log(error);
        }
    }
};


// funzione per creare un nuovo messaggio

document.getElementById('messageForm').addEventListener('submit', async function(event){
    event.preventDefault();
    const title = document.getElementById("title").value;
    const message = document.getElementById("message").value;
    const email = document.getElementById("email").value;
    try{
        await axios.post(apiMessages, {
            title,
            message,
            email,
        })
        window.location.href = "index.html";
    }catch(error){
        console.error(error);
    }
})




/* CODICE GLOBALE EESEGUITO AL LOAD DELLO SCRIPT */
getPhotos();

