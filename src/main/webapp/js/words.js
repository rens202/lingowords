let dropdown = document.getElementById("wordlists");
let words = document.getElementById("words");
let languages = document.getElementById("languages");
let languagesDropdown = document.getElementById("languageDropdown");

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
                			option = document.createElement('option');
                            option.text = data[i].name;
                            option.value = data[i].id;
                            languagesDropdown.add(option);
                			}
                		languages.innerHTML = res;
                	})
                	}
                }
            ).catch(error => console.error(error));
}

document.getElementById("deleteWordList").onclick = function deleteWordslistPressed(){
	let selectedOption = dropdown.options[dropdown.selectedIndex].value;
	deleteWordList(selectedOption);
}

function deleteWordList(selectedOption){
	let url = "restservices/words/wordlists/" + selectedOption
	let fetchoptions = {
            method: 'DELETE',
            headers: {
                "Content-Type": "application/json",
            }
        };
        fetch(url, fetchoptions)
            .then(function (response) {
                if (response.ok) {
                	response.json().then(function (data) {
                		if(data){
                			alert('Succesfully deleted list + words')
                			location.reload();
                		}
	
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
        fetch("restservices/words/wordlists", fetchoptions)
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
	let selectedOption = dropdown.options[dropdown.selectedIndex].value;
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

document.getElementById("insertNewWord").onclick = function addWordToSelectedListPressed(){
	let selectedOption = dropdown.options[dropdown.selectedIndex].value;
	let newword = document.getElementById("newWord").value;
	
	if(newword){
		addWordToSelectedList(selectedOption, newword);
	}
}

function addWordToSelectedList(wordListId, newword){
	let url = "restservices/words/wordlists/" + wordListId + "/" + newword
	let fetchoptions = {
            method: 'PUT',
            headers: {
                "Content-Type": "application/json",
            }
        };
        fetch(url, fetchoptions)
            .then(function (response) {
                if (response.ok) {
                	response.json().then(function (data) {
                		if(data){
                			alert("Succesfully added " + newword);
                			location.reload();
                		}
                	})
                }
                }
            ).catch(error => console.error(error));
	
	
}

document.getElementById("insertNewWord").onclick = function createNewListPressed(){
	let selectedOption = languagesDropdown.options[languagesDropdown.selectedIndex].value;
	let newWordUrl = document.getElementById("newWordUrl").value;
	let newWordListName = document.getElementById("newWordListName").value;
	
	if(newword){
		addWordToSelectedList(selectedOption, newword);
	}
}





getWordLists();
getLanguages();