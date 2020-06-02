let dropdown = document.getElementById("wordlists");
let words = document.getElementById("words");
let languages = document.getElementById("languages");

function getLanguages(){
	let fetchoptions = {
            method: 'GET',
            headers: {
                "Content-Type": "application/json",
            }
        };
        fetch("restservices/languages", fetchoptions)
            .then(function (response) {
                if (response.ok) {
                	response.json().then(function (data) {
                		let res = "";
                		for (let i = 0; i < data.length; i++) {
                			res += '<div id=language' + data[i].id + '>' + data[i].name + '</div>'
                		}
                		languages.innerHTML = res;
	
                	})
                }
                }
            ).catch(error => console.error(error));
}


function deleteWordList(){
	let fetchoptions = {
            method: 'DELETE',
            headers: {
                "Content-Type": "application/json",
            }
        };
        fetch("restservices/words/wordlists", fetchoptions)
            .then(function (response) {
                if (response.ok) {
                	response.json().then(function (data) {
                		console.log(data);
	
                	})
                }
                }
            ).catch(error => console.error(error));
}




function getWordLists(){
	let fetchoptions = {
            method: 'GET',
            headers: {
                "Content-Type": "application/json",
            }
        };
        fetch("restservices/words/wordslists", fetchoptions)
            .then(function (response) {
                if (response.ok) {
                	response.json().then(function (data) {
                        let option;
                        for (let i = 0; i < data.length; i++) {
                            option = document.createElement('option');
                            option.text = data[i].name + " | " + data[i].language.code;
                            option.value = data[i].id;
                            dropdown.add(option);
                        }
                    });
                    return;               	
                }
                }).catch(error => console.error(error));
	
}

document.getElementById("infoWordList").onclick = function getWordsFromListPressed(){
	var selectedOption = dropdown.options[dropdown.selectedIndex].value;
	console.log(selectedOption);
	getWordsFromList(selectedOption);
}

function getWordsFromList(wordListId){
	let url = "restservices/words/" + wordListId;
	let fetchoptions = {
            method: 'GET',
            headers: {
                "Content-Type": "application/json",
            }
        };
        fetch(url, fetchoptions)
            .then(function (response) {
                if (response.ok) {
                	response.json().then(function (data) {
                        console.log(data);
                        let result = "<ul>";
                        for (let i = 0; i < data.length; i++) {
                        	result += '<li>'+ data[i].word + '</li>';
                        }
                        result += "</ul>"
                        words.innerHTML = result;
                    });
                    return;               	
                }
                }).catch(error => console.error(error));
	
}


getWordLists();
getLanguages();