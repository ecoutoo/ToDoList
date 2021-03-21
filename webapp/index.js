'use strict'

let toDoListId = document.querySelector("#listNumForm");

function readAll() {
    clearInfo();
    fetch("http://localhost:8080/todolist", {
    method: 'get',    
    }) // 1  
    .then((response) => {
        if (response.status !== 200) {  //  2
            console.error(`status: ${reponse.status}`);
            return;
        }
        response.json() // 3
        .then(data => {
            console.info(data)
            for (var i = 0; i < data.length; i++) {
                displayList(data[i])
            }
            querySuccess();
        }) // 4
    }).catch((err)=> {
        console.error(`${err}`)
        queryFailure();
    }); // 5
}

function readOne() {
    clearInfo();
    fetch("http://localhost:8080/todolist/" + toDoListId.value, {
    method: 'get',    
    }) // 1  
    .then((response) => {
        if (response.status !== 200) {  //  2
            console.error(`status: ${reponse.status}`);
            return;
        }
        response.json() // 3
        .then(data => {
            console.info(data)
            displayList(data)
            querySuccess();
        })//4
    }).catch((err)=> {
        console.error(`${err}`)
        queryFailure();
    }); // 5

}

function displayList(data){
    console.log("Displaying List")
    let parent = document.querySelector('.listDiv');
    let div = document.createElement('div');
    div.innerHTML = 'LIST ' + data.listId + ' ' + data.title + ', Done?: ' + data.completed;
    parent.appendChild(div);
}

function displayItem(data){
    console.log("Displaying Item")
    let parent = document.querySelector('.listDiv');
    let div = document.createElement('div');
    div.innerHTML = data.itemId + ': ' + data.textBody + ', Done?: ' + data.completed;
    parent.appendChild(div);
}

const querySuccess = () => {
    let parent = document.querySelector('h1');
    parent.innerHTML = "QUERY SUCCESS";
}

const queryFailure = () => {
    let parent = document.querySelector('h1');
    parent.innerHTML = "QUERY FALIURE";
}

const clearInfo = () => {
    console.log("Information cleared")
    let parent = document.querySelector('.listDiv');
    parent.innerHTML = "";
}

function fetchPost1() {
fetch("https://reqres.in/api/users", {
    method: 'post',
    headers: {
        "Content-type": "application/json"
    },
    body: JSON.stringify({
       name: "Morpheus",
       job: "Leader"
      })
    })
    .then(res => res.json())
    .then((data)=> {
        console.log(`Request succeeded with JSON response ${data}`);
    })
    .catch((error)=> {
        console.log(`Request failed ${error}`);
    });
}