'use strict'

function ReadAll() {
    fetch("http://localhost:8080/todolist", {
    method: 'get',    
    }) // 1  
    .then((response) => {
        if (response.status !== 200) {  //  2
            console.error(`status: ${reponse.status}`);
            return;
        }
        response.json() // 3
        .then(data => console.info(data)) // 4
    }).catch((err)=> console.error(`${err}`)); // 5
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