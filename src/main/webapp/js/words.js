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
	let url = "restservices/words/" + selectedOption
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
        fetch("restservices/words", fetchoptions)
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

document.getElementById("insertNewLanguage").onclick = function newLanguagePressed(){
	let newLanguageName = document.getElementById("newLanguageName").value;
	let newLanguageCode = document.getElementById("newLanguageCode").value;
	newLanguage(newLanguageName, newLanguageCode);
}

document.getElementById("infoWordList").onclick = function getWordsFromListPressed(){
	let selectedOption = dropdown.options[dropdown.selectedIndex].value;
	getWordsFromList(selectedOption);
}


function newLanguage(newLanguageName, newLanguageCode){

	let json = '{'
		+'"name": "' + newLanguageName + '",'
		+'"code": "' + newLanguageCode + '"}'
	
	if(newLanguageName && newLanguageCode && newLanguageCode.length < 4){
		let url = "restservices/languages";
		let fetchoptions = {
	            method: 'POST',
	            headers: {
	                "Content-Type": "application/json",
	            },
	            body: json
	        };
	        fetch(url, fetchoptions)
	            .then(function (response) {
	                if (response.ok) {
	                	alert("Succesfully added language");
	                	}
	                })
	              }else{alert("Something went wrong, please try again");}
	
	
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
	let url = "restservices/words/" + wordListId + "/" + newword
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

document.getElementById("submitWordList").onclick = function createNewListPressed(){
	let newWordListLanguage = languagesDropdown.options[languagesDropdown.selectedIndex].value;
	let newWordListUrl = document.getElementById("newWordUrl").value;
	let newWordListName = document.getElementById("newWordListName").value;
	
	let json = '{'
		+'"name": "' + newWordListName + '",'
		+'"url": "' + newWordListUrl + '",'
		+'"language": ' + newWordListLanguage + '}'
	
	if(newWordListLanguage && newWordListUrl && newWordListName && newWordListUrl.includes(".")){
		let url = "restservices/words"
		let fetchoptions = {
	            method: 'POST',
	            headers: {
	                "Content-Type": "application/json",
	            },
	            body: json
	        };
	        fetch(url, fetchoptions)
	            .then(function (response) {
	                if (response.ok) {
	                	alert("succesfully added wordlist")
	                	location.reload();
	                	}
	                })
	              }else{alert("Something went wrong, please try again")}
	}






getWordLists();
getLanguages();