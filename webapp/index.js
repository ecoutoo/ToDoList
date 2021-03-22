'use strict'

let id = document.querySelector("#listNumForm");

let listId = document.querySelector("#NumForm");

let dataType = document.querySelector("#datatype");

let textData = document.querySelector("#listNameForm");

let doneBool = document.querySelector("#myCheck");

function readAll() {
    clearInfo();
    getAllItems();
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
                let currentId = data[i].listId
            }
            querySuccess();
            return;
        }) // 4
    }).catch((err)=> {
        console.error(`${err}`)
    }); // 5
    queryFailure();
}

function readOne() {
    clearInfo();
    fetch("http://localhost:8080/todolist/" + id.value, {
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
    }); // 5
    queryFailure();
}

function deleteOne(){
    if (dataType.value == "listdata") {
        fetch("http://localhost:8080/todolist/" + id.value, {
            method: 'delete'
        }).then(res => {
            if (res.status === 200) {
                console.info(id.value + " deleted")
                querySuccess();
                return;
            } else {
                console.error(`Request failed ${res.body}`)
            }
        }).catch((error) => console.error(`Request failed ${error}`))
    }
    else if (dataType.value == "itemdata"){
        fetch("http://localhost:8080/item/" + id.value, {
            method: 'delete'
        }).then(res => {
            if (res.status === 200) {
                console.info(id.value + " deleted")
                querySuccess();
                return;
            } else {
                console.error(`Request failed ${res.body}`)
            }
        }).catch((error) => console.error(`Request failed ${error}`))
    }
    queryFailure();
}

function createOne(){
    clearInfo();
    if (dataType.value == "listdata") {
        fetch("http://localhost:8080/todolist", {
            method: 'post',
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify({
                "title": textData.value,
                "completed": document.getElementById("myCheck").checked
            })
        }).then(res => res.json())
            .then((data) => {
                displayList(data);
                querySuccess();
                return;
            })
            .catch((error) => console.error(`Request failed ${error}`))
    }
    else if (dataType.value == "itemdata"){
        fetch("http://localhost:8080/item", {
            method: 'post',
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify({
                "textBody": textData.value,
                "taskDone": document.getElementById("myCheck").checked,
                "toDoList": {
                    "listId": id.value
                }
            })
        }).then(res => res.json())
            .then((data) => {
                querySuccess();
                return;
            })
            .catch((error) => console.error(`Request failed ${error}`))
    }
    queryFailure();
}

function updateOne(){
    clearInfo();
    if (dataType.value == "listdata") {
        fetch("http://localhost:8080/todolist/" + id.value, {
            method: 'put',
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify({
                "title": textData.value,
                "completed": document.getElementById("myCheck").checked
            })
        }).then(res => res.json())
            .then((data) => {
                displayList(data);
                querySuccess();
                return;
            })
            .catch((error) => console.error(`Request failed ${error}`))
    }
    else if (dataType.value == "itemdata"){
        fetch("http://localhost:8080/item/" + id.value, {
            method: 'put',
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify({
                "textBody": textData.value,
                "taskDone": document.getElementById("myCheck").checked,
                "toDoList": {
                    "listId": listId.value
                }
            })
        }).then(res => res.json())
            .then((data) => {
                querySuccess();
                return;
            })
            .catch((error) => console.error(`Request failed ${error}`))
    }
    queryFailure();
}

function getAllItems(){
    fetch("http://localhost:8080/item", {
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
                    displayItem(data[i])
                    let currentId = data[i].listId
                }
                querySuccess();
                return;
            }) // 4
        }).catch((err)=> {
            console.error(`${err}`)
        }); // 5
        queryFailure();
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
    div.innerHTML = 'TASK ' + data.itemId + ': ' + data.textBody + ', Done?: ' + data.taskDone;
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